package com.lasun.moblile.assistant.impl;

import java.util.HashMap;

import android.content.Context;

import com.lasun.moblile.assistant.core.CenterController;
import com.lasun.moblile.assistant.core.ContextService;
import com.lasun.moblile.assistant.core.ShareCache;

public class CenterControllerImpl extends CenterController {
	private final static HashMap<String, Object> mapCache = new HashMap<String, Object>();

	@Override
	public ShareCache getShareCache() {
		return sharecache;
	}

	public CenterControllerImpl(Context context) {
		sharecache = new ShareCacheImpl(context);
		contextservice = new ContextServiceImpl(context);

	}

	@Override
	public ContextService getContextService() {
		return contextservice;
	}

	@Override
	public HashMap<String, Object> getMapCache() {
		return mapCache;
	}
}
