package com.zdv.shop.model;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 产品属性名表
 * @author LBY
 * @date: 2018年12月6日
 */
@Table(name = "ot_productobjname")
public class OtProductobjname {
	
	/**
	 * 属性名ID
	 */
	@Id
	@GeneratedValue(generator="UUID")
	private String uobjnameid;
	/**
	 * 属性名
	 */
	private String uobjname;
	public String getUobjnameid() {
		return uobjnameid;
	}
	public void setUobjnameid(String uobjnameid) {
		this.uobjnameid = uobjnameid;
	}
	public String getUobjname() {
		return uobjname;
	}
	public void setUobjname(String uobjname) {
		this.uobjname = uobjname;
	}
	@Override
	public String toString() {
		return "OtProductobjname [uobjnameid=" + uobjnameid + ", uobjname=" + uobjname + "]";
	}

}
