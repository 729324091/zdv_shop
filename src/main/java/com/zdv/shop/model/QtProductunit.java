package com.zdv.shop.model;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 产品单位表
 * @author LBY
 * @date 2019年1月3日
 */
@Table(name="qt_productunit")
public class QtProductunit {
	
	/**
	 * 产品单位ID
	 */
	@Id
	private String uunitid;
	/**
	 * 父级id
	 */
	private String upunitid;
	/**
	 * 产品id
	 */
	private String uproductid;
	/**
	 * 单位名称
	 */
	private String uunitname;
	/**
	 * 与父换算数
	 */
	private Integer ucalernum;
	/**
	 * 描述
	 */
	private String udesc;
	/**
	 * 单位颜色
	 */
	private String ucolor;
	/**
	 * 子单位
	 */
	@Transient
	private QtProductunit childUnit;
	/**
	 * 库存
	 */
	@Transient
	private Integer stock;
	/**
	 * 价格
	 */
	@Transient
	private String price;
	public String getUunitid() {
		return uunitid;
	}
	public void setUunitid(String uunitid) {
		this.uunitid = uunitid;
	}
	public String getUpunitid() {
		return upunitid;
	}
	public void setUpunitid(String upunitid) {
		this.upunitid = upunitid;
	}
	public String getUproductid() {
		return uproductid;
	}
	public void setUproductid(String uproductid) {
		this.uproductid = uproductid;
	}
	public String getUunitname() {
		return uunitname;
	}
	public void setUunitname(String uunitname) {
		this.uunitname = uunitname;
	}
	public Integer getUcalernum() {
		return ucalernum;
	}
	public void setUcalernum(Integer ucalernum) {
		this.ucalernum = ucalernum;
	}
	public String getUdesc() {
		return udesc;
	}
	public void setUdesc(String udesc) {
		this.udesc = udesc;
	}
	public String getUcolor() {
		return ucolor;
	}
	public void setUcolor(String ucolor) {
		this.ucolor = ucolor;
	}
	public QtProductunit getChildUnit() {
		return childUnit;
	}
	public void setChildUnit(QtProductunit childUnit) {
		this.childUnit = childUnit;
	}
	public Integer getStock() {
		return stock;
	}
	public void setStock(Integer stock) {
		this.stock = stock;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	/**
	 * @author LBY
	 * @data 2019年1月23日
	 * @return
	 */
	@Override
	public String toString() {
		return "QtProductunit [uunitid=" + uunitid + ", upunitid=" + upunitid + ", uproductid="
				+ uproductid + ", uunitname=" + uunitname + ", ucalernum=" + ucalernum + ", udesc=" + udesc
				+ ", ucolor=" + ucolor + ", childUnit=" + childUnit + ", stock=" + stock + ", price=" + price + "]";
	}
}
