package com.zdv.shop.model;

import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "ut_financelog")
public class UtFinancelog extends BaseObject{

    @Id
    private String ufinancelogid; //财务日志id
    private String ucompid;         //单位编码id
    private String uuserid;         //用户id
    private String uinfoid;         //支付对象id
    private String ustatus;      //状态(0正在处理,1处理成功,2处理失败)
    private String utype;        //0为用户财务日志，1为企业财务日志
    private String utradetype;   //0充值,1支付2余额扣款,3其他,4转账,5分润
    private String utrademoney;     //交易金额
    private String uglidenumber;    //流水帐号
    private String ifline;       //    是否线下支付（0为线上支付，1为线下已收款）
    private String upaymode;        //    支付方说明，如：支付宝、手动支付、银联
    private String ucreatedate;     //创建时间
    private String accttype;     //    进出类型（0进账，1出账，2内部交易）
    private String transactionId;  //第三方支付成功后返回的订单号码

    public String getUfinancelogid() {
        return ufinancelogid;
    }

    public void setUfinancelogid(String ufinancelogid) {
        this.ufinancelogid = ufinancelogid;
    }

    public String getUcompid() {
        return ucompid;
    }

    public void setUcompid(String ucompid) {
        this.ucompid = ucompid;
    }

    public String getUuserid() {
        return uuserid;
    }

    public void setUuserid(String uuserid) {
        this.uuserid = uuserid;
    }

    public String getUinfoid() {
        return uinfoid;
    }

    public void setUinfoid(String uinfoid) {
        this.uinfoid = uinfoid;
    }

    public String getUstatus() {
        return ustatus;
    }

    public void setUstatus(String ustatus) {
        this.ustatus = ustatus;
    }

    public String getUtype() {
        return utype;
    }

    public void setUtype(String utype) {
        this.utype = utype;
    }

    public String getUtradetype() {
        return utradetype;
    }

    public void setUtradetype(String utradetype) {
        this.utradetype = utradetype;
    }

    public String getUtrademoney() {
        return utrademoney;
    }

    public void setUtrademoney(String utrademoney) {
        this.utrademoney = utrademoney;
    }

    public String getUglidenumber() {
        return uglidenumber;
    }

    public void setUglidenumber(String uglidenumber) {
        this.uglidenumber = uglidenumber;
    }

    public String getIfline() {
        return ifline;
    }

    public void setIfline(String ifline) {
        this.ifline = ifline;
    }

    public String getUpaymode() {
        return upaymode;
    }

    public void setUpaymode(String upaymode) {
        this.upaymode = upaymode;
    }

    public String getUcreatedate() {
        return ucreatedate;
    }

    public void setUcreatedate(String ucreatedate) {
        this.ucreatedate = ucreatedate;
    }

    public String getAccttype() {
        return accttype;
    }

    public void setAccttype(String accttype) {
        this.accttype = accttype;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }
}
