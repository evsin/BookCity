package com.lasun.mobile.assistant.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class APIUtils {
	Properties properties = null;
	private static APIUtils instance = null;
	static {
		instance = new APIUtils();
	}
	private APIUtils() {
		properties = new Properties();
		InputStream ins = this.getClass().getClassLoader().getResourceAsStream("API.properties");
		try {
			properties.load(ins);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (ins != null) {
				try {
					ins.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	public static APIUtils getInstance() {
		return instance;
	}
	public String getValueByKey(String key) {
		return properties.getProperty(key);
	}
}