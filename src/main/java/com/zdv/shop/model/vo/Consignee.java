package com.zdv.shop.model.vo;

/**
 * 收货方信息
 * @author LBY
 * @data 2019年1月21日
 */
public class Consignee {
	/**
	 * 联系电话
	 */
	private String utel;
	/**
	 * 联系人
	 */
	private String ucontact;
	/**
	 * 地址
	 */
	private String uaddress;
	/**
	 * 收货方店铺名称
	 */
	private String udname;
	public String getUtel() {
		return utel;
	}
	public void setUtel(String utel) {
		this.utel = utel;
	}
	public String getUcontact() {
		return ucontact;
	}
	public void setUcontact(String ucontact) {
		this.ucontact = ucontact;
	}
	public String getUaddress() {
		return uaddress;
	}
	public void setUaddress(String uaddress) {
		this.uaddress = uaddress;
	}

	public String getUdname() {
		return udname;
	}
	public void setUdname(String udname) {
		this.udname = udname;
	}
	/**
	 * @author LBY
	 * @data 2019年1月21日
	 * @return
	 */
	@Override
	public String toString() {
		return "Consignee [utel=" + utel + ", ucontact=" + ucontact + ", uaddress=" + uaddress + ", udname=" + udname
				+ "]";
	}
}
