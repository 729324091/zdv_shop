package com.zdv.shop.model;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 经销商经营产品对应产品条码表
 */
@Table(name="qt_product_qrcode")
public class QtProductQrcode {

	//经销商产品条码唯一标识
	@Id
	private String uproqrcodeid;
	//条码ID
	private String uqrcodeid;
	//单品条码编号
	private String uqrcode;
	//箱二维码
	private String uboxqrcode;
	//垛二维码
	private String ulumpqrcode;
	//对应产品ID
	private String uproductid;
	//经销商ID
	private String udistributorid;
	//上级经销商
	private String uparentdistributorid;
	//订单ID
	private String uorderid;
	//订单项ID
	private String uorderitemid;
	//创建时间
	private String ucreatedate;
	public String getUproqrcodeid() {
		return uproqrcodeid;
	}
	public void setUproqrcodeid(String uproqrcodeid) {
		this.uproqrcodeid = uproqrcodeid;
	}
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
	public String getUdistributorid() {
		return udistributorid;
	}
	public void setUdistributorid(String udistributorid) {
		this.udistributorid = udistributorid;
	}
	public String getUparentdistributorid() {
		return uparentdistributorid;
	}
	public void setUparentdistributorid(String uparentdistributorid) {
		this.uparentdistributorid = uparentdistributorid;
	}
	public String getUorderid() {
		return uorderid;
	}
	public void setUorderid(String uorderid) {
		this.uorderid = uorderid;
	}
	public String getUorderitemid() {
		return uorderitemid;
	}
	public void setUorderitemid(String uorderitemid) {
		this.uorderitemid = uorderitemid;
	}
	public String getUcreatedate() {
		return ucreatedate;
	}
	public void setUcreatedate(String ucreatedate) {
		this.ucreatedate = ucreatedate;
	}
	/**
	 * @author LBY
	 * @data 2019年1月21日
	 * @return
	 */
	@Override
	public String toString() {
		return "QtProductQrcode [uproqrcodeid=" + uproqrcodeid + ", uqrcodeid=" + uqrcodeid + ", uqrcode=" + uqrcode
				+ ", uboxqrcode=" + uboxqrcode + ", ulumpqrcode=" + ulumpqrcode + ", uproductid=" + uproductid
				+ ", udistributorid=" + udistributorid + ", uparentdistributorid=" + uparentdistributorid
				+ ", uorderid=" + uorderid + ", uorderitemid=" + uorderitemid + ", ucreatedate=" + ucreatedate + "]";
	}
}
