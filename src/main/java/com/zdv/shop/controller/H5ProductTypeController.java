package com.zdv.shop.controller;

import com.zdv.shop.common.Constant;
import com.zdv.shop.common.utils.StringUtils;
import com.zdv.shop.model.OtCompProductStock;
import com.zdv.shop.model.OtProducttype;
import com.zdv.shop.model.UtUsers;
import com.zdv.shop.model.po.OtCompToProductPO;
import com.zdv.shop.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 产品分类Controller
 * @author LBY
 * @data 2019年3月4日 
 */
@RequestMapping("/h5/category/")
@Controller
public class H5ProductTypeController extends BaseController {

	@Autowired
	private OtProducttypeService otProducttypeService;
	@Autowired
	private OtCompToProductService compToProductService;

	@Autowired
	private OtCompProductStockService compProductStockService;

	@Autowired
	private OtCompProductTypeService compProductTypeService;
	@Autowired
	private UtOrderItemService utOrderItemService;

	@Value("${publicURL}") //在配置文件中
	private String publicurl;
	@Value("${ucustomerid}")
	private String ucustomerid;

	@Autowired
	private OtProductService productService;
	@RequestMapping("/")
	public String view(Model model) {
		//返回产品表的父信息，父信息包含所有子信息
		//关联商户号获取(存在问题 )
//		List<OtProducttype> queryProductTypeListWithH5 =compProductTypeService.queryComProductTypeListWithH5(ucustomerid);


		//直接从产品库获取
		List<OtProducttype> queryProductTypeListWithH5 = otProducttypeService.queryProductTypeListWithH5();
		model.addAttribute("typeList",queryProductTypeListWithH5);
		return "templates/h5/category";
	}
	@RequestMapping("weui")
	public String categoryview(Model model,String uproducttypeid) {
		//返回产品表的父信息，父信息包含所有子信息
		//关联商户号获取(存在问题 )
//		List<OtProducttype> queryProductTypeListWithH5 =compProductTypeService.queryComProductTypeListWithH5(ucustomerid);

		//从商家分类获取
		List<OtProducttype> typeListWithH5 =compProductTypeService.queryComProductTypeListWithH5(ucustomerid);
		//直接从产品库获取
		List<OtProducttype> queryProductTypeListWithH5 = otProducttypeService.queryProductTypeListWithH5();


		if (typeListWithH5 != null && typeListWithH5.size() > 0) {
			queryProductTypeListWithH5 = typeListWithH5;
		}




		model.addAttribute("typeList",queryProductTypeListWithH5);
		int count = 0;
		HttpSession session = getRequest().getSession();
		UtUsers user = (UtUsers) session.getAttribute(Constant.SESSION_H5USER);
		if(user !=null) {
			List<Map<String, Object>> mapList = utOrderItemService.queryCartOrderItem(user.getUuserid());
			for (Map<String, Object> map : mapList) {
				count++;
			}
		}
		System.out.println(count+"----------");
		model.addAttribute("uproducttypeid", uproducttypeid);


		model.addAttribute("cartCount", count);
		return "templates/weui/class";
	}

	@RequestMapping("goods/{uproducttypeid}")
	public String goods(Model model ,@PathVariable("uproducttypeid")String uproducttypeid) {

		List<Map<String, Object>> maps = compToProductService.queryProductList(uproducttypeid, ucustomerid);

		for (Map<String, Object> map : maps) {
			String uhomepic = (String) map.get("uhomepic");
			if (StringUtils.StringisNotEmpty(uhomepic)) {
			String[] split = uhomepic.split("#");
				map.put("uhomepic", split[0]);
			}
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
		model.addAttribute("uproducttypeid", "uproducttypeid="+uproducttypeid);
		model.addAttribute("ucustomerid", ucustomerid);
		return "templates/h5/category_goods";
	}


	@RequestMapping("goods/")
	public String allgoods(Model model,String uproducttypeid ) {

		List<Map<String, Object>> maps = compToProductService.queryProductList(null, ucustomerid);

		for (Map<String, Object> map : maps) {
			String uhomepic = (String) map.get("uhomepic");
			if (StringUtils.StringisNotEmpty(uhomepic)) {
				String[] split = uhomepic.split("#");
				map.put("uhomepic", split[0]);
			}
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
		model.addAttribute("uproducttypeid", "uproducttypeid="+uproducttypeid);
		model.addAttribute("ucustomerid", ucustomerid);
		return "templates/h5/category_goods";
	}


	@RequestMapping("goods/type")
	public String goods(Model model ,String uproducttypeid,String type,String keywords) {
		HttpSession session = getRequest().getSession();
		List<OtCompToProductPO> maps = new ArrayList<OtCompToProductPO>();
		String url = "";
		if(StringUtils.StringisNotEmpty(uproducttypeid) && !StringUtils.StringisNotEmpty(keywords)) {
			//uproducttypeid不为空，且keywords为空
			 maps =  compToProductService.queryProductListAndSort(uproducttypeid,type,ucustomerid);
			 url = "uproducttypeid="+uproducttypeid;
		}else {
			List<String> searchlist = (List<String>) session.getAttribute("search-history");

			if (searchlist != null) {
				searchlist.remove(keywords);
			}else{
				searchlist = new ArrayList<>();
			}
			searchlist.add(keywords);
			session.setAttribute("search-history", searchlist);


			maps =compToProductService.queryProductListByUaliasAndSort(keywords,type,ucustomerid);
			 url = "keywords="+keywords;
		}
		
		for (OtCompToProductPO map : maps) {
			String uhomepic = (String) map.getUhomepic();
			if (StringUtils.StringisNotEmpty(uhomepic)) {
				String[] split = uhomepic.split("#");
				map.setUhomepic(split[0]);
			}
			String ucomproductid = map.getUcomproductid();
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
					if (Double.parseDouble(stock.getUprice()) < min || min == 0.00) {
						min = Double.parseDouble(stock.getUprice());
						marketprice = Double.parseDouble(stock.getUmarketprice());
						vipprice = Double.parseDouble(stock.getUvipprice());
					}
				}
				map.setUprice(BigDecimal.valueOf(min));
				map.setUmarketprice(BigDecimal.valueOf(marketprice));
				map.setUvipprice(BigDecimal.valueOf(vipprice));
				map.setUstock(total);

			}
			}
		model.addAttribute("type", type);
		model.addAttribute("goods", maps);
		//价格，销量等url参数字段。
		model.addAttribute("uproducttypeid", url);
		return "templates/h5/category_goods";
	}


}
