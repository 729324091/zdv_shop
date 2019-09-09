package com.zdv.shop.model;

import javax.persistence.Table;

@Table(name = "ut_bankinfo")
public class UtBankinfo {
	private String ubankId;
	private String ubankName;
	private String ubankCardno;
	private String ubankAccountName;
	private String uifClose;
	private String uuserid;
	private String phone;

	public String getUbankId() {
		return ubankId;
	}

	public void setUbankId(String ubankId) {
		this.ubankId = ubankId;
	}

	public String getUbankName() {
		return ubankName;
	}

	public void setUbankName(String ubankName) {
		this.ubankName = ubankName;
	}

	public String getUbankCardno() {
		return ubankCardno;
	}

	public void setUbankCardno(String ubankCardno) {
		this.ubankCardno = ubankCardno;
	}

	public String getUbankAccountName() {
		return ubankAccountName;
	}

	public void setUbankAccountName(String ubankAccountName) {
		this.ubankAccountName = ubankAccountName;
	}

	public String getUifClose() {
		return uifClose;
	}

	public void setUifClose(String uifClose) {
		this.uifClose = uifClose;
	}

	public String getUuserid() {
		return uuserid;
	}

	public void setUuserid(String uuserid) {
		this.uuserid = uuserid;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
}
