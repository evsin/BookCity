package com.lansun.mobile.assistant.notifydownload;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.util.EntityUtils;

public class HttpUtil {
	public static final int METHOD_GET = 1;
	public static final int METHOD_POST = 2;
	public static final String BASE_URL = "";

	public static HttpEntity getEntity(String uri,
			ArrayList<BasicNameValuePair> params, int method) {
		HttpEntity entity = null;
		HttpClient client = new DefaultHttpClient();
		
		client.getParams().setParameter(
				CoreConnectionPNames.CONNECTION_TIMEOUT, 4000);
		HttpRequest request = null;
		try {
			switch (method) {
			case METHOD_GET:
				StringBuilder builder = new StringBuilder(uri);
				if (params != null && !params.isEmpty()) {
					builder.append("?");
					for (BasicNameValuePair param : params) {
						builder.append(param.getName()).append("=")
								.append(param.getValue()).append("&");
					}
					builder.deleteCharAt(builder.length() - 1);
				}
				request = new HttpGet(builder.toString());
				break;

			case METHOD_POST:
				request = new HttpPost(uri);
				if (params != null && params.isEmpty()) {
					UrlEncodedFormEntity urlEntiry = new UrlEncodedFormEntity(
							params);
					((HttpPost) request).setEntity(urlEntiry);
				}
				break;
			}
			HttpResponse response = client.execute((HttpUriRequest) request);
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				entity = response.getEntity();
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return entity;
	}

	public static InputStream getInputStream(String uri,
			ArrayList<BasicNameValuePair> params, int method)
			throws IOException {
		HttpEntity entity = getEntity(uri, params, method);
		if (entity != null) {
			return entity.getContent();
		}
		return null;
	}

	public static InputStream getStream(HttpEntity entity) throws IOException {
		if (entity != null) {
			return entity.getContent();
		} else {
			return null;
		}
	}

	/**
	 * 根据响应实体获得字节数组
	 * 
	 * @throws IOException
	 */
	public static byte[] getBytes(String uri,
			ArrayList<BasicNameValuePair> params, int method)
			throws IOException {
		HttpEntity entity = getEntity(uri, params, method);
		if (entity != null) {
			return EntityUtils.toByteArray(entity);
		} else {
			return null;
		}
	}

	/** 根据响应实体获得其长度 */
	public static long getLength(HttpEntity entity) {
		if (entity != null) {
			return entity.getContentLength();
		} else {
			return -1;
		}
	}

}
