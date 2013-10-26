package com.lasun.mobile.assistant.domain;

public class VipUser extends User {
	private UserGroup usergroup;

	public UserGroup getUsergroup() {
		return usergroup;
	}

	public void setUsergroup(UserGroup usergroup) {
		this.usergroup = usergroup;
	}
	
}
