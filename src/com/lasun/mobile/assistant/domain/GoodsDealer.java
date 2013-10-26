package com.lasun.mobile.assistant.domain;

import java.io.Serializable;

/**
 * 
 * <pre>
 * 业务名:
 * 功能说明: 
 *    经销商信息
 * 编写日期:
 * 作者:
 * 
 * </pre>
 */
public class GoodsDealer implements Serializable {

	private String local_id;
	private String salesman_name;
	private String salesman_address;
	private String salesman_phone;
	public String getLocal_id() {
		return local_id;
	}
	public void setLocal_id(String local_id) {
		this.local_id = local_id;
	}
	public String getSalesman_name() {
		return salesman_name;
	}
	public void setSalesman_name(String salesman_name) {
		this.salesman_name = salesman_name;
	}
	public String getSalesman_address() {
		return salesman_address;
	}
	public void setSalesman_address(String salesman_address) {
		this.salesman_address = salesman_address;
	}
	public String getSalesman_phone() {
		return salesman_phone;
	}
	public void setSalesman_phone(String salesman_phone) {
		this.salesman_phone = salesman_phone;
	}


}
