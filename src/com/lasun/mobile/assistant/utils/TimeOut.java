/*******************************************************************************
 * Copyright (c) 2011 by lasun Corporation all right reserved.
 * 2011-8-26 
 * 
 *******************************************************************************/
package com.lasun.mobile.assistant.utils;

import java.util.Timer;
import java.util.TimerTask;

import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;

 
public class TimeOut {
	public static final int NO = 0;
	public static final int YES = 1;
	public static final int TRY = 2;
	private static boolean isConnection = false;

	private static final Handler handler = new Handler();
	
	private static Timer mTimer=new Timer();

	public static void startTimer(final Context context, final int timeout,
			final DialogCallback callBack) {
		new Thread(new Runnable() {

			@Override
			public void run() {
				isConnection = false;
				int spend = 0;
				while (!isConnection) {
					if (spend > 2) {
						handler.post(new Runnable() {
							public void run() {
								createDialog(context, callBack);
							}
						});
						isConnection = true;
						return;
					}
					try {
						Thread.sleep(1000);
						spend++;
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
	}

	public static void endTimer(final GetMethod get) {
		new Thread(new Runnable() {

			@Override
			public void run() {
				isConnection = true;
				get.abort();
			}
		}).start();
	}
	
	public static void stopTimer(final DialogCallback callBack){
		mTimer.schedule(new TimerTask() {
			@Override
			public void run() {
				if(isConnection){
					callBack.dialogCallBack(3);
					mTimer.cancel();
				}
			}
		}, 0, 1000);
	}
	
	public static void endTimer(final PostMethod post) {
		new Thread(new Runnable() {

			@Override
			public void run() {
				isConnection = true;
				post.abort();
			}
		}).start();
	}
	
	public static void endTimer() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				isConnection = true;
			}
		}).start();
	}

	public interface DialogCallback {
		public void dialogCallBack(int flag);
	}

	private static void createDialog(Context context,
			final DialogCallback callBack) {
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setTitle("提示");
		builder.setMessage("连接超时！");
		builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				callBack.dialogCallBack(YES);
				dialog.dismiss();
			}
		});
		builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				callBack.dialogCallBack(NO);
				dialog.dismiss();
			}
		});
		builder.setNeutralButton("重试", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				callBack.dialogCallBack(TRY);
				dialog.dismiss();
			}
		});

		builder.create().show();
	}
}
