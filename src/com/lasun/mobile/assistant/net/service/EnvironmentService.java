package com.lasun.mobile.assistant.net.service;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import android.content.Context;
import android.os.Looper;
import android.util.Log;
import com.lasun.mobile.assistant.domain.AgencyBrand;
import com.lasun.mobile.assistant.domain.AgencyGoods;
import com.lasun.mobile.assistant.domain.AppInfo;
import com.lasun.mobile.assistant.domain.UserLoginRequestBody;
import com.lasun.mobile.assistant.domain.UserLoginResponseBody;
import com.lasun.mobile.assistant.http.HttpRequester;
import com.lasun.mobile.assistant.utils.APIUtils;
import com.lasun.mobile.assistant.utils.JsonToBeanUtils;
import com.lasun.mobile.assistant.utils.NetExceptionUtils;
import com.lasun.mobile.assistant.utils.NetUtil;
import com.lasun.moblile.assistant.core.Parameter;

public class EnvironmentService implements APIService {
	public AppInfo checkSoftVersion(List<Parameter> parameters, Context mContext, NetExceptionCallBack mCallBack) {
		if (!NetUtil.checkNet(mContext)) {
			whenNetUnuse(mContext, mCallBack);
			return null;
		}
		try {
			HttpRequester httpRequester = new HttpRequester();
			String url = APIUtils.getInstance().getValueByKey("hiCDMACommonAPI");
			parameters.add(new Parameter("method", MOBILEASSIST_GETLASTESTVERSIONINFO));
			String json = httpRequester.syncRequest(url, HttpRequester.POST, parameters, false);
			AppInfo appinfo = JsonToBeanUtils.processJsonToBean(json, AppInfo.class, mContext);
			return appinfo;
		} catch (Exception e) {
			whenNetTimeout(mContext, mCallBack);
		}
		return null;
	}
	/**
	 * 
	 * 方法说明：网络不可用时网络异常
	 * 
	 * @param mContext
	 * @param mCallBack 回调接口
	 */
	private void whenNetUnuse(final Context mContext, final NetExceptionCallBack mCallBack) {
		new Thread(new Runnable() {
			public void run() {
				if (Looper.myLooper() == null) {
					Looper.prepare();
					NetExceptionUtils.createNetUnused(mContext, mCallBack);
					Looper.loop();
				} else {
					NetExceptionUtils.createNetUnused(mContext, mCallBack);
				}
			}
		}).start();
	}
	/**
	 * 
	 * 方法说明：网络延时时网络异常
	 * 
	 * @param mContext
	 * @param mCallBack 回调接口
	 */
	private void whenNetTimeout(final Context mContext, final NetExceptionCallBack mCallBack) {
		new Thread(new Runnable() {
			public void run() {
				if (Looper.myLooper() == null) {
					Looper.prepare();
					NetExceptionUtils.createNetOuttimeDialog(mContext, mCallBack);
					Looper.loop();
				} else {
					NetExceptionUtils.createNetOuttimeDialog(mContext, mCallBack);
				}
			}
		}).start();
	}
}
