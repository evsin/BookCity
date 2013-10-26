/*******************************************************************************
 * Copyright (c) 2011 by lasun Corporation all right reserved.
 * 2011-8-23 
 * 
 *******************************************************************************/
package com.lasun.mobile.assistant.utils;

import java.security.MessageDigest;

public class MD5Utils {
	private static String bytes2hex(byte[] bArr) {
		String hs = "";
		String stmp = "";
		for (int i = 0; i < bArr.length; i++) {
			stmp = (Integer.toHexString(bArr[i] & 0XFF));
			if (stmp.length() == 1)
				hs = hs + "0" + stmp;
			else
				hs = hs + stmp;
		}
		return hs.toUpperCase();  
	}

	public static String md5Encode(String str) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			return bytes2hex(md.digest(str.getBytes("utf-8")));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

}
