package com.zdv.shop.model;

import javax.persistence.Table;

/**
 * 今日提醒日结表
 */
@Table(name = "OA_Remind")
public class OaRemind {

		private String uremindtype;	//'提醒类型 代码7003',
		private String ucorpid;			//'客户编码 8位日期 ＋ 4位序号',
		private String udatatype;		//'数据分类 用于退休（男、女）',
		private String uoperater;		//'操作员 用于工资发放',
		private String ucount;			//'数量'
		public String getUremindtype() {
			return uremindtype;
		}
		public void setUremindtype(String uremindtype) {
			this.uremindtype = uremindtype;
		}
		public String getUcorpid() {
			return ucorpid;
		}
		public void setUcorpid(String ucorpid) {
			this.ucorpid = ucorpid;
		}
		public String getUdatatype() {
			return udatatype;
		}
		public void setUdatatype(String udatatype) {
			this.udatatype = udatatype;
		}
		public String getUoperater() {
			return uoperater;
		}
		public void setUoperater(String uoperater) {
			this.uoperater = uoperater;
		}
		public String getUcount() {
			return ucount;
		}
		public void setUcount(String ucount) {
			this.ucount = ucount;
		}
		
		

		
		
		
}
