package com.lansun.mobile.client.adapter;

import com.lasun.mobile.assistant.domain.HotGoods;

import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.widget.Gallery;

public class MyGallery extends Gallery {

	public MyGallery(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	private boolean isScrollingLeft(MotionEvent e1,MotionEvent e2){
		return e2.getX()>e1.getX();
		
	}
	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY) {
		int keyCode;
		if(isScrollingLeft(e1, e2)){
			keyCode = KeyEvent.KEYCODE_CTRL_LEFT;
		}else{
			keyCode = KeyEvent.KEYCODE_CTRL_RIGHT;
		}
		onKeyDown(keyCode, null);
		return true;
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		super.onTouchEvent(event);
		int action=event.getAction();
		switch (action) {
		case MotionEvent.ACTION_DOWN:
			HotGoods.isGalleryMove=true;
			return true;
		case MotionEvent.ACTION_MOVE:
			return true;
		case MotionEvent.ACTION_UP:
			HotGoods.isGalleryMove=false;
		case MotionEvent.ACTION_CANCEL:
			HotGoods.isGalleryMove=false;
			return true;
		}
		return super.onTouchEvent(event);
	}
}
