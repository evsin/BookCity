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
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.lasun.mobile.assistant.domain.ShopGoodsGroup;
import com.lasun.mobile.assistant.net.service.APIService;
import com.lasun.mobile.assistant.net.service.APIService.NetExceptionCallBack;
import com.lasun.mobile.assistant.net.service.ShopService;
import com.lasun.mobile.assistant.utils.VideoUtil;

public class PhoneActivity extends MenuActivity {
	private ListView goodsLv;
	private LayoutInflater inflater;
	private GoodsListAdapter mGoodsAdapter;
	private ShopService mShopService;
	private static final String DEFAULT_GROUP_ID = "1002";
	private List<ShopGoodsGroup> data;
	private TextView noData;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.phone);
		init();
	}
	/**
	 * 初始化一切
	 */
	private void init() {
		initView();
		initData();
		initListener();
		goodsLv.setAdapter(mGoodsAdapter);
	}
	@Override
	protected void onStart() {
		super.onStart();
		bigshoot(1);
		to();
		if (getIntent().getBooleanExtra("exit", false)) {
			this.finish();
		}
	}
	private void initView() {
		goodsLv = (ListView) findViewById(R.id.phone_lv_goods);
		noData = (TextView) findViewById(R.id.please_try);
	}
	private void initData() {
		inflater = LayoutInflater.from(this);
		mShopService = new ShopService();
	}
	private void initListener() {
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

	/**
	 * 商品列表适配器
	 * 
	 * @author Administrator
	 * 
	 */
	private class GoodsListAdapter extends BaseAdapter {
		@Override
		public int getCount() {
			return data.size();
		}
		@Override
		public Object getItem(int position) {
			return data.get(position);
		}
		@Override
		public long getItemId(int position) {
			return position;
		}
		@Override
		public View getView(int position, View convertView, ViewGroup arg2) {
			ViewHolder holder = null;
			if (convertView == null) {
				convertView = inflater.inflate(R.layout.goods_item, null);
				holder = new ViewHolder();
				holder.goodsThumb = (ImageView) convertView.findViewById(R.id.goods_item_goodsThumb);
				holder.goodsName = (TextView) convertView.findViewById(R.id.goods_item_goods_name);
				holder.promoteTitle = (TextView) convertView.findViewById(R.id.goods_item_promoto_title);
				holder.marketPrice = (TextView) convertView.findViewById(R.id.goods_item_market_price);
				holder.giftInfo = (TextView) convertView.findViewById(R.id.goods_item_gift_info);
				holder.newIcon = (ImageView) convertView.findViewById(R.id.goods_new_icon);
				holder.inventoryLayout = (RelativeLayout) convertView.findViewById(R.id.goods_item_inventory_layout);
				holder.video = (ImageView) convertView.findViewById(R.id.s1);
				holder.message = (TextView) convertView.findViewById(R.id.s2);
				holder.purchase = (ImageView) convertView.findViewById(R.id.s3);
				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}
			if (data != null && data.size() > 0) {
				final ShopGoodsGroup mSGG = data.get(position);
				String goodsName = mSGG.getGoods_name();
				String marketPrice = mSGG.getMarket_price();
				String promotoTitle = mSGG.getPromote_title1();
				String giftInfo = mSGG.getGift_ids();
				if (goodsName != null && !"".equals(goodsName)) {
					holder.goodsName.setText(goodsName);
				}
				if (marketPrice != null && !"".equals(marketPrice)) {
					holder.marketPrice.setText("￥" + marketPrice);
				}
				if (promotoTitle != null && !"".equals(promotoTitle)) {
					holder.promoteTitle.setText(promotoTitle);
				}
				if (giftInfo != null && !"".equals(giftInfo)) {
					holder.giftInfo.setText(giftInfo);
				}
				holder.inventoryLayout.setVisibility(View.GONE);
				String imageUrl = mSGG.getGoods_middle_img();
				if (imageUrl != null && !"".equals(imageUrl)) {
					holder.goodsThumb.setTag(imageUrl);
				}
				getImageBitmap(holder.goodsThumb);
				holder.video.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						new VideoUtil().playNetVideo("http://daily3gp.com/vids/family_guy_penis_car.3gp", PhoneActivity.this);
					}
				});
				holder.purchase.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						Intent intent = new Intent();
						intent.setData(Uri.parse("http://caigou.china.alibaba.com/buyoffer/12504810.htm"));
						intent.setAction(Intent.ACTION_VIEW);
						startActivity(intent);
					}
				});
				holder.message.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						Intent Dintent = new Intent(PhoneActivity.this, MessageActivity.class);
						Dintent.putExtra("fromItem", true);
						startActivity(Dintent);
					}
				});
				convertView.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View arg0) {
						Intent intent = new Intent(PhoneActivity.this, GoodsDetailsActivity.class);
						intent.putExtra("goodsId", mSGG.getGoods_id());
						intent.putExtra("goodsName", mSGG.getGoods_name());
						startActivity(intent);
					}
				});
			}
			return convertView;
		}
	}
	@Override
	protected void onDestroy() {
		super.onDestroy();
		System.gc();
	}
}
