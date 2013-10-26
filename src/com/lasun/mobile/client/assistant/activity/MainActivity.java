package com.lasun.mobile.client.assistant.activity;

import java.util.List;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;
import com.lasun.mobile.assistant.domain.AgencyBrand;
import com.lasun.mobile.assistant.net.service.UserService;
import com.lasun.mobile.assistant.net.service.APIService.NetExceptionCallBack;
import com.lasun.mobile.assistant.utils.LoginHelper;
import com.lasun.mobile.assistant.utils.PushServiceUtil;
import com.lasun.moblile.assistant.push.PollingSpreadService;

@SuppressLint("HandlerLeak")
public class MainActivity extends MenuActivity {
	private Handler mHandler;
	private ImageView start;
	private String currentUserName;
	private String currentUserId;
	private String currentUserCityCode;
	private int[] start_pic = new int[] { R.drawable.main_start_1, R.drawable.main_start_2, R.drawable.main_start_3 };
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		currentUserName = mShareCache.getDatefromStore(LoginActivity.CURRENT_USER_NAME);
		currentUserId = mShareCache.getDatefromStore(LoginActivity.CURRENT_USER_ID);
		currentUserCityCode = mShareCache.getDatefromStore(LoginHelper.CITYCODE);
		start = (ImageView) findViewById(R.id.start_iv);
		int num = (int) (Math.random() * 3);
		start.setBackgroundResource(start_pic[num]);
	}
	@Override
	protected void onStart() {
		super.onStart();
		mHandler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				switch (msg.what) {
				case 0:
					start.setBackgroundResource(R.drawable.start_bg2);
					mHandler.postDelayed(new Runnable() {
						@Override
						public void run() {
							mHandler.sendEmptyMessage(1);
						}
					}, 1500);
					break;
				case 1:
					Intent mIntent = new Intent();
					if (currentUserName != null && !"".equals(currentUserName)) {
						PushServiceUtil.openPushServiceForStart(MainActivity.this, PollingSpreadService.EXECUTE_MODE_APP);
						if (currentUserId != null && !"".equals(currentUserId) && "true".equals(mShareCache.getDatefromStore(LoginActivity.AUTO_LOGIN))) {
							loadUserBrands(currentUserId);
							mShareCache.putDatetoTemp(LoginActivity.CURRENT_USER_ID, currentUserId);
							mShareCache.putDatetoTemp(LoginActivity.CURRENT_USER_NAME, currentUserName);
							mShareCache.putDatetoStore(LoginHelper.CITYCODE, currentUserCityCode);
						}
					}
					mIntent.setClass(MainActivity.this, MainActivity2.class);
					startActivity(mIntent);
					MainActivity.this.finish();
					break;
				default:
					break;
				}
			}
		};
		mHandler.postDelayed(new Runnable() {
			@Override
			public void run() {
				mHandler.sendEmptyMessage(1);
			}
		}, 1500);
	}
	private void loadUserBrands(final String userId) {
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				List<AgencyBrand> brandData = new UserService().getUserBrandList(userId, getParameters(), MainActivity.this, new NetExceptionCallBack() {
					@Override
					public void netExceptionCallback(int flag) {
					}
				});
				mMapCache.put("userBrands", brandData);
			}
		});
		thread.start();
	}
	public void saveMyFirst() {
		SharedPreferences preferences = getSharedPreferences("first", Context.MODE_PRIVATE);
		Editor editor = preferences.edit();
		editor.putBoolean("flag", true);
		editor.commit();
	}
}
