package com.lasun.mobile.client.assistant.activity;

import java.io.ByteArrayOutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import com.lasun.mobile.assistant.utils.FlashGetUtil;
import com.lasun.moblile.assistant.push.Message;
import com.superbearman6.imagecachetatics.ImageCacheManager;

public class MessageDetailsActivity extends MenuActivity {
	
	public static final String TAG="MessageDetailsActivity";
	
	private ImageView sharemss;
	private Message msg;
	private TextView content;
	private TextView filter;
	private TextView title;
	private TextView time;
	private ImageView mImageView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.message_details);
		init();
	}
	@Override
	protected void onStart() {
		super.onStart();
	}
	private void init() {
		initView();
		initData();
		initListener();
	}
	private void initView() {
		sharemss = (ImageView) findViewById(R.id.message_detail_share);
		content = (TextView) findViewById(R.id.message_details_content);
		filter = (TextView) findViewById(R.id.message_details_filter);
		title = (TextView) findViewById(R.id.message_details_title);
		time = (TextView) findViewById(R.id.message_details_time);
		mImageView = (ImageView) findViewById(R.id.message_details_image);
	}
	@SuppressLint("SimpleDateFormat")
	private void initData() {
		msg = (Message) getIntent().getSerializableExtra("msg");
		content.setText(msg.getDetails());
		filter.setText(msg.getmType());
		title.setText(msg.getTitle());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		sdf.format(new Date(msg.getReceiveTime()));
		time.setText(sdf.format(new Date(msg.getReceiveTime())));
		String imgurl = msg.getPicUrl();
		System.out.println("消息的图片路径，，，" + imgurl);
		super.getImageBitmap(mImageView, imgurl, null);
	}
	private void initListener() {
		sharemss.setOnClickListener(new ShareBtnListener());
	}
	private byte[] Bitmap2Bytes(Bitmap bm) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		bm.compress(Bitmap.CompressFormat.PNG, 100, baos);
		return baos.toByteArray();
	}
	@Override
	protected void onDestroy() {
		super.onDestroy();
		System.gc();
	}

	/**
	 * 详细信息分析按钮监听器@author Leo
	 */
	class ShareBtnListener implements OnClickListener {
		@Override
		public void onClick(View v) {
			if (mFactory != null) {
				ImageCacheManager IC = mFactory.getmImageCacheManager();
				Bitmap bm = null;
				try {
					bm = IC.downlaodImage(new URL(msg.getPicUrl()));
				} catch (MalformedURLException e) {
					e.printStackTrace();
				}
				if (null == bm) {
					Intent intent = new Intent(Intent.ACTION_SEND);
//					intent.setType("image/*");
					
					intent.setType("text/plain");
					intent.putExtra(Intent.EXTRA_SUBJECT, "分享:" + msg.getTitle());
					intent.putExtra(Intent.EXTRA_TEXT, msg.getTitle() + msg.getDetails());
					intent.putExtra("sms_body", msg.getTitle() + msg.getDetails());
					intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
					
					Log.d(TAG, "result: " + "分享:" + msg.getTitle());
					Log.d(TAG, "result: " + "分享:" +  msg.getTitle() + msg.getDetails());
					
					startActivity(intent);
				} else {
					byte[] buffer = Bitmap2Bytes(bm);
					FlashGetUtil mFlashGetUtil = new FlashGetUtil();
					mFlashGetUtil.downFile(buffer, "MA/", msg.getId() + ".jpg");
					Intent intent = new Intent(Intent.ACTION_SEND);
					intent.setType("image/*");
					intent.putExtra(Intent.EXTRA_SUBJECT, "分享:" + msg.getTitle());
					intent.putExtra(Intent.EXTRA_TEXT, msg.getTitle() + msg.getDetails());
					String url = "file:" + Environment.getExternalStorageDirectory() + "/MA/" + msg.getId() + ".jpg";
					System.out.println(url);
					Log.d(TAG, "result: " + url);
					
					intent.putExtra(Intent.EXTRA_STREAM, Uri.parse(url));
					intent.putExtra("sms_body", msg.getTitle() + msg.getDetails());
					intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
					startActivity(intent);
				}
			}
		}
	}
}
