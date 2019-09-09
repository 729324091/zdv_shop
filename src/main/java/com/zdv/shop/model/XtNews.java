package com.zdv.shop.model;

import javax.persistence.Table;

@Table(name = "xt_news")
public class XtNews {

    private String newsid;

    private String ucompid;
    private String ucustomerid;
    private String title;
    private String source;
    private String author;
    private String summary;
    private String iffirstnews;
    private Integer browsepoint;
    private String content;
    private String ucreatedate;
    private String videopath;
    private String imgpath;


    public String getNewsid() {
        return newsid;
    }

    public void setNewsid(String newsid) {
        this.newsid = newsid;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getIffirstnews() {
        return iffirstnews;
    }

    public void setIffirstnews(String iffirstnews) {
        this.iffirstnews = iffirstnews;
    }

    public Integer getBrowsepoint() {
        return browsepoint;
    }

    public void setBrowsepoint(Integer browsepoint) {
        this.browsepoint = browsepoint;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUcreatedate() {
        return ucreatedate;
    }

    public void setUcreatedate(String ucreatedate) {
        this.ucreatedate = ucreatedate;
    }

    public String getVideopath() {
        return videopath;
    }

    public void setVideopath(String videopath) {
        this.videopath = videopath;
    }

    public String getImgpath() {
        return imgpath;
    }

    public void setImgpath(String imgpath) {
        this.imgpath = imgpath;
    }
}
