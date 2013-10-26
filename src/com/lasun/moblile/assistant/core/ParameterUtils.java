/*******************************************************************************
 * Copyright (c) 2011 by lasun Corporation all right reserved.
 * 2011-9-3 
 * 
 *******************************************************************************/
package com.lasun.moblile.assistant.core;

import java.io.UnsupportedEncodingException;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import com.lasun.mobile.assistant.utils.APIUtils;

/**
 * <pre>
 * </pre>
 */
public class ParameterUtils {
	/**
	 * 
	 * 
	 * 
	 * @param parameters
	 * @param apiVersion
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static List<Parameter> getRequiredParameter(List<Parameter> parameters, String apiVersion) throws UnsupportedEncodingException {
		if (parameters != null && parameters.size() > 0) {
			String appkey = APIUtils.getInstance().getValueByKey("appkey");
			String format = APIUtils.getInstance().getValueByKey("format");
			parameters.add(new Parameter("appkey", appkey));
			parameters.add(new Parameter("v", apiVersion));
			parameters.add(new Parameter("format", format));
			parameters.add(new Parameter("sign_type", "rsa"));
			parameters.add(new Parameter("timestamp", new Date().getTime() + ""));
			String sig = Parameter.getSigFromParamters(parameters);
			parameters.add(new Parameter("sig", sig));
			Collections.sort(parameters);
			return parameters;
		}
		return null;
	}
	/**
	 * 
	 * 
	 * @param parameters
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static List<Parameter> getRequiredParameter(List<Parameter> parameters) throws UnsupportedEncodingException {
		if (parameters != null && parameters.size() > 0) {
			String appkey = APIUtils.getInstance().getValueByKey("appkey");
			String v = APIUtils.getInstance().getValueByKey("v");
			String format = APIUtils.getInstance().getValueByKey("format");
			parameters.add(new Parameter("appkey", appkey));
			parameters.add(new Parameter("v", v));
			parameters.add(new Parameter("format", format));
			parameters.add(new Parameter("timestamp", new Date().getTime() + ""));
			String sig = Parameter.getSigFromParamters(parameters);
			parameters.add(new Parameter("sig", sig));
			Collections.sort(parameters);
			return parameters;
		}
		return null;
	}
}
