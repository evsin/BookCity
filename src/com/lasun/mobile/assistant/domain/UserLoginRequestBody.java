/*******************************************************************************
 * Copyright (c) 2011 by lasun Corporation all right reserved.
 * 2011-8-16 
 * 
 *******************************************************************************/
package com.lasun.mobile.assistant.domain;

import java.io.Serializable;

/**
 * <pre>
 * 业务名:
 * 功能说明:用户登录请求体
 * 编写日期:	2011-8-16
 * 作者:	 刘斌
 * 
 * 历史记录
 * 1、修改日期：
 *    修改人：
 *    修改内容：
 * </pre>
 */
public class UserLoginRequestBody implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1623273870203182291L;

	public static final String User_Agent = "User-Agent";
	public static String ctsetSign = "600"; 
	public static String channelSign = "112";
	private String userName; 
	private String userPassword; 
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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
}
