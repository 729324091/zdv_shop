package com.zdv.shop.model;

import java.math.BigDecimal;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 经销商关联产品
 * @author LBY
 * @date: 2018年12月6日
 */
@Table(name = "ot_distributor_to_product")
public class OtDistributorToProduct {

	@Id
	private String udistribproductid;
	/**
	 * 经销商（供应商）主键ID
	 */
	private String udistributorid;
	/**
	 * 经销商名称
	 */
	@Transient
	private String udname;
	/**
	 * 产品ID
	 */
	private String uproductid;
	/**
	 * 产品别名
	 */
	private String ualias;
	/**
	 * 自定义编号
	 */
	private String ucode;
	/**
	 * 父级ID
	 */
	private String uparentid;
	/**
	 * 单位ID
	 */
	private String uunitid;
	/**
	 * 产品单位
	 */
	private String uunit;
	/**
	 * 属性名
	 */
	private String uobjnamevalue;
	/**
	 * 市场价格
	 */
	private BigDecimal umarketprice;
	/**
	 * 销售价
	 */
	private BigDecimal uprice;
	/**
	 * 会员价
	 */
	private BigDecimal uvipprice;
	/**
	 * 是否促销(0不促销，1促销）
	 */
	private Boolean uifsales;
	/**
	 * 促销价
	 */
	private BigDecimal usalesprice;
	/**
	 * 促销开始时间
	 */
	private String usalesBegindate;
	/**
	 * 促销结束时间
	 */
	private String usalesEnddate;
	/**
	 * 成本价
	 */
	private BigDecimal ucostprice;
	/**
	 * 库存
	 */
	private Integer ustock;
	/**
	 * 总销售量
	 */
	private Integer usalesnum;
	/**
	 * 浏览量
	 */
	private Integer ubrowsepoint;
	/**
	 * 刷新时间
	 */
	private Long ufinishflag;
	/**
	 * 是否置顶
	 */
	private Boolean uiftop;
	/**
	 * 是否有效(01上架,02下架)
	 */
	private String uflag;
	
	/*//订单项id
	private String uorderitemid;
	//订单id
	private String uorderid;
	*/
	/**
	 * 产品信息
	 * */
	@Transient
	private OtProduct otProduct;

	public String getUdistribproductid() {
		return udistribproductid;
	}

	public void setUdistribproductid(String udistribproductid) {
		this.udistribproductid = udistribproductid;
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

	public void setUname(String udname) {
		this.udname = udname;
	}

	public String getUproductid() {
		return uproductid;
	}

	public void setUproductid(String uproductid) {
		this.uproductid = uproductid;
	}

	public String getUalias() {
		return ualias;
	}

	public void setUalias(String ualias) {
		this.ualias = ualias;
	}

	public String getUcode() {
		return ucode;
	}

	public void setUcode(String ucode) {
		this.ucode = ucode;
	}

	public String getUparentid() {
		return uparentid;
	}

	public void setUparentid(String uparentid) {
		this.uparentid = uparentid;
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

	public String getUobjnamevalue() {
		return uobjnamevalue;
	}

	public void setUobjnamevalue(String uobjnamevalue) {
		this.uobjnamevalue = uobjnamevalue;
	}

	public BigDecimal getUmarketprice() {
		return umarketprice;
	}

	public void setUmarketprice(BigDecimal umarketprice) {
		this.umarketprice = umarketprice;
	}

	public BigDecimal getUprice() {
		return uprice;
	}

	public void setUprice(BigDecimal uprice) {
		this.uprice = uprice;
	}

	public BigDecimal getUvipprice() {
		return uvipprice;
	}

	public void setUvipprice(BigDecimal uvipprice) {
		this.uvipprice = uvipprice;
	}

	public Boolean getUifsales() {
		return uifsales;
	}

	public void setUifsales(Boolean uifsales) {
		this.uifsales = uifsales;
	}

	public BigDecimal getUsalesprice() {
		return usalesprice;
	}

	public void setUsalesprice(BigDecimal usalesprice) {
		this.usalesprice = usalesprice;
	}

	public String getUsalesBegindate() {
		return usalesBegindate;
	}

	public void setUsalesBegindate(String usalesBegindate) {
		this.usalesBegindate = usalesBegindate;
	}

	public String getUsalesEnddate() {
		return usalesEnddate;
	}

	public void setUsalesEnddate(String usalesEnddate) {
		this.usalesEnddate = usalesEnddate;
	}

	public BigDecimal getUcostprice() {
		return ucostprice;
	}

	public void setUcostprice(BigDecimal ucostprice) {
		this.ucostprice = ucostprice;
	}

	public Integer getUstock() {
		return ustock;
	}

	public void setUstock(Integer ustock) {
		this.ustock = ustock;
	}

	public Integer getUsalesnum() {
		return usalesnum;
	}

	public void setUsalesnum(Integer usalesnum) {
		this.usalesnum = usalesnum;
	}

	public Integer getUbrowsepoint() {
		return ubrowsepoint;
	}

	public void setUbrowsepoint(Integer ubrowsepoint) {
		this.ubrowsepoint = ubrowsepoint;
	}

	public Long getUfinishflag() {
		return ufinishflag;
	}

	public void setUfinishflag(Long ufinishflag) {
		this.ufinishflag = ufinishflag;
	}

	public Boolean getUiftop() {
		return uiftop;
	}

	public void setUiftop(Boolean uiftop) {
		this.uiftop = uiftop;
	}

	public String getUflag() {
		return uflag;
	}

	public void setUflag(String uflag) {
		this.uflag = uflag;
	}

	public OtProduct getOtProduct() {
		return otProduct;
	}

	public void setOtProduct(OtProduct otProduct) {
		this.otProduct = otProduct;
	}

	/**
	 * @author LBY
	 * @data 2019年2月15日
	 * @return
	 */
	@Override
	public String toString() {
		return "OtDistributorToProduct [udistribproductid=" + udistribproductid + ", udistributorid=" + udistributorid
				+ ", udname=" + udname + ", uproductid=" + uproductid + ", ualias=" + ualias + ", ucode=" + ucode
				+ ", uparentid=" + uparentid + ", uunitid=" + uunitid + ", uunit=" + uunit + ", uobjnamevalue="
				+ uobjnamevalue + ", umarketprice=" + umarketprice + ", uprice=" + uprice + ", uvipprice=" + uvipprice
				+ ", uifsales=" + uifsales + ", usalesprice=" + usalesprice + ", usalesBegindate=" + usalesBegindate
				+ ", usalesEnddate=" + usalesEnddate + ", ucostprice=" + ucostprice + ", ustock=" + ustock
				+ ", usalesnum=" + usalesnum + ", ubrowsepoint=" + ubrowsepoint + ", ufinishflag=" + ufinishflag
				+ ", uiftop=" + uiftop + ", uflag=" + uflag + ", otProduct=" + otProduct + "]";
	}
}
