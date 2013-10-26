package com.lasun.moblile.assistant.push;

import com.lasun.mobile.assistant.utils.PushServiceUtil;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * 手机开机监听器 在手机开机后，检查SharedPreferences的值，在获取手机号的情况下将轮询服务开启
 */
public class StartupListener extends BroadcastReceiver {
	@Override
	public void onReceive(Context context, Intent intent) {
		Log.i("info", "我开机啦啦啦啦啦啦拉拉爱啦啦啦啦");
		if (PushServiceUtil.judgeIfPush((Activity) context)) {
			Log.i("info", "我开机啦啦啦啦啦啦拉拉爱啦啦啦啦");
			PushServiceUtil.openPushService(context, PollingSpreadService.EXECUTE_MODE_BOOT);
		}
	}
}
