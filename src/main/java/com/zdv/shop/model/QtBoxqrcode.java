package com.zdv.shop.model;

import javax.persistence.Table;

/**
 * 进货经销商箱码表
 * */

@Table(name="qt_boxqrcode")
public class QtBoxqrcode {

	private String uboxqrcodeid;		//箱二维码ID
	private String uboxqrcode;			//箱二维码
	private String ulumpqrcodeid;		//垛码ID
	private String udistributorid;		//经销商(供应商)名称
	private String uopuserid;			//管理员ID
	private String uname;				//姓名
	private String ulumpqrcode;			//垛二维码
	private String uproductid;			//产品ID
	private String uproducttypeid;		//产品类型
	private String ucreatedate;			//创建时间

	public String getUboxqrcodeid() {
		return uboxqrcodeid;
	}
	public void setUboxqrcodeid(String uboxqrcodeid) {
		this.uboxqrcodeid = uboxqrcodeid;
	}
	public String getUboxqrcode() {
		return uboxqrcode;
	}
	public void setUboxqrcode(String uboxqrcode) {
		this.uboxqrcode = uboxqrcode;
	}
	public String getUlumpqrcodeid() {
		return ulumpqrcodeid;
	}
	public void setUlumpqrcodeid(String ulumpqrcodeid) {
		this.ulumpqrcodeid = ulumpqrcodeid;
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
	public String getUcreatedate() {
		return ucreatedate;
	}
	public void setUcreatedate(String ucreatedate) {
		this.ucreatedate = ucreatedate;
	}
	
}
