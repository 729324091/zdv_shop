package com.zdv.shop.model;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 经销商产品库存与产品属性表
 * @author LBY
 * @data 2019年1月18日 
 */
@Table(name = "ot_distributor_product_stock")
public class OtDistributorProductStock {

	/**
	 * 产品库存属性ID
	 */
	@Id
	private String uproductstockid;
	/**
	 * 经销商关联产品ID
	 */
	private String udistribproductid;
	/**
	 * 产品ID
	 */
	private String uproductid;
	/**
	 * 库存
	 */
	private Integer ustock;
	/**
	 * 市场价格
	 */
	private Double umarketprice;
	/**
	 * 销售价
	 */
	private Double uprice;
	/**
	 * 促销价
	 */
	private Double usalesprice;
	/**
	 * 该属性产品图
	 */
	private String uproductpic;
	public String getUproductstockid() {
		return uproductstockid;
	}
	public void setUproductstockid(String uproductstockid) {
		this.uproductstockid = uproductstockid;
	}
	public String getUdistribproductid() {
		return udistribproductid;
	}
	public void setUdistribproductid(String udistribproductid) {
		this.udistribproductid = udistribproductid;
	}
	public String getUproductid() {
		return uproductid;
	}
	public void setUproductid(String uproductid) {
		this.uproductid = uproductid;
	}
	public Integer getUstock() {
		return ustock;
	}
	public void setUstock(Integer ustock) {
		this.ustock = ustock;
	}
	public Double getUmarketprice() {
		return umarketprice;
	}
	public void setUmarketprice(Double umarketprice) {
		this.umarketprice = umarketprice;
	}
	public Double getUprice() {
		return uprice;
	}
	public void setUprice(Double uprice) {
		this.uprice = uprice;
	}
	public Double getUsalesprice() {
		return usalesprice;
	}
	public void setUsalesprice(Double usalesprice) {
		this.usalesprice = usalesprice;
	}
	public String getUproductpic() {
		return uproductpic;
	}
	public void setUproductpic(String uproductpic) {
		this.uproductpic = uproductpic;
	}
	/**
	 * @author LBY
	 * @data 2019年1月18日
	 * @return
	 */
	@Override
	public String toString() {
		return "OtDistributorProductStock [uproductstockid=" + uproductstockid + ", udistribproductid="
				+ udistribproductid + ", uproductid=" + uproductid + ", ustock=" + ustock + ", umarketprice="
				+ umarketprice + ", uprice=" + uprice + ", usalesprice=" + usalesprice + ", uproductpic=" + uproductpic
				+ "]";
	}
}
