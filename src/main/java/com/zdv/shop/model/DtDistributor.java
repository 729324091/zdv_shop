package com.zdv.shop.model;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 经销商（供应商）表
 */
@Table(name="dt_distributor")
public class DtDistributor {

	@Id
	private String udistributorid;
	private String udname;
	private String ucustomerid;//经销商商户号
	private String utype;////经销商(供应商)类型 代码01  1P省级、02 2P市级 033P县级
	private String utypes;
	private String ustatus;
	private String ulogo;
	private String uaddress;
	private String uareaid;
	private Double ulatitude;
	private Double ulongitude;
	private String ucontact;
	private String utel;
	private String uparentid;
	private String ucertificatepath;
	private String ulevel;
	private String udesc;
	private String ucreatedate;
	@Transient
	private String uareaname;


	public String getUstatus() {
		return ustatus;
	}

	public void setUstatus(String ustatus) {
		this.ustatus = ustatus;
	}

	public String getUcertificatepath() {
		return ucertificatepath;
	}

	public void setUcertificatepath(String ucertificatepath) {
		this.ucertificatepath = ucertificatepath;
	}

	public String getUtypes() {
		return utypes;
	}

	public void setUtypes(String utypes) {
		this.utypes = utypes;
	}

	public String getUareaname() {
		return uareaname;
	}

	public void setUareaname(String uareaname) {
		this.uareaname = uareaname;
	}

	public String getUlevel() {
		return ulevel;
	}

	public void setUlevel(String ulevel) {
		this.ulevel = ulevel;
	}

	public String getUdistributorid() {
		return udistributorid;
	}
	public void setUdistributorid(String udistributorid) {
		this.udistributorid = udistributorid;
	}
	public String getUdname() {
		return udname;
	}
	public void setUname(String udname) {
		this.udname = udname;
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
	public String getUcustomerid() {
		return ucustomerid;
	}

	public void setUdname(String udname) {
		this.udname = udname;
	}

	public void setUcustomerid(String ucustomerid) {
		this.ucustomerid = ucustomerid;
	}

	@Override
	public String toString() {
		return "DtDistributor{" +
				"udistributorid='" + udistributorid + '\'' +
				", udname='" + udname + '\'' +
				", ucustomerid='" + ucustomerid + '\'' +
				", utype='" + utype + '\'' +
				", utypes='" + utypes + '\'' +
				", ulogo='" + ulogo + '\'' +
				", uaddress='" + uaddress + '\'' +
				", uareaid='" + uareaid + '\'' +
				", ulatitude=" + ulatitude +
				", ulongitude=" + ulongitude +
				", ucontact='" + ucontact + '\'' +
				", utel='" + utel + '\'' +
				", uparentid='" + uparentid + '\'' +
				", ucertificatepath='" + ucertificatepath + '\'' +
				", ulevel='" + ulevel + '\'' +
				", udesc='" + udesc + '\'' +
				", ucreatedate='" + ucreatedate + '\'' +
				", uareaname='" + uareaname + '\'' +
				'}';
	}
}
