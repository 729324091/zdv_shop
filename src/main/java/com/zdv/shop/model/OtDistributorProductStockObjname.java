package com.zdv.shop.model;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 经销商产品库存对应产品属性中间表
 * @author LBY
 * @data 2019年1月18日 
 */
@Table(name = "ot_distributor_product_stock_objname")
public class OtDistributorProductStockObjname {

	/**
	 * 产品库存属性ID
	 */
	@Id
	private String uobjnamevalueid;
	/**
	 * 产品ID
	 */
	private String uproductid;
	/**
	 * 产品库存属性ID
	 */
	private String uproductstockid;
	/**
	 * 属性名ID
	 */
	private String uobjnameid;
	/**
	 * 属性名
	 */
	private String uobjname;
	/**
	 * 属性值ID
	 */
	private String uobjvalueid;
	/**
	 * 属性值
	 */
	private String uobjvalue;
	public String getUobjnamevalueid() {
		return uobjnamevalueid;
	}
	public void setUobjnamevalueid(String uobjnamevalueid) {
		this.uobjnamevalueid = uobjnamevalueid;
	}
	public String getUproductid() {
		return uproductid;
	}
	public void setUproductid(String uproductid) {
		this.uproductid = uproductid;
	}
	public String getUproductstockid() {
		return uproductstockid;
	}
	public void setUproductstockid(String uproductstockid) {
		this.uproductstockid = uproductstockid;
	}
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
	/**
	 * @author LBY
	 * @data 2019年1月18日
	 * @return
	 */
	@Override
	public String toString() {
		return "OtDistributorProductStockObjname [uobjnamevalueid=" + uobjnamevalueid + ", uproductid=" + uproductid
				+ ", uproductstockid=" + uproductstockid + ", uobjnameid=" + uobjnameid + ", uobjname=" + uobjname
				+ ", uobjvalueid=" + uobjvalueid + ", uobjvalue=" + uobjvalue + "]";
	}
}
