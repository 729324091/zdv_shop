package com.zdv.shop.weixinh5.controller;

import com.zdv.shop.common.Constant;
import com.zdv.shop.common.utils.KeyId;
import com.zdv.shop.common.utils.StringUtils;
import com.zdv.shop.controller.BaseController;
import com.zdv.shop.model.CtWxpayConfig;
import com.zdv.shop.model.UtFinancelog;
import com.zdv.shop.model.UtOrder;
import com.zdv.shop.service.CtWxpayConfigService;
import com.zdv.shop.service.LogService;
import com.zdv.shop.service.UtFinancelogService;
import com.zdv.shop.service.UtOrderService;
import com.zdv.shop.weixinh5.config.PayConfig;
import com.zdv.shop.weixinh5.sdk.WXPayUtil;
import com.zdv.shop.weixinh5.util.HttpUtil;
import com.zdv.shop.weixinh5.util.PayCommonUtil;
import com.zdv.shop.weixinh5.util.XMLUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.*;
 
/**
 * 微信外支付
 * @author znj
 * @descrition 手机网站支付接口接入页controller 2016年7月4日 下午10:40:12
 */
@Controller
@RequestMapping("/h5/pay/")
public class AppAliPayController extends BaseController {
	@Autowired
	private CtWxpayConfigService wxpayConfigService;
	@Autowired
	private UtOrderService orderService;

	@Autowired
	private UtFinancelogService financelogService;

	@Autowired
    private LogService logService;

	@Value("${publicURL}") //在配置文件中
	private String publicurl;
	@Value("${ucustomerid}") //商户号
	private String ucustomerid;

	// 充值标识
	private Boolean czflg;
	// 充值类型：1 充值 2 订单
	private Integer czstyle = 0;
	private String utradetype = "3";
	/**
	 * 微信H5支付
	 *
	 * @param request
	 * @param response
	 * @param model
	 * @throws Exception
	 */
	@RequestMapping("/wxPayH5")
	public void wxPayH5(String uorderid,String radetype,/*@SessionAttribute(Constant.SESSION_H5USER)UtUsers utUsers,*/ HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
		if (StringUtils.StringisNotEmpty(radetype)) {
			utradetype = radetype;

		}

		Map<String, Object> result = new HashMap<String, Object>();
		result.put("success", false);
		try {
			UtOrder order = new UtOrder();
			order.setUorderid(uorderid);

			UtOrder utOrder = orderService.queryOne(order);

			// 付款金额，必填
//			String total_fee = request.getParameter("WIDtotal_fee");
			//付款金额
			String total_fee = utOrder.getUpayprice()+"";
			//测试
//			total_fee = "0.01";
			// 充值类型：1 积分 2 现金
			czstyle = 1;
			// ip地址获取
			String basePath = request.getServerName() + ":" + request.getServerPort();


			CtWxpayConfig ctWxpayConfig = new CtWxpayConfig();
			ctWxpayConfig.setUcustomerid(ucustomerid);
			//0微信 1支付宝
			ctWxpayConfig.setUtypes("0");
			List<CtWxpayConfig> configs = wxpayConfigService.queryList(ctWxpayConfig);

			// 账号信息
			String appid = PayConfig.APP_ID; // appid
			String mch_id = PayConfig.MCH_ID; // 商业号
			String key = PayConfig.API_KEY; // key

			if (configs.size() > 0) {
				CtWxpayConfig config = configs.get(0);
				appid = config.getAppid();
				mch_id = config.getMchid();
				key = config.getApikey();
			}


			String currTime = PayCommonUtil.getCurrTime();
			String strTime = currTime.substring(8, currTime.length());
			//随机字符串
			String strRandom = PayCommonUtil.buildRandom(4) + "";
			String nonce_str = strTime + strRandom;
			// 价格 注意：价格的单位是分
			String order_price = new BigDecimal(total_fee).multiply(new BigDecimal(100)).toString().split("\\.")[0];
			// 自己网站上的订单号
			String out_trade_no = uorderid; /////111111111111111111
			/*RandomGUID id = new RandomGUID();
			String out_trade_no = id.toString().substring(0, 32);*/
			// 获取发起电脑 ip
			String spbill_create_ip = HttpUtil.getRealIp(request);

			//获得链接
			StringBuffer sb = request.getRequestURL();
			String shopurl = sb.delete(sb.length() - request.getRequestURI().length(), sb.length()).toString();

			// 回调接口
//			String notify_url = PayConfig.NOTIFY_URL_H5 /*.replaceAll("localhostUrl", basePath)+ getCurrentUser().getId()*/;
			String notify_url = shopurl + "/h5/pay/payNotify";
			// 页面跳转同步通知页面路径
			String trade_type = "MWEB";
 
			// 设置package订单参数
			SortedMap<Object, Object> packageParams = new TreeMap<Object, Object>();
			packageParams.put("appid", appid);
			packageParams.put("mch_id", mch_id);
			// 生成签名的时候需要你自己设置随机字符串
			packageParams.put("nonce_str", nonce_str);
			//附加参数
			packageParams.put("attach", utradetype);
			packageParams.put("out_trade_no", out_trade_no);
			packageParams.put("total_fee", order_price);
			packageParams.put("spbill_create_ip", spbill_create_ip);
			packageParams.put("notify_url", notify_url);
			packageParams.put("trade_type", trade_type);
			packageParams.put("body", PayConfig.BODY);
			packageParams.put("scene_info", "{\"h5_info\": {\"type\":\"Wap\",\"wap_url\": \""+shopurl+"h5/weindex\",\"wap_name\": \""+Constant.ucustomername+"\"}}");
			String sign = PayCommonUtil.createSign("UTF-8", packageParams, key);
//			WXPayUtil.generateSignature(packageParams, key);
			packageParams.put("sign", sign);

			String requestXML = PayCommonUtil.getRequestXml(packageParams);
			String resXml = HttpUtil.postData(PayConfig.UFDODER_URL, requestXML);
			System.out.println(resXml);

			Map map = XMLUtil.doXMLParse(resXml);
			String urlCode = (String) map.get("code_url");
			//确认支付过后跳的地址,需要经过urlencode处理
			String urlString = URLEncoder.encode(shopurl+"h5/order/weuiOrderList", "GBK");
			String mweb_url = (String) map.get("mweb_url")+"&redirect_url="+urlString;
			czflg = true;
			response.sendRedirect(mweb_url);
//			LogUtil.writeMsgToFile("before ----" + czflg);
			result.put("sHtmlText", urlCode);
			result.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
//			LogUtil.writeMsgToFile(e);
			result.put("errormsg", e.getMessage());
		}
	}
	
