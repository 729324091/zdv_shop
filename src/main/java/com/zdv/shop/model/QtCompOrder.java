package com.zdv.shop.model;

import java.util.List;

import javax.persistence.Table;
import javax.persistence.Transient;

@Table(name="qt_comp_order")
public class QtCompOrder  extends BaseObject{

	//订单唯一标识
	private String uorderid;//	
	private String uorderno;//订单号
	private Integer uproductnum;//产品数量
	private Double utotalmoney;//总金额
	private String udistributorid;//经销商ID（发货方）
	private String udname;//经销商（供应商）名称
	private String uaddress;//经销商地址
	private String uareaid;//经销商地区ID
	private Double ulatitude;//经销商经度
	private Double ulongitude;//经销商纬度
	private String ucontact;//经销商联系人
	private String utel;//经销商电话
	private String ulogo;//经销商logo
	private String ucomptype;//进货方类型	0为销售商，1为经销商
	private String ucompid;//销售商ID(或经销商  进货方 )
	private String ucname;//销售商名称(或经销商  进货方 )
	private String ucaddress;//销售商地址
	private String ucareaid;//销售商地区ID
	private Double uclatitude;//销售商经度
	private Double uclongitude;//销售商纬度
	private String uccontact;//销售商联系人
	private String uctel;//销售商电话
	private String uclogo;//销售商logo
	private String ueflag;//状态（收货） 00待付款 01待发货 02已发货 03完成交易
	private String upeflag;//经销商状态（发货）00待付款 01待发货 02已发货 03完成交易
	private String ucreatedate;//创建时间
	@Transient
	private String types;//统计类型 0按日统计，1按月统计，2按季度，3按年
	@Transient
	private String uopname;//姓名
	@Transient
	private String umobile;//手机号码
	@Transient
	private String uregdate;//注册时间
	@Transient
	private String urolename;//角色名称
	@Transient
	private String uopuserid;//管理员ID
	//产品类数
	@Transient
	private Integer productTypeNum;
	@Transient
	private Integer productNum;
	@Transient
	private Integer uoutproductnum;//出货数
	@Transient
	private Double uouttotalmoney;//出货金额
	
