package com.lansun.mobile.client.adapter;

import java.util.List;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.net.Uri;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.lansun.mobile.assistant.constant.MyConstant;
import com.lasun.mobile.assistant.domain.AgencyGoods;
import com.lasun.mobile.assistant.utils.APIUtils;
import com.lasun.mobile.assistant.utils.LoginHelper;
import com.lasun.mobile.client.assistant.activity.GoodsDetailsActivity;
import com.lasun.mobile.client.assistant.activity.MenuActivity;
import com.lasun.mobile.client.assistant.activity.MessageActivity;
import com.lasun.mobile.client.assistant.activity.R;
import com.lasun.moblile.assistant.core.CenterController;
import com.lasun.moblile.assistant.push.MySqlite;

/**
 * VIP Listview adapter
 * */
public class VipListAdapter extends BaseAdapter {
	private List<AgencyGoods> data;
	private LayoutInflater mInflater;
	private MenuActivity mContext;

	public VipListAdapter(MenuActivity context) {
		mInflater = LayoutInflater.from(context);
		mContext = context;
	}

	public void setVipData(List<AgencyGoods> data) {
		this.data = data;
		notifyDataSetChanged();
	}

	public List<AgencyGoods> getVipData() {
		return this.data;
	}

	@Override
	public int getCount() {
		if (data == null) {
			return 0;
		}
		return data.size();
	}

	@Override
	public AgencyGoods getItem(int i) {
		if (data == null) {
			return null;
		}
		return data.get(i);
	}

	@Override
	public long getItemId(int i) {
		return i;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.my_goods_item, null);
			holder = new ViewHolder();
			holder.state_pic = (ImageView) convertView
					.findViewById(R.id.my_good_state_pic);
			holder.goods_pic = (ImageView) convertView
					.findViewById(R.id.my_good_pic);
			holder.state_txt1 = (TextView) convertView
					.findViewById(R.id.my_good_state_txt1);
			holder.state_txt2 = (TextView) convertView
					.findViewById(R.id.my_good_state_txt2);
			holder.goods_name = (TextView) convertView
					.findViewById(R.id.my_goods_txt_name);
			holder.goods_goto = (TextView) convertView
					.findViewById(R.id.my_goods_goto);
			holder.buy = (TextView) convertView.findViewById(R.id.my_goods_buy);
			holder.price = (TextView) convertView
					.findViewById(R.id.my_goods_txt_price);
			holder.msg = (TextView) convertView
					.findViewById(R.id.my_goods_txt_sms);
			holder.local = (RatingBar) convertView
					.findViewById(R.id.local_sale);
			holder.province = (RatingBar) convertView
					.findViewById(R.id.province_sale);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
			wipeHolder(holder);
		}

		final AgencyGoods agencyGoods = getItem(position);
		if (agencyGoods != null) {
			setData(agencyGoods, holder);
			setOnClickListener(convertView, agencyGoods, holder);
		}
		return convertView;
	}

	class ViewHolder {
		ImageView state_pic;
		ImageView goods_pic;
		TextView state_txt1;
		TextView state_txt2;
		TextView goods_name;
		TextView goods_goto;
		TextView buy;
		TextView price;
		TextView msg;
		RatingBar local;
		RatingBar province;
	}

	private void setData(AgencyGoods agencyGoods, ViewHolder holder) {
		String goodsName = agencyGoods.getGoods_name();
		String marketPrice = agencyGoods.getMarket_price();
		String imageUrl = APIUtils.getInstance().getValueByKey(
				"hiCDMACommonBaseUrl")
				+ agencyGoods.getGoods_thumb();
		String stock = agencyGoods.getGoods_stock();
		String base_color = agencyGoods.getBase_color();
		String loacl = agencyGoods.getL_rank();
		String province = agencyGoods.getP_rank();
		String keyword = agencyGoods.getBase_model();
		if (!TextUtils.isEmpty(keyword)) {
			int count = MySqlite.getInstance(mContext).getRelativeMessageCount(
					keyword);
			holder.msg.setText(String.valueOf(count));
		}
		if (goodsName != null && !"".equals(goodsName)) {
			holder.goods_name.setText(goodsName); 
		}
		if (marketPrice != null && !"".equals(marketPrice)) {
			holder.price.setText("￥" + marketPrice);
		}
		if (loacl != null && !"".equals(loacl) && !"0".equals(province)) {
			holder.local.setRating(Float.parseFloat(loacl));
		} else {
			holder.local.setRating(1.0f);
		}
		if (province != null && !"".equals(province) && !"0".equals(province)) {
			holder.province.setRating(Float.parseFloat(province));
		} else {
			holder.province.setRating(1.0f);
		}
		if (imageUrl != null && !"".equals(imageUrl)) {
			holder.goods_pic.setTag(imageUrl);
		}
		if (stock != null && !"".equals(stock)) {
			if (stock.equals("中")) {
				holder.state_pic
						.setBackgroundResource(R.drawable.my_goods_state_suit);
				ColorStateList cl = mContext.getResources().getColorStateList(
						R.color.text_stock_suit);
				holder.state_txt1.setTextColor(cl);
				holder.state_txt1.setText("周转期：适中");
				holder.state_txt2.setVisibility(View.GONE);
			} else if (stock.equals("低")) {
				holder.state_pic
						.setBackgroundResource(R.drawable.my_goods_state_warning);
				ColorStateList cl = mContext.getResources().getColorStateList(
						R.color.text_stock_low);
				holder.state_txt1.setTextColor(cl);
				holder.state_txt1.setText("周转期：偏低");
				holder.state_txt2.setVisibility(View.VISIBLE);
			} else if (stock.equals("高")) {
				holder.state_pic
						.setBackgroundResource(R.drawable.my_goods_state_high);
				ColorStateList cl = mContext.getResources().getColorStateList(
						R.color.text_stock_high);
				holder.state_txt1.setTextColor(cl);
				holder.state_txt1.setText("周转期：过高");
				holder.state_txt2.setVisibility(View.GONE);
			}
		}
		mContext.getImageBitmap(holder.goods_pic);
	}

	private void wipeHolder(ViewHolder viewholder) {
		viewholder.goods_pic.setImageBitmap(null);
	}

	private void setOnClickListener(View convertView,
			final AgencyGoods agencyGoods, ViewHolder holder) {
		holder.buy.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String userName = CenterController
						.getCenterController(mContext).getShareCache()
						.getDatefromTemp(LoginHelper.CURRENT_USER_NAME);
				Intent intent = new Intent();
				intent.setData(Uri.parse(MyConstant.getPurchaseEntrance(
						userName, agencyGoods.getGoods_id())));
				intent.setAction(Intent.ACTION_VIEW);
				mContext.startActivity(intent);
			}
		});
		holder.goods_goto.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(mContext, GoodsDetailsActivity.class);
				intent.putExtra("goodsId", agencyGoods.getGoods_id());
				intent.putExtra("goodsName", agencyGoods.getGoods_name());
				mContext.startActivity(intent);
			}
		});
		holder.msg.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent Dintent = new Intent(mContext, MessageActivity.class);
				Dintent.putExtra("keyword", agencyGoods.getGoods_name());
				mContext.startActivity(Dintent);
			}
		});
		
		convertView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(mContext, GoodsDetailsActivity.class);
				intent.putExtra("goodsId", agencyGoods.getGoods_id());
				intent.putExtra("goodsName", agencyGoods.getGoods_name());
				mContext.startActivity(intent);
			}
		});

	}
}