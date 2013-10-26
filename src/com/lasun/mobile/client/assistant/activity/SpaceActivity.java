package com.lasun.mobile.client.assistant.activity;

import java.util.List;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lasun.mobile.assistant.domain.AgencyGoods;
import com.lasun.mobile.assistant.net.service.APIService;
import com.lasun.mobile.assistant.net.service.APIService.NetExceptionCallBack;
import com.lasun.mobile.assistant.net.service.UserService;
import com.lasun.mobile.assistant.utils.LoginHelper;
import com.lasun.mobile.assistant.utils.VideoUtil;

public class SpaceActivity extends MenuActivity {
    private ListView goodsLv;
    private LayoutInflater inflater;
    private EditText etSearch;
    private RelativeLayout searchBtn;
    private RelativeLayout filterBtn;
    private List<AgencyGoods> data;
    private UserService mUserService;
    private String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.space);
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

    @Override
    protected void onStart() {
        super.onStart();
        bigshoot(2);
        to();
    }

    /**
     * 初始化控件
     */
    private void initView() {
        goodsLv = (ListView) findViewById(R.id.phone_lv_goods);
        etSearch = (EditText) findViewById(R.id.et_search);
        filterBtn = (RelativeLayout) findViewById(R.id.phone_filter_btn);
        searchBtn = (RelativeLayout) findViewById(R.id.phone_search_btn);
    }

    /**
     * 初始化数据
     */
    private void initData() {
        inflater = LayoutInflater.from(this);
        userId = centerController.getShareCache().getDatefromTemp(LoginActivity.CURRENT_USER_ID);
        mUserService = new UserService();
        GoodsListAsyn asyn = new GoodsListAsyn();
        asyn.execute();
    }

    /**
     * 初始化监听
     */
    private void initListener() {
        searchBtn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                etSearch.setVisibility(View.VISIBLE);
                searchBtn.setBackgroundResource(R.drawable.search_btn_press);
            }
        });
        filterBtn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(SpaceActivity.this, GoodsFilterActivity.class);
                startActivity(intent);
            }
        });
    }

    /**
     * 加载经销商列表异步任务
     * 
     * @author Administrator
     * 
     */
    private class GoodsListAsyn extends AsyncTask<Void, Void, List<AgencyGoods>> {
        @Override
        protected void onPreExecute() {
            showProgress(SpaceActivity.this);
        }

        @Override
        protected List<AgencyGoods> doInBackground(Void... arg0) {
            data = mUserService.getUserGoodsList("900001", mShareCache.getDatefromStore(LoginHelper.CITYCODE), getParameters(), SpaceActivity.this, new NetExceptionCallBack() {

                @Override
                public void netExceptionCallback(int flag) {
                    switch (flag) {
                    case APIService.RETRY:
                        SpaceActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                            }
                        });
                        initData();
                        break;

                    default:
                        break;
                    }
                }
            });
            return data;
        }

    }

    class ViewHolder {
        ImageView goodsThumb;
        TextView goodsName;
        TextView promoteTitle;
        TextView marketPrice;
        TextView giftInfo;
        ImageView newIcon;
        ImageView video;
        ImageView purchase;
        TextView message;
        RelativeLayout inventoryLayout;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.gc();
    }
}
