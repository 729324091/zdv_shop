/*
package com.zdv.shop.weixinh5.controller;

import com.zdv.shop.controller.BaseController;
import com.zdv.shop.weixinh5.sdk.WXPay;
import com.zdv.shop.weixinh5.sdk.WXPayConfig;
import com.zdv.shop.weixinh5.sdk.WXPayConstants;
import com.zdv.shop.weixinh5.sdk.WXPayUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class WeixinCashOutController extends BaseController {
    public static Logger logger = LoggerFactory.getLogger(BaseController.class);

    */
/**
     * [微信支付提现接口] - 保存调用的相关记录
     * @param payment 支付对象
     * @param wxPayConfig 微信支付单例对象
     * @return map
     *
     * @author yclimb
     * @date 2018/7/30
     *//*

    public Map<String, String> saveWxPayTransfers(Payment payment, WXPayConfig wxPayConfig) throws Exception {
        // 支付前验证

        // 微信支付对象
        // WXPay wxPay = new WXPay(WXPayConfigImpl.getInstance());
        WXPay wxPay = new WXPay(wxPayConfig);

        // 微信退款接口
        Map<String, String> resultMap = wxPay.transfers(...);
        logger.info("saveWxPayTransfers:resultMap:" + resultMap.toString());

        // 下单失败，进行处理
        if (WXPayConstants.FAIL.equals(resultMap.get(WXPayConstants.RETURN_CODE)) || WXPayConstants.FAIL.equals(resultMap.get(WXPayConstants.RESULT_CODE))) {

            // 处理结果返回，无需继续执行

            // 余额不足提醒
            if (WXPayCodeEnum.ERR_CODE_NOTENOUGH.getCode().equals(resultMap.get(WXPayConstants.ERR_CODE))) {
                // 发送余额不足的消息提醒

            }
        }

        // 付款记录修改 & 记录付款日志

        return resultMap;
    }



    */
/**
     * 作用：企业向微信用户个人付款<br>
     * 场景：企业付款为企业提供付款至用户零钱的能力，支持通过API接口付款，或通过微信支付商户平台（pay.weixin.qq.com）网页操作付款。
     * 接口文档地址：https://pay.weixin.qq.com/wiki/doc/api/tools/mch_pay.php?chapter=14_2
     *
     * @param partner_trade_no 商户订单号
     * @param openid           用户openid
     * @param amount           企业付款金额
     * @param desc             企业付款描述信息
     * @param spbill_create_ip 该IP可传用户端或者服务端的IP
     * @return API返回数据
     * @throws Exception e
     *//*

    public Map<String, String> transfers(String partner_trade_no, String openid, String amount, String desc, String spbill_create_ip) throws Exception {

        */
/** 构造请求参数数据 **//*

        Map<String, String> data = new HashMap<>();

        // 商户订单号    partner_trade_no    是   10000098201411111234567890  String  商户订单号，需保持唯一性(只能是字母或者数字，不能包含有符号)
        data.put("partner_trade_no", partner_trade_no);
        // 用户openid openid  是   oxTWIuGaIt6gTKsQRLau2M0yL16E    String  商户appid下，某用户的openid
        data.put("openid", openid);
        // 校验用户姓名选项 check_name  是   FORCE_CHECK String  NO_CHECK：不校验真实姓名,FORCE_CHECK：强校验真实姓名
        data.put("check_name", "NO_CHECK");
        // 金额   amount  是   10099   int 企业付款金额，单位为分
        data.put("amount", String.valueOf(new BigDecimal(amount).multiply(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_HALF_UP).intValue()));
        // 企业付款描述信息 desc    是   理赔  String  企业付款操作说明信息。必填。
        data.put("desc", desc);
        // Ip地址 spbill_create_ip    是   192.168.0.1 String(32)  该IP同在商户平台设置的IP白名单中的IP没有关联，该IP可传用户端或者服务端的IP。
        data.put("spbill_create_ip", spbill_create_ip);

        */
/** 以下参数为非必填参数 **//*


    */
/*//*
/ 设备号    device_info 否   013467007045764 String(32)  微信支付分配的终端设备号
    data.put("device_info", "xxx");
    // 收款用户姓名   re_user_name    可选  王小王 String  收款用户真实姓名。(如果check_name设置为FORCE_CHECK，则必填用户真实姓名)
    data.put("re_user_name", "xxx");*//*


        // 微信调用接口
        Map<String, String> resultMap = this.transfers(data);

        WXPayUtil.getLogger().info("wxPay.transfers:" + resultMap);

        return resultMap;
    }

}
*/
