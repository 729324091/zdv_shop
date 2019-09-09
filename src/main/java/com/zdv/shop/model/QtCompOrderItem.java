package com.zdv.shop.model;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.math.BigDecimal;

/**
 * 经营下单详细表
 * @author JOCK
 * @data: 2019年1月11日
 */
@Table(name="qt_comp_order_item")
public class QtCompOrderItem  extends BaseObject{
	@Id	
	//订单项ID	
	private String uorderitemid;
	//订单ID	
	private String uorderid;
	//单品条码编号	
	private String uqrcode;
	//对应产品ID	
	private String uproductid;
	//产品生产厂家	
	private String ufactoryname;	
	//产品首图	
	private String uhomepic;	
	//经销商ID（卖方）	
	private String udistributorid;
	//销售商ID（买方 可能也是经销商）
	private String ucompid;
	//产品名称	
	private String uproductname;
	//产品单位ID	
	private String uunitid;//不同产品获得不同类型
	//产品数量	
	private Integer uproductnum;//是按照选择的产品单位的数量
	//状态
	private String ueflag;//状态 00下单中，01成交，02失败
	//产品单位	
	private String uunit;//比如：瓶、盒、箱
	//参考价格	
	private Double uprice;	
	//成本
	private Double ucostprice;
	//属性名	
	private String uobjnamevalue;//比如“颜色:红色|大小:40码”
	//创建时间	
	private String ucreatedate;

	//产品类数
	@Transient
	private Integer productTypeNum;
	@Transient
	private Integer productNum;
	@Transient
	private int uoutproductnum;//出货数
	@Transient
	private Double uouttotalmoney;//出货金额
	@Transient
	private String udate;//搜索当前日期
	@Transient
	private OtProduct otProduct;
	@Transient
	private String state;//0本经销商的单个产品销售统计，1某个店的单个产品销售统计，2业务员销售统计
	@Transient
	private String types;//统计类型 0按日统计，1按月统计，2按季度，3按年,4按周
	
	private BigDecimal umarketprice;

	public String getUorderitemid() {
		return uorderitemid;
	}

	public void setUorderitemid(String uorderitemid) {
		this.uorderitemid = uorderitemid;
	}

	public String getUorderid() {
		return uorderid;
	}

	public void setUorderid(String uorderid) {
		this.uorderid = uorderid;
	}

	public String getUqrcode() {
		return uqrcode;
	}

	public void setUqrcode(String uqrcode) {
		this.uqrcode = uqrcode;
	}

	public String getUproductid() {
		return uproductid;
	}

	public void setUproductid(String uproductid) {
		this.uproductid = uproductid;
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

	public String getUproductname() {
		return uproductname;
	}

	public void setUproductname(String uproductname) {
		this.uproductname = uproductname;
	}

	public String getUunitid() {
		return uunitid;
	}

	public void setUunitid(String uunitid) {
		this.uunitid = uunitid;
	}

	public Integer getUproductnum() {
		return uproductnum;
	}

	public void setUproductnum(Integer uproductnum) {
		this.uproductnum = uproductnum;
	}

	public String getUeflag() {
		return ueflag;
	}

	public void setUeflag(String ueflag) {
		this.ueflag = ueflag;
	}

	public String getUunit() {
		return uunit;
	}

	public void setUunit(String uunit) {
		this.uunit = uunit;
	}

	public Double getUprice() {
		return uprice;
	}

	public void setUprice(Double uprice) {
		this.uprice = uprice;
	}

	public Double getUcostprice() {
		return ucostprice;
	}

	public void setUcostprice(Double ucostprice) {
		this.ucostprice = ucostprice;
	}

	public String getUobjnamevalue() {
		return uobjnamevalue;
	}

	public void setUobjnamevalue(String uobjnamevalue) {
		this.uobjnamevalue = uobjnamevalue;
	}

	public String getUcreatedate() {
		return ucreatedate;
	}

	public void setUcreatedate(String ucreatedate) {
		this.ucreatedate = ucreatedate;
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

	public int getUoutproductnum() {
		return uoutproductnum;
	}

	public void setUoutproductnum(int uoutproductnum) {
		this.uoutproductnum = uoutproductnum;
	}

	public Double getUouttotalmoney() {
		return uouttotalmoney;
	}

	public void setUouttotalmoney(Double uouttotalmoney) {
		this.uouttotalmoney = uouttotalmoney;
	}

	public String getUdate() {
		return udate;
	}

	public void setUdate(String udate) {
		this.udate = udate;
	}

	public OtProduct getOtProduct() {
		return otProduct;
	}

	public void setOtProduct(OtProduct otProduct) {
		this.otProduct = otProduct;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getTypes() {
		return types;
	}

	public void setTypes(String types) {
		this.types = types;
	}

	public BigDecimal getUmarketprice() {
		return umarketprice;
	}

	public void setUmarketprice(BigDecimal umarketprice) {
		this.umarketprice = umarketprice;
	}

	/**
	 * @author LBY
	 * @data 2019年2月16日
	 * @return
	 */
	@Override
	public String toString() {
		return "QtCompOrderItem [uorderitemid=" + uorderitemid + ", uorderid=" + uorderid + ", uqrcode=" + uqrcode
				+ ", uproductid=" + uproductid + ", ufactoryname=" + ufactoryname + ", uhomepic=" + uhomepic
				+ ", udistributorid=" + udistributorid + ", ucompid=" + ucompid + ", uproductname=" + uproductname
				+ ", uunitid=" + uunitid + ", uproductnum=" + uproductnum + ", ueflag=" + ueflag
				+ ", uunit=" + uunit + ", uprice=" + uprice + ", ucostprice=" + ucostprice + ", uobjnamevalue="
				+ uobjnamevalue + ", ucreatedate=" + ucreatedate + ", productTypeNum=" + productTypeNum
				+ ", productNum=" + productNum + ", uoutproductnum=" + uoutproductnum + ", uouttotalmoney="
				+ uouttotalmoney + ", udate=" + udate + ", otProduct=" + otProduct + ", state=" + state + ", types="
				+ types + ", umarketprice=" + umarketprice + "]";
	}
}
