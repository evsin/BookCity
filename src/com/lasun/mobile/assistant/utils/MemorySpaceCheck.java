/*******************************************************************************
 * Copyright (c) 2011 by lasun Corporation all right reserved.
 * 2011-11-23 
 * 
 *******************************************************************************/
package com.lasun.mobile.assistant.utils;

import java.io.File;
import android.os.Environment;
import android.os.StatFs;

public class MemorySpaceCheck {
	private static long getAvailableSize(String path) {
		StatFs fileStats = new StatFs(path);
		fileStats.restat(path);
		return (long) fileStats.getAvailableBlocks() * fileStats.getBlockSize();
	}
	private static long getTotalSize(String path) {
		StatFs fileStats = new StatFs(path);
		fileStats.restat(path);
		return (long) fileStats.getBlockCount() * fileStats.getBlockSize();
	}
	public static long getSDAvailableSize() {
		if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
			return getAvailableSize(Environment.getExternalStorageDirectory().toString());
		}
		return 0;
	}
	public static long getSystemAvailableSize() {
		File root = Environment.getDataDirectory();
		return getAvailableSize(root.getPath());
	}
	public static boolean hasEnoughMemory(String filePath) {
		File file = new File(filePath);
		long length = file.length();
		if (filePath.startsWith("/sdcard") || filePath.startsWith("/mnt/sdcard")) {
			return getSDAvailableSize() > length;
		} else {
			return getSystemAvailableSize() > length;
		}
	}
	public static long getSDTotalSize() {
		if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
			return getTotalSize(Environment.getExternalStorageDirectory().toString());
		}
		return 0;
	}
	public static long getSysTotalSize() {
		return getTotalSize("/data");
	}
}
