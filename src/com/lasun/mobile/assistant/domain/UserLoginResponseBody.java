package com.lasun.mobile.assistant.domain;

import java.io.Serializable;


public class UserLoginResponseBody implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5219903270333084125L;
	private String returnVal; 
	private String userId; 
	private String userName; 
	private String isFirstRegister; 
	private String cityCode;   
	private String code;
	private String msg;
	
	

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
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

	public String getReturnVal() {
		return returnVal;
	}

	public void setReturnVal(String returnVal) {
		this.returnVal = returnVal;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getIsFirstRegister() {
		return isFirstRegister;
	}

	public void setIsFirstRegister(String isFirstRegister) {
		this.isFirstRegister = isFirstRegister;
	}
}
