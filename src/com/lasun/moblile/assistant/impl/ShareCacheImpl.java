package com.lasun.moblile.assistant.impl;

import android.content.Context;
import com.lasun.moblile.assistant.core.ShareCache;

public class ShareCacheImpl extends ShareCache {
	public ShareCacheImpl(Context context) {
		tempdatedb = context.getSharedPreferences(TEMPDATE, 0);
		hibernation = context.getSharedPreferences(HIBERNATION, 0);
		disk = context.getSharedPreferences(DISK, 0);
	}
	@Override
	public String getDatefromTemp(String key) {
		return tempdatedb.getString(key, null);
	}
	@Override
	public void putDatetoTemp(String key, String value) {
		tempdatedb.edit().putString(key, value).commit();
	}
	@Override
	public void clearDatetoTemp() {
		tempdatedb.edit().clear().commit();
	}
	@Override
	public void clearDatetoStore() {
		hibernation.edit().clear().commit();
	}
	@Override
	public String getDatefromStore(String key) {
		return hibernation.getString(key, null);
	}
	@Override
	public void putDatetoStore(String key, String value) {
		hibernation.edit().putString(key, value).commit();
	}
	@Override
	public String getDatefromDisk(String key) {
		return disk.getString(key, null);
	}
	@Override
	public void putDatetoDisk(String key, String value) {
		disk.edit().putString(key, value).commit();
	}
	@Override
	public void clearStoreandSaveParticular(String[] keys) {
		for (int i = 0; i < keys.length; i++)
			this.putDatetoDisk(keys[i], this.getDatefromStore(keys[i]));
		hibernation.edit().clear().commit();
		for (int i = 0; i < keys.length; i++)
			this.putDatetoStore(keys[i], this.getDatefromDisk(keys[i]));
	}
}
