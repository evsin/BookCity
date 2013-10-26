/*******************************************************************************
 * Copyright (c) 2011 by lasun Corporation all right reserved.
 * 2011-8-16 
 * 
 *******************************************************************************/
package com.lasun.mobile.assistant.domain;

/**
 * <pre>
 * 业务名:
 * 功能说明: 
 * 
 *     获取赠品信息
 * 编写日期:	2011-8-16
 * 作者:	 李丰川
 * 
 * 历史记录
 * 1、修改日期：
 *    修改人：
 *    修改内容：
 * </pre>
 */
public class GoodsGifts {
	private String goodsId;
	private String giftName;
	private String giftImg;
	private String giftPrice;
	private String giftDesc;
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
	public String getGiftName() {
		return giftName;
	}
	public void setGiftName(String giftName) {
		this.giftName = giftName;
	}
	public String getGiftImg() {
		return giftImg;
	}
	public void setGiftImg(String giftImg) {
		this.giftImg = giftImg;
	}
	public String getGiftPrice() {
		return giftPrice;
	}
	public void setGiftPrice(String giftPrice) {
		this.giftPrice = giftPrice;
	}
	public String getGiftDesc() {
		return giftDesc;
	}
	public void setGiftDesc(String giftDesc) {
		this.giftDesc = giftDesc;
	}
	
	

}
