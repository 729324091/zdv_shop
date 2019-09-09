package com.zdv.shop.model;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Table(name = "ut_users")
public class UtUsers {

	/**
	 * 用户ID
	 */
    @Id
    private String uuserid;
    private String uname;
    private String uareaid;
    private Integer uusercode;//用户7位ID	
    private String ucardtype;
    private String ucardid;
    private String uloginname;
    private String upassword;
    private String upuserid;
    private String uuserlevelid;
    private String ubalance;
    private Integer uintegral;
    private String uemail;
    private String umobile;
    private String ubirthday;
    private String usex;
    private String uregdate;
    private String ulogo;//图像
    private String ulastlogtime;
    private String ulogins;
    private String upaserrnum;
    private String upaserrtime;
    private String uvip;
    private String ueflag;
	private String qrcode;
	private String udistributionid; //分润等级id


	@Transient
	private String ucustomerid;

	public String getUdistributionid() {
		return udistributionid;
	}

	public void setUdistributionid(String udistributionid) {
		this.udistributionid = udistributionid;
	}

	public String getUcustomerid() {
		return ucustomerid;
	}

	public void setUcustomerid(String ucustomerid) {
		this.ucustomerid = ucustomerid;
	}

	public String getQrcode() {
		return qrcode;
	}

	public void setQrcode(String qrcode) {
		this.qrcode = qrcode;
	}

	public String getUareaid() {
		return uareaid;
	}

	public void setUareaid(String uareaid) {
		this.uareaid = uareaid;
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
	public String getUcardtype() {
		return ucardtype;
	}
	public void setUcardtype(String ucardtype) {
		this.ucardtype = ucardtype;
	}
	public String getUcardid() {
		return ucardid;
	}
	public void setUcardid(String ucardid) {
		this.ucardid = ucardid;
	}
	public String getUloginname() {
		return uloginname;
	}
	public void setUloginname(String uloginname) {
		this.uloginname = uloginname;
	}
	public String getUpassword() {
		return upassword;
	}
	public void setUpassword(String upassword) {
		this.upassword = upassword;
	}
	public String getUpuserid() {
		return upuserid;
	}
	public void setUpuserid(String upuserid) {
		this.upuserid = upuserid;
	}
	public String getUuserlevelid() {
		return uuserlevelid;
	}
	public void setUuserlevelid(String uuserlevelid) {
		this.uuserlevelid = uuserlevelid;
	}
	public String getUbalance() {
		return ubalance;
	}
	public void setUbalance(String ubalance) {
		this.ubalance = ubalance;
	}
	public Integer getUintegral() {
		return uintegral;
	}
	public void setUintegral(Integer uintegral) {
		this.uintegral = uintegral;
	}
	public String getUemail() {
		return uemail;
	}
	public void setUemail(String uemail) {
		this.uemail = uemail;
	}
	public String getUmobile() {
		return umobile;
	}
	public void setUmobile(String umobile) {
		this.umobile = umobile;
	}
	public String getUbirthday() {
		return ubirthday;
	}
	public void setUbirthday(String ubirthday) {
		this.ubirthday = ubirthday;
	}
	public String getUsex() {
		return usex;
	}
	public void setUsex(String usex) {
		this.usex = usex;
	}
	public String getUregdate() {
		return uregdate;
	}
	public void setUregdate(String uregdate) {
		this.uregdate = uregdate;
	}
	public String getUlogo() {
		return ulogo;
	}
	public void setUlogo(String ulogo) {
		this.ulogo = ulogo;
	}
	public String getUlastlogtime() {
		return ulastlogtime;
	}
	public void setUlastlogtime(String ulastlogtime) {
		this.ulastlogtime = ulastlogtime;
	}
	public String getUlogins() {
		return ulogins;
	}
	public void setUlogins(String ulogins) {
		this.ulogins = ulogins;
	}
	public String getUpaserrnum() {
		return upaserrnum;
	}
	public void setUpaserrnum(String upaserrnum) {
		this.upaserrnum = upaserrnum;
	}
	public String getUpaserrtime() {
		return upaserrtime;
	}
	public void setUpaserrtime(String upaserrtime) {
		this.upaserrtime = upaserrtime;
	}

	public String getUvip() {
		return uvip;
	}

	public void setUvip(String uvip) {
		this.uvip = uvip;
	}

	public String getUeflag() {
		return ueflag;
	}
	public void setUeflag(String ueflag) {
		this.ueflag = ueflag;
	}
	public Integer getUusercode() {
		return uusercode;
	}

	public void setUusercode(Integer uusercode) {
		this.uusercode = uusercode;
	}

	/**
	 * @author LBY
	 * @data 2019年3月4日
	 * @return
	 */
	@Override
	public String toString() {
		return "UtUsers [uuserid=" + uuserid + ", uname=" + uname + ", ucardtype=" + ucardtype + ", ucardid=" + ucardid
				+ ", uloginname=" + uloginname + ", upassword=" + upassword + ", upuserid=" + upuserid + ", uuserlevelid="
				+ uuserlevelid + ", ubalance=" + ubalance + ", uintegral=" + uintegral + ", uemail=" + uemail
				+ ", umobile=" + umobile + ", ubirthday=" + ubirthday + ", usex=" + usex + ", uregdate=" + uregdate
				+ ", ulogo=" + ulogo + ", ulastlogtime=" + ulastlogtime + ", ulogins=" + ulogins + ", upaserrnum="
				+ upaserrnum + ", upaserrtime=" + upaserrtime + ", uvip=" + uvip + ", ueflag=" + ueflag + "]";
	}
}
