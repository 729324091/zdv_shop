package com.zdv.shop.controller;

import com.zdv.shop.common.Constant;
import com.zdv.shop.common.pojo.AjaxResult;
import com.zdv.shop.common.utils.StringUtils;
import com.zdv.shop.model.*;
import com.zdv.shop.service.*;
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
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author LBY
 * @data 2019年2月28日 
 */
@RequestMapping("/h5/cart/")
@Controller
public class H5CartController extends BaseController {
	
	@Autowired
	private UtOrderItemService utOrderItemService;
	@Autowired
	private OtCompToProductService otCompToProductService;
	@Autowired
	private OtProductService otProductService;
	@Autowired
	private DtDistributorService distributorService;
	@Autowired
	private OtCompProductStockService otCompProductStockService;
	@Autowired
	private UtUserfreeproductService userfreeproductService;
	@Autowired
	private UtUserService userService;
	@Value("${ucustomerid}")
	private String ucustomerid;
	@Value("${ucompid}")
	private String ucompid;

	
	@RequestMapping("/")
	public String view(@SessionAttribute(Constant.SESSION_H5USER) UtUsers user, Model model) {
		List<Map<String, Object>> itemList = utOrderItemService.queryCartOrderItem(user.getUuserid());
		BigDecimal totalPayprice = BigDecimal.ZERO;
		Integer uproductnum = 0;
		for (Map<String, Object> item : itemList) {
			totalPayprice = totalPayprice.add(new BigDecimal((Double)item.get("upayprice")/* * (Integer)item.get("uproductnum")*/));
			uproductnum += (Integer)item.get("uproductnum");
		}

		UtOrder order = new UtOrder();
		order.setUproductnum(uproductnum+"");
		//需支付价格
		order.setUpayprice(totalPayprice.doubleValue() + "");
		model.addAttribute("order", order);
		model.addAttribute("itemList", utOrderItemService.queryCartOrderItem(user.getUuserid()));
		return "templates/h5/cart";
	}



	@RequestMapping("we")
	public String weview(@SessionAttribute(Constant.SESSION_H5USER) UtUsers user, Model model) {
		List<Map<String, Object>> mapList = utOrderItemService.queryCartOrderItem2(user.getUuserid());

		BigDecimal totalPayprice = BigDecimal.ZERO;
		Integer uproductnum = 0;
		/*for (Map<String, Object> item : itemList) {
			totalPayprice = totalPayprice.add(new BigDecimal((Double)item.get("upayprice")*//* * (Integer)item.get("uproductnum")*//*));
			uproductnum += (Integer)item.get("uproductnum");
		}*/

		UtOrder order = new UtOrder();
		order.setUproductnum(uproductnum+"");
		//需支付价格
		order.setUpayprice(totalPayprice.doubleValue() + "");
		model.addAttribute("order", order);
		model.addAttribute("itemList", mapList.get(0));
		model.addAttribute("compname", mapList.get(1));
		return "templates/weui/cart";
	}
	
	/**
	 * 移除购物车
	 * @author LBY
	 * @data 2019年3月1日
	 * @param uorderitemid
	 * @return
	 */
	@RequestMapping("remove/{uorderitemid}")
	public String remove(@PathVariable("uorderitemid")String uorderitemid) {
		utOrderItemService.delById(uorderitemid);
		return "redirect:/h5/cart/";
	}
	@RequestMapping("weremove")
	@ResponseBody
	public AjaxResult weremove(String[] uorderitemid) {
		return utOrderItemService.delByUorderitemids(uorderitemid);
	}
	
