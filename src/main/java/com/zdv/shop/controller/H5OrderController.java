package com.zdv.shop.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zdv.shop.common.Constant;
import com.zdv.shop.common.annotation.Authority;
import com.zdv.shop.common.annotation.ControllerLog;
import com.zdv.shop.common.pojo.AjaxResult;
import com.zdv.shop.common.utils.HttpRequest;
import com.zdv.shop.common.utils.KeyId;
import com.zdv.shop.common.utils.MD5;
import com.zdv.shop.jiguang.PushMessage;
import com.zdv.shop.model.*;
import com.zdv.shop.service.*;
import com.zdv.shop.weixinh5.controller.RefundBuilder;
import com.zdv.shop.weixinh5.model.RefundResult;
import com.zdv.shop.weixinh5.sdk.WXPayUtil;
import com.zdv.shop.weixinh5.util.PayCommonUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.net.ssl.SSLContext;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.security.KeyStore;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/h5/order/")
@Controller
public class H5OrderController extends BaseController {
    @Autowired
    private CtWxpayConfigService wxpayConfigService;
    @Autowired
    private UtOrderService orderService;
    @Autowired
    private UtOrderItemService orderItemService;
    @Autowired
    private UtTakeAddressService addressService;
    @Autowired
    private OtCompProductStockService compProductStockService;
    @Autowired
    private OtCompToProductService compToProductService;
    @Autowired
    private UtOrderexpressService orderexpressService;
    @Autowired
    private CtOpUserToCompService opuserToCompService;
    @Autowired
    private CtProfitService profitService;
    @Autowired
    private DtOpUserToDistributorService opuserToDistributorService;
    @Autowired
    private HttpSession session;
    @Autowired
    private UtUserService userService;
    @Value("${API.docBase}")
    private String docBase;
    @Value("${ucompid}")
    private String ucompid;
    @Value("${ucustomerid}")
    private String ucustomerid;
    /*@RequestMapping("addOrder")
    public String addOrder(UtOrder order, @SessionAttribute(Constant.SESSION_H5USER)UtUsers utUsers, String[] uorderitemid,
                           Model model) {
        String uorderitemids = StringUtils.join(uorderitemid, ",");
        //查询出订单中的所有订单项
        List<UtOrderItem> orderItems = orderItemService.queryOrderItem(uorderitemids);
        List<UtOrder> orderList = new ArrayList<>();
        List<UtOrderItem> itemList = new ArrayList<>();
        for (UtOrderItem orderItem : orderItems) {
        Boolean flag = false; //判断是否存在
            //取出订单项中的商家编号
            String ucompid = orderItem.getUcompid();
            //先判断是否为第一次创建
            if (orderList.size() > 0) {
                //遍历本次创建的订单
                for (UtOrder utOrder : orderList) {
                    //如果订单的商家相同则合并
                    if (utOrder.getUcompid().equals(ucompid)) {
                        //存在相同定订单
                        flag=true;
                    }
                    //合并
                    if (flag) {
                        //商品数量
                        utOrder.setUproductnum(Integer.parseInt(utOrder.getUproductnum())+Integer.parseInt(orderItem.getUproductnum())+"");
                        //设置总价格
                        utOrder.setUtotalprice(Double.parseDouble(utOrder.getUtotalprice())+(orderItem.getUmarketprice()*Integer.parseInt(orderItem.getUproductnum()))+"");
                        //设置 支付价格
                        utOrder.setUpayprice(Double.parseDouble(utOrder.getUpayprice())+orderItem.getUpayprice()+"");
                        //合并成本价
                        utOrder.setUcostprice(utOrder.getUcostprice()+orderItem.getUcostprice());

                        orderItem.setUorderid(utOrder.getUorderid());
                        itemList.add(orderItem);
                        //跳出循环
                        break;
                    }
                }
            }
            //不存在订单
            if(!flag){

                UtOrder utOrder = new UtOrder();
                //付款方式
                utOrder.setUpaytype(order.getUpaytype());
                //地址
                utOrder.setUaddress(order.getUaddress());
                //配送方式
                utOrder.setUsend_type(order.getUsend_type());
                //联系人
                utOrder.setUcontact(order.getUcontact());
                //电话
                utOrder.setUtel(order.getUtel());
                //备注
                utOrder.setUremark(order.getUremark());


                utOrder.setUshoptype("0");


                //设置订单id
                utOrder.setUorderid(KeyId.nextId());
                //设置订单号
                utOrder.setUorderno(getOrderDateNo());
                //设置产品名称
                utOrder.setUproductname("不需要");
                //设置商品数量
                utOrder.setUproductnum(orderItem.getUproductnum());
                //设置总价格(市场价*数量)
                utOrder.setUtotalprice(orderItem.getUmarketprice()*Integer.parseInt(orderItem.getUproductnum())+"");
                //设置支付价格
                utOrder.setUpayprice(orderItem.getUpayprice()+"");
                //成本价
                utOrder.setUcostprice(orderItem.getUcostprice()+"");
                //支付状态
                utOrder.setUpaystate("0");
                //支付类型已携带
                //设置购买用户id
                utOrder.setUuserid(utUsers.getUuserid());
                //设置购买用户名
                utOrder.setUname(utUsers.getUname());

                //设置商家id
                utOrder.setUcompid(orderItem.getUcompid());
               utOrder.setUcompname(order.getUcompname());
                //是否处理
                utOrder.setUsolve("0");
                //是否需要爬楼？
                utOrder.setUcrawl("0");
                //设置状态
                utOrder.setUeflag("00");
                //logo
                utOrder.setUlogo(utUsers.getUlogo());
                //商家地址
                utOrder.setUcaddress(orderItem.getUcaddress());
                //商家联系人
                utOrder.setUccontact(orderItem.getUccontact());
                //商家电话
                utOrder.setUctel(orderItem.getUctel());
                //商家logo
                utOrder.setUclogo(orderItem.getUclogo());


                UtOrderItem utOrderItem = new UtOrderItem();
                //订单项id
                utOrderItem.setUorderitemid(orderItem.getUorderitemid());
                //设置订单id
                utOrderItem.setUorderid(utOrder.getUorderid());
//                orderItemService.updateByID(utOrderItem);
                itemList.add(utOrderItem);
                orderList.add(utOrder);
            }
        }
        //添加新的订单以及更新订单项
        AjaxResult result = orderService.addOrderList(orderList,itemList);
        BigDecimal totalPayprice = BigDecimal.ZERO;
        for (UtOrder utOrder : orderList) {
            totalPayprice =totalPayprice.add(new BigDecimal(Double.parseDouble(utOrder.getUpayprice())));
        }
        if (result.getRetcode() == 1) {
            model.addAttribute("totalPayprice", totalPayprice.doubleValue() + "");
            return "templates/h5/done";
        }else{
            return new String("redirect:checkout");
        }

    }*/



