package com.zdv.shop.model;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 用户中奖表实体类
 * @author jock
 * @date: 2018年12月18日
 */
@Table(name = "qt_user_product_win")
public class QtUserProductWin extends BaseObject{
	@Id
	private String uproductwinid;//用户中奖唯一标识
	private String uuserid;//用户ID
	private String uname;//用户姓名
	private String uqrcodeid;//条码ID 单个产品
	private String uqrcode;//单品条码编号
	private String unqrcode;//内码编号
	private String uboxqrcode;//箱二维码
	private String ulumpqrcode;//垛二维码
	private String uproductid;//对应产品ID
	private String uproductname;//产品名称
	private String ueflag;//是否已兑奖 00 用户未兑奖 01用户已兑奖 02销售商已核销
	private String ucompid;//销售商ID
	private String ucompname;//销售商名称
	private String uopuserid;//管理员ID
	private String uopname;//操作员姓名
	private String uwindate;//兑奖时间
	private String uorderno;//订单号 每次兑奖产生一个订单号
	private String ucreatedate;//扫描时间
	@Transient
	private String ufactoryname;//产品厂家名称
	@Transient
	private String udistributorid;//经销商ID（核销方）
	@Transient
    private String ucontact;    //联系人
	@Transient
    private String umobile;     //手机号码
	@Transient
    private String ulogo;//销售商LOGO 
	@Transient
    private String uhomepic;//产品首图
	@Transient
	private String uunit;//单位
	@Transient
	private String udname;//经销商名称 用户名称
	@Transient
	private String ucashorderid;//订单
	public String getUproductwinid() {
		return uproductwinid;
	}
	public String getUuserid() {
		return uuserid;
	}
	public String getUname() {
		return uname;
	}
	public String getUqrcodeid() {
		return uqrcodeid;
	}
	public String getUqrcode() {
		return uqrcode;
	}
	public String getUnqrcode() {
		return unqrcode;
	}
	public String getUboxqrcode() {
		return uboxqrcode;
	}
	public String getUlumpqrcode() {
		return ulumpqrcode;
	}
	public String getUproductid() {
		return uproductid;
	}
	public String getUproductname() {
		return uproductname;
	}
	public String getUeflag() {
		return ueflag;
	}
	public String getUcompid() {
		return ucompid;
	}
	public String getUcompname() {
		return ucompname;
	}
	public String getUopuserid() {
		return uopuserid;
	}
	public String getUopname() {
		return uopname;
	}
	public String getUwindate() {
		return uwindate;
	}
	public String getUcreatedate() {
		return ucreatedate;
	}
	public void setUproductwinid(String uproductwinid) {
		this.uproductwinid = uproductwinid;
	}
	public void setUuserid(String uuserid) {
		this.uuserid = uuserid;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public void setUqrcodeid(String uqrcodeid) {
		this.uqrcodeid = uqrcodeid;
	}
	public void setUqrcode(String uqrcode) {
		this.uqrcode = uqrcode;
	}
	public void setUnqrcode(String unqrcode) {
		this.unqrcode = unqrcode;
	}
	public void setUboxqrcode(String uboxqrcode) {
		this.uboxqrcode = uboxqrcode;
	}
	public void setUlumpqrcode(String ulumpqrcode) {
		this.ulumpqrcode = ulumpqrcode;
	}
	public void setUproductid(String uproductid) {
		this.uproductid = uproductid;
	}
	public void setUproductname(String uproductname) {
		this.uproductname = uproductname;
	}
	public void setUeflag(String ueflag) {
		this.ueflag = ueflag;
	}
	public void setUcompid(String ucompid) {
		this.ucompid = ucompid;
	}
	public void setUcompname(String ucompname) {
		this.ucompname = ucompname;
	}
	public void setUopuserid(String uopuserid) {
		this.uopuserid = uopuserid;
	}
	public void setUopname(String uopname) {
		this.uopname = uopname;
	}
	public void setUwindate(String uwindate) {
		this.uwindate = uwindate;
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
	public String getUdistributorid() {
		return udistributorid;
	}
	public void setUdistributorid(String udistributorid) {
		this.udistributorid = udistributorid;
	}
	public String getUcontact() {
		return ucontact;
	}
	public String getUmobile() {
		return umobile;
	}
	public void setUcontact(String ucontact) {
		this.ucontact = ucontact;
	}
	public void setUmobile(String umobile) {
		this.umobile = umobile;
	}
	public String getUlogo() {
		return ulogo;
	}
	public String getUhomepic() {
		return uhomepic;
	}
	public void setUlogo(String ulogo) {
		this.ulogo = ulogo;
	}
	public void setUhomepic(String uhomepic) {
		this.uhomepic = uhomepic;
	}
	public String getUunit() {
		return uunit;
	}
	public void setUunit(String uunit) {
		this.uunit = uunit;
	}
	public String getUdname() {
		return udname;
	}
	public void setUdname(String udname) {
		this.udname = udname;
	}
	public String getUcashorderid() {
		return ucashorderid;
	}
	public void setUcashorderid(String ucashorderid) {
		this.ucashorderid = ucashorderid;
	}
	public String getUorderno() {
		return uorderno;
	}
	public void setUorderno(String uorderno) {
		this.uorderno = uorderno;
	}
}
