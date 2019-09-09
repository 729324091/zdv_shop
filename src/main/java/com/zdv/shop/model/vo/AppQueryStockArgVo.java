package com.zdv.shop.model.vo;

/**
 * APP查询库存参数Vo
 * @author LBY
 * @data 2019年1月23日 
 */
public class AppQueryStockArgVo {
	
	/**
	 * 经销商ID
	 */
	private String udistributorid;
	/**
	 * 销售商ID
	 */
	private String ucompid;
	/**
	 * 产品类型ID
	 */
	private String uproducttypeid;
	/**
	 * 关键字，根据产品名称模糊查询
	 */
	private String keywords;
	/**
	 * 库存数量，查询小于等于该指数的记录
	 */
	private Integer ustock;
	/**
	 * 产品二维码编号
	 */
	private String uqrcode;
	public String getUdistributorid() {
		return udistributorid;
	}
	public void setUdistributorid(String udistributorid) {
		this.udistributorid = udistributorid;
	}
	public String getUcompid() {
		return ucompid;
	}
	public void setUcompid(String ucompid) {
		this.ucompid = ucompid;
	}
	public String getUproducttypeid() {
		return uproducttypeid;
	}
	public void setUproducttypeid(String uproducttypeid) {
		this.uproducttypeid = uproducttypeid;
	}
	public String getKeywords() {
		return keywords;
	}
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	public Integer getUstock() {
		return ustock;
	}
	public void setUstock(Integer ustock) {
		this.ustock = ustock;
	}
	public String getUqrcode() {
		return uqrcode;
	}
	public void setUqrcode(String uqrcode) {
		this.uqrcode = uqrcode;
	}
	/**
	 * @author LBY
	 * @data 2019年2月19日
	 * @return
	 */
	@Override
	public String toString() {
		return "AppQueryStockArgVo [udistributorid=" + udistributorid + ", ucompid=" + ucompid + ", uproducttypeid="
				+ uproducttypeid + ", keywords=" + keywords + ", ustock=" + ustock + ", uqrcode=" + uqrcode + "]";
	}
}
