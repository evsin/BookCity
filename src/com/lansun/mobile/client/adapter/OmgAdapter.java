package com.lansun.mobile.client.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.lasun.mobile.client.assistant.activity.MenuActivity;
import com.lasun.mobile.client.assistant.activity.R;

/**
 * 消息Listview adapter
 * */
@SuppressLint("SimpleDateFormat")
public class OmgAdapter extends BaseAdapter {
	private String[] data;
	private LayoutInflater mInflater;
	private ListView lv;
	private MenuActivity mContext;

	public OmgAdapter(MenuActivity context, ListView lv) {
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
		convertView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				if (lv.getVisibility() == View.VISIBLE) {
					lv.setVisibility(View.GONE);
				}
			}
		});
		return convertView;
	}

}