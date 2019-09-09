package com.zdv.shop.model;

import javax.persistence.Table;

/**
 *  管理员与销售商中间
 * @author LBY
 * @date: 2018年12月14日
 */
@Table(name = "ct_opuser_to_comp")
public class CtOpUserToComp {
	/**
	 * 销售商ID
	 */
	private String ucompid;
	/**
	 * 管理员ID
	 */
	private String uopuserid;
	/**
	 * 角色ID
	 */
	private String uroleid;
	/**
	 * 状态 0审核状态，1正常状态，2暂停服务
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
	public String getUopuserid() {
		return uopuserid;
	}
	public void setUopuserid(String uopuserid) {
		this.uopuserid = uopuserid;
	}
	public String getUroleid() {
		return uroleid;
	}
	public void setUroleid(String uroleid) {
		this.uroleid = uroleid;
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
	@Override
	public String toString() {
		return "CtOpUserToComp [ucompid=" + ucompid + ", uopuserid=" + uopuserid + ", uroleid=" + uroleid
				+ ", ustatus=" + ustatus + ", ucreatedate=" + ucreatedate + "]";
	}
}