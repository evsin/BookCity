package com.lasun.moblile.assistant.core;

import com.lasun.mobile.assistant.domain.AppInfo;
import com.lasun.mobile.assistant.domain.User;
import android.app.Activity;
import android.content.Context;

public abstract class ContextService {
	public abstract boolean isNetworkAvailable();
	public abstract boolean getCurrentUser();
	public abstract boolean putCurrentUser(User user);
	public abstract AppInfo checkSoftVersion();
}
