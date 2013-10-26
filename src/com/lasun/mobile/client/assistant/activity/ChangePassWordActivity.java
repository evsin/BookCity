package com.lasun.mobile.client.assistant.activity;

import java.util.ArrayList;

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
import com.lasun.moblile.assistant.core.CenterController;
import com.lasun.moblile.assistant.core.Parameter;

public class ChangePassWordActivity extends MenuActivity {

    private EditText oldwordedittext;
    private EditText newwordedittext;
    private EditText surewordedittext;
    private Button save;

    private String oldPWD;
    private String newPWD;
    private String surePWD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.changepassword);
        init();
    }

    private void init() {
        initView();
        initListener();
    }

    private void initView() {
        oldwordedittext = (EditText) findViewById(R.id.changepwd_oldpwd);
        newwordedittext = (EditText) findViewById(R.id.changepwd_newpwd);
        surewordedittext = (EditText) findViewById(R.id.changepwd_renewpwd);
        save = (Button) findViewById(R.id.changepwd_save);
    }

    private void initData() {
        oldPWD = (oldwordedittext != null) ? (oldwordedittext.getText().toString()) : (null);
        newPWD = (newwordedittext != null) ? (newwordedittext.getText().toString()) : (null);
        surePWD = (surewordedittext != null) ? (surewordedittext.getText().toString()) : (null);
    }

    private boolean checkInput() {
        if (oldPWD == null || "".equals(oldPWD)) {
            Toast.makeText(ChangePassWordActivity.this, "请输入原密码", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (newPWD == null || "".equals(newPWD)) {
            Toast.makeText(ChangePassWordActivity.this, "请输入新密码", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (surePWD == null || "".equals(surePWD)) {
            Toast.makeText(ChangePassWordActivity.this, "请再次输入新密码", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (newPWD != null && !newPWD.equals(surePWD)) {
            Toast.makeText(ChangePassWordActivity.this, "两次密码输入不一致", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;

    }

    private void initListener() {
        save.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                initData();
                boolean con = checkInput();
                if (con) {
                    new ChangePWD().execute("444");
                }
            }
        });
    }

    class ChangePWD extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            UserService mUS = new UserService();
            String result = mUS.changepwd(mShareCache.getDatefromTemp(LoginHelper.CURRENT_USER_ID), newPWD, oldPWD, new ArrayList<Parameter>(), ChangePassWordActivity.this, new NetExceptionCallBack() {
                @Override
                public void netExceptionCallback(int flag) {
                }
            });
            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            save.setEnabled(true);
            if (result == null && "".equals(result))
                result = "未知错误";
            Toast.makeText(ChangePassWordActivity.this, result, Toast.LENGTH_SHORT).show();
            super.onPostExecute(result);
        }

        @Override
        protected void onPreExecute() {
            save.setEnabled(false);
            super.onPreExecute();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.gc();
    }
}
