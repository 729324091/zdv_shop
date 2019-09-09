package com.zdv.shop.model;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 经销商兑奖核销实体类
 * @author jock
 * @date: 2018年12月18日
 */
@Table(name = "qt_cash_offset_win")
public class QtCashOffset extends BaseObject{
	@Id
	private String ucashoffsetid;//经销商兑奖核销id
	private String ucashorderid;//兑奖订单id
	private String uproductwinid;//用户获得奖数据ID
	private String udistributorid;//经销商ID
	private String uproductid;//产品id
	private String ueflag;//是否已兑奖 00正在核销中 01已确认核销
	private String ucreatedate;//扫描时间
	@Transient
	private String ucompid;//
	@Transient
	private String uopname;
	@Transient
	private String uopuserid;
	@Transient
	private String udname;
	@Transient
	private String utype;
	@Transient
	private String ufactoryname;//产品厂家名称
	@Transient
    private String ucontact;    //联系人
	@Transient
    private String umobile;     //手机号码
	@Transient
    private String ulogo;//销售商LOGO 
	@Transient
    private String uhomepic;//产品首图
	public String getUcashoffsetid() {
		return ucashoffsetid;
	}
	
	public String getUeflag() {
		return ueflag;
	}
	public String getUcreatedate() {
		return ucreatedate;
	}
	public String getUfactoryname() {
		return ufactoryname;
	}
	public String getUcontact() {
		return ucontact;
	}
	public String getUmobile() {
		return umobile;
	}
	public String getUlogo() {
		return ulogo;
	}
	public String getUhomepic() {
		return uhomepic;
	}
	public void setUcashoffsetid(String ucashoffsetid) {
		this.ucashoffsetid = ucashoffsetid;
	}
	
	public void setUeflag(String ueflag) {
		this.ueflag = ueflag;
	}
	public void setUcreatedate(String ucreatedate) {
		this.ucreatedate = ucreatedate;
	}
	public void setUfactoryname(String ufactoryname) {
		this.ufactoryname = ufactoryname;
	}
	public void setUcontact(String ucontact) {
		this.ucontact = ucontact;
	}
	public void setUmobile(String umobile) {
		this.umobile = umobile;
	}
	public void setUlogo(String ulogo) {
		this.ulogo = ulogo;
	}
	public void setUhomepic(String uhomepic) {
		this.uhomepic = uhomepic;
	}
	public String getUproductwinid() {
		return uproductwinid;
	}
	public String getUproductid() {
		return uproductid;
	}
	public void setUproductwinid(String uproductwinid) {
		this.uproductwinid = uproductwinid;
	}
	public void setUproductid(String uproductid) {
		this.uproductid = uproductid;
	}
	public String getUcashorderid() {
		return ucashorderid;
	}
	public void setUcashorderid(String ucashorderid) {
		this.ucashorderid = ucashorderid;
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

	public String getUopname() {
		return uopname;
	}

	public String getUopuserid() {
		return uopuserid;
	}

	public String getUdname() {
		return udname;
	}

	public String getUtype() {
		return utype;
	}

	public void setUcompid(String ucompid) {
		this.ucompid = ucompid;
	}

	public void setUopname(String uopname) {
		this.uopname = uopname;
	}

	public void setUopuserid(String uopuserid) {
		this.uopuserid = uopuserid;
	}

	public void setUdname(String udname) {
		this.udname = udname;
	}

	public void setUtype(String utype) {
		this.utype = utype;
	}

	
}