	/**
	 * 执行回调 确认支付后处理事件 例如添加金额到数据库等操作
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/payNotify")
	public void weixin_notify(HttpServletRequest request, HttpServletResponse response, ModelMap model)throws Exception {
		System.out.println("进入支付h5回调=====================");
		String xmlMsg = readData(request);
		System.out.println("pay notice---------"+xmlMsg);


		Map<String, String> params = WXPayUtil.xmlToMap(xmlMsg);
//		Map<String, String> params = new HashMap<String, String>();

//		Map params = XMLUtil.doXMLParse(xmlMsg);

//		String appid  = params.get("appid");
//		//商户号
//		String mch_id  = params.get("mch_id");
//		String result_code  = params.get("result_code")+"";
//		String openId      = params.get("openid");
//		//交易类型
//		String trade_type      = params.get("trade_type");
//		//付款银行
//		String bank_type      = params.get("bank_type");
//		// 总金额
//		String total_fee     = (String) params.get("total_fee");
//		//现金支付金额
		String cash_fee     = (String) params.get("cash_fee");
//		cash_fee = "11.22";
//		// 微信支付订单号
		String transaction_id      = (String) params.get("transaction_id");
//		transaction_id = "123456789";
//		// 商户订单号
		String out_trade_no      = (String) params.get("out_trade_no");
//		out_trade_no = "71121133134688256";
//		// 支付完成时间，格式为yyyyMMddHHmmss
		String time_end      = (String) params.get("time_end");
//		time_end = "199602070000";
		/////////////////////////////以下是附加参数///////////////////////////////////
		//交易类型
		String attach      = params.get("attach")+"";
		attach = "3";
//		String fee_type      = params.get("fee_type");
//		String is_subscribe      = params.get("is_subscribe");
//		String err_code      = params.get("err_code");
//		String err_code_des      = params.get("err_code_des");
		String userid = null;

		try {
 
			// 过滤空 设置 TreeMap
			SortedMap<Object, Object> packageParams = new TreeMap<Object, Object>();
			Iterator it = params.keySet().iterator();
			while (it.hasNext()) {
				String parameter = (String) it.next();
				String parameterValue = params.get(parameter)+"";
				String v = "";
				if (null != parameterValue) {
					v = parameterValue.trim();
				}
				System.out.println("value==============="+v);
				packageParams.put(parameter, v);
			}
			// 查看回调参数
//			 LogUtil.writeMsgToFile(packageParams.toString());
			System.out.println(packageParams.toString());
			String total_fee = new BigDecimal((String) packageParams.get("total_fee")).divide(new BigDecimal(100)).toString();
			userid = (String) packageParams.get("userid");
			// 账号信息
			String resXml = "";
			// ------------------------------
			// 处理业务开始
			// ------------------------------
			if ("SUCCESS".equals((String) packageParams.get("result_code"))) {
				// 这里是支付成功
				czflg = true;
				System.out.println("支付成功============"+czflg+"czstyle========="+czstyle);
				////////// 执行自己的业务逻辑////////////////
				model.put("sHtmlText", "充值成功");
				try {
					synchronized (czflg) {
						/*if (czflg) {
							// 充值积分
							if (czstyle.equals(1)) {
								userIncomeFlowService.saveMoneyFlowChongzhi(total_fee, userid, false);
							} else if (czstyle.equals(2)) {
								// 充值 现金
								userIncomeFlowService.saveMoneyFlowChongzhi(total_fee, userid);
							}
							czflg = false;
							LogUtil.writeMsgToFile("end ----" + czflg);
						}
						LogUtil.writeMsgToFile("enderror ----" + czflg);*/


					}
				} catch (Exception e) {
//					LogUtil.writeMsgToFile(e);

					model.put("Exception", e);
					e.printStackTrace();
				}
				////////// 执行自己的业务逻辑////////////////
				try {
					UtOrder order = orderService.queryByID(out_trade_no);
					//支付后创建财务日志
					UtFinancelog utFinancelog = new UtFinancelog();
					//店铺id
					utFinancelog.setUcompid(order.getUcompid());
					//用户id
					utFinancelog.setUuserid(order.getUuserid());
					//订单id
					utFinancelog.setUinfoid(out_trade_no);
					//状态   正在处理.若完成交易(用户评论之后)则状态变为1
					utFinancelog.setUstatus("0");
					//
					utFinancelog.setUtradetype(attach);
					//支付金额
					utFinancelog.setUtrademoney("-"+total_fee);
					//流水账号
					utFinancelog.setUglidenumber(getGlidenumber());
					//是否线上支付  0线上 1 线下
					utFinancelog.setIfline("0");
					//现在只有微信
					utFinancelog.setUpaymode("微信");
					//第三方订单号
					utFinancelog.setTransactionId(transaction_id);
					//时间
					utFinancelog.setUcreatedate(time_end);

				/*	//创建企业财务日志
					utFinancelog.setUtype("1");
					utFinancelog.setUfinancelogid(KeyId.nextId());
					//进出类型
					utFinancelog.setAccttype("0");

					financelogService.saveFinaceLog(utFinancelog);
*/
					//创建用户财务日志
					utFinancelog.setUtype("0");
					utFinancelog.setUfinancelogid(KeyId.nextId());
					utFinancelog.setAccttype("1");
//					financelogService.saveFinaceLog(utFinancelog);

					UtOrder utOrder = new UtOrder();
					utOrder.setUorderid(out_trade_no);
					utOrder.setUpaytype("1");
					utOrder.setUeflag("01");
					utOrder.setUpaystate("1");
					orderService.overPay(utOrder, utFinancelog);
//					orderService.updateOrder(utOrder);
				// 通知微信.异步确认成功.必写.不然会一直通知后台.八次之后就认为交易失败了.
					resXml = "<xml>" + "<return_code><![CDATA[SUCCESS]]></return_code>" + "<return_msg><![CDATA[OK]]></return_msg>" + "</xml> ";
				} catch (Exception e) {
					e.printStackTrace();

				}

			} else {
				model.put("sHtmlText", "付款失败");
				resXml = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>" + "<return_msg><![CDATA[付款失败]]></return_msg>" + "</xml> ";
			}
			BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());
			out.write(resXml.getBytes());
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
//			LogUtil.writeMsgToFile(e);
		}
 
	}

