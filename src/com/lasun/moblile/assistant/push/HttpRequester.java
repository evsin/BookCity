package com.lasun.moblile.assistant.push;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
import org.json.JSONException;
import org.json.JSONObject;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;
import android.util.Log;

public class HttpRequester {
	public static final String VERSION = "APN_SDT_1.0";
	private static final int CONNECTION_TIMEOUT = 15;
	private static final int MAX_TOTAL_CONNECTIONS = 3;
	public static final String REQUEST = "http://211.103.232.50:8082/apn/AppClient/receive.do";
	public static final String REBACK = "http://211.103.232.50:8082/apn/AppClient/receiveBack.do";
	public HttpRequester() {
	}
	private String httpRequest(String url, Map<String, String> params) {
		StringBuffer buffer = new StringBuffer();
		for (String key : params.keySet()) {
			buffer.append(key).append("=").append(params.get(key)).append("&");
		}
		buffer.deleteCharAt(buffer.length() - 1);
		url += ("?" + buffer.toString());
		url = url.replace(" ", "%20");
		MultiThreadedHttpConnectionManager connectionManager = new MultiThreadedHttpConnectionManager();
		HttpConnectionManagerParams connectionManagerParams = connectionManager.getParams();
		connectionManagerParams.setConnectionTimeout(CONNECTION_TIMEOUT * 1000);
		connectionManagerParams.setSoTimeout(CONNECTION_TIMEOUT * 1000);
		connectionManagerParams.setMaxTotalConnections(MAX_TOTAL_CONNECTIONS);
		connectionManager.setParams(connectionManagerParams);
		HttpClient httpClient = new HttpClient(connectionManager);
		GetMethod getMethod = new GetMethod(url);
		getMethod.getParams().setParameter("http.socket.timeout", new Integer(CONNECTION_TIMEOUT * 1000));
		String responseString = null;
		try {
			int statusCode = httpClient.executeMethod(getMethod);
			if (statusCode != HttpStatus.SC_OK) {
			} else {
				responseString = getMethod.getResponseBodyAsString();
			}
		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return responseString;
	}
	public List<Message> httpReceiveMore(String user, String baseurl) {
		List<Message> msg = null;
		Map<String, String> params = new HashMap<String, String>();
		params.put("userName", user);
		params.put("version", VERSION);
		String json = httpRequest(baseurl, params);
		if (TextUtils.isEmpty(json)) {
			return null;
		}
		msg = parseJsonStringToMessages(json);
		return msg;
	}
	private List<Message> parseJsonStringToMessages(String jsonString) {
		List<Message> messages = new ArrayList<Message>();
		List<Map<String, Object>> list = GsonUtile.getMap(jsonString);
		for (Map<String, Object> map : list) {
			Log.i("info", "list.size()" + list.size());
			Message message = new Message();
			message.setStatus((String) map.get("statusMsg"));
			message.setCode(Integer.parseInt((String) map.get("statusCode")));
			if (message.getCode() == 100) {
				message.setJobTime((String) map.get("jobTime"));
				Map<String, String> values = (Map<String, String>) map.get("values");
				message.setCreateTime(values.get("createTime"));
				message.setPicUrl(values.get("picUrl"));
				message.setId(values.get("id"));
				String mType = values.get("mType");
				Log.i("TYPE", "------>" + mType);
				if ("0".equals(mType)) {
					mType = "促销";
				} else if ("1".equals(mType)) {
					mType = "应用";
				} else if ("2".equals(mType)) {
					mType = "新品";
				} else if ("3".equals(mType)) {
					mType = "通知";
				}
				message.setmType(mType);
				message.setTitle(values.get("title"));
				message.setSearchID(values.get("searchID"));
				message.setType(values.get("type"));
				message.setDetails(values.get("details"));
				message.setDetailsText(values.get("detailsText"));
				message.setId(values.get("id"));
				message.setmLoad(values.get("mLoad"));
				message.setPushForce("1".equals(values.get("pushForce")));
				message.setDetId(values.get("detId"));
				message.setmWord(values.get("mWord"));
				messages.add(message);
			}
		}
		return messages;
	}
	public void httpMessageCount(String user, List<String> messageId) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("userName", user);
		params.put("version", VERSION);
		StringBuffer sb = new StringBuffer();
		for (String msgId : messageId) {
			sb.append(msgId + "_");
		}
		sb.deleteCharAt(sb.length() - 1);
		params.put("msgId", sb.toString());
		httpRequest(REBACK, params);
	}
	private String json2value(String json) {
		String values = null;
		try {
			JSONObject jsonObject = new JSONObject(json);
			values = jsonObject.getString("values");
			return values;
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return "true".equals(values) ? "true" : "false";
	}
	public Bitmap getImageRes(String url) {
		Bitmap bitmap = null;
		URL imageUrl = null;
		InputStream is = null;
		HttpURLConnection conn = null;
		try {
			if (url != null && !"".equals(url)) {
				String urlStr = url.replace(" ", "%20");
				imageUrl = new URL(urlStr);
				conn = (HttpURLConnection) imageUrl.openConnection();
				conn.setRequestMethod("GET");
				conn.setConnectTimeout(10 * 1000);
				conn.connect();
				conn.getHeaderFields();
				int nHttpResultCode = conn.getResponseCode();
				if (nHttpResultCode < HttpURLConnection.HTTP_BAD_REQUEST) {
					is = conn.getInputStream();
					if (nHttpResultCode / 100 == 2) {
						byte[] bt = getBytes(is);
						bitmap = BitmapFactory.decodeByteArray(bt, 0, bt.length);
						if (bitmap != null) {
							return bitmap;
						}
					}
				} else {
					InputStream errStream = conn.getErrorStream();
					if (errStream != null)
						errStream.close();
				}
				return null;
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (is != null) {
					is.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			if (conn != null) {
				conn.disconnect();
			}
		}
		return null;
	}
	private byte[] getBytes(InputStream is) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] b = new byte[1024];
		int len = 0;
		while ((len = is.read(b, 0, 1024)) != -1) {
			baos.write(b, 0, len);
			baos.flush();
		}
		byte[] bytes = baos.toByteArray();
		return bytes;
	}
}
