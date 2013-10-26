/*******************************************************************************
 * Copyright (c) 2011 by lasun Corporation all right reserved.
 * 2011-8-20 
 * 
 *******************************************************************************/
package com.lasun.mobile.assistant.net.service;

import java.net.URLEncoder;
import java.util.List;
import android.content.Context;
import android.os.Looper;
import com.lasun.mobile.assistant.domain.Advertisement;
import com.lasun.mobile.assistant.domain.SerachGoods;
import com.lasun.mobile.assistant.http.HttpRequester;
import com.lasun.mobile.assistant.utils.APIUtils;
import com.lasun.mobile.assistant.utils.JsonToBeanUtils;
import com.lasun.mobile.assistant.utils.NetExceptionUtils;
import com.lasun.mobile.assistant.utils.NetUtil;
import com.lasun.moblile.assistant.core.Parameter;

public class AdvertisementService implements APIService {
	/**
	 * 
	 * 方法说明：5.11.1 搜索商品信息
	 * 
	 * @param keyword
	 * @param parameters
	 * @return List<SerachGoods>
	 */
	public List<Advertisement> getAdsByPosition(String positionId, List<Parameter> parameters, Context mContext, NetExceptionCallBack mCallBack) {
		if (!NetUtil.checkNet(mContext)) {
			whenNetUnuse(mContext, mCallBack);
			return null;
		}
		try {
			HttpRequester httpRequester = new HttpRequester();
			String url = APIUtils.getInstance().getValueByKey("hiCDMACommonAPI");
			parameters.add(new Parameter("positionId", positionId));
			parameters.add(new Parameter("method", AD_GETADSBYPOSITION));
			String json = httpRequester.syncRequest(url, HttpRequester.POST, parameters, false);
			List<Advertisement> advertisementarry = JsonToBeanUtils.processJsonToList2(json, Advertisement.class, mContext);
			return advertisementarry;
		} catch (Exception e) {
			e.printStackTrace();
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
