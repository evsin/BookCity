/*******************************************************************************
 * Copyright (c) 2011 by lasun Corporation all right reserved.
 * 2011-8-23 
 * 
 *******************************************************************************/
package com.lasun.mobile.assistant.net.service;

import java.net.URLEncoder;
import java.util.List;

import android.content.Context;
import android.os.Looper;

import com.lansun.mobile.assistant.constant.MyConstant;
import com.lasun.mobile.assistant.domain.ShopBrands;
import com.lasun.mobile.assistant.domain.ShopGoodsGroup;
import com.lasun.mobile.assistant.http.HttpRequester;
import com.lasun.mobile.assistant.utils.APIUtils;
import com.lasun.mobile.assistant.utils.JsonToBeanUtils;
import com.lasun.mobile.assistant.utils.LoginHelper;
import com.lasun.mobile.assistant.utils.NetExceptionUtils;
import com.lasun.mobile.assistant.utils.NetUtil;
import com.lasun.moblile.assistant.core.Parameter;
import com.lasun.moblile.assistant.core.ShareCache;


public class ShopService implements APIService {
	

	public List<ShopBrands> getBrands(String brandGroupId,
			boolean needBrandGroupId, List<Parameter> parameters,
			Context mContext, NetExceptionCallBack mCallBack) {
		if (!NetUtil.checkNet(mContext)) {
			whenNetUnuse(mContext, mCallBack);
			return null;
		}
		try {
			HttpRequester httpRequester = new HttpRequester();

			String url = APIUtils.getInstance()
					.getValueByKey("hiCDMACommonAPI");

			parameters.add(new Parameter("method", SHOP_BRANDGROUPS));
			if (needBrandGroupId) {
				parameters.add(new Parameter("brandGroupId", brandGroupId));
			}
			String json = httpRequester.syncRequest(url, HttpRequester.POST,
					parameters, false);
			List<ShopBrands> shopBrands = JsonToBeanUtils.processJsonToList(
					json, ShopBrands.class, mContext);
			return shopBrands;
		} catch (Exception e) {
			e.printStackTrace();
			whenNetTimeout(mContext, mCallBack);
		}
		return null;
	}


	public List<ShopGoodsGroup> getGoodsList(String group, String smart,
			String netWork, String brand, List<Parameter> parameters,
			Context mContext, NetExceptionCallBack mCallBack,
			ShareCache sharecache) {
		if (!NetUtil.checkNet(mContext)) {
			whenNetUnuse(mContext, mCallBack);
			return null;
		}
		try {
			HttpRequester httpRequester = new HttpRequester();

			String url = APIUtils.getInstance()
					.getValueByKey("hiCDMACommonAPI");

			parameters.add(new Parameter("method", GOODS_FILTERGOODS));
			parameters.add(new Parameter("group", group));
			if (LoginHelper.checkLogin(mContext))
				parameters.add(new Parameter("local", sharecache
						.getDatefromStore(LoginHelper.CITYCODE)));
			else
				parameters.add(new Parameter("local", ""));
			parameters.add(new Parameter("smart", smart));
			parameters.add(new Parameter("netWork", netWork));
			parameters.add(new Parameter("brand", (brand == null) ? ("")
					: (URLEncoder.encode(brand, "utf-8"))));
			String json = httpRequester.syncRequest(url, HttpRequester.POST,
					parameters, false);
			List<ShopGoodsGroup> shopGroups = JsonToBeanUtils
					.processJsonToList4(json, ShopGoodsGroup.class, mContext);
			return shopGroups;
		} catch (Exception e) {
			e.printStackTrace();
			whenNetTimeout(mContext, mCallBack);
		}
		return null;
	}

	
	private void whenNetUnuse(final Context mContext,
			final NetExceptionCallBack mCallBack) {
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

	
	private void whenNetTimeout(final Context mContext,
			final NetExceptionCallBack mCallBack) {
		new Thread(new Runnable() {
			public void run() {
				if (Looper.myLooper() == null) {
					Looper.prepare();
					NetExceptionUtils.createNetOuttimeDialog(mContext,
							mCallBack);
					Looper.loop();
				} else {
					NetExceptionUtils.createNetOuttimeDialog(mContext,
							mCallBack);
				}
			}
		}).start();

	}
}
