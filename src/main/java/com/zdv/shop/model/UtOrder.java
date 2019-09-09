package com.zdv.shop.model;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Table(name = "ut_order")
public class UtOrder extends BaseObject{

    @Id
    private String uorderid;  //订单ID
    private String uorderno;  //订单号
    private String uproductname;  //产品名称
    private String uproductnum;  //商品数量
    private String utotalprice;  //总价格
    private String upayprice;  //支付价格
    private String upaystate;  //支付状态
    private String upaytype;  //支付类型
    private String ucostprice; //成本价
    private String uuserid;  //购买用户ID
    private String uname;  //用户名
    private String uprofit_rate;  //打折
    private String uaddress;  //用户地址
    private String ucompid;  //销售商主键
    private String ucompname;  //销售商名称
    private String usolve;  //是否处理
    private String usend_type;  //配送类型
    private String ucrawl;  //是否已爬
    private String ushoptype;  //下单平台
    private String uprint;  //打印次数
    private String uremark;  //订单备注
    private String ueflag;  //状态
    private String ucreatedate;  //创建时间
    private String udistributorid;    //经销商ID 用于经销商发货
    private String ucontact; //用户联系人
    private String utel;     //购物电话
    private String ulogo;   //购物人图像
    private String ucaddress;    //商家地址
    private String uccontact;    //商家联系人
    private String uctel;        //商家电话
    private String uclogo;       //商家logo
    private String ucustomerid;   //商户id
    private Integer uintegral;   //商户id
    private String uverificationcode;
    @Transient
    //日期查询排行
    private String types;

    public String getUverificationcode() {
        return uverificationcode;
    }

    public void setUverificationcode(String uverificationcode) {
        this.uverificationcode = uverificationcode;
    }

    public Integer getUintegral() {
        return uintegral;
    }

    public void setUintegral(Integer uintegral) {
        this.uintegral = uintegral;
    }

    public String getUdistributorid() {
        return udistributorid;
    }

    public void setUdistributorid(String udistributorid) {
        this.udistributorid = udistributorid;
    }

    public String getUcustomerid() {
        return ucustomerid;
    }

    public void setUcustomerid(String ucustomerid) {
        this.ucustomerid = ucustomerid;
    }

    public String getUcostprice() {
        return ucostprice;
    }

    public void setUcostprice(String ucostprice) {
        this.ucostprice = ucostprice;
    }

    public String getUcontact() {
        return ucontact;
    }

    public void setUcontact(String ucontact) {
        this.ucontact = ucontact;
    }

    public String getUtel() {
        return utel;
    }

    public void setUtel(String utel) {
        this.utel = utel;
    }

    public String getUlogo() {
        return ulogo;
    }

    public void setUlogo(String ulogo) {
        this.ulogo = ulogo;
    }

    public String getUcaddress() {
        return ucaddress;
    }

    public void setUcaddress(String ucaddress) {
        this.ucaddress = ucaddress;
    }

    public String getUccontact() {
        return uccontact;
    }

    public void setUccontact(String uccontact) {
        this.uccontact = uccontact;
    }

    public String getUctel() {
        return uctel;
    }

    public void setUctel(String uctel) {
        this.uctel = uctel;
    }

    public String getUclogo() {
        return uclogo;
    }

    public void setUclogo(String uclogo) {
        this.uclogo = uclogo;
    }

    public String getUorderid() {
        return uorderid;
    }

    public void setUorderid(String uorderid) {
        this.uorderid = uorderid;
    }

    public String getUorderno() {
        return uorderno;
    }

    public void setUorderno(String uorderno) {
        this.uorderno = uorderno;
    }

    public String getUproductname() {
        return uproductname;
    }

    public void setUproductname(String uproductname) {
        this.uproductname = uproductname;
    }

    public String getUproductnum() {
        return uproductnum;
    }

    public void setUproductnum(String uproductnum) {
        this.uproductnum = uproductnum;
    }

    public String getUtotalprice() {
        return utotalprice;
    }

    public void setUtotalprice(String utotalprice) {
        this.utotalprice = utotalprice;
    }

    public String getUpayprice() {
        return upayprice;
    }

    public void setUpayprice(String upayprice) {
        this.upayprice = upayprice;
    }

    public String getUpaystate() {
        return upaystate;
    }

    public void setUpaystate(String upaystate) {
        this.upaystate = upaystate;
    }

    public String getUpaytype() {
        return upaytype;
    }

    public void setUpaytype(String upaytype) {
        this.upaytype = upaytype;
    }

    public String getUuserid() {
        return uuserid;
    }

    public void setUuserid(String uuserid) {
        this.uuserid = uuserid;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getUprofit_rate() {
        return uprofit_rate;
    }

    public void setUprofit_rate(String uprofit_rate) {
        this.uprofit_rate = uprofit_rate;
    }

    public String getUaddress() {
        return uaddress;
    }

    public void setUaddress(String uaddress) {
        this.uaddress = uaddress;
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

    public String getUsolve() {
        return usolve;
    }

    public void setUsolve(String usolve) {
        this.usolve = usolve;
    }

    public String getUsend_type() {
        return usend_type;
    }

    public void setUsend_type(String usend_type) {
        this.usend_type = usend_type;
    }

    public String getUcrawl() {
        return ucrawl;
    }

    public void setUcrawl(String ucrawl) {
        this.ucrawl = ucrawl;
    }

    public String getUshoptype() {
        return ushoptype;
    }

    public void setUshoptype(String ushoptype) {
        this.ushoptype = ushoptype;
    }

    public String getUprint() {
        return uprint;
    }

    public void setUprint(String uprint) {
        this.uprint = uprint;
    }

    public String getUremark() {
        return uremark;
    }

    public void setUremark(String uremark) {
        this.uremark = uremark;
    }

    public String getUeflag() {
        return ueflag;
    }

    public void setUeflag(String ueflag) {
        this.ueflag = ueflag;
    }

    public String getUcreatedate() {
        return ucreatedate;
    }

    public void setUcreatedate(String ucreatedate) {
        this.ucreatedate = ucreatedate;
    }



	public String getTypes() {
		return types;
	}

	


	public void setTypes(String types) {
		this.types = types;
	}
}
