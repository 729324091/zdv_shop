package com.zdv.shop.controller;

import com.zdv.shop.common.Constant;
import com.zdv.shop.common.utils.StringUtils;
import com.zdv.shop.model.*;
import com.zdv.shop.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.HttpServletRequest;

/**
 * 分润
 */
@Controller
@RequestMapping("/h5/share/")
public class H5ShareController extends BaseController {

    @Autowired
    private UtUserService userService;
    @Autowired
    private OtCompToProductService compToProductService;
    @Autowired
    private UtAreaManagerService areaManagerService;
    @Autowired
    private UtOrderItemService orderItemService;
    @Value("${global.upload.docBase}")
    private String docBase;
    @Value("${publicURL}") //在配置文件中
    private String publicurl;

    @RequestMapping("share")
    public String share(@SessionAttribute(Constant.SESSION_H5USER)UtUsers utUsers, Model model) {
        HttpServletRequest request = getRequest();
        StringBuffer sb = request.getRequestURL();
        String shopurl = sb.delete(sb.length() - request.getRequestURI().length(), sb.length()).toString();
        String qrcode = StringUtils.doimges(shopurl, utUsers.getQrcode());
        model.addAttribute("qrcode", utUsers.getQrcode());
        String path = shopurl + "/h5/user/register?uuserid=" + utUsers.getUuserid();
        model.addAttribute("path", path);
        return "templates/h5/share";
    }

/*
  *//*  @ControllerLog("邀请注册的分润")
    @RequestMapping("registVip")
    public void registVip(String uuserid) {
        UtUsers users = userService.queryByID(uuserid);
        UtUsers utUsers = new UtUsers();
        utUsers.setUuserid(uuserid);
        utUsers.setUbalance(Double.parseDouble(users.getUbalance())+100+"");

        int i = userService.updateByID(utUsers);
    }*//*


    *//**
     * 订单消费 分润
     * @param uuserid 推荐人
     * @param uorderitemid 订单项号
     *//*
    @RequestMapping("consume")
    public void consume(String uuserid,String uorderitemid){
        UtOrderItem utOrderItem = orderItemService.queryByID(uorderitemid);
        UtUsers users = userService.queryByID(uuserid);

//        Double upayprice = Double.parseDouble(utOrder.getUpayprice());
        //成本价
//        Double ucostprice = utOrder.getUcostprice();
        Double uprofit = utOrderItem.getUprofit();

//        Double profit = upayprice-ucostprice;
        UtUsers utUsers = new UtUsers();

        utUsers.setUuserid(uuserid);
        utUsers.setUbalance(users.getUbalance()+uprofit);
        int i = userService.updateByID(utUsers);



    }


    *//**
     * 推荐厂家商品的收益（订单项）
     *//*
    @RequestMapping("product")
    public void product(UtOrderItem orderItem) {
        //查询商品是否有推荐人
        OtCompToProduct otCompToProduct = compToProductService.queryByID(orderItem.getUproductid());
        //如果存在推荐人
        if (otCompToProduct.getUuserid()!=null) {
            //查询推荐人信息
            UtUsers utUsers = userService.queryByID(otCompToProduct.getUuserid());
            if (utUsers != null) {

                Double uprofit = orderItem.getUprofit();
                UtUsers users = new UtUsers();
                users.setUuserid(utUsers.getUuserid());
                //设置总余额
                users.setUbalance(Double.parseDouble(utUsers.getUbalance())+(uprofit*0.4)+"");
                //更新用户余额
                userService.updateByID(users);
            }

        }

    }

    *//**
     * 区域经理分润
     * @param order
     *//*
//    @RequestMapping("shareArea")
    public void shareArea(UtOrder order) {
        //获取用户id
        String uuserid = order.getUuserid();
        //获取用户
        UtUsers utUsers = userService.queryByID(uuserid);

        String uareaid = utUsers.getUareaid();
        UtAreaManager utAreaManager = new UtAreaManager();
        utAreaManager.setUareaid(uareaid);
        //一个地区一个还是多个
        UtAreaManager areaManager = areaManagerService.queryOne(utAreaManager);
        //有区域代理
        if (areaManager != null) {
            Double uprofit = Double.parseDouble(order.getUpayprice())-Double.parseDouble(order.getUcostprice());
            //获取区域代理
            UtUsers manager = userService.queryByID(areaManager.getUuserid());
            UtUsers update = new UtUsers();
            update.setUuserid(manager.getUuserid());
            update.setUbalance(Double.parseDouble(manager.getUbalance())+uprofit+"");
            userService.updateByID(update);
        }
    }

    @RequestMapping("shareArea")
    public void shareArea(UtOrderItem orderItem) {
        //获取用户id
        String uuserid = orderItem.getUuserid();
        //获取用户
        UtUsers utUsers = userService.queryByID(uuserid);

        String uareaid = utUsers.getUareaid();
        UtAreaManager utAreaManager = new UtAreaManager();
        utAreaManager.setUareaid(uareaid);
        //一个地区一个还是多个
        UtAreaManager areaManager = areaManagerService.queryOne(utAreaManager);
        //有区域代理
        if (areaManager != null) {
            Double uprofit = orderItem.getUprofit();
            //获取区域代理
            UtUsers manager = userService.queryByID(areaManager.getUuserid());
            UtUsers update = new UtUsers();
            update.setUuserid(manager.getUuserid());
            update.setUbalance(Double.parseDouble(manager.getUbalance())+uprofit+"");
            userService.updateByID(update);
        }
    }*/




}
