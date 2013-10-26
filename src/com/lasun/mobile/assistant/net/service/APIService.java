/*******************************************************************************
 * Copyright (c) 2011 by Hirisun Corporation all right reserved.
 * 2011-8-11 
 * 
 *******************************************************************************/
package com.lasun.mobile.assistant.net.service;

public interface APIService {
	public static final String AD_GETADSBYPOSITION = "ad.getAdsByPosition";
	public static final String GOODS_LIST = "goods.filter";
	public static final String GOODS_BASEMODE = "goods.getBaseModelByBrand";
	public static final String GOODS_BACKSHELL_MODEL = "goods.getBackShellByModel";
	public static final String GOODS_BACKSHELL_BRAND = "goods.getBackShellByBrand";
	public static final String GOODS_GETNEWINFO = "goods.getNewInfo";
	public static final String GOODS_SOURCE = "goods.getSource";
	public static final String GOODS_GETCOLORSANDIDS = "goods.getColorsAndIds";
	public static final String GOODS_GETPICTURES = "goods.getPictures";
	public static final String GOODS_GETPACKAGING = "goods.getPackaging";
	public static final String GOODS_GETSALESERVICE = "goods.getSaleService";
	public static final String TUAN_GETFORECASTLIST = "tuan.getForecastList";
	public static final String TUAN_GETCARFFORECASTLIST = "tuan.getCardForecastList";
	public static final String GOODS_GETCOLORS = "goods.getColors";
	public static final String USER_GETBRANDS = "user.getAllBrandsByMember";
	public static final String USER_LOGIN = "user.login";
	public static final String MOBILEASSIST_GETLASTESTVERSIONINFO = "mobileAssist.getLastestVersionInfo";
	public static final String USER_USERCHANGEPWD = "user.userChangePwd";
	public static final String USER_GETNEWGOODSLIST = "userInfo.getNewGoodsList";
	public static final String SHOP_GETCATEGORYS = "shop.getCategorys";
	public static final String SHOP_GETBRANDS = "shop.getBrands";
	public static final String SHOP_BRANDGROUPS = "shop.brandGroups";
	public static final String GET_GOODSGROUP = "shop.getGoodsGroup";
	public static final String SHOP_GETNOTICES = "shop.getNotices";
	public static final String SPIKE_GETSORTINFO = "spike.getSortInfo";
	public static final String TUAN_GETSORTINFO = "tuan.getSortInfo";
	public static final String VALIDATEBUYQUALIFICATION = "tuan.validateBuyQualification";
	public static final String SHOP_GETSYSTEMTIME = "shop.getSystemTime";
	public static final String USERINFO_GETADDRESS = "userInfo.getAddress";
	public static final String USER_MODIFIYPASSWORD = "userInfo.editPWD";
	public static final String SHOP_GETSOFTWAREUPDATEDETAILS = "shop.getSoftwareUpdateDetails";
	public static final String UDB_QUERYMOBILENUMBYIMSI = "udb.queryMobileNumByImsi";
	public static final String GOODS_SERACHGOODS = "goods.serachGoods";
	public static final String GOODS_SALESMAN_GETSALESMANBYGOODSID = "goods_salesman.getSalesmanByGoodsId";
	public static final String GOODS_FILTERGOODS = "goods.filterGoods";
	public static final String USERINFO_FILTERNEWGOODSLIST = "userInfo.filterNewGoodsList";
	public static final int CANCEL = 0;
	public static final int RETRY = 1;
	public static final int UNUSE = 2;
	public static final int PREPARECONNECTION = 11;

	public interface NetExceptionCallBack {
		public void netExceptionCallback(int flag);
	}
}
