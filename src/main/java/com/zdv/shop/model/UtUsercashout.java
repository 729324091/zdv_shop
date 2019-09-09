package com.zdv.shop.model;

import javax.persistence.*;

/**
 * 用户申请提现信息表
 */
@Table(name = "ut_usercashout")
public class UtUsercashout {
	/**
	 * id
	 */
    @Id
    private String ucashoutid;//用户提现ID
    private String ucompid;//零售店ID
    private String ucustomerid;//商户号
    private String uuserid;//用户ID
    private String uoperuserid;//处理人员
    private String ustatus;//状态(0请申中,1已同意提现,2条件不够提现失败)
    private String utrademoney;//提现金额	
    private String uglidenumber;//流水帐号
    private String uremark;//提现说明	
    private String transaction_id;//第三方支付成功后返回的订单号码
    private String ufee;//服务费	
    private String uprocesstime;//同意处理时间
	private String uwithdrawtype;	//提现方式 0微信 1银行卡
	private String ubankid;			//用户绑定的银行卡id
	private String ubankcardno;
    private String ucreatedate;//创建时间
    @Transient
    private String uname; //提现用户名

	public String getUbankid() {
		return ubankid;
	}

	public void setUbankid(String ubankid) {
		this.ubankid = ubankid;
	}

	public String getUbankcardno() {
		return ubankcardno;
	}

	public void setUbankcardno(String ubankcardno) {
		this.ubankcardno = ubankcardno;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getUwithdrawtype() {
		return uwithdrawtype;
	}

	public void setUwithdrawtype(String uwithdrawtype) {
		this.uwithdrawtype = uwithdrawtype;
	}

	public String getUcashoutid() {
		return ucashoutid;
	}
	public String getUcompid() {
		return ucompid;
	}
	public String getUcustomerid() {
		return ucustomerid;
	}
	public String getUuserid() {
		return uuserid;
	}
	public String getUoperuserid() {
		return uoperuserid;
	}
	public String getUstatus() {
		return ustatus;
	}
	public String getUtrademoney() {
		return utrademoney;
	}
	public String getUglidenumber() {
		return uglidenumber;
	}
	public String getUremark() {
		return uremark;
	}
	public String getTransaction_id() {
		return transaction_id;
	}
	public String getUfee() {
		return ufee;
	}
	public String getUprocesstime() {
		return uprocesstime;
	}
	public String getUcreatedate() {
		return ucreatedate;
	}
	public void setUcashoutid(String ucashoutid) {
		this.ucashoutid = ucashoutid;
	}
	public void setUcompid(String ucompid) {
		this.ucompid = ucompid;
	}
	public void setUcustomerid(String ucustomerid) {
		this.ucustomerid = ucustomerid;
	}
	public void setUuserid(String uuserid) {
		this.uuserid = uuserid;
	}
	public void setUoperuserid(String uoperuserid) {
		this.uoperuserid = uoperuserid;
	}
	public void setUstatus(String ustatus) {
		this.ustatus = ustatus;
	}
	public void setUtrademoney(String utrademoney) {
		this.utrademoney = utrademoney;
	}
	public void setUglidenumber(String uglidenumber) {
		this.uglidenumber = uglidenumber;
	}
	public void setUremark(String uremark) {
		this.uremark = uremark;
	}
	public void setTransaction_id(String transaction_id) {
		this.transaction_id = transaction_id;
	}
	public void setUfee(String ufee) {
		this.ufee = ufee;
	}
	public void setUprocesstime(String uprocesstime) {
		this.uprocesstime = uprocesstime;
	}
	public void setUcreatedate(String ucreatedate) {
		this.ucreatedate = ucreatedate;
	}


}