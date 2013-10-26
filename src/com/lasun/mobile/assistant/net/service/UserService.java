package com.lasun.mobile.assistant.net.service;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import android.content.Context;
import android.os.Looper;
import android.util.Log;
import com.lasun.mobile.assistant.domain.AgencyBrand;
import com.lasun.mobile.assistant.domain.AgencyGoods;
import com.lasun.mobile.assistant.domain.UserLoginRequestBody;
import com.lasun.mobile.assistant.domain.UserLoginResponseBody;
import com.lasun.mobile.assistant.http.HttpRequester;
import com.lasun.mobile.assistant.utils.APIUtils;
import com.lasun.mobile.assistant.utils.JsonToBeanUtils;
import com.lasun.mobile.assistant.utils.NetExceptionUtils;
import com.lasun.mobile.assistant.utils.NetUtil;
import com.lasun.moblile.assistant.core.Parameter;

public class UserService implements APIService {
	public UserLoginResponseBody userLogin(String userName, String userPassword, List<Parameter> parameters, Context mContext, NetExceptionCallBack mCallBack) {
		if (!NetUtil.checkNet(mContext)) {
			whenNetUnuse(mContext, mCallBack);
			return null;
		}
		try {
			HttpRequester httpRequester = new HttpRequester();
			String url = APIUtils.getInstance().getValueByKey("hiCDMACommonAPI");
			parameters.add(new Parameter("method", USER_LOGIN));
			parameters.add(new Parameter("ctsetSign", UserLoginRequestBody.ctsetSign));
			parameters.add(new Parameter("channelSign", UserLoginRequestBody.channelSign));
			parameters.add(new Parameter("userName", URLEncoder.encode(userName, "utf-8")));
			parameters.add(new Parameter("userPassword", userPassword));
			String json = httpRequester.syncRequest(url, HttpRequester.POST, parameters, false);
			UserLoginResponseBody userLogin = JsonToBeanUtils.processJsonToBean(json, UserLoginResponseBody.class, mContext);
			return userLogin;
		} catch (Exception e) {
			whenNetTimeout(mContext, mCallBack);
		}
		return null;
	}
	public String changepwd(String memberId, String newPassword, String oldPassword, List<Parameter> parameters, Context mContext, NetExceptionCallBack mCallBack) {
		if (!NetUtil.checkNet(mContext)) {
			whenNetUnuse(mContext, mCallBack);
			return null;
		}
		try {
			HttpRequester httpRequester = new HttpRequester();
			String url = APIUtils.getInstance().getValueByKey("hiCDMACommonAPI");
			parameters.add(new Parameter("method", USER_USERCHANGEPWD));
			parameters.add(new Parameter("memberId", memberId));
			parameters.add(new Parameter("oldPwd", oldPassword));
			parameters.add(new Parameter("newPwd", newPassword));
			String json = httpRequester.syncRequest(url, HttpRequester.POST, parameters, false);
			String vaule = JsonToBeanUtils.processJsonToValue2(json, "item");
			return vaule;
		} catch (Exception e) {
			whenNetTimeout(mContext, mCallBack);
		}
		return null;
	}
	public List<AgencyGoods> getUserGoodsList(String memberId, String areaCode, List<Parameter> parameters, final Context mContext, final NetExceptionCallBack mCallBack) {
		if (!NetUtil.checkNet(mContext)) {
			whenNetUnuse(mContext, mCallBack);
			return null;
		}
		try {
			HttpRequester httpRequester = new HttpRequester();
			String url = APIUtils.getInstance().getValueByKey("hiCDMACommonAPI");
			parameters.add(new Parameter("method", USER_GETNEWGOODSLIST));
			parameters.add(new Parameter("memberId", memberId));
			parameters.add(new Parameter("areaCode", areaCode));
			String json = httpRequester.syncRequest(url, HttpRequester.POST, parameters, false);
			List<AgencyGoods> goods = JsonToBeanUtils.processJsonToList(json, AgencyGoods.class, mContext);
			return goods;
		} catch (Exception e) {
			e.printStackTrace();
			whenNetTimeout(mContext, mCallBack);
		}
		return null;
	}
	public List<AgencyBrand> getUserBrandList(String memberId, List<Parameter> parameters, final Context mContext, final NetExceptionCallBack mCallBack) {
		if (!NetUtil.checkNet(mContext)) {
			whenNetUnuse(mContext, mCallBack);
			return null;
		}
		try {
			HttpRequester httpRequester = new HttpRequester();
			String url = APIUtils.getInstance().getValueByKey("hiCDMACommonAPI");
			parameters.add(new Parameter("method", USER_GETBRANDS));
			parameters.add(new Parameter("memberId", memberId));
			String json = httpRequester.syncRequest(url, HttpRequester.POST, parameters, false);
			List<AgencyBrand> Brands = JsonToBeanUtils.processJsonToList(json, AgencyBrand.class, mContext);
			return Brands;
		} catch (Exception e) {
			e.printStackTrace();
			whenNetTimeout(mContext, mCallBack);
		}
		return null;
	}
	public String modifiyPassWord(String oldPassword, String newPassword, List<Parameter> parameters, Context mContext, NetExceptionCallBack mCallBack) {
		if (!NetUtil.checkNet(mContext)) {
			whenNetUnuse(mContext, mCallBack);
			return null;
		}
		try {
			HttpRequester httpRequester = new HttpRequester();
			String url = APIUtils.getInstance().getValueByKey("hiCDMACommonAPI");
			parameters.add(new Parameter("method", USER_MODIFIYPASSWORD));
			String json = httpRequester.syncRequestLongTime(url, HttpRequester.POST, parameters, false);
			return JsonToBeanUtils.processJsonToValue(json, "returnVal", mContext);
		} catch (Exception e) {
			e.printStackTrace();
			whenNetTimeout(mContext, mCallBack);
		}
		return null;
	}
	public String queryMobileNumByImsi(String imsi, String imei, String mac_addr, String os_name, String model, Context mContext, NetExceptionCallBack mCallBack) {
		if (!NetUtil.checkNet(mContext)) {
			whenNetUnuse(mContext, mCallBack);
			return null;
		}
		try {
			List<Parameter> parameters = new ArrayList<Parameter>();
			HttpRequester httpRequester = new HttpRequester();
			String url = APIUtils.getInstance().getValueByKey("hiCDMACommonAPI");
			parameters.add(new Parameter("method", UDB_QUERYMOBILENUMBYIMSI));
			parameters.add(new Parameter("IMSI", imsi));
			parameters.add(new Parameter("IMEI", imei));
			parameters.add(new Parameter("MAC_ADDR", mac_addr));
			parameters.add(new Parameter("OS_NAME", os_name));
			parameters.add(new Parameter("MODEL", model));
			String json = httpRequester.syncRequestLongTime(url, HttpRequester.POST, parameters, false);
			String returnVal = JsonToBeanUtils.processJsonToValue(json, "returnVal", mContext);
			Log.d("TAG", returnVal);
			return returnVal;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * VIP筛选
	 * */
	public List<AgencyGoods> getVIPGoodsList(String memberId, String stockState, String goodsSmartStatus, String goodsGType, String brandId, String areaCode, List<Parameter> parameters, Context mContext, NetExceptionCallBack mCallBack) {
		if (!NetUtil.checkNet(mContext)) {
			whenNetUnuse(mContext, mCallBack);
			return null;
		}
		try {
			HttpRequester httpRequester = new HttpRequester();
			String url = APIUtils.getInstance().getValueByKey("hiCDMACommonAPI");
			parameters.add(new Parameter("method", USERINFO_FILTERNEWGOODSLIST));
			parameters.add(new Parameter("memberId", memberId));
			parameters.add(new Parameter("stockState", stockState));
			parameters.add(new Parameter("goodsSmartStatus", goodsSmartStatus));
			parameters.add(new Parameter("goodsGType", goodsGType));
			parameters.add(new Parameter("brandId", brandId));
			parameters.add(new Parameter("areaCode", areaCode));
			String json = httpRequester.syncRequest(url, HttpRequester.POST, parameters, false);
			List<AgencyGoods> goods = JsonToBeanUtils.processJsonToList(json, AgencyGoods.class, mContext);
			return goods;
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
