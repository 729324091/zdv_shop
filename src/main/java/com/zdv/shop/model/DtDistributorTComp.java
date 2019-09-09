package com.zdv.shop.model;

import javax.persistence.Table;

/**
 * 经销商管理的销售商中间表
 * @author LBY
 * @date: 2018年12月17日
 */
@Table(name = "dt_distributor_to_comp")
public class DtDistributorTComp {

	/**
	 * 销售商ID
	 */
	private String ucompid;
	//商户号
	private String ucustomerid;

	/**
	 * 经销商（供应商）主键ID
	 */
	private String udistributorid;
	/**
	 * 状态（0审核状态，1正常状态，2暂停服务）
	 */
	private Character ustatus;
	/**
	 * 创建时间
	 */
	private String ucreatedate;
	public String getUcompid() {
		return ucompid;
	}
	public void setUcompid(String ucompid) {
		this.ucompid = ucompid;
	}
	public String getUdistributorid() {
		return udistributorid;
	}
	public void setUdistributorid(String udistributorid) {
		this.udistributorid = udistributorid;
	}
	public Character getUstatus() {
		return ustatus;
	}
	public void setUstatus(Character ustatus) {
		this.ustatus = ustatus;
	}
	public String getUcreatedate() {
		return ucreatedate;
	}
	public void setUcreatedate(String ucreatedate) {
		this.ucreatedate = ucreatedate;
	}

	public String getUcustomerid() {
		return ucustomerid;
	}

	public void setUcustomerid(String ucustomerid) {
		this.ucustomerid = ucustomerid;
	}

	@Override
	public String toString() {
		return "DtDistributorToCompany [ucompid=" + ucompid + ", udistributorid=" + udistributorid + ", ustatus="
				+ ustatus + ", ucreatedate=" + ucreatedate + "]";
	}
}
