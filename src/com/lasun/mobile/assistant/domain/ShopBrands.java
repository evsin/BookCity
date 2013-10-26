package com.lasun.mobile.assistant.domain;

import java.io.Serializable;

/**
 * <pre>
 * 业务名:
 * 功能说明:根据品牌群组ID获取品牌列表
 * 编写日期:	
 * 作者:	 张云飞
 * 
 * 历史记录
 * 1、修改日期：
 *    修改人：
 *    修改内容：
 * </pre>
 */
public class ShopBrands implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -57255924279784854L;

	private String begin_time; 
	private String link; 
	private String img;
	private String group_id; 
	private String brand_id; 
	private String brand_desc; 
	private String base_brand_name; 
	private String seo_title; 
	private String brand_name;
	private String id; 
	private String title; 
	private String is_show; 
	private String last_update; 
	private String seq_no; 
	private String end_time; 
	private String description; 
	private String imagesid; 
	private String mall_url; 
	private String brand_logo; 
	private String site_url; 
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

	public String getBegin_time() {
		return begin_time;
	}

	public void setBegin_time(String begin_time) {
		this.begin_time = begin_time;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getGroup_id() {
		return group_id;
	}

	public void setGroup_id(String group_id) {
		this.group_id = group_id;
	}

	public String getBrand_id() {
		return brand_id;
	}

	public void setBrand_id(String brand_id) {
		this.brand_id = brand_id;
	}

	public String getBrand_desc() {
		return brand_desc;
	}

	public void setBrand_desc(String brand_desc) {
		this.brand_desc = brand_desc;
	}

	public String getBase_brand_name() {
		return base_brand_name;
	}

	public void setBase_brand_name(String base_brand_name) {
		this.base_brand_name = base_brand_name;
	}

	public String getSeo_title() {
		return seo_title;
	}

	public void setSeo_title(String seo_title) {
		this.seo_title = seo_title;
	}

	public String getBrand_name() {
		return brand_name;
	}

	public void setBrand_name(String brand_name) {
		this.brand_name = brand_name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getIs_show() {
		return is_show;
	}

	public void setIs_show(String is_show) {
		this.is_show = is_show;
	}

	public String getLast_update() {
		return last_update;
	}

	public void setLast_update(String last_update) {
		this.last_update = last_update;
	}

	public String getSeq_no() {
		return seq_no;
	}

	public void setSeq_no(String seq_no) {
		this.seq_no = seq_no;
	}

	public String getEnd_time() {
		return end_time;
	}

	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImagesid() {
		return imagesid;
	}

	public void setImagesid(String imagesid) {
		this.imagesid = imagesid;
	}

	public String getMall_url() {
		return mall_url;
	}

	public void setMall_url(String mall_url) {
		this.mall_url = mall_url;
	}

	public String getBrand_logo() {
		return brand_logo;
	}

	public void setBrand_logo(String brand_logo) {
		this.brand_logo = brand_logo;
	}

	public String getSite_url() {
		return site_url;
	}

	public void setSite_url(String site_url) {
		this.site_url = site_url;
	}

}
