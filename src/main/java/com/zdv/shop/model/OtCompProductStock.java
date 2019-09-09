package com.zdv.shop.model;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 销售商产品库存与产品属性表
 * @author LBY
 * @data 2019年2月20日 
 */
@Table(name = "ot_comp_product_stock")
public class OtCompProductStock {

	/**
	 * 产品库存属性ID
	 */
	@Id
	private String uproductstockid;
	/**
	 * 销售商关联产品ID
	 */
	private String ucomproductid;
	/**
	 * 产品ID
	 */
	private String uproductid;
	/**
	 * 库存
	 */
	private String ustock;
	/**
	 * 市场价格
	 */
	private String umarketprice;
	/**
	 * 销售价
	 */
	private String uprice;
	/**
	 * 会员价
	 */
	private String uvipprice;
	/**
	 * 促销价
	 */
	private String usalesprice;
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

	public String getUcomproductid() {
		return ucomproductid;
	}

	public void setUcomproductid(String ucomproductid) {
		this.ucomproductid = ucomproductid;
	}

	public String getUproductid() {
		return uproductid;
	}

	public void setUproductid(String uproductid) {
		this.uproductid = uproductid;
	}

	public String getUstock() {
		return ustock;
	}

	public void setUstock(String ustock) {
		this.ustock = ustock;
	}

	public String getUmarketprice() {
		return umarketprice;
	}

	public void setUmarketprice(String umarketprice) {
		this.umarketprice = umarketprice;
	}

	public String getUprice() {
		return uprice;
	}

	public void setUprice(String uprice) {
		this.uprice = uprice;
	}

	public String getUvipprice() {
		return uvipprice;
	}

	public void setUvipprice(String uvipprice) {
		this.uvipprice = uvipprice;
	}

	public String getUsalesprice() {
		return usalesprice;
	}

	public void setUsalesprice(String usalesprice) {
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
	 * @data 2019年2月26日
	 * @return
	 */
	@Override
	public String toString() {
		return "OtCompProductStock [uproductstockid=" + uproductstockid + ", ucomproductid=" + ucomproductid
				+ ", uproductid=" + uproductid + ", ustock=" + ustock + ", umarketprice=" + umarketprice + ", uprice="
				+ uprice + ", uvipprice=" + uvipprice + ", usalesprice=" + usalesprice + ", uproductpic=" + uproductpic
				+ "]";
	}
}
