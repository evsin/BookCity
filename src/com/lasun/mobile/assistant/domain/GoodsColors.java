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
 * 功能说明:获取商品颜色信息
 * 编写日期:	2011-8-16
 * 作者:	 刘斌
 * 
 * 历史记录
 * 1、修改日期：
 *    修改人：
 *    修改内容：
 * </pre>
 */
public class GoodsColors implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5816001001683095776L;
	private String goodsId; 
	private String colorName; 
	private String thumbUrl45; 
	private String goodsUrl; 
	
	
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
	
	public String getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}
	public String getColorName() {
		return colorName;
	}
	public void setColorName(String colorName) {
		this.colorName = colorName;
	}
	public String getThumbUrl45() {
		return thumbUrl45;
	}
	public void setThumbUrl45(String thumbUrl45) {
		this.thumbUrl45 = thumbUrl45;
	}
	public String getGoodsUrl() {
		return goodsUrl;
	}
	public void setGoodsUrl(String goodsUrl) {
		this.goodsUrl = goodsUrl;
	}
	@Override
	public String toString() {
		return "GoodsColors [colorName=" + colorName + ", goodsId=" + goodsId
				+ ", goodsUrl=" + goodsUrl + ", thumbUrl45=" + thumbUrl45 + "]";
	}
}
