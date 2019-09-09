package com.zdv.shop.model;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.math.BigDecimal;

/**
 * 销售商关联产品表
 * @author LBY
 * @date: 2018年12月6日
 */
@Table(name = "ot_comp_to_product")
public class OtCompToProduct {

	@Id
	@GeneratedValue(generator="UUID")
	private String ucomproductid;
	/**
	 * 销售商id
	 */
	private String ucompid;


	/**
	 * 经销商id
	 */
	private String udistributorid;

	/**
	 * 推荐人id
	 */
	private String uuserid;

	/**
	 * 产品ID
	 */
	private String uproductid;
	/**
	 * 自定义产品类型ID
	 */
	private String uproducttypeid;
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
	 * 产品单位
	 */
	private String uunit;
	/**
	 * 单位ID
	 */
	private String uunitid;
	/**
	 * 产品属名称
	 */
	private String uobjname;
	/**
	 * 产品属性值
	 */
	private String uobjvalue;
	/**
	 * 进货价
	 *//*
	private BigDecimal ubuyprice;*/
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
	 * 是否促销
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
	private Long urefreshdate;
	/**
	 * 是否置顶
	 */
	private Boolean uiftop;
	/**
	 * 是否有效（‘01’上架，‘02’下架）
	 * 关联 com.zdv.shop.model.enums.PRODUCT_STATUS
	 */
	private String uflag;
	/**
	 * 成本价
	 */
	private BigDecimal ucostprice;


	/**
	 * 免费送促销 1vip用户每月可以领一次，2vip用户只能领一次
	 */
	private String upromotesales;


	private String uintegral;

	private String uintegralflag;

	/**
	 * 销售商名称
	 */
	@Transient
	private String ucompname;

	@Transient
	private String uhomepic;


	public String getUintegral() {
		return uintegral;
	}
	public void setUintegral(String uintegral) {
		this.uintegral = uintegral;
	}

	public String getUintegralflag() {
		return uintegralflag;
	}

	public void setUintegralflag(String uintegrealflag) {
		this.uintegralflag = uintegrealflag;
	}

	public String getUpromotesales() {
		return upromotesales;
	}

	public void setUpromotesales(String upromotesales) {
		this.upromotesales = upromotesales;
	}

	public String getUhomepic() {
		return uhomepic;
	}

	public void setUhomepic(String uhomepic) {
		this.uhomepic = uhomepic;
	}

	public String getUdistributorid() {
		return udistributorid;
	}

	public void setUdistributorid(String udistributorid) {
		this.udistributorid = udistributorid;
	}

	public String getUuserid() {
		return uuserid;
	}

	public void setUuserid(String uuserid) {
		this.uuserid = uuserid;
	}

	public String getUcomproductid() {
		return ucomproductid;
	}
	public void setUcomproductid(String ucomproductid) {
		this.ucomproductid = ucomproductid;
	}
	public String getUcompid() {
		return ucompid;
	}
	public void setUcompid(String ucompid) {
		this.ucompid = ucompid;
	}
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
	public String getUunit() {
		return uunit;
	}
	public void setUunit(String uunit) {
		this.uunit = uunit;
	}
	public String getUunitid() {
		return uunitid;
	}
	public void setUunitid(String uunitid) {
		this.uunitid = uunitid;
	}
	public String getUobjname() {
		return uobjname;
	}
	public void setUobjname(String uobjname) {
		this.uobjname = uobjname;
	}
	public String getUobjvalue() {
		return uobjvalue;
	}
	public void setUobjvalue(String uobjvalue) {
		this.uobjvalue = uobjvalue;
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
	public Long getUrefreshdate() {
		return urefreshdate;
	}
	public void setUrefreshdate(Long urefreshdate) {
		this.urefreshdate = urefreshdate;
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

	public BigDecimal getUcostprice() {
		return ucostprice;
	}

	public void setUcostprice(BigDecimal ucostprice) {
		this.ucostprice = ucostprice;
	}

	public String getUcompname() {
		return ucompname;
	}
	public void setUcompname(String ucompname) {
		this.ucompname = ucompname;
	}
	/**
	 * @author LBY
	 * @data 2019年3月1日
	 * @return
	 */
	@Override
	public String toString() {
		return "OtCompToProduct [ucomproductid=" + ucomproductid + ", ucompid=" + ucompid + ", uproductid=" + uproductid
				+ ", uproducttypeid=" + uproducttypeid + ", ualias=" + ualias + ", ucode=" + ucode + ", uparentid="
				+ uparentid + ", uunit=" + uunit + ", uunitid=" + uunitid + ", uobjname=" + uobjname + ", uobjvalue="
				+ uobjvalue +  ", umarketprice=" + umarketprice + ", uprice=" + uprice
				+ ", uvipprice=" + uvipprice + ", uifsales=" + uifsales + ", usalesprice=" + usalesprice
				+ ", usalesBegindate=" + usalesBegindate + ", usalesEnddate=" + usalesEnddate + ", ustock=" + ustock
				+ ", usalesnum=" + usalesnum + ", ubrowsepoint=" + ubrowsepoint + ", urefreshdate=" + urefreshdate
				+ ", uiftop=" + uiftop + ", uflag=" + uflag + ", ucostprice=" + ucostprice + ", ucompname=" + ucompname
				+ "]";
	}
}
