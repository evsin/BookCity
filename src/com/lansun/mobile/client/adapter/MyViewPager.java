package com.lansun.mobile.client.adapter;

import com.lasun.mobile.assistant.domain.HotGoods;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.accessibility.AccessibilityEvent;

public class MyViewPager extends ViewPager {

	public MyViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	@Override
	public boolean onInterceptTouchEvent(MotionEvent event) {
		if(HotGoods.isGalleryMove){
			return false;
		}
		return super.onInterceptTouchEvent(event);
	}


}
