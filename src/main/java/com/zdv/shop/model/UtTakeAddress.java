package com.zdv.shop.model;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 用户收货地址
 * @author LBY
 * @data 2019年2月26日 
 */
@Table(name = "ut_takeaddress")
public class UtTakeAddress {
	
	/**
	 * 收货地址ID
	 */
	@Id
	private String utakeaddressid;
	/**
	 * 用户ID
	 */
	private String uuserid;
	/**
	 * 收货人姓名
	 */
	private String uname;
	/**
	 * 手机号码
	 */
	private String umobile;
	/**
	 * 省份
	 */
	private String uprovince;
	/**
	 * 城市
	 */
	private String ucity;
	/**
	 * 地区
	 */
	private String uarea;
	/**
	 * 详细地址
	 */
	private String udetailaddress;

	private String ucardid;

	/**
	 * 是否为默认地址
	 */
	private Boolean udefault;


	public String getUcardid() {
		return ucardid;
	}

	public void setUcardid(String ucardid) {
		this.ucardid = ucardid;
	}

	public String getUtakeaddressid() {
		return utakeaddressid;
	}
	public void setUtakeaddressid(String utakeaddressid) {
		this.utakeaddressid = utakeaddressid;
	}
	public String getUuserid() {
		return uuserid;
	}
	public void setUuserid(String uuserid) {
		this.uuserid = uuserid;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getUmobile() {
		return umobile;
	}
	public void setUmobile(String umobile) {
		this.umobile = umobile;
	}
	public String getUprovince() {
		return uprovince;
	}
	public void setUprovince(String uprovince) {
		this.uprovince = uprovince;
	}
	public String getUcity() {
		return ucity;
	}
	public void setUcity(String ucity) {
		this.ucity = ucity;
	}
	public String getUarea() {
		return uarea;
	}
	public void setUarea(String uarea) {
		this.uarea = uarea;
	}
	public String getUdetailaddress() {
		return udetailaddress;
	}
	public void setUdetailaddress(String udetailaddress) {
		this.udetailaddress = udetailaddress;
	}
	public Boolean getUdefault() {
		return udefault;
	}
	public void setUdefault(Boolean udefault) {
		this.udefault = udefault;
	}
	@Override
	public String toString() {
		return "UtTakeAddress [utakeaddressid=" + utakeaddressid + ", uuserid=" + uuserid + ", uname=" + uname
				+ ", umobile=" + umobile + ", uprovince=" + uprovince + ", ucity=" + ucity + ", uarea=" + uarea
				+ ", udetailaddress=" + udetailaddress + ", udefault=" + udefault + "]";
	}
}
