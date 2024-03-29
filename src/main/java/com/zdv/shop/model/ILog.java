package com.zdv.shop.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 日志信息表
 * @author administrator
 */
@Table(name = "xt_syslog")
public class ILog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	/**请求用户*/
	private String username;
	private String customerid;//商户号
	private String compid;//渠道ID
	/**请求描述*/
	private String description;
	/**请求地址*/
	private String url;
	/**执行方法*/
	private String method;
	/**请求参数*/
	private String params;
	/**日志类型(0操作日志;1异常日志)*/
	private Integer type;
	/**请求IP*/
	private String requestip;
	/**异常描述*/
	private String detail;
	/**请求日期*/
	private String operDate;
	@Transient
	private String startDate;
	@Transient
	private String endDate;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public String getParams() {
		return params;
	}
	public void setParams(String params) {
		this.params = params;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getRequestip() {
		return requestip;
	}
	public void setRequestip(String requestip) {
		this.requestip = requestip;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public String getOperDate() {
		return operDate;
	}
	public void setOperDate(String operDate) {
		this.operDate = operDate;
	}
	public String getCustomerid() {
		return customerid;
	}
	public void setCustomerid(String customerid) {
		this.customerid = customerid;
	}
	public String getCompid() {
		return compid;
	}
	public String getStartDate() {
		return startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setCompid(String compid) {
		this.compid = compid;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	
}
