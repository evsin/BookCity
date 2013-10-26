package com.lasun.mobile.assistant.domain;

import java.io.Serializable;

/**
 * 
 * <pre>
 * 业务名;
 * 功能说明; 
 *    商品信息mode 类
 * 编写日期;
 * 作者;
 * 
 * </pre>
 */
public class AppInfo implements Serializable {

	private static final long serialVersionUID = 8414486149542449435L;
	private String version_id;
	private String version_name;
	private String version_no;
	private String version_date;
	private String version_url;
	private String version_content;

	public String getVersion_id() {
		return version_id;
	}

	public void setVersion_id(String version_id) {
		this.version_id = version_id;
	}

	public String getVersion_name() {
		return version_name;
	}

	public void setVersion_name(String version_name) {
		this.version_name = version_name;
	}

	public String getVersion_no() {
		return version_no;
	}

	public void setVersion_no(String version_no) {
		this.version_no = version_no;
	}

	public String getVersion_date() {
		return version_date;
	}

	public void setVersion_date(String version_date) {
		this.version_date = version_date;
	}

	public String getVersion_url() {
		return version_url;
	}

	public void setVersion_url(String version_url) {
		this.version_url = version_url;
	}

	public String getVersion_content() {
		return version_content;
	}

	public void setVersion_content(String version_content) {
		this.version_content = version_content;
	}

}
