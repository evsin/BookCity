package com.lansun.mobile.client.adapter;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;
import com.lansun.mobile.assistant.constant.MyConstant;
import com.lasun.mobile.assistant.utils.AppUpdate;
import com.lasun.mobile.assistant.utils.LoginHelper;
import com.lasun.mobile.assistant.utils.PushServiceUtil;
import com.lasun.mobile.client.assistant.activity.MainActivity2;
import com.lasun.mobile.client.assistant.activity.MenuActivity;
import com.lasun.mobile.client.assistant.activity.R;
import com.lasun.mobile.client.assistant.activity.SaveAccountActivity;
import com.lasun.moblile.assistant.core.CenterController;
import com.lasun.moblile.assistant.push.PollingSpreadService;

public class MenuListAdapter extends BaseAdapter {
	private LayoutInflater mInflater;
	MenuActivity mContext;
	private String[] str = new String[] { "使用偏好", "售后服务", "信息接收", "账户管理", "软件分享", "版本更新", "清除缓存", "退出系统" };
	private int[] show = new int[] { View.GONE, View.GONE, View.VISIBLE, View.GONE, View.GONE, View.GONE, View.GONE, View.GONE };
	private String[] str_msg = new String[] { "", "", "打开", "", "", "", "", "" };
	private Mattth mMathod;
	private ViewPager mViewPager;
	public MenuListAdapter(MenuActivity context, ViewPager viewpager) {
		if (mInflater == null) {
			mInflater = LayoutInflater.from(context);
		}
		mContext = context;
		mViewPager = viewpager;
	}
	public void setMathod(Mattth m) {
		mMathod = m;
	}
	@Override
	public int getCount() {
		return str.length;
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
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = new ViewHolder();
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.menu_item, null);
			holder.text = (TextView) convertView.findViewById(R.id.menu_text);
			holder.pic = (CheckBox) convertView.findViewById(R.id.menu_pic);
			holder.text_msg = (TextView) convertView.findViewById(R.id.menu_msg);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.text.setText(str[position]);
		holder.pic.setVisibility(show[position]);
		holder.text_msg.setText(str_msg[position]);
		if (position == 0) {
			holder.pic.setVisibility(View.GONE);
			holder.text.setTextSize(16);
			LayoutParams params = new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
			params.addRule(RelativeLayout.CENTER_IN_PARENT);
			params.setMargins(220, 0, 0, 0);
			holder.text.setLayoutParams(params);
		}
		if (position == 1) {
			convertView.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View arg0) {
					String userName = CenterController.getCenterController(mContext).getShareCache().getDatefromStore(SaveAccountActivity.CURRENT_USER_NAME_SHOUHOU);
					if (null == userName) {
						userName = "";
					}
					Intent intent = new Intent();
					intent.setData(Uri.parse(MyConstant.getSaleEntrance(userName)));
					intent.setAction(Intent.ACTION_VIEW);
					mContext.startActivity(intent);
				}
			});
		}
		if (position == 2) {
			if (PushServiceUtil.judgeIfPush(mContext))
				holder.pic.setChecked(true);
			else
				holder.pic.setChecked(false);
			holder.pic.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View arg0) {
					CheckBox cb = (CheckBox) arg0;
					if (PushServiceUtil.judgeIfPush(mContext)) {
						Intent intent = new Intent("com.aaaa.aaaaa.Stop");
						intent.setClass(mContext, PollingSpreadService.class);
						mContext.startService(intent);
						mContext.mShareCache.putDatetoStore(MyConstant.PUSH_SWITCH_FLAG, PollingSpreadService.SWITCH_OFF);
						cb.setChecked(false);
						Toast.makeText(mContext, "关闭推送服务", Toast.LENGTH_SHORT).show();
					} else {
						PushServiceUtil.openPushService(mContext, PollingSpreadService.EXECUTE_MODE_APP);
						mContext.mShareCache.putDatetoStore(MyConstant.PUSH_SWITCH_FLAG, PollingSpreadService.SWITCH_ON);
						cb.setChecked(true);
						Toast.makeText(mContext, "开启推送服务", Toast.LENGTH_SHORT).show();
					}
				}
			});
		}
		if (position == 3) {
			convertView.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View arg0) {
					mViewPager.setCurrentItem(1);
					Intent intent = new Intent(mContext, SaveAccountActivity.class);
					mContext.startActivity(intent);
				}
			});
		}
		if (position == 4) {
			convertView.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View arg0) {
					Intent intent = new Intent(Intent.ACTION_SEND);
					intent.setType("text/plain");
					intent.putExtra(Intent.EXTRA_SUBJECT, "软件分享：手机销售指南-代理商的随身助手");
					intent.putExtra(Intent.EXTRA_TEXT, mContext.getResources().getString(R.string.soft_share));
					Intent intentcreate = Intent.createChooser(intent, mContext.getTitle());
					intentcreate.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
					mContext.startActivity(intentcreate);
				}
			});
		}
		if (position == 5) {
			convertView.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View arg0) {
					AppUpdate au = new AppUpdate(mContext);
					au.startUpdateAsy();
				}
			});
		}
		if (position == 6) {
			convertView.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View arg0) {
					String bindstate = CenterController.getCenterController(mContext).getShareCache().getDatefromStore(LoginHelper.CURRENT_USER_BIND);
					if ("true".equals(bindstate)) {
						String[] keys = { LoginHelper.CURRENT_USER_BIND, LoginHelper.CURRENT_USER_ID, LoginHelper.CURRENT_USER_NAME_B2B, LoginHelper.CURRENT_USER_PWD_B2B, LoginHelper.CURRENT_USER_NAME_SHOUHOU, LoginHelper.CURRENT_USER_PWD_SHOUHOU, LoginHelper.AUTO_LOGIN, LoginHelper.CITYCODE,
								LoginHelper.CURRENT_USER_NAME, LoginHelper.CURRENT_USER_PWD };
						CenterController.getCenterController(mContext).getShareCache().clearStoreandSaveParticular(keys);
					} else {
						CenterController.getCenterController(mContext).getShareCache().clearDatetoStore();
					}
					Toast.makeText(mContext, "清除缓存成功", Toast.LENGTH_SHORT).show();
				}
			});
		}
		if (position == 7) {
			convertView.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View arg0) {
					dialogExit();
				}
			});
		}
		return convertView;
	}

	class ViewHolder {
		TextView text;
		CheckBox pic;
		TextView text_msg;
	}
	/**
	 * 退出程序
	 */
	private void exit() {
		Intent exitMain = new Intent(mContext, MainActivity2.class);
		exitMain.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		exitMain.putExtra("exit", true);
		CenterController.getCenterController(mContext).getShareCache().clearDatetoTemp();
		mContext.startActivity(exitMain);
	}
	/**
	 * 退出程序提示
	 */
	private void dialogExit() {
		AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
		builder.setTitle("退出");
		builder.setMessage("你确认要退出手机销售助理吗？");
		builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				exit();
				mContext.finish();
				dialog.dismiss();
			}
		});
		builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		});
		builder.create().show();
	}

	public interface Mattth {
		void math();
	}
}