/*

	@RequestMapping("tessst")
	public void tessst() {
		UtOrder order = orderService.queryByID("71121133134688256");
		//支付后创建财务日志
		UtFinancelog utFinancelog = new UtFinancelog();
		//店铺id
		utFinancelog.setUcompid(order.getUcompid());
		//用户id
		utFinancelog.setUuserid(order.getUuserid());
		//订单id
		utFinancelog.setUinfoid("71121133134688256");
		//状态   正在处理.若完成交易(用户评论之后)则状态变为1
		utFinancelog.setUstatus("0");
		//
		utFinancelog.setUtradetype("3");
		//支付金额
		utFinancelog.setUtrademoney("0.05");
		//流水账号
		utFinancelog.setUglidenumber(getGlidenumber());
		//是否线上支付  0线上 1 线下
		utFinancelog.setIfline("0");
		//现在只有微信
		utFinancelog.setUpaymode("微信");
		//第三方订单号
		utFinancelog.setTransactionId("123456");
		//时间
//		utFinancelog.setUcreatedate(time_end);

		//创建企业财务日志
		utFinancelog.setUtype("1");
		utFinancelog.setUfinancelogid(KeyId.nextId());
		//进出类型
		utFinancelog.setAccttype("0");

//		financelogService.saveFinaceLog(utFinancelog);

		//创建用户财务日志
		utFinancelog.setUtype("0");
		utFinancelog.setUfinancelogid(KeyId.nextId());
		utFinancelog.setAccttype("1");
//					financelogService.saveFinaceLog(utFinancelog);

		UtOrder utOrder = new UtOrder();
		utOrder.setUorderid("71121133134688256");
		utOrder.setUpaytype("1");
		utOrder.setUeflag("01");
		orderService.overPay(utOrder, utFinancelog);
	}
*/




	
	public static String readData(HttpServletRequest request) {
		BufferedReader br = null;
		try {
			StringBuilder result = new StringBuilder();
			br = request.getReader();
			for (String line; (line=br.readLine())!=null;) {
				if (result.length() > 0) {
					result.append("\n");
				}
				result.append(line);
			}
 
			return result.toString();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		finally {
			if (br != null)
				try {br.close();} catch (IOException e) {e.printStackTrace();}
		}
	}





}