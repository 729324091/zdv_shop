package com.zdv.shop.controller;

import com.alibaba.fastjson.JSONObject;
import com.zdv.shop.common.Constant;
import com.zdv.shop.common.SmsManage;
import com.zdv.shop.common.annotation.ControllerLog;
import com.zdv.shop.common.pojo.AjaxResult;
import com.zdv.shop.common.utils.*;
import com.zdv.shop.model.*;
import com.zdv.shop.service.*;
import com.zdv.shop.weixin.template.TemplateSendUtil;
import com.zdv.shop.weixinh5.config.PayConfig;
import com.zdv.shop.weixinh5.util.HttpUtil;
import com.zdv.shop.weixinh5.util.PayCommonUtil;
import com.zdv.shop.weixinh5.util.XMLUtil;
import com.zdv.shop.weixinpay.Utils.RandCharsUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.util.*;

/**
 * H5用户操作
 */
@Controller
@RequestMapping("/h5/user/")
public class H5UserController extends BaseController {

	@Autowired
	private UtFinancelogService financelogService;
	@Autowired
	private UtThirdloginService thirdloginService;
	@Autowired
	private CtCompService compService;
	@Autowired
	private CtProfitService profitService;

	@Autowired
	private OtCompToProductService compToProductService;
	@Autowired
	private CtWxpayConfigService wxpayConfigService;
	@Autowired
	private OtCompProductStockService compProductStockService;
	@Autowired
	private UtMyproductstoreService myproductstoreService;

    @Autowired
    private UtUserService service;
    @Autowired
    private LogService logService;
    @Autowired
    private HttpSession session;
	/**存放地址*/
	@Value("${global.upload.docBase}")
	private String docBase;
	@Value("${publicURL}") //在配置文件中
	private String publicurl;

	@Value("${ucustomerid}")
	private String ucustomerid;
	@Value("${ucompid}")
	private String ucompid;

	/**
	 * 非微信登陆页面
	 * @return
	 */
    /*@GetMapping("login")
    public String login() {
    	return "templates/h5/login";
    }*/
    @GetMapping("login")
    public String login() {
    	return "templates/weui/login";
    }

	/**
	 * 非微信注册页面
	 * @param uuserid
	 * @param model
	 * @return
	 */
    @GetMapping("register")
    public String register(String uuserid,Model model) {
		UtUsers user = (UtUsers) session.getAttribute(Constant.SESSION_H5USER);

		if (user != null) {
			return "redirect:/h5/home";
		}

		model.addAttribute("upuserid", uuserid);
    	return "templates/h5/register";
    }
	/**
	 * 非微信注册页面
	 * @param uuserid
	 * @param model
	 * @return
	 */
	@GetMapping("weregister")
	public String weregister(String uuserid,Model model) {
		model.addAttribute("upuserid", uuserid);
		return "templates/weui/register";
	}

	/**
     * 登录
     * @author LBY
     * @data 2019年2月27日
     * @param users
     * @return
     */
    @PostMapping("login")
    @ResponseBody
    public AjaxResult login(UtUsers users) {
		users.setUcustomerid(ucustomerid);
    	UtUsers loginUser = service.login(users);
    	if (loginUser != null) {
    		session.setAttribute(Constant.SESSION_H5USER, loginUser);
    		return returnSuccess("登录成功");
    	}
    	return returnFailed("登录失败，用户名或密码有误");
    }

