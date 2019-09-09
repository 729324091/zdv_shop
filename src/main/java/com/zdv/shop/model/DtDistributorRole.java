package com.zdv.shop.model;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 经销商（供应商）角色信息表
 * @author LBY
 * @date: 2018年12月14日
 */
@Table(name = "dt_distributor_role")
public class DtDistributorRole {

	/**
	 * 角色ID
	 */
	@Id
	private String uroleid;
	/**
	 * 层级
	 */
	private String ulevel;
	/**
	 * 父ID
	 */
	private String uparentid;
	/**
	 * 角色名称
	 */
	private String urolename;
	/**
	 * 经销商（供应商）主键ID
	 */
	private String udistributorid;
	/**
	 * 角色级别颜色
	 */
	private String ulevelcolor;
	/**
	 * 角色类型 0超级管理员1管理员2审核员3操作员，5发布员 6业务员,7统计员
	 */
	private Character utypes;
	public String getUroleid() {
		return uroleid;
	}
	public void setUroleid(String uroleid) {
		this.uroleid = uroleid;
	}
	public String getUlevel() {
		return ulevel;
	}
	public void setUlevel(String ulevel) {
		this.ulevel = ulevel;
	}
	public String getUparentid() {
		return uparentid;
	}
	public void setUparentid(String uparentid) {
		this.uparentid = uparentid;
	}
	public String getUrolename() {
		return urolename;
	}
	public void setUrolename(String urolename) {
		this.urolename = urolename;
	}
	public String getUdistributorid() {
		return udistributorid;
	}
	public void setUdistributorid(String udistributorid) {
		this.udistributorid = udistributorid;
	}
	public String getUlevelcolor() {
		return ulevelcolor;
	}
	public void setUlevelcolor(String ulevelcolor) {
		this.ulevelcolor = ulevelcolor;
	}
	public Character getUtypes() {
		return utypes;
	}
	public void setUtypes(Character utypes) {
		this.utypes = utypes;
	}
	@Override
	public String toString() {
		return "DtDistributorRole [uroleid=" + uroleid + ", ulevel=" + ulevel + ", uparentid=" + uparentid + ", urolename="
				+ urolename + ", udistributorid=" + udistributorid + ", ulevelcolor=" + ulevelcolor + ", utypes="
				+ utypes + "]";
	}
}
