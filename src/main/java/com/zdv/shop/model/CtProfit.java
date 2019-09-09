package com.zdv.shop.model;

import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "ct_profit")
public class CtProfit {
    @Id
    private String uprofitid;//唯一id
    private String ucompid;//零售店主键编号
    private String ucustomerid;//商户号
    private Double ureferee;//推荐人分润
    private Double uplatform;//平台分润
    private Double umerchant;//商家分润   //通过该商家进入的粉丝
    private Double uarea;//区域经理分润
    private Double ushopkeeper;//店主分润
    private Double ubranch;//分公司分润
    private Double uvip;//VIP会员年费
    private Double uproducts;//推荐产品
    private Double ureturncash;//返现金额
    private Double uinmerchant;//入住厂家还在销售商
    private Double uservicefee; //提现手续费
    private Double ucashpool;   //现金池
    private Integer uintegral; //

    private String useable;//是否可用

    private String ucreatdate;

    public Integer getUintegral() {
        return uintegral;
    }

    public void setUintegral(Integer uintegral) {
        this.uintegral = uintegral;
    }

    public Double getUcashpool() {
        return ucashpool;
    }

    public void setUcashpool(Double ucashpool) {
        this.ucashpool = ucashpool;
    }

    public Double getUservicefee() {
        return uservicefee;
    }

    public void setUservicefee(Double uservicefee) {
        this.uservicefee = uservicefee;
    }

    public String getUprofitid() {
        return uprofitid;
    }

    public void setUprofitid(String uprofitid) {
        this.uprofitid = uprofitid;
    }

    public String getUcompid() {
        return ucompid;
    }

    public void setUcompid(String ucompid) {
        this.ucompid = ucompid;
    }

    public String getUcustomerid() {
        return ucustomerid;
    }

    public void setUcustomerid(String ucustomerid) {
        this.ucustomerid = ucustomerid;
    }

    public Double getUreferee() {
        return ureferee;
    }

    public void setUreferee(Double ureferee) {
        this.ureferee = ureferee;
    }

    public Double getUplatform() {
        return uplatform;
    }

    public void setUplatform(Double uplatform) {
        this.uplatform = uplatform;
    }

    public Double getUmerchant() {
        return umerchant;
    }

    public void setUmerchant(Double umerchant) {
        this.umerchant = umerchant;
    }

    public Double getUarea() {
        return uarea;
    }

    public void setUarea(Double uarea) {
        this.uarea = uarea;
    }

    public Double getUshopkeeper() {
        return ushopkeeper;
    }

    public void setUshopkeeper(Double ushopkeeper) {
        this.ushopkeeper = ushopkeeper;
    }

    public Double getUbranch() {
        return ubranch;
    }

    public void setUbranch(Double ubranch) {
        this.ubranch = ubranch;
    }

    public Double getUvip() {
        return uvip;
    }

    public void setUvip(Double uvip) {
        this.uvip = uvip;
    }

    public String getUcreatdate() {
        return ucreatdate;
    }

    public void setUcreatdate(String ucreatdate) {
        this.ucreatdate = ucreatdate;
    }

    public String getUseable() {
        return useable;
    }

    public void setUseable(String useable) {
        this.useable = useable;
    }

    public Double getUproducts() {
        return uproducts;
    }

    public void setUproducts(Double uproducts) {
        this.uproducts = uproducts;
    }

    public Double getUreturncash() {
        return ureturncash;
    }

    public void setUreturncash(Double ureturncash) {
        this.ureturncash = ureturncash;
    }

    public Double getUinmerchant() {
        return uinmerchant;
    }

    public void setUinmerchant(Double uinmerchant) {
        this.uinmerchant = uinmerchant;
    }
}