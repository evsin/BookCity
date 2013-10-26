package com.lasun.mobile.assistant.domain;

import java.io.Serializable;

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
public class GoodsBaseColor implements Serializable {

		private String base_color;
		private String b2b_id; 
		private String goods_id; 
		private String corporation_id; 

		public String getBase_color() {
			return base_color;
		}
		public void setBase_color(String base_color) {
			this.base_color = base_color;
		}
		public String getB2b_id() {
			return b2b_id;
		}
		public void setB2b_id(String b2b_id) {
			this.b2b_id = b2b_id;
		}
		public String getGoods_id() {
			return goods_id;
		}
		public void setGoods_id(String goods_id) {
			this.goods_id = goods_id;
		}
		public String getCorporation_id() {
			return corporation_id;
		}
		public void setCorporation_id(String corporation_id) {
			this.corporation_id = corporation_id;
		}

}
