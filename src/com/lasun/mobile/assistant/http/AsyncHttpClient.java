package com.lasun.mobile.assistant.http;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;

public class AsyncHttpClient {
	private static final int CONNECTION_TIMEOUT = 20000;
	/**
	 * Using asynchronously GET method.
	 * 
	 * @param url The remote URL.
	 * @param queryString The query string containing parameters
	 * @param callback Callback handler.
	 * @param cookie Cookie response to handler.
	 * @return Whether request has started.
	 */
	public boolean httpGet(String url, String queryString, AsyncHandler callback, Object cookie) {
		if (url == null || url.equals("")) {
			return false;
		}
		if (queryString != null && !queryString.equals("")) {
			url += "?" + queryString;
		}
		GetMethod httpGet = new GetMethod(url);
		httpGet.getParams().setParameter("http.socket.timeout", new Integer(CONNECTION_TIMEOUT));
		mThreadPool.submit(new AsyncThread(httpGet, callback, cookie));
		return true;
	}
	/**
	 * Using asynchronously POST method.
	 * 
	 * @param url The remote URL.
	 * @param queryString The query string containing parameters
	 * @param callback Callback handler.
	 * @param cookie Cookie response to handler.
	 * @return Whether request has started.
	 */
	public boolean httpPost(String url, String queryString, AsyncHandler callback, Object cookie) {
		if (url == null || url.equals("")) {
			return false;
		}
		if (queryString != null && !queryString.equals("")) {
			url += "?" + queryString;
		}
		PostMethod httpPost = new PostMethod(url);
		httpPost.addParameter("Content-Type", "application/x-www-form-urlencoded");
		httpPost.getParams().setParameter("http.socket.timeout", new Integer(CONNECTION_TIMEOUT));
		mThreadPool.submit(new AsyncThread(httpPost, callback, cookie));
		return true;
	}
	private ExecutorService mThreadPool = Executors.newFixedThreadPool(20);

	/**
	 * Thread for asynchronous HTTP request.
	 * 
	 */
	class AsyncThread extends Thread {
		private HttpMethod mHttpMedthod;
		private AsyncHandler mAsyncHandler;
		private Object mCookie;
		public AsyncThread(HttpMethod method, AsyncHandler handler, Object cookie) {
			this.mHttpMedthod = method;
			this.mAsyncHandler = handler;
			this.mCookie = cookie;
		}
		@Override
		public void run() {
			String responseData = null;
			HttpClient httpClient = HttpClientHelper.createHttpClient();
			int statusCode = -1;
			try {
				statusCode = httpClient.executeMethod(mHttpMedthod);
				if (statusCode != HttpStatus.SC_OK) {
					System.err.println("HttpMethod failed: " + mHttpMedthod.getStatusLine());
				}
				responseData = mHttpMedthod.getResponseBodyAsString();
			} catch (HttpException e) {
				e.printStackTrace();
				if (mAsyncHandler != null) {
					mAsyncHandler.onThrowable(e, mCookie);
				}
				return;
			} catch (IOException e) {
				e.printStackTrace();
				if (mAsyncHandler != null) {
					mAsyncHandler.onThrowable(e, mCookie);
				}
				return;
			} finally {
				mHttpMedthod.releaseConnection();
				httpClient = null;
			}
			if (mAsyncHandler != null) {
				mAsyncHandler.onCompleted(statusCode, responseData, mCookie);
			}
		}
	}
}
