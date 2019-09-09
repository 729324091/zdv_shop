package com.zdv.shop.model.vo;

import com.zdv.shop.model.OtDistributorProductStockObjname;

import java.util.List;

/**
 * 订单详细 Vo
 * @author LBY
 * @data 2019年1月8日 
 */
public class AppOrderItemVo {
	
	/**
	 * 订单项ID
	 */
	private String uorderitemid;
	/**
	 * 产品名称
	 */
	private String uproductname;
	/**
	 * 图片路径
	 */
	private String uhomepic;
	/**
	 * 参考价格
	 */
	private String uprice;
	/**
	 * 数量
	 */
	private String uproductnum;
	/**
	 * 属性
	 */
	private String uobjnamevalue;
	/**
	 * 产品单位ID
	 */
	private String uunitid;
	/**
	 * 单位名称
	 */
	private String uunit;
	/**
	 * 单位颜色
	 */
	private String ucolor;
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
	private List<OtDistributorProductStockObjname> uobjList;
	public String getUorderitemid() {
		return uorderitemid;
	}
	public void setUorderitemid(String uorderitemid) {
		this.uorderitemid = uorderitemid;
	}
	public String getUproductname() {
		return uproductname;
	}
	public void setUproductname(String uproductname) {
		this.uproductname = uproductname;
	}
	public String getUhomepic() {
		return uhomepic;
	}
	public void setUhomepic(String uhomepic) {
		this.uhomepic = uhomepic;
	}
	public String getUprice() {
		return uprice;
	}
	public void setUprice(String uprice) {
		this.uprice = uprice;
	}
	public String getUproductnum() {
		return uproductnum;
	}
	public void setUproductnum(String uproductnum) {
		this.uproductnum = uproductnum;
	}
	public String getUobjnamevalue() {
		return uobjnamevalue;
	}
	public void setUobjnamevalue(String uobjnamevalue) {
		this.uobjnamevalue = uobjnamevalue;
	}
	public String getUunitid() {
		return uunitid;
	}
	public void setUunitid(String uunitid) {
		this.uunitid = uunitid;
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
	public List<OtDistributorProductStockObjname> getUobjList() {
		return uobjList;
	}
	public void setUobjList(List<OtDistributorProductStockObjname> uobjList) {
		this.uobjList = uobjList;
	}
	/**
	 * @author LBY
	 * @data 2019年1月23日
	 * @return
	 */
	@Override
	public String toString() {
		return "AppOrderItemVo [uorderitemid=" + uorderitemid + ", uproductname=" + uproductname + ", uhomepic="
				+ uhomepic + ", uprice=" + uprice + ", uproductnum=" + uproductnum + ", uobjnamevalue=" + uobjnamevalue
				+ ", uunitid=" + uunitid + ", uunit=" + uunit + ", ucolor=" + ucolor + ", uchildunitname="
				+ uchildunitname + ", ucalerNum=" + ucalerNum + ", uobjList=" + uobjList + "]";
	}
}
