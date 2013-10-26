package com.lasun.mobile.assistant.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.google.gson.Gson;
import com.lasun.mobile.assistant.domain.GoodsUrlInfo;
import android.content.Context;
import android.os.Looper;
import android.util.Log;

public class JsonToBeanUtils {
	public static <T> List<T> processJsonToList(String jsonStr, Class clazz, final Context mContext) {
		try {
			if (jsonStr != null && !"".equals(jsonStr) && !(jsonStr.contains("code:") && jsonStr.contains("msg:"))) {
				JSONObject json = new JSONObject(jsonStr).getJSONObject("results");
				if (json != null && !json.toString().equals("{}")) {
					JSONArray jsonArray = json.getJSONArray("item");
					List<T> list = new ArrayList<T>();
					for (int i = 0; i < jsonArray.length(); i++) {
						JSONObject jsonObject2 = (JSONObject) jsonArray.opt(i);
						T object = (T) clazz.newInstance();
						Field[] fields = clazz.getDeclaredFields();
						String value = "";
						for (Field field : fields) {
							String fieldName = field.getName();
							if ("serialVersionUID".equals(fieldName) || "imageData".equals(fieldName))
								continue;
							String methodName = "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
							if (methodName.equals("setGoodsInfo")) {
								continue;
							} else if (methodName.equals("setColorAndIds")) {
								continue;
							} else {
								Method method = clazz.getMethod(methodName, new Class[] { String.class });
								if (!jsonObject2.isNull(fieldName)) {
									value = jsonObject2.getString(fieldName);
								} else
									value = null;
								method.invoke(object, new String[] { value });
							}
						}
						list.add(object);
					}
					return list;
				} else {
					return null;
				}
			} else {
				JSONObject json = new JSONObject(StringUtil.clearBlank(jsonStr));
				if (json != null && !json.toString().equals("{}")) {
					List<T> list = new ArrayList<T>();
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
					list.add(object);
					return list;
				} else {
					return null;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public static <T> List<T> processJsonToList2(String jsonStr, Class clazz, final Context mContext) {
		try {
			if (jsonStr != null && !"".equals(jsonStr) && !(jsonStr.contains("code:") && jsonStr.contains("msg:"))) {
				JSONArray jsonArray = new JSONObject(jsonStr).getJSONArray("results");
				List<T> list = new ArrayList<T>();
				for (int i = 0; i < jsonArray.length(); i++) {
					JSONObject jsonObject2 = (JSONObject) jsonArray.opt(i);
					T object = (T) clazz.newInstance();
					Field[] fields = clazz.getDeclaredFields();
					String value = "";
					for (Field field : fields) {
						String fieldName = field.getName();
						if ("serialVersionUID".equals(fieldName) || "imageData".equals(fieldName))
							continue;
						String methodName = "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
						if (methodName.equals("setGoodsInfo")) {
							continue;
						} else {
							Method method = clazz.getMethod(methodName, new Class[] { String.class });
							if (!jsonObject2.isNull(fieldName)) {
								value = jsonObject2.getString(fieldName);
							} else
								value = null;
							method.invoke(object, new String[] { value });
						}
					}
					list.add(object);
				}
				return list;
			} else {
				JSONObject json = new JSONObject(StringUtil.clearBlank(jsonStr));
				if (json != null && !json.toString().equals("{}")) {
					List<T> list = new ArrayList<T>();
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
					list.add(object);
					return list;
				} else {
					return null;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public static <T> List<T> processJsonToList3(String jsonStr, Class clazz, final Context mContext) {
		try {
			if (jsonStr != null && !"".equals(jsonStr) && !(jsonStr.contains("code:") && jsonStr.contains("msg:"))) {
				JSONObject json = new JSONObject(jsonStr).getJSONObject("results");
				if (json != null && !json.toString().equals("{}")) {
					JSONArray jsonArray = json.getJSONArray("item_inner");
					List<T> list = new ArrayList<T>();
					for (int i = 0; i < jsonArray.length(); i++) {
						JSONObject jsonObject2 = (JSONObject) jsonArray.opt(i);
						T object = (T) clazz.newInstance();
						Field[] fields = clazz.getDeclaredFields();
						String value = "";
						for (Field field : fields) {
							String fieldName = field.getName();
							if ("serialVersionUID".equals(fieldName) || "imageData".equals(fieldName))
								continue;
							String methodName = "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
							if (methodName.equals("setGoodsInfo")) {
								continue;
							} else {
								Method method = clazz.getMethod(methodName, new Class[] { String.class });
								if (!jsonObject2.isNull(fieldName)) {
									value = jsonObject2.getString(fieldName);
								} else
									value = null;
								method.invoke(object, new String[] { value });
							}
						}
						list.add(object);
					}
					return list;
				} else {
					return null;
				}
			} else {
				JSONObject json = new JSONObject(StringUtil.clearBlank(jsonStr));
				if (json != null && !json.toString().equals("{}")) {
					List<T> list = new ArrayList<T>();
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
					list.add(object);
					return list;
				} else {
					return null;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public static <T> List<T> processJsonToList4(String jsonStr, Class clazz, final Context mContext) {
		try {
			if (jsonStr != null && !"".equals(jsonStr) && !(jsonStr.contains("code:") && jsonStr.contains("msg:"))) {
				JSONObject json = new JSONObject(jsonStr).getJSONObject("results");
				if (json != null && !json.toString().equals("{}")) {
					JSONArray jsonArray = json.getJSONArray("item");
					List<T> list = new ArrayList<T>();
					for (int i = 0; i < jsonArray.length(); i++) {
						JSONObject jsonObject2 = (JSONObject) jsonArray.opt(i);
						T object = (T) clazz.newInstance();
						Field[] fields = clazz.getDeclaredFields();
						String value = "";
						for (Field field : fields) {
							String fieldName = field.getName();
							if ("serialVersionUID".equals(fieldName) || "imageData".equals(fieldName))
								continue;
							String methodName = "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
							if (methodName.equals("setSources")) {
								if (!jsonObject2.isNull(fieldName)) {
									value = jsonObject2.getString(fieldName);
									if (value != null && !"".equals(value)) {
										value = "{\"results\":" + value + "}";
										Map<String, Object> map = JsonToBeanUtilsofArrays.getJsonMap(value);
										GoodsUrlInfo urls = new GoodsUrlInfo();
										if (map != null) {
											@SuppressWarnings("unchecked")
											Map<String, List<String>> results = (Map<String, List<String>>) map.get("results");
											urls.setPicUrl(results.get("picUrl"));
											urls.setVideoUrl(results.get("videoUrl"));
											urls.setDocUrl(results.get("docUrl"));
											Log.i("info", urls.toString());
											Method ttmethod = clazz.getMethod(methodName, GoodsUrlInfo.class);
											ttmethod.invoke(object, urls);
										}
									}
								}
								continue;
							}
							if (methodName.equals("setGoodsInfo")) {
								continue;
							} else {
								Method method = clazz.getMethod(methodName, new Class[] { String.class });
								if (!jsonObject2.isNull(fieldName)) {
									value = jsonObject2.getString(fieldName);
								} else
									value = null;
								method.invoke(object, new String[] { value });
							}
						}
						list.add(object);
					}
					return list;
				} else {
					return null;
				}
			} else {
				JSONObject json = new JSONObject(StringUtil.clearBlank(jsonStr));
				if (json != null && !json.toString().equals("{}")) {
					List<T> list = new ArrayList<T>();
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
					list.add(object);
					return list;
				} else {
					return null;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/***
	 * 
	 * 方法说明： 将json字符串序列化为 List集合
	 * 
	 * @param <T>
	 * 
	 * @param jsonStr
	 * @param clazz
	 * @return
	 * @return
	 */
	public static <T> T processJsonToBean(String jsonStr, Class clazz, final Context mContext) {
		try {
			if (jsonStr != null && !(jsonStr.contains("code:") && jsonStr.contains("msg:"))) {
				JSONObject json = new JSONObject(jsonStr).getJSONObject("results");
				if (json != null && !json.toString().equals("{}")) {
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
					return null;
				}
			} else {
				JSONObject json = new JSONObject(StringUtil.clearBlank(jsonStr));
				if (json != null && !json.toString().equals("{}")) {
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
				} else {
					return null;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
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
	public static String processJsonToValue(String jsonStr, String key, Context mContext) {
		if (clearUserObj(jsonStr, mContext)) {
			return null;
		}
		try {
			if (!(jsonStr.contains("code:") && jsonStr.contains("msg:"))) {
				JSONObject json = new JSONObject(jsonStr).getJSONObject("results");
				if (json != null && !json.toString().equals("{}")) {
					JSONArray jsonAry = json.getJSONArray("item");
					JSONObject jsonObj = (JSONObject) jsonAry.getJSONObject(0);
					String vaule = jsonObj.getString(key);
					return vaule;
				} else {
					return null;
				}
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public static String processJsonToValue(String jsonStr, String key) {
		try {
			if (!(jsonStr.contains("code:") && jsonStr.contains("msg:"))) {
				JSONObject json = new JSONObject(jsonStr).getJSONObject("results");
				if (json != null && !json.toString().equals("{}")) {
					JSONArray jsonAry = json.getJSONArray("item");
					JSONObject jsonObj = (JSONObject) jsonAry.getJSONObject(0);
					String vaule = jsonObj.getString(key);
					return vaule;
				} else {
					return null;
				}
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public static String processJsonToValue2(String jsonStr, String key) {
		try {
			if (!(jsonStr.contains("code:") && jsonStr.contains("msg:"))) {
				JSONObject json = new JSONObject(jsonStr).getJSONObject("results");
				if (json != null && !json.toString().equals("{}")) {
					String vaule = json.getString(key);
					return vaule;
				} else {
					return null;
				}
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
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
	public static String processYiOrderResponseToJson(Object object) {
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
				json.append("{order :[").append(jsonObject.toString()).append("]}");
				return json.toString();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	public static String processBeanToJsonForUserLog(Object object) {
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
				json.append("{\"reqParames\":[").append(jsonObject.toString()).append("]}");
				return json.toString();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	public static boolean clearUserObj(String json, final Context mContext) {
		if (json != null && (json.contains("code:") && json.contains("msg:"))) {
			JSONObject jsonObj;
			try {
				jsonObj = new JSONObject(StringUtil.clearBlank(json));
				if ("10000003".equals(jsonObj.getString("code"))) {
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
					return true;
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	public static String contentJsonToValue(String jsonStr, String key, Context mContext) {
		String vaule = null;
		if (clearUserObj(jsonStr, mContext)) {
			return null;
		}
		try {
			if (!(jsonStr.contains("code:") && jsonStr.contains("msg:"))) {
				JSONObject json = new JSONObject(jsonStr);
				if (json != null && !json.toString().equals("{}")) {
					JSONArray jsonAry = json.getJSONArray("item");
					JSONObject jsonObj = (JSONObject) jsonAry.getJSONObject(0);
					vaule = jsonObj.getString(key);
				} else {
					return null;
				}
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return vaule;
	}
}
