package com.lasun.moblile.assistant.impl;

import java.util.ArrayList;
import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;
import com.lasun.mobile.assistant.domain.AppInfo;
import com.lasun.mobile.assistant.domain.User;
import com.lasun.mobile.assistant.net.service.EnvironmentService;
import com.lasun.mobile.assistant.net.service.APIService.NetExceptionCallBack;
import com.lasun.moblile.assistant.core.ContextService;
import com.lasun.moblile.assistant.core.Parameter;

public class ContextServiceImpl extends ContextService {
	private Context mContext;
	public ContextServiceImpl(Context context) {
		mContext = context;
	}
	@Override
	public boolean isNetworkAvailable() {
		ConnectivityManager connectivity = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (connectivity == null)
			return false;
		else {
			NetworkInfo[] info = connectivity.getAllNetworkInfo();
			if (info != null) {
				for (int i = 0; i < info.length; i++) {
					if (info[i].getState() == NetworkInfo.State.CONNECTED)
						return true;
				}
			}
		}
		return false;
	}
	@Override
	public boolean getCurrentUser() {
		return false;
	}
	@Override
	public boolean putCurrentUser(User user) {
		return false;
	}
	@Override
	public AppInfo checkSoftVersion() {
		EnvironmentService es = new EnvironmentService();
		AppInfo appinfo = es.checkSoftVersion(new ArrayList<Parameter>(), mContext, new NetExceptionCallBack() {
			@Override
			public void netExceptionCallback(int flag) {
			}
		});
		return appinfo;
	}
}
