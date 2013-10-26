package com.lasun.moblile.assistant.push;

import java.io.Serializable;

/**
 * @author Administrator
 * 
 */
public class Message implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3750263319752747871L;
	private String mType;
	private String title; 
	private String details; 
	private String detailsText; 
	private String picUrl; 
	private boolean pushForce; 
	private String detId; 
	private String mLoad; 
	private String createTime; 
	private String id; 
	private String searchID;
	private String mWord; 
	private String type;
	private long receiveTime;
	private int newFlag;

	public int getNewFlag() {
		return newFlag;
	}

	public void setNewFlag(int newFlag) {
		this.newFlag = newFlag;
	}

	public String getmType() {
		return mType;
	}

	public void setmType(String mType) {
		this.mType = mType;
	}

	public long getReceiveTime() {
		return receiveTime;
	}

	public void setReceiveTime(long receiveTime) {
		this.receiveTime = receiveTime;
	}

	public String getSearchID() {
		return searchID;
	}

	public void setSearchID(String searchID) {
		this.searchID = searchID;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSearchId() {
		return searchID;
	}

	public void setSearchId(String searchId) {
		this.searchID = searchId;
	}

	public String getmWord() {
		return mWord;
	}

	public void setmWord(String mWord) {
		this.mWord = mWord;
	}

	private int code; 
	private String status; 
	private String jobTime;

	public String getJobTime() {
		return jobTime;
	}

	public void setJobTime(String jobTime) {
		this.jobTime = jobTime;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public String getDetailsText() {
		return detailsText;
	}

	public void setDetailsText(String detailsText) {
		this.detailsText = detailsText;
	}

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public boolean isPushForce() {
		return pushForce;
	}

	public void setPushForce(boolean pushForce) {
		this.pushForce = pushForce;
	}

	public String getDetId() {
		return detId;
	}

	public void setDetId(String detId) {
		this.detId = detId;
	}

	public String getmLoad() {
		return mLoad;
	}

	public void setmLoad(String mLoad) {
		this.mLoad = mLoad;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Message [mType=" + mType + ", title=" + title + ", details="
				+ details + ", detailsText=" + detailsText + ", picUrl="
				+ picUrl + ", pushForce=" + pushForce + ", detId=" + detId
				+ ", mLoad=" + mLoad + ", createTime=" + createTime + ", id="
				+ id + ", searchID=" + searchID + ", mWord=" + mWord
				+ ", type=" + type + ", receiveTime=" + receiveTime + ", code="
				+ code + ", status=" + status + ", jobTime=" + jobTime + "]";
	}

}
