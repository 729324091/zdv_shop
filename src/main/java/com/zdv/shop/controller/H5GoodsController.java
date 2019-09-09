package com.zdv.shop.controller;

import com.github.pagehelper.PageInfo;
import com.zdv.shop.common.Constant;
import com.zdv.shop.common.pojo.AjaxResult;
import com.zdv.shop.common.utils.StringUtils;
import com.zdv.shop.model.*;
import com.zdv.shop.model.po.GradePO;
import com.zdv.shop.model.vo.H5GoodsListVO;
import com.zdv.shop.service.*;
import com.zdv.shop.weixinh5.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.net.CookiePolicy;
import java.net.URLEncoder;
import java.text.NumberFormat;
import java.util.*;

/**
 * H5端商品Controller
 * 
 * @author LBY
 * @data 2019年2月23日
 */
@Controller
@RequestMapping("/h5/")
public class H5GoodsController extends BaseController {
	

	@Autowired
	private UtOrderItemService utOrderItemService;
	@Autowired
	private OtCompToProductService compToProductService;
	@Autowired
	private OtCompProductStockService otCompProductStockService;
	@Autowired
	private CtCompService compService;
	@Autowired
	private UtOrderService orderService;
	@Autowired
	private UtUserService userService;
	@Autowired
	private MessageService messageService;

	@Autowired
	private UtCompMemberUserService compMemberUserService;
	@Autowired
	private OtProductGradeService produService;
	@Autowired
	private UtMyproductstoreService myproductstoreService;
	@Autowired
	private OtCompProductStockObjnameService objnameService;
	@Autowired
    private CtWxpayConfigService wxpayConfigService;
	@Autowired
	private OtCompProductStockService compProductStockService;
	@Autowired
	private LogService logService;
	@Autowired
	private CtProfitService profitService;
	@Autowired
	private OtConfigService configService;
    @Autowired
    private XtNewsService newsService;

	@Value("${ucompid}")
	private String ucompid;

	@Value("${ucustomerid}")
	private String customerid;
	@Value("${publicURL}")
	private static String publicurl;

	@RequestMapping("index")
	public String index(Model model) {
		//推荐商品及广告
		//大广告
	List<OtConfig> slideshowHomeList = configService.getConfigByType(Constant.slideshowHome,customerid);
	model.addAttribute("slideshowHome", slideshowHomeList);
		//系统推荐商品
	try {
		List<OtConfig> productHomeList = configService.getConfigByType(Constant.productHome,customerid);
		List<OtConfig> bigproduct = new ArrayList<>();
		List<OtConfig> littleproduct = new ArrayList<>();
		for (int i = 0; i < productHomeList.size(); i++) {
			if (i % 3 == 0) {
				bigproduct.add(productHomeList.get(i));
			}else{
				littleproduct.add(productHomeList.get(i));
			}
		}
		model.addAttribute("bigproduct", bigproduct);
//		productHomeList.remove(0);
		model.addAttribute("littleproduct", littleproduct);
		//系统推荐分类
		List<OtConfig> categoryHomeList = configService.getConfigByType(Constant.categoryHome,customerid);
		model.addAttribute("categoryHome", categoryHomeList);
		//最新资讯
		List<OtConfig> consultationList = configService.getConfigByType(Constant.consultation,customerid);
		model.addAttribute("consultation", consultationList);
	}catch(Exception e) {
		
	}
		return "templates/h5/index";
	}



