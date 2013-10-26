package com.lasun.mobile.client.assistant.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lasun.mobile.assistant.domain.User;
import com.lasun.mobile.assistant.domain.UserB2b;
import com.lasun.mobile.assistant.domain.UserGroup;
import com.lasun.mobile.assistant.domain.UserLoginResponseBody;
import com.lasun.mobile.assistant.domain.VipUser;
import com.lasun.mobile.assistant.net.service.APIService.NetExceptionCallBack;
import com.lasun.mobile.assistant.net.service.UserService;
import com.lasun.mobile.assistant.utils.LoginHelper;

public class SaveAccountActivity extends MenuActivity {
    private Button save;
    private ImageView back;
    private EditText etUserName;
    private EditText etPassword;
    private UserService mService;
    private UserLoginResponseBody currentUser;
    private String userName;
    private String userPassword;
    private CheckBox rememberLogin;
    private CheckBox bindMobile;

    private EditText mB2bUserName;
    private EditText mB2bPassword;
    private Button mB2bSave;
    private EditText mShouhouUserName;
    private EditText mShouhouPassword;
    private Button mShouhouSave;
    private TextView changePWD;

    public static final String CURRENT_USER_ID = "CURRENT_USER_ID";
    public static final String CURRENT_USER_NAME = "CURRENT_USER_NAME";
    public static final String CURRENT_USER_PWD = "CURRENT_USER_PWD";
    public static final String AUTO_LOGIN = "AUTO_LOGIN";

    public static final String CURRENT_USER_NAME_B2B = "CURRENT_USER_NAME_B2B";
    public static final String CURRENT_USER_PWD_B2B = "CURRENT_USER_PWD_B2B";
    public static final String CURRENT_USER_NAME_SHOUHOU = "CURRENT_USER_NAME_SHOUHOU";
    public static final String CURRENT_USER_PWD_SHOUHOU = "CURRENT_USER_PWD_SHOUHOU";

