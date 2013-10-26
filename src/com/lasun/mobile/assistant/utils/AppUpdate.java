package com.lasun.mobile.assistant.utils;

/**
 * 
 * <pre>
 * 业务名:
 * 功能说明: 
 * 编写日期:	2011-9-1
 * 作者:	李勇
 * 
 * 历史记录
 * 1、修改日期：
 *    修改人：
 *    修改内容：
 * </pre>
 */
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import com.lasun.mobile.assistant.domain.AppInfo;
import com.lasun.mobile.assistant.net.service.APIService.NetExceptionCallBack;
import com.lasun.mobile.client.assistant.activity.R;
import com.lasun.mobile.client.broadcast.PhoneStateReceiver;
import com.lasun.moblile.assistant.core.CenterController;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.IntentFilter;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.RelativeLayout.LayoutParams;

public class AppUpdate {
	private static final int FOR_SDCARD = 1;
	private static final int FOR_SYSTEM = 2;
	private static final String UPDATEFILENAME = "MobAss.apk";
	private TextView updatePercentage;
	private TextView updateDownlength;
	private TextView updateFilelength;
	private RelativeLayout loading;
	public Builder pBar;
	private Activity mActivity;
	private float filelength;
	private float downlength;
	private ProgressBar updateProgress;
	private AlertDialog downloadAlertDialog;
	private AlertDialog retryAlertDialog;
	private AppInfo mAppInfo;
	private String mOldVerCode;
	private String mNewVerCode;
	private PhoneStateReceiver mPhoneStateReceiver;
	public boolean downloadOver = true;
	public AppUpdate(Activity activity) {
		this.mActivity = activity;
		mPhoneStateReceiver = new PhoneStateReceiver(handler);
		registerPhoneReceiver();
	}
	private void registerPhoneReceiver() {
		IntentFilter filter = new IntentFilter(TelephonyManager.ACTION_PHONE_STATE_CHANGED);
		this.mActivity.registerReceiver(mPhoneStateReceiver, filter);
	}
	public void unRegisterPhoneReceiver() {
		if (mPhoneStateReceiver != null) {
			this.mActivity.unregisterReceiver(mPhoneStateReceiver);
		}
	}
	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			if (!Thread.currentThread().isInterrupted()) {
				switch (msg.what) {
				case 0:
					updateProgress.setMax((int) filelength);
					updateFilelength.setText((int) filelength / 1000 + "kb");
					break;
				case 1:
					updateProgress.setProgress((int) downlength);
					updateDownlength.setText((int) downlength / 1000 + "kb/");
					updatePercentage.setText(format(downlength / filelength));
					break;
				case 2:
					pBar.setCancelable(true);
					if (downloadAlertDialog != null && downloadAlertDialog.isShowing()) {
						downloadAlertDialog.dismiss();
						Toast.makeText(mActivity, "文件下载完成", 1).show();
					}
					break;
				case 10:
					if (!downloadOver) {
						interruptDownload();
						retryDialogShow();
					}
					break;
				case -1:
					break;
				}
			}
			super.handleMessage(msg);
		}
	};
	private String getServerVerCode(String androidVersion) {
		CenterController.getCenterController(mActivity).getContextService().checkSoftVersion();
		return null;
	}
	public void startUpdateAsy() {
		UpdateAsy updateAsy = new UpdateAsy();
		updateAsy.execute();
	}
	public boolean compareVerCode(String oldVerCode, String newVerCode) {
		if (compare(oldVerCode, newVerCode) < 0) {
			return true;
		} else {
			return false;
		}
	}
	public void retryDialog() {
		Builder retry = new AlertDialog.Builder(mActivity);
		retry = retry.setTitle("提示").setMessage("网络异常，下载失败").setPositiveButton("重试", new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				downloadOver = true;
				updateDialogCreate();
				dialog.cancel();
			}
		});
		retry.setNegativeButton("取消", new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.cancel();
				downloadOver = true;
			}
		});
		retryAlertDialog = retry.create();
	}
	public void retryDialogShow() {
		if (null != retryAlertDialog && !retryAlertDialog.isShowing()) {
			retryAlertDialog.show();
		} else if (null == retryAlertDialog) {
			retryDialog();
			retryAlertDialog.show();
		}
	}
	public void doNewVersionUpdate(String oldVerCode, String newVerCode) {
		StringBuffer sb = new StringBuffer();
		sb.append("当前版本:");
		sb.append(oldVerCode);
		sb.append(", 发现新版本:");
		sb.append(newVerCode);
		sb.append(", 是否更新?");
		LayoutInflater inflater = LayoutInflater.from(mActivity);
		View updateView = inflater.inflate(R.layout.software_update_details_list, null);
		TextView updatetTitle = (TextView) updateView.findViewById(R.id.update_title);
		updatetTitle.setText(sb.toString());
		Builder dialog = new AlertDialog.Builder(mActivity);
		dialog.setCancelable(false);
		dialog.setTitle("软件更新");
		dialog.setView(updateView);
		dialog.setPositiveButton("更新", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				updateDialogCreate();
			}
		});
		dialog.setNegativeButton("暂不更新", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
				dialog.dismiss();
			}
		});
		downloadAlertDialog = dialog.create();
		downloadAlertDialog.show();
	}
	private void updateDialogCreate() {
		if (!NetUtil.checkNet(mActivity)) {
			whenNetUnuse(mActivity);
			return;
		}
		downloadOver = false;
		View updateView = LayoutInflater.from(mActivity).inflate(R.layout.update_view, null);
		updateProgress = (ProgressBar) updateView.findViewById(R.id.update_bar);
		updatePercentage = (TextView) updateView.findViewById(R.id.update_percentage);
		updateDownlength = (TextView) updateView.findViewById(R.id.update_data);
		updateFilelength = (TextView) updateView.findViewById(R.id.update_data2);
		pBar = new AlertDialog.Builder(mActivity);
		updateProgress.setIndeterminate(false);
		pBar.setTitle("正在下载");
		pBar.setMessage("请稍候...");
		pBar.setView(updateView);
		pBar.setCancelable(false);
		String downloadURL = (mAppInfo == null) ? (null) : (mAppInfo.getVersion_url());
		if (downloadURL == null)
			return;
		if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED) && MemorySpaceCheck.hasEnoughMemory(Environment.getExternalStorageDirectory() + UPDATEFILENAME)) {
			downFile(downloadURL, FOR_SDCARD);
		} else if (MemorySpaceCheck.hasEnoughMemory(Environment.getDataDirectory() + UPDATEFILENAME)) {
			downFile(downloadURL, FOR_SYSTEM);
		}
	}
	private void sendMsg(int flag) {
		Message msg = new Message();
		msg.what = flag;
		handler.sendMessage(msg);
	}
	public void interruptDownload() {
		if (downloadAlertDialog != null && downloadAlertDialog.isShowing()) {
			downloadAlertDialog.cancel();
		}
	}
	Thread thread = null;
	private void downFile(final String url, final int downTage) {
		downloadAlertDialog = pBar.create();
		downloadAlertDialog.show();
		thread = new Thread() {
			public void run() {
				HttpClient client = new DefaultHttpClient();
				HttpGet get = new HttpGet(url);
				HttpResponse response;
				File file = null;
				try {
					response = client.execute(get);
					HttpEntity entity = response.getEntity();
					filelength = entity.getContentLength();
					InputStream is = entity.getContent();
					FileOutputStream fileOutputStream = null;
					if (is != null) {
						if (downTage == FOR_SDCARD) {
							file = new File(Environment.getExternalStorageDirectory(), UPDATEFILENAME);
							fileOutputStream = new FileOutputStream(file);
						} else if (downTage == FOR_SYSTEM) {
							fileOutputStream = mActivity.openFileOutput(UPDATEFILENAME, Context.MODE_WORLD_READABLE);
						}
						byte[] buf = new byte[1024];
						sendMsg(0);
						int ch = -1;
						downlength = 0;
						while ((ch = is.read(buf)) != -1) {
							fileOutputStream.write(buf, 0, ch);
							downlength += ch;
							if (filelength > 0) {
								sendMsg(1);
							}
						}
					}
					if (fileOutputStream != null) {
						fileOutputStream.flush();
						fileOutputStream.close();
						downloadOver = true;
						down();
					}
				} catch (ClientProtocolException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		};
		thread.start();
	}
	/**
	 * 方法说明：
	 * */
	private void down() {
		handler.post(new Runnable() {
			public void run() {
				sendMsg(2);
				update();
			}
		});
	}
	/**
	 * 方法说明：升级替换原程序
	 * */
	private void update() {
		Intent intent = new Intent(Intent.ACTION_VIEW);
		if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
			intent.setDataAndType(Uri.fromFile(new File(Environment.getExternalStorageDirectory(), UPDATEFILENAME)), "application/vnd.android.package-archive");
		} else {
			intent.setDataAndType(Uri.fromFile(new File(mActivity.getFileStreamPath(UPDATEFILENAME).getPath())), "application/vnd.android.package-archive");
		}
		mActivity.startActivity(intent);
	}
	/**
	 * 方法说明：分割
	 * */
	public static List<String> detailsCut(String details) {
		String[] s = details.split(";");
		List<String> list = new ArrayList<String>();
		int i;
		for (i = 0; i < s.length; i++) {
			list.add(s[i]);
		}
		return list;
	}

	private class UpdateAsy extends AsyncTask<String, String, Boolean> {
		@Override
		protected Boolean doInBackground(String... params) {
			mOldVerCode = APIUtils.getInstance().getValueByKey("version_no");
			mAppInfo = CenterController.getCenterController(mActivity).getContextService().checkSoftVersion();
			if (mAppInfo != null && mAppInfo.getVersion_no() != null && mAppInfo.getVersion_url() != null) {
				mNewVerCode = mAppInfo.getVersion_no();
				Boolean b = compareVerCode(mOldVerCode, mNewVerCode);
				return b;
			} else {
				return false;
			}
		}
		@Override
		protected void onPostExecute(Boolean result) {
			result = compareVerCode(mOldVerCode, mNewVerCode);
			offLoading(loading);
			if (result && mNewVerCode != null) {
				doNewVersionUpdate(mOldVerCode, mNewVerCode);
			} else if (mNewVerCode != null) {
				Toast.makeText(mActivity, "您当前为最新版本", Toast.LENGTH_SHORT).show();
			}
			super.onPostExecute(result);
		}
		@Override
		protected void onPreExecute() {
			loading = onLoading(mActivity);
			super.onPreExecute();
		}
	}
	private String format(double i) {
		NumberFormat nf = NumberFormat.getPercentInstance();
		nf.setMaximumIntegerDigits(5);
		nf.setMinimumFractionDigits(0);
		return nf.format(i);
	}
	private int compare(String old, String neww) {
		if (old != null && neww != null) {
			String otemp = old.replace('.', '0');
			int ovd = Integer.parseInt(otemp);
			String ntemp = neww.replace('.', '0');
			int nvd = Integer.parseInt(ntemp);
			int sum = ovd - nvd;
			return sum;
		} else {
			return 1;
		}
	}
	private RelativeLayout onLoading(final Activity activity) {
		final RelativeLayout mProgress = (RelativeLayout) LayoutInflater.from(activity).inflate(R.layout.progress, null);
		final LayoutParams params = new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);
		activity.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				mActivity.addContentView(mProgress, params);
			}
		});
		return mProgress;
	}
	private void offLoading(final RelativeLayout mProgress) {
		if (mProgress != null) {
			mProgress.post(new Runnable() {
				@Override
				public void run() {
					mProgress.removeAllViews();
					mProgress.setVisibility(View.GONE);
				}
			});
		}
	}
	private void whenNetUnuse(final Context mContext) {
		new Thread(new Runnable() {
			public void run() {
				if (Looper.myLooper() == null) {
					Looper.prepare();
					NetExceptionUtils.createNetUnused(mContext, null);
					Looper.loop();
				} else {
					NetExceptionUtils.createNetUnused(mContext, null);
				}
			}
		}).start();
	}
}
