package com.zdv.shop.model;

import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "ut_area_manager")
public class UtAreaManager {

    @Id
    private String umanagerid;
    private String uareaid;
    private String uuserid;
    private String uprofit_rate;

    public String getUmanagerid() {
        return umanagerid;
    }

    public void setUmanagerid(String umanagerid) {
        this.umanagerid = umanagerid;
    }

    public String getUareaid() {
        return uareaid;
    }

    public void setUareaid(String uareaid) {
        this.uareaid = uareaid;
    }

    public String getUuserid() {
        return uuserid;
    }

    public void setUuserid(String uuserid) {
        this.uuserid = uuserid;
    }

    public String getUprofit_rate() {
        return uprofit_rate;
    }

    public void setUprofit_rate(String uprofit_rate) {
        this.uprofit_rate = uprofit_rate;
    }
}
