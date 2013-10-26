package com.lasun.mobile.assistant.domain;

public abstract class User {
	protected String username;
	protected String pwd; 
	protected String id; 
	protected String UUID; 
	protected String UIMEI; 
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUUID() {
		return UUID;
	}
	public void setUUID(String uUID) {
		UUID = uUID;
	}
	public String getUIMEI() {
		return UIMEI;
	}
	public void setUIMEI(String uIMEI) {
		UIMEI = uIMEI;
	}
	
	

}
