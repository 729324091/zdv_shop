package com.zdv.shop.model;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 兑奖订单
 * @author jock
 * @date: 2018年12月21日
 */
@Table(name = "qt_cash_order")
public class QtCashOrder extends BaseObject{
	@Id
	private String ucashorderid;//兑奖订单id	
	private String ucashorderno;//兑奖订单号
	private Integer utotalnum;//总数量
	private String utype;//类型（下单类型）0为销售商，1为经销商
	private String utotype;//谁下单类型 0为下单商家下单，1为接单商家下单
	private String ueflag;//是否已兑奖 00 未核销，01已核销
	private String ucompid;//(下单商家id)
	private String ucname;//名称（下单商家）
	private String uaddress;//下单商家地址
	private Double ulatitude;//下单商家经度
	private Double ulongitude;//下单商家纬度
	private String ucontact;//下单商家联系人
	private String utel;//下单商家电话
	private String ulogo;//下单商家logo
	private String utocompid;//(接单商家ID)
	private String utoname;//(接单商家名称)
	private String utoaddress;//接单商家地址
	private Double utolatitude;//接单商家经度
	private Double utolongitude;//接单商家纬度
	private String utocontact;//接单商家联系人
	private String utotel;//接单商家电话
	private String utologo;//接单商家logo
	private String ucreatedate;//
	@Transient
	private String ucompname;
	@Transient
	private String udistributorid;
	public String getUcashorderid() {
		return ucashorderid;
	}
	public String getUcashorderno() {
		return ucashorderno;
	}
	public Integer getUtotalnum() {
		return utotalnum;
	}
	public String getUtype() {
		return utype;
	}
	public String getUtotype() {
		return utotype;
	}
	public String getUeflag() {
		return ueflag;
	}
	public String getUcompid() {
		return ucompid;
	}
	public String getUcname() {
		return ucname;
	}
	public String getUaddress() {
		return uaddress;
	}
	public Double getUlatitude() {
		return ulatitude;
	}
	public Double getUlongitude() {
		return ulongitude;
	}
	public String getUcontact() {
		return ucontact;
	}
	public String getUtel() {
		return utel;
	}
	public String getUlogo() {
		return ulogo;
	}
	public String getUtocompid() {
		return utocompid;
	}
	public String getUtoname() {
		return utoname;
	}
	public String getUtoaddress() {
		return utoaddress;
	}
	public Double getUtolatitude() {
		return utolatitude;
	}
	public Double getUtolongitude() {
		return utolongitude;
	}
	public String getUtocontact() {
		return utocontact;
	}
	public String getUtotel() {
		return utotel;
	}
	public String getUtologo() {
		return utologo;
	}
	public String getUcreatedate() {
		return ucreatedate;
	}
	public void setUcashorderid(String ucashorderid) {
		this.ucashorderid = ucashorderid;
	}
	public void setUcashorderno(String ucashorderno) {
		this.ucashorderno = ucashorderno;
	}
	public void setUtotalnum(Integer utotalnum) {
		this.utotalnum = utotalnum;
	}
	public void setUtype(String utype) {
		this.utype = utype;
	}
	public void setUtotype(String utotype) {
		this.utotype = utotype;
	}
	public void setUeflag(String ueflag) {
		this.ueflag = ueflag;
	}
	public String getUcompname() {
		return ucompname;
	}
	public void setUcompname(String ucompname) {
		this.ucompname = ucompname;
	}
	public void setUcompid(String ucompid) {
		this.ucompid = ucompid;
	}
	public void setUcname(String ucname) {
		this.ucname = ucname;
	}
	public void setUaddress(String uaddress) {
		this.uaddress = uaddress;
	}
	public void setUlatitude(Double ulatitude) {
		this.ulatitude = ulatitude;
	}
	public void setUlongitude(Double ulongitude) {
		this.ulongitude = ulongitude;
	}
	public void setUcontact(String ucontact) {
		this.ucontact = ucontact;
	}
	public void setUtel(String utel) {
		this.utel = utel;
	}
	public void setUlogo(String ulogo) {
		this.ulogo = ulogo;
	}
	public void setUtocompid(String utocompid) {
		this.utocompid = utocompid;
	}
	public void setUtoname(String utoname) {
		this.utoname = utoname;
	}
	public void setUtoaddress(String utoaddress) {
		this.utoaddress = utoaddress;
	}
	public void setUtolatitude(Double utolatitude) {
		this.utolatitude = utolatitude;
	}
	public void setUtolongitude(Double utolongitude) {
		this.utolongitude = utolongitude;
	}
	public void setUtocontact(String utocontact) {
		this.utocontact = utocontact;
	}
	public void setUtotel(String utotel) {
		this.utotel = utotel;
	}
	public void setUtologo(String utologo) {
		this.utologo = utologo;
	}
	public void setUcreatedate(String ucreatedate) {
		this.ucreatedate = ucreatedate;
	}
	public String getUdistributorid() {
		return udistributorid;
	}
	public void setUdistributorid(String udistributorid) {
		this.udistributorid = udistributorid;
	}
	
	
}
