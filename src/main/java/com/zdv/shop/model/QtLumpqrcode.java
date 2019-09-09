package com.zdv.shop.model;

import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 进货经销商垛码表
 * */

@Table(name="qt_lumpqrcode")
public class QtLumpqrcode {

	private String ulumpqrcodeid;	//垛码ID
	private String ubatchnum;		//批次号
	private String udistributorid;	//经销商ID
	private String uopuserid;		//管理员ID
	private String uname;			//姓名
	private String ulumpqrcode;		//垛二维码
	private String uuwinnrate;		//设置中奖率
	private String uisbox;			//中奖是否精准到箱
	private String uproductid;		//对应产品ID
	private String uproducttypeid;	//产品类型
	private String uproductname;	//产品名称
	private String ucreatedate;		//创建时间
	
	
	public String getUlumpqrcodeid() {
		return ulumpqrcodeid;
	}
	public void setUlumpqrcodeid(String ulumpqrcodeid) {
		this.ulumpqrcodeid = ulumpqrcodeid;
	}
	public String getUbatchnum() {
		return ubatchnum;
	}
	public void setUbatchnum(String ubatchnum) {
		this.ubatchnum = ubatchnum;
	}
	public String getUdistributorid() {
		return udistributorid;
	}
	public void setUdistributorid(String udistributorid) {
		this.udistributorid = udistributorid;
	}
	public String getUopuserid() {
		return uopuserid;
	}
	public void setUopuserid(String uopuserid) {
		this.uopuserid = uopuserid;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getUlumpqrcode() {
		return ulumpqrcode;
	}
	public void setUlumpqrcode(String ulumpqrcode) {
		this.ulumpqrcode = ulumpqrcode;
	}
	public String getUuwinnrate() {
		return uuwinnrate;
	}
	public void setUuwinnrate(String uuwinnrate) {
		this.uuwinnrate = uuwinnrate;
	}
	public String getUisbox() {
		return uisbox;
	}
	public void setUisbox(String uisbox) {
		this.uisbox = uisbox;
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
	public String getUproductname() {
		return uproductname;
	}
	public void setUproductname(String uproductname) {
		this.uproductname = uproductname;
	}
	public String getUcreatedate() {
		return ucreatedate;
	}
	public void setUcreatedate(String ucreatedate) {
		this.ucreatedate = ucreatedate;
	}
}
