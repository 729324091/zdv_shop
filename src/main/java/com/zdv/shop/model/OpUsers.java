package com.zdv.shop.model;

import javax.persistence.*;

/**
 * 管理员信息表
 * @author LBY
 * @date: 2018年12月12日
 */
@Table(name = "op_users")
public class OpUsers {
	/**
	 * 管理员编号
	 */
	@Id
	private String uopuserid;
	/**
	 * 管理员类型 0位平台管理员，1为其他管理员
	 */
	private String uoptype;
	/**
	 * 所属角色ID 平台管理员用
	 */
	private String uroleid;

	/**
	 * 姓名
	 */
	private String uopname;
	/**
	 * 证件类型
	 */
	private String ucardtype;
	/**
	 * 证件号码
	 */
	private String ucardid;
	/**
	 * 登录名称
	 */
	private String uloginname;
	/**
	 * 密码
	 */
	private String upassword;
	/**
	 * 管理员logo
	 */
	private String ulogo;
	/**
	 * 邮箱
	 */
	private String uemail;
	/**
	 * 手机号码
	 */
	private String umobile;
	/**
	 * 出生日期
	 */
	private String ubirthday;
	/**
	 * 性别
	 */
	private String usex;
	/**
	 * 地址
	 */
	private String uaddress;
	/**
	 * 注册时间
	 */
	private String uregdate;
	/**
	 * 上次登录时间
	 */
	private String ulastdate;
	/**
	 * 登录次数
	 */
	private Integer ulognum;
	/**
	 * 上次修改密码时间
	 */
	private String ulastmtime;

	/**
	 * 上次登录IP
	 */
	private String uloginip;

	/**
	 * 是否有效
	 */
	private String uflag;

	/**
	 * 表示该用户在经销商或销售商下的状态
	 */
	@Transient
	private String ustatus;


	/**
	 * 表示所属角色名
	 */
	@Transient
	private String urolename;

	/**
	 * 表示角色创建时间
	 */
	@Transient
	private String ucreatedate;
	@Transient
	private String compname;


	public String getUloginip() {
		return uloginip;
	}

	public void setUloginip(String uloginip) {
		this.uloginip = uloginip;
	}

	public String getUcreatedate() {
		return ucreatedate;
	}

	public void setUcreatedate(String ucreatedate) {
		this.ucreatedate = ucreatedate;
	}

	public String getUstatus() {
		return ustatus;
	}

	public void setUstatus(String ustatus) {
		this.ustatus = ustatus;
	}

	public String getUrolename() {
		return urolename;
	}

	public void setUrolename(String urolename) {
		this.urolename = urolename;
	}

	public String getUopuserid() {
		return uopuserid;
	}
	public void setUopuserid(String uopuserid) {
		this.uopuserid = uopuserid;
	}
	public String getUopname() {
		return uopname;
	}
	public void setUopname(String uopname) {
		this.uopname = uopname;
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
	public String getUlogo() {
		return ulogo;
	}
	public void setUlogo(String ulogo) {
		this.ulogo = ulogo;
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
	public String getCompname() {
		return compname;
	}

	public void setCompname(String compname) {
		this.compname = compname;
	}

	public void setUsex(String usex) {
		this.usex = usex;
	}
	public String getUaddress() {
		return uaddress;
	}
	public void setUaddress(String uaddress) {
		this.uaddress = uaddress;
	}
	public String getUregdate() {
		return uregdate;
	}
	public void setUregdate(String uregdate) {
		this.uregdate = uregdate;
	}
	public String getUlastdate() {
		return ulastdate;
	}
	public void setUlastdate(String ulastdate) {
		this.ulastdate = ulastdate;
	}
	public Integer getUlognum() {
		return ulognum;
	}
	public void setUlognum(Integer ulognum) {
		this.ulognum = ulognum;
	}
	public String getUlastmtime() {
		return ulastmtime;
	}
	public void setUlastmtime(String ulastmtime) {
		this.ulastmtime = ulastmtime;
	}
	public String getUflag() {
		return uflag;
	}
	public void setUflag(String uflag) {
		this.uflag = uflag;
	}
	@Override
	public String toString() {
		return "OpUsers [uopuserid=" + uopuserid + ", uopname=" + uopname + ", ucardtype=" + ucardtype + ", ucardid="
				+ ucardid + ", uloginname=" + uloginname + ", upassword=" + upassword + ", ulogo=" + ulogo + ", uemail="
				+ uemail + ", umobile=" + umobile + ", ubirthday=" + ubirthday + ", usex=" + usex + ", uaddress="
				+ uaddress + ", uregdate=" + uregdate + ", ulastdate=" + ulastdate + ", ulognum=" + ulognum
				+ ", ulastmtime=" + ulastmtime + ", uflag=" + uflag + "]";
	}

	public String getUoptype() {
		return uoptype;
	}

	public void setUoptype(String uoptype) {
		this.uoptype = uoptype;
	}

	public String getUroleid() {
		return uroleid;
	}

	public void setUroleid(String uroleid) {
		this.uroleid = uroleid;
	}
}