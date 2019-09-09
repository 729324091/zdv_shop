package com.zdv.shop.model;

import javax.persistence.Table;

/**
 * 业务员管理的销售商中间表
 * @author LBY
 * @data 2019年1月10日 
 */
@Table(name = "op_opuser_m_comp")
public class OpOpuserMComp {

	/**
	 * 销售商主键ID
	 */
	private String ucompid;
	/**
	 * 管理员ID
	 */
	private String uopuserid;
	/**
	 * 状态(0审核状态，1正常状态，2暂停服务)
	 */
	private String ustatus;
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
	public String getUopuserid() {
		return uopuserid;
	}
	public void setUopuserid(String uopuserid) {
		this.uopuserid = uopuserid;
	}
	public String getUstatus() {
		return ustatus;
	}
	public void setUstatus(String ustatus) {
		this.ustatus = ustatus;
	}
	public String getUcreatedate() {
		return ucreatedate;
	}
	public void setUcreatedate(String ucreatedate) {
		this.ucreatedate = ucreatedate;
	}
	/**
	 * @author LBY
	 * @data 2019年1月10日
	 * @return
	 */
	@Override
	public String toString() {
		return "OpOpuserMComp [ucompid=" + ucompid + ", uopuserid=" + uopuserid + ", ustatus=" + ustatus
				+ ", ucreatedate=" + ucreatedate + "]";
	}
}
