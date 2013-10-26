package com.lasun.moblile.assistant.push;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class GsonUtile {
	public static List<Message> parsseJsonString(String json) {
		List<Message> list = null;
		Gson gson = new Gson();
		Type type = new TypeToken<List<Message>>() {}.getType();
		list = gson.fromJson(json, type);
		return list;
	}
	public static List<Map<String, Object>> getMap(String jsonString) {
		List<Map<String, Object>> map = null;
		Gson gson = new Gson();
		Type type = new TypeToken<List<Map<String, Object>>>() {
		}.getType();
		map = gson.fromJson(jsonString, type);
		return map;
	}
	public static boolean checkNetWorkState(Context context) {
		ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (manager == null) {
			return false;
		}
		NetworkInfo networkinfo = manager.getActiveNetworkInfo();
		if (networkinfo == null || !networkinfo.isAvailable()) {
			return false;
		}
		return true;
	}
}