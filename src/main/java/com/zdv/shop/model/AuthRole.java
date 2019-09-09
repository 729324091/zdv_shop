package com.zdv.shop.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Table(name = "auth_role")
public class AuthRole {
	/**
	 * 主键
	 */
	@Id
	@GeneratedValue(generator="UUID")
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String roleid;
	private String parentid;//父级
	@Transient
    private AuthRole frole;
	/**
	 * 角色名称
	 */
	private String rolename;
	
	private String customerid;//商户号
	private String compid;//渠道ID
	/**
	 * 中文名
	 */
	private String cname;
	private String types;//0超级管理员1管理员2审核员3财务放款员
	
	
	public String getTypes() {
		return types;
	}

	public void setTypes(String types) {
		this.types = types;
	}

	/**
	 * 角色权限
	 */
	@Transient
	private List<AuthOperation> operations = new ArrayList<AuthOperation>();

	/**
	 * 获取主键
	 *
	 * @return id - 主键
	 */
	public String getRoleid() {
		return roleid;
	}

	/**
	 * 设置主键
	 *
	 * @param id 主键
	 */
	public void setRoleid(String roleid) {
		this.roleid = roleid;
	}

	/**
	 * 获取角色名称
	 *
	 * @return rolename - 角色名称
	 */
	public String getRolename() {
		return rolename;
	}

	/**
	 * 设置角色名称
	 *
	 * @param rolename 角色名称
	 */
	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	/**
	 * 获取中文名
	 *
	 * @return cname - 中文名
	 */
	public String getCname() {
		return cname;
	}

	/**
	 * 设置中文名
	 *
	 * @param cname 中文名
	 */
	public void setCname(String cname) {
		this.cname = cname;
	}

	public List<AuthOperation> getOperations() {
		return operations;
	}

	public void setOperations(List<AuthOperation> operations) {
		this.operations = operations;
	}

	public String getParentid() {
		return parentid;
	}

	public void setParentid(String parentid) {
		this.parentid = parentid;
	}

	public String getCustomerid() {
		return customerid;
	}

	public void setCustomerid(String customerid) {
		this.customerid = customerid;
	}

	public AuthRole getFrole() {
		return frole;
	}

	public void setFrole(AuthRole frole) {
		this.frole = frole;
	}

	public String getCompid() {
		return compid;
	}

	public void setCompid(String compid) {
		this.compid = compid;
	}

}