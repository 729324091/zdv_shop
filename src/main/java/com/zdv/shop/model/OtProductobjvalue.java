package com.zdv.shop.model;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 产品属性值表
 * @author LBY
 * @date: 2018年12月6日
 */
@Table(name = "ot_productobjvalue")
public class OtProductobjvalue {

	/**
	 * 属性值ID
	 */
	@Id
	@GeneratedValue(generator="UUID")
	private String uobjvalueid;
	/**
	 * 属性值
	 */
	private String uobjvalue;
	/**
	 * 属性名ID
	 */
	private String uobjnameid;
	public String getUobjvalueid() {
		return uobjvalueid;
	}
	public void setUobjvalueid(String uobjvalueid) {
		this.uobjvalueid = uobjvalueid;
	}
	public String getUobjvalue() {
		return uobjvalue;
	}
	public void setUobjvalue(String uobjvalue) {
		this.uobjvalue = uobjvalue;
	}
	public String getUobjnameid() {
		return uobjnameid;
	}
	public void setUobjnameid(String uobjnameid) {
		this.uobjnameid = uobjnameid;
	}
	@Override
	public String toString() {
		return "OtProductobjvalue [uobjvalueid=" + uobjvalueid + ", uobjvalue=" + uobjvalue + ", uobjnameid="
				+ uobjnameid + "]";
	}
}
