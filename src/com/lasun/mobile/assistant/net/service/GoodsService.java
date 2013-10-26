package com.lasun.mobile.assistant.net.service;

import java.util.List;
import java.util.Map;
import android.content.Context;
import android.os.Looper;
import android.util.Log;
import com.lasun.mobile.assistant.domain.Goods;
import com.lasun.mobile.assistant.domain.GoodsBaseColor;
import com.lasun.mobile.assistant.domain.GoodsColors;
import com.lasun.mobile.assistant.domain.GoodsDealer;
import com.lasun.mobile.assistant.domain.GoodsFilterResponseBody;
import com.lasun.mobile.assistant.domain.GoodsGroupResponseBody;
import com.lasun.mobile.assistant.domain.GoodsIntroduction;
import com.lasun.mobile.assistant.domain.GoodsPackaging;
import com.lasun.mobile.assistant.domain.GoodsPictures;
import com.lasun.mobile.assistant.domain.GoodsSaleService;
import com.lasun.mobile.assistant.domain.GoodsSpecifications;
import com.lasun.mobile.assistant.domain.GoodsUrlInfo;
import com.lasun.mobile.assistant.http.HttpRequester;
import com.lasun.mobile.assistant.utils.APIUtils;
import com.lasun.mobile.assistant.utils.JsonToBeanUtils;
import com.lasun.mobile.assistant.utils.JsonToBeanUtilsofArrays;
import com.lasun.mobile.assistant.utils.NetExceptionUtils;
import com.lasun.mobile.assistant.utils.NetUtil;
import com.lasun.moblile.assistant.core.Parameter;

