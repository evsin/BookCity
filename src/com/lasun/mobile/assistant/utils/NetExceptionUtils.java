package com.lasun.mobile.assistant.utils;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import com.lasun.mobile.assistant.net.service.APIService;
import com.lasun.mobile.assistant.net.service.APIService.NetExceptionCallBack;
import com.lasun.mobile.client.assistant.activity.R;

public class NetExceptionUtils {
	public static synchronized void createNetOuttimeView(final Context context, final APIService.NetExceptionCallBack mCallBack) {
		((Activity) context).runOnUiThread(new Runnable() {
			@Override
			public void run() {
				RelativeLayout netView = (RelativeLayout) LayoutInflater.from(context).inflate(R.layout.progress, null);
				LayoutParams params = new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);
				((Activity) context).addContentView(netView, params);
			}
		});
		mCallBack.netExceptionCallback(APIService.RETRY);
	}
	private static boolean isAllow = true;
	public static synchronized void createNetOuttimeDialog(final Context context, final APIService.NetExceptionCallBack mCallBack) {
		if (!isAllow || !canShowDialog(context)) {
			return;
		}
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setTitle("提示");
		builder.setMessage("网络不太给力哦，请重试");
		builder.setPositiveButton("重试", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				isAllow = true;
				dialog.dismiss();
				mCallBack.netExceptionCallback(APIService.RETRY);
			}
		});
		builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				isAllow = true;
				dialog.dismiss();
				mCallBack.netExceptionCallback(APIService.CANCEL);
			}
		});
		builder.setCancelable(false);
		builder.create().show();
		isAllow = false;
	}
	public static synchronized void createNetUnused(final Context context, final APIService.NetExceptionCallBack mCallBack) {
		if (!isAllow || !canShowDialog(context)) {
			return;
		}
		createDialog(context, mCallBack);
	}
	private static void createDialog(Context context, final NetExceptionCallBack callBack) {
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setTitle("提示");
		builder.setMessage("网络貌似出问题了，请您检查网络");
		builder.setCancelable(false);
		builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				isAllow = true;
				dialog.dismiss();
				if (callBack != null)
					callBack.netExceptionCallback(APIService.UNUSE);
			}
		});
		builder.setCancelable(false);
		builder.create().show();
		isAllow = false;
	}
	public static synchronized void whenSessionWrong(final Context context) {
		if (!isAllow || !canShowDialog(context)) {
			return;
		}
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setTitle("提示");
		builder.setMessage("用户已经退出或在其它地方登录");
		builder.setPositiveButton("重新登录", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				isAllow = true;
				dialog.dismiss();
				Intent it1 = new Intent("lasun.login");
				it1.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
				context.startActivity(it1);
			}
		});
		builder.setNegativeButton("取消", new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				isAllow = true;
				dialog.dismiss();
			}
		});
		builder.setCancelable(false);
		builder.create().show();
		isAllow = false;
	}
	public static boolean canShowDialog(Context context) {
		try {
			ActivityManager activityManager = (ActivityManager) context.getApplicationContext().getSystemService(Context.ACTIVITY_SERVICE);
			RunningTaskInfo taskInfo = activityManager.getRunningTasks(1).get(0);
			String formSystem = taskInfo.topActivity.getClassName();
			Activity requestActivity = (Activity) context;
			String formContext = requestActivity.getComponentName().getClassName();
			if (formSystem.equals(formContext)) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
