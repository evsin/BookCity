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
import com.lasun.mobile.assistant.domain.SerachGoods;
import com.lasun.mobile.assistant.http.HttpRequester;
import com.lasun.mobile.assistant.utils.APIUtils;
import com.lasun.mobile.assistant.utils.JsonToBeanUtils;
import com.lasun.mobile.assistant.utils.NetExceptionUtils;
import com.lasun.mobile.assistant.utils.NetUtil;
import com.lasun.moblile.assistant.core.Parameter;

public class SerachGoodsService implements APIService {
	public List<SerachGoods> serachGoods(String keyword, String startIndex, String maxNum, int sortId, String shopWithinBrandId, String priceInterval, List<Parameter> parameters, Context mContext, NetExceptionCallBack mCallBack) {
		if (!NetUtil.checkNet(mContext)) {
			whenNetUnuse(mContext, mCallBack);
			return null;
		}
		try {
			HttpRequester httpRequester = new HttpRequester();
			String url = APIUtils.getInstance().getValueByKey("hiCDMACommonAPI");
			parameters.add(new Parameter("keyword", URLEncoder.encode(keyword, "utf-8")));
			parameters.add(new Parameter("startIndex", startIndex));
			parameters.add(new Parameter("sortId", Integer.toString(sortId)));
			parameters.add(new Parameter("maxNum", maxNum));
			parameters.add(new Parameter("method", GOODS_SERACHGOODS));
			if (shopWithinBrandId != null && !"".equals(shopWithinBrandId)) {
				parameters.add(new Parameter("shopWithinBrandId", shopWithinBrandId));
			} else {
				shopWithinBrandId = null;
			}
			if (priceInterval != null && !"".equals(priceInterval)) {
				parameters.add(new Parameter("priceInterval", priceInterval));
			} else {
				priceInterval = null;
			}
			String json = httpRequester.syncRequest(url, HttpRequester.POST, parameters, false);
			List<SerachGoods> goodsSerachGoods = JsonToBeanUtils.processJsonToList(json, SerachGoods.class, mContext);
			return goodsSerachGoods;
		} catch (Exception e) {
			e.printStackTrace();
			whenNetTimeout(mContext, mCallBack);
		}
		return null;
	}
	public List<SerachGoods> serachGoods(String keyword, List<Parameter> parameters, Context mContext, NetExceptionCallBack mCallBack) {
		if (!NetUtil.checkNet(mContext)) {
			whenNetUnuse(mContext, mCallBack);
			return null;
		}
		try {
			HttpRequester httpRequester = new HttpRequester();
			String url = APIUtils.getInstance().getValueByKey("hiCDMACommonAPI");
			parameters.add(new Parameter("keyword", URLEncoder.encode(keyword, "utf-8")));
			parameters.add(new Parameter("startIndex", "0"));
			parameters.add(new Parameter("maxNum", "999"));
			parameters.add(new Parameter("method", GOODS_SERACHGOODS));
			String json = httpRequester.syncRequest(url, HttpRequester.POST, parameters, false);
			List<SerachGoods> goodsSerachGoods = JsonToBeanUtils.processJsonToList(json, SerachGoods.class, mContext);
			return goodsSerachGoods;
		} catch (Exception e) {
			e.printStackTrace();
			whenNetTimeout(mContext, mCallBack);
		}
		return null;
	}
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
