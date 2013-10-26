package com.lasun.mobile.client.assistant.activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import com.lasun.mobile.assistant.domain.UserLoginResponseBody;
import com.lasun.mobile.assistant.net.service.APIService.NetExceptionCallBack;
import com.lasun.mobile.assistant.net.service.UserService;
import com.lasun.mobile.assistant.utils.LoginHelper;
import com.lasun.mobile.assistant.utils.PushServiceUtil;
import com.lasun.moblile.assistant.push.PollingSpreadService;

public class LoginActivity extends MenuActivity {
	private Button login;
	private EditText etUserName;
	private EditText etPassword;
	private UserService mService;
	private UserLoginResponseBody currentUser;
	private String userName;
	private String userPassword;
	private CheckBox rememberLogin;
	public static final String CURRENT_USER_ID = "CURRENT_USER_ID";
	public static final String CURRENT_USER_NAME = "CURRENT_USER_NAME";
	public static final String AUTO_LOGIN = "AUTO_LOGIN";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		init();
	}
	private void init() {
		initView();
		initData();
		initListener();
	}
	private void initView() {
		login = (Button) findViewById(R.id.login_btn);
		etUserName = (EditText) findViewById(R.id.login_et_un);
		etPassword = (EditText) findViewById(R.id.login_et_pwd);
	}
	private void initData() {
		mService = new UserService();
		String hisUserName = mShareCache.getDatefromStore(CURRENT_USER_NAME);
		String autoFlag = mShareCache.getDatefromStore(AUTO_LOGIN);
		if (autoFlag == null || "".equals(autoFlag)) {
			mShareCache.putDatetoStore(AUTO_LOGIN, "true");
			rememberLogin.setChecked(true);
		} else if (autoFlag.equals("true")) {
			rememberLogin.setChecked(true);
		} else {
			rememberLogin.setChecked(false);
		}
		if (hisUserName != null && !"".equals(hisUserName)) {
			etUserName.setText(hisUserName);
		}
	}
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
	private void insertCacheForLogin(UserLoginResponseBody mLogin) {
		mShareCache.putDatetoTemp(CURRENT_USER_ID, mLogin.getUserId());
		mShareCache.putDatetoTemp(CURRENT_USER_NAME, mLogin.getUserName());
		mShareCache.putDatetoStore(LoginHelper.CITYCODE, mLogin.getCityCode());
		if (rememberLogin.isChecked()) {
			mShareCache.putDatetoStore(CURRENT_USER_ID, mLogin.getUserId());
		}
		mShareCache.putDatetoStore(CURRENT_USER_NAME, mLogin.getUserName());
		if (rememberLogin.isChecked()) {
			mShareCache.putDatetoStore(AUTO_LOGIN, "true");
		} else {
			mShareCache.putDatetoStore(AUTO_LOGIN, "false");
		}
	}

	private class DoLoginAsyn extends AsyncTask<Void, Void, UserLoginResponseBody> {
		@Override
		protected void onPreExecute() {
			showProgress(LoginActivity.this);
		}
		@Override
		protected UserLoginResponseBody doInBackground(Void... params) {
			currentUser = mService.userLogin(userName, userPassword, getParameters(), LoginActivity.this, new NetExceptionCallBack() {
				@Override
				public void netExceptionCallback(int flag) {
				}
			});
			return currentUser;
		}
		@Override
		protected void onPostExecute(UserLoginResponseBody result) {
			login.setClickable(true);
			dismissProgress();
			if (result != null) {
				if (result.getCode() != null || result.getMsg() != null) {
					Toast.makeText(LoginActivity.this, "账号或密码有误！", Toast.LENGTH_LONG).show();
					return;
				}
				if (result.getReturnVal() != null && !"".equals(result.getReturnVal())) {
					if ("true".equals(result.getReturnVal())) {
						insertCacheForLogin(result);
						Toast.makeText(LoginActivity.this, "登录成功！", Toast.LENGTH_LONG).show();
						PushServiceUtil.openPushServiceForStart(LoginActivity.this, PollingSpreadService.EXECUTE_MODE_APP);
						LoginActivity.this.finish();
					} else {
						Toast.makeText(LoginActivity.this, "账号或密码有误！", Toast.LENGTH_LONG).show();
					}
				}
			}
		}
	}
	@Override
	protected void onDestroy() {
		super.onDestroy();
		System.gc();
	}
}
