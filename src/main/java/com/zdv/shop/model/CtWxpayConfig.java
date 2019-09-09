package com.zdv.shop.model;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 支付公众号配置表
 */
@Table(name = "ct_wxpay_config")
public class CtWxpayConfig {
	/**
	 * iD
	 */
    @Id
    private String uwxpayid;
    private String ucompid;//零售店主键编号
    private String ucustomerid;//商户号
    private String appid;//微信的APP_ID
    private String appsecret;//微信的APP_SECRET
    private String mchid;//微信的商户号MCH_ID
    private String apikey;//微信的支付秘钥API_KEY
    private String utypes;//支付类型 0为微信，1为支付宝，2 银联
    private String wxtoken;//微信响应token
    private String wxpath;//微信响应地址
    private String templateid;//模板信息ID

    private String biz;//微信BIZ码
    private String apiclientcertpath;//apiclient_cert.p12路径	
    private String apiclientcertpempath;//apiclient_cert.pem路径
    private String apiclientkeypath;//apiclient_key.pem路径
    private String ucreatedate;//创建时间戳
	public String getUwxpayid() {
		return uwxpayid;
	}
	public String getUcompid() {
		return ucompid;
	}
	public String getUcustomerid() {
		return ucustomerid;
	}
	public String getAppid() {
		return appid;
	}
	public String getAppsecret() {
		return appsecret;
	}
	public String getMchid() {
		return mchid;
	}
	public String getApikey() {
		return apikey;
	}
	public String getBiz() {
		return biz;
	}
	public String getApiclientcertpath() {
		return apiclientcertpath;
	}
	public String getApiclientcertpempath() {
		return apiclientcertpempath;
	}
	public String getApiclientkeypath() {
		return apiclientkeypath;
	}
	public String getUcreatedate() {
		return ucreatedate;
	}
	public void setUwxpayid(String uwxpayid) {
		this.uwxpayid = uwxpayid;
	}
	public void setUcompid(String ucompid) {
		this.ucompid = ucompid;
	}
	public void setUcustomerid(String ucustomerid) {
		this.ucustomerid = ucustomerid;
	}
	public void setAppid(String appid) {
		this.appid = appid;
	}
	public void setAppsecret(String appsecret) {
		this.appsecret = appsecret;
	}
	public void setMchid(String mchid) {
		this.mchid = mchid;
	}
	public void setApikey(String apikey) {
		this.apikey = apikey;
	}
	public void setBiz(String biz) {
		this.biz = biz;
	}
	public void setApiclientcertpath(String apiclientcertpath) {
		this.apiclientcertpath = apiclientcertpath;
	}
	public void setApiclientcertpempath(String apiclientcertpempath) {
		this.apiclientcertpempath = apiclientcertpempath;
	}
	public void setApiclientkeypath(String apiclientkeypath) {
		this.apiclientkeypath = apiclientkeypath;
	}
	public void setUcreatedate(String ucreatedate) {
		this.ucreatedate = ucreatedate;
	}
	public String getUtypes() {
		return utypes;
	}
	public String getWxtoken() {
		return wxtoken;
	}
	public String getWxpath() {
		return wxpath;
	}
	public void setUtypes(String utypes) {
		this.utypes = utypes;
	}
	public void setWxtoken(String wxtoken) {
		this.wxtoken = wxtoken;
	}
	public void setWxpath(String wxpath) {
		this.wxpath = wxpath;
	}
	public String getTemplateid() {
		return templateid;
	}
	public void setTemplateid(String templateid) {
		this.templateid = templateid;
	}

}