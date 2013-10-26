package com.lasun.mobile.assistant.domain;

import java.io.Serializable;

/**
 * 
 * <pre>
 * 业务名:
 * 功能说明: 
 * 编写日期:
 * 作者:	
 * 
 * 
 * 历史记录
 * 1、修改日期：
 *    修改人：
 *    修改内容：
 * </pre>
 */
public class AgencyBrand implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4985818405613803201L;
	private String base_brand_id;
	private String base_brand_name;

	private String code;
	private String msg;

	public String getBase_brand_id() {
		return base_brand_id;
	}

	public void setBase_brand_id(String base_brand_id) {
		this.base_brand_id = base_brand_id;
	}

	public String getBase_brand_name() {
		return base_brand_name;
	}

	public void setBase_brand_name(String base_brand_name) {
		this.base_brand_name = base_brand_name;
	}

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

}
