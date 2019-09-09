package com.zdv.shop.model.vo;

/**
 * 销售产品Vo
 * @author LBY
 * @data 2019年2月16日 
 */
public class SalesProductVO {

	/**
	 * 产品ID
	 */
	private String uproductid;
	/**
	 * 产品展示图
	 */
	private String uhomepic;
	public String getUproductid() {
		return uproductid;
	}
	public void setUproductid(String uproductid) {
		this.uproductid = uproductid;
	}
	public String getUhomepic() {
		return uhomepic;
	}
	public void setUhomepic(String uhomepic) {
		this.uhomepic = uhomepic;
	}
	/**
	 * @author LBY
	 * @data 2019年2月16日
	 * @return
	 */
	@Override
	public String toString() {
		return "SalesProductVO [uproductid=" + uproductid + ", uhomepic=" + uhomepic + "]";
	}
}
