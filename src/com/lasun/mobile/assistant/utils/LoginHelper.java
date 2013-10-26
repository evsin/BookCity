package com.lasun.mobile.assistant.utils;

import java.util.List;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.lasun.mobile.assistant.domain.AgencyBrand;
import com.lasun.mobile.assistant.domain.UserLoginResponseBody;
import com.lasun.mobile.assistant.net.service.APIService.NetExceptionCallBack;
import com.lasun.mobile.assistant.net.service.UserService;
import com.lasun.mobile.client.assistant.activity.LoginActivity;
import com.lasun.mobile.client.assistant.activity.MenuActivity;
import com.lasun.mobile.client.assistant.activity.R;
import com.lasun.moblile.assistant.core.CenterController;
import com.lasun.moblile.assistant.push.PollingSpreadService;

public class LoginHelper {
	private Button login;
	private EditText etUserName;
	private EditText etPassword;
	private UserService mService;
	private UserLoginResponseBody currentUser;
	private String userName;
	private String userPassword;
	private View loginLayout;
	private MenuActivity mAct;
	private Handler mHandler;
	private int fromType;
	public static final String CURRENT_USER_ID = "CURRENT_USER_ID";
	public static final String CURRENT_USER_NAME = "CURRENT_USER_NAME";
	public static final String CURRENT_USER_PWD = "CURRENT_USER_PWD";
	public static final String CURRENT_USER_BIND = "CURRENT_USER_BIND";
	public static final String AUTO_LOGIN = "AUTO_LOGIN";
	public static final String CURRENT_USER_NAME_B2B = "CURRENT_USER_NAME_B2B";
	public static final String CURRENT_USER_PWD_B2B = "CURRENT_USER_PWD_B2B";
	public static final String CURRENT_USER_NAME_SHOUHOU = "CURRENT_USER_NAME_SHOUHOU";
	public static final String CURRENT_USER_PWD_SHOUHOU = "CURRENT_USER_PWD_SHOUHOU";
	public static final String CITYCODE = "CITYCODE";
	public static final String ISFIRSTREGISTER = "ISFIRSTREGISTER";
	public static int FROM_VIP = 0;
	public static int FROM_MSG = 2;
	public LoginHelper() {
	}
	public LoginHelper(View loginLayout, MenuActivity mAct, Handler mHandler, int fromType, EditText etUN, EditText etPWD) {
		this.loginLayout = loginLayout;
		this.mAct = mAct;
		this.mHandler = mHandler;
		this.fromType = fromType;
		etUserName = etUN;
		etPassword = etPWD;
		init();
	}
	/**
	 * 初始化一切
	 */
	private void init() {
		initView();
		initData();
		initListener();
	}
	/**
	 * 初始化控件
	 */
	private void initView() {
		login = (Button) loginLayout.findViewById(R.id.login_btn);
	}
	/**
	 * 初始化数据
	 */
	private void initData() {
		mService = new UserService();
		String hisUserName = mAct.mShareCache.getDatefromStore(CURRENT_USER_NAME);
		String hisPWD = mAct.mShareCache.getDatefromStore(CURRENT_USER_PWD);
		if (etUserName != null && etPassword != null) {
			etUserName.setText(hisUserName);
			etPassword.setText(hisPWD);
		}
	}
	/**
	 * 初始化监听
	 */
	private void initListener() {
		login.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				login.setClickable(false);
				if (ValidateLoginInfo()) {
					DoLoginAsyn loginAsyn = new DoLoginAsyn();
					loginAsyn.execute();
				}
			}
		});
	}
	/**
	 * 账户信息非空校验
	 * 
	 * @return
	 */
	private boolean ValidateLoginInfo() {
		userName = etUserName.getText().toString().trim();
		userPassword = etPassword.getText().toString().trim();
		if (userName == null || userName.equals("")) {
			login.setClickable(true);
			etUserName.requestFocus();
			etUserName.setError("账号不能为空！");
			return false;
		} else if (userPassword == null || userPassword.equals("")) {
			login.setClickable(true);
			etPassword.requestFocus();
			etPassword.setError("密码不能为空！");
			return false;
		}
		return true;
	}
	/**
	 * 登录成功将登录信息放入临时缓存
	 * 
	 * @param mLogin
	 */
	private void insertCacheForLogin(UserLoginResponseBody mLogin) {
		mAct.mShareCache.putDatetoTemp(CURRENT_USER_ID, mLogin.getUserId());
		mAct.mShareCache.putDatetoTemp(CURRENT_USER_NAME, mLogin.getUserName());
		mAct.mShareCache.putDatetoStore(CITYCODE, mLogin.getCityCode());
		mAct.mShareCache.putDatetoTemp(CITYCODE, mLogin.getCityCode());
	}
	private void loadUserBrands(final String userId) {
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				List<AgencyBrand> brandData = mService.getUserBrandList(userId, mAct.getParameters(), mAct, new NetExceptionCallBack() {
					@Override
					public void netExceptionCallback(int flag) {
					}
				});
				mAct.mMapCache.put("userBrands", brandData);
				mHandler.sendEmptyMessage(5);
			}
		});
		thread.start();
	}

	/**
	 * 登录异步任务
	 * 
	 * @author Administrator
	 * 
	 */
	private class DoLoginAsyn extends AsyncTask<Void, Void, UserLoginResponseBody> {
		@Override
		protected void onPreExecute() {
			mAct.showProgress(mAct);
		}
		@Override
		protected UserLoginResponseBody doInBackground(Void... params) {
			currentUser = mService.userLogin(userName, userPassword, mAct.getParameters(), mAct, new NetExceptionCallBack() {
				@Override
				public void netExceptionCallback(int flag) {
				}
			});
			return currentUser;
		}
		@Override
		protected void onPostExecute(UserLoginResponseBody result) {
			login.setClickable(true);
			mAct.dismissProgress();
			if (result != null) {
				if (result.getCode() != null || result.getMsg() != null) {
					Toast.makeText(mAct, "账号或密码有误！", Toast.LENGTH_LONG).show();
					return;
				}
				if (result.getReturnVal() != null && !"".equals(result.getReturnVal())) {
					if ("true".equals(result.getReturnVal())) {
						insertCacheForLogin(result);
						Toast.makeText(mAct, "登录成功！", Toast.LENGTH_LONG).show();
						loadUserBrands(result.getUserId());
						PushServiceUtil.openPushService(mAct, PollingSpreadService.EXECUTE_MODE_APP);
						if (fromType == FROM_VIP) {
							Message msg = Message.obtain();
							msg.what = FROM_VIP;
							mHandler.sendMessage(msg);
						} else if (fromType == FROM_MSG) {
							Message msg = Message.obtain();
							msg.what = FROM_MSG;
							mHandler.sendMessage(msg);
						}
					} else {
						Toast.makeText(mAct, "账号或密码有误！", Toast.LENGTH_LONG).show();
					}
				}
			}
		}
	}
	/**
	 * 验证用户是否已经登录
	 */
	public static boolean checkLogin(final Context activity) {
		CenterController centerController = CenterController.getCenterController(activity);
		String currentUserId = centerController.getShareCache().getDatefromTemp(LoginActivity.CURRENT_USER_ID);
		String currentUserName = centerController.getShareCache().getDatefromTemp(LoginActivity.CURRENT_USER_NAME);
		if (currentUserId == null || "".equals(currentUserId) || currentUserName == null || "".equals(currentUserName)) {
			return false;
		} else {
			return true;
		}
	}
	/**
	 * 登录验证
	 */
	public static void ValidateLogin(String useName, String pwd, Context mContext) {
	}
}
