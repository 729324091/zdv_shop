package com.zdv.shop.model.po;

import java.sql.Date;

import com.zdv.shop.model.Message;

public class MessageAndSendNameVo {
	private String uopname;
	
	private String messageid;
	
	private String opuserid;
	
	private String userid;
	
	private String infoid;
	
	private String content;
	
	private String ifread;
	
	private Date createdate;	

	public String getUopname() {
		return uopname;
	}

	public void setUopname(String uopname) {
		this.uopname = uopname;
	}

	public String getMessageid() {
		return messageid;
	}

	public void setMessageid(String messageid) {
		this.messageid = messageid;
	}

	public String getOpuserid() {
		return opuserid;
	}

	public void setOpuserid(String opuserid) {
		this.opuserid = opuserid;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getInfoid() {
		return infoid;
	}

	public void setInfoid(String infoid) {
		this.infoid = infoid;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getIfread() {
		return ifread;
	}

	public void setIfread(String ifread) {
		this.ifread = ifread;
	}

	public Date getCreatedate() {
		return createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

	
}
