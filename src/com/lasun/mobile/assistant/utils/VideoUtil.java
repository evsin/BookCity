package com.lasun.mobile.assistant.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.webkit.MimeTypeMap;

public class VideoUtil {

	/**
	 * ≤•∑≈Õ¯¬Á√ΩÃÂ
	 * 
	 * @param url
	 */
	public void playNetVideo(final String url, final Context mContext) {
		Runnable run = new Runnable() {

			@Override
			public void run() {
				String extension = MimeTypeMap.getFileExtensionFromUrl(url);
				String mimeType = MimeTypeMap.getSingleton()
						.getMimeTypeFromExtension(extension);
				Intent mediaIntent = new Intent(Intent.ACTION_VIEW);
				mediaIntent.setDataAndType(Uri.parse(url), mimeType);
				mContext.startActivity(mediaIntent);

			}
		};
		Thread t = new Thread(run);
		t.start();
	}
}
