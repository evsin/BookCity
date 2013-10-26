/*******************************************************************************
 * Copyright (c) 2011 by lasun Corporation all right reserved.
 * 2011-8-13 
 * 
 *******************************************************************************/
package com.lasun.mobile.assistant.http;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;

/**
 * <pre>
 * 业务名:
 * 功能说明: 
 *    解决多线程 中使用HttpClient 的问题
 * 编写日期:	2011-8-13
 * 作者:	 李丰川
 * 
 * 历史记录
 * 1、修改日期：
 *    修改人：
 *    修改内容：
 * </pre>
 */
public class HttpClientHelper {
	public static HttpClient createHttpClient() {
		MultiThreadedHttpConnectionManager connectionManager = new MultiThreadedHttpConnectionManager();
		HttpConnectionManagerParams params = connectionManager.getParams();
		params.setConnectionTimeout(10 * 1000);
		params.setSoTimeout(10 * 1000);
		params.setMaxTotalConnections(30);
		connectionManager.setParams(params);
		HttpClient client = new HttpClient(connectionManager);
		return client;
	}
}
