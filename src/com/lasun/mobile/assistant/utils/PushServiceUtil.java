package com.lasun.mobile.assistant.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.lansun.mobile.assistant.constant.MyConstant;
import com.lasun.moblile.assistant.core.CenterController;
import com.lasun.moblile.assistant.core.ShareCache;
import com.lasun.moblile.assistant.push.PollingSpreadService;

public class PushServiceUtil {
	/**
	 * 方法说明：开启推送服务 需要加两个开关，一个是客户端开关，另一个是用户选择的开关
	 * 
	 * @param context
	 */
	public static void openPushServiceForStart(Context context, int executeMode) {
		if ("on".equals(MyConstant.PUSH_SWITCH) && judgeIfPush((Activity) context)) {
			Log.i("info", "准备开启服务");
			Intent in = new Intent(context, PollingSpreadService.class);
			in.putExtra("executeMode", executeMode);
			context.startService(in);
		} else {
			Log.i("info", "开启服务的开关未打开");
		}
	}
	/**
	 * 方法说明：
	 * 
	 * @param context
	 */
	public static void openPushService(Context context, int executeMode) {
		Log.i("info", "准备开启服务");
		Intent in = new Intent(context, PollingSpreadService.class);
		in.putExtra("executeMode", executeMode);
		context.startService(in);
	}
	/**
	 * 方法说明：关闭推送服务
	 * 
	 * @param context
	 */
	public static void closePushService(Context context) {
		Log.i("info", "ClientController-----closePushService");
		Intent in = new Intent(context, PollingSpreadService.class);
		context.stopService(in);
	}
	public static boolean judgeIfPush(Context context) {
		CenterController centerController = CenterController.getCenterController(context);
		ShareCache mShareCache = centerController.getShareCache();
		String pushFlag = mShareCache.getDatefromStore(MyConstant.PUSH_SWITCH_FLAG);
		Log.i("info", "openPushService---pref=" + pushFlag);
		if (pushFlag == null || "".equals(pushFlag)) {
			mShareCache.putDatetoStore(MyConstant.PUSH_SWITCH_FLAG, PollingSpreadService.SWITCH_ON);
		}
		pushFlag = mShareCache.getDatefromStore(MyConstant.PUSH_SWITCH_FLAG);
		if (PollingSpreadService.SWITCH_ON.equals(pushFlag)) {
			return true;
		} else {
			return false;
		}
	}
}