	@RequestMapping("weindex")
	public String weindex(Model model,HttpServletRequest request) throws UnsupportedEncodingException {
		HttpSession session = getRequest().getSession();
		UtUsers user = (UtUsers) session.getAttribute(Constant.SESSION_H5USER);
		StringBuffer sb = request.getRequestURL();
		String shopurl = sb.delete(sb.length() - request.getRequestURI().length(), sb.length()).toString();

		//判断是否是微信打开
		String ua = ((HttpServletRequest) request).getHeader("user-agent").toLowerCase();
		if (ua.indexOf("micromessenger") > 0) {// 是微信浏览器
			if (user == null) {
				//获取code的微信api
				String codeUrl = "https://open.weixin.qq.com/connect/oauth2/authorize";
//			String shopurl = PayConfig.SHOPURL;
				String upuserid = request.getParameter("upuserid");
				if (StringUtils.StringisNotEmpty(upuserid)) {
					session.setAttribute("upuserid",upuserid);
				}
				//微信回调地址
				String uri =URLEncoder.encode(shopurl+"/h5/user/wxautologin", "GBK");
                CtWxpayConfig ctWxpayConfig = new CtWxpayConfig();

                ctWxpayConfig.setUcustomerid(customerid);
                //0微信 1支付宝
                ctWxpayConfig.setUtypes("0");
                List<CtWxpayConfig> configs = wxpayConfigService.queryList(ctWxpayConfig);
                if (configs.size() > 0) {
                    String appid = configs.get(0).getAppid();
                    String url = codeUrl + "?appid=" + appid + "&redirect_uri=" + uri + "&response_type=code&scope=snsapi_userinfo&state=1#wechat_redirect";
                    return  "redirect:"+url+"";
                }
			}
		}

		//
		UtCompMemberUser byUuserid = compMemberUserService.getByUuserid(user.getUuserid());
		String ucompid = byUuserid.getUcompid();


		//推荐商品及广告
		//大广告
		List<OtConfig> slideshowHomeList = configService.getConfigByType(Constant.slideshowHome,customerid,ucompid);
		model.addAttribute("slideshowHome", slideshowHomeList);

		//系统推荐商品
		try {
			List<OtConfig> productHomeList = configService.getConfigByType(Constant.productHome,customerid,ucompid);
			List<OtConfig> bigproduct = new ArrayList<>();
			List<OtConfig> littleproduct = new ArrayList<>();
			for (int i = 0; i < productHomeList.size(); i++) {
				if (i % 3 == 0) {
					bigproduct.add(productHomeList.get(i));
				}else{
					littleproduct.add(productHomeList.get(i));
				}
			}
			model.addAttribute("bigproduct", bigproduct);
//		productHomeList.remove(0);
			model.addAttribute("littleproduct", littleproduct);
			//系统推荐分类
			List<OtConfig> categoryHomeList = configService.getConfigByType(Constant.categoryHome,customerid,ucompid);
			model.addAttribute("categoryHome", categoryHomeList);
			//最新资讯
            XtNews news = new XtNews();
            news.setUcustomerid(customerid);
            news.setUcompid(ucompid);
            news.setIffirstnews("1");
            List<XtNews> newsList = newsService.queryNewsList(news);
            model.addAttribute("newsList", newsList);
//			List<OtConfig> consultationList = configService.getConfigByType(Constant.consultation,customerid);
//			model.addAttribute("consultation", consultationList);
			//显示登录用户的购物车数量
			int count = 0;
			if(user !=null) {
				List<Map<String, Object>> mapList = utOrderItemService.queryCartOrderItem(user.getUuserid());
				for (Map<String, Object> map : mapList) {
					count++;
				}
			}
			System.out.println(count+"----------");

			model.addAttribute("cartCount", count);

			List<OtCompToProduct> newest = compToProductService.queryNewestProduct(customerid,10,ucompid);
			model.addAttribute("newlist", newest);
			model.addAttribute("ucustomerid", customerid);

			UtUsers users = userService.queryByID(user.getUuserid());
			model.addAttribute("user", users);
		}catch(Exception e) {

		}



		return "templates/weui/index";
	}
	@RequestMapping("home")
	public String home(Model model , String requestUri, HttpServletRequest request, HttpServletResponse response) {

		HttpSession session = getRequest().getSession();
		System.out.println("home.....requestUri==============================="+requestUri);
//		requestUri = request.getParameter("requestUri");

		UtUsers user = (UtUsers) session.getAttribute(Constant.SESSION_H5USER);
		if (user == null) {
			//判断是否是微信打开
			String ua = ((HttpServletRequest) request).getHeader("user-agent").toLowerCase();
			if (ua.indexOf("micromessenger") > 0) {// 是微信浏览器
				StringBuffer sb = request.getRequestURL();
				String shopurl = sb.delete(sb.length() - request.getRequestURI().length(), sb.length()).toString();

				if (user == null) {
					String codeUrl = "https://open.weixin.qq.com/connect/oauth2/authorize";
			//			String shopurl = PayConfig.SHOPURL;

					String upuserid = request.getParameter("upuserid");
					if (StringUtils.StringisNotEmpty(upuserid)) {
						session.setAttribute("upuserid",upuserid);
					}
					String uri = null;
					try {
						uri = URLEncoder.encode(shopurl+"/h5/user/wxautologin", "GBK");
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
					}

                    CtWxpayConfig ctWxpayConfig = new CtWxpayConfig();
                    ctWxpayConfig.setUcustomerid(customerid);
                    //0微信 1支付宝
                    ctWxpayConfig.setUtypes("0");
                    List<CtWxpayConfig> configs = wxpayConfigService.queryList(ctWxpayConfig);
                    if (configs.size() > 0) {
                        String appid = configs.get(0).getAppid();
                        String url = codeUrl + "?appid=" + appid + "&redirect_uri=" + uri + "&response_type=code&scope=snsapi_userinfo&state=1#wechat_redirect";
						System.out.println(url);

                        return  "redirect:"+url+"";
                    }
				}
			}else{
				return  "templates/weui/login";
			}
		}


		// 查询出未付款 待收货 待评价 订单数
		UtOrder utOrder = new UtOrder();
		utOrder.setUuserid(user.getUuserid());
		// 各个状态订单数
		orderService.queryCenterOrderCount(utOrder, model);

		UtUsers utUsers = userService.queryByID(user.getUuserid());

		String uvip = utUsers.getUvip();
		if (uvip.equalsIgnoreCase("0")) {
			utUsers.setUvip("普通会员");
		} else if (uvip.equalsIgnoreCase("1")) {
			utUsers.setUvip("VIP");
		} else if (uvip.equalsIgnoreCase("2")) {
			utUsers.setUvip("经理");
		} else if (uvip.equalsIgnoreCase("3")) {
			utUsers.setUvip("分公司");
		} else if (uvip.equalsIgnoreCase("4")) {
			utUsers.setUvip("店长");
		}


		//未读消息
		Integer unreadMessageByuserId = messageService.getUnreadMessageByuserId(user.getUuserid());
		model.addAttribute("nomessage", unreadMessageByuserId);
		model.addAttribute("user", utUsers);
		int count = 0;
		if(user !=null) {
			List<Map<String, Object>> mapList = utOrderItemService.queryCartOrderItem(user.getUuserid());
			for (Map<String, Object> map : mapList) {
				count++;
			}
		}
		System.out.println(count+"----------");
		model.addAttribute("cartCount", count);
		//获取访问页面的url
		StringBuffer sb = request.getRequestURL();
		String shopurl = sb.delete(sb.length() - request.getRequestURI().length(), sb.length()).toString();


		String path = shopurl + "/h5/user/weregister?uuserid=" + utUsers.getUuserid();
		model.addAttribute("path", path);
		//获取收藏数量
		List<UtMyproductstore> list = myproductstoreService.queryListByUserid(utUsers.getUuserid(), customerid);
		model.addAttribute("starsize",list.size());

		//获取足迹记录
		List<String> history = (List<String>) session.getAttribute("history");
		if (StringUtils.objectIsNull(history)) {
			model.addAttribute("historysize","0");
		}else{
			model.addAttribute("historysize", history.size());
		}

		UtCompMemberUser memberUser = new UtCompMemberUser();
		memberUser.setUuserid(user.getUuserid());
		memberUser.setUcustomerid(customerid);
		//获取推广二维码
		UtCompMemberUser compMemberUser = compMemberUserService.queryOne(memberUser);
		if (compMemberUser != null) {
			model.addAttribute("qrcodepath", compMemberUser.getQrcodepath());
		}



		CtProfit ctProfit = profitService.queryProfitByCompId(ucompid, customerid);
		if (ctProfit != null) {
			model.addAttribute("vipcash", ctProfit.getUvip());
		}


		return "templates/weui/home";
	}

