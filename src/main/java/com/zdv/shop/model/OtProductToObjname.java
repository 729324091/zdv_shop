package com.zdv.shop.model;

import javax.persistence.Table;

/**
 * 产品与产品属性中间表
 * @author LBY
 * @date: 2018年12月6日
 */
@Table(name = "ot_product_to_objname")
public class OtProductToObjname {

	/**
	 * 产品ID
	 */
	private String uproductid;
	/**
	 * 属性名ID
	 */
	private String uobjnameid;
	public String getUproductid() {
		return uproductid;
	}
	public void setUproductid(String uproductid) {
		this.uproductid = uproductid;
	}
	public String getUobjnameid() {
		return uobjnameid;
	}
	public void setUobjnameid(String uobjnameid) {
		this.uobjnameid = uobjnameid;
	}
	/**
	 * @author LBY
	 * @data 2019年1月24日
	 * @return
	 */
	@Override
	public String toString() {
		return "OtProductToObjname [uproductid=" + uproductid + ", uobjnameid=" + uobjnameid + "]";
	}
}
