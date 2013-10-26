package com.lasun.mobile.client.assistant.activity;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.utils.URLEncodedUtils;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.lansun.mobile.assistant.constant.MyConstant;
import com.lansun.mobile.assistant.notifydownload.DocDownloadService;
import com.lansun.mobile.assistant.notifydownload.GlobalUtils;
import com.lansun.mobile.client.adapter.ColorListAdapter;
import com.lansun.mobile.client.adapter.OmgAdapter;
import com.lansun.mobile.client.adapter.Omg_1Adapter;
import com.lasun.mobile.assistant.domain.Advertisement;
import com.lasun.mobile.assistant.domain.Goods;
import com.lasun.mobile.assistant.domain.GoodsBaseColor;
import com.lasun.mobile.assistant.domain.GoodsDealer;
import com.lasun.mobile.assistant.domain.GoodsUrlInfo;
import com.lasun.mobile.assistant.net.service.APIService;
import com.lasun.mobile.assistant.net.service.AdvertisementService;
import com.lasun.mobile.assistant.net.service.APIService.NetExceptionCallBack;
import com.lasun.mobile.assistant.net.service.GoodsService;
import com.lasun.mobile.assistant.utils.APIUtils;
import com.lasun.mobile.assistant.utils.LoginHelper;
import com.lasun.mobile.assistant.utils.VideoUtil;
import com.lasun.moblile.assistant.core.Parameter;
import com.lasun.moblile.assistant.push.MySqlite;

