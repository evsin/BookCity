package com.lansun.mobile.client.adapter;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import com.google.gson.Gson;
import com.lansun.mobile.assistant.constant.MyConstant;
import com.lansun.mobile.assistant.notifydownload.DocDownloadService;
import com.lansun.mobile.assistant.notifydownload.GlobalUtils;
import com.lasun.mobile.assistant.domain.Advertisement;
import com.lasun.mobile.assistant.domain.Goods;
import com.lasun.mobile.assistant.domain.GoodsBaseColor;
import com.lasun.mobile.assistant.domain.GoodsDealer;
import com.lasun.mobile.assistant.domain.GoodsUrlInfo;
import com.lasun.mobile.assistant.domain.HotGoods;
import com.lasun.mobile.assistant.domain.ShopAd;
import com.lasun.mobile.assistant.domain.ShopAdsGroup;
import com.lasun.mobile.assistant.domain.ShopGoodsGroup;
import com.lasun.mobile.assistant.domain.ShopGoodsGroup2;
import com.lasun.mobile.assistant.domain.UserB2b;
import com.lasun.mobile.assistant.domain.UserGroup;
import com.lasun.mobile.assistant.domain.VipUser;
import com.lasun.mobile.assistant.net.service.APIService;
import com.lasun.mobile.assistant.net.service.AdvertisementService;
import com.lasun.mobile.assistant.net.service.APIService.NetExceptionCallBack;
import com.lasun.mobile.assistant.net.service.GoodsService;
import com.lasun.mobile.assistant.utils.APIUtils;
import com.lasun.mobile.assistant.utils.LoginHelper;
import com.lasun.mobile.assistant.utils.VideoUtil;
import com.lasun.mobile.client.assistant.activity.GoodsDetailsActivity;
import com.lasun.mobile.client.assistant.activity.LoginActivity;
import com.lasun.mobile.client.assistant.activity.MainActivity2;
import com.lasun.mobile.client.assistant.activity.MenuActivity;
import com.lasun.mobile.client.assistant.activity.R;
import com.lasun.mobile.client.assistant.activity.SaveAccountActivity;
import com.lasun.moblile.assistant.core.CenterController;
import com.lasun.moblile.assistant.core.Parameter;
import com.lasun.moblile.assistant.core.ShareCache;
import com.lasun.moblile.assistant.push.GsonUtile;

/**
 * 热销单品Listview adapter
 * */
public class HotListAdapter extends BaseAdapter {
	public static final int CHANGE_GALLERY = 1;
	private List<HotGoods> data;
	private LayoutInflater mInflater;
	private MenuActivity mContext;
	private GoodsUrlInfo urls;
	private Gallery mGallery;
	private boolean isGalleryHandler = true;
	private int visiblecount = 7;
	private List<ShopGoodsGroup2> data_goods;
	public HotListAdapter(MenuActivity context) {
		mInflater = LayoutInflater.from(context);
		mContext = context;
	}
	/**
	 * 得到广告、listitem混合数据列表
	 * 
	 * @param ads 广告列表对象
	 * @param goods listitem数据列表
	 * @return 返回混合列表
	 */
	public List<HotGoods> getHotList(ShopAdsGroup ads, List<ShopGoodsGroup> goods) {
		List<HotGoods> hotGoods = new ArrayList<HotGoods>();
		hotGoods.add(new HotGoods(ads));
		if (goods != null && goods.size() > 0) {
			for (ShopGoodsGroup good : goods) {
				HotGoods hot = new HotGoods(good);
				hotGoods.add(hot);
			}
			return hotGoods;
		} else {
			return null;
		}
	}
	public void setHotData(List<HotGoods> data) {
		this.data = data;
		notifyDataSetChanged();
	}
	public int getVisiblecount() {
		return visiblecount;
	}
	public void setVisiblecount(int visiblecount) {
		this.visiblecount = visiblecount;
	}
	public List<HotGoods> getHotData() {
		return this.data;
	}
	@Override
	public int getCount() {
		if (this.data == null)
			return 0;
		return this.data.size();
	}
	@Override
	public HotGoods getItem(int i) {
		if (data == null) {
			return null;
		}
		return data.get(i);
	}
	@Override
	public long getItemId(int i) {
		return i;
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		HotGoods item = getItem(position);
		if (item != null && item.isAd) {
			ViewHolder holder = null;
			if (convertView == null || !((ViewHolder) convertView.getTag()).isAd) {
				convertView = mInflater.inflate(R.layout.hot_goods_listview_item_ad, null);
				holder = new ViewHolder();
				holder.gallery = (MyGallery) convertView.findViewById(R.id.gallery);
				mGallery = holder.gallery;
				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}
			holder.isAd = true;
			new ShopAdListAsyn().execute();
		} else {
			ViewHolder holder = null;
			if (convertView == null || ((ViewHolder) convertView.getTag()).isAd) {
				convertView = mInflater.inflate(R.layout.hot_goods_listview_item_new, null);
				holder = new ViewHolder();
				holder.pic = (ImageView) convertView.findViewById(R.id.hot_goods_listview_item_pic);
				holder.name = (TextView) convertView.findViewById(R.id.hot_goods_listview_item_name);
				holder.act = (TextView) convertView.findViewById(R.id.hot_goods_listview_item_act);
				holder.price = (TextView) convertView.findViewById(R.id.hot_goods_listview_item_price);
				holder.ratingBar = (RatingBar) convertView.findViewById(R.id.ratingbar);
				holder.ratingBar2 = (RatingBar) convertView.findViewById(R.id.ratingbar_2);
				holder.buy = (TextView) convertView.findViewById(R.id.hot_goods_listview_item_buy);
				holder.video = (ImageView) convertView.findViewById(R.id.hot_goods_listview_item_video);
				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}
			holder.isAd = false;
			if (item != null) {
				final ShopGoodsGroup shopGoodsGroup = item.goods;
				if (shopGoodsGroup != null) {
					setData(shopGoodsGroup, holder);
					setOnClickListener(convertView, shopGoodsGroup, holder, item);
				}
			}
		}
		return convertView;
	}

