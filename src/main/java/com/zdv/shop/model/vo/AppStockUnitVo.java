package com.zdv.shop.model.vo;

/**
 * APP库存单位 Vo实体
 * @author LBY
 * @date 2019年1月3日
 */
public class AppStockUnitVo {

	/**
	 * 单位ID
	 */
	private String unitId;
	/**
	 * 单位名称
	 */
	private String unitName;
	/**
	 * 与子换算数
	 */
	private String calerNum;
	/**
	 * 子单位名称
	 */
	private String childName;
	/**
	 * 价格
	 */
	private String price;
	/**
	 * 库存数量
	 */ 
	private Integer stockNum;
	/**
	 * 单位颜色
	 */
	private String ucolor;
	public String getUnitId() {
		return unitId;
	}
	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}
	public String getUnitName() {
		return unitName;
	}
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
	public String getCalerNum() {
		return calerNum;
	}
	public void setCalerNum(String calerNum) {
		this.calerNum = calerNum;
	}
	public String getChildName() {
		return childName;
	}
	public void setChildName(String childName) {
		this.childName = childName;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public Integer getStockNum() {
		return stockNum;
	}
	public void setStockNum(Integer stockNum) {
		this.stockNum = stockNum;
	}
	public String getUcolor() {
		return ucolor;
	}
	public void setUcolor(String ucolor) {
		this.ucolor = ucolor;
	}
	/**
	 * @author LBY
	 * @data 2019年1月23日
	 * @return
	 */
	@Override
	public String toString() {
		return "AppStockUnitVo [unitId=" + unitId + ", unitName=" + unitName + ", calerNum=" + calerNum + ", childName="
				+ childName + ", price=" + price + ", stockNum=" + stockNum + ", ucolor=" + ucolor + "]";
	}
}