	@RequestMapping("personal_center")
	public String personalCenter(Model model, @SessionAttribute(Constant.SESSION_H5USER) UtUsers user) {
		// 查询出未付款 待收货 待评价 订单数
		UtOrder utOrder = new UtOrder();
		utOrder.setUuserid(user.getUuserid());
		// 各个状态订单数
		orderService.queryCenterOrderCount(utOrder, model);

		UtUsers utUsers = userService.queryByID(user.getUuserid());
		//未读消息
		Integer unreadMessageByuserId = messageService.getUnreadMessageByuserId(user.getUuserid());
		model.addAttribute("nomessage", unreadMessageByuserId);
		model.addAttribute("user", utUsers);

		return "templates/h5/personal_center";
	}

	@RequestMapping("shop_goods/{ucompid}")
	public String shopGoods(@PathVariable("ucompid") String ucompid, Model model) {
		CtComp ctComp = compService.queryByID(ucompid);
		model.addAttribute("ctComp", ctComp);
		model.addAttribute("goodsList", compToProductService.queryGoodsListByCompid(ucompid));
		return "templates/h5/shop_goods";
	}

	/**
	 * 查询商品详情
	 * 
	 * @author LBY
	 * @data 2019年2月26日
	 * @param model
	 * @return
	 */
	@RequestMapping("goods/{ucomproductid}")
	public String goodsView(Map<String, Object> map,
			/* @SessionAttribute(Constant.SESSION_H5USER) UtUsers user, */ Model model,
			@PathVariable("ucomproductid") String ucomproductid) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, NoSuchFieldException {
		Map<String, Object> product = compToProductService.queryGoodsByComproductid(ucomproductid);

		OtCompProductStock productStock = new OtCompProductStock();
		productStock.setUcomproductid(ucomproductid);
		//查询商品的库存表
		List<OtCompProductStock> productStockList = otCompProductStockService.queryList(productStock);
		//若商品存在规格库存
		if (productStockList.size() > 0) {
			//最高价
			Double max = 0.00;
			//最低价
			Double min = 0.00;
			//总库存
			Integer total = 0;
			Double vipprice = 0.00;
			Double marketprice = 0.00;
			for (OtCompProductStock compProductStock : productStockList) {
				//总库存
				total += Integer.parseInt(compProductStock.getUstock());
				//获得最大价格
				if (Double.parseDouble(compProductStock.getUprice()) > max) {
					max = Double.parseDouble(compProductStock.getUprice());
				}
				//获得最小
				if (Double.parseDouble(compProductStock.getUprice()) < min|| min ==0.00) {
					min = Double.parseDouble(compProductStock.getUprice());
					marketprice = Double.parseDouble(compProductStock.getUmarketprice());
					vipprice = Double.parseDouble(compProductStock.getUvipprice());
				}
			}
			product.put("uprice", min);
			product.put("umarketprice", marketprice);
			product.put("uvipprice", vipprice);
			product.put("ustock", total);

		}


		if(product==null) {
			return "";
		}
		String isNull = "";
		String uhomepic = (String) product.get("uhomepic");
		String[] split = uhomepic.split("#");
		model.addAttribute("piclist", split);
		if (product != null) {
//			List<Map<String,Object>> queryObjNameList = otCompProductStockService.queryObjNameList(product.get("ucomproductid").toString());
//			model.addAttribute("objList", queryObjNameList);

			// 获取属性
			Map<String, ArrayList<String>> objnamesByucompproductid = objnameService
					.getObjnamesByucompproductid(product.get("ucomproductid").toString());
			//属性不为空，则进入
			if(!objnamesByucompproductid.isEmpty()) {
				isNull="noNull";
			}
			model.addAttribute("isNull", isNull);
			model.addAttribute("objnames", objnamesByucompproductid);
		}
		model.addAttribute("goods", product);


		//刷新点击量
		OtCompToProduct otCompToProduct = compToProductService.queryByID(ucomproductid);
		OtCompToProduct compToProduct = new OtCompToProduct();
		compToProduct.setUbrowsepoint(otCompToProduct.getUbrowsepoint() + 1);
		compToProduct.setUcomproductid(ucomproductid);
		compToProductService.updateByID(compToProduct);

		// 购物车数量？
		UtUsers user = (UtUsers) getRequest().getSession().getAttribute(Constant.SESSION_H5USER);
		if (user != null) {

			List<Map<String, Object>> itemList = utOrderItemService.queryCartOrderItem(user.getUuserid());
			model.addAttribute("cartnum", itemList.size());
		} else {
			model.addAttribute("cartnum", "0");
		}
		// 获取前3页评论
		PageInfo<OtProductGrade> commenByUorderitemid = produService.getCommenByUorderitemid(ucomproductid, null, 1, 3);
		CommenConditionUtil(commenByUorderitemid.getList(), model, ucomproductid);
		// model.addAttribute("prodCommen", commenByUorderitemid);
		return "templates/h5/goods";
	}


