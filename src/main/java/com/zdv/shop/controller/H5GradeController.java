package com.zdv.shop.controller;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zdv.shop.common.Constant;
import com.zdv.shop.common.pojo.AjaxResult;
import com.zdv.shop.common.utils.KeyId;
import com.zdv.shop.common.utils.UploadUtil;
import com.zdv.shop.model.OtProductGrade;
import com.zdv.shop.model.UtOrderItem;
import com.zdv.shop.model.UtUsers;
import com.zdv.shop.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/h5/grade/")
public class H5GradeController extends BaseController {
    @Autowired
    private OtCompToProductService compToProductService;
    @Autowired
    private UtAreaManagerService areaManagerService;
    @Autowired
    private UtOrderItemService orderItemService;
    @Autowired
    private UtOrderService orderService;
    @Autowired
    private UtUserService userService;
    @Autowired
    private OtProductGradeService productGradeService;
    @Autowired
    private UtFinancelogService financelogService;

    /**访问地址*/
    @Value("${global.upload.path}")
    private String path;
    /**存放地址*/
    @Value("${global.upload.docBase}")
    private String docBase;
    @Autowired
    private UploadUtil uploadUtil;

    @RequestMapping("grade/{uorderid}")
    public String grade(Model model, @PathVariable("uorderid")String uorderid, @SessionAttribute(Constant.SESSION_H5USER)UtUsers utUsers) {
        UtOrderItem utOrderItem = new UtOrderItem();
        utOrderItem.setUorderid(uorderid);
        utOrderItem.setUuserid(utUsers.getUuserid());

        List<UtOrderItem> itemList = orderItemService.queryOrderItemList(utOrderItem);

        model.addAttribute("itemlist", itemList);


        return "templates/h5/grade";
    }

    @RequestMapping("comment/{uorderid}")
    public String comment(Model model, @PathVariable("uorderid")String uorderid, @SessionAttribute(Constant.SESSION_H5USER)UtUsers utUsers) {
        UtOrderItem utOrderItem = new UtOrderItem();
        utOrderItem.setUorderid(uorderid);
        utOrderItem.setUuserid(utUsers.getUuserid());

        List<UtOrderItem> itemList = orderItemService.queryOrderItemList(utOrderItem);

        model.addAttribute("itemlist", itemList);


        return "templates/weui/comment";
    }

    @RequestMapping("comment")
    @ResponseBody
    public AjaxResult comment(@SessionAttribute(Constant.SESSION_H5USER)UtUsers users,@RequestParam("list")String userList) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            JavaType jt = mapper.getTypeFactory().constructParametricType(ArrayList.class, OtProductGrade.class);
            //变为list集合
            List<OtProductGrade> list =  (List<OtProductGrade>)mapper.readValue(userList, jt);
            List<OtProductGrade> gradelist = new ArrayList<OtProductGrade>();
            List<UtOrderItem> orderItemList = new ArrayList<UtOrderItem>();
            String uorderid = "";
            List<String> productgradeids = new ArrayList<>();
            //遍历获取到的评论
            for (OtProductGrade grade : list) {
                String uorderitemid = grade.getUorderitemid();
                //查询出该评论的订单项
                UtOrderItem utOrderItem = orderItemService.queryByID(uorderitemid);
                //查询出改订单项的用户
                UtUsers utUsers = userService.queryByID(utOrderItem.getUuserid());
                //获取推荐人id
                String upuserid = utUsers.getUpuserid();
                grade.setUcompid(utOrderItem.getUcompid());
                grade.setUuserid(utOrderItem.getUuserid());
                grade.setUproductid(utOrderItem.getUproductid());
                String productgradeid = KeyId.nextId();
                grade.setProductgradeid(productgradeid);
                uorderid = utOrderItem.getUorderid();
                gradelist.add(grade);
                orderItemList.add(utOrderItem);
                productgradeids.add(productgradeid);
            }

            //添加评论
            AjaxResult ajaxResult = productGradeService.addProductGradeList(gradelist,uorderid,users);



            return ajaxResult;
        }catch(Exception e) {
            e.printStackTrace();
            return new AjaxResult(0,"评论出错");
        }
    }

    /*@ControllerLog("添加评论")
    @RequestMapping("addGrade")
    @ResponseBody
    public AjaxResult addGrade(@SessionAttribute(Constant.SESSION_H5USER)UtUsers users,@RequestParam("list")String userList)  {
    	try {
        ObjectMapper mapper = new ObjectMapper();
        JavaType jt = mapper.getTypeFactory().constructParametricType(ArrayList.class, OtProductGrade.class);
        //变为list集合
        List<OtProductGrade> list =  (List<OtProductGrade>)mapper.readValue(userList, jt);

        List<OtProductGrade> gradelist = new ArrayList<OtProductGrade>();
        List<UtOrderItem> orderItemList = new ArrayList<UtOrderItem>();
        String uorderid = "";
            List<String> productgradeids = new ArrayList<>();
        //遍历获取到的评论
        for (OtProductGrade grade : list) {
            String uorderitemid = grade.getUorderitemid();
            //查询出该评论的订单项
            UtOrderItem utOrderItem = orderItemService.queryByID(uorderitemid);
            //查询出改订单项的用户
            UtUsers utUsers = userService.queryByID(utOrderItem.getUuserid());
            //获取推荐人id
            String upuserid = utUsers.getUpuserid();
            grade.setUcompid(utOrderItem.getUcompid());
            grade.setUuserid(utOrderItem.getUuserid());
            grade.setUproductid(utOrderItem.getUproductid());
            String productgradeid = KeyId.nextId();
            grade.setProductgradeid(productgradeid);
            uorderid = utOrderItem.getUorderid();
            gradelist.add(grade);
            orderItemList.add(utOrderItem);
            productgradeids.add(productgradeid);
        }

        //添加评论
        AjaxResult ajaxResult = productGradeService.addProductGradeList(gradelist);

        ajaxResult.setData(productgradeids);
        if (ajaxResult.getRetcode() == 1) {
            if (StringUtils.StringisNotEmpty(uorderid)) {
                UtOrder utOrder = new UtOrder();
                utOrder.setUorderid(uorderid);
                utOrder.setUeflag("04");
                //更新订单状态
                AjaxResult update = orderService.updateOrder(utOrder);
                if (update.getRetcode() == 1) {
                    *//*UtOrderItem orderItem = new UtOrderItem();
                    orderItem.setUorderid(uorderid);*//*
                    Double totalProfit = 0.00;
                    for (UtOrderItem utOrderItem : orderItemList) {
                        String uorderitemid = utOrderItem.getUorderitemid();
                        OtProductGrade productGrade = new OtProductGrade();
                        productGrade.setUorderitemid(uorderitemid);
                        //订单的第一次评论
                        int i = productGradeService.queryCount(productGrade);
                        //不是第一次评价
                        if (i != 1) {
                            continue;
                        }
                        //总利润
                        totalProfit += utOrderItem.getUprofit();
                    }
                    //分润
                    if (totalProfit > 0.00) {
                        //购买用户users,总利润totalProfit,订单utOrder
                        financelogService.shareProfit(users, utOrder);
                    }

                }
            }
        }


        return ajaxResult;
    	}catch(Exception e) {
    		return new AjaxResult(0,"评论出错");
    	}
    }*/


    @RequestMapping("addGradeImg")
    @ResponseBody
    public AjaxResult addGradeImg(@RequestParam MultipartFile[] files,String[] uorderitemid,String[] productgradeids) {
            return productGradeService.uploadImg(files, docBase, path, uorderitemid,productgradeids);
    }




}
