package com.lasun.mobile.assistant.http;

import java.io.File;
import java.io.FileWriter;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
import android.app.Dialog;
import android.content.Context;
import android.os.Environment;
import android.util.Log;
import com.lasun.mobile.assistant.utils.TimeOut;

public class SyncHttpClient {
	private static final int CONNECTION_TIMEOUT = 15000;
	private Context context;
	private Dialog dialog;
	public SyncHttpClient() {
	}
	public SyncHttpClient(Context context, Dialog dialog) {
		this.context = context;
		this.dialog = dialog;
	}
	/**
	 * 手机用户登录需要传递 User-Agent
	 * 
	 * @throws Exception
	 */
	public String httpPostUserAgent(String url, String queryString, String userAgent) throws Exception {
		String responseData = null;
		int retry = 2;
		int count = 0;
		while (count < retry) {
			count = count + 1;
			try {
				responseData = executeHttpPostUserAgent(url, queryString, userAgent);
				return responseData;
			} catch (Exception e) {
				if (count < retry) {
					responseData = executeHttpPostUserAgent(url, queryString, userAgent);
					return responseData;
				} else {
				}
			}
		}
		return responseData;
	}
	/**
	 * 手机用户登录需要传递 User-Agent
	 * 
	 * @throws Exception
	 */
	private String executeHttpPostUserAgent(String url, String queryString, String userAgent) throws Exception {
		String responseData = null;
		HttpClient httpClient = new HttpClient();
		if (queryString != null && !queryString.equals("")) {
			url += "?" + queryString;
		}
		Log.i("URL2", url);
		final PostMethod httpPost = new PostMethod(url.replace(" ", "%20"));
		httpPost.addParameter("Content-Type", "application/x-www-form-urlencoded");
		httpPost.getParams().setParameter("http.socket.timeout", Integer.valueOf(CONNECTION_TIMEOUT));
		try {
			int statusCode = httpClient.executeMethod(httpPost);
			if (statusCode != HttpStatus.SC_OK) {
				System.err.println("HttpPost Method failed: " + httpPost.getStatusLine());
			}
			responseData = httpPost.getResponseBodyAsString();
			Log.i("responseData", responseData);
		} catch (Exception e) {
		} finally {
			httpPost.releaseConnection();
			httpClient = null;
		}
		return responseData;
	}
	public String httpGet(String url, String queryString) throws Exception {
		String responseData = null;
		int retry = 2;
		int count = 0;
		while (count < retry) {
			count = count + 1;
			try {
				responseData = executeHttpGet(url, queryString);
				return responseData;
			} catch (Exception e) {
				if (count < retry) {
					responseData = executeHttpGet(url, queryString);
					return responseData;
				} else {
				}
			}
		}
		return responseData;
	}
	public String executeHttpGetUpdatePassword(String url, String queryString) throws Exception {
		String responseData = null;
		if (queryString != null && !queryString.equals("")) {
			url += "?" + queryString;
		}
		int timeOut = 100 * 1000;
		MultiThreadedHttpConnectionManager connectionManager = new MultiThreadedHttpConnectionManager();
		HttpConnectionManagerParams params = connectionManager.getParams();
		params.setConnectionTimeout(timeOut);
		params.setSoTimeout(timeOut);
		params.setMaxTotalConnections(10);
		connectionManager.setParams(params);
		HttpClient httpClient = new HttpClient(connectionManager);
		Log.i("info", "url==" + url);
		GetMethod httpGet = new GetMethod(url);
		httpGet.getParams().setParameter("http.socket.timeout", Integer.valueOf(CONNECTION_TIMEOUT));
		try {
			int statusCode = httpClient.executeMethod(httpGet);
			if (statusCode != HttpStatus.SC_OK) {
				System.err.println("HttpGet Method failed: " + httpGet.getStatusLine());
			}
			responseData = httpGet.getResponseBodyAsString();
			Log.i("info", "responseData==" + responseData);
			TimeOut.endTimer(httpGet);
		} catch (Exception e) {
		} finally {
			httpGet.releaseConnection();
			httpClient = null;
		}
		return responseData;
	}
	/**
	 * Using POST method.
	 * 
	 * @param url The remote URL.
	 * @param queryString The query string containing parameters
	 * @return Response string.
	 * @throws Exception
	 */
	public String httpPost(String url, String queryString) throws Exception {
		String responseData = null;
		int retry = 2;
		int count = 0;
		while (count < retry) {
			count = count + 1;
			try {
				responseData = executeHttpPost(url, queryString);
				return responseData;
			} catch (Exception e) {
				if (count < retry) {
					responseData = executeHttpPost(url, queryString);
					return responseData;
				} else {
				}
			}
		}
		return responseData;
	}
	public String executeHttpGet(String url, String queryString) throws Exception {
		String responseData = null;
		if (queryString != null && !queryString.equals("")) {
			url += "?" + queryString;
		}
		Log.i("URL", url);
		HttpClient httpClient = HttpClientHelper.createHttpClient();
		GetMethod httpGet = new GetMethod(url);
		httpGet.getParams().setParameter("http.socket.timeout", Integer.valueOf(CONNECTION_TIMEOUT));
		try {
			int statusCode = httpClient.executeMethod(httpGet);
			if (statusCode != HttpStatus.SC_OK) {
				System.err.println("HttpGet Method failed: " + httpGet.getStatusLine());
			}
			responseData = httpGet.getResponseBodyAsString();
			Log.i("rcesponseData", responseData);
		} catch (Exception e) {
		} finally {
			httpGet.releaseConnection();
			httpClient = null;
		}
		return responseData;
	}
	public String executeHttpPost(String url, String queryString) throws Exception {
		String responseData = null;
		HttpClient httpClient = HttpClientHelper.createHttpClient();
		if (queryString != null && !queryString.equals("")) {
			url += "?" + queryString;
		}
		Log.i("URL", url);
		PostMethod httpPost = new PostMethod(url);
		httpPost.addParameter("Content-Type", "application/x-www-form-urlencoded");
		httpPost.getParams().setParameter("http.socket.timeout", Integer.valueOf(CONNECTION_TIMEOUT));
		try {
			int statusCode = httpClient.executeMethod(httpPost);
			if (statusCode != HttpStatus.SC_OK) {
				System.err.println("HttpPost Method failed: " + httpPost.getStatusLine());
			}
			responseData = httpPost.getResponseBodyAsString();
			Log.i("responseData", responseData);
		} catch (Exception e) {
		} finally {
			httpPost.releaseConnection();
			httpClient = null;
		}
		return responseData;
	}
	public String executeHttpPostLongTime(String url, String queryString) throws Exception {
		String responseData = null;
		int timeOut = 100 * 1000;
		MultiThreadedHttpConnectionManager connectionManager = new MultiThreadedHttpConnectionManager();
		HttpConnectionManagerParams params = connectionManager.getParams();
		params.setConnectionTimeout(timeOut);
		params.setSoTimeout(timeOut);
		params.setMaxTotalConnections(10);
		connectionManager.setParams(params);
		HttpClient httpClient = new HttpClient(connectionManager);
		if (queryString != null && !queryString.equals("")) {
			url += "?" + queryString;
		}
		Log.i("URL", url);
		PostMethod httpPost = new PostMethod(url);
		httpPost.addParameter("Content-Type", "application/x-www-form-urlencoded");
		httpPost.getParams().setParameter("http.socket.timeout", Integer.valueOf(CONNECTION_TIMEOUT));
		try {
			int statusCode = httpClient.executeMethod(httpPost);
			if (statusCode != HttpStatus.SC_OK) {
				System.err.println("HttpPost Method failed: " + httpPost.getStatusLine());
			}
			responseData = httpPost.getResponseBodyAsString();
			Log.i("responseData", responseData);
		} catch (Exception e) {
		} finally {
			httpPost.releaseConnection();
			httpClient = null;
		}
		return responseData;
	}
	public String httpPost(String url, String queryString, String data) throws Exception {
		String responseData = null;
		int retry = 2;
		int count = 0;
		while (count < retry) {
			count = count + 1;
			try {
				responseData = executeHttpPost(url, queryString, data);
				return responseData;
			} catch (Exception e) {
				if (count < retry) {
					responseData = executeHttpPost(url, queryString, data);
					return responseData;
				} else {
				}
			}
		}
		return responseData;
	}
	private String executeHttpPost(String url, String queryString, String data) throws Exception {
		String responseData = null;
		HttpClient httpClient = HttpClientHelper.createHttpClient();
		if (queryString != null && !queryString.equals("")) {
			url += "?" + queryString;
		}
		PostMethod httpPost = new PostMethod(url);
		httpPost.addParameter("Content-Type", "application/x-www-form-urlencoded");
		httpPost.getParams().setParameter("http.socket.timeout", Integer.valueOf(CONNECTION_TIMEOUT));
		try {
			int statusCode = httpClient.executeMethod(httpPost);
			if (statusCode != HttpStatus.SC_OK) {
				System.err.println("HttpPost Method failed: " + httpPost.getStatusLine());
			}
			responseData = httpPost.getResponseBodyAsString();
		} catch (Exception e) {
		} finally {
			httpPost.releaseConnection();
			httpClient = null;
		}
		return responseData;
	}
	/**
	 * 将文件写入sd卡
	 */
	public Boolean w2SD(String sdpath, String filename, String mString) {
		if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
			try {
				File file = new File(sdpath, filename);
				if (!file.exists()) {
					file.createNewFile();
				}
				FileWriter fileWriter = new FileWriter(sdpath + filename, true);
				String netline = System.getProperty("line.separator");
				fileWriter.write(mString + netline);
				fileWriter.flush();
				fileWriter.close();
				return true;
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		} else {
			return false;
		}
	}
	public Boolean w2SD(String mString) {
		String sdpath = Environment.getExternalStorageDirectory() + "/";
		String filename = "HttpClientURL.txt";
		return this.w2SD(sdpath, filename, mString);
	}
}