	/**
	 * 查询商品详情
	 *
	 * @author LBY
	 * @data 2019年2月26日
	 * @param model
	 * @return
	 */
	@RequestMapping("item/{ucomproductid}")
	public String itemView(Map<String, Object> map,
			/* @SessionAttribute(Constant.SESSION_H5USER) UtUsers user, */ Model model,
							@PathVariable("ucomproductid") String ucomproductid) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, NoSuchFieldException {
		Map<String, Object> product = compToProductService.queryGoodsByComproductid(ucomproductid);

		OtCompProductStock productStock = new OtCompProductStock();
		productStock.setUcomproductid(ucomproductid);
		//查询商品的库存表
		List<OtCompProductStock> productStockList = otCompProductStockService.queryList(productStock);
		//若商品存在规格库存
		if (productStockList.size() > 0) {
			//最高价
			Double max = 0.00;
			//最低价
			Double min = 0.00;
			//总库存
			Integer total = 0;
			Double vipprice = 0.00;
			Double marketprice = 0.00;
			for (OtCompProductStock compProductStock : productStockList) {
				//总库存
				total += Integer.parseInt(compProductStock.getUstock());
				//获得最大价格
				if (Double.parseDouble(compProductStock.getUprice()) > max) {
					max = Double.parseDouble(compProductStock.getUprice());
				}
				//获得最小
				if (Double.parseDouble(compProductStock.getUprice()) < min|| min ==0.00) {
					min = Double.parseDouble(compProductStock.getUprice());
					marketprice = Double.parseDouble(compProductStock.getUmarketprice());
					vipprice = Double.parseDouble(compProductStock.getUvipprice());
				}
			}
			product.put("uprice", min);
			product.put("umarketprice", marketprice);
			product.put("uvipprice", vipprice);
			product.put("ustock", total);

		}


		if(product==null) {
			return "";
		}
		String isNull = "";
		String uhomepic = (String) product.get("uhomepic");
		if (uhomepic != null) {
			String[] split = uhomepic.split("#");
			model.addAttribute("piclist", split);
		}
		if (product != null) {
//			List<Map<String,Object>> queryObjNameList = otCompProductStockService.queryObjNameList(product.get("ucomproductid").toString());
//			model.addAttribute("objList", queryObjNameList);

			// 获取属性
			Map<String, ArrayList<String>> objnamesByucompproductid = objnameService
					.getObjnamesByucompproductid(product.get("ucomproductid").toString());
			//属性不为空，则进入
			if(!objnamesByucompproductid.isEmpty()) {
				isNull="noNull";
			}
			model.addAttribute("isNull", isNull);
			model.addAttribute("objnames", objnamesByucompproductid);
		}
		model.addAttribute("goods", product);


		//刷新点击量
		OtCompToProduct otCompToProduct = compToProductService.queryByID(ucomproductid);
		OtCompToProduct compToProduct = new OtCompToProduct();
		compToProduct.setUbrowsepoint(otCompToProduct.getUbrowsepoint() + 1);
		compToProduct.setUcomproductid(ucomproductid);
		compToProductService.updateByID(compToProduct);
		//先设置为未收藏
		model.addAttribute("collect", false);
		// 购物车数量？
		UtUsers user = (UtUsers) getRequest().getSession().getAttribute(Constant.SESSION_H5USER);
		if (user != null) {


			UtMyproductstore utMyproductstore = new UtMyproductstore();
			utMyproductstore.setUcomproductid(ucomproductid);
			utMyproductstore.setUcompid(otCompToProduct.getUcompid());
			utMyproductstore.setUuserid(user.getUuserid());
			utMyproductstore.setUproductid(otCompToProduct.getUproductid());

			List<UtMyproductstore> utMyproductstores = myproductstoreService.queryList(utMyproductstore);

			if (utMyproductstores.size() > 0) {

				model.addAttribute("collect", true);
			}

			List<Map<String, Object>> itemList = utOrderItemService.queryCartOrderItem(user.getUuserid());
			model.addAttribute("cartnum", itemList.size());
		} else {
			model.addAttribute("cartnum", "0");
		}
		// 获取前3页评论
		PageInfo<OtProductGrade> commenByUorderitemid = produService.getCommenByUorderitemid(ucomproductid, null, 1, 3);
		CommenConditionUtil(commenByUorderitemid.getList(), model, ucomproductid);
		// model.addAttribute("prodCommen", commenByUorderitemid);


		HttpSession session = getRequest().getSession();

		List<String> history = (List<String>) session.getAttribute("history");
		if (StringUtils.objectIsNull(history)) {
			history = new ArrayList<>();
		}
		if (history.contains(ucomproductid)) {
			history.remove(ucomproductid);
			history.add(ucomproductid);
		}else{
			history.add(ucomproductid);
		}
		session.setAttribute("history",history);


		model.addAttribute("ucustomerid", customerid);

		return "templates/weui/item";
	}




