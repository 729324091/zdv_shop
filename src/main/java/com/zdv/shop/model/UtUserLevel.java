package com.zdv.shop.model;

import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "ut_user_level")
public class UtUserLevel {

    @Id
    private String uuserlevelid;
    private String ucompid;
    private String uuserlevel;
    private String udiscount;
    private String uintegral;
    private String ustyle;
    private String udistribution;
    private String udefault;
    private String uprofit_rate;
    private String uprofit_view_all;


    public String getUuserlevelid() {
        return uuserlevelid;
    }

    public void setUuserlevelid(String uuserlevelid) {
        this.uuserlevelid = uuserlevelid;
    }

    public String getUcompid() {
        return ucompid;
    }

    public void setUcompid(String ucompid) {
        this.ucompid = ucompid;
    }

    public String getUuserlevel() {
        return uuserlevel;
    }

    public void setUuserlevel(String uuserlevel) {
        this.uuserlevel = uuserlevel;
    }

    public String getUdiscount() {
        return udiscount;
    }

    public void setUdiscount(String udiscount) {
        this.udiscount = udiscount;
    }

    public String getUintegral() {
        return uintegral;
    }

    public void setUintegral(String uintegral) {
        this.uintegral = uintegral;
    }

    public String getUstyle() {
        return ustyle;
    }

    public void setUstyle(String ustyle) {
        this.ustyle = ustyle;
    }

    public String getUdistribution() {
        return udistribution;
    }

    public void setUdistribution(String udistribution) {
        this.udistribution = udistribution;
    }

    public String getUdefault() {
        return udefault;
    }

    public void setUdefault(String udefault) {
        this.udefault = udefault;
    }

    public String getUprofit_rate() {
        return uprofit_rate;
    }

    public void setUprofit_rate(String uprofit_rate) {
        this.uprofit_rate = uprofit_rate;
    }

    public String getUprofit_view_all() {
        return uprofit_view_all;
    }

    public void setUprofit_view_all(String uprofit_view_all) {
        this.uprofit_view_all = uprofit_view_all;
    }
}
