package com.lasun.mobile.assistant.http;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import android.app.Dialog;
import android.content.Context;
import com.lasun.moblile.assistant.core.Parameter;
import com.lasun.moblile.assistant.core.ParameterUtils;

public class HttpRequester {
	public static final String POST = "POST";
	public static final String GET = "GET";
	private Context context;
	private Dialog dialog;
	public HttpRequester() {
	}
	public HttpRequester(Context context, Dialog dialog) {
		this.context = context;
		this.dialog = dialog;
	}
	public String syncRequest(String url, String httpMethod, List<Parameter> listParam, String apiVersion, boolean isUrlEncode) throws Exception {
		try {
			if (url == null || url.equals("")) {
				return null;
			}
			String queryString = "";
			List<Parameter> parameters = ParameterUtils.getRequiredParameter(listParam, apiVersion);
			if (isUrlEncode)
				queryString = formEncodeParameters(parameters);
			else
				queryString = normalizeRequestParameters(parameters);
			SyncHttpClient http = new SyncHttpClient(context, dialog);
			if (GET.equals(httpMethod)) {
				return http.httpGet(url, queryString);
			} else if (POST.equals(httpMethod)) {
				return http.httpPost(url, queryString);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 
	 * 方法说明： 同步HTTP请求
	 * 
	 * @param url 请求的URL地址
	 * @param httpMethod 请求方法 POST或者GET
	 * @param listParam 请求参数
	 * @param isUrlEncode 是否要对参数进行 URLENCODE 编码
	 * @return
	 * @throws Exception
	 */
	public String syncRequest(String url, String httpMethod, List<Parameter> listParam, boolean isUrlEncode) throws Exception {
		try {
			if (url == null || url.equals("")) {
				return null;
			}
			String queryString = "";
			List<Parameter> parameters = ParameterUtils.getRequiredParameter(listParam);
			if (isUrlEncode)
				queryString = formEncodeParameters(parameters);
			else
				queryString = normalizeRequestParameters(parameters);
			SyncHttpClient http = new SyncHttpClient(context, dialog);
			if (GET.equals(httpMethod)) {
				return http.httpGet(url, queryString);
			} else if (POST.equals(httpMethod)) {
				return http.httpPost(url, queryString);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 
	 * 方法说明： 同步HTTP请求
	 * 
	 * @param url 请求的URL地址
	 * @param httpMethod 请求方法 POST或者GET
	 * @param listParam 请求参数
	 * @param isUrlEncode 是否要对参数进行 URLENCODE 编码
	 * @return
	 * @throws Exception
	 */
	public String syncRequestLongTime(String url, String httpMethod, List<Parameter> listParam, boolean isUrlEncode) throws Exception {
		try {
			if (url == null || url.equals("")) {
				return null;
			}
			String queryString = "";
			List<Parameter> parameters = ParameterUtils.getRequiredParameter(listParam);
			if (isUrlEncode)
				queryString = formEncodeParameters(parameters);
			else
				queryString = normalizeRequestParameters(parameters);
			SyncHttpClient http = new SyncHttpClient(context, dialog);
			if (GET.equals(httpMethod)) {
				return http.executeHttpGetUpdatePassword(url, queryString);
			} else if (POST.equals(httpMethod)) {
				return http.executeHttpPostLongTime(url, queryString);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public String syncRequestLongTime(String url, String httpMethod, List<Parameter> listParam, String apiVersion, boolean isUrlEncode) throws Exception {
		try {
			if (url == null || url.equals("")) {
				return null;
			}
			String queryString = "";
			List<Parameter> parameters = ParameterUtils.getRequiredParameter(listParam, apiVersion);
			if (isUrlEncode)
				queryString = formEncodeParameters(parameters);
			else
				queryString = normalizeRequestParameters(parameters);
			SyncHttpClient http = new SyncHttpClient(context, dialog);
			if (GET.equals(httpMethod)) {
				return http.executeHttpGetUpdatePassword(url, queryString);
			} else if (POST.equals(httpMethod)) {
				return http.executeHttpPostLongTime(url, queryString);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public String syncRequest(String url, List<Parameter> listParam, String userAgent) throws Exception {
		try {
			if (url == null || url.equals("")) {
				return null;
			}
			String queryString = "";
			List<Parameter> parameters = ParameterUtils.getRequiredParameter(listParam);
			queryString = normalizeRequestParameters(parameters);
			SyncHttpClient http = new SyncHttpClient(context, dialog);
			return http.httpPostUserAgent(url, queryString, userAgent);
		} catch (Exception e) {
			return null;
		}
	}
	public String syncRequest(String url, String httpMethod, List<Parameter> listParam, boolean isUrlEncode, String data) throws Exception {
		try {
			if (url == null || url.equals("")) {
				return null;
			}
			String queryString = "";
			List<Parameter> parameters = ParameterUtils.getRequiredParameter(listParam);
			if (isUrlEncode)
				queryString = formEncodeParameters(parameters);
			else
				queryString = normalizeRequestParameters(parameters);
			SyncHttpClient http = new SyncHttpClient(context, dialog);
			if (GET.equals(httpMethod)) {
				return http.httpGet(url, queryString);
			} else if (POST.equals(httpMethod)) {
				return http.httpPost(url, queryString, data);
			}
		} catch (Exception e) {
		}
		return null;
	}
	/**
	 * 
	 * 方法说明： 同步HTTP请求
	 * 
	 * @param url 请求的URL地址
	 * @param httpMethod 请求方法 POST或者GET
	 * @param listParam 请求参数
	 * @param isUrlEncode 是否要对参数进行 URLENCODE 编码
	 * @return
	 * @throws Exception
	 * @throws Exception
	 */
	public boolean asyncRequest(String url, String httpMethod, List<Parameter> listParam, AsyncHandler asyncHandler, Object cookie, boolean isUrlEncode) throws Exception {
		try {
			String queryString = "";
			List<Parameter> parameters = ParameterUtils.getRequiredParameter(listParam);
			if (isUrlEncode)
				queryString = formEncodeParameters(parameters);
			else
				queryString = normalizeRequestParameters(parameters);
			AsyncHttpClient asyncHttp = new AsyncHttpClient();
			if (GET.equals(httpMethod)) {
				return asyncHttp.httpGet(url, queryString, asyncHandler, cookie);
			} else if (POST.equals(httpMethod)) {
				return asyncHttp.httpPost(url, queryString, asyncHandler, cookie);
			}
			return false;
		} catch (Exception e) {
			return false;
		}
	}
	/**
	 * 
	 * 方法说明： 将参数拼成URL地址
	 * 
	 * @param parameters
	 * @return
	 */
	public static String normalizeRequestParameters(List<Parameter> parameters) {
		StringBuffer sb = new StringBuffer();
		Parameter p = null;
		for (int i = 0, size = parameters.size(); i < size; i++) {
			p = parameters.get(i);
			sb.append(p.mName);
			sb.append("=");
			sb.append(p.mValue);
			if (i < size - 1) {
				sb.append("&");
			}
		}
		return sb.toString();
	}
	/**
	 * 
	 * 方法说明： 对请求参数进行URL编码
	 * 
	 * @param parameters
	 * @return
	 */
	private String formEncodeParameters(List<Parameter> parameters) {
		List<Parameter> encodeParams = new ArrayList<Parameter>();
		for (Parameter a : parameters) {
			encodeParams.add(new Parameter(a.mName, encode(a.mValue)));
		}
		return normalizeRequestParameters(encodeParams);
	}
	private String encode(String value) {
		String encoded = null;
		try {
			encoded = URLEncoder.encode(value, "UTF-8");
			return encoded;
		} catch (UnsupportedEncodingException ignore) {
		}
		return null;
	}
}