	@RequestMapping("hotClickGoods")
	public String hotClickGoods(H5GoodsListVO vo, Model model) {
		if ("ASC".equalsIgnoreCase(vo.getOrder()))
			vo.setOrder("DESC");
		else
			vo.setOrder("ASC");
		model.addAttribute("vo", vo);
		return "templates/h5/hot_click_goods";
	}

	/**
	 * 首页查询推荐商品列表
	 * 
	 * @author LBY
	 * @data 2019年2月23日
	 * @param page
	 * @return
	 */
	@RequestMapping("queryRecommendGoodsPage")
	@ResponseBody
	public AjaxResult queryRecommendGoodsPage(H5GoodsListVO vo) {
		defaultPage(vo);
		//设置商户号id
		vo.setUcustomerid(customerid);
		List<Map<String, Object>> maps = compToProductService.queryRecommendGoodsPage(vo);
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		for (Map<String, Object> map : maps) {
			Map<String, String> hashMap = new HashMap<>();
			String uhomepic = (String) map.get("uhomepic");
            if (StringUtils.StringisNotEmpty(uhomepic)) {
                String[] split = uhomepic.split("#");
                String s = split[0];
                map.put("uhomepic", s);
            }
			// 分割展示第一张

			/*
			 * map.get("uprice"); map.get("ualias"); map.get("uhomepic");
			 * map.put("ucomproductid",map.get("ucomproductid")+"");
			 */
			/*
			 * hashMap.put("ualias",map.get("ualias")+"");
			 * hashMap.put("uhomepic",map.get("uhomepic")+"");
			 * hashMap.put("ucomproductid",map.get("ucomproductid")+"");
			 * hashMap.put("uprice",map.get("uprice")+"");
			 */

			String ucomproductid = map.get("ucomproductid").toString();
			OtCompProductStock productStock = new OtCompProductStock();
			productStock.setUcomproductid(ucomproductid);
			List<OtCompProductStock> stockList = otCompProductStockService.queryList(productStock);

			//若商品存在规格库存
			if (stockList.size() > 0) {
				//最高价
				Double max = 0.00;
				//最低价
				Double min = 0.00;
				//总库存
				Integer total = 0;
				Double vipprice = 0.00;
				Double marketprice = 0.00;
				for (OtCompProductStock compProductStock : stockList) {
					//总库存
					total += Integer.parseInt(compProductStock.getUstock());
					//获得最大价格
					if (Double.parseDouble(compProductStock.getUprice()) > max) {
						max = Double.parseDouble(compProductStock.getUprice());
					}
					//获得最小
					if (Double.parseDouble(compProductStock.getUprice()) < min|| min ==0.00) {
						min = Double.parseDouble(compProductStock.getUprice());
						marketprice = Double.parseDouble(compProductStock.getUmarketprice());
						vipprice = Double.parseDouble(compProductStock.getUvipprice());
					}
				}
				map.put("uprice", min);
				map.put("umarketprice", marketprice);
				map.put("uvipprice", vipprice);
				map.put("ustock", total);
			}


			list.add(hashMap);
			map.get("ualias");
			map.get("uhomepic");
			map.put("ucomproductid", map.get("ucomproductid") + "");
		}
		AjaxResult ajaxResult = returnSuccess(maps);
		return ajaxResult;
	}