	@ResponseBody // 发生验证码 loginname
	@RequestMapping("code")
	public AjaxResult code(String umobile) {
		try {
			if ((umobile == null || umobile.equals(""))) {
				return returnFailed(0,"缺少参数");
			}
			if (service.queryCountByUmobileOnCompany(umobile,ucustomerid) > 0) {
				return new AjaxResult(0, "该账号已存在");
			}
			Integer num = logService.sp_createRandom(umobile);
			SmsManage.sendMessage(umobile, "【"+Constant.ucustomername+"】您的验证码:" + num + ",请在30分钟内有效。非本人请忽略，打扰了！");
			return returnSuccess("验证码已经成功发送，请查收");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return returnFailed("缺少参数");
	}
    /**
     * 注册
     * @author LBY
     * @data 2019年2月27日
     * @param users
     * @return
     */
    @PostMapping("register")
    @ResponseBody
    public AjaxResult register(UtUsers users,String codeno,String type,HttpServletRequest request, HttpServletResponse response) {
    	//验证验证码是否正确
		boolean b = logService.selectCodeBymobileCount(codeno, users.getUmobile()) > 0;
		if (b) {
			//type=1 微信内  type=2 普通浏览器
			if (!StringUtils.isEmpty(users.getUmobile())
					&& !StringUtils.isEmpty(users.getUloginname()) && !StringUtils.isEmpty(users.getUpassword())) {
				if (service.selectCountByUmobile(users.getUmobile()) > 0) {
					return returnFailed("手机号码已存在");
				} else if (service.selectCountByUserName(users.getUloginname()) > 0) {
					return returnFailed("用户名称已存在");
				} else {
					users.setUuserid(nextId());
					users.setUname(users.getUloginname());
					Integer usercode = compService.getSysSN(4);
					users.setUusercode(usercode);
					users.setUregdate(timeStamp() + "");
					//获取访问页面的url
					StringBuffer sb = request.getRequestURL();
					String shopurl = sb.delete(sb.length() - request.getRequestURI().length(), sb.length()).toString();
					//生成二维码
					users.setQrcode("/uploadwallet" + QRCodeUtil.encode(shopurl + "/h5/user/register?uuserid=" + users.getUuserid(), docBase + "logo.png", docBase, "/qrcode/", true));
					users.setUeflag("01");
					if (users.getUvip() == null || users.getUvip().equalsIgnoreCase("0")) {
						users.setUvip("0");
					} else {
						users.setUvip("0");
						UtUsers regUser = service.register(users);

						if (regUser != null) {

							return new AjaxResult(3, "注册成功，支付后成为会员", regUser);
						}
					}
					users.setUvip(users.getUvip() == null ? "0" : users.getUvip());
					UtUsers regUser = service.register(users);
					if (regUser != null)
						return returnSuccess("注册成功，请登录");
					return returnFailed("注册失败");
				}
			}
		}else{
			return returnFailed("验证码错误");
		}
    	return returnFailed("缺少参数");
    }

	/**
	 * 退出登录
	 * @return
	 */
    @GetMapping("loginout")
    public String loginout() {
    	session.invalidate();
    	return "redirect:/h5/user/login";
    }

	@RequestMapping("/registVIP")
	public void registVIP(String uuserid, HttpServletRequest request, HttpServletResponse response) throws Exception {


		Map<String, Object> result = new HashMap<String, Object>();
		result.put("success", false);
		try {



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



			// 付款金额，必填
//			String total_fee = request.getParameter("WIDtotal_fee");
			//付款金额
			String total_fee = "131";
			CtProfit ctProfit = profitService.queryProfitByCompId(ucompid, ucustomerid);
			if (ctProfit != null) {
				total_fee = ctProfit.getUvip().toString();

			}
			//测试
//			total_fee = "0.01";
			// ip地址获取
			String basePath = request.getServerName() + ":" + request.getServerPort();
			// 账号信息
			/*String appid = PayConfig.APP_ID; // appid
			String mch_id = PayConfig.MCH_ID; // 商业号
			String key = PayConfig.API_KEY; // key*/

			String currTime = PayCommonUtil.getCurrTime();
			String strTime = currTime.substring(8, currTime.length());
			String strRandom = PayCommonUtil.buildRandom(4) + "";
			String nonce_str = strTime + strRandom;
			// 价格 注意：价格的单位是分
			String order_price = new BigDecimal(total_fee).multiply(new BigDecimal(100)).toString().split("\\.")[0];
			// 订单号为 uuserid
			String out_trade_no = uuserid;
			/*RandomGUID id = new RandomGUID();
			String out_trade_no = id.toString().substring(0, 32);*/
			// 获取发起电脑 ip
			String spbill_create_ip = HttpUtil.getRealIp(request);
			StringBuffer sb = request.getRequestURL();
			String shopurl = sb.delete(sb.length() - request.getRequestURI().length(), sb.length()).toString();
			// 回调接口
			String notify_url = shopurl +PayConfig.NOTIFY_URL_REGISTVIP /*.replaceAll("localhostUrl", basePath)+ getCurrentUser().getId()*/;
			// 页面跳转同步通知页面路径
			String trade_type = "MWEB";

			// 设置package订单参数
			SortedMap<Object, Object> packageParams = new TreeMap<Object, Object>();
			packageParams.put("appid", appid);
			packageParams.put("mch_id", mch_id);
			// 生成签名的时候需要你自己设置随机字符串
			packageParams.put("nonce_str", nonce_str);
			packageParams.put("out_trade_no", out_trade_no);
			packageParams.put("total_fee", order_price);
			packageParams.put("spbill_create_ip", spbill_create_ip);
			packageParams.put("notify_url", notify_url);
			packageParams.put("trade_type", trade_type);
			packageParams.put("body", "会员注册");
			packageParams.put("scene_info", "{\"h5_info\": {\"type\":\"Wap\",\"wap_url\": \""+shopurl+"h5/weindex\",\"wap_name\": \""+Constant.ucustomername+"\"}}");
			String sign = PayCommonUtil.createSign("UTF-8", packageParams, key);
			packageParams.put("sign", sign);
			String requestXML = PayCommonUtil.getRequestXml(packageParams);
			String resXml = HttpUtil.postData(PayConfig.UFDODER_URL, requestXML);
			//System.out.println(resXml);

			Map map = XMLUtil.doXMLParse(resXml);
			String urlCode = (String) map.get("code_url");
			//确认支付过后跳的地址,需要经过urlencode处理
			String urlString = URLEncoder.encode(shopurl+"/h5/home", "GBK");
			String mweb_url = (String) map.get("mweb_url")+"&redirect_url="+urlString;
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
	 * 获取微信code
	 * @param uuserid
	 * @return
	 */
	@RequestMapping("getCode")
	public String getCode(String uuserid) {
		try {
		String codeUrl = "https://open.weixin.qq.com/connect/oauth2/authorize";
		String state = "";
		if (com.zdv.shop.common.utils.StringUtils.StringisNotEmpty(uuserid)) {
			state = uuserid;
		}



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
			HttpServletRequest request = getRequest();
			StringBuffer sb = request.getRequestURL();
			String shopurl = sb.delete(sb.length() - request.getRequestURI().length(), sb.length()).toString();


		String uri =URLEncoder.encode(shopurl+PayConfig.REDIRECT_URI_VIP, "GBK");
		String url = codeUrl + "?appid=" + appid + "&redirect_uri=" + uri + "&response_type=code&scope=snsapi_base&state=" + state + "#wechat_redirect";
		return  "redirect:"+url+"";
		}catch(UnsupportedEncodingException e) {
			return null;
		}
	}

	/**
	 * 跳转支付中间页
	 * @param code
	 * @param state
	 * @param model
	 * @return
	 */
	@RequestMapping("repay")
	public String repay(String code, String state, Model model) {
		model.addAttribute("code", code);

		model.addAttribute("state", state);
		return "templates/h5/payvip";
	}


	/**
	 * 获取openid并支付
	 * @param code
	 * @param state
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping("getOpenid")
	@ResponseBody
	public Map  getOpenid(String code, String state,HttpServletRequest request, HttpServletResponse response, ModelMap model) {
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
		System.out.println("openid======"+openid);

		Map<String, Object> result = new HashMap<String, Object>();
		result.put("success", false);
		try {

			UtUsers utUsers = service.queryByID(state);

			// 付款金额，必填
//			String total_fee = request.getParameter("WIDtotal_fee");
			String total_fee = "131";
			CtProfit ctProfit = profitService.queryProfitByCompId(ucompid, ucustomerid);
			if (ctProfit != null) {
				total_fee = ctProfit.getUvip().toString();
			}
			//付款金额

			//测试
//			total_fee = "0.01";

			// ip地址获取
			String basePath = request.getServerName() + ":" + request.getServerPort();
			// 账号信息
			/*String appid = PayConfig.APP_ID; // appid
			String mch_id = PayConfig.MCH_ID; // 商业号
			String key = PayConfig.API_KEY; // key*/

			String currTime = PayCommonUtil.getCurrTime();
			String strTime = currTime.substring(8, currTime.length());
			String strRandom = PayCommonUtil.buildRandom(4) + "";
			String nonce_str = strTime + strRandom;
			// 价格 注意：价格的单位是分
			String order_price = new BigDecimal(total_fee).multiply(new BigDecimal(100)).toString().split("\\.")[0];
			// 自己网站上的订单号
			String out_trade_no = state; /////111111111111111111
			/*RandomGUID id = new RandomGUID();
			String out_trade_no = id.toString().substring(0, 32);*/
			// 获取发起电脑 ip
			String spbill_create_ip = HttpUtil.getRealIp(request);
			StringBuffer sb = request.getRequestURL();
			String shopurl = sb.delete(sb.length() - request.getRequestURI().length(), sb.length()).toString();

			// 回调接口
			String notify_url = shopurl+PayConfig.NOTIFY_URL_REGISTVIP /*.replaceAll("localhostUrl", basePath)+ getCurrentUser().getId()*/;
			// 页面跳转同步通知页面路径
			String trade_type = "JSAPI";

			// 设置package订单参数
			SortedMap<Object, Object> packageParams = new TreeMap<Object, Object>();
			packageParams.put("appid", appid);
			packageParams.put("mch_id", mch_id);
			// 生成签名的时候需要你自己设置随机字符串
			packageParams.put("nonce_str", nonce_str);
			packageParams.put("out_trade_no", out_trade_no);
			packageParams.put("total_fee", order_price);
			packageParams.put("spbill_create_ip", spbill_create_ip);
			packageParams.put("notify_url", notify_url);
			packageParams.put("trade_type", trade_type);
			packageParams.put("body", PayConfig.BODY);
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


	/**
	 * 支付后回调
	 * @param request
	 * @param response
	 * @param model
	 * @throws Exception
	 */
	@RequestMapping("/vipNotify")
	public void weixin_notify(HttpServletRequest request, HttpServletResponse response, ModelMap model)throws Exception {
		System.out.println("进入支付h5回调=====================");
		String xmlMsg = readData(request);
		System.out.println("pay notice---------"+xmlMsg);
		Map params = XMLUtil.doXMLParse(xmlMsg);
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
//		// 微信支付订单号
		String transaction_id      = (String) params.get("transaction_id");
//		// 商户订单号
		String out_trade_no      = (String) params.get("out_trade_no");
//		// 支付完成时间，格式为yyyyMMddHHmmss
		String time_end      = (String) params.get("time_end");

		/////////////////////////////以下是附加参数///////////////////////////////////

//		String attach      = params.get("attach")+"";
//		String fee_type      = params.get("fee_type");
//		String is_subscribe      = params.get("is_subscribe");
//		String err_code      = params.get("err_code");
//		String err_code_des      = params.get("err_code_des");


//		String userid = null;
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
//			userid = (String) packageParams.get("userid");
			// 账号信息
			String resXml = "";
			// ------------------------------
			// 处理业务开始
			// ------------------------------
			if ("SUCCESS".equals((String) packageParams.get("result_code"))) {
				// 这里是支付成功

//				System.out.println("支付成功============"+czflg+"czstyle========="+czstyle);
				////////// 执行自己的业务逻辑////////////////
				model.put("sHtmlText", "充值成功");
				/*try {
					synchronized (czflg) {
						*//*if (czflg) {
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
						LogUtil.writeMsgToFile("enderror ----" + czflg);*//*


					}
				} catch (Exception e) {
//					LogUtil.writeMsgToFile(e);

					model.put("Exception", e);
					e.printStackTrace();
				}*/
				////////// 执行自己的业务逻辑////////////////
				try {

					UtFinancelog utFinancelog = new UtFinancelog();
					//店铺id 如何获取店铺的ucompid
					utFinancelog.setUcompid(ucompid);
					//用户id
					utFinancelog.setUuserid(out_trade_no);
					//订单id
					utFinancelog.setUinfoid(out_trade_no);
					//状态
					utFinancelog.setUstatus("0");
					//微信支付
					utFinancelog.setUtradetype("1");
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

					//创建用户财务日志
					utFinancelog.setUtype("0");
					utFinancelog.setUfinancelogid(KeyId.nextId());
					utFinancelog.setAccttype("1"); //出账
					financelogService.saveFinaceLog(utFinancelog);
					UtUsers vipuser = new UtUsers();
					UtUsers utUsers = service.queryByID(out_trade_no);
					vipuser.setUvip("1");
					vipuser.setUuserid(utUsers.getUuserid());
					service.updateByID(vipuser);


					//用户
					List<UtUsers> usersList = new ArrayList<>();
					//财务日志
					List<UtFinancelog> financelogList = new ArrayList<>();

					usersList.add(vipuser);
					financelogList.add(utFinancelog);
					//如果存在父用户（推荐人）
					if (StringUtils.isNotEmpty(utUsers.getUpuserid())) {
						// accttype   进出类型（0进账，1出账，2内部交易）


						UtUsers parent = new UtUsers();
						UtUsers query = service.queryByID(utUsers.getUpuserid());
						parent.setUuserid(utUsers.getUpuserid());
						parent.setUbalance(Double.parseDouble(query.getUbalance())+100+"");


						UtFinancelog parentFinancelog = new UtFinancelog();
						//店铺id 如何获取店铺的ucompid
						parentFinancelog.setUcompid(ucompid);
						//用户id
						parentFinancelog.setUuserid(out_trade_no);
						//订单id
						parentFinancelog.setUinfoid(out_trade_no);
						//状态
						parentFinancelog.setUstatus("0");
						//类型分润
						parentFinancelog.setUtradetype("5");
						//支付金额
						parentFinancelog.setUtrademoney(""+total_fee);
						//流水账号
						parentFinancelog.setUglidenumber(getGlidenumber());
						//是否线上支付  0线上 1 线下
						parentFinancelog.setIfline("0");
						//现在只有微信
						parentFinancelog.setUpaymode("微信");
						//第三方订单号
						parentFinancelog.setTransactionId(transaction_id);
						//时间
						parentFinancelog.setUcreatedate(time_end);
						//创建用户财务日志
						parentFinancelog.setUtype("0");
						parentFinancelog.setUfinancelogid(KeyId.nextId());
						parentFinancelog.setAccttype("2"); //内部交易
						CtProfit ctProfit = profitService.queryProfitByCompId(ucompid, ucustomerid);
						if (ctProfit != null && StringUtils.isNotEmpty(ctProfit.getUreturncash()+"") && ctProfit.getUreturncash()>0) {
							parent.setUbalance(Double.parseDouble(query.getUbalance())+ctProfit.getUreturncash()+"");
							parentFinancelog.setUtrademoney(""+ctProfit.getUreturncash());
							financelogList.add(parentFinancelog);
							usersList.add(parent);
						}

					}
					financelogService.saveUserVip(usersList,financelogList);


					// 通知微信.异步确认成功.必写.不然会一直通知后台.八次之后就认为交易失败了.
					resXml = "<xml>" + "<return_code><![CDATA[SUCCESS]]></return_code>" + "<return_msg><![CDATA[OK]]></return_msg>" + "</xml> ";
				} catch (Exception e) {
					e.printStackTrace();
				}

			} else {
				model.put("sHtmlText", "付款失败");
				resXml = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>" + "<return_msg><![CDATA[充值失败]]></return_msg>" + "</xml> ";
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

/*
	@RequestMapping("getWxcode")
	public AjaxResult getWxcode() throws UnsupportedEncodingException {
		HttpServletRequest request = getRequest();
		StringBuffer sb = request.getRequestURL();
		String shopurl = sb.delete(sb.length() - request.getRequestURI().length(), sb.length()).toString();

		//获取code的微信api
		String codeUrl = "https://open.weixin.qq.com/connect/oauth2/authorize";
//			String shopurl = PayConfig.SHOPURL;

		//微信回调地址
		String uri =URLEncoder.encode(shopurl+"/h5/user/wxautologin", "GBK");
		CtWxpayConfig ctWxpayConfig = new CtWxpayConfig();

		ctWxpayConfig.setUcustomerid(ucustomerid);
		//0微信 1支付宝
		ctWxpayConfig.setUtypes("0");
		List<CtWxpayConfig> configs = wxpayConfigService.queryList(ctWxpayConfig);
		if (configs.size() > 0) {
			String appid = configs.get(0).getAppid();
			String url = codeUrl + "?appid=" + appid + "&redirect_uri=" + uri + "&response_type=code&scope=snsapi_userinfo&state=1#wechat_redirect";
			return  "redirect:"+url+"";
		}
	}*/


	/**
	 * 微信内自动登录
	 * @param code
	 * @param model
	 * @return
	 */
	@RequestMapping("wxautologin")
	public String wxautologin(String code,Model model) {
		HttpServletRequest request = getRequest();
		String attribute = (String) session.getAttribute("url&param");

		System.out.println("wxautologin.....requestUri==============================="+attribute);

		String openUrl = "https://api.weixin.qq.com/sns/oauth2/access_token";


		CtWxpayConfig ctWxpayConfig = new CtWxpayConfig();
		ctWxpayConfig.setUcustomerid(ucustomerid);
		CtComp ctComp = compService.queryByID(ucompid);

		//0微信 1支付宝
		ctWxpayConfig.setUtypes("0");
		List<CtWxpayConfig> configs = wxpayConfigService.queryList(ctWxpayConfig);
		String appid = "";
		String appsecret = "";
		if (configs.size() > 0) {
			appid = configs.get(0).getAppid();
			appsecret = configs.get(0).getAppsecret();
		}

		//通过code获取openid和access_token
		String param = "appid=" + appid + "&secret=" + appsecret + "&code=" + code + "&grant_type=authorization_code";
		String s = HttpRequest.sendGet(openUrl, param);
		JSONObject json = JSONObject.parseObject(s);
		String openid = json.getString("openid");
		String access_token = json.getString("access_token");

		//通过token和openid获取用户信息
		String url = "https://api.weixin.qq.com/sns/userinfo";
		String param2 = "access_token=" + access_token + "&openid=" + openid + "&lang=zh_CN";

		String userinfo = HttpRequest.sendGet(url, param2);
		JSONObject userinfoJSON = JSONObject.parseObject(userinfo);

		//如果获取到openid
		if (StringUtils.isNotEmpty(openid)) {
			//判断用户是否已存在
			UtThirdlogin thirdlogin = thirdloginService.selectInfoByOpenId(openid, "0");
			if (thirdlogin != null) {
				UtUsers users = service.queryByID(thirdlogin.getUuserid());
				if(users != null){
					session.setAttribute(Constant.SESSION_H5USER, users);
				}

			}else{
				UtUsers users = new UtUsers();
				//如果存在父id
				String upuserid = (String) session.getAttribute("upuserid");

				users.setUuserid(nextId());
				users.setUloginname(openid);
				Integer usercode = compService.getSysSN(4);
				users.setUusercode(usercode);
				users.setUlogo(userinfoJSON.getString("headimgurl"));
				Random random = new Random();
				int i = random.nextInt(1000000);
				users.setUpassword(MD5Util.encrypt(i + ""));
				users.setUname(NickNameFilter.filterEmoji(userinfoJSON.getString("nickname")));
				users.setUvip("0");
				UtThirdlogin utThirdlogin = new UtThirdlogin();
				utThirdlogin.setThirdloginid(nextId());
				utThirdlogin.setAccess_token(access_token);
				utThirdlogin.setOpenid(openid);
				utThirdlogin.setUuserid(users.getUuserid());
				utThirdlogin.setUtype("0");
				session.setAttribute(Constant.SESSION_H5USER, users);

				//创建ut_comp_member_user数据
				UtCompMemberUser utCompMemberUser = new UtCompMemberUser();

				utCompMemberUser.setUcompid(ucompid);
				utCompMemberUser.setUcustomerid(ucustomerid);
				utCompMemberUser.setUuserid(users.getUuserid());
				utCompMemberUser.setUfromchannel("自由加入");
				if (StringUtils.isNotEmpty(upuserid)) {
					users.setUpuserid(upuserid);

					UtThirdlogin parentthirdlogin = thirdloginService.selectInfoByUserid(upuserid, "0");
					if (parentthirdlogin != null) {
						//标题
						String title = "推荐用户";
						//门店
						String shop = ctComp.getUcompname();
						//服务
						String service = "推荐用户";
						//时间
						String time = DateFormat.getDateTimeInstance(2, 2, Locale.CHINESE).format(new java.util.Date());
						//数据 (详细内容)
						String data = "您推荐的用户ID" + users.getUusercode() + "名称:" + users.getUname() + "成功注册成为了会员";
						String templateid = "MNFURs3OtrK8tpRxPIjLE0MjCJwGEp3OH3XqRp9TGSQ";

						TemplateSendUtil.send_template_message(appid, appsecret, parentthirdlogin.getOpenid(), templateid, title, shop, service, time, data);
					}

				}
//				compMemberUserService.addCompMemberUser(utCompMemberUser);
				service.addUser(users,utThirdlogin,utCompMemberUser);
				session.setAttribute(Constant.SESSION_H5USER, users);
			}
		}
        System.out.println("requestUri==============================="+attribute);

		if (StringUtils.isNotEmpty(attribute)) {
			model.addAttribute("url", attribute);
			return "templates/weui/skip";
		}

		return  "redirect:/h5/weindex";
	}


	/**
	 * VIP选择付款页面
	 * @param model
	 * @return
	 */
	@ControllerLog("跳转到成为VIP页面")
	@RequestMapping("vipPage")
	public String vipPage(Model model) {
		UtUsers users = (UtUsers) session.getAttribute(Constant.SESSION_H5USER);

		model.addAttribute("user", users);

		CtProfit ctProfit = profitService.queryProfitByCompId(null, ucustomerid);

		Double vipcash = ctProfit.getUvip();
		if (vipcash != null) {
			model.addAttribute("vipcash", vipcash);
		}else{
			model.addAttribute("vipcash", 0.0);
		}

		return "templates/weui/vip";
	}

	/**
	 * 零钱支付VIP
	 * @return
	 */
	@RequestMapping("walletVIP")
	@ResponseBody
	public AjaxResult walletVIP() {
		UtUsers users = (UtUsers) session.getAttribute(Constant.SESSION_H5USER);


		if (users == null) {
			return new AjaxResult(0, "用户未登录,请登陆后操作");
		}


		UtUsers user = service.queryByID(users.getUuserid());

		if (user.getUvip().equals("1")) {
			return new AjaxResult(0, "该用户已是会员");
		}
		Double ubalance = Double.valueOf(user.getUbalance());
		String vipcash = "131";
		CtProfit ctProfit = profitService.queryProfitByCompId(ucompid, ucustomerid);
		if (ctProfit != null) {
			vipcash = ctProfit.getUvip().toString();

		}

		double doubleVipcash = Double.parseDouble(vipcash);
		try {
			if (ubalance >= doubleVipcash) {
			ubalance -= doubleVipcash;
			UtUsers utUsers = new UtUsers();
			String uuserid = users.getUuserid();
			utUsers.setUbalance(ubalance + "");
			utUsers.setUuserid(uuserid);
			utUsers.setUvip("1");
			UtFinancelog utFinancelog = new UtFinancelog();
			//店铺id 如何获取店铺的ucompid
			utFinancelog.setUcompid(ucompid);
			//用户id
			utFinancelog.setUuserid(uuserid);
			//订单id
			utFinancelog.setUinfoid(uuserid);
			//状态
			utFinancelog.setUstatus("0");
			//零钱支付
			utFinancelog.setUtradetype("2");
			//支付金额
			utFinancelog.setUtrademoney("-"+vipcash);

			//流水账号
			utFinancelog.setUglidenumber(getGlidenumber());
			//是否线上支付  0线上 1 线下
			utFinancelog.setIfline("0");
			//现在
			utFinancelog.setUpaymode("零钱");
			//第三方订单号
//			utFinancelog.setTransactionId(transaction_id);

			//创建用户财务日志
			utFinancelog.setUtype("0");
			utFinancelog.setUfinancelogid(KeyId.nextId());
			utFinancelog.setAccttype("1"); //出账
//			financelogService.saveFinaceLog(utFinancelog);
			//用户
			List<UtUsers> usersList = new ArrayList<>();
			//财务日志
			List<UtFinancelog> financelogList = new ArrayList<>();

			usersList.add(utUsers);
			financelogList.add(utFinancelog);
			//如果存在父用户（推荐人）
			if (StringUtils.isNotEmpty(user.getUpuserid())) {
				// accttype   进出类型（0进账，1出账，2内部交易）

				String upuserid = user.getUpuserid();
				UtUsers parent = new UtUsers();
				UtUsers query = service.queryByID(upuserid);
				parent.setUuserid(upuserid);
				//默认返现金额
				Double parentBalance = 100.00;

				if (ctProfit != null) {
					parentBalance = ctProfit.getUreturncash();

				}
				parent.setUbalance(Double.parseDouble(query.getUbalance()) +parentBalance+"");

				UtFinancelog parentFinancelog = new UtFinancelog();
				//店铺id
				parentFinancelog.setUcompid(ucompid);
				//用户id
				parentFinancelog.setUuserid(upuserid);
				//订单id
				parentFinancelog.setUinfoid(uuserid);
				//状态
				parentFinancelog.setUstatus("0");
				//
				parentFinancelog.setUtradetype("5");
				//支付金额
				parentFinancelog.setUtrademoney(""+parentBalance);
				//流水账号
				parentFinancelog.setUglidenumber(getGlidenumber());
				//是否线上支付  0线上 1 线下
				parentFinancelog.setIfline("0");
				//现在只有微信
				parentFinancelog.setUpaymode("零钱");
				//第三方订单号
//				parentFinancelog.setTransactionId(transaction_id);
				//时间
//				parentFinancelog.setUcreatedate(time_end);
				//创建用户财务日志
				parentFinancelog.setUtype("0");
				parentFinancelog.setUfinancelogid(KeyId.nextId());
				parentFinancelog.setAccttype("2"); //内部交易


				usersList.add(parent);
				financelogList.add(parentFinancelog);
				}
				financelogService.saveUserVip(usersList,financelogList);


			}else{
				return new AjaxResult(0, "用户余额不足");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new AjaxResult(0, "支付出错,请联系商家");
		}

		return new AjaxResult(1, "支付成功");

	}

	@RequestMapping("checkMobile")
	@ResponseBody
	public AjaxResult checkMobile(String umobile) {

		UtUsers users = (UtUsers) session.getAttribute(Constant.SESSION_H5USER);


		if (users == null) {
			return new AjaxResult(0, "用户未登录,请登陆后操作");
		}
		Map<String, Object> map = new HashMap<>();
		map.put("umobile", umobile);
		map.put("ucustomerid", ucustomerid);



		int i = service.queryCountByUmobileOnCompany(umobile,ucustomerid);
		if (i > 0) {
			return new AjaxResult(0, "该手机号已存在绑定用户");
		}else{
			return new AjaxResult();
		}

	}


}
