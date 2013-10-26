package com.lasun.mobile.client.broadcast;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;

public class PhoneStateReceiver extends BroadcastReceiver {
	private Handler mHandler;
	
	public PhoneStateReceiver (Handler Handler){
		mHandler = Handler;
	}
	@Override
	public void onReceive(Context context, Intent intent) {
		Message message = null;
		if (intent.getAction().equals(Intent.ACTION_NEW_OUTGOING_CALL)) {
			message = new Message();
			message.what = 14;
			mHandler.sendMessage(message);
		} else {
			message = new Message();
			message.what = 10;
			mHandler.sendMessage(message);
			TelephonyManager tm = (TelephonyManager) context
					.getSystemService(Service.TELEPHONY_SERVICE);
			tm.listen(listener, PhoneStateListener.LISTEN_CALL_STATE);
		}
	}

	PhoneStateListener listener = new PhoneStateListener() {
		public void onCallStateChanged(int state, String incomingNumber) {
			super.onCallStateChanged(state, incomingNumber);
			Message message = null;
			switch (state) {
			case TelephonyManager.CALL_STATE_IDLE:
				message = new Message();
				message.what = 11;
				mHandler.sendMessage(message);
				break;
			case TelephonyManager.CALL_STATE_OFFHOOK:
				message = new Message();
				message.what = 12;
				mHandler.sendMessage(message);
				break;
			case TelephonyManager.CALL_STATE_RINGING:
				message = new Message();
				message.what = 13;
				mHandler.sendMessage(message);
				break;
			}
		};
	};

}