	/**
	 * 查询热销产品列表
	 * 
	 * @author LBY
	 * @data 2019年2月23日
	 * @param page
	 * @return
	 */
	@RequestMapping("queryHotClickGoodsPage")
	@ResponseBody
	public AjaxResult queryHotClickGoodsPage(H5GoodsListVO vo) {
		defaultPage(vo);
		return returnSuccess(compToProductService.queryHotClickGoodsPage(vo));
	}

	/**
	 * 查询促销产品列表
	 * 
	 * @author LBY
	 * @data 2019年2月23日
	 * @param page
	 * @return
	 */
	@RequestMapping("queryPromoteSalesGoodsPage")
	@ResponseBody
	public AjaxResult queryPromoteSalesGoodsPage(H5GoodsListVO vo) {
		defaultPage(vo);
		return returnSuccess(compToProductService.queryPromoteSalesGoodsPage(vo));
	}

	private H5GoodsListVO defaultPage(H5GoodsListVO vo) {
		if (vo.getPageNo() == 0)
			vo.setPageNo(1);
		if (vo.getPageSize() == 0)
			vo.setPageSize(16);
		return vo;
	}

	@RequestMapping("product/{ucomproductid}")
	@ResponseBody
	public AjaxResult queryProduct() {
		return returnSuccess();
	}

