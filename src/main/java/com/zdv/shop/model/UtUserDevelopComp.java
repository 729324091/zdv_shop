package com.zdv.shop.model;

import javax.persistence.Table;

@Table(name = "ut_user_devlop_comp")
public class UtUserDevelopComp {

    private String uuserid;
    private String ucompid;
    private String ucompname;
    private String ucustomerid;
    private String uremark;
    private String ucreatedate;

    public String getUuserid() {
        return uuserid;
    }

    public void setUuserid(String uuserid) {
        this.uuserid = uuserid;
    }

    public String getUcompid() {
        return ucompid;
    }

    public void setUcompid(String ucompid) {
        this.ucompid = ucompid;
    }

    public String getUcompname() {
        return ucompname;
    }

    public void setUcompname(String ucompname) {
        this.ucompname = ucompname;
    }

    public String getUcustomerid() {
        return ucustomerid;
    }

    public void setUcustomerid(String ucustomerid) {
        this.ucustomerid = ucustomerid;
    }

    public String getUremark() {
        return uremark;
    }

    public void setUremark(String uremark) {
        this.uremark = uremark;
    }

    public String getUcreatedate() {
        return ucreatedate;
    }

    public void setUcreatedate(String ucreatedate) {
        this.ucreatedate = ucreatedate;
    }
}
