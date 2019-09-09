package com.zdv.shop.model;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 通知公告信息
 * @author LBY
 * @date: 2018年12月6日
 */
@Table(name = "ot_notice")
public class OtNotice {

	/**
	 * 公告ID
	 */
	@Id
	@GeneratedValue(generator="UUID")
	private String unoticeid;
	/**
	 * 公告标题
	 */
	private String utitle;
	/**
	 * 源来
	 */
	private String usource;
	/**
	 * 作者
	 */
	private String uauthor;
	/**
	 * 简要
	 */
	private String usummary;
	/**
	 * 封面图
	 */
	private String uhomepic;
	/**
	 * 描述
	 */
	private String udesc;
	/**
	 * 发布时间
	 */
	private Long ucreatedate;
	/**
	 * 是否有效(01有效,02无效)
	 * - 关联：com.zdv.shop.model.enums.STATUS
	 */
	private String uflag;
	public String getUnoticeid() {
		return unoticeid;
	}
	public void setUnoticeid(String unoticeid) {
		this.unoticeid = unoticeid;
	}
	public String getUtitle() {
		return utitle;
	}
	public void setUtitle(String utitle) {
		this.utitle = utitle;
	}
	public String getUsource() {
		return usource;
	}
	public void setUsource(String usource) {
		this.usource = usource;
	}
	public String getUauthor() {
		return uauthor;
	}
	public void setUauthor(String uauthor) {
		this.uauthor = uauthor;
	}
	public String getUsummary() {
		return usummary;
	}
	public void setUsummary(String usummary) {
		this.usummary = usummary;
	}
	public String getUhomepic() {
		return uhomepic;
	}
	public void setUhomepic(String uhomepic) {
		this.uhomepic = uhomepic;
	}
	public String getUdesc() {
		return udesc;
	}
	public void setUdesc(String udesc) {
		this.udesc = udesc;
	}
	public Long getUcreatedate() {
		return ucreatedate;
	}
	public void setUcreatedate(Long ucreatedate) {
		this.ucreatedate = ucreatedate;
	}
	/**
	 * 是否有效(01有效,02无效)
	 * - 关联：com.zdv.shop.model.enums.STATUS
	 * @author LBY
	 * @date 2018年12月6日
	 * @return
	 */
	public String getUflag() {
		return uflag;
	}
	/**
	 * 是否有效(01有效,02无效)
	 * - 关联：com.zdv.shop.model.enums.STATUS
	 * @author LBY
	 * @date 2018年12月6日
	 * @param uflag
	 */
	public void setUflag(String uflag) {
		this.uflag = uflag;
	}
	@Override
	public String toString() {
		return "OtNotice [unoticeid=" + unoticeid + ", utitle=" + utitle + ", usource=" + usource + ", uauthor="
				+ uauthor + ", usummary=" + usummary + ", uhomepic=" + uhomepic + ", udesc=" + udesc + ", ucreatedate="
				+ ucreatedate + ", uflag=" + uflag + "]";
	}
}
