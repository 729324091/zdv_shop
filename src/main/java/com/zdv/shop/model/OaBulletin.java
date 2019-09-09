package com.zdv.shop.model;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * 公告管理
 */
@Table(name = "OA_Bulletin")
public class OaBulletin {
		
		/**
		 * 主键
		 */
		@Id
		@GeneratedValue(generator="uuID")
		private Integer ublid;								//'公告编号 8位日期 ＋ 4位序号',
		public String ubltype;							//'公告类型 代码7003',
		private Integer ucorpid;							//'发布公司',
		private Integer udeptno;						//'发布部门',
		private Integer usender;						// '发布人员',
		private String usddatetime;					//'发送时间',
		private int ucorpids;								//'发布范围（分公司编码） 可以多个，以“、”隔开',
		private String ucorpnames;					//'发布范围（分公司名称）可以多个，以“、”隔开',
		private String udeptnos;						//'发布范围（部门编码）可以多个，以“、”隔开',
		private String udeptnames;					//'发布范围（部门名称）可以多个，以“、”隔开',
		private String uroleids;							//'发布范围（角色编号） 可以多个，以“、”隔开',
		private String urolenames;					//'发布范围（角色名称）可以多个，以“、”隔开',
		private String ustaffids;							//'发布范围（经办人编号）可以多个，以“、”隔开',
		private boolean ustaffnames ;				//'发布范围（经办人名称）可以多个，以“、”隔开',
		private String utitle;								//'标题',
		private boolean udatestart;					//'生效日期',
		private String udateend;						//'失效日期',
		private String uattachname;					// '附件名称',
		private String uattachpath;					// '附件路径',
		private String usms;								//'手机短信提醒'
		private int utopflag;								//'置顶',
		private String ucontent;						//'内容',
		private String uflid;								//'审批编号',
		private String umtstate;						//'当前状态',
		private String ustepname;						//'当前步骤名称',
		private boolean ueflag;							// '有效标志'
		public Integer getUblid() {
			return ublid;
		}
		public void setUblid(Integer ublid) {
			this.ublid = ublid;
		}
		public String getUbltype() {
			return ubltype;
		}
		public void setUbltype(String ubltype) {
			this.ubltype = ubltype;
		}
		public Integer getUcorpid() {
			return ucorpid;
		}
		public void setUcorpid(Integer ucorpid) {
			this.ucorpid = ucorpid;
		}
		public Integer getUdeptno() {
			return udeptno;
		}
		public void setUdeptno(Integer udeptno) {
			this.udeptno = udeptno;
		}
		public Integer getUsender() {
			return usender;
		}
		public void setUsender(Integer usender) {
			this.usender = usender;
		}
		public String getUsddatetime() {
			return usddatetime;
		}
		public void setUsddatetime(String usddatetime) {
			this.usddatetime = usddatetime;
		}
		public int getUcorpids() {
			return ucorpids;
		}
		public void setUcorpids(int ucorpids) {
			this.ucorpids = ucorpids;
		}
		public String getUcorpnames() {
			return ucorpnames;
		}
		public void setUcorpnames(String ucorpnames) {
			this.ucorpnames = ucorpnames;
		}
		public String getUdeptnos() {
			return udeptnos;
		}
		public void setUdeptnos(String udeptnos) {
			this.udeptnos = udeptnos;
		}
		public String getUdeptnames() {
			return udeptnames;
		}
		public void setUdeptnames(String udeptnames) {
			this.udeptnames = udeptnames;
		}
		public String getUroleids() {
			return uroleids;
		}
		public void setUroleids(String uroleids) {
			this.uroleids = uroleids;
		}
		public String getUrolenames() {
			return urolenames;
		}
		public void setUrolenames(String urolenames) {
			this.urolenames = urolenames;
		}
		public String getUstaffids() {
			return ustaffids;
		}
		public void setUstaffids(String ustaffids) {
			this.ustaffids = ustaffids;
		}
		public boolean isUstaffnames() {
			return ustaffnames;
		}
		public void setUstaffnames(boolean ustaffnames) {
			this.ustaffnames = ustaffnames;
		}
		public String getUtitle() {
			return utitle;
		}
		public void setUtitle(String utitle) {
			this.utitle = utitle;
		}
		public boolean isUdatestart() {
			return udatestart;
		}
		public void setUdatestart(boolean udatestart) {
			this.udatestart = udatestart;
		}
		public String getUdateend() {
			return udateend;
		}
		public void setUdateend(String udateend) {
			this.udateend = udateend;
		}
		public String getUattachname() {
			return uattachname;
		}
		public void setUattachname(String uattachname) {
			this.uattachname = uattachname;
		}
		public String getUattachpath() {
			return uattachpath;
		}
		public void setUattachpath(String uattachpath) {
			this.uattachpath = uattachpath;
		}
		public String getUsms() {
			return usms;
		}
		public void setUsms(String usms) {
			this.usms = usms;
		}
		public int getUtopflag() {
			return utopflag;
		}
		public void setUtopflag(int utopflag) {
			this.utopflag = utopflag;
		}
		public String getUcontent() {
			return ucontent;
		}
		public void setUcontent(String ucontent) {
			this.ucontent = ucontent;
		}
		public String getUflid() {
			return uflid;
		}
		public void setUflid(String uflid) {
			this.uflid = uflid;
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
		public boolean isUeflag() {
			return ueflag;
		}
		public void setUeflag(boolean ueflag) {
			this.ueflag = ueflag;
		}
		
		
}
