package com.zdv.shop.model;

import javax.persistence.Table;

/**
 * 销售商权限表
 * @author LBY
 * @data 2019年1月10日
 */
@Table(name = "ct_role_operation")
public class CtRoleOperation {
    
	/**
	 * 角色ID
	 */
	private String uroleid;
	/**
	 * 销售商ID
	 */
	private String ucompid;

	/**
	 * 权限id
	 */
	private String uopid;

	public String getUopid() {
		return uopid;
	}

	public void setUopid(String uopid) {
		this.uopid = uopid;
	}

	public String getUroleid() {
		return uroleid;
	}
	public void setUroleid(String uroleid) {
		this.uroleid = uroleid;
	}
	public String getUcompid() {
		return ucompid;
	}
	public void setUcompid(String ucompid) {
		this.ucompid = ucompid;
	}

	/**
	 * @return
	 * @author LBY
	 * @data 2019年1月10日
	 */
	@Override
	public String toString() {
		return "CtRoleOperation{" +
				"uroleid='" + uroleid + '\'' +
				", ucompid='" + ucompid + '\'' +
				", uopid='" + uopid + '\'' +
				'}';
	}
}