package com.zdv.shop.model;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * 短信发送主表
 */
@Table(name = "OA_SmsSendMaster")
public class OaSmsSendMaster {

		/**
		 * 主键
		 */
		@Id
		@GeneratedValue(generator="UUID")	
		private Integer usmsSendid;			//'发送单编号',
		private String usmsType;				//'短信分类 可变代码 01：招聘短信 02：生日祝福短信 03：节日祝福短信 04：其他短信',
		private String usmsPersonType;	//'短信人员类型 01：外部人员短信 02：内部具体人员短信(允许挑选有手机号码的客户联系人) 03：内部人员范围短信',
		private String usmsSendType;		//'短信发送类型 01：立即发送 02：定时发送',
		private Date usmsSendTimer;		//'指定的发送时间 当“短信发送类型”为“02：定时短信”时，必须指定发送时间',
		private String uremark;					//'备注',
		private String umtstate;				//'当前状态',
		private String ustepname;				//'当前步骤名称',
		private String uflid;						//'审批编号',
		private String uoperater;				//'操作人员',
		private Date uoperatedate;			//'操作时间',
		private String ueflag;						//'有效标志'
		public Integer getUsmsSendid() {
			return usmsSendid;
		}
		public void setUsmsSendid(Integer usmsSendid) {
			this.usmsSendid = usmsSendid;
		}
		public String getUsmsType() {
			return usmsType;
		}
		public void setUsmsType(String usmsType) {
			this.usmsType = usmsType;
		}
		public String getUsmsPersonType() {
			return usmsPersonType;
		}
		public void setUsmsPersonType(String usmsPersonType) {
			this.usmsPersonType = usmsPersonType;
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
		public String getUremark() {
			return uremark;
		}
		public void setUremark(String uremark) {
			this.uremark = uremark;
		}
		public String getUmtstate() {
			return umtstate;
		}
		public void setUmtstate(String umtstate) {
			this.umtstate = umtstate;
		}
		public String getUstepname() {
			return ustepname;
		}
		public void setUstepname(String ustepname) {
			this.ustepname = ustepname;
		}
		public String getUflid() {
			return uflid;
		}
		public void setUflid(String uflid) {
			this.uflid = uflid;
		}
		public String getUoperater() {
			return uoperater;
		}
		public void setUoperater(String uoperater) {
			this.uoperater = uoperater;
		}
		public Date getUoperatedate() {
			return uoperatedate;
		}
		public void setUoperatedate(Date uoperatedate) {
			this.uoperatedate = uoperatedate;
		}
		public String getUeflag() {
			return ueflag;
		}
		public void setUeflag(String ueflag) {
			this.ueflag = ueflag;
		}
	
		
}