	/**
	 * 加入购物车
	 * @author LBY
	 * @data 2019年3月1日
	 * @param ucomproductid 销售商关联产品ID
	 * @param orderItem
	 * @param quick 是否有商品规格
	 * @param user
	 * @param tocart 0为直接购买 1为到购物车
	 * @return
	 */
	@RequestMapping("add")
	@ResponseBody
	public AjaxResult add(
			String ucomproductid,
			UtOrderItem orderItem,
			Character quick,
			@SessionAttribute(Constant.SESSION_H5USER) UtUsers user,
			Model model,
			String[] spec,
			String[] uobjnamevalue, String tocart, HttpServletResponse response) {
		
		try {
			HttpServletRequest request = getRequest();
			HttpSession session = request.getSession();




			//用于查看购物车中是否已经存在该类型的商品
            UtOrderItem utOrderItem = new UtOrderItem();

            //查询出是否购买过免费商品或者未超过一个月
			OtCompToProduct otCompToProduct = otCompToProductService.queryByID(ucomproductid);
			//如果该商品是免费商品
			if(!otCompToProduct.getUpromotesales().equals("0") && !otCompToProduct.getUcomproductid().equalsIgnoreCase("95730749885657088")){
				UtUsers users = userService.queryByID(user.getUuserid());
				String uvip = user.getUvip();
				if (uvip.equals("0")) {
					return new AjaxResult(0, "该商品需VIP才可领取");
				}

				/***如果是免费商品则固定为一件****/
				orderItem.setUproductnum("1");
				//用于查询
				UtUserfreeproduct utUserfreeproduct = new UtUserfreeproduct();
				utUserfreeproduct.setUcustomerid(ucustomerid);
				utUserfreeproduct.setUcompid(ucompid);
				utUserfreeproduct.setUuserid(user.getUuserid());
				utUserfreeproduct.setUpromotesales(otCompToProduct.getUpromotesales());
				UtUserfreeproduct freeproduct = userfreeproductService.getLastFreeproduct(utUserfreeproduct);

				if (freeproduct != null) {
					//每月领取一次的商品
					if (otCompToProduct.getUpromotesales().equals("1")) {
						SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						Calendar c = Calendar.getInstance();
						c.setTime(new Date());
						c.add(Calendar.MONTH, -1);
						Date m = c.getTime();
						String mon = format.format(m);
						Long amonthago = date2TimeStamp(mon, "yyyy-MM-dd HH:mm:ss");
						String ucreatedate = freeproduct.getUcreatedate();


						BigDecimal month = new BigDecimal(amonthago).divide( new BigDecimal(1000));
						BigDecimal lasttime = new BigDecimal(ucreatedate);

						//如果超过一个月
						if (month.compareTo(lasttime) == 1) {


						}else{
							String date = timeStamp2Date(ucreatedate+"000", "yyyy-MM-dd HH:mm:ss");

							return new AjaxResult(0, "上次领取免费商品时间为" + date);
						}
						System.out.println("过去一个月："+mon);
					}else{

						return new AjaxResult(0, "您已经领取过一次VIP限领商品,不能重复领取");
					}

				}

			}

            //属性名与值
            utOrderItem.setUobjnamevalue(orderItem.getUobjnamevalue());
            //原商品id
            //utOrderItem.setUproductid(ucomproductid);
            //商家关联商品id
			utOrderItem.setUcomproductid(ucomproductid);
			utOrderItem.setUuserid(user.getUuserid());

			List<Map<String, Object>> list = new ArrayList<>();
			if (!StringUtils.objectIsNull(uobjnamevalue)) {

				if (uobjnamevalue.length == 1) {
					String[] split = uobjnamevalue[0].split(":");
					String uobjname = split[0];
					String uobjvalue = split[1];
					list = otCompProductStockService.queryByObjnamevalue(ucomproductid, uobjname, uobjvalue);
				} else if (uobjnamevalue.length == 2) {
					String[] split = uobjnamevalue[0].split(":");
					String uobjname = split[0];
					String uobjvalue = split[1];
					String[] split1 = uobjnamevalue[1].split(":");
					String uobjname1 = split1[0];
					String uobjvalue1 = split1[1];
					list = otCompProductStockService.queryByObjnamevalues(ucomproductid, uobjname, uobjvalue, uobjname1, uobjvalue1);
				}
			}

			if(list.size()>0){
				Map<String, Object> map = list.get(0);
				String uproductstockid = map.get("uproductstockid").toString();
				utOrderItem.setUproductstockid(uproductstockid);


			}


			//查询出存在的订单项集合
            List<UtOrderItem> utOrderItemList = utOrderItemService.queryCartItemList(utOrderItem);
            //若订单项存在
            if (utOrderItemList.size() == 1) {
            	//获取订单项
                UtOrderItem item = utOrderItemList.get(0);
                //若已存在相同的商品则更新数量

				//如果是直接购买则tocart==0
				if (tocart.equalsIgnoreCase("0")) {
					item.setUproductnum(Integer.parseInt(orderItem.getUproductnum()) + "");
				}else{
					item.setUproductnum(Integer.parseInt(orderItem.getUproductnum()) + Integer.parseInt(item.getUproductnum()) + "");
				}

				item.setUpayprice(item.getUprice()*Integer.parseInt(item.getUproductnum()));
				if(StringUtils.StringisNotEmpty(item.getUhomepic())){
                    String[] split = item.getUhomepic().split("#");
                    item.setUhomepic(split[0]);
                }



                /***如果是免费商品则固定为一件****/
				if (!otCompToProduct.getUpromotesales().equals("0")) {
					item.setUproductnum("1");
				}

                if (utOrderItemService.updateByID(item)>0) {
                    List<Map<String, Object>> maps = utOrderItemService.queryCartOrderItem(user.getUuserid());
                    return returnSuccess(item);
                }


            }


            OtProduct otProduct = new OtProduct();
            //获取商家销售商品信息
            OtCompToProduct ctp = otCompToProductService.queryByID(ucomproductid);
            if (ctp == null || ctp.getClass() == null) {
                //不存在商家定义
                otProduct = otProductService.queryByID(ucomproductid);
            }else{
                otProduct = otProductService.queryByID(ctp.getUproductid());
            }


			if (otProduct == null || otProduct.getClass() == null) {
				return returnFailed("商品已失效");
			}
            if (StringUtils.StringisNotEmpty(otProduct.getUdistributorid())) {

				DtDistributor dtDistributor = distributorService.queryByID(otProduct.getUdistributorid());
                orderItem.setUdname(dtDistributor.getUdname());	// 经销商名称


            }
			String uorderitemid = nextId();
			orderItem.setUorderitemid(uorderitemid);			// 订单项ID

			orderItem.setUuserid(user.getUuserid());		// 用户ID

			orderItem.setUcompid(ctp.getUcompid());			// 销售商ID

			orderItem.setUproductid(ctp.getUproductid());			// 商品ID

			orderItem.setUcomproductid(ctp.getUcomproductid());		//销售商商品id

			orderItem.setUdistributorid(ctp.getUdistributorid());

			orderItem.setUproductname(ctp.getUalias());		// 产品名称

			orderItem.setUdiscount("0");					// 折扣

			orderItem.setUmarketprice(ctp.getUmarketprice() != null ? ctp.getUmarketprice().doubleValue() : 0D);	// 市场价

            if (StringUtils.StringisNotEmpty(otProduct.getUhomepic())) {
                orderItem.setUhomepic(otProduct.getUhomepic().split("#")[0]);
            }

			orderItem.setUcompname(ctp.getUcompname());

			//不等于0是会员
			if (!user.getUvip().equalsIgnoreCase("0")) {
				//如果用户是会员
				orderItem.setUprice(ctp.getUvipprice().doubleValue());				// 价格

				orderItem.setUpayprice(ctp.getUvipprice().doubleValue());		//支付价格
			}else{
				orderItem.setUprice(ctp.getUprice().doubleValue());				// 价格
				orderItem.setUpayprice(ctp.getUprice().doubleValue());			// 支付价格
			}
			orderItem.setUcostprice(Double.parseDouble(ctp.getUcostprice() == null ? "0.0" : ctp.getUcostprice().toString()));	// 成本价
			orderItem.setUcosttype("1");					// 消费类型，1购买、0赠送
			orderItem.setUunit(ctp.getUunit());				// 产品单位
			orderItem.setUunitid(ctp.getUunitid() == null ? "0" : ctp.getUunitid());	// 产品单位ID
//			orderItem.setUobjnamevalue(uobjnamevalue == null ? "" : uobjnamevalue.toString());	// 属性值
			orderItem.setUpaystatus("0");					// 是否付款
			orderItem.setUprofit((orderItem.getUprice() == null ? BigDecimal.ZERO : new BigDecimal(orderItem.getUprice())).subtract(orderItem.getUcostprice() == null ? BigDecimal.ZERO : new BigDecimal(orderItem.getUcostprice())).doubleValue());	// 利润
			orderItem.setUcost(Double.parseDouble(ctp.getUcostprice() == null ? "0.0" : ctp.getUcostprice().toString())); 	// 成本
			orderItem.setUcreatedate(timeStamp() + "");

			//如果存在规格价格
			if (list.size() > 0) {
				Map<String, Object> map = list.get(0);
				orderItem.setUcost((Double) map.get("ucostprice"));
				orderItem.setUcostprice((Double) map.get("ucostprice"));
				orderItem.setUmarketprice((Double) map.get("umarketprice"));
				//设置库存id
				orderItem.setUproductstockid(map.get("uproductstockid").toString());

				if (!user.getUvip().equalsIgnoreCase("0")) {
					//会员
					orderItem.setUpayprice((Double) map.get("uvipprice"));
					orderItem.setUprice((Double) map.get("uvipprice"));
				} else {
					//非会员
					orderItem.setUpayprice((Double) map.get("uprice"));
					orderItem.setUprice((Double) map.get("uprice"));
				}
				//如果为促销状态
				if (StringUtils.StringisNotEmpty((String) map.get("usalesprice"))) {
					orderItem.setUpayprice((Double) map.get("usalesprice"));
					orderItem.setUprice((Double) map.get("usalesprice"));
				}
				orderItem.setUprofit((orderItem.getUprice() == null ? BigDecimal.ZERO : new BigDecimal(orderItem.getUprice())).subtract(orderItem.getUcostprice() == null ? BigDecimal.ZERO : new BigDecimal(orderItem.getUcostprice())).doubleValue());	// 利润

			}


			if (utOrderItemService.insert(orderItem) > 0) {
				List<Map<String, Object>> maps = utOrderItemService.queryCartOrderItem(user.getUuserid());

//				response.sendRedirect("/h5/order/wecheckout?uorderitemid=" + orderItem.getUorderitemid());

				return returnSuccess(orderItem);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return returnFailed("加入购物车失败");
	}

	@RequestMapping("changeNumber")
	@ResponseBody
	public AjaxResult changeNumber(String uproductnum,String uorderitemid ,@SessionAttribute(Constant.SESSION_H5USER) UtUsers user) {
		UtOrderItem utOrderItem = utOrderItemService.queryByID(uorderitemid);
		utOrderItem.setUproductnum(uproductnum);
		utOrderItem.setUpayprice(utOrderItem.getUprice() * Integer.parseInt(uproductnum));
		utOrderItemService.update(utOrderItem);
		List<Map<String, Object>> maps = utOrderItemService.queryCartOrderItem(user.getUuserid());
		HashMap<String, Object> orderitem = new HashMap<>();
		orderitem.put("uproductnum", uproductnum);
		//总价=市场价*数量
		BigDecimal totalprice = BigDecimal.ZERO;
		BigDecimal totalPayprice = BigDecimal.ZERO;
		Integer uproducttotalnum = 0;
		for (Map<String, Object> map : maps) {
			Integer num = (Integer) map.get("uproductnum");
			uproducttotalnum += num ;
			Double umarketprice = (Double) map.get("umarketprice");
			totalprice = totalprice.add(new BigDecimal(num * umarketprice));
			totalPayprice = totalPayprice.add(new BigDecimal((Double) map.get("upayprice")));
		}
		orderitem.put("uproducttotalnum", uproducttotalnum);
		orderitem.put("totalprice", totalprice);

		orderitem.put("totalPayprice", totalPayprice.doubleValue()+"");
		return returnSuccess(orderitem);
	}


}
