package com.zdv.shop.weixinh5.controller;


import com.alibaba.fastjson.JSONObject;
import com.zdv.shop.common.Constant;
import com.zdv.shop.common.utils.HttpRequest;
import com.zdv.shop.common.utils.StringUtils;
import com.zdv.shop.controller.BaseController;
import com.zdv.shop.model.CtWxpayConfig;
import com.zdv.shop.model.UtOrder;
import com.zdv.shop.model.UtOrderItem;
import com.zdv.shop.service.*;
import com.zdv.shop.weixinh5.config.PayConfig;
import com.zdv.shop.weixinh5.model.PayException;
import com.zdv.shop.weixinh5.model.RefundVo;
import com.zdv.shop.weixinh5.util.HttpUtil;
import com.zdv.shop.weixinh5.util.PayCommonUtil;
import com.zdv.shop.weixinh5.util.XMLUtil;
import com.zdv.shop.weixinpay.Utils.RandCharsUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.xml.sax.SAXException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.*;

/**
 * 微信内支付
 */
@RequestMapping("/h5/weixin/")
@Controller
public class WeixinPayController extends BaseController {

    @Autowired
    private UtOrderService orderService;
    @Autowired
    private CtWxpayConfigService wxpayConfigService;
    @Autowired
    private UtOrderItemService orderItemService;
    @Autowired
    private UtFinancelogService financelogService;
    @Autowired
    private UtUserService userService;
	@Value("${publicURL}") //在配置文件中
	private String publicurl;
    @Value("${ucustomerid}") //商户号
    private String ucustomerid;

    @Value("${API.docBase}")
    private String docBase;

    @Value("${ucompid}")
    private String ucompid;

    /**
     * 订单编号,交易类型,返回链接
     * @param uorderid
     * @param radetype
     * @param retUrl
     * @return
     * @throws UnsupportedEncodingException
     */
    @RequestMapping("getCode")
    public String getCode(String uorderid,String radetype,String retUrl) throws UnsupportedEncodingException {
        String codeUrl = "https://open.weixin.qq.com/connect/oauth2/authorize";
        String state = uorderid;

        //如果订单号不为空
        if (StringUtils.StringisNotEmpty(uorderid)) {
            state = uorderid +"-"+ radetype;
        }else{
            state = "-" + radetype;
        }
        HttpServletRequest request = getRequest();
        StringBuffer sb = request.getRequestURL();
        String shopurl = sb.delete(sb.length() - request.getRequestURI().length(), sb.length()).toString();

        String uri =URLEncoder.encode(shopurl+"/h5/weixin/repay", "GBK");
        CtWxpayConfig ctWxpayConfig = new CtWxpayConfig();
        ctWxpayConfig.setUcustomerid(ucustomerid);
        //0微信 1支付宝
        ctWxpayConfig.setUtypes("0");
        List<CtWxpayConfig> configs = wxpayConfigService.queryList(ctWxpayConfig);
        String appid = "";
        if (configs.size() > 0) {
            CtWxpayConfig config = configs.get(0);
            //获取appid
            appid = config.getAppid();
        }
        String url = codeUrl + "?appid=" + appid + "&redirect_uri=" + uri + "&response_type=code&scope=snsapi_base&state=" + state + "&radetype="+radetype+"#wechat_redirect";
//
        return  "redirect:"+url+"";
    }


    @RequestMapping("repay")
    public String repay(String code, String state,String radetype, Model model) {
        model.addAttribute("code", code);

        model.addAttribute("state", state);



        return "templates/h5/pay";
    }

