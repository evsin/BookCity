/*******************************************************************************
 * Copyright (c) 2011 by Hirisun Corporation all right reserved.
 * 2011-8-11 
 * 
 *******************************************************************************/ 
package com.lasun.mobile.assistant.domain;

import java.io.Serializable;


public class GoodsPackaging implements Serializable {
	
	private String goodsId;
	private String goodsPackaging;
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
	public String getGoodsPackaging() {
		return goodsPackaging;
	}
	public void setGoodsPackaging(String goodsPackaging) {
		this.goodsPackaging = goodsPackaging;
	}
	
	
	


}
