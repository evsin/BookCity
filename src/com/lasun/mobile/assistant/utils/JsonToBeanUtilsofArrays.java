/*******************************************************************************
 * Copyright (c) 2011 by Hirisun Corporation all right reserved.
 * 2011-8-11 
 * 
 *******************************************************************************/
package com.lasun.mobile.assistant.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;
import android.content.Context;
import android.os.Looper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class JsonToBeanUtilsofArrays {
	public static <T> T processJsonToBean(String jsonStr, Class clazz, final Context mContext) {
		try {
			if (jsonStr != null && !(jsonStr.contains("code:") && jsonStr.contains("msg:"))) {
				JSONObject json = new JSONObject(jsonStr).getJSONObject("results");
				JSONArray jsonArray = json.getJSONArray("item");
				JSONObject jsonObject2 = (JSONObject) jsonArray.opt(0);
				T object = (T) clazz.newInstance();
				Field[] fields = clazz.getDeclaredFields();
				String value = "";
				for (Field field : fields) {
					String fieldName = field.getName();
					if ("serialVersionUID".equals(fieldName))
						continue;
					String methodName = "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
					Method method = clazz.getMethod(methodName, new Class[] { String.class });
					if (!jsonObject2.isNull(fieldName)) {
						value = jsonObject2.getString(fieldName);
					} else
						value = null;
					method.invoke(object, new String[] { value });
				}
				return object;
			} else {
				JSONObject json = new JSONObject(StringUtil.clearBlank(jsonStr));
				T object = (T) clazz.newInstance();
				Method method = clazz.getMethod("setCode", new Class[] { String.class });
				method.invoke(object, new String[] { json.getString("code") });
				if ("10000003".equals(json.getString("code")) && mContext != null) {
					Method method2 = clazz.getMethod("setMsg", new Class[] { String.class });
					method2.invoke(object, new String[] { "输入sessionKey字段错误" });
					new Thread(new Runnable() {
						public void run() {
							if (Looper.myLooper() == null) {
								Looper.prepare();
								NetExceptionUtils.whenSessionWrong(mContext);
								Looper.loop();
							} else {
								NetExceptionUtils.whenSessionWrong(mContext);
							}
						}
					}).start();
				} else {
					Method method2 = clazz.getMethod("setMsg", new Class[] { String.class });
					method2.invoke(object, new String[] { json.getString("msg") });
				}
				return object;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public static Map<String, Object> getJsonMap(String JsonString) {
		Map<String, Object> map = null;
		Gson gson = new Gson();
		Type type = new TypeToken<Map<String, Object>>() {
		}.getType();
		map = gson.fromJson(JsonString, type);
		return map;
	}
	/***
	 * 
	 * 方法说明： 将Bean对象序列化为 json字符串
	 * 
	 * @param object
	 * @return
	 */
	public static String processBeanToJson(Object object) {
		if (object != null) {
			try {
				StringBuffer json = new StringBuffer();
				Class<? extends Object> ownerClass = object.getClass();
				Field[] fields = ownerClass.getDeclaredFields();
				JSONObject jsonObject = new JSONObject();
				for (Field field : fields) {
					String fieldName = field.getName();
					if ("serialVersionUID".equals(fieldName))
						continue;
					Method method = ownerClass.getMethod("get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1));
					String value = (String) method.invoke(object, new Object[] {});
					jsonObject.put(fieldName, value);
				}
				json.append("{\"item\":[").append(jsonObject.toString()).append("]}");
				return json.toString();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	/**
	 * 
	 * 方法说明：
	 * 
	 * @param title 交易API保存定单专用
	 * @param object
	 * @return
	 */
	public static String processBeanToJson(String title, Object object) {
		if (object != null) {
			try {
				StringBuffer json = new StringBuffer();
				Class<? extends Object> ownerClass = object.getClass();
				Field[] fields = ownerClass.getDeclaredFields();
				JSONObject jsonObject = new JSONObject();
				for (Field field : fields) {
					String fieldName = field.getName();
					if ("serialVersionUID".equals(fieldName))
						continue;
					Method method = ownerClass.getMethod("get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1));
					String value = (String) method.invoke(object, new Object[] {});
					jsonObject.put(fieldName, value);
				}
				json.append("\"" + title + "\":[").append(jsonObject.toString()).append("]");
				return json.toString();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	/**
	 * 
	 * 方法说明：根据传入的JSON字符串获取特定的值（保存定单专用）
	 * 
	 * @param jsonStr
	 * @return
	 */
	public static String processJsonToValue(String jsonStr, String key) {
		try {
			JSONObject json = new JSONObject(jsonStr).getJSONObject("results");
			JSONArray jsonAry = json.getJSONArray("item");
			JSONObject jsonObj = (JSONObject) jsonAry.opt(0);
			return jsonObj.getString(key);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 
	 * 方法说明：将对象转换为 {" sourceType " : 0," sourceId " : 300865," sourceName " : "三星i909 黑色"," quantity " :1 }格式
	 * 
	 * @param object
	 * @return
	 */
	public static String processBeanToJsonUnit(Object object) {
		if (object != null) {
			try {
				Class<? extends Object> ownerClass = object.getClass();
				Field[] fields = ownerClass.getDeclaredFields();
				JSONObject jsonObject = new JSONObject();
				for (Field field : fields) {
					String fieldName = field.getName();
					if ("serialVersionUID".equals(fieldName) || "code".equals(fieldName) || "msg".equals(fieldName))
						continue;
					Method method = ownerClass.getMethod("get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1));
					String value = (String) method.invoke(object, new Object[] {});
					jsonObject.put(fieldName, value);
				}
				return jsonObject.toString();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}
}
