package com.zdv.shop.model.vo;

import java.util.List;

import com.zdv.shop.model.OtCompProductStockObjname;

/**
 * 销售订单商品详情
 * @author LBY
 * @data 2019年2月18日 
 */
public class SalesProductDetailsVO extends SalesProductVO {

	/**
	 * 产品名称
	 */
	private String uproductname;
	/**
	 * 属性值
	 */
	private String uobjnamevalue;
	/**
	 * 产品数量
	 */
	private String uproductnum;
	/**
	 * 支付价格
	 */
	private String upayprice;
	/**
	 * 产品单位
	 */
	private String uunit;
	/**
	 * 单位颜色
	 */
	private String ucolor;
	/**
	 * 产品单位ID
	 */
	private String uunitid;
	/**
	 * 子单位名称
	 */
	private String uchildunitname;
	/**
	 * 转换数
	 */
	private Integer ucalerNum;
	/**
	 * 产品属性List
	 */
	private List<OtCompProductStockObjname> uobjList;
	public String getUproductname() {
		return uproductname;
	}
	public void setUproductname(String uproductname) {
		this.uproductname = uproductname;
	}
	public String getUobjnamevalue() {
		return uobjnamevalue;
	}
	public void setUobjnamevalue(String uobjnamevalue) {
		this.uobjnamevalue = uobjnamevalue;
	}
	public String getUproductnum() {
		return uproductnum;
	}
	public void setUproductnum(String uproductnum) {
		this.uproductnum = uproductnum;
	}
	public String getUpayprice() {
		return upayprice;
	}
	public void setUpayprice(String upayprice) {
		this.upayprice = upayprice;
	}
	public String getUunit() {
		return uunit;
	}
	public void setUunit(String uunit) {
		this.uunit = uunit;
	}
	public String getUcolor() {
		return ucolor;
	}
	public void setUcolor(String ucolor) {
		this.ucolor = ucolor;
	}
	public String getUunitid() {
		return uunitid;
	}
	public void setUunitid(String uunitid) {
		this.uunitid = uunitid;
	}
	public String getUchildunitname() {
		return uchildunitname;
	}
	public void setUchildunitname(String uchildunitname) {
		this.uchildunitname = uchildunitname;
	}
	public Integer getUcalerNum() {
		return ucalerNum;
	}
	public void setUcalerNum(Integer ucalerNum) {
		this.ucalerNum = ucalerNum;
	}
	public List<OtCompProductStockObjname> getUobjList() {
		return uobjList;
	}
	public void setUobjList(List<OtCompProductStockObjname> uobjList) {
		this.uobjList = uobjList;
	}
	/**
	 * @author LBY
	 * @data 2019年2月19日
	 * @return
	 */
	@Override
	public String toString() {
		return "SalesProductDetailsVO [uproductname=" + uproductname + ", uobjnamevalue=" + uobjnamevalue
				+ ", uproductnum=" + uproductnum + ", upayprice=" + upayprice + ", uunit=" + uunit + ", ucolor="
				+ ucolor + ", uunitid=" + uunitid + ", uchildunitname=" + uchildunitname + ", ucalerNum="
				+ ucalerNum + ", uobjList=" + uobjList + "]";
	}
	
}