public class GoodsDetailsActivity extends MenuActivity {
	private ImageView videoImg; 
	private ImageView goodsImg; 
	private String omgUrl; 
	private TextView introduction; 
	private TextView attribute; 
	private LinearLayout style1;
	private LinearLayout style2;
	private Button purchase_btn; 
	private RelativeLayout message; 
	private TextView msgCount; 
	private RelativeLayout display2; 
	private LinearLayout displayL2; 
	private RelativeLayout display3; 
	private LinearLayout displayL3; 
	private RelativeLayout display4; 
	private LinearLayout displayL4; 
	private RelativeLayout display5; 
	private LinearLayout displayL5; 
	private RelativeLayout displayV; 
	private LinearLayout displayLV; 
	private ScrollView myScroll;
	private Gallery omgScroll; 
	private ImageView arrow2, arrow3, arrow4, arrow5, arrowv; 
	private TextView fuckLine2, fuckLine3, fuckLine4, fuckLine5, fuckLine6;
	private List<Goods> data; 
	private GoodsUrlInfo urls; 
	private GoodsService mGoodsService;
	private String[] goods;
	private TextView marketPrice; 
	private TextView goodsName;
	private TextView promotoTitle1;
	private TextView promotoTitle2;
	private TextView promotoTitle3;
	private TextView promotoTitle4;
	private List<TextView> pts;
	private TextView cheapPromoto; 
	private ImageView backArrow; 
	private RatingBar localSale;
	private RatingBar provinceSale; 
	private List<TextView> vns;
	private List<TextView> dns;
	private TextView video1;
	private TextView video2;
	private TextView video3;
	private TextView doc1;
	private TextView doc2;
	private TextView doc3;
	private TextView video_tv1;
	private TextView doc_tv2;

	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.goods_details);
		init();
		new GoodsDealerAsy().execute();
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
		video_tv1 = (TextView) findViewById(R.id.tv1);
		doc_tv2 = (TextView) findViewById(R.id.tv2);
		myScroll = (ScrollView) findViewById(R.id.my_scroll);
		omgScroll = (Gallery) findViewById(R.id.omg_scroll);
		videoImg = (ImageView) findViewById(R.id.goods_video);
		goodsImg = (ImageView) findViewById(R.id.goods_details_goods_image);
		style1 = (LinearLayout) findViewById(R.id.inventoty_layout_one);
		style2 = (LinearLayout) findViewById(R.id.inventoty_layout_two);
		if (getIntent().getBooleanExtra("display", false)) {
			style1.setVisibility(View.VISIBLE);
			style2.setVisibility(View.GONE);
		}
		introduction = (TextView) findViewById(R.id.goods_details_introduction);
		attribute = (TextView) findViewById(R.id.goods_details_attribute);
		purchase_btn = (Button) findViewById(R.id.purchase_btn);
		message = (RelativeLayout) findViewById(R.id.goods_details_message_layout);
		msgCount = (TextView) findViewById(R.id.goods_detailsmessage_text);
		display2 = (RelativeLayout) findViewById(R.id.display_two);
		displayL2 = (LinearLayout) findViewById(R.id.display_two_layout);
		display3 = (RelativeLayout) findViewById(R.id.display_three);
		displayL3 = (LinearLayout) findViewById(R.id.display_three_layout);
		display4 = (RelativeLayout) findViewById(R.id.display_four);
		displayL4 = (LinearLayout) findViewById(R.id.display_four_layout);
		display5 = (RelativeLayout) findViewById(R.id.display_five);
		displayL5 = (LinearLayout) findViewById(R.id.display_five_layout);
		displayV = (RelativeLayout) findViewById(R.id.display_video);
		displayLV = (LinearLayout) findViewById(R.id.display_video_layout);
		arrow2 = (ImageView) findViewById(R.id.arrow2);
		arrow3 = (ImageView) findViewById(R.id.arrow3);
		arrow4 = (ImageView) findViewById(R.id.arrow4);
		arrow5 = (ImageView) findViewById(R.id.arrow5);
		arrowv = (ImageView) findViewById(R.id.arrowv);
		fuckLine2 = (TextView) findViewById(R.id.fuck_line2);
		fuckLine3 = (TextView) findViewById(R.id.fuck_line3);
		fuckLine4 = (TextView) findViewById(R.id.fuck_line4);
		fuckLine5 = (TextView) findViewById(R.id.fuck_line5);
		fuckLine6 = (TextView) findViewById(R.id.fuck_line6);
		marketPrice = (TextView) findViewById(R.id.goods_details_market_price);
		goodsName = (TextView) findViewById(R.id.goods_details_goods_name);
		promotoTitle1 = (TextView) findViewById(R.id.goods_details_promoto_title1);
		promotoTitle2 = (TextView) findViewById(R.id.goods_details_promoto_title2);
		promotoTitle3 = (TextView) findViewById(R.id.goods_details_promoto_title3);
		promotoTitle4 = (TextView) findViewById(R.id.goods_details_promoto_title4);
		pts = new ArrayList<TextView>();
		pts.add(promotoTitle1);
		pts.add(promotoTitle2);
		pts.add(promotoTitle3);
		pts.add(promotoTitle4);
		backArrow = (ImageView) findViewById(R.id.arrow_back);
		cheapPromoto = (TextView) findViewById(R.id.cheap_pro);
		localSale = (RatingBar) findViewById(R.id.rb_local_sale);
		provinceSale = (RatingBar) findViewById(R.id.rb_province_sale);
		video1 = (TextView) findViewById(R.id.video_text1);
		video2 = (TextView) findViewById(R.id.video_text2);
		video3 = (TextView) findViewById(R.id.video_text3);
		doc1 = (TextView) findViewById(R.id.doc_text1);
		doc2 = (TextView) findViewById(R.id.doc_text2);
		doc3 = (TextView) findViewById(R.id.doc_text3);
		vns = new ArrayList<TextView>();
		dns = new ArrayList<TextView>();
		vns.add(video1);
		vns.add(video2);
		vns.add(video3);
		dns.add(doc1);
		dns.add(doc2);
		dns.add(doc3);

		displayL2.setVisibility(View.VISIBLE);
		arrow2.setBackgroundResource(R.drawable.arrow_up);
		fuckLine3.setVisibility(View.GONE);
		displayL3.setVisibility(View.VISIBLE);
		arrow3.setBackgroundResource(R.drawable.arrow_up);
		fuckLine4.setVisibility(View.GONE);
		displayL4.setVisibility(View.VISIBLE);
		arrow4.setBackgroundResource(R.drawable.arrow_up);
		fuckLine5.setVisibility(View.GONE);
		displayL5.setVisibility(View.VISIBLE);
		arrow5.setBackgroundResource(R.drawable.arrow_up);
		fuckLine6.setVisibility(View.GONE);

	}

	/**
	 * 初始化数据
	 */
	@SuppressLint("HandlerLeak")
	private void initData() {
		goods = new String[] { getIntent().getStringExtra("goodsId"),
				getIntent().getStringExtra("goodsName") };
		mGoodsService = new GoodsService();
		GoodsDetailsAsyn goodsAsyn = new GoodsDetailsAsyn();
		goodsAsyn.execute();
		GoodsUrlsAsyn asyn = new GoodsUrlsAsyn();
		asyn.execute();
	}

	/**
	 * 初始化监听
	 */
	private void initListener() {
		backArrow.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				GoodsDetailsActivity.this.finish();
			}
		});
		videoImg.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (urls != null && urls.getVideoUrl().size() > 0) {
					String videoUrl = APIUtils.getInstance().getValueByKey(
							"hiCDMACommonBaseUrl")
							+ "/" + urls.getVideoUrl().get(0);
					Log.i("info", "videoUrl = " + videoUrl);
					if (videoUrl != null && !"".equals(videoUrl)) {
						new VideoUtil().playNetVideo(videoUrl,
								GoodsDetailsActivity.this);
					} else {
						Toast.makeText(GoodsDetailsActivity.this, "暂无相关视频信息！",
								Toast.LENGTH_SHORT).show();
					}
				} else {
					Toast.makeText(GoodsDetailsActivity.this, "暂无相关视频信息！",
							Toast.LENGTH_SHORT).show();
				}
			}
		});
		purchase_btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				goodsDetailColorChose();
			}
		});
		message.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(GoodsDetailsActivity.this,
						MessageActivity.class);
				intent.putExtra("keyword", data.get(0).getBase_model());
				startActivity(intent);
			}
		});
		display2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				if (displayL2.getVisibility() == View.VISIBLE) {
					displayL2.setVisibility(View.GONE);
					arrow2.setBackgroundResource(R.drawable.arrow_down);
					fuckLine3.setVisibility(View.VISIBLE);
				} else {
					displayL2.setVisibility(View.VISIBLE);
					arrow2.setBackgroundResource(R.drawable.arrow_up);
					fuckLine3.setVisibility(View.GONE);
				}

			}
		});
		display3.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				if (displayL3.getVisibility() == View.VISIBLE) {
					displayL3.setVisibility(View.GONE);
					arrow3.setBackgroundResource(R.drawable.arrow_down);
					fuckLine4.setVisibility(View.VISIBLE);
				} else {
					displayL3.setVisibility(View.VISIBLE);
					arrow3.setBackgroundResource(R.drawable.arrow_up);
					fuckLine4.setVisibility(View.GONE);
				}

			}
		});
		display4.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				if (displayL4.getVisibility() == View.VISIBLE) {
					displayL4.setVisibility(View.GONE);
					arrow4.setBackgroundResource(R.drawable.arrow_down);
					fuckLine5.setVisibility(View.GONE);
				} else {
					displayL4.setVisibility(View.VISIBLE);
					arrow4.setBackgroundResource(R.drawable.arrow_up);
					fuckLine5.setVisibility(View.GONE);
				}

			}
		});
		display5.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				if (displayL5.getVisibility() == View.VISIBLE) {
					displayL5.setVisibility(View.GONE);
					arrow5.setBackgroundResource(R.drawable.arrow_down);
					fuckLine6.setVisibility(View.GONE);
				} else {
					displayL5.setVisibility(View.VISIBLE);
					arrow5.setBackgroundResource(R.drawable.arrow_up);
					fuckLine6.setVisibility(View.GONE);
				}

			}
		});
		displayV.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				if (displayLV.getVisibility() == View.VISIBLE) {
					displayLV.setVisibility(View.GONE);
					arrowv.setBackgroundResource(R.drawable.arrow_down);
					fuckLine2.setVisibility(View.VISIBLE);
				} else {
					displayLV.setVisibility(View.VISIBLE);
					arrowv.setBackgroundResource(R.drawable.arrow_up);
					fuckLine2.setVisibility(View.GONE);
				}

			}
		});
		goodsImg.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (omgUrl != null && !"".equals(omgUrl)) {
					omgScroll.setVisibility(View.VISIBLE);
					Omg_1Adapter adapter = new Omg_1Adapter(
							GoodsDetailsActivity.this, omgScroll);
					adapter.setMsgData(new String[] { omgUrl });
					omgScroll.setAdapter(adapter);
				}
			}
		});
		omgScroll.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				if (omgScroll.getVisibility() == View.VISIBLE) {
					omgScroll.setVisibility(View.GONE);
				}
			}
		});
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == event.KEYCODE_BACK) {
			if (omgScroll.getVisibility() == View.VISIBLE) {
				omgScroll.setVisibility(View.GONE);
			} else {
				GoodsDetailsActivity.this.finish();
			}
		}
		return false;
	}

	 List<GoodsBaseColor> gbcList = null;
		public void  goodsDetailColorChose() {
			LayoutInflater inflater = LayoutInflater.from(GoodsDetailsActivity.this);
			View updateView = inflater.inflate(
					R.layout.color_choose_item, null);
			 ListView phoneColorList = (ListView) updateView
					 .findViewById(R.id.phone_color);
			 ColorListAdapter cla = new ColorListAdapter(GoodsDetailsActivity.this);
			
			 if(data!=null&&data.size()>0){
				 gbcList = data.get(0).getColorAndIds();
				 cla.setMsgData(gbcList); 
				 phoneColorList.setAdapter(cla); 
			 }
			
			 Builder dialog = new AlertDialog.Builder(GoodsDetailsActivity.this);
				dialog.setTitle("请选择终端颜色");
				dialog.setView(updateView);
				dialog.create().show();
				phoneColorList.setOnItemClickListener(new OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> arg0, View arg1,
							int arg2, long arg3) {
						System.out.println("gbcList.get(arg2).getGoods_id() "+gbcList.get(arg2).getGoods_id());
						String userName = mShareCache
								.getDatefromStore(SaveAccountActivity.CURRENT_USER_NAME_B2B);
						if (null == userName) {
							userName = "";
						}
						Intent intent = new Intent();
						intent.setData(Uri.parse(MyConstant.getPurchaseEntrance(
								userName, gbcList.get(arg2).getB2b_id())));
						intent.setAction(Intent.ACTION_VIEW);
						startActivity(intent);
					}
				}) ;
			
		}
	
	
	/**
	 * 获取商品详情
	 * 
	 * @author Administrator
	 * 
	 */
	private class GoodsDetailsAsyn extends AsyncTask<Void, Void, List<Goods>> {
		@Override
		protected void onPreExecute() {
			showProgress(GoodsDetailsActivity.this);
		}

		@Override
		protected List<Goods> doInBackground(Void... params) {
			String temp = "";
			if (LoginHelper.checkLogin(GoodsDetailsActivity.this))
				temp = mShareCache.getDatefromStore(LoginHelper.CITYCODE);
			data = mGoodsService.getGoodsInfoByIds(
					new String[] { goods.length > 1 ? goods[0] : "1" }, temp,
					getParameters(), GoodsDetailsActivity.this,
					new NetExceptionCallBack() {

						@Override
						public void netExceptionCallback(int flag) {
							switch (flag) {
							case APIService.RETRY:
								initData();
								break;

							default:
								break;
							}
						}
					});
			List<GoodsBaseColor> GoodsBaseColors = mGoodsService
					.getColorAndIds(
							goods.length > 1 ? URLEncoder.encode(goods[1])
									: "1", getParameters(),
							GoodsDetailsActivity.this,
							new NetExceptionCallBack() {

								@Override
								public void netExceptionCallback(int flag) {
									switch (flag) {
									case APIService.RETRY:
										initData();
										break;

									default:
										break;
									}
								}
							});
			for (int i = 0; data != null && i < data.size(); i++) {
				data.get(i).setColorAndIds(GoodsBaseColors);
			}
			return data;
		}

		@Override
		protected void onPostExecute(List<Goods> result) {
			dismissProgress();
			if (result != null && result.get(0).getCode() == null
					&& result.get(0).getMsg() == null) {
				omgUrl = APIUtils.getInstance().getValueByKey(
						"hiCDMACommonBaseUrl")
						+ result.get(0).getGoods_indexurl();
				marketPrice.setText("￥" + result.get(0).getMarket_price());
				goodsName.setText(result.get(0).getGoods_name() + "  "
						+ result.get(0).getBase_color());
				String promoteTitleAll = result.get(0).getPromote_title2();
				String temp = promoteTitleAll.replaceAll("；", "\n");
				promotoTitle1.setText(temp);
				goodsImg.setTag(APIUtils.getInstance().getValueByKey(
						"hiCDMACommonBaseUrl")
						+ result.get(0).getGoods_img());
				videoImg.setTag(APIUtils.getInstance().getValueByKey(
						"hiCDMACommonBaseUrl")
						+ result.get(0).getGoods_middle_img());
				cheapPromoto.setText(result.get(0).getPromote_title1());
				String local = result.get(0).getL_rank();
				String province = result.get(0).getP_rank();
				String goodsDesc = result.get(0).getGoods_desc();
				String keyword = result.get(0).getBase_model();
				String intro = result.get(0).getGoods_attribute();
				if (!TextUtils.isEmpty(keyword)) {
					int count = MySqlite.getInstance(GoodsDetailsActivity.this)
							.getRelativeMessageCount(keyword);
					msgCount.setText(String.valueOf(count));
				}
				if (goodsDesc != null && !"".equals(goodsDesc)) {
					goodsDesc = goodsDesc.replace(" ", "");
					attribute.setText(goodsDesc);
				}
				if (intro != null && !"".equals(intro)) {
					introduction.setText(intro);
				}
				if (local != null && !"".equals(local) && !"0".equals(local)) {
					localSale.setRating(Float.parseFloat(local));
				} else {
					localSale.setRating(1.0f);
				}
				if (province != null && !"".equals(province)
						&& !"0".equals(province)) {
					provinceSale.setRating(Float.parseFloat(province));
				} else {
					provinceSale.setRating(1.0f);
				}
				getImageBitmap(goodsImg);
				getImageBitmap(videoImg);
			} else {
				Toast.makeText(GoodsDetailsActivity.this, "未拿到数据！",
						Toast.LENGTH_SHORT).show();
			}
		}
	}

	/**
	 * 获取商品urls
	 * 
	 * @author Administrator
	 * 
	 */
	private class GoodsUrlsAsyn extends AsyncTask<Void, Void, GoodsUrlInfo> {
		@Override
		protected void onPreExecute() {
			videoImg.setClickable(false);
		}

		@Override
		protected GoodsUrlInfo doInBackground(Void... params) {
			GoodsUrlInfo data = mGoodsService.getGoodsSource(
					goods.length > 1 ? goods[0] : "1", getParameters(),
					GoodsDetailsActivity.this, new NetExceptionCallBack() {

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
		protected void onPostExecute(GoodsUrlInfo result) {
			videoImg.setClickable(true);
			urls = result;
			if (urls != null) {
				setVDData();
			} else {

			}
		}
	}

	private String[] getUrlItem(List<String> str) {
		if (str != null && str.size() > 0) {
			String[] strs = new String[str.size()];
			for (int i = 0; i < str.size(); i++) {
				String s = str.get(i);
				strs[i] = s.substring(s.lastIndexOf("/") + 1, s.length());
			}
			return strs;
		} else {
			return null;
		}
	}

	private void setVDData() {
		String[] ViseoNames = getUrlItem(urls.getVideoUrl());
		if (ViseoNames != null && ViseoNames.length > 0) {
			for (int i = 0; i < ViseoNames.length; i++) {
				if (ViseoNames[i] != null && !"".equals(ViseoNames[i])) {
					video_tv1.setVisibility(View.VISIBLE);
					vns.get(i).setText((ViseoNames[i]));
					setOnclick(vns.get(i), urls.getVideoUrl().get(i), "v", null);
				}
			}
		}
		String[] docNames = getUrlItem(urls.getDocUrl());
		if (docNames != null && docNames.length > 0) {
			for (int i = 0; i < docNames.length; i++) {
				if (docNames[i] != null && !"".equals(docNames[i])) {
					doc_tv2.setVisibility(View.VISIBLE);
					dns.get(i).setText((docNames[i]));
					setOnclick(dns.get(i), urls.getDocUrl().get(i), "d",
							docNames[i]);
				}
			}
		}
	}

	private void setOnclick(View view, final String url, final String fromType,
			final String docName) {
		view.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				if (fromType.equals("v")) {
					String nowUrl = APIUtils.getInstance().getValueByKey(
							"hiCDMACommonBaseUrl")
							+ "/" + url;
					new VideoUtil().playNetVideo(nowUrl,
							GoodsDetailsActivity.this);
				} else {
					String nowUrl = APIUtils.getInstance().getValueByKey(
							"hiCDMACommonBaseUrl")
							+ "/" + url;
					Intent intent = new Intent(GoodsDetailsActivity.this,
							DocDownloadService.class);
					intent.putExtra(GlobalUtils.EXTRA_DOC_NAME, docName);
					intent.putExtra(GlobalUtils.EXTRA_DOC_URL,
							nowUrl.replace(" ", "%20"));
					startService(intent);
				}
			}
		});
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		System.gc();
	}

	/**
	 * 获取经销商信息列表
	 * */
	class GoodsDealerAsy extends AsyncTask<Void, Void, List<GoodsDealer>> {

		@Override
		protected List<GoodsDealer> doInBackground(Void... params) {
			GoodsService service = new GoodsService();
			List<GoodsDealer> goodsDealer = service.getGoodsDealerByIds(
					goods.length > 1 ? goods[0] : "1",
					mShareCache.getDatefromStore(LoginHelper.CITYCODE),
					new ArrayList<Parameter>(), GoodsDetailsActivity.this,
					new NetExceptionCallBack() {

						@Override
						public void netExceptionCallback(int flag) {

						}
					});

			return goodsDealer;
		}

		@Override
		protected void onPostExecute(List<GoodsDealer> result) {
			super.onPostExecute(result);
			if (result != null && result.size() != 0) {
				List<GoodsDealer> dealersList = result;
				for (int i = 0; i < dealersList.size(); i++) {
					View view = getLayoutInflater().inflate(
							R.layout.goods_details_dealer_item, null);
					TextView dealerName = (TextView) view
							.findViewById(R.id.goods_details_dealer_name_2);
					TextView dealerAdd = (TextView) view
							.findViewById(R.id.goods_details_dealer_add_2);
					TextView dealerPhone = (TextView) view
							.findViewById(R.id.goods_details_dealer_phone_2);
					ImageView phone = (ImageView) view
							.findViewById(R.id.goods_details_call_native);
					final String ph = dealersList.get(i).getSalesman_phone();
					phone.setOnClickListener(new OnClickListener() {

						@Override
						public void onClick(View arg0) {
							Intent callint2 = new Intent(Intent.ACTION_DIAL,
									Uri.parse("tel:" + ph));
							GoodsDetailsActivity.this.startActivity(callint2);

						}
					});

					String id = dealersList.get(i).getLocal_id();
					Log.d("id:", id);
					if (id.equals("0")) {
						Log.d("Name:", dealersList.get(i).getSalesman_name());
						dealerName.setText(dealersList.get(i)
								.getSalesman_name());
						dealerAdd.setText(dealersList.get(i)
								.getSalesman_address());
						dealerPhone.setText(dealersList.get(i)
								.getSalesman_phone());
						displayL4.addView(view);
					} else if (LoginHelper
							.checkLogin(GoodsDetailsActivity.this)) {
						dealerName.setText(dealersList.get(i)
								.getSalesman_name());
						dealerAdd.setText(dealersList.get(i)
								.getSalesman_address());
						dealerPhone.setText(dealersList.get(i)
								.getSalesman_phone());
						displayL5.addView(view);
					}
				}

			}
		}
	}
}
