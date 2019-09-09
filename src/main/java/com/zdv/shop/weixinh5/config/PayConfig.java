package com.zdv.shop.weixinh5.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;

/**
 * 支付参数
 *
 * @author huangshouyi
 */
public class PayConfig {

	@Value("${publicURL}")
	private static String publicURL;

	// 微信号
	public static String APP_ID = "wx1274d3fe4548ef75";
	// 应用对应的凭证
	public static String APP_SECRET = "1c25cbcdbd6784298af0e78883e6c33a";
	// 商户密钥
	public static String API_KEY = "qwertyuiopasdfghjklzxcvbnmzxcvbn";
	// 商业号
	public static String MCH_ID = "1528603551";
	// 回调地址
	public static String NOTIFY_URL = "http://localhostUrl/resourceManager/xxxxxx?userid=";
	//微信支付h5 回调地址
	public static String NOTIFY_URL_H5 = "/h5/pay/payNotify";
	public static String NOTIFY_URL_REGISTVIP = "/h5/user/vipNotify";
	// 商品名称
	public static String BODY = "h5支付测试啦";
	// 请求地址
	public static String UFDODER_URL = "https://api.mch.weixin.qq.com/pay/unifiedorder";
	// 微信支付V2账单查询接口
	public static String ORDERQUERY = "https://api.mch.weixin.qq.com/pay/orderquery";

	//商城地址
	public static String SHOPURL =  "http://znj.free.idcfengye.com";

	//
	public static String REDIRECT_URI =   "/h5/weixin/repay";
	public static String REDIRECT_URI_VIP =   "/h5/user/repay";


	/*// 微信号
	public static String APP_ID = "wx1274d3fe4548ef75";
	// 应用对应的凭证
	public static String APP_SECRET = "1c25cbcdbd6784298af0e78883e6c33a";
	// 商户密钥
	public static String API_KEY = "wwwdiqiucunmallcn20190314JockYun";
	// 商业号
	public static String MCH_ID = "1528603551";
	// 回调地址
	public static String NOTIFY_URL = "http://localhostUrl/resourceManager/xxxxxx?userid=";
	//微信支付h5 回调地址
	public static String NOTIFY_URL_H5 = "http://shop.diqiucunmall.cn/h5/pay/payNotify";
	public static String NOTIFY_URL_REGISTVIP = "http://shop.diqiucunmall.cn/h5/user/vipNotify";
	// 商品名称
	public static String BODY = "h5支付";
	// 请求地址
	public static String UFDODER_URL = "https://api.mch.weixin.qq.com/pay/unifiedorder";
	// 微信支付V2账单查询接口
	public static String ORDERQUERY = "https://api.mch.weixin.qq.com/pay/orderquery";

	//
	public static String REDIRECT_URI = "http://shop.diqiucunmall.cn/h5/weixin/repay";
	public static String REDIRECT_URI_VIP = "http://shop.diqiucunmall.cn/h5/user/repay";*/
	public static String BIZ = "MzU2NDYzMTExNQ==";

	public static JSONObject doGetJson(String url) throws ClientProtocolException, IOException {
		JSONObject jsonObject = null;
		DefaultHttpClient client = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet(url);
		HttpResponse response = client.execute(httpGet);
		HttpEntity entity = response.getEntity();
		if (entity != null) {
			//把返回的结果转换为JSON对象
			String result = EntityUtils.toString(entity, "UTF-8");
			jsonObject = JSON.parseObject(result);
		}

		return jsonObject;
	}
}