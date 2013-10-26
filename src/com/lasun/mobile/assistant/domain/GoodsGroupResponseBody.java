/*******************************************************************************
 * Copyright (c) 2011 by lasun Corporation all right reserved.
 * 2011-8-16 
 * 
 *******************************************************************************/
package com.lasun.mobile.assistant.domain;

import java.io.Serializable;

/**
 * <pre>
 * 业务名:
 * 功能说明:获取商品列表响应体
 * 编写日期:	2011-8-16
 * 作者:	 刘斌
 * 
 * 历史记录
 * 1、修改日期：
 *    修改人：
 *    修改内容：
 * </pre>
 */
public class GoodsGroupResponseBody implements Serializable {

	private static final long serialVersionUID = -6046033148473707560L;
	private String goodsGroupId; 
	private String goodsName; 
	private String goodsId; 
	private String goodsThumb; 
	private String marketPrice;
	private String shopPrice; 
	private String goodsRate; 
	private String goodsBrandId; 
	private String goodsBrandName; 
	private String goodsCategoryId; 
	private String goodsCategoryName; 
	private String promoteTitle; 
	private String goodsGiftInfo; 
	private String shortDesc; 
	private String goodsDesc;
	private String goodsPreviewImg; 
	private String goodsUrl; 
	private String goodsLongName;
	private String evaluatePsnum;
	private String status;

	public String getEvaluatePsnum() {
		return evaluatePsnum;
	}

	public void setEvaluatePsnum(String evaluatePsnum) {
		this.evaluatePsnum = evaluatePsnum;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getGoodsGroupId() {
		return goodsGroupId;
	}

	public void setGoodsGroupId(String goodsGroupId) {
		this.goodsGroupId = goodsGroupId;
	}

	public String getGoodsBrandId() {
		return goodsBrandId;
	}

	public void setGoodsBrandId(String goodsBrandId) {
		this.goodsBrandId = goodsBrandId;
	}

	public String getGoodsCategoryId() {
		return goodsCategoryId;
	}

	public void setGoodsCategoryId(String goodsCategoryId) {
		this.goodsCategoryId = goodsCategoryId;
	}

	public String getPromoteTitle() {
		return promoteTitle;
	}

	public void setPromoteTitle(String promoteTitle) {
		this.promoteTitle = promoteTitle;
	}

	public String getGoodsGiftInfo() {
		return goodsGiftInfo;
	}

	public void setGoodsGiftInfo(String goodsGiftInfo) {
		this.goodsGiftInfo = goodsGiftInfo;
	}

	public String getShortDesc() {
		return shortDesc;
	}

	public void setShortDesc(String shortDesc) {
		this.shortDesc = shortDesc;
	}

	public String getGoodsDesc() {
		return goodsDesc;
	}

	public void setGoodsDesc(String goodsDesc) {
		this.goodsDesc = goodsDesc;
	}

	public String getGoodsPreviewImg() {
		return goodsPreviewImg;
	}

	public void setGoodsPreviewImg(String goodsPreviewImg) {
		this.goodsPreviewImg = goodsPreviewImg;
	}

	private byte[] imageData;
	private String code;
	private String msg;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	@Override
	public String toString() {
		return "GoodsGroupResponseBody [goodsBrandId=" + goodsBrandId
				+ ", goodsBrandName=" + goodsBrandName + ", goodsCategoryId="
				+ goodsCategoryId + ", goodsCategoryName=" + goodsCategoryName
				+ ", goodsDesc=" + goodsDesc + ", goodsGiftInfo="
				+ goodsGiftInfo + ", goodsGroupId=" + goodsGroupId
				+ ", goodsId=" + goodsId + ", goodsName=" + goodsName
				+ ", goodsPreviewImg=" + goodsPreviewImg + ", goodsRate="
				+ goodsRate + ", goodsThumb=" + goodsThumb + ", goodsUrl="
				+ goodsUrl + ",marketPrice=" + marketPrice + ", msg=" + msg
				+ ", promoteTitle=" + promoteTitle + ", shopPrice=" + shopPrice
				+ ", shortDesc=" + shortDesc + "]";
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}

	public String getGoodsThumb() {
		return goodsThumb;
	}

	public void setGoodsThumb(String goodsThumb) {
		this.goodsThumb = goodsThumb;
	}

	public String getMarketPrice() {
		return marketPrice;
	}

	public void setMarketPrice(String marketPrice) {
		this.marketPrice = marketPrice;
	}

	public String getShopPrice() {
		return shopPrice;
	}

	public void setShopPrice(String shopPrice) {
		this.shopPrice = shopPrice;
	}

	public String getGoodsRate() {
		return goodsRate;
	}

	public void setGoodsRate(String goodsRate) {
		this.goodsRate = goodsRate;
	}

	public String getGoodsBrandName() {
		return goodsBrandName;
	}

	public void setGoodsBrandName(String goodsBrandName) {
		this.goodsBrandName = goodsBrandName;
	}

	public String getGoodsCategoryName() {
		return goodsCategoryName;
	}

	public void setGoodsCategoryName(String goodsCategoryName) {
		this.goodsCategoryName = goodsCategoryName;
	}

	public String getGoodsUrl() {
		return goodsUrl;
	}

	public void setGoodsUrl(String goodsUrl) {
		this.goodsUrl = goodsUrl;
	}

	public void setImageData(byte[] paramArrayOfByte) {
		this.imageData = paramArrayOfByte;
	}

	public byte[] getImageData() {
		return this.imageData;
	}

	public String getGoodsLongName() {
		if (goodsLongName != null) {
			goodsLongName = goodsLongName.replace("@", "");
		}
		return goodsLongName;
	}

	public void setGoodsLongName(String goodsLongName) {
		this.goodsLongName = goodsLongName;
	}

}