    @RequestMapping("delOrder/{uorderid}")
    public String delOrder(@PathVariable("uorderid")String uorderid,@SessionAttribute(Constant.SESSION_H5USER)UtUsers utUsers) {
        try {
            orderService.delOrder(uorderid, utUsers.getUuserid());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new String("redirect:/h5/order/nopayList");
    }

    @ControllerLog("生成订单")
    @RequestMapping("checkout")
    public String checkout(@SessionAttribute(Constant.SESSION_H5USER)UtUsers utUsers,Model model) {
        List<UtTakeAddress> utTakeAddresses = addressService.queryByUserid(utUsers.getUuserid());
        if (utTakeAddresses.size() < 1) {
            model.addAttribute("addresstype", "1");
            return "templates/h5/address_list";
        }else{
             String addressid = (String) session.getAttribute("selectAddressid");
             //如果不为空则选择过地址

            UtTakeAddress address = new UtTakeAddress();
            address.setUuserid(utUsers.getUuserid());
            address.setUdefault(true);
            UtTakeAddress utTakeAddress = addressService.queryOne(address);
            if (StringUtils.isNotEmpty(addressid)) {
                utTakeAddress = addressService.queryByID(addressid);
            }
            model.addAttribute("utTakeAddress", utTakeAddress);


            List<Map<String, Object>> maps = orderItemService.queryCartOrderItem(utUsers.getUuserid());
            BigDecimal totalPayprice = BigDecimal.ZERO;
            Integer uproductnum = 0;
            BigDecimal totalprice = BigDecimal.ZERO;
            model.addAttribute("flag", true);
            for (Map<String, Object> map : maps) {
                totalPayprice = totalPayprice.add(new BigDecimal((Double)map.get("upayprice")/* * (Integer)item.get("uproductnum")*/));
                Integer num = (Integer) map.get("uproductnum");
                uproductnum += num;
                totalprice = totalprice.add(new BigDecimal((Double)map.get("uprice")*(Integer)map.get("uproductnum")));
                //获得订单项的库存id
                Object stockid = map.get("uproductstockid");
                map.put("udesc", "");

                String ucomproductid = map.get("ucomproductid").toString();

                //判断是否存在规格库存
                if (!com.zdv.shop.common.utils.StringUtils.objectIsNull(stockid)) {
                    String uproductstockid = stockid.toString();
                    //存在
                    OtCompProductStock compProductStock = compProductStockService.queryByID(uproductstockid);
                    //获得商品库存
                    Integer ustock = Integer.valueOf(compProductStock.getUstock());
                    //如果商品库存小于要用户希望购买的数量
                    if (num > ustock) {
                        map.put("udesc", "商品库存不足,当前库存为"+ustock);
                        model.addAttribute("flag", false);
                    }
                }else{
                    //查询商品库存
                    OtCompToProduct otCompToProduct = compToProductService.queryByID(ucomproductid);
                    Integer ustock = Integer.valueOf(otCompToProduct.getUstock());
                    if (num > ustock) {
                        map.put("udesc", "商品库存不足,当前库存为"+ustock);
                        model.addAttribute("flag", false);

                    }
                }

            }
            UtOrder order = new UtOrder();
            order.setUproductnum(uproductnum+"");
            //需支付价格
            order.setUpayprice(totalPayprice.doubleValue() + "");
            order.setUtotalprice(totalprice.doubleValue()+"");
            model.addAttribute("order", order);
            model.addAttribute("orderitem", maps);
        }
/*
        if (StringUtils.isNotEmpty(utakeaddressid)) {
            UtTakeAddress utTakeAddress = addressService.queryByID(utakeaddressid);
            model.addAttribute("utTakeAddress", utTakeAddress);
        }*/

        return "templates/h5/checkout";
    }








    @ControllerLog("生成订单")
    @RequestMapping("wecheckout")
    public String wecheckout(@SessionAttribute(Constant.SESSION_H5USER)UtUsers utUsers,Model model,String[] uorderitemid,String selectAddressid) {
        HttpSession session = getRequest().getSession();

        if (com.zdv.shop.common.utils.StringUtils.objectIsNull(uorderitemid) || uorderitemid.length == 0) {
            uorderitemid = (String[]) session.getAttribute("uorderitemid");
            if (uorderitemid == null) {
                return "redirect:/h5/cart/we";
            }
        }
        List<Map<String, Object>> maps = orderItemService.queryOrderItemListByUorderitemids(uorderitemid);
        //判断是否需要地址
        Boolean needAddress = true;
        for (Map<String, Object> map : maps) {
            //如果是一元产品
            /*if (map.get("ucomproductid").toString().equalsIgnoreCase("95730749885657088")) {
                needAddress = false;

            }*/
        }

        List<UtTakeAddress> utTakeAddresses = addressService.queryByUserid(utUsers.getUuserid());
        //拼接返回链接
        String url = "/h5/order/wecheckout";
        for (int i = 0; i < uorderitemid.length; i++) {
            if (i == 0) {
                url += "?";
            }else{
                url += "&";
            }
            url += "uorderitemid=" + uorderitemid[i];
        }
        session.setAttribute("uorderitemid", uorderitemid);
        model.addAttribute("url", url);
        //地址数量小于1或者需要地址
        if (utTakeAddresses.size() < 1 && needAddress) {
            model.addAttribute("addresstype", "1");
            return "templates/weui/address_list";
        }else{
            if (needAddress) {
                UtTakeAddress address = new UtTakeAddress();
                address.setUuserid(utUsers.getUuserid());
                //获取所有的地址
                List<UtTakeAddress> queryList = addressService.queryList(address);
                address.setUdefault(true);

                UtTakeAddress utTakeAddress = addressService.queryOne(address);
                //如果有默认地址
                if (utTakeAddress != null) {
                    model.addAttribute("utTakeAddress", utTakeAddress);
                }else{
                    model.addAttribute("utTakeAddress", queryList.get(0));
                }
                if (StringUtils.isNotEmpty(selectAddressid)) {
                    UtTakeAddress query = addressService.queryByID(selectAddressid);
                    if (query.getUuserid().equals(utUsers.getUuserid())) {
                        model.addAttribute("utTakeAddress",query);
                    }
                }
            }
            model.addAttribute("needAddress",needAddress);
//            List<Map<String, Object>> maps = orderItemService.queryCartOrderItem(utUsers.getUuserid());
            BigDecimal totalPayprice = BigDecimal.ZERO;
            Double postage = 0.00;

            Integer uproductnum = 0;
            BigDecimal totalprice = BigDecimal.ZERO;
            model.addAttribute("flag", true);
            for (Map<String, Object> map : maps) {
                String uhomepic = (String) map.get("uhomepic");
                if (StringUtils.isNotEmpty(uhomepic)) {

                    String[] split = uhomepic.split("#");
                    uhomepic = split[0];
                    map.put("uhomepic", uhomepic);
                    String[] test = uhomepic.split("#");
                }

                totalPayprice = totalPayprice.add(new BigDecimal((Double)map.get("upayprice")/* * (Integer)item.get("uproductnum")*/));
                if (!map.get("upromotesales").equals("0")) {
                    postage += (Double) map.get("uprice");
                }
                Integer num = (Integer) map.get("uproductnum");
                uproductnum += num;
                totalprice = totalprice.add(new BigDecimal((Double)map.get("uprice")*(Integer)map.get("uproductnum")));
                //获得订单项的库存id
                Object stockid = map.get("uproductstockid");
                map.put("udesc", "");

                String ucomproductid = map.get("ucomproductid").toString();


                //判断是否存在规格库存
                if (!com.zdv.shop.common.utils.StringUtils.objectIsNull(stockid)) {
                    String uproductstockid = stockid.toString();
                    //存在
                    OtCompProductStock compProductStock = compProductStockService.queryByID(uproductstockid);
                    //获得商品库存
                    Integer ustock = Integer.valueOf(compProductStock.getUstock());
                    //如果商品库存小于要用户希望购买的数量
                    if (num > ustock) {
                        map.put("udesc", "商品库存不足,当前库存为"+ustock);
                        model.addAttribute("flag", false);
                    }
                }else{
                    //查询商品库存
                    OtCompToProduct otCompToProduct = compToProductService.queryByID(ucomproductid);
                    Integer ustock = Integer.valueOf(otCompToProduct.getUstock());
                    if (num > ustock) {
                        map.put("udesc", "商品库存不足,当前库存为"+ustock);
                        model.addAttribute("flag", false);

                    }
                }

            }
            UtOrder order = new UtOrder();
            order.setUproductnum(uproductnum+"");
            //需支付价格
            order.setUpayprice(totalPayprice.doubleValue() + "");
            order.setUtotalprice(totalprice.doubleValue()+"");
            CtProfit ctProfit = profitService.queryProfitByCompId(ucompid, ucustomerid);
            if (ctProfit != null && ctProfit.getUintegral() != null && ctProfit.getUintegral() > 0) {
                Integer uintegral = ctProfit.getUintegral();
                if (uintegral != null && uintegral > 0) {
                    uintegral = (int)totalprice.doubleValue() / uintegral;
                    model.addAttribute("uintegral", uintegral);
                }
            }
            model.addAttribute("order", order);
            model.addAttribute("postage", postage);
            model.addAttribute("orderitem", maps);
        }
/*
        if (StringUtils.isNotEmpty(utakeaddressid)) {
            UtTakeAddress utTakeAddress = addressService.queryByID(utakeaddressid);
            model.addAttribute("utTakeAddress", utTakeAddress);
        }*/

        return "templates/weui/order_info2";
    }



    @RequestMapping("orderList")
    public String orderList(UtOrder order, @SessionAttribute(Constant.SESSION_H5USER) UtUsers utUsers, Model model) {

        order.setUuserid(utUsers.getUuserid());
        List<Map<String,Object>> maps = orderService.queryOrderList(order);


        model.addAttribute("orderlist", maps);

        return "templates/h5/order_list";
    }


    @RequestMapping("nopayList")
    public String nopayList(UtOrder order, @SessionAttribute(Constant.SESSION_H5USER) UtUsers utUsers, Model model) {
        order.setUuserid(utUsers.getUuserid());
        order.setUeflag("00");
        List<Map<String,Object>> maps = orderService.queryOrderList(order);
        model.addAttribute("orderlist", maps);
        return "templates/h5/order_list";
    }

    @RequestMapping("waitGoods")
    public String waitGoods(UtOrder order, @SessionAttribute(Constant.SESSION_H5USER) UtUsers utUsers, Model model) {
        order.setUuserid(utUsers.getUuserid());
        order.setUeflag("01");
        List<Map<String,Object>> maps = orderService.queryOrderList(order);
        order.setUeflag("02");
        List<Map<String,Object>> maps2 = orderService.queryOrderList(order);
        for (Map<String, Object> ordermap : maps2) {
            maps.add(ordermap);
        }

        model.addAttribute("orderlist", maps);
        return "templates/h5/order_list";
    }
    @RequestMapping("estimate")
    public String estimate(UtOrder order, @SessionAttribute(Constant.SESSION_H5USER) UtUsers utUsers, Model model) {
        order.setUuserid(utUsers.getUuserid());
        order.setUeflag("03");
        List<Map<String,Object>> maps = orderService.queryOrderList(order);
        order.setUeflag("04");
        List<Map<String,Object>> maps2 = orderService.queryOrderList(order);
        for (Map<String, Object> ordermap : maps2) {
            maps.add(ordermap);
        }
        model.addAttribute("orderlist", maps);
        return "templates/h5/order_list";
    }



    @RequestMapping("orderdetail")
    public String orderdetail(UtOrder order,UtOrderItem orderItem, @SessionAttribute(Constant.SESSION_H5USER) UtUsers utUsers, Model model) {

        order.setUuserid(utUsers.getUuserid());

        List<Map<String, Object>> maps = orderService.queryOrderList(order);
        if (maps.size() > 0) {
            Map<String, Object> utOrder = maps.get(0);
            model.addAttribute("order", utOrder);
        }
        orderItem.setUuserid(utUsers.getUuserid());
        List<UtOrderItem> orderItems = orderItemService.queryOrderItemList(orderItem);

        model.addAttribute("itemlist", orderItems);

        return "templates/h5/order_detail";
    }


    /**
     * @param uorderid
     * 确认收货,并跳转至评论页面
     *
     * @return
     */
    @RequestMapping("completeOrder/{uorderid}")
    public String completeOrder(@PathVariable("uorderid") String uorderid , @SessionAttribute(Constant.SESSION_H5USER) UtUsers utUsers, Model model) {
        UtOrder utOrder = new UtOrder();
        utOrder.setUorderid(uorderid);
        utOrder.setUuserid(utUsers.getUuserid());
        utOrder.setUeflag("03");
        orderService.updateOrder(utOrder);

        UtOrder order = new UtOrder();
        order.setUuserid(utUsers.getUuserid());
        order.setUeflag("03");
        List<Map<String,Object>> maps = orderService.queryOrderList(order);
        order.setUeflag("04");
        List<Map<String,Object>> maps2 = orderService.queryOrderList(order);
        for (Map<String, Object> ordermap : maps2) {
            maps.add(ordermap);
        }
        model.addAttribute("orderlist", maps);
        return "templates/h5/order_list";
    }
    @RequestMapping("wecompleteOrder/{uorderid}")
    public String wecompleteOrder(@PathVariable("uorderid") String uorderid , @SessionAttribute(Constant.SESSION_H5USER) UtUsers utUsers, Model model) {
        UtOrder utOrder = new UtOrder();
        utOrder.setUorderid(uorderid);
        utOrder.setUuserid(utUsers.getUuserid());
        utOrder.setUeflag("03");
        orderService.updateOrder(utOrder);
        UtOrder order = new UtOrder();
        order.setUuserid(utUsers.getUuserid());
        order.setUeflag("03");
        List<Map<String,Object>> maps = orderService.queryOrderList(order);
        order.setUeflag("04");
        List<Map<String,Object>> maps2 = orderService.queryOrderList(order);
        for (Map<String, Object> ordermap : maps2) {
            maps.add(ordermap);
        }
        model.addAttribute("orderlist", maps);
        return "templates/weui/order";
    }


    @RequestMapping("weuiOrderList")
    public String weuiOrderList(String active,UtOrder order, @SessionAttribute(Constant.SESSION_H5USER) UtUsers utUsers, Model model) {

        order.setUuserid(utUsers.getUuserid());
        if (!StringUtils.isNotEmpty(active)) {
            active = "all";
        }
        if (active.equalsIgnoreCase("dfk")) {
            order.setUeflag("00");
        } else if (active.equalsIgnoreCase("dfh")) {
            order.setUeflag("01");
        } else if (active.equalsIgnoreCase("dsh")) {
            order.setUeflag("02");
        } else if (active.equalsIgnoreCase("dpj")) {
            order.setUeflag("03");
        }
        List<Map<String,Object>> maps = orderService.queryOrderList(order);


        model.addAttribute("orderlist", maps);
        model.addAttribute("active", active);
        return "templates/weui/order";
    }


    @RequestMapping("weaddOrder")
    public String weaddOrder(UtOrder order, @SessionAttribute(Constant.SESSION_H5USER)UtUsers utUsers, String[] uorderitemid,
                             Model model) {
        String uorderitemids = StringUtils.join(uorderitemid, ",");
        //查询出订单中的所有订单项
        List<UtOrderItem> orderItems = orderItemService.queryOrderItem(uorderitemids);
        List<UtOrder> orderList = new ArrayList<>();
        List<UtOrderItem> itemList = new ArrayList<>();
        List<UtUserfreeproduct> freeList = new ArrayList<>();
        for (UtOrderItem orderItem : orderItems) {
            //不是普通商品是会员商品
            if (!orderItem.getUpromotesales().equals("0")) {
                UtUserfreeproduct utUserfreeproduct = new UtUserfreeproduct();
                utUserfreeproduct.setUuserfreeproductid(nextId());
                utUserfreeproduct.setUpromotesales(orderItem.getUpromotesales());
                utUserfreeproduct.setUuserid(utUsers.getUuserid());
                utUserfreeproduct.setUorderitemid(orderItem.getUorderitemid());
                utUserfreeproduct.setUcomproductid(orderItem.getUcomproductid());
                utUserfreeproduct.setUcompid(ucompid);
                utUserfreeproduct.setUcustomerid(ucustomerid);
                utUserfreeproduct.setUproductid(orderItem.getUproductid());
                freeList.add(utUserfreeproduct);
            }


            Boolean flag = false; //判断是否存在
            //取出订单项中的商家编号
            String ucompid = orderItem.getUcompid();
            String udistributorid = orderItem.getUdistributorid();
            //先判断是否为第一次创建
            if (orderList.size() > 0) {
                //遍历本次创建的订单
                for (UtOrder utOrder : orderList) {
                    //如果订单的商家相同则合并
                    if (utOrder.getUcompid().equals(ucompid) && utOrder.getUdistributorid().equals(udistributorid)) {
                        //存在相同定订单
                        flag=true;
                    }/*else {
                    	List<Map<String, Object>> opcomplist = opuserToCompService.opusercomplistByCompid(utOrder.getUcompid());
                    	for (Map<String, Object> opcomp : opcomplist) 
                    	//不相同 发送推送消息
                    	(new PushMessage()).pushmessagejxs(opcomp.get("uopuserid")+"",opcomp.get("uopuserid")+"","您有新的订单信息",order.getUcontact()+"下单了,请查看");
                    	//给相关联的产品经销商发信息
                    	List<Map<String, Object>> opdistrlist = opuserToDistributorService.opuserDistributorBycomproductid(orderItem.getUcomproductid());
                    	for (Map<String, Object> opdistr : opdistrlist) 
                        	//不相同 发送推送消息
                        	(new PushMessage()).pushmessagejxs(opdistr.get("uopuserid")+"",opdistr.get("uopuserid")+"","您有新的订单信息",order.getUcontact()+"下单了,请查看");
                    }*/
                    
                    //合并
                    if (flag) {
                        //商品数量
                        utOrder.setUproductnum(Integer.parseInt(utOrder.getUproductnum())+Integer.parseInt(orderItem.getUproductnum())+"");
                        //设置总价格
                        utOrder.setUtotalprice(Double.parseDouble(utOrder.getUtotalprice())+(orderItem.getUprice()*Integer.parseInt(orderItem.getUproductnum()))+"");
                        //设置 支付价格
                        utOrder.setUpayprice(Double.parseDouble(utOrder.getUpayprice()) + orderItem.getUpayprice() * Integer.parseInt(orderItem.getUproductnum()) + "");
                        //合并成本价
                        utOrder.setUcostprice(Double.parseDouble(utOrder.getUcostprice()) + orderItem.getUcostprice() * Integer.parseInt(orderItem.getUproductnum())+"");

                        orderItem.setUorderid(utOrder.getUorderid());
                        if (StringUtils.isNotEmpty(orderItem.getUhomepic())) {
                            orderItem.setUhomepic(orderItem.getUhomepic().split("#")[0]);
                        }

                        itemList.add(orderItem);
                        //跳出循环
                        break;
                    }
                }
            }
            //不存在订单
            if(!flag){

                UtOrder utOrder = new UtOrder();
                //付款方式
                utOrder.setUpaytype(order.getUpaytype());
                //地址
                utOrder.setUaddress(order.getUaddress());
                //配送方式
                utOrder.setUsend_type(order.getUsend_type());
                //联系人
                utOrder.setUcontact(order.getUcontact());
                //电话
                utOrder.setUtel(order.getUtel());
                //备注
                utOrder.setUremark(order.getUremark());

                utOrder.setUcustomerid(ucustomerid);

                utOrder.setUshoptype("0");
                utOrder.setUpaytype("0");


                //设置订单id
                utOrder.setUorderid(KeyId.nextId());
                //设置订单号
                utOrder.setUorderno(getOrderDateNo());
                //设置产品名称
                utOrder.setUproductname("商城下单");
                //设置商品数量
                utOrder.setUproductnum(orderItem.getUproductnum());
                //设置总价格(销售价*数量)
                utOrder.setUtotalprice(orderItem.getUprice()*Integer.parseInt(orderItem.getUproductnum())+"");
                //设置支付价格
                utOrder.setUpayprice(orderItem.getUpayprice()*Integer.parseInt(orderItem.getUproductnum())+"");
                //成本价
                utOrder.setUcostprice(orderItem.getUcostprice()*Integer.parseInt(orderItem.getUproductnum())+"");
                //支付状态
                utOrder.setUpaystate("0");
                //支付类型已携带
                //设置购买用户id
                utOrder.setUuserid(utUsers.getUuserid());
                //设置购买用户名
                utOrder.setUname(utUsers.getUname());

                //设置商家id
                utOrder.setUcompid(orderItem.getUcompid());
                utOrder.setUcompname(orderItem.getUcompname());
                //是否处理
                utOrder.setUsolve("0");
                //是否需要爬楼？
                utOrder.setUcrawl("0");
                //设置状态
                utOrder.setUeflag("00");
                //logo
                utOrder.setUlogo(utUsers.getUlogo());
                //商家地址
                utOrder.setUcaddress(orderItem.getUcaddress());
                //商家联系人
                utOrder.setUccontact(orderItem.getUccontact());
                //商家电话
                utOrder.setUctel(orderItem.getUctel());
                //商家logo
                utOrder.setUclogo(orderItem.getUclogo());

                utOrder.setUdistributorid(orderItem.getUdistributorid());

                UtOrderItem utOrderItem = new UtOrderItem();
                //订单项id
                utOrderItem.setUorderitemid(orderItem.getUorderitemid());
                //设置订单id
                utOrderItem.setUorderid(utOrder.getUorderid());
                utOrderItem.setUcomproductid(orderItem.getUcomproductid());
//                orderItemService.updateByID(utOrderItem);
                utOrderItem.setUproductstockid(orderItem.getUproductstockid());
                utOrderItem.setUproductnum(orderItem.getUproductnum());


                itemList.add(utOrderItem);
                orderList.add(utOrder);
            }



        }
        //添加新的订单以及更新订单项
        AjaxResult result = orderService.addOrderList(orderList,itemList,freeList);
        BigDecimal totalPayprice = BigDecimal.ZERO;
        for (UtOrder utOrder : orderList) {
            //推送消息
            List<Map<String, Object>> opcomplist = opuserToCompService.opusercomplistByCompid(utOrder.getUcompid());
            for (Map<String, Object> opcomp : opcomplist)
                //不相同 发送推送消息
                (new PushMessage()).pushmessagelsd(opcomp.get("uopuserid")+"",opcomp.get("uopuserid")+"","您有新的订单信息",order.getUcontact()+"下单了,请查看");
            //给相关联的产品经销商发信息
//            List<Map<String, Object>> opdistrlist = opuserToDistributorService.opuserDistributorBycomproductid(utOrder.getUcomproductid());
            if (StringUtils.isNotEmpty(utOrder.getUdistributorid())) {


                List<Map<String, Object>> opdistrlist = opuserToDistributorService.opuserDistributorBydistributorid(utOrder.getUdistributorid());
                if (opdistrlist != null && opdistrlist.size() > 0) {
                    for (Map<String, Object> opdistr : opdistrlist) {
                        //不相同 发送推送消息
                        (new PushMessage()).pushmessagejxs(opdistr.get("uopuserid") + "", opdistr.get("uopuserid") + "", "您有新的订单信息", order.getUcontact() + "下单了,请查看");
                    }

                }

            }
            totalPayprice =totalPayprice.add(new BigDecimal(Double.parseDouble(utOrder.getUpayprice())));
        }
        if (result.getRetcode() == 1) {
            model.addAttribute("totalPayprice", totalPayprice.doubleValue() + "");
            //清楚缓存
            getRequest().getSession().removeAttribute("uorderitemid");
//            return "templates/h5/weuiOrderList?active=dfk";
            return new String("redirect:weuiOrderList?active=dfk");
        }else{
            return new String("redirect:wecheckout");
        }

    }




    @RequestMapping("wedelOrder")
    @ResponseBody
    public AjaxResult wedelOrder(String uorderid,@SessionAttribute(Constant.SESSION_H5USER)UtUsers utUsers) {
        try {
            boolean result = orderService.delOrder(uorderid, utUsers.getUuserid()) > 0;
            if (result) {
                return new AjaxResult(1, "删除成功");
            }else{
                return new AjaxResult("删除失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new AjaxResult("删除失败");
        }

    }



    @ControllerLog("订单快递信息")
    @RequestMapping("orderexpress/{uorderid}")
    @Authority(uifoper = "1",uopcode = "220101", uopname = "订单快递信息",uoptype = "0",uismenu = "0")
    public String orderexpress(Map<String, Object> map, @PathVariable("uorderid")String uorderid,@SessionAttribute(Constant.SESSION_H5USER)UtUsers user) {
        UtOrderexpress orderexpress = orderexpressService.selectByIds(user.getUuserid(),uorderid);
        if(!(orderexpress==null||orderexpress.getClass()==null)) {
            String param ="{\"com\":\""+orderexpress.getUexpresscompcode()+"\",\"num\":\""+orderexpress.getUexpressnum()+"\"}";
            String customer ="77A1A039876E5C76B21E99335E1CCC45";
            String key = "yXBlNuLB1510";
            String sign = MD5.encode(param+key+customer);
            HashMap<String, String> params = new HashMap<String, String>();
            params.put("param",param);
            params.put("sign",sign);
            params.put("customer",customer);
            String resp;
            try {
                resp = new HttpRequest().postData("http://poll.kuaidi100.com/poll/query.do", params, "utf-8").toString();
                JSONObject object = JSON.parseObject(resp);
                map.put("expressinfo", object);
                map.put("orderexpress", orderexpress);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }else {
//            map.put("expressinfo", "");

        }
      /*  UtOrderexpress utOrderexpress = new UtOrderexpress();
        utOrderexpress.setUexpresscompname("哪都通");
        utOrderexpress.setUexpressnum("132321313");
        map.put("orderexpress", utOrderexpress);
        String test = "{\"message\":\"ok\",\"nu\":\"4600265766007\",\"ischeck\":\"1\",\"condition\":\"D01\",\"com\":\"yunda\",\"status\":\"200\",\"state\":\"3\",\"data\":[{\"time\":\"2019-05-11 12:00:54\",\"ftime\":\"2019-05-11 12:00:54\",\"context\":\"【深圳市】 已签收 : 由万科麓城三期5栋架空层1号丰巢智能柜FC7555602 代签收，如有问题联系陈振华(14774933105)\"},{\"time\":\"2019-05-11 10:17:48\",\"ftime\":\"2019-05-11 10:17:48\",\"context\":\"【深圳市】 广东深圳公司龙华区布吉远安分部 派件员 陈振华(14774933105)正在为您派送\"},{\"time\":\"2019-05-11 08:15:29\",\"ftime\":\"2019-05-11 08:15:29\",\"context\":\"【深圳市】 已离开 广东深圳公司龙华区甘坑分拨分部 发往 广东深圳公司龙华区布吉远安分部(0755-61884406)\"},{\"time\":\"2019-05-11 03:35:46\",\"ftime\":\"2019-05-11 03:35:46\",\"context\":\"【深圳市】 已离开 广东深圳公司 发往 广东深圳公司龙华区甘坑分拨分部(0755-61886188)\"},{\"time\":\"2019-05-10 20:35:45\",\"ftime\":\"2019-05-10 20:35:45\",\"context\":\"【深圳市】 广东深圳公司龙岗区坑梓集包分部 已揽收\"}]}";
        JSONObject object = JSON.parseObject(test);

        map.put("expressinfo", object);*/
        return "templates/weui/orderexpress";
    }



    @ControllerLog("退款/取消订单")
    @RequestMapping("refund")
    @ResponseBody
    public synchronized AjaxResult refund(String uorderid) {

        HttpSession session = getRequest().getSession();
        UtUsers users = (UtUsers) session.getAttribute(Constant.SESSION_H5USER);
        UtOrder utOrder = orderService.queryByID(uorderid);

        //如果订单是该用户的则可以退款
        if (utOrder.getUuserid().equals(users.getUuserid())) {

            if (utOrder.getUeflag().equals("00")) {
                //如果未付款
                UtOrder updateOrder = new UtOrder();
                updateOrder.setUorderid(utOrder.getUorderid());
                updateOrder.setUeflag("99");
                return orderService.updateOrder(updateOrder);
            }

            if (!utOrder.getUeflag().equals("01") && !utOrder.getUeflag().equals("00")) {
                if (utOrder.getUeflag().equals("99")) {
                    return new AjaxResult(0, "该订单已关闭");
                }
                return new AjaxResult(0, "商家已发货");
            }
            try {

                UtUsers utUsers = userService.queryByID(users.getUuserid());
               /* if(utUsers.getUintegral() - utOrder.getUintegral()<0){
                    return new AjaxResult(0, "积分不足");
                }*/
                if (utOrder.getUintegral() != null && utOrder.getUintegral() > 0) {
                    UtUsers upuser = new UtUsers();
                    upuser.setUuserid(users.getUuserid());
                    upuser.setUintegral(utUsers.getUintegral() - utOrder.getUintegral());
                    userService.updateByUuserid(upuser);
                }
                //用于退款用户日志
                UtFinancelog utFinancelog = new UtFinancelog();
                String total_fee = utOrder.getUpayprice();
                //等于4 代表用户零钱付款 所以零钱退款
                if (utOrder.getUpaytype().equals("4")) {
                    //执行零钱退款流程

                    utFinancelog.setUpaymode("零钱退款");
                    utUsers.setUbalance(Double.parseDouble(utUsers.getUbalance()) + Double.parseDouble(total_fee)+"");
                    //零钱退款
                    utFinancelog.setUtradetype("6");


                } else if (utOrder.getUpaytype().equals("1")) {
                    //执行微信退款流程

                    //获取商户信息
                    CtWxpayConfig ctWxpayConfig = new CtWxpayConfig();
                    ctWxpayConfig.setUcustomerid(ucustomerid);
                    //0微信 1支付宝
                    ctWxpayConfig.setUtypes("0");
                    List<CtWxpayConfig> configs = wxpayConfigService.queryList(ctWxpayConfig);
                    String appid = "";
                    String appsecret = "";
                    String mch_id = "";
                    String key = "";
                    String filePath = "";
                    if (configs.size() > 0) {
                        CtWxpayConfig config = configs.get(0);
                        //获取appid
                        appid = config.getAppid();
                        appsecret = config.getAppsecret();
                        mch_id = config.getMchid();
                        key = config.getApikey();
                        filePath = docBase +config.getApiclientcertpath();
                        System.out.println(filePath);

                    }
                    String currTime = PayCommonUtil.getCurrTime();
                    String strTime = currTime.substring(8, currTime.length());
                    String strRandom = PayCommonUtil.buildRandom(4) + "";
                    String nonce_str = strTime + strRandom;

                    /***********************测试**************************/
//                    total_fee = "0.01";

                    // 价格 注意：价格的单位是分
                    String order_price = new BigDecimal(total_fee).multiply(new BigDecimal(100)).toString().split("\\.")[0];
                    Map<String, String> map = new HashMap<String, String>();
                    map.put("appid", appid);
                    map.put("mch_id", mch_id);
                    map.put("nonce_str", nonce_str);
//                map.put("transaction_id", queryFinancelogList.get(0).getTransactionId());
                    //订单id
                    map.put("out_trade_no", utOrder.getUorderid());

                    //退款订单号
                    map.put("out_refund_no", getOrderDateNo());
                    //订单总金额(分)
                    map.put("total_fee", order_price);
                    //退款金额(分)
                    map.put("refund_fee", order_price);
                    String sign = WXPayUtil.getSignCode(map, key);
                    map.put("sign", sign);
                    String xml = WXPayUtil.mapToXml(map);
                    String url = "https://api.mch.weixin.qq.com/secapi/pay/refund";
//                String resXml = HttpUtil.postData(url, xml);
//                    String filePath = docBase + "apiclient_cert.p12";
//                    filePath = "D:\\MyIDE\\IDEASVN\\svn\\zdv_shop\\src\\main\\webapp\\assets\\ghl\\apiclient_cert.p12";
                    String resXml = executeBySslPost(url, xml, filePath, mch_id);
                    Map<String, String> resMap = WXPayUtil.xmlToMap(resXml);
                    String return_code = resMap.get("return_code");
                    //成功
                    if (return_code.equals("SUCCESS")) {
                        if (resMap.get("result_code").equals("FAIL")) {
                            return new AjaxResult(0, resMap.get("result_msg"));
                        } else if (resMap.get("result_code").equals("SUCCESS")) {
                            //成功
                            RefundResult refundResult = new RefundBuilder().refundResultBuild(resMap);
                            //设置第三方
                            utFinancelog.setUfinancelogid(refundResult.getTransactionId());
                            utFinancelog.setUpaymode("微信退款");
                            //其他
                            utFinancelog.setUtradetype("7");
                        }
                    }else{
                        return new AjaxResult(0, resMap.get("return_msg"));
                    }


                }



                utFinancelog.setUfinancelogid(nextId());
                utFinancelog.setUcompid(ucompid);
                utFinancelog.setUuserid(users.getUuserid());
                utFinancelog.setUinfoid(utOrder.getUorderid());

                utFinancelog.setUstatus("1");
                utFinancelog.setUtype("0");
                //退款金额
                utFinancelog.setUtrademoney(""+total_fee);
                utFinancelog.setUglidenumber(getGlidenumber());
                utFinancelog.setIfline("1");

                UtOrder updateOrder = new UtOrder();
                updateOrder.setUorderid(utOrder.getUorderid());
                updateOrder.setUeflag("99");
                return orderService.refund(updateOrder, utFinancelog,utUsers);

            } catch (Exception e) {
                e.printStackTrace();
                return new AjaxResult(0, "该操作出错,请及时联系商家");
            }

        }
        return new AjaxResult(0, "用户与订单不匹配");
    }


    public static String executeBySslPost(String url, String body,String certificatePath,String password) throws Exception {
        String result = "";
        //商户id
        //指定读取证书格式为PKCS12
        KeyStore keyStore = KeyStore.getInstance("PKCS12");
        //读取本机存放的PKCS12证书文件
        FileInputStream instream = new FileInputStream(new File(certificatePath));
        try {
            //指定PKCS12的密码(商户ID)
            keyStore.load(instream, password.toCharArray());
        } finally {
            instream.close();
        }
        SSLContext sslcontext = SSLContexts.custom().loadKeyMaterial(keyStore, password.toCharArray()).build();
        //指定TLS版本
        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext, new String[]{"TLSv1"}, null, SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
        //设置httpclient的SSLSocketFactory
        CloseableHttpClient httpclient = HttpClients.custom().setSSLSocketFactory(sslsf).build();
        try {
            HttpPost httppost = new HttpPost(url);
            StringEntity reqEntity = new StringEntity(body, "UTF-8");
            httppost.setEntity(reqEntity);

            System.out.println("Executing request: " + httppost.getRequestLine());
            CloseableHttpResponse response = null;
            try {
                response = httpclient.execute(httppost);
                result = EntityUtils.toString(response.getEntity(),"UTF-8");
            } catch (Exception e) {
                e.printStackTrace();
//                log.error("请求失败", e);
                throw new RuntimeException(e);
            } finally {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
//            log.error("请求失败", e);
            throw new RuntimeException(e);
        } finally {
            try {
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    @RequestMapping("getVerificationcode")
    @ResponseBody
    public AjaxResult getVerificationcode(String uorderid,@SessionAttribute(Constant.SESSION_H5USER)UtUsers user) {

        String verificationcode = orderService.getVerificationcode(uorderid, user.getUuserid());
        if (StringUtils.isNotEmpty(verificationcode)) {
            return returnSuccess(verificationcode);

        }else{
            return returnFailed(0, "未找到核销码");
        }

    }



}
