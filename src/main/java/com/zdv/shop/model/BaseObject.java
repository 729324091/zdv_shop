 package com.zdv.shop.model;
 import javax.persistence.Transient;

import org.apache.ibatis.annotations.Param;

 /**
  * 基础对象 公共条件
  * @author jock
  *  
  */
 public abstract class BaseObject{
	@Transient
	public static final int DEFAULT_PAGE_SIZE = 15;
	@Transient
	protected String orderByClause; //排序 字段
	@Transient
	protected String sorting; //排序 顺序
	@Transient
	private String keywords;//搜索条件
	@Transient
	private Integer pageNo;
	@Transient
	private Integer pageSize=DEFAULT_PAGE_SIZE;
	@Transient
	private Integer num;
	@Transient
	private String uopuserid;//登录管理员
	@Transient
	private String begindate;//查询用的开始时间
	@Transient
	private String enddate;//查询用的结束时间
	public String getOpuserid() {
		return uopuserid;
	}
	public void setOpuserid(String uopuserid) {
		this.uopuserid = uopuserid;
	}
	public String getOrderByClause() {
		return orderByClause;
	}
	public String getSorting() {
		return sorting;
	}
	public String getKeywords() {
		return keywords;
	}
	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}
	public void setSorting(String sorting) {
		this.sorting = sorting;
	}
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	public Integer getPageNo() {
		return pageNo;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	public String getBegindate() {
		return begindate;
	}
	public String getEnddate() {
		return enddate;
	}
	public void setBegindate(String begindate) {
		this.begindate = begindate;
	}
	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}
	

 }