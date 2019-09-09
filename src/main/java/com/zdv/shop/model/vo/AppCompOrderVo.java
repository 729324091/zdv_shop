package com.zdv.shop.model.vo;

import java.util.List;

/**
 * 经销商订单Vo
 * @author LBY
 * @data 2019年1月8日 
 */
public class AppCompOrderVo {

	/**
	 * 订单ID
	 */
	private String uorderid;
	/**
	 * 订单号
	 */
	private String uorderno;
	/**
	 * 状态码(收货)
	 */
	private String ueflag;
	/**
	 * 状态码(发货)
	 */
	private String upeflag;
	/**
	 * 状态(收货)
	 */
	private String ueflagname;
	/**
	 * 状态(发货)
	 */
	private String upeflagname;
	/**
	 * 经销商联系人
	 */
	private String ucontact;
	/**
	 * 经销商电话
	 */
	private String utel;
	/**
	 * 经销商地址
	 */
	private String uaddress;
	/**
	 * 订单产品数量
	 */
	private String utotalnum;
	/**
	 * 上级经销商联系人
	 */
	private String upcontact;
	/**
	 * 上级经销商电话
	 */
	private String uputel;
	/**
	 * 总金额
	 */
	private String utotalmoney;
	/**
	 * 创建时间
	 */
	private String ucreatedate;
	/**
	 * 订单项List
	 */
	private List<AppCompOrderItemVo> orderItemList;
	public String getUorderid() {
		return uorderid;
	}
	public void setUorderid(String uorderid) {
		this.uorderid = uorderid;
	}
	public String getUorderno() {
		return uorderno;
	}
	public void setUorderno(String uorderno) {
		this.uorderno = uorderno;
	}
	public String getUeflag() {
		return ueflag;
	}
	public void setUeflag(String ueflag) {
		this.ueflag = ueflag;
	}
	public String getUpeflag() {
		return upeflag;
	}
	public void setUpeflag(String upeflag) {
		this.upeflag = upeflag;
	}
	public String getUeflagname() {
		return ueflagname;
	}
	public void setUeflagname(String ueflagname) {
		this.ueflagname = ueflagname;
	}
	public String getUpeflagname() {
		return upeflagname;
	}
	public void setUpeflagname(String upeflagname) {
		this.upeflagname = upeflagname;
	}
	public String getUcontact() {
		return ucontact;
	}
	public void setUcontact(String ucontact) {
		this.ucontact = ucontact;
	}
	public String getUtel() {
		return utel;
	}
	public void setUtel(String utel) {
		this.utel = utel;
	}
	public String getUaddress() {
		return uaddress;
	}
	public void setUaddress(String uaddress) {
		this.uaddress = uaddress;
	}
	public String getUtotalnum() {
		return utotalnum;
	}
	public void setUtotalnum(String utotalnum) {
		this.utotalnum = utotalnum;
	}
	public String getUpcontact() {
		return upcontact;
	}
	public void setUpcontact(String upcontact) {
		this.upcontact = upcontact;
	}
	public String getUputel() {
		return uputel;
	}
	public void setUputel(String uputel) {
		this.uputel = uputel;
	}
	public String getUtotalmoney() {
		return utotalmoney;
	}
	public void setUtotalmoney(String utotalmoney) {
		this.utotalmoney = utotalmoney;
	}
	public String getUcreatedate() {
		return ucreatedate;
	}
	public void setUcreatedate(String ucreatedate) {
		this.ucreatedate = ucreatedate;
	}
	public List<AppCompOrderItemVo> getOrderItemList() {
		return orderItemList;
	}
	public void setOrderItemList(List<AppCompOrderItemVo> orderItemList) {
		this.orderItemList = orderItemList;
	}
	/**
	 * @author LBY
	 * @data 2019年1月10日
	 * @return
	 */
	@Override
	public String toString() {
		return "AppCompOrderVo [uorderid=" + uorderid + ", uorderno=" + uorderno + ", ueflag=" + ueflag
				+ ", upeflag=" + upeflag + ", ueflagname=" + ueflagname + ", upeflagname=" + upeflagname + ", ucontact="
				+ ucontact + ", utel=" + utel + ", uaddress=" + uaddress + ", utotalnum=" + utotalnum + ", upcontact="
				+ upcontact + ", uputel=" + uputel + ", utotalmoney=" + utotalmoney + ", ucreatedate=" + ucreatedate
				+ ", orderItemList=" + orderItemList + "]";
	}
}
