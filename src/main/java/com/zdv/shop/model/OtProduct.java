package com.zdv.shop.model;

import java.math.BigDecimal;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 产品实体类
 * @author LBY
 * @date: 2018年12月6日
 */
@Table(name = "ot_product")
public class OtProduct {
	
	@Id
	@GeneratedValue(generator="UUID")
	private String uproductid;
	/**
	 * 产品类型
	 */
	private String uproducttypeid;
	/**
	 * 产品名称
	 */
	private String uproductname;
	/**
	 * 来源
	 */
	private String usource;
	/**
	 * 管理员ID(录入)
	 */
	private String uopuserid;
	/**
	 * 产品二维码编号
	 */
	private String uqrcode;
	/**
	 * 产品主码二维码编号
	 */
	private String umainqrcode;
	/**
	 * 产品单位
	 */
	private String uunit;
	/**
	 * 参考价格
	 */
	private BigDecimal uprice;
	/**
	 * 区域id
	 */
	private String uareaid;
	/**
	 * 区域名称
	 */
	private String uareaname;
	//厂家名称
	private String ufactoryname;

	/**
	 * 列表显示图
	 */
	private String uhomepic;
	/**
	 * 是否有效
	 */
	private String uflag;
	/**
	 * 描述
	 */
	private String udesc;
	/**
	 * 浏览量
	 */
	private Integer ubrowsepoint;
	/**
	 * 创建时间
	 */
	private Long ucreatedate;
	
	/**
	 * 供应商
	 * */
	private String udistributorid;
	
	/**
	 * 供应商名称
	 */
	@Transient
	private String udname;
	
	/**
	 * 产品数量
	 **/
	@Transient
	private String productStock;
	
	public String getUproductid() {
		return uproductid;
	}

	public void setUproductid(String uproductid) {
		this.uproductid = uproductid;
	}

	public String getUproducttypeid() {
		return uproducttypeid;
	}

	public void setUproducttypeid(String uproducttypeid) {
		this.uproducttypeid = uproducttypeid;
	}

	public String getUproductname() {
		return uproductname;
	}

	public void setUproductname(String uproductname) {
		this.uproductname = uproductname;
	}

	public String getUsource() {
		return usource;
	}

	public void setUsource(String usource) {
		this.usource = usource;
	}

	public String getUopuserid() {
		return uopuserid;
	}

	public void setUopuserid(String uopuserid) {
		this.uopuserid = uopuserid;
	}

	public String getUqrcode() {
		return uqrcode;
	}

	public void setUqrcode(String uqrcode) {
		this.uqrcode = uqrcode;
	}

	public String getUmainqrcode() {
		return umainqrcode;
	}

	public void setUmainqrcode(String umainqrcode) {
		this.umainqrcode = umainqrcode;
	}

	public String getUunit() {
		return uunit;
	}

	public void setUunit(String uunit) {
		this.uunit = uunit;
	}

	public BigDecimal getUprice() {
		return uprice;
	}

	public void setUprice(BigDecimal uprice) {
		this.uprice = uprice;
	}

	public String getUareaid() {
		return uareaid;
	}

	public void setUareaid(String uareaid) {
		this.uareaid = uareaid;
	}

	public String getUareaname() {
		return uareaname;
	}

	public void setUareaname(String uareaname) {
		this.uareaname = uareaname;
	}

	public String getUfactoryname() {
		return ufactoryname;
	}

	public void setUfactoryname(String ufactoryname) {
		this.ufactoryname = ufactoryname;
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

	public String getUdesc() {
		return udesc;
	}

	public void setUdesc(String udesc) {
		this.udesc = udesc;
	}

	public Integer getUbrowsepoint() {
		return ubrowsepoint;
	}

	public void setUbrowsepoint(Integer ubrowsepoint) {
		this.ubrowsepoint = ubrowsepoint;
	}

	public Long getUcreatedate() {
		return ucreatedate;
	}

	public void setUcreatedate(Long ucreatedate) {
		this.ucreatedate = ucreatedate;
	}

	public String getUdistributorid() {
		return udistributorid;
	}

	public void setUdistributorid(String udistributorid) {
		this.udistributorid = udistributorid;
	}

	public String getUdname() {
		return udname;
	}

	public void setUdname(String udname) {
		this.udname = udname;
	}

	public String getProductStock() {
		return productStock;
	}

	public void setProductStock(String productStock) {
		this.productStock = productStock;
	}

	/**
	 * @author LBY
	 * @data 2019年1月23日
	 * @return
	 */
	@Override
	public String toString() {
		return "OtProduct [uproductid=" + uproductid + ", uproducttypeid=" + uproducttypeid + ", uproductname="
				+ uproductname + ", usource=" + usource + ", uopuserid=" + uopuserid + ", uqrcode=" + uqrcode
				+ ", umainqrcode=" + umainqrcode + ", uunit=" + uunit + ", uprice=" + uprice + ", uareaid=" + uareaid
				+ ", uareaname=" + uareaname + ", ufactoryname=" + ufactoryname + ", uhomepic=" + uhomepic + ", uflag="
				+ uflag + ", udesc=" + udesc + ", ubrowsepoint=" + ubrowsepoint + ", ucreatedate=" + ucreatedate
				+ ", udistributorid=" + udistributorid + ", udname=" + udname + ", productStock=" + productStock + "]";
	}
}
