package com.zdv.shop.model;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 附件图片视频信息
 * @author LBY
 * @date: 2018年12月6日
 */
@Table(name = "ot_annex")
public class OtAnnex {
	
	@Id
	@GeneratedValue(generator="UUID")
	private String uannexid;
	/**
	 * 产品ID
	 */
	private String uproductid;
	/**
	 * 附件类型(0为图片，1为视频，2为word,3为xls,4为text)
	 * - 关联com.kx.exam.model.OtAnnex
	 */
	private Character ufiletype;
	/**
	 * 附件路径
	 */
	private String ufilepath;
	/**
	 * 备注/附件名称
	 */
	private String uremark;
	public String getUannexid() {
		return uannexid;
	}
	public void setUannexid(String uannexid) {
		this.uannexid = uannexid;
	}
	public String getUproductid() {
		return uproductid;
	}
	public void setUproductid(String uproductid) {
		this.uproductid = uproductid;
	}
	/**
	 * 附件类型(0为图片，1为视频，2为word,3为xls,4为text)
	 * - 关联com.kx.exam.model.OtAnnex
	 * @author LBY
	 * @date 2018年12月6日
	 * @return
	 */
	public Character getUfiletype() {
		return ufiletype;
	}
	/**
	 * 附件类型(0为图片，1为视频，2为word,3为xls,4为text)
	 * - 关联com.kx.exam.model.OtAnnex
	 * @author LBY
	 * @date 2018年12月6日
	 * @param ufiletype
	 */
	public void setUfiletype(Character ufiletype) {
		this.ufiletype = ufiletype;
	}
	public String getUfilepath() {
		return ufilepath;
	}
	public void setUfilepath(String ufilepath) {
		this.ufilepath = ufilepath;
	}
	public String getUremark() {
		return uremark;
	}
	public void setUremark(String uremark) {
		this.uremark = uremark;
	}
	@Override
	public String toString() {
		return "OtAnnex [uannexid=" + uannexid + ", uproductid=" + uproductid + ", ufiletype=" + ufiletype
				+ ", ufilepath=" + ufilepath + ", uremark=" + uremark + "]";
	}
}
