package com.lasun.mobile.assistant.utils;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetUtil {
	public static boolean checkNet(Context context) {
		try {
			ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
			if (connectivity != null) {
				NetworkInfo info = connectivity.getActiveNetworkInfo();
				if (info != null && info.isConnected()) {
					if (info.getState() == NetworkInfo.State.CONNECTED) {
						return true;
					}
				}
			}
		} catch (Exception e) {
		}
		return false;
	}
	public static BitmapDrawable getImageFromUrl(URL url) {
		BitmapDrawable icon = null;
		try {
			icon = new BitmapDrawable(getBitmapImageFromUrl(url));
		} catch (Exception e) {
		}
		return icon;
	}
	public static Bitmap getBitmapImageFromUrl(URL url) {
		Bitmap bitmap = null;
		HttpURLConnection hc = null;
		try {
			hc = (HttpURLConnection) url.openConnection();
			hc.getHeaderFields();
			int nHttpResultCode = hc.getResponseCode();
			if (nHttpResultCode < HttpURLConnection.HTTP_BAD_REQUEST) {
				InputStream input = (InputStream) hc.getInputStream();
				if (nHttpResultCode / 100 == 2) {
					bitmap = BitmapFactory.decodeStream(new FlushedInputStream(input));
				}
				input.close();
			} else {
				InputStream errStream = hc.getErrorStream();
				if (errStream != null)
					errStream.close();
			}
		} catch (Exception e) {
		} finally {
			hc.disconnect();
		}
		return bitmap;
	}
}
