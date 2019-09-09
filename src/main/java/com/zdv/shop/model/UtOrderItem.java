package com.zdv.shop.model;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Table(name = "ut_order_item")
public class UtOrderItem extends BaseObject{


	@Id
    private String uorderitemid;  //订单项唯一标识
    private String uorderid;  //订单ID
    private String ucompid;  //销售商主键
    private String uproductid;  //商品ID
	private String ucomproductid; //销售商关联商品id

	private String uproductstockid; //规格id

	private String uopuserid;   //谁操作的订单
    private String uuserid;		// 用户ID
    private String uproductnum;		// 产品数量
    private String uproductname;  //产品名称
    private String udiscount;  //商品打折
    private Double umarketprice;//市场价格
    private Double uprice;  //单价格
    private Double upayprice;  //支付价格
    private String ucosttype;  //消费类型(1购买，0赠送)
    private String uunit;  //产品单位
    private String uunitid;	// 产品单位ID
    private String uobjnamevalue;  //属性值
    private String udistributorid;  //经销商ID
    private String udname;  //经销商（供应商）名称
    private String upaystatus;  //是否付款
    private Double uprofit;  //利润
    private Double ucostprice;  //本价成
    private Double ucost;		// 成本
    private String ucreatedate;  //创建时间
    private String uhomepic;
    @Transient
    private String ufactoryname;
    @Transient
    private Double utotalprice;  //总价格
    @Transient
    private String type;//销售货为1，进货为0


	@Transient
	private String ucaddress;    //商家地址
	@Transient
	private String uccontact;    //商家联系人
	@Transient
	private String uctel;        //商家电话
	@Transient
	private String uclogo;       //商家logo
	@Transient
	private String ucompname;  //商家名


	@Transient
	private String upromotesales;


	public String getUpromotesales() {
		return upromotesales;
	}

	public void setUpromotesales(String upromotesales) {
		this.upromotesales = upromotesales;
	}

	public String getUcomproductid() {
		return ucomproductid;
	}

	public void setUcomproductid(String ucomproductid) {
		this.ucomproductid = ucomproductid;
	}

	public String getUproductstockid() {
		return uproductstockid;
	}

	public void setUproductstockid(String uproductstockid) {
		this.uproductstockid = uproductstockid;
	}

	public void setUdname(String udname) {
		this.udname = udname;
	}

	public String getUcompname() {
		return ucompname;
	}

	public void setUcompname(String ucompname) {
		this.ucompname = ucompname;
	}

	public String getUcaddress() {
		return ucaddress;
	}

	public void setUcaddress(String ucaddress) {
		this.ucaddress = ucaddress;
	}

	public String getUopuserid() {
		return uopuserid;
	}

	public void setUopuserid(String uopuserid) {
		this.uopuserid = uopuserid;
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
	public String getUuserid() {
		return uuserid;
	}
	public void setUuserid(String uuserid) {
		this.uuserid = uuserid;
	}
	public String getUproductnum() {
		return uproductnum;
	}
	public void setUproductnum(String uproductnum) {
		this.uproductnum = uproductnum;
	}
	public String getUproductname() {
		return uproductname;
	}
	public void setUproductname(String uproductname) {
		this.uproductname = uproductname;
	}
	public String getUdiscount() {
		return udiscount;
	}
	public void setUdiscount(String udiscount) {
		this.udiscount = udiscount;
	}
	public Double getUmarketprice() {
		return umarketprice;
	}
	public void setUmarketprice(Double umarketprice) {
		this.umarketprice = umarketprice;
	}
	public Double getUprice() {
		return uprice;
	}
	public void setUprice(Double uprice) {
		this.uprice = uprice;
	}
	public Double getUpayprice() {
		return upayprice;
	}
	public void setUpayprice(Double upayprice) {
		this.upayprice = upayprice;
	}
	public String getUcosttype() {
		return ucosttype;
	}
	public void setUcosttype(String ucosttype) {
		this.ucosttype = ucosttype;
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
	public String getUobjnamevalue() {
		return uobjnamevalue;
	}
	public void setUobjnamevalue(String uobjnamevalue) {
		this.uobjnamevalue = uobjnamevalue;
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
	public String getUpaystatus() {
		return upaystatus;
	}
	public void setUpaystatus(String upaystatus) {
		this.upaystatus = upaystatus;
	}
	public Double getUprofit() {
		return uprofit;
	}
	public void setUprofit(Double uprofit) {
		this.uprofit = uprofit;
	}
	public Double getUcostprice() {
		return ucostprice;
	}
	public void setUcostprice(Double ucostprice) {
		this.ucostprice = ucostprice;
	}
	public Double getUcost() {
		return ucost;
	}
	public void setUcost(Double ucost) {
		this.ucost = ucost;
	}
	public String getUcreatedate() {
		return ucreatedate;
	}
	public void setUcreatedate(String ucreatedate) {
		this.ucreatedate = ucreatedate;
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
	public Double getUtotalprice() {
		return utotalprice;
	}
	public void setUtotalprice(Double utotalprice) {
		this.utotalprice = utotalprice;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * @author LBY
	 * @data 2019年3月1日
	 * @return
	 */
	@Override
	public String toString() {
		return "UtOrderItem [uorderitemid=" + uorderitemid + ", uorderid=" + uorderid + ", ucompid=" + ucompid
				+ ", uproductid=" + uproductid + ", uuserid=" + uuserid + ", uproductnum=" + uproductnum
				+ ", uproductname=" + uproductname + ", udiscount=" + udiscount + ", umarketprice=" + umarketprice
				+ ", uprice=" + uprice + ", upayprice=" + upayprice + ", ucosttype=" + ucosttype + ", uunit=" + uunit
				+ ", uunitid=" + uunitid + ", uobjnamevalue=" + uobjnamevalue + ", udistributorid="
				+ udistributorid + ", udname=" + udname + ", upaystatus=" + upaystatus + ", uprofit=" + uprofit
				+ ", ucostprice=" + ucostprice + ", ucost=" + ucost + ", ucreatedate=" + ucreatedate + ", ufactoryname="
				+ ufactoryname + ", uhomepic=" + uhomepic + ", utotalprice=" + utotalprice + ", type=" + type + "]";
	}
}
