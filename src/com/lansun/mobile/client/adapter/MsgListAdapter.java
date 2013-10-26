package com.lansun.mobile.client.adapter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnKeyListener;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.lasun.mobile.assistant.domain.LocalMessage;
import com.lasun.mobile.client.assistant.activity.MenuActivity;
import com.lasun.mobile.client.assistant.activity.MessageDetailsActivity;
import com.lasun.mobile.client.assistant.activity.R;
import com.lasun.moblile.assistant.core.CenterController;
import com.lasun.moblile.assistant.push.Message;
import com.lasun.moblile.assistant.push.MySqlite;

/**
 * 消息Listview adapter
 * */
@SuppressLint("SimpleDateFormat")
public class MsgListAdapter extends BaseAdapter {
	private List<LocalMessage> data;
	private LayoutInflater mInflater;
	private MenuActivity mContext;
	private ViewHolder mHolder = null;
	private Refresh mRefresh;
	public MsgListAdapter(MenuActivity context, Refresh refresh) {
		mInflater = LayoutInflater.from(context);
		mContext = context;
		mRefresh = refresh;
	}
	public void setMsgData(List<LocalMessage> data) {
		this.data = data;
		notifyDataSetChanged();
	}
	public List<LocalMessage> getMsgData() {
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
			convertView = mInflater.inflate(R.layout.message_item_new, null);
			mHolder = new ViewHolder();
			mHolder.filter = (TextView) convertView.findViewById(R.id.message_item_filter);
			mHolder.title = (TextView) convertView.findViewById(R.id.message_item_title);
			mHolder.orNew = (TextView) convertView.findViewById(R.id.message_item_new);
			mHolder.content = (TextView) convertView.findViewById(R.id.message_item_content);
			mHolder.time = (TextView) convertView.findViewById(R.id.message_item_time);
			convertView.setTag(mHolder);
		} else {
			mHolder = (ViewHolder) convertView.getTag();
		}
		final LocalMessage msg = (LocalMessage) getItem(position);
		if (msg != null) {
			setData(msg);
			setOnClickListener(convertView, msg);
		}
		return convertView;
	}

	class ViewHolder {
		TextView filter;
		TextView title;
		TextView orNew;
		TextView content;
		TextView time;
	}
	private void setData(LocalMessage mmsg) {
		Message msg = mmsg.getMessage();
		String filter = msg.getmType();
		String title = msg.getTitle();
		String isnew = mmsg.getState();
		String content = msg.getDetails();
		if (filter != null && !"".equals(filter)) {
			mHolder.filter.setText(filter);
			if (filter.equals("促销")) {
				mHolder.filter.setBackgroundColor(mContext.getResources().getColor(R.color.msg_red));
			} else if (filter.equals("通知")) {
				mHolder.filter.setBackgroundColor(mContext.getResources().getColor(R.color.msg_green));
			} else if (filter.equals("应用")) {
				mHolder.filter.setBackgroundColor(mContext.getResources().getColor(R.color.msg_blue));
			} else {
				mHolder.filter.setBackgroundColor(mContext.getResources().getColor(R.color.button_bg));
			}
		}
		if (title != null && !"".equals(title)) {
			mHolder.title.setText(title);
		}
		if (LocalMessage.VIRGIN.equals(isnew)) {
			mHolder.orNew.setVisibility(View.VISIBLE);
			System.out.println("消息是处女");
		} else {
			System.out.println("消息是不是不是！处女");
			mHolder.orNew.setVisibility(View.GONE);
		}
		if (content != null && !"".equals(content)) {
			mHolder.content.setText(content);
		}
		SimpleDateFormat sdf = new SimpleDateFormat("MM-dd HH:mm");
		mHolder.time.setText(sdf.format(new Date(msg.getReceiveTime())));
	}
	private void setOnClickListener(View convertView, final LocalMessage mmsg) {
		convertView.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				CenterController centerController = CenterController.getCenterController(arg0.getContext());
				centerController.getShareCache().putDatetoStore(mmsg.getMessage().getId(), LocalMessage.USED);
				Intent intent = new Intent(mContext, MessageDetailsActivity.class);
				intent.putExtra("msg", mmsg.getMessage());
				mContext.startActivity(intent);
			}
		});
		convertView.setOnLongClickListener(new OnLongClickListener() {
			@Override
			public boolean onLongClick(View arg0) {
				deleteCurrentItem(arg0, mmsg);
				return true;
			}
		});
	}
	private void deleteCurrentItem(View convertview, LocalMessage localmessage) {
		final View v = convertview;
		final LocalMessage mmsg = localmessage;
		if (localmessage != null && localmessage.getMessage() != null) {
			AlertDialog.Builder alertbBuilder = new AlertDialog.Builder(mContext);
			alertbBuilder.setTitle("删除").setMessage("你确定要删除当前消息吗？").setPositiveButton("确定", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
					MySqlite.getInstance(mContext).deleteMessageById(mmsg.getMessage().getId());
					mRefresh.refreshDate();
				}
			}).setNegativeButton("取消", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
					dialog.cancel();
				}
			}).create();
			alertbBuilder.show();
			alertbBuilder.setCancelable(false);
			alertbBuilder.setOnKeyListener(new OnKeyListener() {
				@Override
				public boolean onKey(DialogInterface arg0, int arg1, KeyEvent arg2) {
					return true;
				}
			});
		}
	}

	public interface Refresh {
		public void refreshDate();
	}
}