package com.zdv.shop.model;

import javax.persistence.Table;

//单位角色与权限
@Table(name = "dt_role_operation")
public class DtRoleOperation {

    private String uroleid;

    private String uopid;

    private String udistributorid;

    public String getUdistributorid() {
        return udistributorid;
    }

    public void setUdistributorid(String udistributorid) {
        this.udistributorid = udistributorid;
    }

    public String getUroleid() {
        return uroleid;
    }

    public void setUroleid(String uroleid) {
        this.uroleid = uroleid;
    }

    public String getUopid() {
        return uopid;
    }

    public void setUopid(String uopid) {
        this.uopid = uopid;
    }

    @Override
    public String toString() {
        return "DtRoleOperation{" +
                "uroleid='" + uroleid + '\'' +
                ", uopid='" + uopid + '\'' +
                ", udistributorid='" + udistributorid + '\'' +
                '}';
    }
}