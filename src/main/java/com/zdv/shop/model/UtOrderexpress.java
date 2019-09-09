package com.zdv.shop.model;

import javax.persistence.*;

/**
 * 订单快递信息表
 */
@Table(name = "ut_orderexpress")
public class UtOrderexpress {
	/**
	 * id
	 */
    @Id
    private String uorderexpressid;//订单快递ID
    private String ucompid;//零售店ID
    private String ucustomerid;//商户号
    private String uuserid;//用户ID
    private String uorderid;//订单ID
    private String uexpresscompname;//快递名称
    private String uexpresscompcode;//快递编码
    private String uexpressnum;//快递单号
    private String ucreatedate;//创建时间
	public String getUorderexpressid() {
		return uorderexpressid;
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
	public String getUorderid() {
		return uorderid;
	}
	public String getUexpresscompname() {
		return uexpresscompname;
	}
	public String getUexpresscompcode() {
		return uexpresscompcode;
	}
	public String getUexpressnum() {
		return uexpressnum;
	}
	public String getUcreatedate() {
		return ucreatedate;
	}
	public void setUorderexpressid(String uorderexpressid) {
		this.uorderexpressid = uorderexpressid;
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
	public void setUorderid(String uorderid) {
		this.uorderid = uorderid;
	}
	public void setUexpresscompname(String uexpresscompname) {
		this.uexpresscompname = uexpresscompname;
	}
	public void setUexpresscompcode(String uexpresscompcode) {
		this.uexpresscompcode = uexpresscompcode;
	}
	public void setUexpressnum(String uexpressnum) {
		this.uexpressnum = uexpressnum;
	}
	public void setUcreatedate(String ucreatedate) {
		this.ucreatedate = ucreatedate;
	}

}