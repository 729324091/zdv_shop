package com.zdv.shop.model;

import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "ot_product_grade")
public class OtProductGrade  {


    @Id
    private String productgradeid;
    private String ucompid;
    private String uuserid;
    private String uproductid;
    private String uorderitemid;
    private String contentgrade;
    private String logisticsgrade;
    private String servicegrade;
    private String content;
    private String imgpic;
    private String isshowname;
    private String createdate;

    public String getIsshowname() {
		return isshowname;
	}

	public void setIsshowname(String isshowname) {
		this.isshowname = isshowname;
	}

	public String getCreatedate() {
		return createdate;
	}

	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	}

	public String getUorderitemid() {
        return uorderitemid;
    }

    public void setUorderitemid(String uorderitemid) {
        this.uorderitemid = uorderitemid;
    }

    public String getProductgradeid() {
        return productgradeid;
    }

    public void setProductgradeid(String productgradeid) {
        this.productgradeid = productgradeid;
    }

    public String getUcompid() {
        return ucompid;
    }

    public void setUcompid(String ucompid) {
        this.ucompid = ucompid;
    }

    public String getUuserid() {
        return uuserid;
    }

    public void setUuserid(String uuserid) {
        this.uuserid = uuserid;
    }

    public String getUproductid() {
        return uproductid;
    }

    public void setUproductid(String uproductid) {
        this.uproductid = uproductid;
    }

    public String getContentgrade() {
        return contentgrade;
    }

    public void setContentgrade(String contentgrade) {
        this.contentgrade = contentgrade;
    }

    public String getLogisticsgrade() {
        return logisticsgrade;
    }

    public void setLogisticsgrade(String logisticsgrade) {
        this.logisticsgrade = logisticsgrade;
    }

    public String getServicegrade() {
        return servicegrade;
    }

    public void setServicegrade(String servicegrade) {
        this.servicegrade = servicegrade;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImgpic() {
        return imgpic;
    }

    public void setImgpic(String imgpic) {
        this.imgpic = imgpic;
    }
}
