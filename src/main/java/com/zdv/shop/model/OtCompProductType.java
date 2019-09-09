package com.zdv.shop.model;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 销售商自定义产品类型表
 * @author LBY
 * @date: 2018年12月6日
 */
@Table(name = "ot_comp_producttype")
public class OtCompProductType {
	
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
	 *
	 */
	private String ucompid;
	/**
	 * 字体颜色
	 */
	private String ucolor;
	/**
	 * 父级ID
	 */
	private String uparentid;


	/**
	 * 商户号
	 */
	private String ucustomerid;
	private String uhomepic;
	private String uflag;

	private Integer uorderno;
	public String getUcustomerid() {
		return ucustomerid;
	}

	public void setUcustomerid(String ucustomerid) {
		this.ucustomerid = ucustomerid;
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

	public String getUcolor() {
		return ucolor;
	}
	public void setUcolor(String ucolor) {
		this.ucolor = ucolor;
	}
	public String getUparentid() {
		return uparentid;
	}
	public void setUparentid(String uparentid) {
		this.uparentid = uparentid;
	}


	public String getUcompid() {
		return ucompid;
	}

	public void setUcompid(String ucompid) {
		this.ucompid = ucompid;
	}

	public String getUhomepic() {
		return uhomepic;
	}

	public void setUhomepic(String uhomepic) {
		this.uhomepic = uhomepic;
	}

	public String getUflag() {
		return uflag;
	}

	public void setUflag(String uflag) {
		this.uflag = uflag;
	}

	public Integer getUorderno() {
		return uorderno;
	}

	public void setUorderno(Integer uorderno) {
		this.uorderno = uorderno;
	}
}
