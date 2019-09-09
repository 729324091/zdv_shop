package com.zdv.shop.model;

import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "ut_userfreeproduct")
public class UtUserfreeproduct {

    @Id
    private String uuserfreeproductid;
    private String ucompid;
    private String ucustomerid;
    private String uuserid;
    private String ucomproductid;
    private String uorderitemid;
    private String uproductid;
    private String upromotesales;
    private String ucreatedate;


    public String getUorderitemid() {
        return uorderitemid;
    }

    public void setUorderitemid(String uorderitemid) {
        this.uorderitemid = uorderitemid;
    }

    public String getUuserfreeproductid() {
        return uuserfreeproductid;
    }

    public void setUuserfreeproductid(String uuserfreeproductid) {
        this.uuserfreeproductid = uuserfreeproductid;
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

    public String getUuserid() {
        return uuserid;
    }

    public void setUuserid(String uuserid) {
        this.uuserid = uuserid;
    }

    public String getUcomproductid() {
        return ucomproductid;
    }

    public void setUcomproductid(String ucomproductid) {
        this.ucomproductid = ucomproductid;
    }

    public String getUproductid() {
        return uproductid;
    }

    public void setUproductid(String uproductid) {
        this.uproductid = uproductid;
    }

    public String getUpromotesales() {
        return upromotesales;
    }

    public void setUpromotesales(String upromotesales) {
        this.upromotesales = upromotesales;
    }

    public String getUcreatedate() {
        return ucreatedate;
    }

    public void setUcreatedate(String ucreatedate) {
        this.ucreatedate = ucreatedate;
    }
}
