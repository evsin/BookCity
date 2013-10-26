package com.lasun.mobile.assistant.domain;

import java.io.Serializable;

/**
 * 
 * <pre>
 * 业务名:
 * 功能说明: 
 *    商品信息mode 类
 * 编写日期:
 * 作者:
 * 
 * </pre>
 */
public class GoodsInfo implements Serializable {

	private static final long serialVersionUID = 8414486149542449435L;
	private String goodsName;
	private String goodsId;

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

}
