package com.lasun.mobile.client.assistant.activity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

import com.lasun.mobile.assistant.utils.VideoUtil;

public class GoodsVideoActivity extends MenuActivity {
    private ImageView video;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.goods_video);
        init();
    }

    /**
     * ��ʼ��һ��
     */
    private void init() {
        initView();
        initData();
        initListener();
    }

    /**
     * ��ʼ���ؼ�
     */
    private void initView() {
        video = (ImageView) findViewById(R.id.goods_video_thumb);
    }

    /**
     * ��ʼ�����
     */
    private void initData() {
    }

    /**
     * ��ʼ������
     */
    private void initListener() {
        video.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                new VideoUtil().playNetVideo("http://daily3gp.com/vids/family_guy_penis_car.3gp", GoodsVideoActivity.this);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.gc();
    }
}
