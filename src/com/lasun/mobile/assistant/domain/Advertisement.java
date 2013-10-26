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
public class Advertisement implements Serializable {

	private String id;
	private String mediaType;
	private String adName;
	private String adImg1;
	private String adImg2;
	private String adLink;
	private String adCode;
	private String startTime;
	private String endTime;
	private String linkMan;
	private String linkEmail;
	private String linkPhone;
	private String clickCount;
	private String enabled;
	private String seqNo;
	private String showLevel;
	private String imagesid;
	private String imagesid1;
	private String adNote;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMediaType() {
		return mediaType;
	}

	public void setMediaType(String mediaType) {
		this.mediaType = mediaType;
	}

	public String getAdName() {
		return adName;
	}

	public void setAdName(String adName) {
		this.adName = adName;
	}

	public String getAdImg1() {
		return adImg1;
	}

	public void setAdImg1(String adImg1) {
		this.adImg1 = adImg1;
	}

	public String getAdImg2() {
		return adImg2;
	}

	public void setAdImg2(String adImg2) {
		this.adImg2 = adImg2;
	}

	public String getAdLink() {
		return adLink;
	}

	public void setAdLink(String adLink) {
		this.adLink = adLink;
	}

	public String getAdCode() {
		return adCode;
	}

	public void setAdCode(String adCode) {
		this.adCode = adCode;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getLinkMan() {
		return linkMan;
	}

	public void setLinkMan(String linkMan) {
		this.linkMan = linkMan;
	}

	public String getLinkEmail() {
		return linkEmail;
	}

	public void setLinkEmail(String linkEmail) {
		this.linkEmail = linkEmail;
	}

	public String getLinkPhone() {
		return linkPhone;
	}

	public void setLinkPhone(String linkPhone) {
		this.linkPhone = linkPhone;
	}

	public String getClickCount() {
		return clickCount;
	}

	public void setClickCount(String clickCount) {
		this.clickCount = clickCount;
	}

	public String getEnabled() {
		return enabled;
	}

	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}

	public String getSeqNo() {
		return seqNo;
	}

	public void setSeqNo(String seqNo) {
		this.seqNo = seqNo;
	}

	public String getShowLevel() {
		return showLevel;
	}

	public void setShowLevel(String showLevel) {
		this.showLevel = showLevel;
	}

	public String getImagesid() {
		return imagesid;
	}

	public void setImagesid(String imagesid) {
		this.imagesid = imagesid;
	}

	public String getImagesid1() {
		return imagesid1;
	}

	public void setImagesid1(String imagesid1) {
		this.imagesid1 = imagesid1;
	}

	public String getAdNote() {
		return adNote;
	}

	public void setAdNote(String adNote) {
		this.adNote = adNote;
	}

}
