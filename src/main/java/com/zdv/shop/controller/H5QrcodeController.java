package com.zdv.shop.controller;

import com.zdv.shop.common.pojo.AjaxResult;
import com.zdv.shop.common.utils.StringUtils;
import com.zdv.shop.model.*;
import com.zdv.shop.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/h5/qrcode/")
public class H5QrcodeController extends BaseController {
    @Autowired
    private OtCompToProductService compToProductService;

    @Autowired
    private OtCompProductStockService stockService;

    @Autowired
    private OtCompProductStockObjnameService stockObjnameService;
    @Autowired
    private CtCompService compService;
    @Autowired
    private UtOrderService orderService;
    @Autowired
    private UtOrderItemService orderItemService;


    @RequestMapping("qrepay")
    public String qrepay(Model model, String ucustomerid, String ucompid, String ucomproductid, String uproductstockid) {
        OtCompToProduct otCompToProduct = compToProductService.queryByUcomproductid(ucomproductid);
        CtComp ctComp = compService.queryByID(ucompid);
        String uobjnamevalue = "";
        if (StringUtils.StringisNotEmpty(uproductstockid)) {
            //获取商品信息
            //获取规格信息
            OtCompProductStock compProductStock = stockService.queryByID(uproductstockid);

            OtCompProductStockObjname otCompProductStockObjname = new OtCompProductStockObjname();
            otCompProductStockObjname.setUproductstockid(uproductstockid);
            //获取属性信息
            List<OtCompProductStockObjname> objnameList = stockObjnameService.queryList(otCompProductStockObjname);

            for (int i = 0; i < objnameList.size(); i++) {
                if (i == 0) {
                    uobjnamevalue =objnameList.get(i).getUobjname()+objnameList.get(i).getUobjvalue();
                }else{
                    uobjnamevalue += "#" + objnameList.get(i).getUobjname() + objnameList.get(i).getUobjvalue();
                }
            }
            otCompToProduct.setUstock(Integer.valueOf(compProductStock.getUstock()));
            otCompToProduct.setUprice(new BigDecimal(compProductStock.getUprice()));
            otCompToProduct.setUmarketprice(new BigDecimal(compProductStock.getUmarketprice()));
            otCompToProduct.setUvipprice(new BigDecimal(compProductStock.getUvipprice()));
            otCompToProduct.setUsalesprice(new BigDecimal(compProductStock.getUsalesprice()));


            model.addAttribute("stock", compProductStock);
            model.addAttribute("uobjnamevalue", uobjnamevalue);
        }
        model.addAttribute("ucustomerid", ucustomerid);
        model.addAttribute("ucompid", ucompid);
        model.addAttribute("product", otCompToProduct);



        return "templates/h5/qrepay";

    }


    @RequestMapping("h5qrpay")
    @ResponseBody
    public AjaxResult h5qrpay(Model model, UtOrderItem orderItem, String ucompid , String ucustomerid, String uproductstockid , String ucomproductid) {

        OtCompToProduct otCompToProduct = compToProductService.queryByUcomproductid(ucomproductid);
        CtComp ctComp = compService.queryByID(ucompid);

//        生成订单项
//        UtOrderItem orderItem = new UtOrderItem();
        orderItem.setUorderitemid(nextId());
        orderItem.setUcompid(ucompid);
        orderItem.setUproductid(otCompToProduct.getUproductid());
        orderItem.setUcomproductid(ucomproductid);
        orderItem.setUproductstockid(uproductstockid);
//        orderItem.setUproductnum("1");
        orderItem.setUproductname(otCompToProduct.getUalias());
        //折扣
        orderItem.setUdiscount("0");
        orderItem.setUmarketprice(otCompToProduct.getUmarketprice().doubleValue());

        orderItem.setUprice(otCompToProduct.getUprice().doubleValue());
        //支付价格
        orderItem.setUpayprice(otCompToProduct.getUprice().multiply(new BigDecimal(orderItem.getUproductnum())).doubleValue());
        orderItem.setUcostprice(otCompToProduct.getUcostprice().doubleValue());
        orderItem.setUunit(otCompToProduct.getUunit());
        orderItem.setUunitid(otCompToProduct.getUunitid());
//        orderItem.setUobjnamevalue(uobjnamevalue);
        orderItem.setUdistributorid(otCompToProduct.getUdistributorid());
//        orderItem.setUdname(otCompToProduct.getudname);
        orderItem.setUpaystatus("0");
        BigDecimal totalprice = new BigDecimal(orderItem.getUprice()).multiply(new BigDecimal(orderItem.getUproductnum()));
        BigDecimal totalcostprice = new BigDecimal(orderItem.getUcostprice()).multiply(new BigDecimal(orderItem.getUproductnum()));

        BigDecimal uprofit = new BigDecimal(orderItem.getUprice()).subtract(new BigDecimal(orderItem.getUcostprice())).multiply(new BigDecimal(orderItem.getUproductnum()));
        orderItem.setUprofit(uprofit.doubleValue());
        orderItem.setUcost(totalcostprice.doubleValue());
        orderItem.setUhomepic(otCompToProduct.getUhomepic());



        //生成订单
        UtOrder utOrder = new UtOrder();
        utOrder.setUorderid(nextId());
        utOrder.setUorderno(getOrderDateNo());
        utOrder.setUproductname(otCompToProduct.getUalias());
        utOrder.setUproductnum(orderItem.getUproductnum());
        utOrder.setUtotalprice(orderItem.getUpayprice().toString());
        utOrder.setUpayprice(orderItem.getUpayprice().toString());
        utOrder.setUcostprice(orderItem.getUcost().toString());
        utOrder.setUpaystate("0");
        utOrder.setUpaytype("2"); //微信

        utOrder.setUcompid(orderItem.getUcompid());
        utOrder.setUcustomerid(ucustomerid);
        utOrder.setUcompname(ctComp.getUcompname());
        utOrder.setUsolve("0");
        utOrder.setUsend_type("0");
        utOrder.setUcrawl("0");

        utOrder.setUshoptype("1");


        utOrder.setUeflag("00");
        utOrder.setUcaddress(ctComp.getUaddress());

        List<UtOrder> orderList = new ArrayList<>();
        List<UtOrderItem> itemList = new ArrayList<>();

        itemList.add(orderItem);
        orderList.add(utOrder);
        AjaxResult ajaxResult = orderService.addOrderList(orderList, itemList, new ArrayList<>());
        if (ajaxResult.getRetcode() == 1) {
            ajaxResult.setData(utOrder);
        }
        return ajaxResult;
    }

}
