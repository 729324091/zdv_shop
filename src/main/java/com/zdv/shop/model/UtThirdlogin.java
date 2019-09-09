package com.zdv.shop.model;

import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "ut_thirdlogin")
public class UtThirdlogin {

    @Id
    private String thirdloginid;

    private String access_token;

    private String openid;

    private String uuserid;

    private String utype;

    public String getThirdloginid() {
        return thirdloginid;
    }

    public void setThirdloginid(String thirdloginid) {
        this.thirdloginid = thirdloginid;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getUuserid() {
        return uuserid;
    }

    public void setUuserid(String uuserid) {
        this.uuserid = uuserid;
    }

    public String getUtype() {
        return utype;
    }

    public void setUtype(String utype) {
        this.utype = utype;
    }
}