	class ViewHolder {
		boolean isAd;
		MyGallery gallery;
		ImageView pic;
		TextView name;
		TextView act;
		TextView price;
		RatingBar ratingBar;
		RatingBar ratingBar2;
		TextView buy;
		ImageView video;
	}
	private void setData(ShopGoodsGroup shopGoodsGroup, ViewHolder holder) {
		String imageUrl = APIUtils.getInstance().getValueByKey("hiCDMACommonBaseUrl") + shopGoodsGroup.getGoods_middle_img();
		String goodsName = shopGoodsGroup.getGoods_name();
		String base_color = shopGoodsGroup.getBase_color();
		String marketPrice = shopGoodsGroup.getMarket_price();
		String cheapPromite = shopGoodsGroup.getPromote_title1();
		String province = shopGoodsGroup.getP_rank();
		String loaclSale = shopGoodsGroup.getL_rank();
		setUseableSources(shopGoodsGroup, holder.video);
		if (imageUrl != null && !"".equals(imageUrl)) {
			holder.pic.setTag(imageUrl);
		}
		mContext.getImageBitmap(holder.pic);
		if (goodsName != null && !"".equals(goodsName)) {
			holder.name.setText(goodsName);
		}
		if (marketPrice != null && !"".equals(marketPrice)) {
			holder.price.setText(marketPrice);
		}
		if (cheapPromite != null && !"".equals(cheapPromite)) {
			holder.act.setText(cheapPromite);
		}
		if (province != null && !"".equals(province) && !"0".equals(province)) {
			holder.ratingBar.setRating(Float.parseFloat(province));
		} else {
			holder.ratingBar.setRating(1.0f);
		}
		if (loaclSale != null && !"".equals(loaclSale) && !"0".equals(loaclSale)) {
			holder.ratingBar2.setRating(Float.parseFloat(loaclSale));
		} else {
			holder.ratingBar2.setRating(1.0f);
		}
	}
	private void setOnClickListener(View convertView, final ShopGoodsGroup shopGoodsGroup, ViewHolder holder, final HotGoods item) {
		holder.video.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				GoodsUrlsAsyn asyn = new GoodsUrlsAsyn();
				asyn.execute(new String[] { shopGoodsGroup.getGoods_id() });
			}
		});
		holder.buy.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				GoodsDealerAsy ddd = new GoodsDealerAsy();
				ShopGoodsGroup2 s2 = new ShopGoodsGroup2();
				s2.setShopgoodsgroup(shopGoodsGroup);
				data_goods.add(s2);
				ddd.execute(new String[] { shopGoodsGroup.getGoods_name() });
			}
		});
		convertView.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(mContext, GoodsDetailsActivity.class);
				intent.putExtra("goodsId", shopGoodsGroup.getGoods_id());
				intent.putExtra("goodsName", shopGoodsGroup.getGoods_name());
				mContext.startActivity(intent);
			}
		});
	}
	private void setUseableSources(ShopGoodsGroup shopGoodsGroup, ImageView imageview) {
		if (shopGoodsGroup == null || imageview == null)
			return;
		GoodsUrlInfo urlinfo = shopGoodsGroup.getSources();
		if (urlinfo != null && urlinfo.getVideoUrl() != null && urlinfo.getVideoUrl().size() > 0) {
			imageview.setImageResource(R.drawable.hot_video);
		} else if (urlinfo != null && urlinfo.getDocUrl() != null && urlinfo.getDocUrl().size() > 0) {
			imageview.setImageResource(R.drawable.hot_txt);
		} else {
			imageview.setImageResource(R.drawable.hot_video_none);
		}
	}

	/**
	 * 获取商品urls
	 * 
	 * @author Administrator
	 * 
	 */
	private class GoodsUrlsAsyn extends AsyncTask<String, Void, GoodsUrlInfo> {
		@Override
		protected void onPreExecute() {
			mContext.showProgress(mContext);
		}
		@Override
		protected GoodsUrlInfo doInBackground(String... params) {
			urls = new GoodsService().getGoodsSource(params[0], mContext.getParameters(), mContext, new NetExceptionCallBack() {
				@Override
				public void netExceptionCallback(int flag) {
					switch (flag) {
					case APIService.RETRY:
						break;
					default:
						break;
					}
				}
			});
			return urls;
		}
		@Override
		protected void onPostExecute(GoodsUrlInfo result) {
			mContext.dismissProgress();
			if (result != null) {
				GoodsUrlInfo urlinfo = result;
				if (urlinfo != null && urlinfo.getVideoUrl() != null && urlinfo.getVideoUrl().size() > 0) {
					String videoUrl = APIUtils.getInstance().getValueByKey("hiCDMACommonBaseUrl") + "/" + result.getVideoUrl().get(0);
					if (videoUrl != null && !"".equals(videoUrl))
						new VideoUtil().playNetVideo(videoUrl, mContext);
					else
						Toast.makeText(mContext, "视频信息不完整！", Toast.LENGTH_SHORT).show();
				} else if (urlinfo != null && urlinfo.getDocUrl() != null && urlinfo.getDocUrl().size() > 0) {
					String docUrl = APIUtils.getInstance().getValueByKey("hiCDMACommonBaseUrl") + "/" + result.getDocUrl().get(0);
					String[] docNames = getUrlItem(result.getDocUrl());
					Toast.makeText(mContext, "执行下载任务", Toast.LENGTH_SHORT).show();
					Intent intent = new Intent(mContext, DocDownloadService.class);
					intent.putExtra(GlobalUtils.EXTRA_DOC_NAME, docNames[0]);
					intent.putExtra(GlobalUtils.EXTRA_DOC_URL, docUrl.replace(" ", "%20"));
					mContext.startService(intent);
				} else {
					Toast.makeText(mContext, "暂无相关信息！", Toast.LENGTH_SHORT).show();
				}
			} else
				Toast.makeText(mContext, "暂无相关信息！", Toast.LENGTH_SHORT).show();
		}
	}
	private String[] getUrlItem(List<String> str) {
		if (str != null && str.size() > 0) {
			String[] strs = new String[str.size()];
			for (int i = 0; i < str.size(); i++) {
				String s = str.get(i);
				strs[i] = s.substring(s.lastIndexOf("/") + 1, s.length());
			}
			return strs;
		} else {
			return null;
		}
	}
	public void goodsDetailColorChose(final List<GoodsBaseColor> gbcList) {
		LayoutInflater inflater = LayoutInflater.from(mContext);
		View updateView = inflater.inflate(R.layout.color_choose_item, null);
		ListView phoneColorList = (ListView) updateView.findViewById(R.id.phone_color);
		ColorListAdapter cla = new ColorListAdapter(mContext);
		if (gbcList != null && gbcList.size() > 0) {
			cla.setMsgData(gbcList);
			phoneColorList.setAdapter(cla);
		}
		Builder dialog = new AlertDialog.Builder(mContext);
		dialog.setTitle("请选择终端颜色");
		dialog.setView(updateView);
		dialog.create().show();
		phoneColorList.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				System.out.println("gbcList.get(arg2).getGoods_id() " + gbcList.get(arg2).getGoods_id());
				String userName = CenterController.getCenterController(mContext).getShareCache().getDatefromStore(SaveAccountActivity.CURRENT_USER_NAME_B2B);
				if (null == userName) {
					userName = "";
				}
				Intent intent = new Intent();
				intent.setData(Uri.parse(MyConstant.getPurchaseEntrance(userName, gbcList.get(arg2).getB2b_id())));
				intent.setAction(Intent.ACTION_VIEW);
				mContext.startActivity(intent);
			}
		});
	}

	class GoodsDealerAsy extends AsyncTask<String, Void, List<ShopGoodsGroup2>> {
		@Override
		protected List<ShopGoodsGroup2> doInBackground(String... params) {
			GoodsService mGoodsService = new GoodsService();
			List<GoodsBaseColor> GoodsBaseColors = mGoodsService.getColorAndIds(params[0], mContext.getParameters(), mContext, new NetExceptionCallBack() {
				@Override
				public void netExceptionCallback(int flag) {
					switch (flag) {
					case APIService.RETRY:
						break;
					default:
						break;
					}
				}
			});
			for (int i = 0; data_goods != null && i < data_goods.size(); i++) {
				data_goods.get(i).setColorandids(GoodsBaseColors);
			}
			return data_goods;
		}
		@Override
		protected void onPostExecute(List<ShopGoodsGroup2> result) {
			goodsDetailColorChose(result.size() > 0 ? result.get(0).getColorandids() : null);
		}
	}

	/**
	 * 获取广告位列表
	 * */
	private class ShopAdListAsyn extends AsyncTask<Void, Void, List<Advertisement>> {
		@Override
		protected List<Advertisement> doInBackground(Void... params) {
			AdvertisementService service = new AdvertisementService();
			List<Advertisement> ads = service.getAdsByPosition("300360", new ArrayList<Parameter>(), mContext, new NetExceptionCallBack() {
				@Override
				public void netExceptionCallback(int flag) {
				}
			});
			Log.d("TAG", ads + "");
			return ads;
		}
		@Override
		protected void onPostExecute(List<Advertisement> result) {
			if (result != null) {
				System.out.println(result.size());
				super.onPostExecute(result);
				final List<Advertisement> ads = result;
				mGallery.setAdapter(new BaseAdapter() {
					@Override
					public View getView(int position, View convertView, ViewGroup parent) {
						ImageView image = new ImageView(mContext);
						image.setScaleType(ScaleType.CENTER_CROP);
						String imageUrl = APIUtils.getInstance().getValueByKey("hiCDMACommonBaseUrl");
						mContext.getImageBitmap(image, imageUrl + ads.get(position % ads.size()).getAdImg1());
						image.setLayoutParams(new Gallery.LayoutParams(Gallery.LayoutParams.MATCH_PARENT, Gallery.LayoutParams.MATCH_PARENT));
						convertView = image;
						return convertView;
					}
					@Override
					public long getItemId(int position) {
						return position;
					}
					@Override
					public Advertisement getItem(int position) {
						return ads.get(position);
					}
					@Override
					public int getCount() {
						return Integer.MAX_VALUE;
					}
				});
				mGallery.setSelection(500, true);
				mGallery.onKeyDown(KeyEvent.KEYCODE_DPAD_RIGHT, null);
				if (isGalleryHandler) {
					new Thread(new Runnable() {
						@Override
						public void run() {
							while (true) {
								isGalleryHandler = false;
								mHandler.sendEmptyMessage(CHANGE_GALLERY);
								try {
									Thread.sleep(3000);
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
							}
						}
					}).start();
				}
			}
		}
	}
	private Handler mHandler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case CHANGE_GALLERY:
				mGallery.onKeyDown(KeyEvent.KEYCODE_DPAD_RIGHT, null);
				break;
			default:
				break;
			}
		};
	};
}
