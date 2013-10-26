package com.lasun.mobile.client.assistant.activity;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.view.inputmethod.InputMethodManager;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.EditText;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.SlidingDrawer;
import android.widget.TextView;
import android.widget.Toast;
import com.lansun.mobile.assistant.constant.MyConstant;
import com.lansun.mobile.client.adapter.HotListAdapter;
import com.lansun.mobile.client.adapter.MenuListAdapter;
import com.lansun.mobile.client.adapter.MenuListAdapter.Mattth;
import com.lansun.mobile.client.adapter.MsgListAdapter;
import com.lansun.mobile.client.adapter.MsgListAdapter.Refresh;
import com.lansun.mobile.client.adapter.VipListAdapter;
import com.lasun.mobile.assistant.domain.Advertisement;
import com.lasun.mobile.assistant.domain.AgencyBrand;
import com.lasun.mobile.assistant.domain.AgencyGoods;
import com.lasun.mobile.assistant.domain.LocalMessage;
import com.lasun.mobile.assistant.domain.SerachGoods;
import com.lasun.mobile.assistant.domain.ShopAd;
import com.lasun.mobile.assistant.domain.ShopAdsGroup;
import com.lasun.mobile.assistant.domain.ShopGoodsGroup;
import com.lasun.mobile.assistant.net.service.APIService;
import com.lasun.mobile.assistant.net.service.APIService.NetExceptionCallBack;
import com.lasun.mobile.assistant.net.service.AdvertisementService;
import com.lasun.mobile.assistant.net.service.SerachGoodsService;
import com.lasun.mobile.assistant.net.service.ShopService;
import com.lasun.mobile.assistant.net.service.UserService;
import com.lasun.mobile.assistant.utils.DateUtils;
import com.lasun.mobile.assistant.utils.LoginHelper;
import com.lasun.moblile.assistant.core.CenterController;
import com.lasun.moblile.assistant.core.Parameter;
import com.lasun.moblile.assistant.push.Message;
import com.lasun.moblile.assistant.push.MySqlite;
import com.lasun.moblile.assistant.push.PollingSpreadService;
import com.lasun.moblile.assistant.push.SharedPreferencesHelper;

