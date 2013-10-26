package com.lasun.moblile.assistant.core;

import android.content.SharedPreferences;

public abstract class ShareCache {
	protected static String TEMPDATE = "10001";
	protected static String HIBERNATION = "10002";
	protected static String DISK = "10003";
	protected SharedPreferences tempdatedb;
	protected SharedPreferences hibernation;
	protected SharedPreferences disk;
	public abstract String getDatefromTemp(String key);
	public abstract void putDatetoTemp(String key, String value);
	public abstract String getDatefromStore(String key);
	public abstract void putDatetoStore(String key, String value);
	public abstract String getDatefromDisk(String key);
	public abstract void putDatetoDisk(String key, String value);
	public abstract void clearDatetoTemp();
	public abstract void clearDatetoStore();
	public abstract void clearStoreandSaveParticular(String[] keys);
}
