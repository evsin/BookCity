package com.lasun.mobile.assistant.domain;

import java.util.ArrayList;
import java.util.List;

public class UserGroup {

	private static int B2B = 1;

	private List<User> b2bList = new ArrayList<User>();

	private static int SHOUHOU = 2;

	private List<User> shouhouList = new ArrayList<User>();

	public User getUserbyName(int usertype, String name) {
		List<User> l = getList(usertype);
		if (l != null) {
			for (User u : l) {
				if (name != null && !"".equals(name)
						&& name.equals(u.getUsername()))
					return u;
				else
					continue;
			}
		} else {
			return null;
		}
		return null;
	}

	public String getFirstUsername(int usertype) {
		List<User> l = getList(usertype);
		String username = null;
		if (l != null) {
			username = l.get(0).getUsername();
			return username;
		}
		return null;
	}
	public boolean setUser(int usertype, User user) {
		List<User> l = getList(usertype);
		if (l != null) {
			l.add(user);
			return true;
		} else {
			return false;
		}
	}

	public User getUserbyId(int usertype, String id) {

		List<User> l = getList(usertype);
		if (l != null) {
			for (User u : l) {
				if (id != null && !"".equals(id) && id.equals(u.getUsername()))
					return u;
				else
					continue;
			}
		} else {
			return null;
		}
		return null;

	}

	private List<User> getList(int usertype) {
		switch (usertype) {
		case 1:
			return b2bList;
		case 2:
			return shouhouList;
		default:
			return null;
		}
	}

}
