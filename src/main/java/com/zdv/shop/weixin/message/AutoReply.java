package com.zdv.shop.weixin.message;

import java.util.Date;

/**
 * 
 * @Title:AutoReply
 * @Description:自动回复消息设置bean
 */
public class AutoReply {

	private String autoreplyid;
	private String ucustomerid;
	private String appId;
	private String foreignkey;
	private String replyType;
	private String msgType;
	private String content;
	private String mediaId;
	private String title;
	private String description;
	private Date createTime;
	

	public String getAutoreplyid() {
		return autoreplyid;
	}
	public void setAutoreplyid(String autoreplyid) {
		this.autoreplyid = autoreplyid;
	}

	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getForeignkey() {
		return foreignkey;
	}
	public void setForeignkey(String foreignkey) {
		this.foreignkey = foreignkey;
	}
	public String getReplyType() {
		return replyType;
	}
	public void setReplyType(String replyType) {
		this.replyType = replyType;
	}
	public String getMsgType() {
		return msgType;
	}
	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getMediaId() {
		return mediaId;
	}
	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getUcustomerid() {
		return ucustomerid;
	}
	public void setUcustomerid(String ucustomerid) {
		this.ucustomerid = ucustomerid;
	}
	
}
