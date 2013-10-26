/*******************************************************************************
 * Copyright (c) 2011 by Hirisun Corporation all right reserved.
 * 2011-8-11 
 * 
 *******************************************************************************/
package com.lasun.mobile.assistant.domain;

import java.io.Serializable;


public class GoodsPictures implements Serializable{
	private String goodsId;
	private String imgUrl;
	private String ImgDesc;
	private String thumbUrl;
	private String imgOriginal;
	private String thumbUrl160;
	private String thumbUrl120;
	private String thumbUrl80;
	private String thumbUrl45;
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
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	public String getImgDesc() {
		return ImgDesc;
	}
	public void setImgDesc(String imgDesc) {
		ImgDesc = imgDesc;
	}
	public String getThumbUrl() {
		return thumbUrl;
	}
	public void setThumbUrl(String thumbUrl) {
		this.thumbUrl = thumbUrl;
	}
	public String getImgOriginal() {
		return imgOriginal;
	}
	public void setImgOriginal(String imgOriginal) {
		this.imgOriginal = imgOriginal;
	}
	public String getThumbUrl160() {
		return thumbUrl160;
	}
	public void setThumbUrl160(String thumbUrl160) {
		this.thumbUrl160 = thumbUrl160;
	}
	public String getThumbUrl120() {
		return thumbUrl120;
	}
	public void setThumbUrl120(String thumbUrl120) {
		this.thumbUrl120 = thumbUrl120;
	}
	public String getThumbUrl80() {
		return thumbUrl80;
	}
	public void setThumbUrl80(String thumbUrl80) {
		this.thumbUrl80 = thumbUrl80;
	}
	public String getThumbUrl45() {
		return thumbUrl45;
	}
	public void setThumbUrl45(String thumbUrl45) {
		this.thumbUrl45 = thumbUrl45;
	}
	
	
	
	

}
