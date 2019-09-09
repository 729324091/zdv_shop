package com.zdv.shop.model;

import javax.persistence.Table;

/**
 * 单品二维码信息表
 * */

@Table(name="qt_qrcode")
public class QtQrcode {
	
	private String uqrcodeid;		//条码ID
	private String uqrcode;			//单品条码编号
	private String uqrcodepath;		//二维码图Path
	private String uboxqrcode;		//箱二维码
	private String ulumpqrcode;		//垛二维码
	private String uproductid;		//对应产品ID
	private String uproducttypeid;	//产品类型
	private String uproductname;	//产品名称
	private String udistributorid;	//经销商ID
	private String ueflag;			//状态
	private String uiswin;			//是否设置中奖
	private String ucreatedate;		//创建时间
	
	public String getUqrcodeid() {
		return uqrcodeid;
	}
	public void setUqrcodeid(String uqrcodeid) {
		this.uqrcodeid = uqrcodeid;
	}
	public String getUqrcode() {
		return uqrcode;
	}
	public void setUqrcode(String uqrcode) {
		this.uqrcode = uqrcode;
	}
	public String getUqrcodepath() {
		return uqrcodepath;
	}
	public void setUqrcodepath(String uqrcodepath) {
		this.uqrcodepath = uqrcodepath;
	}
	public String getUboxqrcode() {
		return uboxqrcode;
	}
	public void setUboxqrcode(String uboxqrcode) {
		this.uboxqrcode = uboxqrcode;
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
	public String getUproductname() {
		return uproductname;
	}
	public void setUproductname(String uproductname) {
		this.uproductname = uproductname;
	}
	public String getUdistributorid() {
		return udistributorid;
	}
	public void setUdistributorid(String udistributorid) {
		this.udistributorid = udistributorid;
	}
	public String getUeflag() {
		return ueflag;
	}
	public void setUeflag(String ueflag) {
		this.ueflag = ueflag;
	}
	public String getUiswin() {
		return uiswin;
	}
	public void setUiswin(String uiswin) {
		this.uiswin = uiswin;
	}
	public String getUcreatedate() {
		return ucreatedate;
	}
	public void setUcreatedate(String ucreatedate) {
		this.ucreatedate = ucreatedate;
	}
	
}
