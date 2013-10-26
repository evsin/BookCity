package com.lasun.moblile.assistant.core;

import java.util.HashMap;
import android.content.Context;
import com.lasun.moblile.assistant.impl.CenterControllerImpl;

public abstract class CenterController {
	protected static CenterController self;
	protected ShareCache sharecache;
	protected ContextService contextservice;
	protected HashMap<String, Object> mapCache;
	/** ȡ�ù��?�� */
	public abstract ShareCache getShareCache();
	public abstract ContextService getContextService();
	public abstract HashMap<String, Object> getMapCache();
	public static CenterController getCenterController(Context context) {
		if (self == null)
			self = new CenterControllerImpl(context);
		return self;
	}
}
