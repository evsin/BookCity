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
public class GoodsFilterResponseBody implements Serializable {

	private static final long serialVersionUID = -6046033148473707560L;
	/**
	 * itemCount startIndex currentCount goodsName goodsId goodsThumb goodsImg
	 * goodsMiddleImg marketPrice shopPrice goodsRate goodsBrandName
	 * goodsCategoryName goodsUrl giftInfo goodsNameMiddle goodsNameLong star
	 * evaluatePsnum status
	 */
	/**
	 * */

	private String goodsImg;

	public String getGoodsImg() {
		return goodsImg;
	}

	public void setGoodsImg(String goodsImg) {
		this.goodsImg = goodsImg;
	}

	public String getGoodsMiddleImg() {
		return goodsMiddleImg;
	}

	public void setGoodsMiddleImg(String goodsMiddleImg) {
		this.goodsMiddleImg = goodsMiddleImg;
	}

	public String getGoodsNameMiddle() {
		return goodsNameMiddle;
	}

	public void setGoodsNameMiddle(String goodsNameMiddle) {
		this.goodsNameMiddle = goodsNameMiddle;
	}

	public String getEvaluatePsnum() {
		return evaluatePsnum;
	}

	public void setEvaluatePsnum(String evaluatePsnum) {
		this.evaluatePsnum = evaluatePsnum;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	private String goodsMiddleImg;

	private String goodsNameMiddle;

	private String evaluatePsnum;

	private String itemCount; 
	private String startIndex; 
	private String currentCount; 
	private String goodsName; 
	private String goodsId; 
	private String goodsThumb; 
	private String marketPrice; 
	private String shopPrice; 
	private String goodsRate; 
	private String goodsBrandName; 
	private String goodsCategoryName; 
	private String goodsUrl; 
	private byte[] imageData; 
	private String goodsNameLong; 
	private String status;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}



	public void setGoodsNameLong(String goodsNameLong) {
		this.goodsNameLong = goodsNameLong;
	}

	private String giftInfo;

	public String getGiftInfo() {
		return giftInfo;
	}

	public void setGiftInfo(String goodsGift) {
		this.giftInfo = goodsGift;
	}

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

	public String getItemCount() {
		return itemCount;
	}

	public void setItemCount(String itemCount) {
		this.itemCount = itemCount;
	}

	public String getStartIndex() {
		return startIndex;
	}

	public void setStartIndex(String startIndex) {
		this.startIndex = startIndex;
	}

	public String getCurrentCount() {
		return currentCount;
	}

	public void setCurrentCount(String currentCount) {
		this.currentCount = currentCount;
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

}
