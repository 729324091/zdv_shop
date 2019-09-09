package com.zdv.shop.model;

import javax.persistence.Table;

@Table(name = "ut_myproductstore")
public class UtMyproductstore {

    private String uproductstoreid;
    private String ucomproductid;
    private String ucompid;
    private String ucustomerid;
    private String uuserid;
    private String uproductid;
    private String ucreatedate;


    public String getUproductstoreid() {
        return uproductstoreid;
    }

    public void setUproductstoreid(String uproductstoreid) {
        this.uproductstoreid = uproductstoreid;
    }

    public String getUcomproductid() {
        return ucomproductid;
    }

    public void setUcomproductid(String ucomproductid) {
        this.ucomproductid = ucomproductid;
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

    public String getUproductid() {
        return uproductid;
    }

    public void setUproductid(String uproductid) {
        this.uproductid = uproductid;
    }

    public String getUcreatedate() {
        return ucreatedate;
    }

    public void setUcreatedate(String ucreatedate) {
        this.ucreatedate = ucreatedate;
    }
}
