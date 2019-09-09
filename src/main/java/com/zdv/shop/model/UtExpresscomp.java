package com.zdv.shop.model;

import javax.persistence.*;

/**
 * 快递公司对应代码表
 */
@Table(name = "ut_expresscomp")
public class UtExpresscomp {
	/**
	 * iD
	 */
    @Id
    private String expresscompid;//快递企业ID
    private String expressname;//快递名称
    private String expresscode;//快递公司编号
	public String getExpresscompid() {
		return expresscompid;
	}
	public String getExpressname() {
		return expressname;
	}
	public String getExpresscode() {
		return expresscode;
	}
	public void setExpresscompid(String expresscompid) {
		this.expresscompid = expresscompid;
	}
	public void setExpressname(String expressname) {
		this.expressname = expressname;
	}
	public void setExpresscode(String expresscode) {
		this.expresscode = expresscode;
	}
    

}