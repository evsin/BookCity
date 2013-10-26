package com.lansun.mobile.assistant.notifydownload;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import android.os.Handler;
import android.os.Message;

public class DownloadUtil {
	public static void save(InputStream in, File file, Handler handler) {
		if (in != null && file != null) {
			if (!file.getParentFile().exists()) {
				file.getParentFile().mkdirs();
			}
		}
		try {
			FileOutputStream fos = new FileOutputStream(file);
			BufferedInputStream is = new BufferedInputStream(in);
			BufferedOutputStream out = new BufferedOutputStream(fos);
			int len = -1;
			byte[] bytes = new byte[1024];
			int progress = 0;
			while ((len = is.read(bytes)) != -1) {
				out.write(bytes, 0, len);
				if (++progress % 400 == 0 && handler != null) {
					Message msg = Message.obtain();
					msg.what = GlobalUtils.MSG_WHAT_PROGRESS;
					msg.arg1 = progress;
					handler.sendMessage(msg);
				}
			}
			out.close();
			fos.close();
			is.close();
			in.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
