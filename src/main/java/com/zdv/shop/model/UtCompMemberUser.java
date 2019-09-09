package com.zdv.shop.model;

import javax.persistence.Table;
import javax.persistence.Transient;

@Table(name = "ut_comp_member_user")
public class UtCompMemberUser {
    private String ucompid;
    private String ucustomerid;
    private String uuserid;
    private String uopuserid;
    private String ufromchannel;
    private String qrcodepath;//户用分享平台微信公众号关注二维码
    private String ucreatedate;
    @Transient
    private String appid;//微信的APP_ID
    @Transient
    private String appsecret;//微信的APP_SECRET
    @Transient
    private String mchid;//微信的商户号MCH_ID

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

    public String getUopuserid() {
        return uopuserid;
    }

    public void setUopuserid(String uopuserid) {
        this.uopuserid = uopuserid;
    }

    public String getUfromchannel() {
        return ufromchannel;
    }

    public void setUfromchannel(String ufromchannel) {
        this.ufromchannel = ufromchannel;
    }

    public String getUcreatedate() {
        return ucreatedate;
    }

    public void setUcreatedate(String ucreatedate) {
        this.ucreatedate = ucreatedate;
    }

	public String getQrcodepath() {
		return qrcodepath;
	}

	public void setQrcodepath(String qrcodepath) {
		this.qrcodepath = qrcodepath;
	}

	public String getAppid() {
		return appid;
	}

	public String getAppsecret() {
		return appsecret;
	}

	public String getMchid() {
		return mchid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public void setAppsecret(String appsecret) {
		this.appsecret = appsecret;
	}

	public void setMchid(String mchid) {
		this.mchid = mchid;
	}
}
