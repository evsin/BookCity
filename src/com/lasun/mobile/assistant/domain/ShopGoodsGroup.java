/*******************************************************************************
 * Copyright (c) 2011 by lasun Corporation all right reserved.
 * 2011-8-16 
 * 
 *******************************************************************************/
package com.lasun.mobile.assistant.domain;

import java.io.Serializable;


public class ShopGoodsGroup implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1597138126053737265L;

	private String goodsGroupId; 
	private String goods_name; 
	private String goods_middle_img;
	private String goods_id; 
	private String into_price;
	private String base_goods_name;
	private String goods_number;
	private String min_buy_number;
	private String base_chbm;
	private String base_color;
	private String base_brand_id;
	private String is_hot;
	private String goods_indexurl;
	private String goods_img;
	private String min_add_number;
	private String purchase_quantity;
	private String goods_thumb; 
	private String market_price; 
	private String base_brand_name; 
	private String promote_title1; 
	private String promote_title2;
	private String local_sale;
	private String province_sale;
	private String p_rank; 
	private String l_rank; 
	private GoodsUrlInfo sources;

	public String getP_rank() {
		return p_rank;
	}

	public void setP_rank(String p_rank) {
		this.p_rank = p_rank;
	}

	public String getL_rank() {
		return l_rank;
	}

	public void setL_rank(String l_rank) {
		this.l_rank = l_rank;
	}

	public GoodsUrlInfo getSources() {
		return sources;
	}

	public void setSources(GoodsUrlInfo sources) {
		this.sources = sources;
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

	private String gift_ids; 
	private String img_url; 
	private String alive;
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

	public String getGoodsGroupId() {
		return goodsGroupId;
	}

	public String getGoods_name() {
		return goods_name;
	}

	public void setGoods_name(String goods_name) {
		this.goods_name = goods_name;
	}

	public String getGoods_middle_img() {
		return goods_middle_img;
	}

	public void setGoods_middle_img(String goods_middle_img) {
		this.goods_middle_img = goods_middle_img;
	}

	public String getGoods_id() {
		return goods_id;
	}

	public void setGoods_id(String goods_id) {
		this.goods_id = goods_id;
	}

	public String getInto_price() {
		return into_price;
	}

	public void setInto_price(String into_price) {
		this.into_price = into_price;
	}

	public String getBase_goods_name() {
		return base_goods_name;
	}

	public void setBase_goods_name(String base_goods_name) {
		this.base_goods_name = base_goods_name;
	}

	public String getGoods_number() {
		return goods_number;
	}

	public void setGoods_number(String goods_number) {
		this.goods_number = goods_number;
	}

	public String getMin_buy_number() {
		return min_buy_number;
	}

	public void setMin_buy_number(String min_buy_number) {
		this.min_buy_number = min_buy_number;
	}

	public String getBase_chbm() {
		return base_chbm;
	}

	public void setBase_chbm(String base_chbm) {
		this.base_chbm = base_chbm;
	}

	public String getBase_color() {
		return base_color;
	}

	public void setBase_color(String base_color) {
		this.base_color = base_color;
	}

	public String getBase_brand_id() {
		return base_brand_id;
	}

	public void setBase_brand_id(String base_brand_id) {
		this.base_brand_id = base_brand_id;
	}

	public String getIs_hot() {
		return is_hot;
	}

	public void setIs_hot(String is_hot) {
		this.is_hot = is_hot;
	}

	public String getGoods_indexurl() {
		return goods_indexurl;
	}

	public void setGoods_indexurl(String goods_indexurl) {
		this.goods_indexurl = goods_indexurl;
	}

	public String getGoods_img() {
		return goods_img;
	}

	public void setGoods_img(String goods_img) {
		this.goods_img = goods_img;
	}

	public String getMin_add_number() {
		return min_add_number;
	}

	public void setMin_add_number(String min_add_number) {
		this.min_add_number = min_add_number;
	}

	public String getPurchase_quantity() {
		return purchase_quantity;
	}

	public void setPurchase_quantity(String purchase_quantity) {
		this.purchase_quantity = purchase_quantity;
	}

	public String getGoods_thumb() {
		return goods_thumb;
	}

	public void setGoods_thumb(String goods_thumb) {
		this.goods_thumb = goods_thumb;
	}

	public String getMarket_price() {
		return market_price;
	}

	public void setMarket_price(String market_price) {
		this.market_price = market_price;
	}

	public String getBase_brand_name() {
		return base_brand_name;
	}

	public void setBase_brand_name(String base_brand_name) {
		this.base_brand_name = base_brand_name;
	}

	public String getPromote_title1() {
		return promote_title1;
	}

	public void setPromote_title1(String promote_title1) {
		this.promote_title1 = promote_title1;
	}

	public String getPromote_title2() {
		return promote_title2;
	}

	public void setPromote_title2(String promote_title2) {
		this.promote_title2 = promote_title2;
	}

	public String getGift_ids() {
		return gift_ids;
	}

	public void setGift_ids(String gift_ids) {
		this.gift_ids = gift_ids;
	}

	public String getImg_url() {
		return img_url;
	}

	public void setImg_url(String img_url) {
		this.img_url = img_url;
	}

	public String getAlive() {
		return alive;
	}

	public void setAlive(String alive) {
		this.alive = alive;
	}

	public void setGoodsGroupId(String goodsGroupId) {
		this.goodsGroupId = goodsGroupId;
	}

	@Override
	public String toString() {
		return "ShopGoodsGroup [goodsGroupId=" + goodsGroupId + ", goods_name="
				+ goods_name + ", goods_middle_img=" + goods_middle_img
				+ ", goods_id=" + goods_id + ", into_price=" + into_price
				+ ", base_goods_name=" + base_goods_name + ", goods_number="
				+ goods_number + ", min_buy_number=" + min_buy_number
				+ ", base_chbm=" + base_chbm + ", base_color=" + base_color
				+ ", base_brand_id=" + base_brand_id + ", is_hot=" + is_hot
				+ ", goods_indexurl=" + goods_indexurl + ", goods_imag="
				+ goods_img + ", min_add_number=" + min_add_number
				+ ", purchase_quantity=" + purchase_quantity + ", goods_thumb="
				+ goods_thumb + ", market_price=" + market_price
				+ ", base_brand_name=" + base_brand_name + ", promote_title1="
				+ promote_title1 + ", promote_title2=" + promote_title2
				+ ", gift_ids=" + gift_ids + ", img_url=" + img_url
				+ ", alive=" + alive + ", code=" + code + ", msg=" + msg + "]";
	}

}
