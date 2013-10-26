package com.lasun.mobile.assistant.async;

import java.util.List;
import android.os.AsyncTask;
import com.lasun.mobile.assistant.domain.AgencyGoods;
import com.lasun.mobile.assistant.net.service.UserService;
import com.lasun.mobile.client.assistant.activity.MenuActivity;

public class VipStockScreenAsyn extends AsyncTask<String, Void, List<AgencyGoods>> {
	private UserService mService;
	private MenuActivity mAct;
	public VipStockScreenAsyn(UserService mService, MenuActivity mAct) {
		this.mService = mService;
		this.mAct = mAct;
	}
	@Override
	protected List<AgencyGoods> doInBackground(String... params) {
		return null;
	}
	@Override
	protected void onPreExecute() {
		super.onPreExecute();
	}
	@Override
	protected void onPostExecute(List<AgencyGoods> result) {
		super.onPostExecute(result);
	}
}