@SuppressLint("HandlerLeak")
public class MainActivity2 extends MenuActivity {
	public static final String SHARE_CACHE_KEY_1 = "SHARE_CACHE_KEY_1";
	public static final String SHARE_CACHE_KEY_2 = "SHARE_CACHE_KEY_2";
	public static final String SHARE_CACHE_KEY_3 = "SHARE_CACHE_KEY_3";
	public static final String SHARE_CACHE_KEY_4 = "SHARE_CACHE_KEY_4";
	public static final String SHARE_CACHE_KEY_VIP_2 = "SHARE_CACHE_KEY_VIP_2";
	public static final String SHARE_CACHE_KEY_VIP_3 = "SHARE_CACHE_KEY_VIP_3";
	public static final String SHARE_CACHE_KEY_VIP_4 = "SHARE_CACHE_KEY_VIP_4";
	private TextView mTabVip;
	private TextView mTabHot;
	private TextView mTabMsg;
	private EditText etUserName;
	private EditText etUserPWD;
	private LinearLayout mBlock;
	private LinearLayout mBlock2;
	private View mBlockChild;
	public View nowBottomMenu;
	private View mBottomMenu;
	private View mBottomMenu1;
	private View mBottomMenu2;
	private LinearLayout mSearchMenu;
	private RelativeLayout mListFooter;
	private String userId;
	private View mSearch;
	private View mSearch2;
	private EditText mSearchEdit;
	private ViewPager mViewPager;
	private LayoutInflater mInflater;
	DisplayMetrics outMetrics;
	private int mTabWidth;
	private UserService mUserService;
	private ShopService mShopService;
	private SerachGoodsService mSearchService;
	private static String DEFAULT_GROUP_ID = "1000";
	private static String NOW_KEWWORD;
	private static String NOW_MSG_CONDITION;
	private static long NOW_MSG_TIME_CONDITION = 0;
	private static String STOCK_STATE = "-1";
	private static String STOCK_STATE_LOW = "1";
	private static String STOCK_STATE_SUIT = "2";
	private static String STOCK_STATE_HIGH = "3";
	private static String NOW_VIP_STOCK_FLAG;
	private static String NOW_BRAND_ID;
	VipViewHolder mVipHolder;
	HotViewHolder mHotHolder;
	MsgViewHolder mMsgHolder;
	VipListAdapter mVipListAdapter;
	HotListAdapter mHotListAdapter;
	MsgListAdapter mMsgListAdapter;
	private LinearLayout mMenuLayout;
	private RelativeLayout mMainLayout;
	public static boolean isMenuHide = true;
	private ImageView mBtnMenu;
	private ListView mMenuListView;
	MenuListAdapter menuAdapter;
	private TextView filter_recommendation;
	private TextView filter_native_hot;
	private TextView filter_new;
	private TextView filter_up;
	private TextView filter_state_hot;
	private TextView all_21;
	private TextView un_intelligent;
	private TextView intelligent;
	private TextView all_31;
	private TextView net_2g;
	private TextView net_3g;
	private TextView all_41;
	private TextView samsung;
	private TextView htc;
	private TextView apple;
	private TextView lenove;
	private TextView zte;
	private TextView huawei;
	private TextView hisense;
	private TextView coopad;
	private TextView screen;
	private TextView screen_VIP;
	private TextView cancle_Hot;
	private TextView cancle_VIP;
	private TextView promotion;
	private TextView notification;
	private TextView newPro;
	private TextView app;
	private TextView weekAgo;
	private TextView monthAgo;
	private TextView msgDefault;
	private TextView stockAll;
	private TextView stockHigh;
	private TextView stockSuit;
	private TextView stockLow;
	private TextView vipSmartAll;
	private TextView vipSmart_un;
	private TextView vipSmart;
	private TextView vip_net_All;
	private TextView vip_net_2G;
	private TextView vip_net_3G;
	private TextView vipBrand1;
	private TextView vipBrand2;
	private TextView vipBrand3;
	private TextView vipBrand4;
	private TextView vipBrand5;
	private TextView vipBrand6;
	private TextView vipBrand7;
	private TextView vipBrand8;
	private TextView vipBrand9;
	private List<TextView> vipBrands;
	private Handler mHandler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			updateSelecteButton();
			updateHotSelected();
			switch (msg.what) {
			case 0:
				loadView();
				onVipSelected();
				mBlock2.setVisibility(View.VISIBLE);
				break;
			case 2:
				loadView();
				onMsgSelected();
				mViewPager.setCurrentItem(2);
				mBlock2.setVisibility(View.VISIBLE);
				break;
			case 5:
				break;
			default:
				break;
			}
		};
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.phone_new2);
		outMetrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(outMetrics);
		init();
		mShopService = new ShopService();
		mUserService = new UserService();
		mSearchService = new SerachGoodsService();
		menuAdapter = new MenuListAdapter(MainActivity2.this, mViewPager);
		menuAdapter.setMathod(new Mattth() {
			@Override
			public void math() {
				quicklybackHide();
			}
		});
		loadView();
		setListener();
		mViewPager.setCurrentItem(1);
	}
	@Override
	protected void onStart() {
		super.onStart();
		String notifyFlag = getIntent().getStringExtra("notify");
		if (getIntent().getBooleanExtra("exit", false)) {
			this.finish();
		}
		if (notifyFlag != null && !"".equals(notifyFlag)) {
			if (notifyFlag.equals("notify")) {
				mShareCache.putDatetoStore(PollingSpreadService.MESSAGENUMBER, null);
				onMsgSelected();
				mViewPager.setCurrentItem(2);
			}
		}
		updateSelecteButton();
	}
	private void updateSelecteButton() {
		if (LoginHelper.checkLogin(this)) {
			filter_native_hot.setText("本地热销");
			filter_state_hot.setVisibility(View.VISIBLE);
		} else {
			filter_native_hot.setText("全省热销");
			filter_state_hot.setVisibility(View.GONE);
		}
	}
	private void updateHotSelected() {
		if (LoginHelper.checkLogin(this)) {
			mHotListAdapter.setHotData(null);
		}
	}
	private void init() {
		mTabVip = (TextView) findViewById(R.id.tab_vip);
		mTabHot = (TextView) findViewById(R.id.tab_hot);
		mTabMsg = (TextView) findViewById(R.id.tab_msg);
		mBlock = (LinearLayout) findViewById(R.id.block);
		mBlock2 = (LinearLayout) findViewById(R.id.block2);
		mBlockChild = findViewById(R.id.block_child);
		mBottomMenu = findViewById(R.id.tab_bottom_menu);
		mBottomMenu1 = findViewById(R.id.tab_bottom_menu1);
		mBottomMenu2 = findViewById(R.id.tab_bottom_menu2);
		nowBottomMenu = mBottomMenu;
		mSearchMenu = (LinearLayout) findViewById(R.id.search_and_menu);
		mSearch = findViewById(R.id.btn_search);
		mSearch2 = findViewById(R.id.btn_search2);
		mSearchEdit = (EditText) findViewById(R.id.hot_search_edittext);
		mViewPager = (ViewPager) findViewById(R.id.phone_viewPager);
		mMenuLayout = (LinearLayout) findViewById(R.id.menu_layout);
		mMainLayout = (RelativeLayout) findViewById(R.id.main_layout);
		mMenuListView = (ListView) findViewById(R.id.menu_listview);
		mBtnMenu = (ImageView) findViewById(R.id.btn_menu);
		mListFooter = (RelativeLayout) LayoutInflater.from(this).inflate(R.layout.list_foot_loading, null);
		int width = (int) (outMetrics.density * 80);
		mTabWidth = (outMetrics.widthPixels - width) / 3;
		mBlockChild.setLayoutParams(new LinearLayout.LayoutParams(mTabWidth, (int) (2 * outMetrics.density)));
		mBlock2.setPadding(mTabWidth + mTabWidth / 2 - (int) (outMetrics.density * 30 + 30) / 2, 0, 0, 0);
		filter_recommendation = (TextView) findViewById(R.id.hot_bottom_menu_2);
		filter_native_hot = (TextView) findViewById(R.id.hot_bottom_menu_3);
		filter_state_hot = (TextView) findViewById(R.id.hot_bottom_menu_6);
		filter_new = (TextView) findViewById(R.id.hot_bottom_menu_4);
		filter_up = (TextView) findViewById(R.id.hot_bottom_menu_5);
		userId = centerController.getShareCache().getDatefromTemp(LoginHelper.CURRENT_USER_ID);
		all_21 = (TextView) findViewById(R.id.hot_bottom_menu_22);
		un_intelligent = (TextView) findViewById(R.id.hot_bottom_menu_23);
		intelligent = (TextView) findViewById(R.id.hot_bottom_menu_24);
		all_31 = (TextView) findViewById(R.id.hot_bottom_menu_32);
		net_2g = (TextView) findViewById(R.id.hot_bottom_menu_33);
		net_3g = (TextView) findViewById(R.id.hot_bottom_menu_34);
		all_41 = (TextView) findViewById(R.id.hot_bottom_menu_41);
		samsung = (TextView) findViewById(R.id.hot_bottom_menu_42);
		htc = (TextView) findViewById(R.id.hot_bottom_menu_43);
		apple = (TextView) findViewById(R.id.hot_bottom_menu_44);
		lenove = (TextView) findViewById(R.id.hot_bottom_menu_45);
		zte = (TextView) findViewById(R.id.hot_bottom_menu_46);
		huawei = (TextView) findViewById(R.id.hot_bottom_menu_47);
		hisense = (TextView) findViewById(R.id.hot_bottom_menu_48);
		screen = (TextView) findViewById(R.id.hot_screen);
		cancle_Hot = (TextView) findViewById(R.id.hot_cancle);
		promotion = (TextView) findViewById(R.id.hot_bottom_menu_a2);
		notification = (TextView) findViewById(R.id.hot_bottom_menu_a3);
		app = (TextView) findViewById(R.id.hot_bottom_menu_a4);
		newPro = (TextView) findViewById(R.id.hot_bottom_menu_a5);
		weekAgo = (TextView) findViewById(R.id.hot_bottom_menu_a42);
		monthAgo = (TextView) findViewById(R.id.hot_bottom_menu_a43);
		msgDefault = (TextView) findViewById(R.id.msg_default);
		stockAll = (TextView) findViewById(R.id.hot_bottom_menu_s12);
		stockLow = (TextView) findViewById(R.id.hot_bottom_menu_s2);
		stockSuit = (TextView) findViewById(R.id.hot_bottom_menu_s3);
		stockHigh = (TextView) findViewById(R.id.hot_bottom_menu_s4);
		vipSmartAll = (TextView) findViewById(R.id.hot_bottom_menu_s22);
		vipSmart_un = (TextView) findViewById(R.id.hot_bottom_menu_s23);
		vipSmart = (TextView) findViewById(R.id.hot_bottom_menu_s24);
		vip_net_All = (TextView) findViewById(R.id.hot_bottom_menu_s32);
		vip_net_2G = (TextView) findViewById(R.id.hot_bottom_menu_s33);
		vip_net_3G = (TextView) findViewById(R.id.hot_bottom_menu_s34);
		screen_VIP = (TextView) findViewById(R.id.vip_screen);
		cancle_VIP = (TextView) findViewById(R.id.vip_cancle);
		vipBrand1 = (TextView) findViewById(R.id.hot_bottom_menu_s42);
		vipBrand2 = (TextView) findViewById(R.id.hot_bottom_menu_s43);
		vipBrand3 = (TextView) findViewById(R.id.hot_bottom_menu_s44);
		vipBrand4 = (TextView) findViewById(R.id.hot_bottom_menu_s45);
		vipBrand5 = (TextView) findViewById(R.id.hot_bottom_menu_s46);
		vipBrand6 = (TextView) findViewById(R.id.hot_bottom_menu_s47);
		vipBrand7 = (TextView) findViewById(R.id.hot_bottom_menu_s48);
		vipBrand8 = (TextView) findViewById(R.id.hot_bottom_menu_s49);
		vipBrand9 = (TextView) findViewById(R.id.hot_bottom_menu_s50);
		vipBrands = new ArrayList<TextView>();
		vipBrands.add(vipBrand1);
		vipBrands.add(vipBrand2);
		vipBrands.add(vipBrand3);
		vipBrands.add(vipBrand4);
		vipBrands.add(vipBrand5);
		vipBrands.add(vipBrand6);
		vipBrands.add(vipBrand7);
		vipBrands.add(vipBrand8);
		vipBrands.add(vipBrand9);
	}
	private void loadView() {
		List<View> list = new ArrayList<View>();
		mInflater = LayoutInflater.from(this);
		list.add(initVipView());
		list.add(initHotView());
		list.add(initMsgView());
		mViewPager.setAdapter(new MyPagerApapter(list));
	}
	private void setListener() {
		mViewPager.setOnPageChangeListener(new MyOnPageChangeListener());
		MyOnClickListener myOnClickListener = new MyOnClickListener();
		mTabVip.setOnClickListener(myOnClickListener);
		mTabHot.setOnClickListener(myOnClickListener);
		mTabMsg.setOnClickListener(myOnClickListener);
		mSearch.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				mSearch2.setVisibility(View.VISIBLE);
				mSearchEdit.setVisibility(View.VISIBLE);
				mSearchEdit.requestFocus();
				InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
				imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
			}
		});
		mSearch2.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
				imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
				String key = mSearchEdit.getText().toString().trim();
				if (key == null || key.equals("")) {
					mSearch2.setVisibility(View.GONE);
					mSearchEdit.setVisibility(View.GONE);
				} else {
					mSearch2.setVisibility(View.GONE);
					mSearchEdit.setVisibility(View.GONE);
					NOW_KEWWORD = key;
					GoodsSearchListAsyn asyn = new GoodsSearchListAsyn();
					asyn.execute();
				}
			}
		});
		mBlock2.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				if (nowBottomMenu.getVisibility() == View.GONE) {
					nowBottomMenu.setVisibility(View.VISIBLE);
				} else {
					nowBottomMenu.setVisibility(View.GONE);
				}
			}
		});
		mBtnMenu.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				dismissNoData();
				backHide();
			}
		});
		filter_recommendation.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				DEFAULT_GROUP_ID = MyConstant.GROUP_ONE;
				mShareCache.putDatetoStore(SHARE_CACHE_KEY_1, DEFAULT_GROUP_ID);
				filter_native_hot.setBackgroundColor(getResources().getColor(R.color.gray_lower));
				filter_state_hot.setBackgroundColor(getResources().getColor(R.color.gray_lower));
				filter_new.setBackgroundColor(getResources().getColor(R.color.gray_lower));
				filter_up.setBackgroundColor(getResources().getColor(R.color.gray_lower));
				filter_recommendation.setBackgroundColor(getResources().getColor(R.color.red));
			}
		});
		filter_native_hot.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				TextView tv = (TextView) v;
				if (LoginHelper.checkLogin(MainActivity2.this))
					DEFAULT_GROUP_ID = MyConstant.GROUP_TWO;
				else
					DEFAULT_GROUP_ID = MyConstant.GROUP_TWO_TWO;
				mShareCache.putDatetoStore(SHARE_CACHE_KEY_1, DEFAULT_GROUP_ID);
				filter_native_hot.setBackgroundColor(getResources().getColor(R.color.red));
				filter_new.setBackgroundColor(getResources().getColor(R.color.gray_lower));
				filter_up.setBackgroundColor(getResources().getColor(R.color.gray_lower));
				filter_recommendation.setBackgroundColor(getResources().getColor(R.color.gray_lower));
				filter_state_hot.setBackgroundColor(getResources().getColor(R.color.gray_lower));
			}
		});
		filter_state_hot.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				TextView tv = (TextView) v;
				DEFAULT_GROUP_ID = MyConstant.GROUP_TWO_TWO;
				mShareCache.putDatetoStore(SHARE_CACHE_KEY_1, DEFAULT_GROUP_ID);
				filter_state_hot.setBackgroundColor(getResources().getColor(R.color.red));
				filter_native_hot.setBackgroundColor(getResources().getColor(R.color.gray_lower));
				filter_new.setBackgroundColor(getResources().getColor(R.color.gray_lower));
				filter_up.setBackgroundColor(getResources().getColor(R.color.gray_lower));
				filter_recommendation.setBackgroundColor(getResources().getColor(R.color.gray_lower));
			}
		});
		filter_new.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				DEFAULT_GROUP_ID = MyConstant.GROUP_THREE;
				mShareCache.putDatetoStore(SHARE_CACHE_KEY_1, DEFAULT_GROUP_ID);
				filter_native_hot.setBackgroundColor(getResources().getColor(R.color.gray_lower));
				filter_state_hot.setBackgroundColor(getResources().getColor(R.color.gray_lower));
				filter_new.setBackgroundColor(getResources().getColor(R.color.red));
				filter_up.setBackgroundColor(getResources().getColor(R.color.gray_lower));
				filter_recommendation.setBackgroundColor(getResources().getColor(R.color.gray_lower));
			}
		});
		filter_up.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				DEFAULT_GROUP_ID = MyConstant.GROUP_FOUR;
				mShareCache.putDatetoStore(SHARE_CACHE_KEY_1, DEFAULT_GROUP_ID);
				filter_native_hot.setBackgroundColor(getResources().getColor(R.color.gray_lower));
				filter_state_hot.setBackgroundColor(getResources().getColor(R.color.gray_lower));
				filter_new.setBackgroundColor(getResources().getColor(R.color.gray_lower));
				filter_up.setBackgroundColor(getResources().getColor(R.color.red));
				filter_recommendation.setBackgroundColor(getResources().getColor(R.color.gray_lower));
			}
		});
		all_21.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				mShareCache.putDatetoStore(SHARE_CACHE_KEY_2, "-1");
				all_21.setBackgroundColor(getResources().getColor(R.color.red));
				un_intelligent.setBackgroundColor(getResources().getColor(R.color.gray_lower));
				intelligent.setBackgroundColor(getResources().getColor(R.color.gray_lower));
			}
		});
		un_intelligent.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				mShareCache.putDatetoStore(SHARE_CACHE_KEY_2, "0");
				all_21.setBackgroundColor(getResources().getColor(R.color.gray_lower));
				un_intelligent.setBackgroundColor(getResources().getColor(R.color.red));
				intelligent.setBackgroundColor(getResources().getColor(R.color.gray_lower));
			}
		});
		intelligent.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				mShareCache.putDatetoStore(SHARE_CACHE_KEY_2, "1");
				all_21.setBackgroundColor(getResources().getColor(R.color.gray_lower));
				un_intelligent.setBackgroundColor(getResources().getColor(R.color.gray_lower));
				intelligent.setBackgroundColor(getResources().getColor(R.color.red));
			}
		});
		all_31.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				mShareCache.putDatetoStore(SHARE_CACHE_KEY_3, "-1");
				all_31.setBackgroundColor(getResources().getColor(R.color.red));
				net_2g.setBackgroundColor(getResources().getColor(R.color.gray_lower));
				net_3g.setBackgroundColor(getResources().getColor(R.color.gray_lower));
			}
		});
		net_2g.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				mShareCache.putDatetoStore(SHARE_CACHE_KEY_3, "0");
				all_31.setBackgroundColor(getResources().getColor(R.color.gray_lower));
				net_2g.setBackgroundColor(getResources().getColor(R.color.red));
				net_3g.setBackgroundColor(getResources().getColor(R.color.gray_lower));
			}
		});
		net_3g.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				mShareCache.putDatetoStore(SHARE_CACHE_KEY_3, "1");
				all_31.setBackgroundColor(getResources().getColor(R.color.gray_lower));
				net_2g.setBackgroundColor(getResources().getColor(R.color.gray_lower));
				net_3g.setBackgroundColor(getResources().getColor(R.color.red));
			}
		});
		all_41.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				NOW_KEWWORD = "";
				mShareCache.putDatetoStore(SHARE_CACHE_KEY_4, NOW_KEWWORD);
				all_41.setBackgroundColor(getResources().getColor(R.color.red));
				samsung.setBackgroundColor(getResources().getColor(R.color.gray_lower));
				htc.setBackgroundColor(getResources().getColor(R.color.gray_lower));
				apple.setBackgroundColor(getResources().getColor(R.color.gray_lower));
				lenove.setBackgroundColor(getResources().getColor(R.color.gray_lower));
				zte.setBackgroundColor(getResources().getColor(R.color.gray_lower));
				huawei.setBackgroundColor(getResources().getColor(R.color.gray_lower));
				hisense.setBackgroundColor(getResources().getColor(R.color.gray_lower));
			}
		});
		samsung.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				NOW_KEWWORD = samsung.getText().toString();
				mShareCache.putDatetoStore(SHARE_CACHE_KEY_4, NOW_KEWWORD);
				all_41.setBackgroundColor(getResources().getColor(R.color.gray_lower));
				samsung.setBackgroundColor(getResources().getColor(R.color.red));
				htc.setBackgroundColor(getResources().getColor(R.color.gray_lower));
				apple.setBackgroundColor(getResources().getColor(R.color.gray_lower));
				lenove.setBackgroundColor(getResources().getColor(R.color.gray_lower));
				zte.setBackgroundColor(getResources().getColor(R.color.gray_lower));
				huawei.setBackgroundColor(getResources().getColor(R.color.gray_lower));
				hisense.setBackgroundColor(getResources().getColor(R.color.gray_lower));
			}
		});
		htc.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				NOW_KEWWORD = htc.getText().toString();
				mShareCache.putDatetoStore(SHARE_CACHE_KEY_4, NOW_KEWWORD);
				all_41.setBackgroundColor(getResources().getColor(R.color.gray_lower));
				samsung.setBackgroundColor(getResources().getColor(R.color.gray_lower));
				htc.setBackgroundColor(getResources().getColor(R.color.red));
				apple.setBackgroundColor(getResources().getColor(R.color.gray_lower));
				lenove.setBackgroundColor(getResources().getColor(R.color.gray_lower));
				zte.setBackgroundColor(getResources().getColor(R.color.gray_lower));
				huawei.setBackgroundColor(getResources().getColor(R.color.gray_lower));
				hisense.setBackgroundColor(getResources().getColor(R.color.gray_lower));
			}
		});
		apple.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				NOW_KEWWORD = apple.getText().toString();
				mShareCache.putDatetoStore(SHARE_CACHE_KEY_4, NOW_KEWWORD);
				all_41.setBackgroundColor(getResources().getColor(R.color.gray_lower));
				samsung.setBackgroundColor(getResources().getColor(R.color.gray_lower));
				htc.setBackgroundColor(getResources().getColor(R.color.gray_lower));
				apple.setBackgroundColor(getResources().getColor(R.color.red));
				lenove.setBackgroundColor(getResources().getColor(R.color.gray_lower));
				zte.setBackgroundColor(getResources().getColor(R.color.gray_lower));
				huawei.setBackgroundColor(getResources().getColor(R.color.gray_lower));
				hisense.setBackgroundColor(getResources().getColor(R.color.gray_lower));
			}
		});
		lenove.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				NOW_KEWWORD = lenove.getText().toString();
				mShareCache.putDatetoStore(SHARE_CACHE_KEY_4, NOW_KEWWORD);
				all_41.setBackgroundColor(getResources().getColor(R.color.gray_lower));
				samsung.setBackgroundColor(getResources().getColor(R.color.gray_lower));
				htc.setBackgroundColor(getResources().getColor(R.color.gray_lower));
				apple.setBackgroundColor(getResources().getColor(R.color.gray_lower));
				lenove.setBackgroundColor(getResources().getColor(R.color.red));
				zte.setBackgroundColor(getResources().getColor(R.color.gray_lower));
				huawei.setBackgroundColor(getResources().getColor(R.color.gray_lower));
				hisense.setBackgroundColor(getResources().getColor(R.color.gray_lower));
			}
		});
		zte.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				NOW_KEWWORD = zte.getText().toString();
				mShareCache.putDatetoStore(SHARE_CACHE_KEY_4, NOW_KEWWORD);
				all_41.setBackgroundColor(getResources().getColor(R.color.gray_lower));
				samsung.setBackgroundColor(getResources().getColor(R.color.gray_lower));
				htc.setBackgroundColor(getResources().getColor(R.color.gray_lower));
				apple.setBackgroundColor(getResources().getColor(R.color.gray_lower));
				lenove.setBackgroundColor(getResources().getColor(R.color.gray_lower));
				zte.setBackgroundColor(getResources().getColor(R.color.red));
				huawei.setBackgroundColor(getResources().getColor(R.color.gray_lower));
				hisense.setBackgroundColor(getResources().getColor(R.color.gray_lower));
			}
		});
		huawei.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				NOW_KEWWORD = huawei.getText().toString();
				mShareCache.putDatetoStore(SHARE_CACHE_KEY_4, NOW_KEWWORD);
				all_41.setBackgroundColor(getResources().getColor(R.color.gray_lower));
				samsung.setBackgroundColor(getResources().getColor(R.color.gray_lower));
				htc.setBackgroundColor(getResources().getColor(R.color.gray_lower));
				apple.setBackgroundColor(getResources().getColor(R.color.gray_lower));
				lenove.setBackgroundColor(getResources().getColor(R.color.gray_lower));
				zte.setBackgroundColor(getResources().getColor(R.color.gray_lower));
				huawei.setBackgroundColor(getResources().getColor(R.color.red));
				hisense.setBackgroundColor(getResources().getColor(R.color.gray_lower));
			}
		});
		hisense.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				NOW_KEWWORD = hisense.getText().toString();
				mShareCache.putDatetoStore(SHARE_CACHE_KEY_4, NOW_KEWWORD);
				all_41.setBackgroundColor(getResources().getColor(R.color.gray_lower));
				samsung.setBackgroundColor(getResources().getColor(R.color.gray_lower));
				htc.setBackgroundColor(getResources().getColor(R.color.gray_lower));
				apple.setBackgroundColor(getResources().getColor(R.color.gray_lower));
				lenove.setBackgroundColor(getResources().getColor(R.color.gray_lower));
				zte.setBackgroundColor(getResources().getColor(R.color.gray_lower));
				huawei.setBackgroundColor(getResources().getColor(R.color.gray_lower));
				hisense.setBackgroundColor(getResources().getColor(R.color.red));
			}
		});
		screen.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				String str = mShareCache.getDatefromStore(SHARE_CACHE_KEY_1);
				String str1 = mShareCache.getDatefromStore(SHARE_CACHE_KEY_2);
				String str2 = mShareCache.getDatefromStore(SHARE_CACHE_KEY_3);
				String str3 = mShareCache.getDatefromStore(SHARE_CACHE_KEY_4);
				if (str == null || "".equals(str)) {
					str = MyConstant.GROUP_ONE;
					mShareCache.putDatetoStore(SHARE_CACHE_KEY_1, str);
				}
				if (str1 == null || "".equals(str1)) {
					str1 = "-1";
					mShareCache.putDatetoStore(SHARE_CACHE_KEY_2, str1);
				}
				if (str2 == null || "".equals(str2)) {
					str2 = "-1";
					mShareCache.putDatetoStore(SHARE_CACHE_KEY_3, str2);
				}
				if (str3 == null || str3.equals("")) {
					str3 = "";
					mShareCache.putDatetoStore(SHARE_CACHE_KEY_4, str3);
				}
				new GetGoodsListAsy().execute();
				nowBottomMenu.setVisibility(View.GONE);
			}
		});
		cancle_Hot.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				if (LoginHelper.checkLogin(MainActivity2.this))
					DEFAULT_GROUP_ID = MyConstant.GROUP_TWO;
				else
					DEFAULT_GROUP_ID = MyConstant.GROUP_TWO_TWO;
				mShareCache.putDatetoStore(SHARE_CACHE_KEY_1, DEFAULT_GROUP_ID);
				mShareCache.putDatetoStore(SHARE_CACHE_KEY_2, "");
				mShareCache.putDatetoStore(SHARE_CACHE_KEY_3, "");
				mShareCache.putDatetoStore(SHARE_CACHE_KEY_4, "");
				cancleHot();
				nowBottomMenu.setVisibility(View.GONE);
				dismissNoData();
				new GetGoodsListAsy().execute();
			}
		});
		promotion.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				NOW_MSG_CONDITION = promotion.getText().toString();
				promotion.setBackgroundColor(getResources().getColor(R.color.red));
				notification.setBackgroundColor(getResources().getColor(R.color.gray_lower));
				newPro.setBackgroundColor(getResources().getColor(R.color.gray_lower));
				app.setBackgroundColor(getResources().getColor(R.color.gray_lower));
				weekAgo.setBackgroundColor(getResources().getColor(R.color.gray_lower));
				monthAgo.setBackgroundColor(getResources().getColor(R.color.gray_lower));
				onMsgSelected2();
				nowBottomMenu.setVisibility(View.GONE);
			}
		});
		notification.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				NOW_MSG_CONDITION = notification.getText().toString();
				promotion.setBackgroundColor(getResources().getColor(R.color.gray_lower));
				notification.setBackgroundColor(getResources().getColor(R.color.red));
				newPro.setBackgroundColor(getResources().getColor(R.color.gray_lower));
				app.setBackgroundColor(getResources().getColor(R.color.gray_lower));
				weekAgo.setBackgroundColor(getResources().getColor(R.color.gray_lower));
				monthAgo.setBackgroundColor(getResources().getColor(R.color.gray_lower));
				onMsgSelected2();
				nowBottomMenu.setVisibility(View.GONE);
			}
		});
		newPro.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				NOW_MSG_CONDITION = newPro.getText().toString();
				promotion.setBackgroundColor(getResources().getColor(R.color.gray_lower));
				notification.setBackgroundColor(getResources().getColor(R.color.gray_lower));
				newPro.setBackgroundColor(getResources().getColor(R.color.red));
				app.setBackgroundColor(getResources().getColor(R.color.gray_lower));
				weekAgo.setBackgroundColor(getResources().getColor(R.color.gray_lower));
				monthAgo.setBackgroundColor(getResources().getColor(R.color.gray_lower));
				onMsgSelected2();
				nowBottomMenu.setVisibility(View.GONE);
			}
		});
		app.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				NOW_MSG_CONDITION = app.getText().toString();
				promotion.setBackgroundColor(getResources().getColor(R.color.gray_lower));
				notification.setBackgroundColor(getResources().getColor(R.color.gray_lower));
				newPro.setBackgroundColor(getResources().getColor(R.color.gray_lower));
				app.setBackgroundColor(getResources().getColor(R.color.red));
				weekAgo.setBackgroundColor(getResources().getColor(R.color.gray_lower));
				monthAgo.setBackgroundColor(getResources().getColor(R.color.gray_lower));
				onMsgSelected2();
				nowBottomMenu.setVisibility(View.GONE);
			}
		});
		weekAgo.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				NOW_MSG_TIME_CONDITION = DateUtils.dateToLong(DateUtils.lastWeek());
				promotion.setBackgroundColor(getResources().getColor(R.color.gray_lower));
				notification.setBackgroundColor(getResources().getColor(R.color.gray_lower));
				newPro.setBackgroundColor(getResources().getColor(R.color.gray_lower));
				app.setBackgroundColor(getResources().getColor(R.color.gray_lower));
				weekAgo.setBackgroundColor(getResources().getColor(R.color.red));
				monthAgo.setBackgroundColor(getResources().getColor(R.color.gray_lower));
				onMsgSelected3();
				nowBottomMenu.setVisibility(View.GONE);
			}
		});
		monthAgo.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				NOW_MSG_TIME_CONDITION = DateUtils.dateToLong(DateUtils.lastMonth());
				promotion.setBackgroundColor(getResources().getColor(R.color.gray_lower));
				notification.setBackgroundColor(getResources().getColor(R.color.gray_lower));
				newPro.setBackgroundColor(getResources().getColor(R.color.gray_lower));
				app.setBackgroundColor(getResources().getColor(R.color.gray_lower));
				weekAgo.setBackgroundColor(getResources().getColor(R.color.gray_lower));
				monthAgo.setBackgroundColor(getResources().getColor(R.color.red));
				onMsgSelected3();
				nowBottomMenu.setVisibility(View.GONE);
			}
		});
		msgDefault.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				NOW_MSG_TIME_CONDITION = DateUtils.dateToLong(DateUtils.lastMonth());
				promotion.setBackgroundColor(getResources().getColor(R.color.gray_lower));
				notification.setBackgroundColor(getResources().getColor(R.color.gray_lower));
				newPro.setBackgroundColor(getResources().getColor(R.color.gray_lower));
				app.setBackgroundColor(getResources().getColor(R.color.gray_lower));
				weekAgo.setBackgroundColor(getResources().getColor(R.color.gray_lower));
				monthAgo.setBackgroundColor(getResources().getColor(R.color.gray_lower));
				onMsgSelected("sss");
				nowBottomMenu.setVisibility(View.GONE);
			}
		});
		stockAll.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				NOW_VIP_STOCK_FLAG = STOCK_STATE;
				stockAll.setBackgroundColor(getResources().getColor(R.color.red));
				stockHigh.setBackgroundColor(getResources().getColor(R.color.gray_lower));
				stockSuit.setBackgroundColor(getResources().getColor(R.color.gray_lower));
				stockLow.setBackgroundColor(getResources().getColor(R.color.gray_lower));
			}
		});
		stockHigh.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				NOW_VIP_STOCK_FLAG = STOCK_STATE_HIGH;
				stockAll.setBackgroundColor(getResources().getColor(R.color.gray_lower));
				stockHigh.setBackgroundColor(getResources().getColor(R.color.red));
				stockSuit.setBackgroundColor(getResources().getColor(R.color.gray_lower));
				stockLow.setBackgroundColor(getResources().getColor(R.color.gray_lower));
			}
		});
		stockSuit.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				NOW_VIP_STOCK_FLAG = STOCK_STATE_SUIT;
				stockAll.setBackgroundColor(getResources().getColor(R.color.gray_lower));
				stockHigh.setBackgroundColor(getResources().getColor(R.color.gray_lower));
				stockSuit.setBackgroundColor(getResources().getColor(R.color.red));
				stockLow.setBackgroundColor(getResources().getColor(R.color.gray_lower));
			}
		});
		stockLow.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				NOW_VIP_STOCK_FLAG = STOCK_STATE_LOW;
				stockAll.setBackgroundColor(getResources().getColor(R.color.gray_lower));
				stockHigh.setBackgroundColor(getResources().getColor(R.color.gray_lower));
				stockSuit.setBackgroundColor(getResources().getColor(R.color.gray_lower));
				stockLow.setBackgroundColor(getResources().getColor(R.color.red));
			}
		});
		vipSmartAll.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				mShareCache.putDatetoStore(SHARE_CACHE_KEY_VIP_2, "-1");
				vipSmartAll.setBackgroundColor(getResources().getColor(R.color.red));
				vipSmart_un.setBackgroundColor(getResources().getColor(R.color.gray_lower));
				vipSmart.setBackgroundColor(getResources().getColor(R.color.gray_lower));
			}
		});
		vipSmart_un.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				mShareCache.putDatetoStore(SHARE_CACHE_KEY_VIP_2, "0");
				vipSmartAll.setBackgroundColor(getResources().getColor(R.color.gray_lower));
				vipSmart_un.setBackgroundColor(getResources().getColor(R.color.red));
				vipSmart.setBackgroundColor(getResources().getColor(R.color.gray_lower));
			}
		});
		vipSmart.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				mShareCache.putDatetoStore(SHARE_CACHE_KEY_VIP_2, "1");
				vipSmartAll.setBackgroundColor(getResources().getColor(R.color.gray_lower));
				vipSmart_un.setBackgroundColor(getResources().getColor(R.color.gray_lower));
				vipSmart.setBackgroundColor(getResources().getColor(R.color.red));
			}
		});
		vip_net_All.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				mShareCache.putDatetoStore(SHARE_CACHE_KEY_VIP_3, "-1");
				vip_net_All.setBackgroundColor(getResources().getColor(R.color.red));
				vip_net_2G.setBackgroundColor(getResources().getColor(R.color.gray_lower));
				vip_net_3G.setBackgroundColor(getResources().getColor(R.color.gray_lower));
			}
		});
		vip_net_2G.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				mShareCache.putDatetoStore(SHARE_CACHE_KEY_VIP_3, "0");
				vip_net_All.setBackgroundColor(getResources().getColor(R.color.gray_lower));
				vip_net_2G.setBackgroundColor(getResources().getColor(R.color.red));
				vip_net_3G.setBackgroundColor(getResources().getColor(R.color.gray_lower));
			}
		});
		vip_net_3G.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				mShareCache.putDatetoStore(SHARE_CACHE_KEY_VIP_3, "1");
				vip_net_All.setBackgroundColor(getResources().getColor(R.color.gray_lower));
				vip_net_2G.setBackgroundColor(getResources().getColor(R.color.gray_lower));
				vip_net_3G.setBackgroundColor(getResources().getColor(R.color.red));
			}
		});
		vipBrand1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				mShareCache.putDatetoStore(SHARE_CACHE_KEY_VIP_4, vipBrand1.getText().toString());
				vipBrand1.setBackgroundColor(getResources().getColor(R.color.red));
				vipBrand2.setBackgroundColor(getResources().getColor(R.color.gray_lower));
				vipBrand3.setBackgroundColor(getResources().getColor(R.color.gray_lower));
				vipBrand4.setBackgroundColor(getResources().getColor(R.color.gray_lower));
				vipBrand5.setBackgroundColor(getResources().getColor(R.color.gray_lower));
				vipBrand6.setBackgroundColor(getResources().getColor(R.color.gray_lower));
				vipBrand7.setBackgroundColor(getResources().getColor(R.color.gray_lower));
				vipBrand8.setBackgroundColor(getResources().getColor(R.color.gray_lower));
			}
		});
		vipBrand2.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				mShareCache.putDatetoStore(SHARE_CACHE_KEY_VIP_4, vipBrand2.getText().toString());
				vipBrand1.setBackgroundColor(getResources().getColor(R.color.gray_lower));
				vipBrand2.setBackgroundColor(getResources().getColor(R.color.red));
				vipBrand3.setBackgroundColor(getResources().getColor(R.color.gray_lower));
				vipBrand4.setBackgroundColor(getResources().getColor(R.color.gray_lower));
				vipBrand5.setBackgroundColor(getResources().getColor(R.color.gray_lower));
				vipBrand6.setBackgroundColor(getResources().getColor(R.color.gray_lower));
				vipBrand7.setBackgroundColor(getResources().getColor(R.color.gray_lower));
				vipBrand8.setBackgroundColor(getResources().getColor(R.color.gray_lower));
			}
		});
		vipBrand3.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				mShareCache.putDatetoStore(SHARE_CACHE_KEY_VIP_4, vipBrand2.getText().toString());
				vipBrand1.setBackgroundColor(getResources().getColor(R.color.gray_lower));
				vipBrand2.setBackgroundColor(getResources().getColor(R.color.gray_lower));
				vipBrand3.setBackgroundColor(getResources().getColor(R.color.red));
				vipBrand4.setBackgroundColor(getResources().getColor(R.color.gray_lower));
				vipBrand5.setBackgroundColor(getResources().getColor(R.color.gray_lower));
				vipBrand6.setBackgroundColor(getResources().getColor(R.color.gray_lower));
				vipBrand7.setBackgroundColor(getResources().getColor(R.color.gray_lower));
				vipBrand8.setBackgroundColor(getResources().getColor(R.color.gray_lower));
			}
		});
		vipBrand4.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				mShareCache.putDatetoStore(SHARE_CACHE_KEY_VIP_4, vipBrand2.getText().toString());
				vipBrand1.setBackgroundColor(getResources().getColor(R.color.gray_lower));
				vipBrand2.setBackgroundColor(getResources().getColor(R.color.gray_lower));
				vipBrand3.setBackgroundColor(getResources().getColor(R.color.gray_lower));
				vipBrand4.setBackgroundColor(getResources().getColor(R.color.red));
				vipBrand5.setBackgroundColor(getResources().getColor(R.color.gray_lower));
				vipBrand6.setBackgroundColor(getResources().getColor(R.color.gray_lower));
				vipBrand7.setBackgroundColor(getResources().getColor(R.color.gray_lower));
				vipBrand8.setBackgroundColor(getResources().getColor(R.color.gray_lower));
			}
		});
		vipBrand5.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				mShareCache.putDatetoStore(SHARE_CACHE_KEY_VIP_4, vipBrand2.getText().toString());
				vipBrand1.setBackgroundColor(getResources().getColor(R.color.gray_lower));
				vipBrand2.setBackgroundColor(getResources().getColor(R.color.gray_lower));
				vipBrand3.setBackgroundColor(getResources().getColor(R.color.gray_lower));
				vipBrand4.setBackgroundColor(getResources().getColor(R.color.gray_lower));
				vipBrand5.setBackgroundColor(getResources().getColor(R.color.red));
				vipBrand6.setBackgroundColor(getResources().getColor(R.color.gray_lower));
				vipBrand7.setBackgroundColor(getResources().getColor(R.color.gray_lower));
				vipBrand8.setBackgroundColor(getResources().getColor(R.color.gray_lower));
			}
		});
		vipBrand6.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				mShareCache.putDatetoStore(SHARE_CACHE_KEY_VIP_4, vipBrand2.getText().toString());
				vipBrand1.setBackgroundColor(getResources().getColor(R.color.gray_lower));
				vipBrand2.setBackgroundColor(getResources().getColor(R.color.gray_lower));
				vipBrand3.setBackgroundColor(getResources().getColor(R.color.gray_lower));
				vipBrand4.setBackgroundColor(getResources().getColor(R.color.gray_lower));
				vipBrand5.setBackgroundColor(getResources().getColor(R.color.gray_lower));
				vipBrand6.setBackgroundColor(getResources().getColor(R.color.red));
				vipBrand7.setBackgroundColor(getResources().getColor(R.color.gray_lower));
				vipBrand8.setBackgroundColor(getResources().getColor(R.color.gray_lower));
			}
		});
		vipBrand7.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				mShareCache.putDatetoStore(SHARE_CACHE_KEY_VIP_4, vipBrand2.getText().toString());
				vipBrand1.setBackgroundColor(getResources().getColor(R.color.gray_lower));
				vipBrand2.setBackgroundColor(getResources().getColor(R.color.gray_lower));
				vipBrand3.setBackgroundColor(getResources().getColor(R.color.gray_lower));
				vipBrand4.setBackgroundColor(getResources().getColor(R.color.gray_lower));
				vipBrand5.setBackgroundColor(getResources().getColor(R.color.gray_lower));
				vipBrand6.setBackgroundColor(getResources().getColor(R.color.gray_lower));
				vipBrand7.setBackgroundColor(getResources().getColor(R.color.red));
				vipBrand8.setBackgroundColor(getResources().getColor(R.color.gray_lower));
			}
		});
		vipBrand8.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				mShareCache.putDatetoStore(SHARE_CACHE_KEY_VIP_4, vipBrand8.getText().toString());
				vipBrand1.setBackgroundColor(getResources().getColor(R.color.gray_lower));
				vipBrand2.setBackgroundColor(getResources().getColor(R.color.gray_lower));
				vipBrand3.setBackgroundColor(getResources().getColor(R.color.gray_lower));
				vipBrand4.setBackgroundColor(getResources().getColor(R.color.gray_lower));
				vipBrand5.setBackgroundColor(getResources().getColor(R.color.gray_lower));
				vipBrand6.setBackgroundColor(getResources().getColor(R.color.gray_lower));
				vipBrand7.setBackgroundColor(getResources().getColor(R.color.gray_lower));
				vipBrand8.setBackgroundColor(getResources().getColor(R.color.red));
			}
		});
		screen_VIP.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				String str1 = mShareCache.getDatefromStore(SHARE_CACHE_KEY_VIP_2);
				String str2 = mShareCache.getDatefromStore(SHARE_CACHE_KEY_VIP_3);
				String str3 = mShareCache.getDatefromStore(SHARE_CACHE_KEY_VIP_4);
				if (NOW_VIP_STOCK_FLAG == null || "".equals(NOW_VIP_STOCK_FLAG)) {
					NOW_VIP_STOCK_FLAG = STOCK_STATE;
				}
				if (str1 == null || "".equals(str1)) {
					str1 = "-1";
					mShareCache.putDatetoStore(SHARE_CACHE_KEY_VIP_2, str1);
				}
				if (str2 == null || "".equals(str2)) {
					str2 = "-1";
					mShareCache.putDatetoStore(SHARE_CACHE_KEY_VIP_3, str2);
				}
				if (str3 == null || str3.equals("")) {
					str3 = "";
					mShareCache.putDatetoStore(SHARE_CACHE_KEY_VIP_4, str3);
				}
				new GetVIPGoodsListAsy().execute();
				nowBottomMenu.setVisibility(View.GONE);
			}
		});
		cancle_VIP.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				cancleVIP();
				NOW_VIP_STOCK_FLAG = "";
				mShareCache.putDatetoStore(SHARE_CACHE_KEY_VIP_2, "");
				mShareCache.putDatetoStore(SHARE_CACHE_KEY_VIP_3, "");
				mShareCache.putDatetoStore(SHARE_CACHE_KEY_VIP_4, "");
				dismissNoData();
				new GoodsListAsyn_vip().execute(new String[] { "all" });
				nowBottomMenu.setVisibility(View.GONE);
			}
		});
		if (LoginHelper.checkLogin(this)) {
			mVipHolder.listView.setOnScrollListener(new OnScrollListener() {
				@Override
				public void onScrollStateChanged(AbsListView view, int scrollState) {
					nowBottomMenu.setVisibility(View.GONE);
				}
				@Override
				public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
				}
			});
		}
		mMsgHolder.listview.setOnScrollListener(new OnScrollListener() {
			@Override
			public void onScrollStateChanged(AbsListView arg0, int arg1) {
				nowBottomMenu.setVisibility(View.GONE);
			}
			@Override
			public void onScroll(AbsListView arg0, int arg1, int arg2, int arg3) {
			}
		});
		mHotHolder.listView.setOnScrollListener(new OnScrollListener() {
			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {
				nowBottomMenu.setVisibility(View.GONE);
			}
			@Override
			public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
			}
		});
		mMenuListView.setAdapter(menuAdapter);
	}
	private void cancleVIP() {
		stockAll.setBackgroundColor(getResources().getColor(R.color.gray_lower));
		stockHigh.setBackgroundColor(getResources().getColor(R.color.gray_lower));
		stockSuit.setBackgroundColor(getResources().getColor(R.color.gray_lower));
		stockLow.setBackgroundColor(getResources().getColor(R.color.gray_lower));
		vipSmartAll.setBackgroundColor(getResources().getColor(R.color.gray_lower));
		vipSmart_un.setBackgroundColor(getResources().getColor(R.color.gray_lower));
		vipSmart.setBackgroundColor(getResources().getColor(R.color.gray_lower));
		vip_net_All.setBackgroundColor(getResources().getColor(R.color.gray_lower));
		vip_net_2G.setBackgroundColor(getResources().getColor(R.color.gray_lower));
		vip_net_3G.setBackgroundColor(getResources().getColor(R.color.gray_lower));
		vipBrand1.setBackgroundColor(getResources().getColor(R.color.gray_lower));
		vipBrand2.setBackgroundColor(getResources().getColor(R.color.gray_lower));
		vipBrand3.setBackgroundColor(getResources().getColor(R.color.gray_lower));
		vipBrand4.setBackgroundColor(getResources().getColor(R.color.gray_lower));
		vipBrand5.setBackgroundColor(getResources().getColor(R.color.gray_lower));
		vipBrand6.setBackgroundColor(getResources().getColor(R.color.gray_lower));
		vipBrand7.setBackgroundColor(getResources().getColor(R.color.gray_lower));
		vipBrand8.setBackgroundColor(getResources().getColor(R.color.gray_lower));
	}
	private View initVipView() {
		if (LoginHelper.checkLogin(this)) {
			View vipLayout = mInflater.inflate(R.layout.vip_listview_new, null);
			mVipHolder = new VipViewHolder();
			mVipHolder.listView = (ListView) vipLayout.findViewById(R.id.vip_listview_new);
			mVipListAdapter = new VipListAdapter(this);
			mVipHolder.listView.setAdapter(mVipListAdapter);
			return vipLayout;
		} else {
			View loginLayout = mInflater.inflate(R.layout.login, null);
			etUserName = (EditText) loginLayout.findViewById(R.id.login_et_un);
			etUserPWD = (EditText) loginLayout.findViewById(R.id.login_et_pwd);
			new LoginHelper(loginLayout, this, mHandler, LoginHelper.FROM_VIP, etUserName, etUserPWD);
			return loginLayout;
		}
	}

	/**
	 * VIP中用到的控件
	 * */
	class VipViewHolder {
		ListView listView;
	}

	/**
	 * 加载经销商列表异步任务
	 * 
	 * @author Administrator
	 * 
	 */
	private class GoodsListAsyn_vip extends AsyncTask<String, Void, List<AgencyGoods>> {
		@Override
		protected void onPreExecute() {
			showProgress(MainActivity2.this);
		}
		@Override
		protected List<AgencyGoods> doInBackground(String... arg0) {
			if (arg0[0].equals("all")) {
				List<AgencyGoods> data = mUserService.getUserGoodsList(userId, mShareCache.getDatefromStore(LoginHelper.CITYCODE), getParameters(), MainActivity2.this, new NetExceptionCallBack() {
					@Override
					public void netExceptionCallback(int flag) {
						switch (flag) {
						case APIService.RETRY:
							break;
						default:
							break;
						}
					}
				});
				return data;
			}
			return null;
		}
		@Override
		protected void onPostExecute(List<AgencyGoods> result) {
			dismissProgress();
			NOW_BRAND_ID = null;
			NOW_VIP_STOCK_FLAG = null;
			if (result != null && result.size() > 0 && result.get(0).getMsg() == null && result.get(0).getCode() == null) {
				dismissNoData();
				mVipListAdapter.setVipData(result);
			} else {
				mVipListAdapter.setVipData(null);
				showNoData(MainActivity2.this);
			}
		}
	}
	private void cancleHot() {
		filter_native_hot.setBackgroundColor(getResources().getColor(R.color.gray_lower));
		filter_state_hot.setBackgroundColor(getResources().getColor(R.color.gray_lower));
		filter_new.setBackgroundColor(getResources().getColor(R.color.gray_lower));
		filter_up.setBackgroundColor(getResources().getColor(R.color.gray_lower));
		filter_recommendation.setBackgroundColor(getResources().getColor(R.color.gray_lower));
		all_21.setBackgroundColor(getResources().getColor(R.color.gray_lower));
		un_intelligent.setBackgroundColor(getResources().getColor(R.color.gray_lower));
		intelligent.setBackgroundColor(getResources().getColor(R.color.gray_lower));
		all_31.setBackgroundColor(getResources().getColor(R.color.gray_lower));
		net_2g.setBackgroundColor(getResources().getColor(R.color.gray_lower));
		net_3g.setBackgroundColor(getResources().getColor(R.color.gray_lower));
		all_41.setBackgroundColor(getResources().getColor(R.color.gray_lower));
		samsung.setBackgroundColor(getResources().getColor(R.color.gray_lower));
		htc.setBackgroundColor(getResources().getColor(R.color.gray_lower));
		apple.setBackgroundColor(getResources().getColor(R.color.gray_lower));
		lenove.setBackgroundColor(getResources().getColor(R.color.gray_lower));
		zte.setBackgroundColor(getResources().getColor(R.color.gray_lower));
		huawei.setBackgroundColor(getResources().getColor(R.color.gray_lower));
		hisense.setBackgroundColor(getResources().getColor(R.color.gray_lower));
	}
	private View initHotView() {
		View hotLayout = mInflater.inflate(R.layout.hot_goods_new, null);
		mHotHolder = new HotViewHolder();
		mHotHolder.imageSwicther = (ImageSwitcher) hotLayout.findViewById(R.id.hot_image_switcher);
		mHotHolder.listView = (ListView) hotLayout.findViewById(R.id.hot_listview);
		mHotListAdapter = new HotListAdapter(this);
		mHotHolder.listView.setAdapter(mHotListAdapter);
		return hotLayout;
	}

	class HotViewHolder {
		ImageSwitcher imageSwicther;
		ListView listView;
	}

	private class GoodsSearchListAsyn extends AsyncTask<Void, Void, List<SerachGoods>> {
		@Override
		protected void onPreExecute() {
			showProgress(MainActivity2.this);
		}
		@Override
		protected List<SerachGoods> doInBackground(Void... arg0) {
			List<SerachGoods> data = mSearchService.serachGoods(NOW_KEWWORD, getParameters(), MainActivity2.this, new NetExceptionCallBack() {
				@Override
				public void netExceptionCallback(int flag) {
					switch (flag) {
					case APIService.RETRY:
						break;
					default:
						break;
					}
				}
			});
			return data;
		}
		@Override
		protected void onPostExecute(List<SerachGoods> result) {
			dismissProgress();
			if (result != null && result.size() > 0 && result.get(0).getMsg() == null && result.get(0).getCode() == null) {
				dismissNoData();
				List<ShopAd> list = new ArrayList<ShopAd>();
				ShopAd shopAd = new ShopAd();
				shopAd.setId("1");
				shopAd.setPic(R.drawable.hot_ad_0);
				list.add(shopAd);
				shopAd = new ShopAd();
				shopAd.setId("2");
				shopAd.setPic(R.drawable.hot_ad_1);
				list.add(shopAd);
				shopAd = new ShopAd();
				shopAd.setId("3");
				shopAd.setPic(R.drawable.hot_ad_2);
				list.add(shopAd);
				shopAd = new ShopAd();
				shopAd.setId("4");
				shopAd.setPic(R.drawable.hot_ad_3);
				list.add(shopAd);
				ShopAdsGroup ads = new ShopAdsGroup(list);
				List<ShopGoodsGroup> suit = new ArrayList<ShopGoodsGroup>();
				for (SerachGoods goods : result) {
					ShopGoodsGroup sgg = new ShopGoodsGroup();
					sgg.setGoods_name(goods.getGoodsName());
					sgg.setGoods_id(goods.getGoodsId());
					sgg.setGoods_middle_img(goods.getGoodsMiddleImg());
					sgg.setPromote_title2(goods.getPromoteTitle2());
					sgg.setLocal_sale(goods.getLocal_sale());
					sgg.setMarket_price(goods.getMarketPrice());
					sgg.setBase_color(goods.getBase_color());
					sgg.setPromote_title2(goods.getPromote_title2());
					suit.add(sgg);
				}
				mHotListAdapter.setHotData(mHotListAdapter.getHotList(ads, suit));
				mViewPager.setCurrentItem(1);
			} else {
				showNoData(MainActivity2.this);
				mHotListAdapter.setHotData(mHotListAdapter.getHotList(null, null));
				mViewPager.setCurrentItem(1);
			}
		}
	}
	private View initMsgView() {
		View msgLayout = mInflater.inflate(R.layout.message_listview_new, null);
		mMsgHolder = new MsgViewHolder();
		mMsgHolder.listview = (ListView) msgLayout.findViewById(R.id.message_listview_new);
		mMsgListAdapter = new MsgListAdapter(this, new Refresh() {
			@Override
			public void refreshDate() {
				onMsgSelected();
			}
		});
		mMsgHolder.listview.setAdapter(mMsgListAdapter);
		return msgLayout;
	}

	class MsgViewHolder {
		ListView listview;
	}

	class MyOnClickListener implements OnClickListener {
		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.tab_vip:
				mViewPager.setCurrentItem(0);
				break;
			case R.id.tab_hot:
				mViewPager.setCurrentItem(1);
				break;
			case R.id.tab_msg:
				mViewPager.setCurrentItem(2);
				break;
			default:
				break;
			}
		}
	}

	class MyOnPageChangeListener implements OnPageChangeListener {
		@Override
		public void onPageScrollStateChanged(int state) {
		}
		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
		}
		@Override
		public void onPageSelected(int position) {
			isMenuHide = true;
			int blockWidth = (int) (outMetrics.density * 30 + 30);
			switch (position) {
			case 0:
				mSearch.setVisibility(View.VISIBLE);
				mSearch2.setVisibility(View.GONE);
				mSearchEdit.setVisibility(View.GONE);
				mBlock.setPadding(0, 0, 0, 0);
				mBlock2.setPadding(mTabWidth / 2 - blockWidth / 2, 0, 0, 0);
				if (LoginHelper.checkLogin(MainActivity2.this)) {
					mBlock2.setVisibility(View.VISIBLE);
				} else {
					mBlock2.setVisibility(View.GONE);
				}
				if (nowBottomMenu.getVisibility() == View.VISIBLE) {
					mBottomMenu.setVisibility(View.GONE);
					mBottomMenu1.setVisibility(View.GONE);
					mBottomMenu2.setVisibility(View.GONE);
				}
				nowBottomMenu = mBottomMenu1;
				mTabVip.setTextColor(getResources().getColor(R.color.red_text));
				mTabHot.setTextColor(getResources().getColor(R.color.gray_title));
				mTabMsg.setTextColor(getResources().getColor(R.color.gray_title));
				String hisUserName = mShareCache.getDatefromStore(LoginHelper.CURRENT_USER_NAME);
				String hisPWD = mShareCache.getDatefromStore(LoginHelper.CURRENT_USER_PWD);
				if (etUserName != null && etUserPWD != null) {
					etUserName.setText(hisUserName);
					etUserPWD.setText(hisPWD);
				}
				onVipSelected();
				break;
			case 1:
				mSearch.setVisibility(View.VISIBLE);
				mSearch2.setVisibility(View.GONE);
				mSearchEdit.setVisibility(View.GONE);
				mBlock.setPadding(mTabWidth, 0, 0, 0);
				mBlock2.setPadding(mTabWidth + mTabWidth / 2 - blockWidth / 2, 0, 0, 0);
				mBlock2.setVisibility(View.VISIBLE);
				if (nowBottomMenu.getVisibility() == View.VISIBLE) {
					mBottomMenu.setVisibility(View.GONE);
					mBottomMenu1.setVisibility(View.GONE);
					mBottomMenu2.setVisibility(View.GONE);
				}
				nowBottomMenu = mBottomMenu;
				mTabVip.setTextColor(getResources().getColor(R.color.gray_title));
				mTabHot.setTextColor(getResources().getColor(R.color.red_text));
				mTabMsg.setTextColor(getResources().getColor(R.color.gray_title));
				onHotSelected();
				break;
			case 2:
				mSearch.setVisibility(View.VISIBLE);
				mSearch2.setVisibility(View.GONE);
				mSearchEdit.setVisibility(View.GONE);
				mBlock.setPadding(mTabWidth * 2, 0, 0, 0);
				mBlock2.setPadding(mTabWidth * 2 + mTabWidth / 2 - blockWidth / 2, 0, 0, 0);
				mBlock2.setVisibility(View.VISIBLE);
				if (nowBottomMenu.getVisibility() == View.VISIBLE) {
					mBottomMenu.setVisibility(View.GONE);
					mBottomMenu1.setVisibility(View.GONE);
					mBottomMenu2.setVisibility(View.GONE);
				}
				nowBottomMenu = mBottomMenu2;
				mTabHot.setTextColor(getResources().getColor(R.color.gray_title));
				mTabVip.setTextColor(getResources().getColor(R.color.gray_title));
				mTabMsg.setTextColor(getResources().getColor(R.color.red_text));
				onMsgSelected();
				break;
			default:
				break;
			}
		}
	}
	public void onVipSelected() {
		dismissNoData();
		if (!LoginHelper.checkLogin(this)) {
			return;
		}
		if (mVipListAdapter.getVipData() == null || mVipListAdapter.getVipData().size() == 0) {
			new GoodsListAsyn_vip().execute(new String[] { "all" });
			userId = centerController.getShareCache().getDatefromTemp(LoginHelper.CURRENT_USER_ID);
			if (userId != null && !"".equals(userId)) {
				mVipHolder.listView.setOnScrollListener(new OnScrollListener() {
					@Override
					public void onScrollStateChanged(AbsListView view, int scrollState) {
						nowBottomMenu.setVisibility(View.GONE);
					}
					@Override
					public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
					}
				});
			}
		}
	}
	public void onHotSelected() {
		dismissNoData();
		if (mHotListAdapter.getHotData() == null || mHotListAdapter.getHotData().size() == 0) {
			if (LoginHelper.checkLogin(MainActivity2.this))
				DEFAULT_GROUP_ID = MyConstant.GROUP_TWO;
			else
				DEFAULT_GROUP_ID = MyConstant.GROUP_TWO_TWO;
			mShareCache.putDatetoStore(SHARE_CACHE_KEY_1, DEFAULT_GROUP_ID);
			new GetGoodsListAsy().execute();
		}
	}
	public void onMsgSelected() {
		dismissNoData();
		List<Message> data = MySqlite.getInstance(this).getMessageByMword(null);
		Log.i("info", "msg_size == " + data.size());
		if (data != null && data.size() > 0) {
			mMsgListAdapter.setMsgData(formatMessage(data));
		} else {
			mMsgListAdapter.setMsgData(new ArrayList<LocalMessage>());
			showNoData(this);
		}
	}
	public void onMsgSelected(String s) {
		if (mMsgListAdapter.getMsgData() == null || mMsgListAdapter.getMsgData().size() == 0) {
			List<Message> data = MySqlite.getInstance(this).getMessageByMword(null);
			Log.i("info", "msg_size == " + data.size());
			if (data != null && data.size() > 0) {
				dismissNoData();
				mMsgListAdapter.setMsgData(formatMessage(data));
			} else {
				showNoData(this);
			}
		}
	}
	private List<LocalMessage> formatMessage(List<Message> messages) {
		List<LocalMessage> localMessages = new ArrayList<LocalMessage>();
		for (Message sms : messages) {
			if (sms != null) {
				LocalMessage lm = new LocalMessage();
				lm.setMessage(sms);
				String messtate = centerController.getShareCache().getDatefromStore(sms.getId());
				if (messtate == null) {
					lm.setState(lm.VIRGIN);
				}
				localMessages.add(lm);
			}
		}
		List<LocalMessage> mm = new ArrayList<LocalMessage>();
		for (int i = localMessages.size() - 1; i >= 0; i--) {
			mm.add(localMessages.get(i));
		}
		return mm;
	}
	public void onMsgSelected2() {
		dismissNoData();
		List<Message> data = MySqlite.getInstance(this).getMessageByMType(NOW_MSG_CONDITION);
		Log.i("info", "msg_size == " + data.size());
		if (data != null && data.size() > 0) {
			mMsgListAdapter.setMsgData(formatMessage(data));
		} else {
			mMsgListAdapter.setMsgData(formatMessage(data));
			showNoData(this);
		}
	}
	public void onMsgSelected3() {
		dismissNoData();
		List<Message> data = MySqlite.getInstance(this).getMessageByMTime(NOW_MSG_TIME_CONDITION);
		Log.i("info", "msg_size == " + data.size());
		if (data != null && data.size() > 0) {
			mMsgListAdapter.setMsgData(formatMessage(data));
		} else {
			mMsgListAdapter.setMsgData(formatMessage(data));
			showNoData(this);
		}
	}

	class MyPagerApapter extends PagerAdapter {
		private List<View> mList = new ArrayList<View>();
		public MyPagerApapter() {
		}
		public MyPagerApapter(List<View> list) {
			mList = list;
		}
		@Override
		public int getCount() {
			return mList.size();
		}
		@Override
		public boolean isViewFromObject(View view, Object obj) {
			return view == (obj);
		}
		@Override
		public Object instantiateItem(View container, int position) {
			((ViewPager) container).addView(mList.get(position), 0);
			return mList.get(position);
		}
		@Override
		public void destroyItem(View container, int position, Object object) {
			((ViewPager) container).removeView(mList.get(position));
		}
	}
	public void backHide() {
		final int len = (outMetrics.widthPixels) / 7 * 5 + (int) (outMetrics.density * 6);
		Animation animation = null;
		if (isMenuHide) {
			animation = new TranslateAnimation(0, -len, 0, 0);
		} else {
			animation = new TranslateAnimation(0, len, 0, 0);
		}
		animation.setFillAfter(true);
		animation.setDuration(500);
		animation.setAnimationListener(new AnimationListener() {
			@Override
			public void onAnimationStart(Animation arg0) {
			}
			@Override
			public void onAnimationRepeat(Animation arg0) {
			}
			@Override
			public void onAnimationEnd(Animation arg0) {
				if (isMenuHide) {
					mMainLayout.layout(-len, 0, outMetrics.widthPixels - len, outMetrics.heightPixels);
				} else {
					mMainLayout.layout(0, 0, outMetrics.widthPixels, outMetrics.heightPixels);
				}
				mMainLayout.clearAnimation();
				isMenuHide = !isMenuHide;
			}
		});
		mMainLayout.startAnimation(animation);
	}
	public void quicklybackHide() {
		final int len = (outMetrics.widthPixels) / 7 * 5 + (int) (outMetrics.density * 6);
		Animation animation = null;
		animation = AnimationUtils.loadAnimation(MainActivity2.this, android.R.anim.fade_in);
		animation.setFillAfter(true);
		animation.setDuration(0);
		animation.setAnimationListener(new AnimationListener() {
			@Override
			public void onAnimationStart(Animation arg0) {
			}
			@Override
			public void onAnimationRepeat(Animation arg0) {
			}
			@Override
			public void onAnimationEnd(Animation arg0) {
				if (isMenuHide) {
					mMainLayout.layout(-len, 0, outMetrics.widthPixels - len, outMetrics.heightPixels);
				} else {
					mMainLayout.layout(0, 0, outMetrics.widthPixels, outMetrics.heightPixels);
				}
				mMainLayout.clearAnimation();
				isMenuHide = !isMenuHide;
			}
		});
		mMainLayout.startAnimation(animation);
	}
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			exitBy2Click();
		}
		return false;
	}
	private static Boolean isExit = false;
	private void exitBy2Click() {
		if (!isMenuHide) {
			backHide();
			return;
		}
		Timer tExit = null;
		if (isExit == false) {
			isExit = true;
			Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
			tExit = new Timer();
			tExit.schedule(new TimerTask() {
				@Override
				public void run() {
					isExit = false;
				}
			}, 2000);
		} else {
			SharedPreferencesHelper sharedPreferencesHelper = PollingSpreadService.sharedPreferencesHelper;
			if (sharedPreferencesHelper != null) {
				String userName = mShareCache.getDatefromTemp(LoginHelper.CURRENT_USER_NAME);
				sharedPreferencesHelper.putString("userName", userName);
			}
			mShareCache.clearDatetoTemp();
			super.finish();
		}
	}

	class GetGoodsListAsy extends AsyncTask<Void, Void, List<ShopGoodsGroup>> {
		protected void onPreExecute() {
			showProgress(MainActivity2.this);
		}
		@Override
		protected List<ShopGoodsGroup> doInBackground(Void... params) {
			List<ShopGoodsGroup> goodsGroup = mShopService.getGoodsList(mShareCache.getDatefromStore(SHARE_CACHE_KEY_1), mShareCache.getDatefromStore(SHARE_CACHE_KEY_2), mShareCache.getDatefromStore(SHARE_CACHE_KEY_3), mShareCache.getDatefromStore(SHARE_CACHE_KEY_4), new ArrayList<Parameter>(),
					MainActivity2.this, new NetExceptionCallBack() {
						@Override
						public void netExceptionCallback(int flag) {
						}
					}, mShareCache);
			return goodsGroup;
		}
		@Override
		protected void onPostExecute(List<ShopGoodsGroup> result) {
			super.onPostExecute(result);
			dismissProgress();
			if (result != null && result.size() > 0 && result.get(0).getMsg() == null && result.get(0).getCode() == null) {
				dismissNoData();
				List<ShopAd> list = new ArrayList<ShopAd>();
				ShopAd shopAd = new ShopAd();
				shopAd.setId("1");
				shopAd.setPic(R.drawable.hot_ad_0);
				list.add(shopAd);
				shopAd = new ShopAd();
				shopAd.setId("2");
				shopAd.setPic(R.drawable.hot_ad_1);
				list.add(shopAd);
				shopAd = new ShopAd();
				shopAd.setId("3");
				shopAd.setPic(R.drawable.hot_ad_2);
				list.add(shopAd);
				shopAd = new ShopAd();
				shopAd.setId("4");
				shopAd.setPic(R.drawable.hot_ad_3);
				list.add(shopAd);
				ShopAdsGroup ads = new ShopAdsGroup(list);
				mHotListAdapter.setHotData(mHotListAdapter.getHotList(ads, result));
			} else {
				showNoData(MainActivity2.this);
				mHotListAdapter.setHotData(mHotListAdapter.getHotList(null, null));
			}
		}
	}

	class GetVIPGoodsListAsy extends AsyncTask<Void, Void, List<AgencyGoods>> {
		protected void onPreExecute() {
			showProgress(MainActivity2.this);
		}
		@Override
		protected List<AgencyGoods> doInBackground(Void... params) {
			List<AgencyGoods> ads = mUserService.getVIPGoodsList(userId, NOW_VIP_STOCK_FLAG, mShareCache.getDatefromStore(SHARE_CACHE_KEY_VIP_2), mShareCache.getDatefromStore(SHARE_CACHE_KEY_VIP_3), mShareCache.getDatefromStore(SHARE_CACHE_KEY_VIP_4),
					mShareCache.getDatefromStore(LoginHelper.CITYCODE), new ArrayList<Parameter>(), MainActivity2.this, new NetExceptionCallBack() {
						@Override
						public void netExceptionCallback(int flag) {
						}
					});
			return ads;
		}
		@Override
		protected void onPostExecute(List<AgencyGoods> result) {
			super.onPostExecute(result);
			dismissProgress();
			NOW_BRAND_ID = null;
			NOW_VIP_STOCK_FLAG = null;
			if (result != null && result.size() > 0 && result.get(0).getMsg() == null && result.get(0).getCode() == null) {
				dismissNoData();
				mVipListAdapter.setVipData(result);
			} else {
				mVipListAdapter.setVipData(null);
				showNoData(MainActivity2.this);
			}
		}
	}
}
