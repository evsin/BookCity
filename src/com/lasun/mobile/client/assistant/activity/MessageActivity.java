package com.lasun.mobile.client.assistant.activity;

import java.util.List;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import com.lasun.moblile.assistant.push.Message;
import com.lasun.moblile.assistant.push.MySqlite;

public class MessageActivity extends MenuActivity {
	private ListView messageLv;
	private TextView title;
	private List<Message> allMessage;
	private LayoutInflater inflater;
	private MessageAdapter adapter;
	private String keyword;
	private TextView noMsg;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.message);
		init();
	}
	@Override
	protected void onStart() {
		super.onStart();
	}
	/**
	 * 初始化一切
	 */
	private void init() {
		initView();
		initData();
		initListener();
		messageLv.setAdapter(adapter);
	}
	/**
	 * 初始化控件
	 */
	private void initView() {
		messageLv = (ListView) findViewById(R.id.message_lv);
		title = (TextView) findViewById(R.id.message_title_text);
		noMsg = (TextView) findViewById(R.id.no_condition_message);
	}
	/**
	 * 初始化数据
	 */
	private void initData() {
		inflater = LayoutInflater.from(this);
		adapter = new MessageAdapter();
		keyword = getIntent().getStringExtra("keyword");
		if (!TextUtils.isEmpty(keyword)) {
			allMessage = MySqlite.getInstance(this).getMessageByMword(keyword);
		}
		if (allMessage == null || allMessage.size() == 0) {
			noMsg.setVisibility(View.VISIBLE);
		}
		title.setText(getIntent().getStringExtra("keyword"));
	}
	/**
	 * 初始化监听
	 */
	private void initListener() {
	}

	class ViewHolder {
		TextView titleTv;
		Button filterBtn;
		TextView detailsTv;
	}

	/**
	 * 信息列表适配器
	 * 
	 * @author Administrator
	 * 
	 */
	private class MessageAdapter extends BaseAdapter {
		@Override
		public int getCount() {
			if (allMessage != null && allMessage.size() > 0) {
				return allMessage.size();
			} else {
				return 0;
			}
		}
		@Override
		public Object getItem(int position) {
			return position;
		}
		@Override
		public long getItemId(int position) {
			return position;
		}
		@Override
		public View getView(int position, View convertView, ViewGroup arg2) {
			ViewHolder holder = null;
			if (convertView == null) {
				holder = new ViewHolder();
				convertView = inflater.inflate(R.layout.message_item, null);
				holder.filterBtn = (Button) convertView.findViewById(R.id.message_item_filter);
				holder.titleTv = (TextView) convertView.findViewById(R.id.message_item_title);
				holder.detailsTv = (TextView) convertView.findViewById(R.id.message_item_content);
				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}
			final Message msg = allMessage.get(position);
			holder.filterBtn.setText(msg.getmType());
			holder.titleTv.setText(msg.getTitle());
			holder.detailsTv.setText(msg.getDetails());
			convertView.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View arg0) {
					Intent intent = new Intent(MessageActivity.this, MessageDetailsActivity.class);
					intent.putExtra("msg", msg);
					startActivity(intent);
				}
			});
			return convertView;
		}
	}
	@Override
	protected void onDestroy() {
		super.onDestroy();
		System.gc();
	}
}
