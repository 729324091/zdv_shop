package com.zdv.shop.model.vo;

import java.util.List;

/**
 * 销售订单信息
 * @author LBY
 * @data 2019年2月16日 
 */
public class SalesOrderVO {

	/**
	 * 订单ID
	 */
	private String uorderid;
	/**
	 * 订单号
	 */
	private String uorderno;
	/**
	 * 总价格
	 */
	private String utotalprice;
	/**
	 * 商品数量
	 */
	private String uproductnum;
	/**
	 * 下单时间
	 */
	private String ucreatedate;
	/**
	 * 销售产品
	 */
	private List<Object> orderItemList;
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
	public String getUtotalprice() {
		return utotalprice;
	}
	public void setUtotalprice(String utotalprice) {
		this.utotalprice = utotalprice;
	}
	public String getUproductnum() {
		return uproductnum;
	}
	public void setUproductnum(String uproductnum) {
		this.uproductnum = uproductnum;
	}
	public String getUcreatedate() {
		return ucreatedate;
	}
	public void setUcreatedate(String ucreatedate) {
		this.ucreatedate = ucreatedate;
	}
	public List<Object> getOrderItemList() {
		return orderItemList;
	}
	public void setOrderItemList(List<Object> orderItemList) {
		this.orderItemList = orderItemList;
	}
	/**
	 * @author LBY
	 * @data 2019年2月21日
	 * @return
	 */
	@Override
	public String toString() {
		return "SalesOrderVO [uorderid=" + uorderid + ", uorderno=" + uorderno + ", utotalprice=" + utotalprice
				+ ", uproductnum=" + uproductnum + ", ucreatedate=" + ucreatedate + ", orderItemList=" + orderItemList
				+ "]";
	}
}
