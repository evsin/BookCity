package com.lasun.moblile.assistant.push;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.NetworkInfo;
import android.util.Log;
import com.lasun.mobile.assistant.utils.PushServiceUtil;

/**
 * 网络连接成功监听器，在网络连接成功后首先检查服务是否已经开启， 若没有开启，则开启，若已经开启，根据SharedPreferences值判断是否立刻请求
 */
public class NetworkConnectListener extends BroadcastReceiver {
	@Override
	public void onReceive(Context context, Intent intent) {
		NetworkInfo networkInfo = (NetworkInfo) intent.getParcelableExtra(android.net.ConnectivityManager.EXTRA_NETWORK_INFO);
		if (networkInfo.getState() == NetworkInfo.State.CONNECTED) {
			Log.i("info", "我有网啦啦啦啦啦啦拉拉爱啦啦啦啦");
			if (PushServiceUtil.judgeIfPush(context)) {
				PushServiceUtil.openPushService(context, PollingSpreadService.EXECUTE_MODE_NET);
			}
		}
	}
}
