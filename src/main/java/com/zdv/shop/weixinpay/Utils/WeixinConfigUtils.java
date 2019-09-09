package com.zdv.shop.weixinpay.Utils;

import com.zdv.shop.common.Constant;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;

/**
 * 微信的配置参数
 * @author Jock
 * @date 2015年11月25日下午4:19:57
 */
@SuppressWarnings("unused")
public class WeixinConfigUtils {
	 @Value("${publicURL}") //在配置文件中
	private static String publicurl;
	private static final Log log = LogFactory.getLog(WeixinConfigUtils.class);

	//开发平台支持APP支付
	public static String appid="wxee341e3933e3d698"; //wx7d22f0ee17502f9a
	public static String mch_id="1418371002";//430822/"";1485981382  1457893602 微信支付商户号==登陆微信支付后台，即可看到
	private static String appsecret = "0a55190ae0af826cb19bfb83620ed991";
	private static String partnerkey = "WWWLeTiQiuCOM20160616ShuXiangJie";
	//服务号支持JSAPI支付
	public static String gappid="wx1274d3fe4548ef75";
	public static String gmch_id="1528603551";
	private static String gappsecret = "9e84d80e3e57fdd1415c025a2e9215fa";
	private static String gpartnerkey = "wwwdiqiucunmallcn20190314JockYun";
	public static String notify_url=publicurl+"/h5/pay/payNotify";
	public static String webnotify_url="https://minapps.letiqiu.com/payNotify";//OssViewGlobalConst.PublicURL+"/app/py/webNotifyUrl";
	public static String certLocalPath ="/WEB-INF/cert/apiclient_cert.p12";
	public static String REFUND_API="https://api.mch.weixin.qq.com/secapi/pay/refund";

}
