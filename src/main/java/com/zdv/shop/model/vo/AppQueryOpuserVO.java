package com.zdv.shop.model.vo;

/**
 * APP查询管理人员列表查询参数
 * @author LBY
 * @data 2019年1月15日 
 */
public class AppQueryOpuserVO {

	/**
	 * 经销商ID
	 */
	private String udistributorid;
	//销售商ID
	private String ucompid;
	/**
	 * 用户ID
	 */
	private String uopuserid;
	/**
	 * 角色ID
	 */
	private String uroleid;
	/**
	 * 模糊查询
	 */
	private String keywords;
	/**
	 * 角色ID选项查询条件
	 */
	private String uroleidCriteria;
	/**
	 * 当前页码
	 */
	private Integer pageNo;
	/**
	 * 显示页数
	 */
	private Integer pageSize;
	public String getUdistributorid() {
		return udistributorid;
	}
	public void setUdistributorid(String udistributorid) {
		this.udistributorid = udistributorid;
	}
	public String getUopuserid() {
		return uopuserid;
	}
	public void setUopuserid(String uopuserid) {
		this.uopuserid = uopuserid;
	}
	public String getUroleid() {
		return uroleid;
	}
	public void setUroleid(String uroleid) {
		this.uroleid = uroleid;
	}
	public String getKeywords() {
		return keywords;
	}
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	public String getUroleidCriteria() {
		return uroleidCriteria;
	}
	public void setUroleidCriteria(String uroleidCriteria) {
		this.uroleidCriteria = uroleidCriteria;
	}
	public Integer getPageNo() {
		return pageNo;
	}
	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	/**
	 * @author LBY
	 * @data 2019年1月15日
	 * @return
	 */
	@Override
	public String toString() {
		return "AppQueryOpuserVO [udistributorid=" + udistributorid + ", uopuserid=" + uopuserid + ", uroleid="
				+ uroleid + ", keywords=" + keywords + ", uroleidCriteria=" + uroleidCriteria + ", pageNo=" + pageNo
				+ ", pageSize=" + pageSize + "]";
	}
	public String getUcompid() {
		return ucompid;
	}
	public void setUcompid(String ucompid) {
		this.ucompid = ucompid;
	}
}
