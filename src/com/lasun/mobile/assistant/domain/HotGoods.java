package com.lasun.mobile.assistant.domain;

public class HotGoods {
	public static boolean isGalleryMove;
	
	public boolean isAd;
	public ShopGoodsGroup goods;
	public ShopAdsGroup ads;
	public HotGoods(ShopGoodsGroup goods) {
		isAd=false;
		this.goods=goods;
	}
	public HotGoods(ShopAdsGroup ads) {
		isAd=true;
		this.ads=ads;
	}
}
