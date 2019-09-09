package com.zdv.shop.model;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 销售商信息表
 * @author LBY
 * @date: 2018年12月17日
 */
@Table(name = "ct_comp")
public class CtComp {

	/**
	 * 销售商主键编号
	 */
	@Id
	private String ucompid;
	/**
	 * 销售商名称
	 */
	private String ucompname;
	/**
	 * 销售商类型(代码01 单体店，02连锁店,03直营店，04加盟店，05嘉年华)
	 */
	private String utype;
	/**
	 * 销售商logo
	 */
	private String ulogo;


	private String ucustomerid;
	/**
	 * 地址
	 */
	private String uaddress;
	/**
	 * 地区ID
	 */
	private String uareaid;
	/**
	 * 经度
	 */
	private Double ulatitude;
	/**
	 * 纬度
	 */
	private Double ulongitude;
	/**
	 * 联系人
	 */
	private String ucontact;
	/**
	 * 电话
	 */
	private String utel;
	/**
	 * 父ID
	 */
	private String uparentid;
	/**
	 * 介绍
	 */
	private String udesc;
	/**
	 * 创建时间
	 */
	private String ucreatedate;
	private String ucompno;//单位代码
	private String ushortname;//单位简称
	private String ufullname;	// 单位全称
	private String umobile;//手机号码
	private String uemail;//邮箱
	private String upostcode;//邮政编号
	private Double ubalance;


	public Double getUbalance() {
		return ubalance;
	}

	public void setUbalance(Double ubalance) {
		this.ubalance = ubalance;
	}

	public String getUcustomerid() {
		return ucustomerid;
	}

	public void setUcustomerid(String ucustomerid) {
		this.ucustomerid = ucustomerid;
	}

	public String getUcompid() {
		return ucompid;
	}
	public void setUcompid(String ucompid) {
		this.ucompid = ucompid;
	}
	public String getUcompname() {
		return ucompname;
	}
	public void setUcompname(String ucompname) {
		this.ucompname = ucompname;
	}
	public String getUtype() {
		return utype;
	}
	public void setUtype(String utype) {
		this.utype = utype;
	}
	public String getUlogo() {
		return ulogo;
	}
	public void setUlogo(String ulogo) {
		this.ulogo = ulogo;
	}
	public String getUaddress() {
		return uaddress;
	}
	public void setUaddress(String uaddress) {
		this.uaddress = uaddress;
	}
	public String getUareaid() {
		return uareaid;
	}
	public void setUareaid(String uareaid) {
		this.uareaid = uareaid;
	}
	public Double getUlatitude() {
		return ulatitude;
	}
	public void setUlatitude(Double ulatitude) {
		this.ulatitude = ulatitude;
	}
	public Double getUlongitude() {
		return ulongitude;
	}
	public void setUlongitude(Double ulongitude) {
		this.ulongitude = ulongitude;
	}
	public String getUcontact() {
		return ucontact;
	}
	public void setUcontact(String ucontact) {
		this.ucontact = ucontact;
	}
	public String getUtel() {
		return utel;
	}
	public void setUtel(String utel) {
		this.utel = utel;
	}
	public String getUparentid() {
		return uparentid;
	}
	public void setUparentid(String uparentid) {
		this.uparentid = uparentid;
	}
	public String getUdesc() {
		return udesc;
	}
	public void setUdesc(String udesc) {
		this.udesc = udesc;
	}
	public String getUcreatedate() {
		return ucreatedate;
	}
	public void setUcreatedate(String ucreatedate) {
		this.ucreatedate = ucreatedate;
	}
	public String getUcompno() {
		return ucompno;
	}
	public void setUcompno(String ucompno) {
		this.ucompno = ucompno;
	}
	public String getUshortname() {
		return ushortname;
	}
	public void setUshortname(String ushortname) {
		this.ushortname = ushortname;
	}
	public String getUfullname() {
		return ufullname;
	}
	public void setUfullname(String ufullname) {
		this.ufullname = ufullname;
	}
	public String getUmobile() {
		return umobile;
	}
	public void setUmobile(String umobile) {
		this.umobile = umobile;
	}
	public String getUemail() {
		return uemail;
	}
	public void setUemail(String uemail) {
		this.uemail = uemail;
	}
	public String getUpostcode() {
		return upostcode;
	}
	public void setUpostcode(String upostcode) {
		this.upostcode = upostcode;
	}
	/**
	 * @author LBY
	 * @data 2019年1月14日
	 * @return
	 */
	@Override
	public String toString() {
		return "CtComp [ucompid=" + ucompid + ", ucompname=" + ucompname + ", utype=" + utype + ", ulogo=" + ulogo
				+ ", uaddress=" + uaddress + ", uareaid=" + uareaid + ", ulatitude=" + ulatitude + ", ulongitude="
				+ ulongitude + ", ucontact=" + ucontact + ", utel=" + utel + ", uparentid=" + uparentid + ", udesc="
				+ udesc + ", ucreatedate=" + ucreatedate + ", ucompno=" + ucompno + ", ushortname=" + ushortname
				+ ", ufullname=" + ufullname + ", umobile=" + umobile + ", uemail=" + uemail + ", upostcode="
				+ upostcode + "]";
	}
}