    @RequestMapping("getOpenid")
    @ResponseBody
    public Map  getOpenid(String code, String state,HttpServletRequest request, HttpServletResponse response, ModelMap model) throws SAXException, IOException {
        CtWxpayConfig ctWxpayConfig = new CtWxpayConfig();
        ctWxpayConfig.setUcustomerid(ucustomerid);
        //0微信 1支付宝
        ctWxpayConfig.setUtypes("0");
        List<CtWxpayConfig> configs = wxpayConfigService.queryList(ctWxpayConfig);
        String appid = "";
        String appsecret = "";
        String mch_id = "";
        String key = "";
        if (configs.size() > 0) {
            CtWxpayConfig config = configs.get(0);
            //获取appid
            appid = config.getAppid();
            appsecret = config.getAppsecret();
            mch_id = config.getMchid();
            key = config.getApikey();
        }



        String openUrl = "https://api.weixin.qq.com/sns/oauth2/access_token";
        String param = "appid=" + appid + "&secret=" + appsecret + "&code=" + code + "&grant_type=authorization_code";
        String s = HttpRequest.sendGet(openUrl, param);
        JSONObject json = JSONObject.parseObject(s);
        String openid = json.getString("openid");
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("success", false);
        try {
            UtOrder order = new UtOrder();
            String[] split = state.split("-");
            //获取订单号
            order.setUorderid(split[0]);

            UtOrder utOrder = orderService.queryOne(order);

            // 付款金额，必填
//			String total_fee = request.getParameter("WIDtotal_fee");
            //付款金额
            String total_fee = utOrder.getUpayprice()+"";
            //测试
//            total_fee = "0.01";

            // ip地址获取
            String basePath = request.getServerName() + ":" + request.getServerPort();
            // 账号信息
        /*    String appid = PayConfig.APP_ID; // appid
            String mch_id = PayConfig.MCH_ID; // 商业号
            String key = PayConfig.API_KEY; // key*/

            String currTime = PayCommonUtil.getCurrTime();
            String strTime = currTime.substring(8, currTime.length());
            String strRandom = PayCommonUtil.buildRandom(4) + "";
            String nonce_str = strTime + strRandom;
            // 价格 注意：价格的单位是分
            String order_price = new BigDecimal(total_fee).multiply(new BigDecimal(100)).toString().split("\\.")[0];
            // 自己网站上的订单号
            String out_trade_no = split[0]; /////111111111111111111
			/*RandomGUID id = new RandomGUID();
			String out_trade_no = id.toString().substring(0, 32);*/
            // 获取发起电脑 ip
            String spbill_create_ip = HttpUtil.getRealIp(request);
            StringBuffer sb = request.getRequestURL();
            String shopurl = sb.delete(sb.length() - request.getRequestURI().length(), sb.length()).toString();
            // 回调接口
            String notify_url = shopurl+ PayConfig.NOTIFY_URL_H5 /*.replaceAll("localhostUrl", basePath)+ getCurrentUser().getId()*/;
            // 页面跳转同步通知页面路径
            String trade_type = "JSAPI";

            // 设置package订单参数
            SortedMap<Object, Object> packageParams = new TreeMap<Object, Object>();
            packageParams.put("appid", appid);
            packageParams.put("mch_id", mch_id);
            // 生成签名的时候需要你自己设置随机字符串
            packageParams.put("nonce_str", nonce_str);
            if (split.length > 2) {

            //携带参数
            packageParams.put("attach", split[1]);
            }

            packageParams.put("out_trade_no", out_trade_no);
            packageParams.put("total_fee", order_price);
            packageParams.put("spbill_create_ip", spbill_create_ip);
            packageParams.put("notify_url", notify_url);
            packageParams.put("trade_type", trade_type);
            List<UtOrderItem> itemList = orderItemService.listOrderitemByUorderid(order.getUorderid());
            String body = "";
            for (UtOrderItem orderItem : itemList) {
                body +=orderItem.getUproductname()+"|";
            }
//            packageParams.put("body", PayConfig.BODY);
            packageParams.put("body", body);
            packageParams.put("openid", openid);
            packageParams.put("scene_info", "{\"h5_info\": {\"type\":\"Wap\",\"wap_url\": \""+shopurl+"h5/weindex\",\"wap_name\": \""+Constant.ucustomername+"\"}}");
            String sign = PayCommonUtil.createSign("UTF-8", packageParams, key);
            packageParams.put("sign", sign);
            String requestXML = PayCommonUtil.getRequestXml(packageParams);
            String resXml = HttpUtil.postData(PayConfig.UFDODER_URL, requestXML);
            System.out.println(resXml);
            String prepay_id = "";//预支付id
            //以下内容是返回前端页面的json数据
            if (resXml.indexOf("SUCCESS") != -1) {

                Map<String, String> map = XMLUtil.doXMLParse(resXml);

                prepay_id = (String) map.get("prepay_id");

            }

            SortedMap<Object, Object> payMap = new TreeMap<Object, Object>();

//            SortedMap<String, Object> payMap = new Map<String, Object>();

            payMap.put("appId", appid);

            payMap.put("timeStamp", PayCommonUtil.getCurrTime()+"");

            payMap.put("nonceStr", RandCharsUtils.getRandomString(32));

            payMap.put("signType", "MD5");

            payMap.put("package", "prepay_id=" + prepay_id);
            String paySign = PayCommonUtil.createSign("UTF-8",payMap, key);

            payMap.put("paySign", paySign);

            return payMap;

        } catch (Exception e) {

            e.printStackTrace();

        }

        return null;


    }




    private void check(RefundVo vo) throws PayException {

        if (StringUtils.objectIsNull(vo.getAppid())) {
            throw new PayException("申请退款参数为空——appid");
        }
        if (StringUtils.objectIsNull(vo.getMchId())) {
            throw new PayException("申请退款参数为空——mch_id");
        }
        if (StringUtils.objectIsNull(vo.getNonceStr())) {
            throw new PayException("申请退款参数为空——nonce_str");
        }
        if (StringUtils.objectIsNull(vo.getSign())) {
            throw new PayException("申请退款参数为空——sign");
        }
        if (StringUtils.objectIsNull(vo.getTransactionId()) && StringUtils.objectIsNull(vo.getOutTradeNo())) {
            throw new PayException("申请退款参数为空——transaction_id或者out_trade_no");
        }
        if (StringUtils.objectIsNull(vo.getOutRefundNo())) {
            throw new PayException("申请退款参数为空——out_refund_no");
        }
        if (StringUtils.objectIsNull(vo.getTotalFee())) {
            throw new PayException("申请退款参数为空——total_fee");
        }
        if (StringUtils.objectIsNull(vo.getRefundFee())) {
            throw new PayException("申请退款参数为空——refund_fee");
        }
        if (StringUtils.objectIsNull(vo.getOpUserId())) {
            throw new PayException("申请退款参数为空——op_user_id");
        }
    }





}
