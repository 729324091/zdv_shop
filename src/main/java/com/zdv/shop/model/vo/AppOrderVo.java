package com.zdv.shop.model.vo;

import java.util.List;

/**
 * 订单Vo
 * @author LBY
 * @data 2019年1月8日 
 */
public class AppOrderVo {

	/**
	 * 订单ID
	 */
	private String uorderid;
	/**
	 * 订单号
	 */
	private String uorderno;
	/**
	 * 总数量
	 */
	private String uproductnum;
	/**
	 * 订单总价格
	 */
	private String utotalmoney;
	/**
	 * 状态
	 */
	private String ustatus;
	/**
	 * 创建时间
	 */
	private String ucreatedate;
	/**
	 * 订单项list
	 */
	private List<AppOrderItemVo> orderItemList;
	/**
	 * 发货方信息
	 */
	private Shipper shipper;
	/**
	 * 收货方信息
	 */
	private Consignee consignee;
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
	public String getUproductnum() {
		return uproductnum;
	}
	public void setUproductnum(String uproductnum) {
		this.uproductnum = uproductnum;
	}
	public String getUtotalmoney() {
		return utotalmoney;
	}
	public void setUtotalmoney(String utotalmoney) {
		this.utotalmoney = utotalmoney;
	}
	public String getUstatus() {
		return ustatus;
	}
	public void setUstatus(String ustatus) {
		this.ustatus = ustatus;
	}
	public String getUcreatedate() {
		return ucreatedate;
	}
	public void setUcreatedate(String ucreatedate) {
		this.ucreatedate = ucreatedate;
	}
	public List<AppOrderItemVo> getOrderItemList() {
		return orderItemList;
	}
	public void setOrderItemList(List<AppOrderItemVo> orderItemList) {
		this.orderItemList = orderItemList;
	}
	public Shipper getShipper() {
		return shipper;
	}
	public void setShipper(Shipper shipper) {
		this.shipper = shipper;
	}
	public Consignee getConsignee() {
		return consignee;
	}
	public void setConsignee(Consignee consignee) {
		this.consignee = consignee;
	}
	/**
	 * @author LBY
	 * @data 2019年2月15日
	 * @return
	 */
	@Override
	public String toString() {
		return "AppOrderVo [uorderid=" + uorderid + ", uorderno=" + uorderno + ", uproductnum=" + uproductnum
				+ ", utotalmoney=" + utotalmoney + ", ustatus=" + ustatus + ", ucreatedate=" + ucreatedate
				+ ", orderItemList=" + orderItemList + ", shipper=" + shipper + ", consignee=" + consignee + "]";
	}
	
}
