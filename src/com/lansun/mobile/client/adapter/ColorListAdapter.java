package com.lansun.mobile.client.adapter;

import java.util.List;
import com.lasun.mobile.assistant.domain.GoodsBaseColor;
import com.lasun.mobile.client.assistant.activity.MenuActivity;
import com.lasun.mobile.client.assistant.activity.R;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ColorListAdapter extends BaseAdapter {
	private List<GoodsBaseColor> data;
	private LayoutInflater mInflater;
	private MenuActivity mContext;
	private ViewHolder mHolder = null;
	public ColorListAdapter(MenuActivity context) {
		mInflater = LayoutInflater.from(context);
		mContext = context;
	}
	public void setMsgData(List<GoodsBaseColor> data) {
		this.data = data;
		notifyDataSetChanged();
	}
	public List<GoodsBaseColor> getMsgData() {
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
	public Object getItem(int i) {
		return data.get(i);
	}
	@Override
	public long getItemId(int i) {
		return i;
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.software_update_deta_list_item, null);
			mHolder = new ViewHolder();
			mHolder.filter = (TextView) convertView.findViewById(R.id.update_details);
			convertView.setTag(mHolder);
		} else {
			mHolder = (ViewHolder) convertView.getTag();
		}
		final GoodsBaseColor msg = (GoodsBaseColor) getItem(position);
		if (msg != null) {
			setData(msg);
		}
		return convertView;
	}

	class ViewHolder {
		TextView filter;
	}
	private void setData(GoodsBaseColor mmsg) {
		String color = mmsg.getBase_color();
		mHolder.filter.setText(color);
	}
}