	/**
	 * 查询商品库存，并计算商品价格 ${goods?.ustock}
	 */
	@RequestMapping("queryStockAndPrice")
	@ResponseBody
	public Map<String, Object> queryStockAndPrice(String id, String[] attr, String number) {
		Map<String, Object> result = new HashMap<>();
		result.put("err_msg", "");
		try {
			// 查询商品库存，并查询价格，计算出总价格
			compToProductService.queryStockAndPrice(id,attr, number, result);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			result.put("err_msg", "失败");
		}
		return result;
	}

	public void CommenConditionUtil(List<OtProductGrade> commenByUorderitemid, Model model, String ucomproductid) {
		String isNull = "";
		String feedback = "";
		// 获取评论总数，
		Integer gradeSum = produService.getGradeToTalByType(ucomproductid, null);
		Integer goodGradeNum = produService.getGradeToTalByType(ucomproductid, "1");
		Integer middonGradeNum = produService.getGradeToTalByType(ucomproductid, "0");
		Integer badGradeNum = produService.getGradeToTalByType(ucomproductid, "-1");
		if (commenByUorderitemid != null && commenByUorderitemid.size() > 0) {
			List<GradePO> gradeList = new ArrayList<GradePO>();
			// 拿出订单项id,找到评价者
			for (int i = 0; i < commenByUorderitemid.size(); i++) {
				GradePO grade = new GradePO();
				grade.setContent(commenByUorderitemid.get(i).getContent());
				grade.setIsShowName(commenByUorderitemid.get(i).getIsshowname());
				grade.setServiceGrade(commenByUorderitemid.get(i).getServicegrade());
				String oldcreatedate = commenByUorderitemid.get(i).getCreatedate();
				String newcreatedate = DateUtil.formatDate("yyyy-MM-dd HH:mm:ss", Long.parseLong(oldcreatedate));
				grade.setDate(newcreatedate);
				// 订单项id
				String uorderitemid = commenByUorderitemid.get(i).getUorderitemid();
				String queryUnameByUorderitemid = userService.queryUnameByUorderitemid(uorderitemid);
				grade.setUname(queryUnameByUorderitemid);
				gradeList.add(grade);

			}
			model.addAttribute("gradeContent", gradeList);
			isNull = "true";
		} else {
			isNull = "false";
		}
		if (gradeSum == 0) {
			feedback = "100%";
		} else if (goodGradeNum == 0) {
			feedback = "";
		} else {
			Integer back = this.getFeedback(goodGradeNum, gradeSum);
			// 如果好评率小于50，则不显示好评率;
			if (back > 50) {
				feedback = back + "%";
			}
		}
		model.addAttribute("gradeSum", gradeSum);
		model.addAttribute("isNull", isNull);
		model.addAttribute("feedback", feedback);
	}
	
	public Integer getFeedback(Integer goodGradeNum, Integer gradeSum) {
		// 创建一个数值格式化对象

		NumberFormat numberFormat = NumberFormat.getInstance();

		// 设置精确到小数点后2位

		numberFormat.setMaximumFractionDigits(0);

		String result = numberFormat.format((float) goodGradeNum / (float) gradeSum * 100);

		return Integer.parseInt(result);
	}


	@RequestMapping("searchPage")
	public String searchPage(Model model) {
		HttpSession session = getRequest().getSession();
		List<String> searchlist = (List<String>) session.getAttribute("search-history");
		if (searchlist != null) {
			Collections.reverse(searchlist);
			model.addAttribute("searchlist", searchlist);
		}



		return "templates/weui/search";
	}

