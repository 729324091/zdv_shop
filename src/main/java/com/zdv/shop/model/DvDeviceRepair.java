package com.zdv.shop.model;

import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "dv_deviceRepair")
public class DvDeviceRepair {

    @Id
    private String udeviceRepairid;
    private String udeviceid;
    private String ummsed;
    private String ubatchno;
    private String udevicename;
    private String ustatus;
    private String ucompid;
    private String ucompname;
    private String uopname;
    private String uoperdate;


    public String getUdeviceRepairid() {
        return udeviceRepairid;
    }

    public void setUdeviceRepairid(String udeviceRepairid) {
        this.udeviceRepairid = udeviceRepairid;
    }

    public String getUdeviceid() {
        return udeviceid;
    }

    public void setUdeviceid(String udeviceid) {
        this.udeviceid = udeviceid;
    }

    public String getUmmsed() {
        return ummsed;
    }

    public void setUmmsed(String ummsed) {
        this.ummsed = ummsed;
    }

    public String getUbatchno() {
        return ubatchno;
    }

    public void setUbatchno(String ubatchno) {
        this.ubatchno = ubatchno;
    }

    public String getUdevicename() {
        return udevicename;
    }

    public void setUdevicename(String udevicename) {
        this.udevicename = udevicename;
    }

    public String getUstatus() {
        return ustatus;
    }

    public void setUstatus(String ustatus) {
        this.ustatus = ustatus;
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

    public String getUopname() {
        return uopname;
    }

    public void setUopname(String uopname) {
        this.uopname = uopname;
    }

    public String getUoperdate() {
        return uoperdate;
    }

    public void setUoperdate(String uoperdate) {
        this.uoperdate = uoperdate;
    }
}
