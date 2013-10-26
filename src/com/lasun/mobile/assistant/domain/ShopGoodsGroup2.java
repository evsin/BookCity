package com.lasun.mobile.assistant.domain;

import java.io.Serializable;
import java.util.List;

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
public class ShopGoodsGroup2 implements Serializable {

	private ShopGoodsGroup shopgoodsgroup;
	private List<GoodsBaseColor> colorandids;

	public ShopGoodsGroup getShopgoodsgroup() {
		return shopgoodsgroup;
	}

	public void setShopgoodsgroup(ShopGoodsGroup shopgoodsgroup) {
		this.shopgoodsgroup = shopgoodsgroup;
	}

	public List<GoodsBaseColor> getColorandids() {
		return colorandids;
	}

	public void setColorandids(List<GoodsBaseColor> colorandids) {
		this.colorandids = colorandids;
	}

}