	@RequestMapping("search")
	public String search (Model model ,String keyword) {

		List<Map<String, Object>> maps = compToProductService.searchCompToProduct(keyword,customerid);

//		List<Map<String, Object>> maps = compToProductService.queryProductList(uproducttypeid, ucustomerid);
//
		for (Map<String, Object> map : maps) {
			String uhomepic = (String) map.get("uhomepic");
			String[] split = uhomepic.split("#");
			map.put("uhomepic","http://shop.diqiucunmall.cn"+ split[0]);
			String ucomproductid =  map.get("ucomproductid").toString();
			//库存及价格
			OtCompProductStock compProductStock = new OtCompProductStock();
			compProductStock.setUcomproductid(ucomproductid);
			List<OtCompProductStock> stockList = compProductStockService.queryList(compProductStock);

			if (stockList.size() > 0) {
				//最高价
				Double max = 0.00;
				//最低价
				Double min = 0.00;
				//总库存
				Integer total = 0;
				Double vipprice = 0.00;
				Double marketprice = 0.00;
				for (OtCompProductStock stock : stockList) {
					//总库存
					total += Integer.parseInt(stock.getUstock());
					//获得最大价格
					if (Double.parseDouble(stock.getUprice()) > max) {
						max = Double.parseDouble(stock.getUprice());
					}
					//获得最小
					if (Double.parseDouble(stock.getUprice()) < min|| min ==0.00) {
						min = Double.parseDouble(stock.getUprice());
						marketprice = Double.parseDouble(stock.getUmarketprice());
						vipprice = Double.parseDouble(stock.getUvipprice());
					}
				}
				map.put("uprice", min);
				map.put("umarketprice", marketprice);
				map.put("uvipprice", vipprice);
				map.put("ustock", total);


			}


		}


		model.addAttribute("goods", maps);
//		model.addAttribute("uproducttypeid", "uproducttypeid="+uproducttypeid);
		model.addAttribute("ucustomerid", customerid);
		return "templates/h5/category_goods";
	}


	@RequestMapping("newproduct")
	public String newproduct(Model model) {
		HttpServletRequest request = getRequest();
		HttpSession session = request.getSession();
		UtUsers user = (UtUsers) session.getAttribute(Constant.SESSION_H5USER);

		String ucompid = (String) request.getSession().getAttribute("ucompid");

		List<OtCompToProduct> newest = compToProductService.queryNewestProduct(customerid,10,ucompid);
		model.addAttribute("newlist", newest);

		return "templates/weui/newproduct";
	}

	@RequestMapping("freeproduct")
	public String freeproduct(Model model, String type) {

		if (StringUtils.StringisNotEmpty(type)) {
			if (type.equals("1") || type.equals("2")) {
			}else{
				type = "1";
			}

		}else{
			type = "1";
		}
		List<OtCompToProduct> free = compToProductService.queryFreeProduct(customerid,type);
		/*List<OtCompToProduct> once = new ArrayList<>();
		List<OtCompToProduct> month = new ArrayList<>();
		for (OtCompToProduct otCompToProduct : free) {

			if (otCompToProduct.getUpromotesales().equals("1")) {
				month.add(otCompToProduct);
			} else {
				once.add(otCompToProduct);
			}
		}*/

		model.addAttribute("freelist", free);
		/*model.addAttribute("oncelist", once);
		model.addAttribute("monthlist", month);*/
		model.addAttribute("type", type);
		return "templates/weui/seckill";
	}

	@RequestMapping("integralproduct")
	public String integralproduct(Model model, String type) {

		if (StringUtils.StringisNotEmpty(type)) {
			if (type.equals("1") || type.equals("2")) {
			}else{
				type = "1";
			}

		}else{
			type = "1";
		}
		List<OtCompToProduct> integral = compToProductService.queryIntegralProduct(customerid,ucompid);
		/*List<OtCompToProduct> once = new ArrayList<>();
		List<OtCompToProduct> month = new ArrayList<>();
		for (OtCompToProduct otCompToProduct : free) {

			if (otCompToProduct.getUpromotesales().equals("1")) {
				month.add(otCompToProduct);
			} else {
				once.add(otCompToProduct);
			}
		}*/

		model.addAttribute("integrallist", integral);
		/*model.addAttribute("oncelist", once);
		model.addAttribute("monthlist", month);*/
//		model.addAttribute("type", type);
		return "templates/weui/integral";
	}



}
