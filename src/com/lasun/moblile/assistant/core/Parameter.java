package com.lasun.moblile.assistant.core;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import com.lasun.mobile.assistant.utils.MD5Utils;

public class Parameter implements Serializable, Comparable<Parameter> {
	private static final long serialVersionUID = -2984681566296254227L;
	public String mName;
	public String mValue;
	public Parameter(String mName, String mValue) {
		this.mName = mName;
		this.mValue = (mValue != null ? mValue.trim() : mValue);
	}
	public boolean equals(Object arg0) {
		if (arg0 == null) {
			return false;
		}
		if (this == arg0) {
			return true;
		}
		if (arg0 instanceof Parameter) {
			Parameter param = (Parameter) arg0;
			return this.mName.equals(param.mName) && this.mValue.equals(param.mValue);
		}
		return false;
	}
	public int compareTo(Parameter parameter) {
		int compared;
		compared = mName.compareTo(parameter.mName);
		if (0 == compared) {
			compared = mValue.compareTo(parameter.mValue);
		}
		return compared;
	}
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
	public static String getSigFromParamters(List<Parameter> parameters) {
		if (parameters != null && parameters.size() > 0) {
			List<Parameter> params = new ArrayList<Parameter>();
			for (Parameter parameter : parameters) {
				if (!("requestVal".equals(parameter.mName) || "reqParames".equals(parameter.mName)))
					params.add(parameter);
			}
			Collections.sort(params);
			String sig = normalizeRequestParameters(params);
			try {
				sig = URLDecoder.decode(sig, "UTF-8");
			} catch (UnsupportedEncodingException e1) {
				e1.printStackTrace();
			}
			if (sig != null) {
				try {
					String urlEncodeSig = URLEncoder.encode(sig, "UTF-8");
					String md5Str = MD5Utils.md5Encode(urlEncodeSig);
					return md5Str;
				} catch (UnsupportedEncodingException e) {
				}
			}
		}
		return null;
	}
	public String getmName() {
		return mName;
	}
	public String getmValue() {
		return mValue;
	}
}
