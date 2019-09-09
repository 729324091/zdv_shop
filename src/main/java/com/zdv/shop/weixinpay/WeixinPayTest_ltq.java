package com.zdv.shop.weixinpay;

import com.zdv.shop.weixinpay.Utils.*;
import com.zdv.shop.weixinpay.entity.Unifiedorder;
import com.zdv.shop.weixinpay.entity.UnifiedorderResult;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;



/**
 * 微信支付测试
 * @author xiebin
 * @date 2015年11月26日上午9:58:19
 */
public class WeixinPayTest_ltq {

	@SuppressWarnings("static-access")
	public static void main(String[] args) {
		/*try {
			wxRefund();
			if(true){
				return;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		WeixinConfigUtils config = new WeixinConfigUtils();
		Double trademoney =1.99;
		Double d = 1.23;
		d=d*100;
		System.out.println(d.intValue());
		String product_id=new SimpleDateFormat("yyyyMMddHHmmssSSSS").format(new Date());
		//参数组
		String appid = config.appid;
		//System.out.println("201708010015101095984152175651".length()+"appid是："+appid);
		String mch_id = config.mch_id;
		String openid = "okmeTwoGz0o7M0kH1INs4yvWysAA";
		//System.out.println("mch_id是："+mch_id);
		String nonce_str = RandCharsUtils.getRandomString(16);
		//System.out.println("随机字符串是："+nonce_str);
		String body = "jock测试微信支付0.01_2";
		String detail = "jock0.01_元测试开始";
		String attach = "备用参数，先留着，后面会有用的";
		String out_trade_no =  new SimpleDateFormat("yyyyMMddHHmmssSSSS").format(new Date());
		int total_fee = 1;//单位是分，即是0.01元
		String spbill_create_ip = "127.0.0.1";
		String time_start = RandCharsUtils.timeStart();
		System.out.println(time_start);
		String time_expire = RandCharsUtils.timeExpire();
		System.out.println(time_expire);
		String notify_url = config.notify_url;
		System.out.println("notify_url是："+notify_url);
		String trade_type = "JSAPI";
		long time1=Long.parseLong(String.valueOf(System.currentTimeMillis()).toString().substring(0,10));
		
		//参数：开始生成签名
		SortedMap<Object,Object> parameters = new TreeMap<Object,Object>();
		parameters.put("appid", appid);
		parameters.put("mch_id", mch_id);
		//parameters.put("sub_mch_id", "1457893602");
		parameters.put("nonce_str", nonce_str);
		parameters.put("body", body);
		parameters.put("detail", detail);
		parameters.put("attach", attach);
		parameters.put("out_trade_no", out_trade_no);
		parameters.put("total_fee", total_fee);
		parameters.put("time_start", time_start);
		parameters.put("time_expire", time_expire);
		parameters.put("notify_url", notify_url);
		parameters.put("trade_type", trade_type);
		parameters.put("spbill_create_ip", spbill_create_ip);

	
		
		String sign = WXSignUtils.createSign("UTF-8", parameters);
		System.out.println("签名是："+sign);
		

		Unifiedorder unifiedorder = new Unifiedorder();
		unifiedorder.setAppid(appid);
		unifiedorder.setMch_id(mch_id);
		unifiedorder.setSub_mch_id("1457893602");
		unifiedorder.setOpenid(openid);
		unifiedorder.setProduct_id(product_id);
		unifiedorder.setNonce_str(nonce_str);
		unifiedorder.setSign(sign);
		unifiedorder.setBody(body);
		unifiedorder.setDetail(detail);
		unifiedorder.setAttach(attach);
		unifiedorder.setOut_trade_no(out_trade_no);
		unifiedorder.setTotal_fee(total_fee);
		unifiedorder.setSpbill_create_ip(spbill_create_ip);
		unifiedorder.setTime_start(time_start);
		unifiedorder.setTime_expire(time_expire);
		unifiedorder.setNotify_url(notify_url);
		unifiedorder.setTrade_type(trade_type);

		
		//构造xml参数
		String xmlInfo = HttpXmlUtils.xmlInfo(unifiedorder);
		System.out.println("xmlInfo:"+xmlInfo);
		String wxUrl = "https://api.mch.weixin.qq.com/pay/unifiedorder";
		
		String method = "POST";
		
		String weixinPost = HttpXmlUtils.httpsRequest(wxUrl, method, xmlInfo).toString();
		
		//System.out.println(weixinPost);
		
		ParseXMLUtils.jdom2ParseXml(weixinPost);
		

		
		UnifiedorderResult unifieorderResult = new UnifiedorderResult();
		unifieorderResult =JdomParseXmlUtils.getUnifiedorderResult(weixinPost);

		
		
		SortedMap<Object,Object> p = new TreeMap<Object,Object>();
		p.put("appid", appid);
		p.put("noncestr", nonce_str);
		p.put("partnerid", mch_id);
		p.put("package", "Sign=WXPay");
		p.put("prepayid", unifieorderResult.getPrepay_id());
		p.put("timestamp", time1);
		p.put("openid", openid);
		//
		/*p.put("appid", "wx4363b96513fd59de");
		p.put("noncestr", "EJRE42t6fIW9xjjtYgTM2aNSqRGAU07p");
		p.put("package", "Sign=WXPay");
		p.put("partnerid", "1385478902");
		p.put("prepayid", "wx20161031154956efc40e93080329468306");
		p.put("timestamp", "1477900190");*/
		/*String sign1 = WXSignUtils.createSign("UTF-8", p);
System.out.println("sign1:"+sign1);*///249D2C737C052A53BF29D6900FF1904C   30055A72018B5C59B2DEC8CDD3495FD7
	}
	  /* 处理退款请求
	    * @param request
	    * @return
	    * @throws Exception
	    */
    /**
     * 请求退款服务
     * @param transaction_id 是微信系统为每一笔支付交易分配的订单号，通过这个订单号可以标识这笔交易，它由支付订单API支付成功时返回的数据里面获取到。建议优先使用
     * @param out_trade_no 商户系统内部的订单号,transaction_id 、out_trade_no 二选一，如果同时存在优先级：transaction_id>out_trade_no
     * @param out_refund_no 商户系统内部的退款单号，商户系统内部唯一，同一退款单号多次请求只退一笔
     * @param totalFee 订单总金额，单位为分
     * @param refundFee 退款总金额，单位为分
     */
	   public static String wxRefund() throws Exception {
		   WeixinConfigUtils config = new WeixinConfigUtils();
	       //获得当前目录
	       String path = PathTool.getWebRootPath();
	       //LogUtils.trace(path);

	       Date now = new Date();
	       SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");//可以方便地修改日期格式
	       String out_refund_no = "NO" + dateFormat.format( now );
	       //获得退款的传入参数
	       String transaction_id = "4008202001201609012711655620";
	       String out_trade_no = "20161201141024";
	       Integer total_fee = 1;
	       Integer refund_fee = total_fee;
	       String op_user_id = "u" + dateFormat.format( now );

	       String nonce_str = RandCharsUtils.getRandomString(32);
	       
	       
	        //根据API给的签名规则进行签名
	        SortedMap<Object,Object> parameters = new TreeMap<Object,Object>();
	        parameters.put("appid", config.appid);
	        parameters.put("mch_id", config.mch_id);
	        parameters.put("nonce_str", nonce_str);
	        parameters.put("transaction_id", transaction_id);
	        parameters.put("out_trade_no", out_trade_no);
	        parameters.put("out_refund_no", out_refund_no);
	        parameters.put("total_fee", total_fee);
	        parameters.put("refund_fee", refund_fee);
	        parameters.put("op_user_id", op_user_id);
	        
	 
	        String sign = WXSignUtils.createSign("UTF-8", parameters);
	       String info="<xml>"
	    		   +"<appid>"+config.appid+"</appid>"
	    		   + "<mch_id>"+config.mch_id+"</mch_id>"
	    		   + "<nonce_str>"+nonce_str+"</nonce_str>"
	    		   + "<op_user_id>"+op_user_id+"</op_user_id>"
	    		   + "<out_trade_no>"+out_trade_no+"</out_trade_no>"
	    		   + "<out_refund_no>"+out_refund_no+"</out_refund_no>"
	    		   + "<refund_fee>"+refund_fee+"</refund_fee>"
	    		   + "<total_fee>"+total_fee+"</total_fee>"
	    		   + "<sign>"+sign+"</sign>"
	    		   +"</xml>";

	       try {
	           RefundRequest refundRequest = new RefundRequest();
	           String result = refundRequest.httpsRequest(config.REFUND_API, info, path);
	           Map<String, String> getMap = ParseXMLUtils.doXMLParse(result);//MobiMessage.parseXml(new String(result.toString().getBytes(), "utf-8"));
	           System.out.println(getMap.get("return_code"));
	           System.out.println(getMap.get("return_msg"));
	           if("SUCCESS".equals(getMap.get("return_code")) && "SUCCESS".equals(getMap.get("return_msg"))){
	        	   return null;
	           }else{
	               //返回错误描述
	               return null;
	           }
	       }catch(Exception e){
	           e.printStackTrace();
	           return null;
	       }
	   }
	   
}
