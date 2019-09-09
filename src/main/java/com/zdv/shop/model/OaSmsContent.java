package com.zdv.shop.model;

import java.util.Date;

import javax.persistence.Table;

/**
 * 短信内容表
 */
@Table(name = "OA_SmsContent")
public class OaSmsContent {
	
	
		private Integer usmsSendid;			//'发送单编号',
		private Integer usmsid;					//'短信编号',
		private String usmsType;				//'短信分类 可变代码 01：注册验证短息 02：业务短息',
		private String usmsSendType;		//'短信发送类型 01：立即发送 02：定时发送',
		private Date usmsSendTimer;		//'指定的发送时间 当“短信发送类型”为“02：定时短信”时，必须指定发送时间',
		private String ucorpid;					//'客户编号 冗余字段，便于后台查询',
		private String ugareenno;				//'入职编号 冗余字段，便于后台查询',
		private String uname;					//'姓名',
		private String umobile;					//'手机号码',
		private String ucontent;				//'短信内容',
		private String ueflag;						//'有效标志 默认为“01”',
		private String uCustomerid;		//'商户号',
		private String usmsSendStatus;	    //'发送状态 01：数据保存 02：待发送 03：已发送至短信平台 04：已反馈结果',
		private String usmsBatchid;			//'发送批次号 window服务定时发送时生成的批次号（默认为‘’）',
		private Date usendtime;				//'实际发送时间 发送完后更新',
		private String usuccess;				//'发送结果 0：待发送 1：发送成功 2：发送失败',
		private String ufailReason;			//'失败原因',
		private int unum;							//'实际发送条数'
		public Integer getUsmsSendid() {
			return usmsSendid;
		}
		public void setUsmsSendid(Integer usmsSendid) {
			this.usmsSendid = usmsSendid;
		}
		public Integer getUsmsid() {
			return usmsid;
		}
		public void setUsmsid(Integer usmsid) {
			this.usmsid = usmsid;
		}
		public String getUsmsType() {
			return usmsType;
		}
		public void setUsmsType(String usmsType) {
			this.usmsType = usmsType;
		}
		public String getUsmsSendType() {
			return usmsSendType;
		}
		public void setUsmsSendType(String usmsSendType) {
			this.usmsSendType = usmsSendType;
		}
		public Date getUsmsSendTimer() {
			return usmsSendTimer;
		}
		public void setUsmsSendTimer(Date usmsSendTimer) {
			this.usmsSendTimer = usmsSendTimer;
		}
		public String getUcorpid() {
			return ucorpid;
		}
		public void setUcorpid(String ucorpid) {
			this.ucorpid = ucorpid;
		}
		public String getUgareenno() {
			return ugareenno;
		}
		public void setUgareenno(String ugareenno) {
			this.ugareenno = ugareenno;
		}
		public String getUname() {
			return uname;
		}
		public void setUname(String uname) {
			this.uname = uname;
		}
		public String getUmobile() {
			return umobile;
		}
		public void setUmobile(String umobile) {
			this.umobile = umobile;
		}
		public String getUcontent() {
			return ucontent;
		}
		public void setUcontent(String ucontent) {
			this.ucontent = ucontent;
		}
		public String getUeflag() {
			return ueflag;
		}
		public void setUeflag(String ueflag) {
			this.ueflag = ueflag;
		}
		
		public String getuCustomerid() {
			return uCustomerid;
		}
		public void setuCustomerid(String uCustomerid) {
			this.uCustomerid = uCustomerid;
		}

		public String getUsmsSendStatus() {
			return usmsSendStatus;
		}
		public void setUsmsSendStatus(String usmsSendStatus) {
			this.usmsSendStatus = usmsSendStatus;
		}
		public String getUsmsBatchid() {
			return usmsBatchid;
		}
		public void setUsmsBatchid(String usmsBatchid) {
			this.usmsBatchid = usmsBatchid;
		}
		public Date getUsendtime() {
			return usendtime;
		}
		public void setUsendtime(Date usendtime) {
			this.usendtime = usendtime;
		}
		public String getUsuccess() {
			return usuccess;
		}
		public void setUsuccess(String usuccess) {
			this.usuccess = usuccess;
		}
		public String getUfailReason() {
			return ufailReason;
		}
		public void setUfailReason(String ufailReason) {
			this.ufailReason = ufailReason;
		}
		public int getUnum() {
			return unum;
		}
		public void setUnum(int unum) {
			this.unum = unum;
		}
	
		
		
}