	@Transient
	private String uproductid;
	@Transient
	private String uproductname;//
	@Transient
	private String uunit;
	@Transient
	private String ufactoryname;
	@Transient
	private String uhomepic;
	@Transient
	private String udate;//搜索当前日期
	@Transient
	private List<QtCompOrderItem> orderItemList;	// 订单项
	public String getUorderid() {
		return uorderid;
	}
	public void setUorderid(String uorderid) {
		this.uorderid = uorderid;
	}
	public String getUorderno() {
		return uorderno;
	}
	public void setUorderno(String uorderno) {
		this.uorderno = uorderno;
	}
	public Integer getUproductnum() {
		return uproductnum;
	}
	public void setUproductnum(Integer uproductnum) {
		this.uproductnum = uproductnum;
	}
	public Double getUtotalmoney() {
		return utotalmoney;
	}
	public void setUtotalmoney(Double utotalmoney) {
		this.utotalmoney = utotalmoney;
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
	public String getUaddress() {
		return uaddress;
	}
	public void setUaddress(String uaddress) {
		this.uaddress = uaddress;
	}
	public String getUareaid() {
		return uareaid;
	}
	public void setUareaid(String uareaid) {
		this.uareaid = uareaid;
	}
	public Double getUlatitude() {
		return ulatitude;
	}
	public void setUlatitude(Double ulatitude) {
		this.ulatitude = ulatitude;
	}
	public Double getUlongitude() {
		return ulongitude;
	}
	public void setUlongitude(Double ulongitude) {
		this.ulongitude = ulongitude;
	}
	public String getUcontact() {
		return ucontact;
	}
	public void setUcontact(String ucontact) {
		this.ucontact = ucontact;
	}
	public String getUtel() {
		return utel;
	}
	public void setUtel(String utel) {
		this.utel = utel;
	}
	public String getUlogo() {
		return ulogo;
	}
	public void setUlogo(String ulogo) {
		this.ulogo = ulogo;
	}
	public String getUcomptype() {
		return ucomptype;
	}
	public void setUcomptype(String ucomptype) {
		this.ucomptype = ucomptype;
	}
	public String getUcompid() {
		return ucompid;
	}
	public void setUcompid(String ucompid) {
		this.ucompid = ucompid;
	}
	public String getUcname() {
		return ucname;
	}
	public void setUcname(String ucname) {
		this.ucname = ucname;
	}
	public String getUcaddress() {
		return ucaddress;
	}
	public void setUcaddress(String ucaddress) {
		this.ucaddress = ucaddress;
	}
	public String getUcareaid() {
		return ucareaid;
	}
	public void setUcareaid(String ucareaid) {
		this.ucareaid = ucareaid;
	}
	public Double getUclatitude() {
		return uclatitude;
	}
	public void setUclatitude(Double uclatitude) {
		this.uclatitude = uclatitude;
	}
	public Double getUclongitude() {
		return uclongitude;
	}
	public void setUclongitude(Double uclongitude) {
		this.uclongitude = uclongitude;
	}
	public String getUccontact() {
		return uccontact;
	}
	public void setUccontact(String uccontact) {
		this.uccontact = uccontact;
	}
	public String getUctel() {
		return uctel;
	}
	public void setUctel(String uctel) {
		this.uctel = uctel;
	}
	public String getUclogo() {
		return uclogo;
	}
	public void setUclogo(String uclogo) {
		this.uclogo = uclogo;
	}
	public String getUeflag() {
		return ueflag;
	}
	public void setUeflag(String ueflag) {
		this.ueflag = ueflag;
	}
	public String getUpeflag() {
		return upeflag;
	}
	public void setUpeflag(String upeflag) {
		this.upeflag = upeflag;
	}
	public String getUcreatedate() {
		return ucreatedate;
	}
	public void setUcreatedate(String ucreatedate) {
		this.ucreatedate = ucreatedate;
	}
	public String getTypes() {
		return types;
	}
	public void setTypes(String types) {
		this.types = types;
	}
	public String getUopname() {
		return uopname;
	}
	public void setUopname(String uopname) {
		this.uopname = uopname;
	}
	public String getUmobile() {
		return umobile;
	}
	public void setUmobile(String umobile) {
		this.umobile = umobile;
	}
	public String getUregdate() {
		return uregdate;
	}
	public void setUregdate(String uregdate) {
		this.uregdate = uregdate;
	}
	public String getUrolename() {
		return urolename;
	}
	public void setUrolename(String urolename) {
		this.urolename = urolename;
	}
	public String getUopuserid() {
		return uopuserid;
	}
	public void setUopuserid(String uopuserid) {
		this.uopuserid = uopuserid;
	}
	public Integer getProductTypeNum() {
		return productTypeNum;
	}
	public void setProductTypeNum(Integer productTypeNum) {
		this.productTypeNum = productTypeNum;
	}
	public Integer getProductNum() {
		return productNum;
	}
	public void setProductNum(Integer productNum) {
		this.productNum = productNum;
	}
	public Integer getUoutproductnum() {
		return uoutproductnum;
	}
	public void setUoutproductnum(Integer uoutproductnum) {
		this.uoutproductnum = uoutproductnum;
	}
	public Double getUouttotalmoney() {
		return uouttotalmoney;
	}
	public void setUouttotalmoney(Double uouttotalmoney) {
		this.uouttotalmoney = uouttotalmoney;
	}
	public String getUproductid() {
		return uproductid;
	}
	public void setUproductid(String uproductid) {
		this.uproductid = uproductid;
	}
	public String getUproductname() {
		return uproductname;
	}
	public void setUproductname(String uproductname) {
		this.uproductname = uproductname;
	}
	public String getUunit() {
		return uunit;
	}
	public void setUunit(String uunit) {
		this.uunit = uunit;
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
	public String getUdate() {
		return udate;
	}
	public void setUdate(String udate) {
		this.udate = udate;
	}
	public List<QtCompOrderItem> getOrderItemList() {
		return orderItemList;
	}
	public void setOrderItemList(List<QtCompOrderItem> orderItemList) {
		this.orderItemList = orderItemList;
	}
	/**
	 * @author LBY
	 * @data 2019年2月20日
	 * @return
	 */
	@Override
	public String toString() {
		return "QtCompOrder [uorderid=" + uorderid + ", uorderno=" + uorderno + ", uproductnum=" + uproductnum
				+ ", utotalmoney=" + utotalmoney + ", udistributorid=" + udistributorid + ", udname=" + udname
				+ ", uaddress=" + uaddress + ", uareaid=" + uareaid + ", ulatitude=" + ulatitude + ", ulongitude="
				+ ulongitude + ", ucontact=" + ucontact + ", utel=" + utel + ", ulogo=" + ulogo + ", ucomptype="
				+ ucomptype + ", ucompid=" + ucompid + ", ucname=" + ucname + ", ucaddress=" + ucaddress + ", ucareaid="
				+ ucareaid + ", uclatitude=" + uclatitude + ", uclongitude=" + uclongitude + ", uccontact=" + uccontact
				+ ", uctel=" + uctel + ", uclogo=" + uclogo + ", ueflag=" + ueflag + ", upeflag=" + upeflag
				+ ", ucreatedate=" + ucreatedate + ", types=" + types + ", uopname=" + uopname + ", umobile=" + umobile
				+ ", uregdate=" + uregdate + ", urolename=" + urolename + ", uopuserid=" + uopuserid
				+ ", productTypeNum=" + productTypeNum + ", productNum=" + productNum + ", uoutproductnum="
				+ uoutproductnum + ", uouttotalmoney=" + uouttotalmoney + ", uproductid=" + uproductid
				+ ", uproductname=" + uproductname + ", uunit=" + uunit + ", ufactoryname=" + ufactoryname
				+ ", uhomepic=" + uhomepic + ", udate=" + udate + ", orderItemList=" + orderItemList + "]";
	}
}
