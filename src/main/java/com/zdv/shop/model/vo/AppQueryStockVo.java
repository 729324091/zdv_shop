package com.zdv.shop.model.vo;

import java.util.List;

import javax.persistence.Transient;

/**
 * APP查询库存 Vo实体
 * @author LBY
 * @date 2019年1月3日
 */
public class AppQueryStockVo {

	/**
	 * 产品ID
	 */
	private String uproductid;
	/**
	 * 产品库存属性ID
	 */
	private String uproductstockid;
	/**
	 * 列表显示图
	 */
	private String uproductpic;
	/**
	 * 产品名称
	 */
	private String uproductname;
	/**
	 * 厂家名称
	 */
	private String ufactoryname;
	/**
	 * 经销商产品规格ID
	 */
//	private String uproductspecid;
	/**
	 * 规格名称
	 */
//	private String uobjname;
	/**
	 * 规格值
	 */
//	private String uobjvalue;
	/**
	 * 库存数量
	 */
	private String ustock;
	/**
	 * 单位ID
	 */
	private String uunitid;
	/**
	 * 单位名称
	 */
	private String uunit;
	/**
	 * 销售价
	 */
	private String uprice;
	/**
	 * 单位颜色
	 */
	private String ucolor;
	/**
	 * 单位List
	 */
	@Transient
	private List<AppStockUnitVo> unitList;
	/**
	 * 产品库存对应产品属性中间表
	 */
	@Transient
	private List<?> uobjList;
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
	public String getUproductpic() {
		return uproductpic;
	}
	public void setUproductpic(String uproductpic) {
		this.uproductpic = uproductpic;
	}
	public String getUproductname() {
		return uproductname;
	}
	public void setUproductname(String uproductname) {
		this.uproductname = uproductname;
	}
	public String getUfactoryname() {
		return ufactoryname;
	}
	public void setUfactoryname(String ufactoryname) {
		this.ufactoryname = ufactoryname;
	}
	public String getUstock() {
		return ustock;
	}
	public void setUstock(String ustock) {
		this.ustock = ustock;
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
	public String getUprice() {
		return uprice;
	}
	public void setUprice(String uprice) {
		this.uprice = uprice;
	}
	public String getUcolor() {
		return ucolor;
	}
	public void setUcolor(String ucolor) {
		this.ucolor = ucolor;
	}
	public List<AppStockUnitVo> getUnitList() {
		return unitList;
	}
	public void setUnitList(List<AppStockUnitVo> unitList) {
		this.unitList = unitList;
	}
	public List<?> getUobjList() {
		return uobjList;
	}
	public void setUobjList(List<?> uobjList) {
		this.uobjList = uobjList;
	}
	/**
	 * @author LBY
	 * @data 2019年2月20日
	 * @return
	 */
	@Override
	public String toString() {
		return "AppQueryStockVo [uproductid=" + uproductid + ", uproductstockid=" + uproductstockid + ", uproductpic="
				+ uproductpic + ", uproductname=" + uproductname + ", ufactoryname=" + ufactoryname + ", ustock="
				+ ustock + ", uunitid=" + uunitid + ", uunit=" + uunit + ", uprice=" + uprice + ", ucolor=" + ucolor
				+ ", unitList=" + unitList + ", uobjList=" + uobjList + "]";
	}
}
