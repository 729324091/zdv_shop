package com.zdv.shop.model;

import java.util.List;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 产品类型
 * @author LBY
 * @date: 2018年12月6日
 */
@Table(name = "ot_producttype")
public class OtProducttype {

	/**
	 * 产品类型ID
	 */
	@Id
	private String uproducttypeid;
	/**
	 * 产品类型名称
	 */
	private String uname;
	/**
	 * 父级ID
	 */
	private String uparentid;
	/**
	 * 是否首页显示
	 */
	private String uhomepic;
	//排序字段
	private String uorderno;
	/**
	 * 子产品类型
	 */
	@Transient
	private List<OtProducttype> uchildList;
	public String getUhomepic() {
		return uhomepic;
	}
	public void setUhomepic(String uhomepic) {
		this.uhomepic = uhomepic;
	}
	public String getUproducttypeid() {
		return uproducttypeid;
	}
	public void setUproducttypeid(String uproducttypeid) {
		this.uproducttypeid = uproducttypeid;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getUparentid() {
		return uparentid;
	}
	public void setUparentid(String uparentid) {
		this.uparentid = uparentid;
	}
	public String getUorderno() {
		return uorderno;
	}
	public void setUorderno(String uorderno) {
		this.uorderno = uorderno;
	}
	public List<OtProducttype> getUchildList() {
		return uchildList;
	}
	public void setUchildList(List<OtProducttype> uchildList) {
		this.uchildList = uchildList;
	}
	@Override
	public String toString() {
		return "OtProducttype [uproducttypeid=" + uproducttypeid + ", uname=" + uname + ", uparentid=" + uparentid
				+ ", uorderno=" + uorderno + ", uchildList=" + uchildList + "]";
	}
}
