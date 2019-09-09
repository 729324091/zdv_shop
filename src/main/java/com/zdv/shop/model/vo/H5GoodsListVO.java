package com.zdv.shop.model.vo;

/**
 * @author LBY
 * @data 2019年2月25日 
 */
public class H5GoodsListVO {

	private String ucustomerid;
	/**
	 * 最低价格
	 */
	private Integer priceMin;
	/**
	 * 最高价格
	 */
	private Integer priceMax;
	/**
	 * 排序字段
	 */
	private String sort;
	/**
	 * 排序类型
	 */
	private String order;
	/**
	 * 搜索关键字
	 */
	private String keywords;
	/**
	 * 当前页码
	 */
	private Integer pageNo;
	/**
	 * 每页显示条数
	 */
	private Integer pageSize;


	public String getUcustomerid() {
		return ucustomerid;
	}

	public void setUcustomerid(String ucustomerid) {
		this.ucustomerid = ucustomerid;
	}

	public Integer getPriceMin() {
		return priceMin;
	}
	public void setPriceMin(Integer priceMin) {
		this.priceMin = priceMin;
	}
	public Integer getPriceMax() {
		return priceMax;
	}
	public void setPriceMax(Integer priceMax) {
		this.priceMax = priceMax;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}
	public String getKeywords() {
		return keywords;
	}
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	public Integer getPageNo() {
		return pageNo;
	}
	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	@Override
	public String toString() {
		return "H5GoodsListVO [priceMin=" + priceMin + ", priceMax=" + priceMax + ", sort=" + sort + ", order=" + order
				+ ", keywords=" + keywords + ", pageNo=" + pageNo + ", pageSize=" + pageSize + "]";
	}
}
