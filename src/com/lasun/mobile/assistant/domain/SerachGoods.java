/*******************************************************************************
 * Copyright (c) 2011 by lasun Corporation all right reserved.
 * 2011-8-16 
 * 
 *******************************************************************************/
package com.lasun.mobile.assistant.domain;

import java.io.Serializable;

public class SerachGoods implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2530570837056843829L;
	private String keyword; 
	private String itemCount; 
	private String startIndex; 
	private String currentCount; 

	private String goodsName; 
	private String goodsId; 
	private String goodsThumb; 
	private String marketPrice; 
	private String goodsBrandName;
	private String promoteTitle2; 
	private String goodsMiddleImg; 
	private String local_sale;
	private String province_sale;
	private String promote_title2;
	private String base_color;

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

	private String code;
	private String msg;

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
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

	public String getGoodsBrandName() {
		return goodsBrandName;
	}

	public void setGoodsBrandName(String goodsBrandName) {
		this.goodsBrandName = goodsBrandName;
	}

	public String getPromoteTitle2() {
		return promoteTitle2;
	}

	public void setPromoteTitle2(String promoteTitle2) {
		this.promoteTitle2 = promoteTitle2;
	}

	public String getGoodsMiddleImg() {
		return goodsMiddleImg;
	}

	public void setGoodsMiddleImg(String goodsMiddleImg) {
		this.goodsMiddleImg = goodsMiddleImg;
	}

	public String getLocal_sale() {
		return local_sale;
	}

	public void setLocal_sale(String local_sale) {
		this.local_sale = local_sale;
	}

	public String getProvince_sale() {
		return province_sale;
	}

	public void setProvince_sale(String province_sale) {
		this.province_sale = province_sale;
	}

	public String getPromote_title2() {
		return promote_title2;
	}

	public void setPromote_title2(String promote_title2) {
		this.promote_title2 = promote_title2;
	}

	public String getBase_color() {
		return base_color;
	}

	public void setBase_color(String base_color) {
		this.base_color = base_color;
	}

}
