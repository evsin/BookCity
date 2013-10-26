package com.lasun.mobile.assistant.domain;

import java.io.Serializable;

import android.net.NetworkInfo.State;

import com.lasun.moblile.assistant.push.Message;

/**
 * 
 * <pre>
 * 业务名:
 * 功能说明: 
 *    商品信息mode 类
 * 编写日期:
 * 作者:	
 * 
 * 
 * 历史记录
 * 1、修改日期：
 *    修改人：
 *    修改内容：
 * </pre>
 */
public class LocalMessage {

	public static String VIRGIN = "VIRGIN";
	public static String USED = "USED";

	private Message message;
	private String state = USED;

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message mMessage) {
		this.message = mMessage;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

}
