package com.lasun.moblile.assistant.push;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.IBinder;
import android.util.Log;
import android.widget.RemoteViews;
import com.lasun.mobile.assistant.utils.LoginHelper;
import com.lasun.mobile.assistant.utils.PushServiceUtil;
import com.lasun.mobile.client.assistant.activity.MainActivity2;
import com.lasun.mobile.client.assistant.activity.R;
import com.lasun.moblile.assistant.core.CenterController;
import com.lasun.moblile.assistant.core.ShareCache;

public class PollingSpreadService extends Service {
	public static final String RECEIVE_SWITCH = "switch";
	public static final String SWITCH_ON = "on";
	public static final String SWITCH_OFF = "off";
	public static final String MESSAGENUMBER = "MessageNumber";
	private HttpRequester httpRequester;
	private String userName;
	public static SharedPreferencesHelper sharedPreferencesHelper;
	private CenterController centerController;
	public ShareCache mShareCache;
	private static final int TIME_INTERVAL = 5 * 60 * 1000;
	/**
	 * 打开客户端时开启服务
	 */
	public static final int EXECUTE_MODE_APP = 0xFFFF01;
	/**
	 * 网络成功连接网络的时间开启服务
	 */
	public static final int EXECUTE_MODE_NET = 0xFFFF02;
	/**
	 * 开机的时间开启服务
	 */
	public static final int EXECUTE_MODE_BOOT = 0xFFFF03;
	private static boolean initFlag = false;
	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}
	@Override
	public void onDestroy() {
		super.onDestroy();
		trunOffBeforeService();
	}
	private void trunOffBeforeService() {
		if (timer != null) {
			timer.cancel();
			timer = null;
		}
		if (timerTask != null) {
			timerTask.cancel();
			timerTask = null;
		}
		Log.i("info", "推送服务销毁了");
	}
	private long intervalTime = 0;
	@Override
	public void onStart(Intent intent, int startId) {
		super.onStart(intent, startId);
		if (intent == null) {
			Intent it = new Intent();
			init(it);
			return;
		}
		if (initFlag) {
			initFlag = false;
			init(intent);
		}
		int executeMode = intent.getIntExtra("executeMode", -1);
		if (executeMode == EXECUTE_MODE_NET) {
			if (System.currentTimeMillis() - intervalTime > 2000) {
				new Thread(new Runnable() {
					@Override
					public void run() {
						receiveAllMessage();
					}
				}).start();
				intervalTime = System.currentTimeMillis();
			} else {
				intervalTime = 0;
			}
		}
	}
	/**
	 * 在onCreate中设置标志位，以便于在onStart中检查SharedPreferences并将数据读取到内存中
	 */
	@Override
	public void onCreate() {
		super.onCreate();
		Log.i("info", "oncreate-----");
		initFlag = true;
		sharedPreferencesHelper = new SharedPreferencesHelper("spread", PollingSpreadService.this);
	}
	/**
	 * 在此服务第一次调用时执行初始化的工作
	 */
	TimerTask timerTask;
	Timer timer;
	private void init(Intent intent) {
		httpRequester = new HttpRequester();
		if (!sharedPreferencesHelper.exists()) {
			int executeMode = intent.getIntExtra("executeMode", -1);
			if (executeMode == EXECUTE_MODE_APP) {
				userName = CenterController.getCenterController(this).getShareCache().getDatefromTemp(LoginHelper.CURRENT_USER_NAME);
				if (userName != null && !userName.trim().equals("")) {
					sharedPreferencesHelper.putString("userName", userName);
				} else {
					userName = "";
				}
			}
		} else {
			userName = sharedPreferencesHelper.getString("userName");
		}
		if (null == userName || "".equals(userName)) {
		} else {
			timerTask = new TimerTask() {
				@Override
				public void run() {
					receiveAllMessage();
				}
			};
			timer = new Timer();
			timer.schedule(timerTask, 20 * 1000, TIME_INTERVAL);
		}
	}
	public void receiveAllMessage() {
		if (GsonUtile.checkNetWorkState(this)) {
			userName = CenterController.getCenterController(this).getShareCache().getDatefromTemp(LoginHelper.CURRENT_USER_NAME);
			if (userName == null || "".equals(userName)) {
				userName = sharedPreferencesHelper.getString("userName");
			}
			if ("".equals(userName)) {
				return;
			}
			List<Message> list = httpRequester.httpReceiveMore(userName, HttpRequester.REQUEST);
			if (null == list || list.size() == 0) {
				return;
			} else {
				final List<String> idList = new ArrayList<String>();
				for (Message msg : list) {
					idList.add(msg.getId());
					msg.setReceiveTime(System.currentTimeMillis());
					msg.setNewFlag(0);
					MySqlite.getInstance(this).insertMessage(msg);
				}
				if (list.size() > 0) {
					notifyMessage(list);
				}
				new Thread(new Runnable() {
					@Override
					public void run() {
						httpRequester.httpMessageCount(userName, idList);
					}
				}).start();
			}
		}
	}
	/**
	 * 展示多条推送消息
	 */
	public void notifyMessage(List<Message> messages) {
		NotificationManager mNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		int icon = R.drawable.icon;
		centerController = CenterController.getCenterController(this);
		mShareCache = centerController.getShareCache();
		String temp = (mShareCache.getDatefromStore(MESSAGENUMBER) == null) ? ("") : (mShareCache.getDatefromStore(MESSAGENUMBER));
		for (int i = 0; i < messages.size(); i++) {
			temp += "1";
		}
		mShareCache.putDatetoStore(MESSAGENUMBER, temp);
		CharSequence tickerText = "您有" + temp.length() + "条推送信息";
		long when = System.currentTimeMillis();
		Notification notification = new Notification(icon, "您有新消息了", when);
		Intent intent = new Intent();
		intent.setClass(this, MainActivity2.class);
		intent.putExtra("notify", "notify");
		notification.defaults = Notification.DEFAULT_SOUND;
		notification.flags = Notification.FLAG_AUTO_CANCEL;
		PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
		notification.setLatestEventInfo(this, "推送消息", tickerText, pendingIntent);
		mNotificationManager.notify(444, notification);
	}
	private boolean processMessage(Message message) {
		boolean flag = false;
		if (message == null) {
			sharedPreferencesHelper.putString("immediate", String.valueOf(true));
			flag = false;
		} else if (message.getCode() == 105) {
			sharedPreferencesHelper.putString("immediate", String.valueOf(false));
			flag = false;
		} else if (message.getCode() == 100) {
			sharedPreferencesHelper.putString("immediate", String.valueOf(false));
			notification(message);
			flag = true;
		}
		return flag;
	}
	@SuppressLint("SimpleDateFormat")
	boolean checkDate(String jobTime) {
		boolean flag = true;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date now = new Date();
		String nowTime = sdf.format(now);
		if (!nowTime.equals(jobTime)) {
			flag = false;
		}
		return flag;
	}
	public void notification(Message message) {
		Notification notification = new Notification();
		notification.icon = R.drawable.icon;
		notification.flags = Notification.FLAG_AUTO_CANCEL;
		notification.tickerText = message.getDetailsText();
		notification.flags = message.isPushForce() ? Notification.FLAG_ONGOING_EVENT | Notification.FLAG_AUTO_CANCEL : Notification.FLAG_AUTO_CANCEL;
		Bitmap bitmap = null;
		if (message.getPicUrl() != null && "".equals(message.getPicUrl())) {
			bitmap = ((BitmapDrawable) this.getResources().getDrawable(R.drawable.icon)).getBitmap();
		} else {
			bitmap = httpRequester.getImageRes(message.getPicUrl());
		}
		if (bitmap == null) {
			bitmap = ((BitmapDrawable) this.getResources().getDrawable(R.drawable.icon)).getBitmap();
		}
		RemoteViews contentView = new RemoteViews(getPackageName(), R.layout.custom_notifi);
		contentView.setImageViewBitmap(R.id.notifi_image, bitmap);
		centerController = CenterController.getCenterController(this);
		mShareCache = centerController.getShareCache();
		String temp = (mShareCache.getDatefromStore(MESSAGENUMBER) == null) ? ("") : (mShareCache.getDatefromStore(MESSAGENUMBER));
		mShareCache.putDatetoStore(MESSAGENUMBER, temp += "1");
		contentView.setTextViewText(R.id.notifi_title, "您当前有" + temp.length() + "条新消息");
		contentView.setTextViewText(R.id.notifi_desc, "");
		notification.contentView = contentView;
		Intent intent = new Intent(this, MainActivity2.class);
		intent.putExtra("message", message);
		intent.putExtra("notify", "notify");
		PendingIntent contentIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
		notification.contentIntent = contentIntent;
		Random random = new Random(new Date().getTime());
		NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		notificationManager.notify(444, notification);
	}
	public void offMsg() {
		this.stopSelf();
	}
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		if (null != intent && "com.aaaa.aaaaa.Stop".equals(intent.getAction())) {
			offMsg();
		}
		return super.onStartCommand(intent, flags, startId);
	}
}
