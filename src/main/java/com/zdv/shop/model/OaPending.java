package com.zdv.shop.model;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 待办事宜信息表
 */
@Table(name = "OA_Pending")
public class OaPending {
		
		/**
		 * 主键
		 */
		@Id
		@GeneratedValue(generator="UUID")
		private Integer Uid;					//'待办事宜ID ',
		private String Updtype;				//'待办事宜类型 代码7002',
		private Integer Uflid;					//'审批编号',
		private Integer Uflowid;				//'流程编号',
		private Integer Ustepid;				//'步骤编号',
		private String Umdid;				//'模块编号 来自模块代码表(BSmoduleinfo)',
		private int Uopid;						//'操作步骤',
		private String Uoperatetype;		//'处理者类型',
		private String Ucorpids;				//'执行公司',
		private String Udeptnos;			//'执行部门编码',
		private String Uroleids;				//'角色编号',
		private String Ustaffid;				//'待办人员',
		private String Uattachstaffids;	//'附加执行人',
		private boolean UallowEntrust;//'允许委托',
		private String UTrustee;			//'被委托人员',
		private boolean UCountersign;	// '是否会签',
		private String Usddatetime;		//'发送时间',
		private String Uremark;				//'待办说明 Uflowid在表BSflowmaster中对应的Uplname + Umdname',
		private String Uprioritytype;		//'优先级别',
		private String Upriorityreason;	//'优先原因'
		private int Upressnum;				//'催办次数',
		private String Ucorpid;				//'发送公司',
		private String Udeptno;				//'发送部门',
		private String Usender;				//'发送人员',
		private String Ulaststep;			//'上一步骤',
		private boolean Uisreturn;		//'是否退回',
		private String Uenddatetime;		//'处理时间',
		private boolean Uopflag;			//'处理标志 0待办 1已办',
		private String Uovertimeflag;		//'超时处理标志 01 超时处理'
		
		public Integer getUid() {
			return Uid;
		}
		public void setUid(Integer uid) {
			Uid = uid;
		}
		public String getUpdtype() {
			return Updtype;
		}
		public void setUpdtype(String updtype) {
			Updtype = updtype;
		}
		public Integer getUflid() {
			return Uflid;
		}
		public void setUflid(Integer uflid) {
			Uflid = uflid;
		}
		public Integer getUflowid() {
			return Uflowid;
		}
		public void setUflowid(Integer uflowid) {
			Uflowid = uflowid;
		}
		public Integer getUstepid() {
			return Ustepid;
		}
		public void setUstepid(Integer ustepid) {
			Ustepid = ustepid;
		}
		public String getUmdid() {
			return Umdid;
		}
		public void setUmdid(String umdid) {
			Umdid = umdid;
		}
		public int getUopid() {
			return Uopid;
		}
		public void setUopid(int uopid) {
			Uopid = uopid;
		}
		public String getUoperatetype() {
			return Uoperatetype;
		}
		public void setUoperatetype(String uoperatetype) {
			Uoperatetype = uoperatetype;
		}
		public String getUcorpids() {
			return Ucorpids;
		}
		public void setUcorpids(String ucorpids) {
			Ucorpids = ucorpids;
		}
		public String getUdeptnos() {
			return Udeptnos;
		}
		public void setUdeptnos(String udeptnos) {
			Udeptnos = udeptnos;
		}
		public String getUroleids() {
			return Uroleids;
		}
		public void setUroleids(String uroleids) {
			Uroleids = uroleids;
		}
		public String getUstaffid() {
			return Ustaffid;
		}
		public void setUstaffid(String ustaffid) {
			Ustaffid = ustaffid;
		}
		public String getUattachstaffids() {
			return Uattachstaffids;
		}
		public void setUattachstaffids(String uattachstaffids) {
			Uattachstaffids = uattachstaffids;
		}
		public boolean isUallowEntrust() {
			return UallowEntrust;
		}
		public void setUallowEntrust(boolean uallowEntrust) {
			UallowEntrust = uallowEntrust;
		}
		public String getUTrustee() {
			return UTrustee;
		}
		public void setUTrustee(String uTrustee) {
			UTrustee = uTrustee;
		}
		public boolean isUCountersign() {
			return UCountersign;
		}
		public void setUCountersign(boolean uCountersign) {
			UCountersign = uCountersign;
		}
		public String getUsddatetime() {
			return Usddatetime;
		}
		public void setUsddatetime(String usddatetime) {
			Usddatetime = usddatetime;
		}
		public String getUremark() {
			return Uremark;
		}
		public void setUremark(String uremark) {
			Uremark = uremark;
		}
		public String getUprioritytype() {
			return Uprioritytype;
		}
		public void setUprioritytype(String uprioritytype) {
			Uprioritytype = uprioritytype;
		}
		public String getUpriorityreason() {
			return Upriorityreason;
		}
		public void setUpriorityreason(String upriorityreason) {
			Upriorityreason = upriorityreason;
		}
		public int getUpressnum() {
			return Upressnum;
		}
		public void setUpressnum(int upressnum) {
			Upressnum = upressnum;
		}
		public String getUcorpid() {
			return Ucorpid;
		}
		public void setUcorpid(String ucorpid) {
			Ucorpid = ucorpid;
		}
		public String getUdeptno() {
			return Udeptno;
		}
		public void setUdeptno(String udeptno) {
			Udeptno = udeptno;
		}
		public String getUsender() {
			return Usender;
		}
		public void setUsender(String usender) {
			Usender = usender;
		}
		public String getUlaststep() {
			return Ulaststep;
		}
		public void setUlaststep(String ulaststep) {
			Ulaststep = ulaststep;
		}
		public boolean isUisreturn() {
			return Uisreturn;
		}
		public void setUisreturn(boolean uisreturn) {
			Uisreturn = uisreturn;
		}
		public String getUenddatetime() {
			return Uenddatetime;
		}
		public void setUenddatetime(String uenddatetime) {
			Uenddatetime = uenddatetime;
		}
		public boolean isUopflag() {
			return Uopflag;
		}
		public void setUopflag(boolean uopflag) {
			Uopflag = uopflag;
		}
		public String getUovertimeflag() {
			return Uovertimeflag;
		}
		public void setUovertimeflag(String uovertimeflag) {
			Uovertimeflag = uovertimeflag;
		}
		
		
}
