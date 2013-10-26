package com.lasun.mobile.client.assistant.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import com.lasun.mobile.assistant.utils.ImageFactory;
import com.lasun.mobile.assistant.utils.ImageFactory.MethodforSetImageBitmap;
import com.lasun.mobile.assistant.utils.LoginHelper;
import com.lasun.moblile.assistant.core.CenterController;
import com.lasun.moblile.assistant.core.Parameter;
import com.lasun.moblile.assistant.core.ShareCache;

public class MenuActivity extends Activity {
	private RelativeLayout layout_phone;
	private RelativeLayout layout_space;
	private RelativeLayout layout_message;
	private ImageView layout_phone_icon;
	private ImageView layout_space_icon;
	private ImageView layout_message_icon;
	private TextView layout_phone_text;
	private TextView layout_space_text;
	private TextView layout_message_text;
	public static String CASE_FLAG = "1";
	private LayoutInflater inflater;
	private RelativeLayout mProgress;
	private RelativeLayout noData;
	public static ImageFactory mFactory;
	public CenterController centerController;
	public ShareCache mShareCache;
	public HashMap<String, Object> mMapCache;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menu_down);
		init();
	}
	@Override
	protected void onStart() {
		super.onStart();
		menuFun();
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
		layout_phone = (RelativeLayout) findViewById(R.id.layout_phone);
		layout_space = (RelativeLayout) findViewById(R.id.layout_space);
		layout_message = (RelativeLayout) findViewById(R.id.layout_message);
		layout_phone_icon = (ImageView) findViewById(R.id.menu_phone_icon);
		layout_space_icon = (ImageView) findViewById(R.id.menu_space_icon);
		layout_message_icon = (ImageView) findViewById(R.id.menu_message_icon);
		layout_phone_text = (TextView) findViewById(R.id.menu_phone_text);
		layout_space_text = (TextView) findViewById(R.id.menu_space_text);
		layout_message_text = (TextView) findViewById(R.id.menu_message_text);
	}
	/**
	 * 初始化数据
	 */
	private void initData() {
		inflater = LayoutInflater.from(this);
		centerController = CenterController.getCenterController(this);
		mShareCache = centerController.getShareCache();
		mMapCache = centerController.getMapCache();
		mFactory = ImageFactory.getImageFactory(centerController, this);
	}
	/**
	 * 初始化监听
	 */
	private void initListener() {
	}
	public void getImageBitmap(ImageView iv) {
		final String url = (String) iv.getTag();
		mFactory.downloadBitMap(url, iv, new MethodforSetImageBitmap() {
			@Override
			public void setImageBitmap(Bitmap bm, View view) {
				ImageView tiv = (ImageView) view;
				tiv.setImageBitmap(bm);
			}
			@Override
			public void setDefaultImageBitmap(View view) {
				ImageView tiv = (ImageView) view;
				tiv.setImageResource(R.drawable.no_resourse);
			}
		});
	}
	public void getImageBitmap(ImageView iv, Bitmap defaultbitmap) {
		final String url = (String) iv.getTag();
		final Bitmap ddm = defaultbitmap;
		mFactory.downloadBitMap(url, iv, new MethodforSetImageBitmap() {
			@Override
			public void setImageBitmap(Bitmap bm, View view) {
				ImageView tiv = (ImageView) view;
				tiv.setImageBitmap(bm);
			}
			@Override
			public void setDefaultImageBitmap(View view) {
				ImageView tiv = (ImageView) view;
				tiv.setImageBitmap(ddm);
			}
		});
	}
	public void getImageBitmap(ImageView iv, final String url) {
		iv.setTag(url);
		this.getImageBitmap(iv);
	}
	public void getImageBitmap(ImageView iv, final String url, Bitmap defaultbitmap) {
		iv.setTag(url);
		this.getImageBitmap(iv, defaultbitmap);
	}
	public void bigshoot(int shot) {
		switch (shot) {
		case 1:
			layout_phone_icon.setBackgroundResource(R.drawable.menu_phone_btn2);
			layout_phone_text.setTextColor(getResources().getColor(R.color.menu_down_color_select));
			layout_space_icon.setBackgroundResource(R.drawable.menu_space_btn);
			layout_space_text.setTextColor(getResources().getColor(R.color.menu_down_color_normal));
			layout_message_icon.setBackgroundResource(R.drawable.menu_message_btn);
			layout_message_text.setTextColor(getResources().getColor(R.color.menu_down_color_normal));
			break;
		case 2:
			layout_space_icon.setBackgroundResource(R.drawable.menu_space_btn2);
			layout_space_text.setTextColor(getResources().getColor(R.color.menu_down_color_select));
			layout_phone_icon.setBackgroundResource(R.drawable.menu_phone_btn);
			layout_phone_text.setTextColor(getResources().getColor(R.color.menu_down_color_normal));
			layout_message_icon.setBackgroundResource(R.drawable.menu_message_btn);
			layout_message_text.setTextColor(getResources().getColor(R.color.menu_down_color_normal));
			break;
		case 3:
			layout_message_icon.setBackgroundResource(R.drawable.menu_message_btn2);
			layout_message_text.setTextColor(getResources().getColor(R.color.menu_down_color_select));
			layout_space_icon.setBackgroundResource(R.drawable.menu_space_btn);
			layout_space_text.setTextColor(getResources().getColor(R.color.menu_down_color_normal));
			layout_phone_icon.setBackgroundResource(R.drawable.menu_phone_btn);
			layout_phone_text.setTextColor(getResources().getColor(R.color.menu_down_color_normal));
			break;
		}
	}
	/**
	 *
	 * 
	 */
	public void to() {
		layout_phone.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				if (!CASE_FLAG.equals("1")) {
					Intent intent = new Intent(MenuActivity.this, PhoneActivity.class);
					startActivity(intent);
					CASE_FLAG = "1";
				}
			}
		});
		layout_space.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (!CASE_FLAG.equals("2")) {
					if (LoginHelper.checkLogin(MenuActivity.this)) {
						CASE_FLAG = "2";
						Intent mintent = new Intent(MenuActivity.this, SpaceActivity.class);
						startActivity(mintent);
					}
				}
			}
		});
		layout_message.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (!CASE_FLAG.equals("3")) {
					Intent Dintent = new Intent(MenuActivity.this, MessageActivity.class);
					startActivity(Dintent);
					CASE_FLAG = "3";
				}
			}
		});
	}
	/**
	 * 
	 * 
	 */
	public void menuFun() {
		init();
	}
	public List<Parameter> getParameters() {
		return new ArrayList<Parameter>();
	}
	public void showNoData(final Activity mActivity) {
		mActivity.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				if (noData == null) {
					noData = (RelativeLayout) inflater.inflate(R.layout.no_data, null);
					LayoutParams params = new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);
					mActivity.addContentView(noData, params);
				} else {
					noData.setVisibility(View.VISIBLE);
				}
			}
		});
	}
	public void dismissNoData() {
		if (noData != null) {
			if (noData.getVisibility() == View.VISIBLE) {
				noData.post(new Runnable() {
					@Override
					public void run() {
						noData.setVisibility(View.GONE);
					}
				});
			}
		}
	}
	public void showProgress(final Activity mActivity) {
		mActivity.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				mProgress = (RelativeLayout) inflater.inflate(R.layout.progress, null);
				LayoutParams params = new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);
				mActivity.addContentView(mProgress, params);
			}
		});
	}
	public void dismissProgress() {
		if (mProgress != null) {
			mProgress.post(new Runnable() {
				@Override
				public void run() {
					mProgress.removeAllViews();
					mProgress.setVisibility(View.GONE);
				}
			});
		}
	}
	public void exit() {
		Intent exitMain = new Intent(this, PhoneActivity.class);
		exitMain.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		exitMain.putExtra("exit", true);
		mShareCache.clearDatetoTemp();
		startActivity(exitMain);
	}
	@Override
	protected void onDestroy() {
		super.onDestroy();
		System.gc();
	}
}
