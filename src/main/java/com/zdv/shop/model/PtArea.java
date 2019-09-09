package com.zdv.shop.model;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 区域表实体类
 * @author LBY
 * @date: 2018年12月17日
 */
@Table(name = "pt_area")
public class PtArea {

	/**
	 * 区域ID
	 */
	@Id
	private String uareaid;
	/**
	 * 区域名称
	 */
	private String uareaname;
	/**
	 * 父ID
	 */
	private String uparentid;
	/**
	 * 经度
	 */
	private Double ulatitude;
	/**
	 * 纬度
	 */
	private Double ulongitude;
	public String getUareaid() {
		return uareaid;
	}
	public void setUareaid(String uareaid) {
		this.uareaid = uareaid;
	}
	public String getUareaname() {
		return uareaname;
	}
	public void setUareaname(String uareaname) {
		this.uareaname = uareaname;
	}
	public String getUparentid() {
		return uparentid;
	}
	public void setUparentid(String uparentid) {
		this.uparentid = uparentid;
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
	@Override
	public String toString() {
		return "PtArea [uareaid=" + uareaid + ", uareaname=" + uareaname + ", uparentid=" + uparentid + ", ulatitude="
				+ ulatitude + ", ulongitude=" + ulongitude + "]";
	}
}