public class GoodsService implements APIService {
	public List<Goods> getGoodsInfoByIds(String[] goodsIds, String areaCode, List<Parameter> parameters, Context mContext, NetExceptionCallBack mCallBack) {
		if (!NetUtil.checkNet(mContext)) {
			whenNetUnuse(mContext, mCallBack);
			return null;
		}
		try {
			StringBuffer ids = new StringBuffer();
			HttpRequester httpRequester = new HttpRequester();
			String url = APIUtils.getInstance().getValueByKey("hiCDMACommonAPI");
			for (String goodsid : goodsIds) {
				ids.append(goodsid).append(",");
			}
			ids.deleteCharAt(ids.lastIndexOf(","));
			parameters.add(new Parameter("method", GOODS_GETNEWINFO));
			parameters.add(new Parameter("goodsId", ids.toString()));
			parameters.add(new Parameter("areaCode", areaCode));
			String json = httpRequester.syncRequest(url, HttpRequester.POST, parameters, false);
			List<Goods> goods = JsonToBeanUtils.processJsonToList(json, Goods.class, mContext);
			return goods;
		} catch (Exception e) {
			e.printStackTrace();
			whenNetTimeout(mContext, mCallBack);
		}
		return null;
	}
	public GoodsUrlInfo getGoodsSource(String goodsId, List<Parameter> parameters, Context mContext, NetExceptionCallBack mCallBack) {
		if (!NetUtil.checkNet(mContext)) {
			whenNetUnuse(mContext, mCallBack);
			return null;
		}
		try {
			HttpRequester httpRequester = new HttpRequester();
			String url = APIUtils.getInstance().getValueByKey("hiCDMACommonAPI");
			parameters.add(new Parameter("method", GOODS_SOURCE));
			parameters.add(new Parameter("goodsId", goodsId));
			String json = httpRequester.syncRequest(url, HttpRequester.POST, parameters, false);
			Map<String, Object> map = JsonToBeanUtilsofArrays.getJsonMap(json);
			GoodsUrlInfo urls = new GoodsUrlInfo();
			@SuppressWarnings("unchecked")
			Map<String, List<String>> results = (Map<String, List<String>>) map.get("results");
			urls.setPicUrl(results.get("picUrl"));
			urls.setVideoUrl(results.get("videoUrl"));
			urls.setDocUrl(results.get("docUrl"));
			Log.i("info", urls.toString());
			return urls;
		} catch (Exception e) {
			e.printStackTrace();
			whenNetTimeout(mContext, mCallBack);
		}
		return null;
	}
	public List<GoodsBaseColor> getColorAndIds(String goodsName, List<Parameter> parameters, Context mContext, NetExceptionCallBack mCallBack) {
		if (!NetUtil.checkNet(mContext)) {
			whenNetUnuse(mContext, mCallBack);
			return null;
		}
		try {
			HttpRequester httpRequester = new HttpRequester();
			String url = APIUtils.getInstance().getValueByKey("hiCDMACommonAPI");
			parameters.add(new Parameter("method", GOODS_GETCOLORSANDIDS));
			parameters.add(new Parameter("goodsName", goodsName));
			String json = httpRequester.syncRequest(url, HttpRequester.POST, parameters, false);
			List<GoodsBaseColor> goodsbasecolors = JsonToBeanUtils.processJsonToList(json, GoodsBaseColor.class, mContext);
			return goodsbasecolors;
		} catch (Exception e) {
			e.printStackTrace();
			whenNetTimeout(mContext, mCallBack);
		}
		return null;
	}
	public List<GoodsPictures> getGoodsPicturesByGoodsIds(String[] goodsIds, List<Parameter> parameters, Context mContext, NetExceptionCallBack mCallBack) {
		if (!NetUtil.checkNet(mContext)) {
			whenNetUnuse(mContext, mCallBack);
			return null;
		}
		try {
			StringBuffer ids = new StringBuffer();
			HttpRequester httpRequester = new HttpRequester();
			String url = APIUtils.getInstance().getValueByKey("hiCDMACommonAPI");
			for (String goodsid : goodsIds) {
				ids.append(goodsid).append(",");
			}
			ids.deleteCharAt(ids.lastIndexOf(","));
			parameters.add(new Parameter("method", GOODS_GETPICTURES));
			parameters.add(new Parameter("goodsId", ids.toString()));
			String json = httpRequester.syncRequest(url, HttpRequester.POST, parameters, false);
			List<GoodsPictures> goodsPictures = JsonToBeanUtils.processJsonToList(json, GoodsPictures.class, mContext);
			return goodsPictures;
		} catch (Exception e) {
			e.printStackTrace();
			whenNetTimeout(mContext, mCallBack);
		}
		return null;
	}
	public List<GoodsPackaging> getGoodsPackagingByGoodsIds(String[] goodsIds, List<Parameter> parameters, Context mContext, NetExceptionCallBack mCallBack) {
		if (!NetUtil.checkNet(mContext)) {
			whenNetUnuse(mContext, mCallBack);
			return null;
		}
		try {
			StringBuffer ids = new StringBuffer();
			HttpRequester httpRequester = new HttpRequester();
			String url = APIUtils.getInstance().getValueByKey("hiCDMACommonAPI");
			for (String goodsid : goodsIds) {
				ids.append(goodsid).append(",");
			}
			ids.deleteCharAt(ids.lastIndexOf(","));
			parameters.add(new Parameter("method", GOODS_GETPACKAGING));
			parameters.add(new Parameter("goodsId", ids.toString()));
			String json = httpRequester.syncRequest(url, HttpRequester.POST, parameters, false);
			List<GoodsPackaging> goodsPictures = JsonToBeanUtils.processJsonToList(json, GoodsPackaging.class, mContext);
			return goodsPictures;
		} catch (Exception e) {
			e.printStackTrace();
			whenNetTimeout(mContext, mCallBack);
		}
		return null;
	}
	public List<GoodsSaleService> getGoodsSaleServiceByGoodsIds(String[] goodsIds, List<Parameter> parameters, Context mContext, NetExceptionCallBack mCallBack) {
		if (!NetUtil.checkNet(mContext)) {
			whenNetUnuse(mContext, mCallBack);
			return null;
		}
		try {
			StringBuffer ids = new StringBuffer();
			HttpRequester httpRequester = new HttpRequester();
			String url = APIUtils.getInstance().getValueByKey("hiCDMACommonAPI");
			for (String goodsid : goodsIds) {
				ids.append(goodsid).append(",");
			}
			ids.deleteCharAt(ids.lastIndexOf(","));
			parameters.add(new Parameter("method", GOODS_GETSALESERVICE));
			parameters.add(new Parameter("goodsId", ids.toString()));
			String json = httpRequester.syncRequest(url, HttpRequester.POST, parameters, false);
			List<GoodsSaleService> goodsPictures = JsonToBeanUtils.processJsonToList(json, GoodsSaleService.class, mContext);
			return goodsPictures;
		} catch (Exception e) {
			e.printStackTrace();
			whenNetTimeout(mContext, mCallBack);
		}
		return null;
	}
	public List<GoodsColors> getGoodsColorsServiceByGoodsIds(String[] goodsIds, List<Parameter> parameters, Context mContext, NetExceptionCallBack mCallBack) {
		if (!NetUtil.checkNet(mContext)) {
			whenNetUnuse(mContext, mCallBack);
			return null;
		}
		try {
			StringBuffer ids = new StringBuffer();
			HttpRequester httpRequester = new HttpRequester();
			String url = APIUtils.getInstance().getValueByKey("hiCDMACommonAPI");
			for (String goodsid : goodsIds) {
				ids.append(goodsid).append(",");
			}
			ids.deleteCharAt(ids.lastIndexOf(","));
			parameters.add(new Parameter("method", GOODS_GETCOLORS));
			parameters.add(new Parameter("goodsId", ids.toString()));
			String json = httpRequester.syncRequest(url, HttpRequester.POST, parameters, false);
			List<GoodsColors> goodsColors = JsonToBeanUtils.processJsonToList(json, GoodsColors.class, mContext);
			return goodsColors;
		} catch (Exception e) {
			e.printStackTrace();
			whenNetTimeout(mContext, mCallBack);
		}
		return null;
	}
	public List<GoodsFilterResponseBody> getGoodsInfoByIndex(String startIndex, String maxNum, String orderByName, String orderDirec, List<Parameter> parameters, Context mContext, NetExceptionCallBack mCallBack) {
		if (!NetUtil.checkNet(mContext)) {
			whenNetUnuse(mContext, mCallBack);
			return null;
		}
		try {
			HttpRequester httpRequester = new HttpRequester();
			String url = APIUtils.getInstance().getValueByKey("hiCDMACommonAPI");
			parameters.add(new Parameter("method", GOODS_LIST));
			parameters.add(new Parameter("startIndex", startIndex + ""));
			parameters.add(new Parameter("maxNum", maxNum + ""));
			parameters.add(new Parameter("orderByName", orderByName));
			parameters.add(new Parameter("orderDirec", orderDirec));
			String json = httpRequester.syncRequest(url, HttpRequester.POST, parameters, false);
			List<GoodsFilterResponseBody> goods = JsonToBeanUtils.processJsonToList(json, GoodsFilterResponseBody.class, mContext);
			return goods;
		} catch (Exception e) {
			e.printStackTrace();
			whenNetTimeout(mContext, mCallBack);
		}
		return null;
	}
	public List<GoodsFilterResponseBody> getGoodsInfoByCondition(String startIndex, String maxNum, String orderByName, String orderDirec, String shopWithinBrandId, String shopWithinCategoryId, String priceInterval, List<Parameter> parameters, Context mContext, NetExceptionCallBack mCallBack) {
		if (!NetUtil.checkNet(mContext)) {
			whenNetUnuse(mContext, mCallBack);
			return null;
		}
		try {
			HttpRequester httpRequester = new HttpRequester();
			String url = APIUtils.getInstance().getValueByKey("hiCDMACommonAPI");
			parameters.add(new Parameter("method", GOODS_LIST));
			parameters.add(new Parameter("startIndex", startIndex + ""));
			parameters.add(new Parameter("maxNum", maxNum + ""));
			parameters.add(new Parameter("orderByName", orderByName));
			parameters.add(new Parameter("orderDirec", orderDirec));
			if (shopWithinBrandId != null) {
				parameters.add(new Parameter("shopWithinBrandId", shopWithinBrandId));
			}
			if (shopWithinCategoryId != null) {
				parameters.add(new Parameter("shopWithinCategoryId", shopWithinCategoryId));
			}
			if (priceInterval != null) {
				parameters.add(new Parameter("priceInterval", priceInterval));
			}
			String json = httpRequester.syncRequest(url, HttpRequester.POST, parameters, false);
			List<GoodsFilterResponseBody> goods = JsonToBeanUtils.processJsonToList(json, GoodsFilterResponseBody.class, mContext);
			return goods;
		} catch (Exception e) {
			e.printStackTrace();
			whenNetTimeout(mContext, mCallBack);
		}
		return null;
	}
	public List<GoodsFilterResponseBody> getBackShellByBrand(String brandId, String minPrice, String maxPrice, String startIndex, String maxNum, String orderByName, String orderDirec, List<Parameter> parameters, Context mContext, NetExceptionCallBack mCallBack) {
		if (!NetUtil.checkNet(mContext)) {
			whenNetUnuse(mContext, mCallBack);
			return null;
		}
		try {
			HttpRequester httpRequester = new HttpRequester();
			String url = APIUtils.getInstance().getValueByKey("hiCDMACommonAPI");
			parameters.add(new Parameter("method", GOODS_BACKSHELL_BRAND));
			parameters.add(new Parameter("startIndex", startIndex + ""));
			parameters.add(new Parameter("maxNum", maxNum + ""));
			parameters.add(new Parameter("orderByName", orderByName));
			parameters.add(new Parameter("orderDirec", orderDirec));
			if (brandId != null) {
				parameters.add(new Parameter("brandId", brandId));
			}
			if (maxPrice != null) {
				parameters.add(new Parameter("highestPrice", maxPrice));
			}
			if (minPrice != null) {
				parameters.add(new Parameter("lowestPrice", minPrice));
			}
			String json = httpRequester.syncRequest(url, HttpRequester.POST, parameters, false);
			List<GoodsFilterResponseBody> goods = JsonToBeanUtils.processJsonToList(json, GoodsFilterResponseBody.class, mContext);
			return goods;
		} catch (Exception e) {
			e.printStackTrace();
			whenNetTimeout(mContext, mCallBack);
		}
		return null;
	}
	public List<GoodsGroupResponseBody> getGoodsGroupById(String goodsGroupId, List<Parameter> parameters, Context mContext, NetExceptionCallBack mCallBack) {
		if (!NetUtil.checkNet(mContext)) {
			whenNetUnuse(mContext, mCallBack);
			return null;
		}
		try {
			HttpRequester httpRequester = new HttpRequester();
			String url = APIUtils.getInstance().getValueByKey("hiCDMACommonAPI");
			parameters.add(new Parameter("method", GET_GOODSGROUP));
			parameters.add(new Parameter("goodsGroupId", goodsGroupId));
			String json = httpRequester.syncRequest(url, HttpRequester.POST, parameters, false);
			List<GoodsGroupResponseBody> goods = JsonToBeanUtils.processJsonToList(json, GoodsGroupResponseBody.class, mContext);
			return goods;
		} catch (Exception e) {
			whenNetTimeout(mContext, mCallBack);
		}
		return null;
	}
	/**
	 * 经销商列表
	 * */
	public List<GoodsDealer> getGoodsDealerByIds(String goodsId, String areaCode, List<Parameter> parameters, Context mContext, NetExceptionCallBack mCallBack) {
		if (!NetUtil.checkNet(mContext)) {
			whenNetUnuse(mContext, mCallBack);
			return null;
		}
		try {
			HttpRequester httpRequester = new HttpRequester();
			String url = APIUtils.getInstance().getValueByKey("hiCDMACommonAPI");
			parameters.add(new Parameter("method", GOODS_SALESMAN_GETSALESMANBYGOODSID));
			parameters.add(new Parameter("goodsId", goodsId));
			parameters.add(new Parameter("areaCode", areaCode));
			String json = httpRequester.syncRequest(url, HttpRequester.POST, parameters, false);
			List<GoodsDealer> goodsDealer = JsonToBeanUtils.processJsonToList3(json, GoodsDealer.class, mContext);
			return goodsDealer;
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
