package com.lasun.mobile.client.assistant.activity;

import android.os.Bundle;

public class MySaleServiceActivity extends MenuActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sale_service);
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
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		System.gc();
	}
}
