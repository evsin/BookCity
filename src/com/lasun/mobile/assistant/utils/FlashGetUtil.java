package com.lasun.mobile.assistant.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class FlashGetUtil {
	public String downloadTXT(String address) throws IOException {
		StringBuffer sb = new StringBuffer();
		String txtline = null;
		BufferedReader buffer = null;
		buffer = new BufferedReader(new InputStreamReader(getInputFromURL(address)));
		while ((txtline = buffer.readLine()) != null) {
			sb.append(txtline);
		}
		buffer.close();
		return sb.toString();
	}
	public String downFile(String address, String fileName) {
		return this.downFile(address, "MA/", fileName);
	}
	public String downFile(String address, String path, String fileName) {
		InputStream in = null;
		File rsfile = null;
		try {
			FileUtil mFileUtil = new FileUtil();
			if (mFileUtil.isFileExist(path, fileName))
				mFileUtil.deleteFile(path, fileName);
			in = getInputFromURL(address);
			if (in != null)
				rsfile = mFileUtil.write2SDFromInput(path, fileName, in);
			if (rsfile == null)
				return null;
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			try {
				if (in != null)
					in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return path + fileName;
	}
	public String downFile(byte[] buffer, String path, String fileName) {
		InputStream in = null;
		File rsfile = null;
		try {
			FileUtil mFileUtil = new FileUtil();
			if (mFileUtil.isFileExist(path, fileName))
				mFileUtil.deleteFile(path, fileName);
			rsfile = mFileUtil.write2SDFromInput(path, fileName, buffer);
			if (rsfile == null)
				return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			try {
				if (in != null)
					in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return path + fileName;
	}
	public InputStream getInputFromURL(String address) {
		URL url = null;
		InputStream in = null;
		try {
			url = new URL(address);
			HttpURLConnection urlconn = (HttpURLConnection) url.openConnection();
			in = urlconn.getInputStream();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return in;
	}
}
