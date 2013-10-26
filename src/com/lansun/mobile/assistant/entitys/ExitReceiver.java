package com.lansun.mobile.assistant.entitys;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * 自定义退出程序广播接收器,动态注册
 * 
 */
public class ExitReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		Log.i("info", intent.getAction());
		Log.i("info", "接收到退出广播信息");
		((Activity) context).finish();
	}
}