    private VipUser vipUser;
    private UserGroup userGroup;
    public static String vipStr = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account_save);
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
        save = (Button) findViewById(R.id.login_save);
        etUserName = (EditText) findViewById(R.id.login_save__et_un);
        etPassword = (EditText) findViewById(R.id.login_save_et_pwd);
        rememberLogin = (CheckBox) findViewById(R.id.cb_save_login);
        bindMobile = (CheckBox) findViewById(R.id.cb_bind_mobile);
        back = (ImageView) findViewById(R.id.account_back);

        mB2bUserName = (EditText) findViewById(R.id.login_save_et_un_b2b);
        mB2bPassword = (EditText) findViewById(R.id.login_save_et_pwd_b2b);
        mB2bSave = (Button) findViewById(R.id.login_save_b2b);
        mShouhouUserName = (EditText) findViewById(R.id.login_save_et_un_shouhou);
        mShouhouPassword = (EditText) findViewById(R.id.login_save_et_pwd_shouhou);
        mShouhouSave = (Button) findViewById(R.id.login_save_shouhou);
        changePWD = (TextView) findViewById(R.id.change_pwd);

    }

    /**
     * 初始化数据
     */
    private void initData() {
        vipUser = new VipUser();
        userGroup = new UserGroup();
        mService = new UserService();
        String hisUserName = mShareCache.getDatefromStore(CURRENT_USER_NAME);
        String hisUserPWD = mShareCache.getDatefromStore(CURRENT_USER_PWD);
        String autoFlag = mShareCache.getDatefromStore(AUTO_LOGIN);
        String bindstate = mShareCache.getDatefromStore(LoginHelper.CURRENT_USER_BIND);
        if (autoFlag == null || "".equals(autoFlag)) {
            mShareCache.putDatetoStore(AUTO_LOGIN, "true");
            rememberLogin.setChecked(true);
        } else if (autoFlag.equals("true")) {
            rememberLogin.setChecked(true);
        } else {
            rememberLogin.setChecked(false);
        }
        if (bindstate == null || "".equals(bindstate)) {
            bindMobile.setChecked(true);
        } else if ("true".equals(bindstate)) {
            bindMobile.setChecked(true);
        } else {
            bindMobile.setChecked(false);
        }
        if (rememberLogin.isChecked()) {
            if (hisUserName != null && !"".equals(hisUserName)) {
                etUserName.setText(hisUserName);
            }
            if (hisUserPWD != null && !"".equals(hisUserPWD)) {
                etPassword.setText(hisUserPWD);
            }
        }
        String b2bUserName = mShareCache.getDatefromStore(CURRENT_USER_NAME_B2B);
        String b2bUserPWD = mShareCache.getDatefromStore(CURRENT_USER_PWD_B2B);
        if (b2bUserName != null && !"".equals(b2bUserName)) {
            mB2bUserName.setText(b2bUserName);
        }
        if (b2bUserPWD != null && !"".equals(b2bUserPWD)) {
            mB2bPassword.setText(b2bUserPWD);
        }
        String shouhouUserName = mShareCache.getDatefromStore(CURRENT_USER_NAME_SHOUHOU);
        String shouhouUserPWD = mShareCache.getDatefromStore(CURRENT_USER_PWD_SHOUHOU);
        if (shouhouUserName != null && !"".equals(shouhouUserName)) {
            mShouhouUserName.setText(shouhouUserName);
        }
        if (shouhouUserPWD != null && !"".equals(shouhouUserPWD)) {
            mShouhouPassword.setText(shouhouUserPWD);
        }
    }

    /**
     * 初始化监听
     */
    private void initListener() {
        back.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                SaveAccountActivity.this.finish();
            }
        });
        mB2bSave.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                String b2bUserName = mB2bUserName.getText().toString().trim();
                String b2bPWD = mB2bPassword.getText().toString().trim();
                mShareCache.putDatetoStore(CURRENT_USER_NAME_B2B, b2bUserName);
                mShareCache.putDatetoStore(CURRENT_USER_PWD_B2B, b2bPWD);
                if (b2bUserName == null || b2bUserName.equals("")) {
                    mB2bUserName.requestFocus();
                    mB2bUserName.setError("账号不能为空！");
                } else if (b2bPWD == null || b2bPWD.equals("")) {
                    mB2bPassword.requestFocus();
                    mB2bPassword.setError("密码不能为空！");
                }
                User user_b2b = new UserB2b();
                user_b2b.setUsername(b2bUserName);
                userGroup.setUser(1, user_b2b);
                vipUser.setUsergroup(userGroup);
                Gson gson = new Gson();
                vipStr = gson.toJson(vipUser);
                Toast.makeText(SaveAccountActivity.this, "保存成功！", Toast.LENGTH_SHORT).show();
            }
        });
        mShouhouSave.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                String shouhouUserName = mShouhouUserName.getText().toString();
                String shouhouPWD = mShouhouPassword.getText().toString();
                mShareCache.putDatetoStore(CURRENT_USER_NAME_SHOUHOU, shouhouUserName);
                mShareCache.putDatetoStore(CURRENT_USER_PWD_SHOUHOU, shouhouPWD);
                User user_shouhou = new UserB2b();
                user_shouhou.setUsername(shouhouUserName);
                userGroup.setUser(2, user_shouhou);
                vipUser.setUsergroup(userGroup);
                Gson gson = new Gson();
                vipStr = gson.toJson(vipUser);
                Toast.makeText(SaveAccountActivity.this, "保存成功！", Toast.LENGTH_SHORT).show();
            }
        });
        save.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                save.setClickable(false);
                String hisUserName = etUserName.getText().toString();
                String hisPWD = etPassword.getText().toString();
                mShareCache.putDatetoStore(CURRENT_USER_NAME, hisUserName);
                mShareCache.putDatetoStore(CURRENT_USER_PWD, hisPWD);
                if (ValidateLoginInfo()) {
                    DoLoginAsyn loginAsyn = new DoLoginAsyn();
                    loginAsyn.execute();
                }
                vipUser.setUsername(hisUserName);
                vipUser.setPwd(hisPWD);
                Gson gson = new Gson();
                vipStr = gson.toJson(vipUser);
            }
        });
        changePWD.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                SaveAccountActivity.this.startActivity(new Intent(SaveAccountActivity.this, ChangePassWordActivity.class));
                SaveAccountActivity.this.finish();
            }
        });
    }

    private boolean ValidateLoginInfo() {
        userName = etUserName.getText().toString().trim();
        userPassword = etPassword.getText().toString().trim();
        if (userName == null || userName.equals("")) {
            save.setClickable(true);
            etUserName.requestFocus();
            etUserName.setError("账号不能为空！");
            return false;
        } else if (userPassword == null || userPassword.equals("")) {
            save.setClickable(true);
            etPassword.requestFocus();
            etPassword.setError("密码不能为空！");
            return false;
        }
        return true;
    }

    private void insertCacheForLogin(UserLoginResponseBody mLogin) {
        mShareCache.putDatetoStore(CURRENT_USER_ID, mLogin.getUserId());
        mShareCache.putDatetoStore(CURRENT_USER_NAME, mLogin.getUserName());
        mShareCache.putDatetoStore(CURRENT_USER_PWD, userPassword);
        mShareCache.putDatetoStore(LoginHelper.CITYCODE, mLogin.getCityCode());
        if (rememberLogin.isChecked()) {
            mShareCache.putDatetoStore(CURRENT_USER_ID, mLogin.getUserId());
        }
        if (bindMobile.isChecked()) {
            mShareCache.putDatetoStore(LoginHelper.CURRENT_USER_BIND, "true");
        } else {
            mShareCache.putDatetoStore(LoginHelper.CURRENT_USER_BIND, "false");
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
            showProgress(SaveAccountActivity.this);
        }

        @Override
        protected UserLoginResponseBody doInBackground(Void... params) {

            currentUser = mService.userLogin(userName, userPassword, getParameters(), SaveAccountActivity.this, new NetExceptionCallBack() {

                @Override
                public void netExceptionCallback(int flag) {

                }
            });
            return currentUser;
        }

        @Override
        protected void onPostExecute(UserLoginResponseBody result) {
            save.setClickable(true);
            dismissProgress();
            if (result != null) {
                if (result.getCode() != null || result.getMsg() != null) {
                    Toast.makeText(SaveAccountActivity.this, "账号或密码有误！", Toast.LENGTH_LONG).show();
                    return;
                }
                if (result.getReturnVal() != null && !"".equals(result.getReturnVal())) {
                    if ("true".equals(result.getReturnVal())) {
                        insertCacheForLogin(result);
                        Toast.makeText(SaveAccountActivity.this, "保存成功！", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(SaveAccountActivity.this, "账号或密码有误！", Toast.LENGTH_LONG).show();
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
