/*
package com.zdv.shop.weixinh5.controller;

import com.zdv.shop.common.pojo.ParamData;
import com.zdv.shop.controller.BaseController;
import com.zdv.shop.weixinh5.model.EnterprisesPayment;
import com.zdv.shop.weixinh5.sdk.WXPayUtil;
import com.zdv.shop.weixinh5.util.ClientCustomSSL;
import com.zdv.shop.weixinh5.util.PayCommonUtil;
import com.zdv.shop.weixinh5.util.WeixinpayUtil;

import java.util.SortedMap;
import java.util.TreeMap;

*/
/**
 * @Auther: liuyubo
 * @Date: 2018/8/10 20:59
 * @Description: test
 *//*

public class Test{

    private static SortedMap<String,String> sortedMap = new TreeMap<>();
 
    public static void main(String[] args) {

 
        String sginCode = getSgin("xxxx",111);//获取用户openid 和 用户要提现的金额  拿到签名
        EnterprisesPayment enterprisesPayment  = addEnterprisesPayment("xxxx",111,sginCode);//拿到签名后加密得到要发送到对象数据
        String enterprisesPaymentXML = WeixinpayUtil.createDocumentForEnterprisesPayment(enterprisesPayment);   //封装xml 数据发送
        String returnXmlData =  ClientCustomSSL.doRefund("https://api.mch.weixin.qq.com/mmpaymkttransfers/promotion/transfers",enterprisesPaymentXML);

 
 
    }
 
 
    public EnterprisesPayment addEnterprisesPayment(String openid, Integer amount, String sginCode){ 
        EnterprisesPayment enterprisesPayment = new EnterprisesPayment(); 
        enterprisesPayment.setMch_appid("appid");//商户号appid 
        enterprisesPayment.setMchid("商户号");//商户号 
        // enterprisesPayment.setDevice_info(null);//设备号 非必填 
        enterprisesPayment.setNonce_str(sortedMap.get("nonce_str"));//随机字符串 
        enterprisesPayment.setSign(sginCode);//签名 
        enterprisesPayment.setPartner_trade_no(sortedMap.get("partner_trade_no"));//商户订单号 
        enterprisesPayment.setOpenid(openid);     
        enterprisesPayment.setCheck_name("NO_CHECK"); 
        enterprisesPayment.setRe_user_name(null); //根据checkName字段判断是否需要 
        enterprisesPayment.setAmount(amount);//金额 
        enterprisesPayment.setDesc("desc");//描述 
        enterprisesPayment.setSpbill_create_ip("ip");//ip地址 
        return enterprisesPayment; 
    }
 
       public String getSgin(String openid, Integer amount) { 
        sortedMap.put("mch_appid","appid"); 
        sortedMap.put("mchid","商户号"); 
        String currTime = PayCommonUtil.getCurrTime();

        String strTime = currTime.substring(8, currTime.length());
        String strRandom = PayCommonUtil.buildRandom(4) + "";
        String nonce_str = strTime + strRandom;
        sortedMap.put("nonce_str", nonce_str); 
        sortedMap.put("partner_trade_no",getOrderDateNo()); 
        sortedMap.put("openid", openid); 
        sortedMap.put("check_name", "NO_CHECK"); 
        sortedMap.put("amount", amount.toString()); 
        sortedMap.put("desc", "desc"); 
        sortedMap.put("spbill_create_ip", "ip"); 
        sortedMap.put("re_user_name", "null"); 
        return WXPayUtil.getSignCode(sortedMap,"api秘钥"); 
    }
}*/
