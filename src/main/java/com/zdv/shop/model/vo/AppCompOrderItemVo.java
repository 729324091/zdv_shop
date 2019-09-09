package com.zdv.shop.model.vo;

/**
 * 经销商经营下单详细 Vo
 * @author LBY
 * @data 2019年1月8日 
 */
public class AppCompOrderItemVo {
	
	/**
	 * 订单项ID
	 */
	private String uorderitemid;
	/**
	 * 产品名称
	 */
	private String uproductname;
	/**
	 * 列表显示图
	 */
	private String uhomepic;
	/**
	 * 价格
	 */
	private String uprice;
	/**
	 * 产品数量
	 */
	private Integer uproductnum;
	/**
	 * 单位ID
	 */
	private String uunitid;
	/**
	 * 单位名称
	 */
	private String uunitname;
	/**
	 * 子单位名称
	 */
	private String uchildunitname;
	/**
	 * 转换数
	 */
	private Integer ucalerNum;
	/**
	 * 规格名称
	 */
	private String uobjname;
	/**
	 * 规格值
	 */
	private String uobjvalue;
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
	public Integer getUproductnum() {
		return uproductnum;
	}
	public void setUproductnum(Integer uproductnum) {
		this.uproductnum = uproductnum;
	}
	public String getUunitid() {
		return uunitid;
	}
	public void setUunitid(String uunitid) {
		this.uunitid = uunitid;
	}
	public String getUunitname() {
		return uunitname;
	}
	public void setUunitname(String uunitname) {
		this.uunitname = uunitname;
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
	public String getUobjname() {
		return uobjname;
	}
	public void setUobjname(String uobjname) {
		this.uobjname = uobjname;
	}
	public String getUobjvalue() {
		return uobjvalue;
	}
	public void setUobjvalue(String uobjvalue) {
		this.uobjvalue = uobjvalue;
	}
	/**
	 * @author LBY
	 * @data 2019年1月8日
	 * @return
	 */
	@Override
	public String toString() {
		return "AppCompOrderItemVo [uorderitemid=" + uorderitemid + ", uproductname=" + uproductname
				+ ", uhomepic=" + uhomepic + ", uprice=" + uprice + ", uproductnum=" + uproductnum + ", uunitid="
				+ uunitid + ", uunitname=" + uunitname + ", uchildunitname=" + uchildunitname + ", ucalerNum="
				+ ucalerNum + ", uobjname=" + uobjname + ", uobjvalue=" + uobjvalue + "]";
	}
}
