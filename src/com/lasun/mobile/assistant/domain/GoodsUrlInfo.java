package com.lasun.mobile.assistant.domain;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * <pre>
 * 业务名:
 * 功能说明: 
 *    商地址信息 类
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
public class GoodsUrlInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1101817293886919928L;
	private List<String> picUrl;
	private List<String> videoUrl;
	private List<String> docUrl;

	public List<String> getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(List<String> picUrl) {
		this.picUrl = picUrl;
	}

	public List<String> getVideoUrl() {
		return videoUrl;
	}

	public void setVideoUrl(List<String> videoUrl) {
		this.videoUrl = videoUrl;
	}

	public List<String> getDocUrl() {
		return docUrl;
	}

	public void setDocUrl(List<String> docUrl) {
		this.docUrl = docUrl;
	}

	@Override
	public String toString() {
		return "GoodsUrlInfo [picUrl=" + picUrl + ", videoUrl=" + videoUrl
				+ ", docUrl=" + docUrl + "]";
	}

}
