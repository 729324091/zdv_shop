package com.zdv.shop.weixin.message;

import java.util.Date;
/**
 * 
 * @Title:MassMessage
 * @Description: 群发消息
 * @author Administrator
 */
public class MassMessage {

	private String msgid;
	private String msgtype;
	private String mediaid;
	private String content;
	private String msgdataid;	//该字段只有在群发图文消息时，才会出现
	private Date createtime;
	
	
	public String getMsgid() {
		return msgid;
	}
	public void setMsgid(String msgid) {
		this.msgid = msgid;
	}
	public String getMsgtype() {
		return msgtype;
	}
	public void setMsgtype(String msgtype) {
		this.msgtype = msgtype;
	}
	public String getMediaid() {
		return mediaid;
	}
	public void setMediaid(String mediaid) {
		this.mediaid = mediaid;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getMsgdataid() {
		return msgdataid;
	}
	public void setMsgdataid(String msgdataid) {
		this.msgdataid = msgdataid;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	
	
}
