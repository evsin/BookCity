/*******************************************************************************
 * Copyright (c) 2011 by Hirisun Corporation all right reserved.
 * 2011-8-10 
 * 
 *******************************************************************************/
package com.lasun.mobile.client.assistant.activity;

import java.util.ArrayList;
import java.util.List;

import com.lasun.mobile.assistant.domain.Advertisement;
import com.lasun.mobile.assistant.net.service.APIService.NetExceptionCallBack;
import com.lasun.mobile.assistant.net.service.AdvertisementService;
import com.lasun.moblile.assistant.core.Parameter;

import android.test.AndroidTestCase;


 
public class JsonUtilsTest extends AndroidTestCase {
	private static final String TAG = "JsonUtilsTest";

	private NetExceptionCallBack mNetExceptionCallBack = new NetExceptionCallBack() {

		@Override
		public void netExceptionCallback(int flag) {

		}
	};

	public void testService() {
		AdvertisementService service = new AdvertisementService();
		List<Advertisement> ads = service.getAdsByPosition("300360", new ArrayList<Parameter>(), getContext(), mNetExceptionCallBack);
		System.out.println(ads.size());
	}

}
