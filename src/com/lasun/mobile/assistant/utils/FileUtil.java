package com.lasun.mobile.assistant.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import android.R.bool;
import android.app.Activity;
import android.content.Context;
import android.os.Environment;

public class FileUtil {
	private String SDPATH;
	private int FILESIZE = 4 * 1024;
	public String getSDPATH() {
		return SDPATH;
	}
	public FileUtil() {
		SDPATH = Environment.getExternalStorageDirectory() + "/";
	}
	public File createSDFile(String fileName) throws IOException {
		File file = new File(SDPATH + fileName);
		file.createNewFile();
		return file;
	}
	public Boolean deleteFile(String path, String fileName) {
		return this.deleteFile(SDPATH + path + fileName);
	}
	public Boolean deleteFile(String url) {
		File file = new File(url);
		return file.delete();
	}
	public File createSDDir(String dirName) {
		File dir = new File(SDPATH + dirName);
		dir.mkdir();
		return dir;
	}
	public boolean isFileExist(String path, String fileName) {
		return this.isFileExist(SDPATH + path + fileName);
	}
	public boolean isFileExist(String url) {
		File file = new File(url);
		return file.exists();
	}
	public File write2SDFromInput(String path, String fileName, InputStream input) {
		File file = null;
		OutputStream output = null;
		try {
			createSDDir(path);
			file = createSDFile(path + fileName);
			output = new FileOutputStream(file);
			byte[] buffer = new byte[FILESIZE];
			while ((input.read(buffer)) != -1) {
				output.write(buffer);
			}
			output.flush();
			output.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (output != null)
					output.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return file;
	}
	public File write2SDFromInput(String path, String fileName, byte[] buffer) {
		File file = null;
		OutputStream output = null;
		try {
			createSDDir(path);
			file = createSDFile(path + fileName);
			output = new FileOutputStream(file);
			output.write(buffer);
			output.flush();
			output.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (output != null)
					output.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return file;
	}
	public Boolean write2SDString(String string, String path, String filename) {
		FileWriter mFileWriter = null;
		File mFile = null;
		try {
			createSDDir(path);
			mFile = createSDFile(path + filename);
			mFileWriter = new FileWriter(mFile, true);
			mFileWriter.write(string);
			mFileWriter.write("\n");
			mFileWriter.flush();
			mFileWriter.close();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	public Boolean write2SDString(String string) {
		return this.write2SDString(string, "HICDMA/", "QLog.txt");
	}
	public StringBuffer readFileformSD(String url) {
		if (!isFileExist(url)) {
			return null;
		}
		File file = new File(url);
		BufferedReader reader = null;
		FileInputStream io = null;
		StringBuffer sb = new StringBuffer();
		try {
			io = new FileInputStream(file);
			reader = new BufferedReader(new InputStreamReader(io, "UTF-8"));
			String tempString = null;
			int line = 1;
			while ((tempString = reader.readLine()) != null) {
				sb.append(tempString);
				line++;
			}
			reader.close();
			io.close();
		} catch (IOException e) {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
				}
			}
			e.printStackTrace();
			return null;
		}
		return sb;
	}
	public StringBuffer readFileformRAW(int id, Activity activity) {
		BufferedReader reader = null;
		InputStream io = null;
		StringBuffer sb = new StringBuffer();
		try {
			io = activity.getResources().openRawResource(id);
			reader = new BufferedReader(new InputStreamReader(io, "UTF-8"));
			String tempString = null;
			int line = 1;
			while ((tempString = reader.readLine()) != null) {
				sb.append(tempString);
				line++;
			}
			reader.close();
			io.close();
		} catch (IOException e) {
			if (reader != null) {
				try {
					reader.close();
					if (io != null)
						io.close();
				} catch (IOException e1) {
				}
			}
			e.printStackTrace();
			return null;
		}
		return sb;
	}
	public StringBuffer readFileformSD(String path, String filename) {
		return this.readFileformSD(path + filename);
	}
	public StringBuffer readSearchIndexformSD(String fileName) {
		String url = SDPATH + "HICDMA/" + fileName;
		return this.readFileformSD(url);
	}
	public StringBuffer readSearchIndexformRAW(int id, Activity activity) {
		return this.readFileformRAW(id, activity);
	}
}
