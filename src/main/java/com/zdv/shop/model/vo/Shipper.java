package com.zdv.shop.model.vo;

/**
 * 发货方信息
 * @author LBY
 * @data 2019年1月21日
 */
public class Shipper {
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
	/**
	 * @author LBY
	 * @data 2019年1月25日
	 * @return
	 */
	@Override
	public String toString() {
		return "Shipper [utel=" + utel + ", ucontact=" + ucontact + ", uaddress=" + uaddress + "]";
	}
}