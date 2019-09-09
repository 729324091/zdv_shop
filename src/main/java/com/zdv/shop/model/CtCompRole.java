package com.zdv.shop.model;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 销售商角色信息表
 * @author LBY
 * @date: 2018年12月14日
 */
@Table(name = "ct_comp_role")
public class CtCompRole {
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
	 * 角色级别颜色
	 */
	private String ulevelcolor;
	/**
	 * 销售商ID
	 */
	private String ucompid;
	/**
	 * 角色类型 0超级管理员1管理员2店长3操作员 4促销员
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
	public String getUcompid() {
		return ucompid;
	}
	public void setUcompid(String ucompid) {
		this.ucompid = ucompid;
	}
	public Character getUtypes() {
		return utypes;
	}
	public void setUtypes(Character utypes) {
		this.utypes = utypes;
	}

	public String getUlevelcolor() {
		return ulevelcolor;
	}

	public void setUlevelcolor(String ulevelcolor) {
		this.ulevelcolor = ulevelcolor;
	}

	@Override
	public String toString() {
		return "CtCompRole [uroleid=" + uroleid + ", ulevel=" + ulevel + ", uparentid=" + uparentid + ", urolename="
				+ urolename + ", ucompid=" + ucompid + ", utypes=" + utypes + "]";
	}
}