package com.zdv.shop.model;

import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "ut_user_distribution")
public class UtUserDistribution extends BaseObject {


    @Id
    private String udistributionid;
    private String ucompid;
    private String ucustomerid;
    private String udistributionname;
    private Double uprofit;
    private String ulevel;
    private Integer usubordinatesnum;
    private String uupdatemode;
    private String uparentid;
    private String ucreatedate;

    private Integer udistributionprice;

    private Integer usubordinatesprice;
    private String udesc; //s说明

    public String getUdesc() {
        return udesc;
    }

    public void setUdesc(String udesc) {
        this.udesc = udesc;
    }

    public Integer getUdistributionprice() {
        return udistributionprice;
    }

    public void setUdistributionprice(Integer udistributionprice) {
        this.udistributionprice = udistributionprice;
    }

    public Integer getUsubordinatesprice() {
        return usubordinatesprice;
    }

    public void setUsubordinatesprice(Integer usubordinatesprice) {
        this.usubordinatesprice = usubordinatesprice;
    }

    public String getUdistributionid() {
        return udistributionid;
    }

    public void setUdistributionid(String udistributionid) {
        this.udistributionid = udistributionid;
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

    public String getUdistributionname() {
        return udistributionname;
    }

    public void setUdistributionname(String udistributionname) {
        this.udistributionname = udistributionname;
    }

    public Double getUprofit() {
        return uprofit;
    }

    public void setUprofit(Double uprofit) {
        this.uprofit = uprofit;
    }

    public String getUlevel() {
        return ulevel;
    }

    public void setUlevel(String ulevel) {
        this.ulevel = ulevel;
    }

    public Integer getUsubordinatesnum() {
        return usubordinatesnum;
    }

    public void setUsubordinatesnum(Integer usubordinatesnum) {
        this.usubordinatesnum = usubordinatesnum;
    }

    public String getUupdatemode() {
        return uupdatemode;
    }

    public void setUupdatemode(String uupdatemode) {
        this.uupdatemode = uupdatemode;
    }

    public String getUparentid() {
        return uparentid;
    }

    public void setUparentid(String uparentid) {
        this.uparentid = uparentid;
    }

    public String getUcreatedate() {
        return ucreatedate;
    }

    public void setUcreatedate(String ucreatedate) {
        this.ucreatedate = ucreatedate;
    }
}
