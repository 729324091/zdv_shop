package com.zdv.shop.model;

public class Version {

	private String versionid;//
	private String versionno;//100-》1.0.0
	private String versionname;//V1.0.0
	private String type;//是否强制性升级 0 非强制更新 1强制更新',
	private String itunesurl;//https://app.yoda-as.com/uploadyoda/Yoda.apk
	private String updatecontent;//Android 1.0
	private String updatedate;//时间
	public String getVersionid() {
		return versionid;
	}
	public String getVersionno() {
		return versionno;
	}
	public String getVersionname() {
		return versionname;
	}
	public String getType() {
		return type;
	}
	public String getItunesurl() {
		return itunesurl;
	}
	public String getUpdatecontent() {
		return updatecontent;
	}
	public String getUpdatedate() {
		return updatedate;
	}
	public void setVersionid(String versionid) {
		this.versionid = versionid;
	}
	public void setVersionno(String versionno) {
		this.versionno = versionno;
	}
	public void setVersionname(String versionname) {
		this.versionname = versionname;
	}
	public void setType(String type) {
		this.type = type;
	}
	public void setItunesurl(String itunesurl) {
		this.itunesurl = itunesurl;
	}
	public void setUpdatecontent(String updatecontent) {
		this.updatecontent = updatecontent;
	}
	public void setUpdatedate(String updatedate) {
		this.updatedate = updatedate;
	}
    
	
}