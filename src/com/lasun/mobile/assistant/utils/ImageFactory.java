package com.lasun.mobile.assistant.utils;

import java.lang.ref.SoftReference;
import java.net.URL;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.WeakHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import android.app.Activity;
import android.graphics.Bitmap;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import com.lasun.moblile.assistant.core.CenterController;
import com.lasun.moblile.assistant.core.ShareCache;
import com.superbearman6.imagecachetatics.ImageCacheManager;

public class ImageFactory {
	private static ImageFactory self;
	private FactoryCache factoryCache;
	private ShareCache shareCache;
	private Map<View, String> imageViews = Collections.synchronizedMap(new WeakHashMap<View, String>());
	private ExecutorService threadpool = Executors.newFixedThreadPool(1);
	private ImageCacheManager mImageCacheManager;
	private MethodforSetImageBitmap method;
	private Activity currentactivity;
	public ImageFactory(CenterController centercontroller, Activity context) {
		photoLoaderThread.setPriority(Thread.NORM_PRIORITY - 1);
		shareCache = centercontroller.getShareCache();
		factoryCache = new FactoryCache();
		currentactivity = context;
		mImageCacheManager = ImageCacheManager.getImageCacheService(currentactivity, ImageCacheManager.MODE_FIXED_MEMORY_USED, "memory");
		mImageCacheManager.setMax_Memory(512 * 1024 * 1024);
	}
	public ImageCacheManager getmImageCacheManager() {
		return mImageCacheManager;
	}
	public void downloadBitMap(String url, View view, MethodforSetImageBitmap methodforSetImageBitmap) {
		method = methodforSetImageBitmap;
		imageViews.put(view, url);
		Bitmap bitmap = factoryCache.get(url);
		Animation anim = AnimationUtils.loadAnimation(currentactivity, android.R.anim.fade_in);
		view.setAnimation(anim);
		if (bitmap != null && method != null)
			method.setImageBitmap(bitmap, view);
		else {
			if (method != null)
				method.setDefaultImageBitmap(view);
			addPhotoQueue(url, view);
		}
	}

	public interface MethodforSetImageBitmap {
		void setImageBitmap(Bitmap bm, View view);
		void setDefaultImageBitmap(View view);
	}
	private void addPhotoQueue(String url, View view) {
		imageStack.Clean(view);
		Block b = new Block(url, view);
		synchronized (imageStack.mStack) {
			imageStack.mStack.push(b);
			imageStack.mStack.notifyAll();
		}
		threadpool.submit(photoLoaderThread);
	}
	private Bitmap getBitmap(Block b) {
		Bitmap bm = null;
		String key = b.url;
		try {
			bm = mImageCacheManager.downlaodImage(new URL(b.url));
			factoryCache.put(key, bm);
			return bm;
		} catch (Exception e) {
			return null;
		}
	}

	private class Block {
		public String url;
		public View view;
		public Block(String u, View v) {
			url = u;
			view = v;
		}
	}
	ImageStack imageStack = new ImageStack();
	public void stopThread() {
		photoLoaderThread.interrupt();
	}

	class ImageStack {
		private Stack<Block> mStack = new Stack<Block>();
		public void Clean(View image) {
			for (int j = 0; j < mStack.size();) {
				if (mStack.get(j).view == image)
					mStack.remove(j);
				else
					++j;
			}
		}
	}

	class ImageLoaderThread extends Thread {
		public void run() {
			try {
				while (true) {
					if (imageStack.mStack.size() == 0)
						synchronized (imageStack.mStack) {
							imageStack.mStack.wait();
						}
					if (imageStack.mStack.size() != 0) {
						Block block;
						synchronized (imageStack.mStack) {
							block = imageStack.mStack.pop();
						}
						Bitmap bmp = getBitmap(block);
						String tag = imageViews.get(block.view);
						if (tag != null && tag.equals(block.url)) {
							BitmapDisplayer bd = new BitmapDisplayer(bmp, block.view);
							Activity a = (Activity) block.view.getContext();
							a.runOnUiThread(bd);
						}
					}
				}
			} catch (InterruptedException e) {
			}
		}
	}
	ImageLoaderThread photoLoaderThread = new ImageLoaderThread();

	class BitmapDisplayer implements Runnable {
		Bitmap bitmap;
		View view;
		public BitmapDisplayer(Bitmap b, View i) {
			bitmap = b;
			view = i;
		}
		public void run() {
			if (bitmap != null && method != null)
				method.setImageBitmap(bitmap, view);
		}
	}

	class FactoryCache {
		private HashMap<String, SoftReference<Bitmap>> cache = new HashMap<String, SoftReference<Bitmap>>();
		public Bitmap get(String id) {
			if (!cache.containsKey(id))
				return null;
			SoftReference<Bitmap> ref = cache.get(id);
			return ref.get();
		}
		public void put(String id, Bitmap bitmap) {
			cache.put(id, new SoftReference<Bitmap>(bitmap));
		}
		public void clear() {
			cache.clear();
		}
	}
	public void clearFactory() {
		factoryCache.clear();
	}
	public static ImageFactory getImageFactory(CenterController centercontroller, Activity activity) {
		if (self == null)
			self = new ImageFactory(centercontroller, activity);
		return self;
	}
}
