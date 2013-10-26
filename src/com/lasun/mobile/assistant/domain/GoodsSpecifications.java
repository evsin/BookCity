/*******************************************************************************
 * Copyright (c) 2011 by Hirisun Corporation all right reserved.
 * 2011-8-11 
 * 
 *******************************************************************************/
package com.lasun.mobile.assistant.domain;

import java.io.Serializable;

/**
 * <pre>
 * 业务名:
 * 功能说明: 
 *   商品规格参数
 * 编写日期:	2013-6-5
 * 作者:	 
 * 
 * 历史记录
 * 1、修改日期：
 *    修改人：
 *    修改内容：
 * </pre>
 */
public class GoodsSpecifications implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4422171377662961459L;
	private String goods_id;
	private String goods_attribute;
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

	public String getGoods_id() {
		return goods_id;
	}

	public void setGoods_id(String goods_id) {
		this.goods_id = goods_id;
	}

	public String getGoods_attribute() {
		return goods_attribute;
	}

	public void setGoods_attribute(String goods_attribute) {
		this.goods_attribute = goods_attribute;
	}

}
