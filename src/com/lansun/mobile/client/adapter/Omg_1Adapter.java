package com.lansun.mobile.client.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.ListView;

import com.lasun.mobile.client.assistant.activity.MenuActivity;
import com.lasun.mobile.client.assistant.activity.R;

/**
 * 终极页大图 adapter
 * */
@SuppressLint("SimpleDateFormat")
public class Omg_1Adapter extends BaseAdapter {
	private String[] data;
	private LayoutInflater mInflater;
	private Gallery lv;
	private MenuActivity mContext;

	public Omg_1Adapter(MenuActivity context, Gallery lv) {
		mInflater = LayoutInflater.from(context);
		this.lv = lv;
		mContext = context;
	}

	public void setMsgData(String[] data) {
		this.data = data;
		notifyDataSetChanged();
	}

	public String[] getMsgData() {
		return this.data;
	}


	@Override
	public int getCount() {
		if (data == null) {
			return 0;
		}
		return data.length;
	}

	@Override
	public Object getItem(int i) {
		return data[i];
	}

	@Override
	public long getItemId(int i) {
		return i;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		convertView = mInflater.inflate(R.layout.omg_item, null);
		ImageView omg = (ImageView) convertView.findViewById(R.id.omg);
		omg.setTag(data[position]);
		mContext.getImageBitmap(omg);

		return convertView;
	}

}