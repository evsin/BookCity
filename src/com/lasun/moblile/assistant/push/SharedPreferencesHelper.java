package com.lasun.moblile.assistant.push;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesHelper {
	private SharedPreferences sharedPreferences;
	public SharedPreferencesHelper(String name, Context context) {
		sharedPreferences = context.getSharedPreferences(name, Context.MODE_PRIVATE);
	}
	/**
	 * 若SharedPreferences中的值为空，则认为为空，否则不为空
	 * 
	 * @return
	 */
	public boolean exists() {
		return sharedPreferences.getAll().size() > 0;
	}
	/**
	 * 验证是否包含指定的key值
	 * 
	 * @param key 要检查的key值
	 * @return 返回检查结果
	 */
	public boolean contains(String key) {
		return exists() && sharedPreferences.contains(key);
	}
	/** 根据key获取对应的value */
	public String getString(String key, String defValue) {
		return sharedPreferences.getString(key, defValue);
	}
	/** 根据key获取对应的value */
	public String getString(String key) {
		return getString(key, "");
	}
	/**
	 * 根据key获取对应的value
	 * 
	 * @param key
	 * @param defValue
	 * @return
	 */
	public int getInt(String key, int defValue) {
		return sharedPreferences.getInt(key, defValue);
	}
	/**
	 * Set a String value in the preferences editor, to be written back once commit or apply are called.
	 * 
	 * @param key The name of the preference to modify.
	 * @param value The new value for the preference.
	 * @return Returns true if the new values were successfully written to persistent storage.
	 */
	public boolean putString(String key, String value) {
		return sharedPreferences.edit().putString(key, value).commit();
	}
	/**
	 * Set an int value in the preferences editor, to be written back once commit or apply are called.
	 * 
	 * @param key The name of the preference to modify.
	 * @param value The new value for the preference.
	 * @return Returns true if the new values were successfully written to persistent storage.
	 */
	public boolean putInt(String key, int value) {
		return sharedPreferences.edit().putInt(key, value).commit();
	}
	/**
	 * Mark in the editor to remove all values from the preferences. Once commit is called, the only remaining preferences will be any that you have
	 * defined in this editor. Note that when committing back to the preferences, the clear is done first, regardless of whether you called clear
	 * before or after put methods on this editor.
	 * 
	 * @return
	 */
	public boolean clear() {
		return sharedPreferences.edit().clear().commit();
	}
}
