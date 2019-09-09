package com.zdv.shop.service;

import com.zdv.shop.common.Constant;
import com.zdv.shop.common.annotation.ServiceLog;
import com.zdv.shop.common.utils.KeyId;
import com.zdv.shop.common.utils.StringUtils;
import com.zdv.shop.mapper.UtFinancelogMapper;
import com.zdv.shop.model.*;
import com.zdv.shop.weixin.template.TemplateSendUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@Service
public class UtFinancelogService extends AbstratService<UtFinancelog> {

    @Autowired
    private UtFinancelogMapper financelogMapper;

    @Autowired
    private UtUserService userService;

    @Autowired
    private CtCompService compService;
    @Autowired
    private CtProfitService profitService;
    @Autowired
    private UtOrderService orderService;
    @Autowired
    private UtOrderItemService orderItemService;
    @Autowired
    private OtCompToProductService compToProductService;
    @Autowired
    private UtUserDevelopCompService userDevelopCompService;
    @Autowired
    private UtCompMemberUserService compMemberUserService;
    @Autowired
    private UtThirdloginService thirdloginService;
    @Autowired
    private CtWxpayConfigService wxpayConfigService;
    @Autowired
    private UtUserDistributionService userDistributionService;


    @Value("${ucustomerid}")
    private String ucustomerid;

    public int saveFinaceLog(UtFinancelog utFinancelog) {
        return financelogMapper.saveFinaceLog(utFinancelog);
    }

    public List<UtFinancelog> queryFinancelogList(UtFinancelog financelog) {
        return financelogMapper.queryFinancelogList(financelog);

    }
    @ServiceLog("利润提成及创建日志")
    public void shareProfit(UtUsers users,String uorderitemid) {
//        先创建购买用户财务日志
//        String uuserid = users.getUuserid();
        UtOrderItem orderItem = orderItemService.queryByID(uorderitemid);


//        CtProfit profit = profitService.queryProfitByCompId(orderItem.getUcompid(), ctComp.getUcustomerid());
        UtOrder utOrder = orderService.queryByID(orderItem.getUorderid());

        //生成财务日志
        UtFinancelog userFinancelog = new UtFinancelog();
        //订单id
        userFinancelog.setUinfoid(utOrder.getUorderid());
        //用户id
        userFinancelog.setUuserid(users.getUuserid());
        userFinancelog.setUtype("0");
        //获得该用户本次的财务日志
        List<UtFinancelog> list = financelogMapper.select(userFinancelog);
        if (list.size() > 0) {
            userFinancelog.setUstatus("1");
            userFinancelog.setUfinancelogid(list.get(0).getUfinancelogid());
            boolean update = financelogMapper.updateByUfinancelogid(userFinancelog) > 0;
            //更新成功
        }
    }

    /**
     * 搜索用户,分润金额,订单项,当前等级
     * @param utUsers
     * @param query
     * @param shareprofit
     * @param orderItem
     * @param ulevel
     */
    private void shareprofit(UtUsers users, UtUsers query, double shareprofit, UtOrderItem orderItem, String ulevel,String type) {

            if (StringUtils.StringisNotEmpty(query.getUpuserid())) {
                int length = ulevel.length();
                //父信息
                UtUsers parent = userService.queryByID(query.getUpuserid());
                //存在父信息
                if (parent != null) {
                    //父存在分润等级id
                    if (StringUtils.StringisNotEmpty(parent.getUdistributionid())) {
                        //父的分润信息
                        UtUserDistribution parentDistribution = userDistributionService.queryByID(parent.getUdistributionid());
                        //符合分润
                        if (parentDistribution != null && parentDistribution.getUlevel().length() < ulevel.length()) {
                            //获取分润率
                            Double uprofit = parentDistribution.getUprofit();
                            double v = formatDouble2(uprofit * shareprofit);

                            CtWxpayConfig ctWxpayConfig = new CtWxpayConfig();
                            ctWxpayConfig.setUcustomerid(ucustomerid);
                            //0微信 1支付宝
                            ctWxpayConfig.setUtypes("0");
                            List<CtWxpayConfig> configs = wxpayConfigService.queryList(ctWxpayConfig);
                            String appid = "";
                            String appsecret = "";
                            if (configs.size() > 0) {
                                appid = configs.get(0).getAppid();
                                appsecret = configs.get(0).getAppsecret();
                            }



                            //标题
                            String title = "推荐人利润提成";
                            //门店
                            String shop = orderItem.getUcompname();
                            //服务
                            String service = "利润提成";
                            //时间
                            String time = DateFormat.getDateTimeInstance(2, 2, Locale.CHINESE).format(new java.util.Date());
                            //数据 (详细内容)
                            String data = "您推荐的用户ID" +users.getUusercode()+"名称:"+users.getUname()+"购买商品:"+orderItem.getUproductname()+"您获得利润提成:"+v+"元";
                            String templateid = "MNFURs3OtrK8tpRxPIjLE0MjCJwGEp3OH3XqRp9TGSQ";

                            //如果是支付
                            if (type.equals("01")) {
                                shareFinancelog(parent.getUuserid(),orderItem,v);
                                UtThirdlogin thirdlogin = thirdloginService.selectInfoByUserid(users.getUpuserid(), "0");
                                if (thirdlogin != null) {

                                    TemplateSendUtil.send_template_message(appid, appsecret, thirdlogin.getOpenid(), templateid, title, shop, service, time, data);
                                }

                                //如果是退款
                            } else if (type.equals("00")) {
                                returnShareFinancelog(parent.getUuserid(),orderItem,v);
                                UtThirdlogin thirdlogin = thirdloginService.selectInfoByUserid(users.getUpuserid(), "0");
                                if (thirdlogin != null) {
                                    //标题
                                    title = "推荐人利润提成";
                                    //门店
                                    shop = orderItem.getUcompname();
                                    //服务
                                    service = "取消利润提成";
                                    //时间
                                    time = DateFormat.getDateTimeInstance(2, 2, Locale.CHINESE).format(new java.util.Date());
                                    //数据 (详细内容)
                                    data = "您推荐的用户ID" +users.getUusercode()+"名称:"+users.getUname()+"购买商品:"+orderItem.getUproductname()+"取消了订单,您获得的利润提成:"+v+"元将被收回";
                                    templateid = "MNFURs3OtrK8tpRxPIjLE0MjCJwGEp3OH3XqRp9TGSQ";

                                    TemplateSendUtil.send_template_message(appid, appsecret, thirdlogin.getOpenid(), templateid, title, shop, service, time, data);
                                }

                            }

                            if (parentDistribution.getUlevel().length() > 7) {
                                shareprofit(query, parent, shareprofit, orderItem, parentDistribution.getUlevel(),type);
                            }
                        }else{
                            shareprofit(query, parent, shareprofit, orderItem, ulevel,type);

                        }
                    }
                }

            }


    }

    /**
     * @param users
     * @param uorderitemid
     * @param type   00退款  01评论成功完成订单
     */
    //利润提成
    public void share(UtUsers users,String uorderitemid,String type) {
        CtWxpayConfig ctWxpayConfig = new CtWxpayConfig();
        ctWxpayConfig.setUcustomerid(ucustomerid);
        //0微信 1支付宝
        ctWxpayConfig.setUtypes("0");
        List<CtWxpayConfig> configs = wxpayConfigService.queryList(ctWxpayConfig);
        String appid = "";
        String appsecret = "";
        if (configs.size() > 0) {
            appid = configs.get(0).getAppid();
            appsecret = configs.get(0).getAppsecret();
        }

        if ("10000055".equalsIgnoreCase(ucustomerid)) {
            UtOrderItem orderItem = orderItemService.queryByID(uorderitemid);
            Double itemprofit = orderItem.getUprofit();
            double shareprofit = itemprofit * 0.25;
            shareprofit = formatDouble2(shareprofit);


            UtUsers query = userService.queryByID(users.getUuserid());
            String superiorid = users.getUuserid();
            boolean flag = true;
            while (flag) {
                superiorid = userService.getSuperiorUuseridByUuserid(superiorid);

                if (StringUtils.StringisNotEmpty(superiorid)) {

                    UtUserDistribution distribution = userDistributionService.queryUserDistributionByUuserid(superiorid);
                    Double uprofit = distribution.getUprofit();
                    double v = formatDouble2(uprofit * shareprofit);


                    //标题
                    String title = "推荐人利润提成";
                    //门店
                    String shop = orderItem.getUcompname();
                    //服务
                    String service = "利润提成";
                    //时间
                    String time = DateFormat.getDateTimeInstance(2, 2, Locale.CHINESE).format(new java.util.Date());
                    //数据 (详细内容)
                    String data = "您推荐的用户ID" +users.getUusercode()+"名称:"+users.getUname()+"购买商品:"+orderItem.getUproductname()+"您获得利润提成:"+v+"元";
                    String templateid = "MNFURs3OtrK8tpRxPIjLE0MjCJwGEp3OH3XqRp9TGSQ";

                    //如果是支付
                    if (type.equals("01")) {
                        shareFinancelog(superiorid,orderItem,v);
                        UtThirdlogin thirdlogin = thirdloginService.selectInfoByUserid(users.getUpuserid(), "0");
                        if (thirdlogin != null) {

                            TemplateSendUtil.send_template_message(appid, appsecret, thirdlogin.getOpenid(), templateid, title, shop, service, time, data);
                        }

                        //如果是退款
                    } else if (type.equals("00")) {
                        returnShareFinancelog(superiorid,orderItem,v);
                        UtThirdlogin thirdlogin = thirdloginService.selectInfoByUserid(users.getUpuserid(), "0");
                        if (thirdlogin != null) {
                            //标题
                            title = "推荐人利润提成";
                            //门店
                            shop = orderItem.getUcompname();
                            //服务
                            service = "取消利润提成";
                            //时间
                            time = DateFormat.getDateTimeInstance(2, 2, Locale.CHINESE).format(new java.util.Date());
                            //数据 (详细内容)
                            data = "您推荐的用户ID" +users.getUusercode()+"名称:"+users.getUname()+"购买商品:"+orderItem.getUproductname()+"取消了订单,您获得的利润提成:"+v+"元将被收回";
                            templateid = "MNFURs3OtrK8tpRxPIjLE0MjCJwGEp3OH3XqRp9TGSQ";

                            TemplateSendUtil.send_template_message(appid, appsecret, thirdlogin.getOpenid(), templateid, title, shop, service, time, data);
                        }

                    }


                }else{

                    flag = false;

                }


            }



            /*//测试新分润
            //获取该用户分润等级信息
            UtUserDistribution utUserDistribution = userDistributionService.queryByID(query.getUdistributionid());
            if (utUserDistribution.getUlevel().length() > 7) {
                //非顶级才可能存在分润      //购买商品得到用户, 用于查询上级的用户,分润的金额,订单项,查询分润的等级,退款还是进款
                shareprofit(query,query, shareprofit,orderItem,utUserDistribution.getUlevel(),type);
            }*/
        }


        String uuserid = users.getUuserid();

        UtOrderItem orderItem = orderItemService.queryByID(uorderitemid);
        CtComp ctComp = compService.queryByID(orderItem.getUcompid());
        System.out.println("*****************************************");
        System.out.println(orderItem.getUcompid());
        System.out.println(uorderitemid);
        System.out.println(ctComp.getUcustomerid());
        System.out.println("*****************************************");
        //获得利润提成列表
        CtProfit profit = profitService.queryProfitByCompId(orderItem.getUcompid(), ctComp.getUcustomerid());

        if (profit != null) {

            /*CtWxpayConfig ctWxpayConfig = new CtWxpayConfig();
            ctWxpayConfig.setUcustomerid(ucustomerid);
            //0微信 1支付宝
            ctWxpayConfig.setUtypes("0");
            List<CtWxpayConfig> configs = wxpayConfigService.queryList(ctWxpayConfig);
            String appid = "";
            String appsecret = "";
            if (configs.size() > 0) {
                appid = configs.get(0).getAppid();
                appsecret = configs.get(0).getAppsecret();
            }*/


            if (profit.getUseable().equalsIgnoreCase("1")) {
                Double ureferee = profit.getUreferee(); //推荐人
                Double uplatform = profit.getUplatform(); //平台00
                Double umerchant = profit.getUmerchant(); //商家
                Double uarea = profit.getUarea();   //区域经理
                Double ushopkeeper = profit.getUshopkeeper(); //店主
                Double ubranch = profit.getUbranch(); //分公司
                Double uproducts = profit.getUproducts(); //推荐产品
                Double ucashpool = profit.getUcashpool();//公司现金池

                String ucompid = orderItem.getUcompid();
                Double upayprice = orderItem.getUpayprice();
                Double uprofit = orderItem.getUprofit();

                //地球村分润
                if ("10000027".equalsIgnoreCase(ucustomerid)) {
                    if (ureferee != 0 && StringUtils.StringisNotEmpty(users.getUpuserid())) {
                        //获得分取的利润
                        double urefereeProfit = ureferee * orderItem.getUprofit();
                        urefereeProfit = formatDouble2(urefereeProfit);

                        UtUsers puser = userService.queryByID(users.getUpuserid());
                        //利润大于0才会利润提成
                        //添加财务日志
                        if (urefereeProfit > 0) {
                            //标题
                            String title = "推荐人利润提成";
                            //门店
                            String shop = ctComp.getUcompname();
                            //服务
                            String service = "利润提成";
                            //时间
                            String time = DateFormat.getDateTimeInstance(2, 2, Locale.CHINESE).format(new java.util.Date());
                            //数据 (详细内容)
                            String data = "您推荐的用户ID" +users.getUusercode()+"名称:"+users.getUname()+"购买商品:"+orderItem.getUproductname()+"您获得利润提成:"+urefereeProfit+"元";
                            String templateid = "MNFURs3OtrK8tpRxPIjLE0MjCJwGEp3OH3XqRp9TGSQ";

                            //如果是支付
                            if (type.equals("01")) {
                                shareFinancelog(users.getUpuserid(), orderItem, urefereeProfit);
                                UtThirdlogin thirdlogin = thirdloginService.selectInfoByUserid(users.getUpuserid(), "0");
                                if (thirdlogin != null) {

                                    TemplateSendUtil.send_template_message(appid, appsecret, thirdlogin.getOpenid(), templateid, title, shop, service, time, data);
                                }

                                //如果是退款
                            } else if (type.equals("00")) {
                                returnShareFinancelog(users.getUpuserid(), orderItem, urefereeProfit);
                                UtThirdlogin thirdlogin = thirdloginService.selectInfoByUserid(users.getUpuserid(), "0");
                                if (thirdlogin != null) {
                                    //标题
                                    title = "推荐人利润提成";
                                    //门店
                                    shop = ctComp.getUcompname();
                                    //服务
                                    service = "取消利润提成";
                                    //时间
                                    time = DateFormat.getDateTimeInstance(2, 2, Locale.CHINESE).format(new java.util.Date());
                                    //数据 (详细内容)
                                    data = "您推荐的用户ID" +users.getUusercode()+"名称:"+users.getUname()+"购买商品:"+orderItem.getUproductname()+"取消了订单,您获得的利润提成:"+urefereeProfit+"元将被收回";
                                    templateid = "MNFURs3OtrK8tpRxPIjLE0MjCJwGEp3OH3XqRp9TGSQ";

                                    TemplateSendUtil.send_template_message(appid, appsecret, thirdlogin.getOpenid(), templateid, title, shop, service, time, data);
                                }

                            }

                        }
                    }
                    ///////////////////////////////////////////////////////////////////////////////////
                    //平台分润
                    if (uplatform > 0) {
//

                    }
                    /////////////////////////////////////////////

                        //商家利润提成



                    UtUsers shoper = userService.getShoper(users);
                //如果分润大于0
                if (ushopkeeper > 0 && shoper !=null) {


                    //支付
                    if (type.equals("01")) {

                    double ushopkeeperProfit = ushopkeeper * uprofit;
                    //保留两位小数
                    ushopkeeperProfit = formatDouble2(ushopkeeperProfit);
                    if (ushopkeeperProfit > 0) {


                        //存在userid
                        shareFinancelog(shoper.getUuserid(),orderItem,ushopkeeperProfit);

                        //利润提成推送
                        UtThirdlogin thirdlogin = thirdloginService.selectInfoByUserid(shoper.getUuserid(), "0");
                        if (thirdlogin != null) {
                            //标题
                            String title = "店主利润提成";
                            //门店
                            String shop = ctComp.getUcompname();
                            //服务
                            String service = "利润提成";
                            //时间
                            String time = DateFormat.getDateTimeInstance(2, 2, Locale.CHINESE).format(new java.util.Date());
                            //数据 (详细内容)
                            String data = "用户ID" + users.getUusercode() + "名称:" + users.getUname() + "购买商品:" + orderItem.getUproductname() + "您获得利润提成:" + ushopkeeperProfit+"元";
                            String templateid = "MNFURs3OtrK8tpRxPIjLE0MjCJwGEp3OH3XqRp9TGSQ";

                            TemplateSendUtil.send_template_message(appid, appsecret, thirdlogin.getOpenid(), templateid, title, shop, service, time, data);
                        }
                    }


                }else if (type.equals("00")) {

                        if (ushopkeeper > 0) {
                            //店主


                            double ushopkeeperProfit = ushopkeeper * uprofit;
                            //保留两位小数
                            ushopkeeperProfit = formatDouble2(ushopkeeperProfit);
                            if (ushopkeeperProfit > 0) {
                                //存在userid
                                returnShareFinancelog(shoper.getUuserid(),orderItem,ushopkeeperProfit);

                                //利润提成推送
                                UtThirdlogin thirdlogin = thirdloginService.selectInfoByUserid(shoper.getUuserid(), "0");
                                if (thirdlogin != null) {
                                    //标题
                                    String title = "店主利润提成";
                                    //门店
                                    String shop = ctComp.getUcompname();
                                    //服务
                                    String service = "取消利润提成";
                                    //时间
                                    String time = DateFormat.getDateTimeInstance(2, 2, Locale.CHINESE).format(new java.util.Date());
                                    //数据 (详细内容)
                                    String data = "用户ID" + users.getUusercode() + "名称:" + users.getUname() + "取消了购买商品:" + orderItem.getUproductname() + "的订单,您获得利润提成:" + ushopkeeperProfit+"元将被退回";
                                    String templateid = "MNFURs3OtrK8tpRxPIjLE0MjCJwGEp3OH3XqRp9TGSQ";

                                    TemplateSendUtil.send_template_message(appid, appsecret, thirdlogin.getOpenid(), templateid, title, shop, service, time, data);
                                }
                            }

                        }

                    }

            }







                    //经理分润
                    if (uarea >= 0) {
                        double uareaProfit = uarea * uprofit;
                        //保留两位小数
                        uareaProfit = formatDouble2(uareaProfit);

                        UtUsers areaManager = userService.getAreaManager(users);
                        if (areaManager != null) {
                            if (uareaProfit > 0) {
                                UtThirdlogin thirdlogin = thirdloginService.selectInfoByUserid(areaManager.getUuserid(), "0");
                                if (type.equals("01")) {
                                    //执行利润提成
                                    shareFinancelog(areaManager.getUuserid(),orderItem,uareaProfit);
                                    //利润提成推送
                                    if (thirdlogin != null) {
                                        //标题
                                        String title = "经理利润提成";
                                        //门店
                                        String shop = ctComp.getUcompname();
                                        //服务
                                        String service = "利润提成";
                                        //时间
                                        String time = DateFormat.getDateTimeInstance(2, 2, Locale.CHINESE).format(new java.util.Date());
                                        //数据 (详细内容)
                                        String data = "您团队的用户,ID:" + users.getUusercode() + "名称:" + users.getUname() + "购买了商品:" + orderItem.getUproductname() + "您获得利润提成:" + uareaProfit+"元";
                                        String templateid = "MNFURs3OtrK8tpRxPIjLE0MjCJwGEp3OH3XqRp9TGSQ";

                                        TemplateSendUtil.send_template_message(appid, appsecret, thirdlogin.getOpenid(), templateid, title, shop, service, time, data);
                                    }

                                } else if (type.equals("00")) {
                                    //执行利润提成
                                    returnShareFinancelog(areaManager.getUuserid(),orderItem,uareaProfit);
                                    //利润提成推送
                                    if (thirdlogin != null) {
                                        //标题
                                        String title = "经理利润提成";
                                        //门店
                                        String shop = ctComp.getUcompname();
                                        //服务
                                        String service = "取消利润提成";
                                        //时间
                                        String time = DateFormat.getDateTimeInstance(2, 2, Locale.CHINESE).format(new java.util.Date());
                                        //数据 (详细内容)
                                        String data = "您团队的用户,ID:" + users.getUusercode() + "名称:" + users.getUname() + "取消了购买商品:" + orderItem.getUproductname() + "的订单,您获得利润提成:" + uareaProfit+"元将被退回";
                                        String templateid = "MNFURs3OtrK8tpRxPIjLE0MjCJwGEp3OH3XqRp9TGSQ";

                                        TemplateSendUtil.send_template_message(appid, appsecret, thirdlogin.getOpenid(), templateid, title, shop, service, time, data);
                                    }


                                }


                            }
                        }

                    }

                    if (ubranch >= 0) {
                        double ubranchProfit = ubranch * uprofit;
                        //保留两位小数
                        ubranchProfit = formatDouble2(ubranchProfit);
                        if (ubranchProfit > 0) {
                            //分公司利润提成
                            UtUsers branchUser = userService.getBranch(users);
                            if (branchUser != null) {
                                UtThirdlogin thirdlogin = thirdloginService.selectInfoByUserid(branchUser.getUuserid(), "0");

                                if (type.equals("01")) {
                                    shareFinancelog(branchUser.getUuserid(),orderItem,ubranchProfit);
                                    //利润提成推送
                                    if (thirdlogin != null) {
                                        //标题
                                        String title = "分公司利润提成";
                                        //门店
                                        String shop = ctComp.getUcompname();
                                        //服务
                                        String service = "利润提成";
                                        //时间
                                        String time = DateFormat.getDateTimeInstance(2, 2, Locale.CHINESE).format(new java.util.Date());
                                        //数据 (详细内容)
                                        String data = "您团队的用户,ID:" + users.getUusercode() + "名称:" + users.getUname() + "在购买商品:" + orderItem.getUproductname() + "您获得利润提成:" + ubranchProfit;
                                        String templateid = "MNFURs3OtrK8tpRxPIjLE0MjCJwGEp3OH3XqRp9TGSQ";

                                        TemplateSendUtil.send_template_message(appid, appsecret, thirdlogin.getOpenid(), templateid, title, shop, service, time, data);
                                    }

                                } else if (type.equals("00")) {

//                                shareFinancelog(branchUser.getUuserid(),orderItem,ubranchProfit);
                                    returnShareFinancelog(branchUser.getUuserid(),orderItem,ubranchProfit);
                                    //利润提成推送
                                    if (thirdlogin != null) {
                                        //标题
                                        String title = "分公司利润提成";
                                        //门店
                                        String shop = ctComp.getUcompname();
                                        //服务
                                        String service = "取消利润提成";
                                        //时间
                                        String time = DateFormat.getDateTimeInstance(2, 2, Locale.CHINESE).format(new java.util.Date());
                                        //数据 (详细内容)
                                        String data = "您团队的用户,ID:" + users.getUusercode() + "名称:" + users.getUname() + "取消了购买商品:" + orderItem.getUproductname() + "的订单,您获得的利润提成:" + ubranchProfit + "元将被退回";
                                        String templateid = "MNFURs3OtrK8tpRxPIjLE0MjCJwGEp3OH3XqRp9TGSQ";

                                        TemplateSendUtil.send_template_message(appid, appsecret, thirdlogin.getOpenid(), templateid, title, shop, service, time, data);
                                    }
                                }

                            }
                        }

                    }

                    if (uproducts > 0) {
                        double uproductsProfit = umerchant * uprofit;
                        //保留两位小数
                        uproductsProfit = formatDouble2(uproductsProfit);
                        if (uproductsProfit > 0) {
                            //利润提成利润大于0
                            String ucomproductid = orderItem.getUcomproductid();
                            if (StringUtils.StringisNotEmpty(ucomproductid)) {
                                //获取商品信息
                                OtCompToProduct otCompToProduct = compToProductService.queryByID(ucomproductid);
                                if (otCompToProduct != null) {
                                    if (StringUtils.StringisNotEmpty(otCompToProduct.getUuserid())) {
                                        //如果存在商品推荐人
                                        UtUsers productUser = userService.queryByID(otCompToProduct.getUuserid());
                                        if (productUser != null) {
                                            //利润提成推送
                                            UtThirdlogin thirdlogin = thirdloginService.selectInfoByUserid(productUser.getUuserid(), "0");

                                            if (type.equals("01")) {
                                                shareFinancelog(productUser.getUuserid(), orderItem, uproductsProfit);
                                                if (thirdlogin != null) {
                                                    //标题
                                                    String title = "推荐商品利润提成";
                                                    //门店
                                                    String shop = ctComp.getUcompname();
                                                    //服务
                                                    String service = "利润提成";
                                                    //时间
                                                    String time = DateFormat.getDateTimeInstance(2, 2, Locale.CHINESE).format(new java.util.Date());
                                                    //数据 (详细内容)
                                                    String data = "您推荐的商品:" + orderItem.getUproductname() + "被用户,ID:" + users.getUusercode() + "名称:" + users.getUname() + "购买,您获得利润提成:" + uproductsProfit+"元";
                                                    String templateid = "MNFURs3OtrK8tpRxPIjLE0MjCJwGEp3OH3XqRp9TGSQ";

                                                    TemplateSendUtil.send_template_message(appid, appsecret, thirdlogin.getOpenid(), templateid, title, shop, service, time, data);
                                                }
                                            } else if (type.equals("00")) {
                                                returnShareFinancelog(productUser.getUuserid(), orderItem, uproductsProfit);
                                                if (thirdlogin != null) {
                                                    //标题
                                                    String title = "推荐商品利润提成";
                                                    //门店
                                                    String shop = ctComp.getUcompname();
                                                    //服务
                                                    String service = "取消利润提成";
                                                    //时间
                                                    String time = DateFormat.getDateTimeInstance(2, 2, Locale.CHINESE).format(new java.util.Date());
                                                    //数据 (详细内容)
                                                    String data = "您推荐的商品:" + orderItem.getUproductname() + "由于用户,ID:" + users.getUusercode() + "名称:" + users.getUname() + "取消了购买订单,您获得利润提成:" + uproductsProfit+"元将被退回";
                                                    String templateid = "MNFURs3OtrK8tpRxPIjLE0MjCJwGEp3OH3XqRp9TGSQ";

                                                    TemplateSendUtil.send_template_message(appid, appsecret, thirdlogin.getOpenid(), templateid, title, shop, service, time, data);
                                                }

                                            }

                                        }
                                    }
                                }

                            }


                        }

                    }


                } else if ("10000032".equals(ucustomerid)) {
                    //港货灵分润


                    //先扣除现金池
                    if (ucashpool > 0) {
                        //扣除的现金池金额 ,在利润中扣除
                        double cashpoolmoney = uprofit * ucashpool;

                        uprofit -= cashpoolmoney;
                        UtFinancelog utFinancelog = new UtFinancelog();
                        utFinancelog.setUtradetype("5");

                        utFinancelog.setIfline("0");
                        Date date = new Date();
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
                        String time = sdf.format(date);

                        //流水账号
                        utFinancelog.setUglidenumber(time + KeyId.nextId());
                        utFinancelog.setUfinancelogid(KeyId.nextId());
                        utFinancelog.setUuserid(orderItem.getUuserid());
                        utFinancelog.setUcompid(ucompid);
                        utFinancelog.setUstatus("1");
                        utFinancelog.setUtype("1");

                        if (type.equals("01")) {
                            utFinancelog.setAccttype("0");
                            utFinancelog.setUtrademoney(cashpoolmoney+"");
                            utFinancelog.setUpaymode("现金池");

                        } else if (type.equals("00")) {
                            utFinancelog.setAccttype("1");
                            utFinancelog.setUtrademoney("-"+cashpoolmoney+"");
                            utFinancelog.setUpaymode("撤回现金池收益");
                        }
                        financelogMapper.saveFinaceLog(utFinancelog);
                    }
                    if (uprofit > 0) {
                        //平台
                        if(uplatform>0){

//                            如果平台分润大于0
                            double uplatformProfit = uplatform * uprofit;
                            if (uplatformProfit > 0) {


                                if(type.equals("01")){
                                    //存在userid
                                    shareFinancelog("0",orderItem,uplatformProfit);

                                    //利润提成推送
                                    UtThirdlogin thirdlogin = thirdloginService.selectInfoByUserid("0", "0");
                                    if (thirdlogin != null) {
                                        //标题
                                        String title = "平台利润提成";
                                        //门店
                                        String shop = ctComp.getUcompname();
                                        //服务
                                        String service = "利润提成";
                                        //时间
                                        String time = DateFormat.getDateTimeInstance(2, 2, Locale.CHINESE).format(new java.util.Date());
                                        //数据 (详细内容)
//                                String data = "用户ID" + users.getUusercode() + "名称:" + users.getUname() + "在您推荐的店铺购买商品:" + orderItem.getUproductname() + "您获得利润提成:" + ushopkeeperProfit+"元";
//                                String templateid = "MNFURs3OtrK8tpRxPIjLE0MjCJwGEp3OH3XqRp9TGSQ";

//                                TemplateSendUtil.send_template_message(appid, appsecret, thirdlogin.getOpenid(), templateid, title, shop, service, time, data);
                                    }
                                } else if (type.equals("00")) {
                                    //存在userid
                                    returnShareFinancelog("0",orderItem,uplatformProfit);

                                }

                            }



                        }

                        if (ushopkeeper > 0) {
                            UtUserDevelopComp userDevelopComp = userDevelopCompService.getByUcompid(ucompid);

                            if (userDevelopComp != null && userDevelopComp.getClass() != null && StringUtils.StringisNotEmpty(userDevelopComp.getUuserid())) {
                                String umerchantUserid = userDevelopComp.getUuserid();
                                //店主分润
                                double ushopkeeperProfit = ushopkeeper * uprofit;
                                //保留两位小数
                                ushopkeeperProfit = formatDouble2(ushopkeeperProfit);

                                //经理分润
                                double uareaProfit = uarea * uprofit;
                                uareaProfit = formatDouble2(uareaProfit);
                                UtUsers shoper = userService.queryByID(umerchantUserid);
                                UtUsers areaManager = userService.getAreaManager(shoper);

                                //支付
                                if (type.equals("01")) {
                                    //存在推荐关系
                                    if (ushopkeeperProfit > 0) {
                                        //存在userid
                                        shareFinancelog(umerchantUserid,orderItem,ushopkeeperProfit);

                                        //利润提成推送
                                        UtThirdlogin thirdlogin = thirdloginService.selectInfoByUserid(umerchantUserid, "0");
                                        if (thirdlogin != null) {
                                            //标题
                                            String title = "店主利润提成";
                                            //门店
                                            String shop = ctComp.getUcompname();
                                            //服务
                                            String service = "利润提成";
                                            //时间
                                            String time = DateFormat.getDateTimeInstance(2, 2, Locale.CHINESE).format(new java.util.Date());
                                            //数据 (详细内容)
                                            String data = "用户ID" + users.getUusercode() + "名称:" + users.getUname() + "在您的店铺购买商品:" + orderItem.getUproductname() + "您获得利润提成:" + ushopkeeperProfit+"元";
                                            String templateid = "MNFURs3OtrK8tpRxPIjLE0MjCJwGEp3OH3XqRp9TGSQ";

                                            TemplateSendUtil.send_template_message(appid, appsecret, thirdlogin.getOpenid(), templateid, title, shop, service, time, data);
                                        }
                                    }

                                    //经理
                                    if (uareaProfit > 0) {

                                        //利润提成推送
                                        UtThirdlogin thirdlogin = thirdloginService.selectInfoByUserid(areaManager.getUuserid(), "0");
                                        //执行利润提成
                                        shareFinancelog(areaManager.getUuserid(),orderItem,uareaProfit);
                                        //利润提成推送
                                        if (thirdlogin != null) {
                                            //标题
                                            String title = "经理利润提成";
                                            //门店
                                            String shop = ctComp.getUcompname();
                                            //服务
                                            String service = "利润提成";
                                            //时间
                                            String time = DateFormat.getDateTimeInstance(2, 2, Locale.CHINESE).format(new java.util.Date());
                                            //数据 (详细内容)
                                            String data = "您团队的用户,ID:" + users.getUusercode() + "名称:" + users.getUname() + "购买了商品:" + orderItem.getUproductname() + "您获得利润提成:" + uareaProfit+"元";
                                            String templateid = "MNFURs3OtrK8tpRxPIjLE0MjCJwGEp3OH3XqRp9TGSQ";

                                            TemplateSendUtil.send_template_message(appid, appsecret, thirdlogin.getOpenid(), templateid, title, shop, service, time, data);
                                        }
                                    }


                                } else if (type.equals("00")) {

                                    if (ushopkeeper > 0) {
                                        //存在推荐关系
                                        if (ushopkeeperProfit > 0) {
                                            //存在userid
                                            returnShareFinancelog(umerchantUserid,orderItem,ushopkeeperProfit);

                                            //利润提成推送
                                            UtThirdlogin thirdlogin = thirdloginService.selectInfoByUserid(umerchantUserid, "0");
                                            if (thirdlogin != null) {
                                                //标题
                                                String title = "店主利润提成";
                                                //门店
                                                String shop = ctComp.getUcompname();
                                                //服务
                                                String service = "取消利润提成";
                                                //时间
                                                String time = DateFormat.getDateTimeInstance(2, 2, Locale.CHINESE).format(new java.util.Date());
                                                //数据 (详细内容)
                                                String data = "用户ID" + users.getUusercode() + "名称:" + users.getUname() + "取消了在您推荐的店铺购买商品:" + orderItem.getUproductname() + "的订单,您获得利润提成:" + ushopkeeperProfit+"元将被退回";
                                                String templateid = "MNFURs3OtrK8tpRxPIjLE0MjCJwGEp3OH3XqRp9TGSQ";
                                                TemplateSendUtil.send_template_message(appid, appsecret, thirdlogin.getOpenid(), templateid, title, shop, service, time, data);
                                            }
                                        }

                                        if (uareaProfit > 0) {

                                            UtThirdlogin thirdlogin = thirdloginService.selectInfoByUserid(areaManager.getUuserid(), "0");
                                            //执行利润提成
                                            returnShareFinancelog(areaManager.getUuserid(),orderItem,uareaProfit);
                                            //利润提成推送
                                            if (thirdlogin != null) {
                                                //标题
                                                String title = "经理利润提成";
                                                //门店
                                                String shop = ctComp.getUcompname();
                                                //服务
                                                String service = "取消利润提成";
                                                //时间
                                                String time = DateFormat.getDateTimeInstance(2, 2, Locale.CHINESE).format(new java.util.Date());
                                                //数据 (详细内容)
                                                String data = "您团队的用户,ID:" + users.getUusercode() + "名称:" + users.getUname() + "取消了购买商品:" + orderItem.getUproductname() + "的订单,您获得利润提成:" + uareaProfit+"元将被退回";
                                                String templateid = "MNFURs3OtrK8tpRxPIjLE0MjCJwGEp3OH3XqRp9TGSQ";

                                                TemplateSendUtil.send_template_message(appid, appsecret, thirdlogin.getOpenid(), templateid, title, shop, service, time, data);
                                            }
                                        }

                                    }

                                }



                            }

                        }


                    }
                }
            }

        }
    }



    private void saveCompFinancelog(String ucompid, UtOrderItem orderItem, Double uprofit) {
        CtComp ctComp = compService.queryByID(ucompid);
        if (!StringUtils.objectIsNull(ctComp)) {

            Double ubalance = ctComp.getUbalance() + uprofit;

            CtComp comp = new CtComp();
            comp.setUcompid(ucompid);
            comp.setUbalance(ubalance);
            //更新金额
            compService.updateBalanceByUcompid(comp);
            UtFinancelog utFinancelog = new UtFinancelog();
            //id
            utFinancelog.setUfinancelogid(KeyId.nextId());
            //订单id
            //userFinancelog.setUinfoid(utOrder.getUorderid());
            //获取第三方订单号
            //String transactionId = list.get(0).getTransactionId();
            //utFinancelog.setTransactionId(transactionId);
            //内部交易
            utFinancelog.setAccttype("2");
            if (StringUtils.StringisNotEmpty(ucompid)) {
                //id
                utFinancelog.setUuserid(ucompid);
            }
            //订单号
            utFinancelog.setUinfoid(orderItem.getUorderitemid());
            //状态
            utFinancelog.setUstatus("1");
            utFinancelog.setUtype("0");
            //类型利润提成
            utFinancelog.setUtradetype("5");
            utFinancelog.setUtrademoney(""+uprofit + "");
            //是否线上支付  0线上 1 线下
            utFinancelog.setIfline("0");

            utFinancelog.setUpaymode("利润提成");

            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            String time = sdf.format(date);

            //流水账号
            utFinancelog.setUglidenumber(time + KeyId.nextId());
            //                        utFinancelog.setUcreatedate(System.currentTimeMillis() / 1000 + "");
            financelogMapper.saveFinaceLog(utFinancelog);
        }
    }
    private void returnCompFinancelog(String ucompid, UtOrderItem orderItem, Double uprofit) {
        CtComp ctComp = compService.queryByID(ucompid);
        if (!StringUtils.objectIsNull(ctComp)) {

            Double ubalance = ctComp.getUbalance() - uprofit;

            CtComp comp = new CtComp();
            comp.setUcompid(ucompid);
            comp.setUbalance(ubalance);
            //更新金额
            compService.updateBalanceByUcompid(comp);
            UtFinancelog utFinancelog = new UtFinancelog();
            //id
            utFinancelog.setUfinancelogid(KeyId.nextId());
            //订单id
            //userFinancelog.setUinfoid(utOrder.getUorderid());
            //获取第三方订单号
            //String transactionId = list.get(0).getTransactionId();
            //utFinancelog.setTransactionId(transactionId);
            //内部交易
            utFinancelog.setAccttype("2");
            if (StringUtils.StringisNotEmpty(ucompid)) {
                //id
                utFinancelog.setUuserid(ucompid);
            }
            //订单号
            utFinancelog.setUinfoid(orderItem.getUorderitemid());
            //状态
            utFinancelog.setUstatus("1");
            utFinancelog.setUtype("0");
            //类型利润提成
            utFinancelog.setUtradetype("5");
            utFinancelog.setUtrademoney("-"+uprofit + "");
            //是否线上支付  0线上 1 线下
            utFinancelog.setIfline("0");

            utFinancelog.setUpaymode("取消利润提成");

            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            String time = sdf.format(date);

            //流水账号
            utFinancelog.setUglidenumber(time + KeyId.nextId());
            //                        utFinancelog.setUcreatedate(System.currentTimeMillis() / 1000 + "");
            financelogMapper.saveFinaceLog(utFinancelog);
        }
    }


    /**
     * @param uuserid   获得利润提成对象的id
     * @param orderItem 利润提成来源的订单项
     * @param uprofit   利润提成得到的金额
     */
    public synchronized void shareFinancelog(String uuserid,UtOrderItem orderItem,Double uprofit) {

        UtUsers users = userService.queryByID(uuserid);
        if (!StringUtils.objectIsNull(users)) {

            String ubalance = Double.parseDouble(users.getUbalance()) + uprofit + "";
            UtUsers utUsers = new UtUsers();
            utUsers.setUuserid(uuserid);
            utUsers.setUbalance(ubalance);
            //更新金额
            userService.updateByUuserid(utUsers);

            UtFinancelog utFinancelog = new UtFinancelog();
            //id
            utFinancelog.setUfinancelogid(KeyId.nextId());
            //订单id
            //userFinancelog.setUinfoid(utOrder.getUorderid());
            //获取第三方订单号
            //String transactionId = list.get(0).getTransactionId();
            //utFinancelog.setTransactionId(transactionId);
            //内部交易
            utFinancelog.setAccttype("2");
            if (StringUtils.StringisNotEmpty(uuserid)) {
                //id
                utFinancelog.setUuserid(uuserid);
            }
            //订单号
            utFinancelog.setUinfoid(orderItem.getUorderitemid());
            //状态
            utFinancelog.setUstatus("1");
            utFinancelog.setUtype("0");
            //类型利润提成
            utFinancelog.setUtradetype("5");
            utFinancelog.setUtrademoney(uprofit+"");
            //是否线上支付  0线上 1 线下
            utFinancelog.setIfline("0");
            utFinancelog.setUcompid(orderItem.getUcompid());
            utFinancelog.setUpaymode("利润提成");

            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            String time = sdf.format(date);

            //流水账号
            utFinancelog.setUglidenumber(time + KeyId.nextId());
    //                        utFinancelog.setUcreatedate(System.currentTimeMillis() / 1000 + "");
            financelogMapper.saveFinaceLog(utFinancelog);
        }

    }

    /**
     * @param uuserid   获得利润提成对象的id
     * @param orderItem 利润提成来源的订单项
     * @param uprofit   利润提成得到的金额
     */
    public synchronized void returnShareFinancelog(String uuserid,UtOrderItem orderItem,Double uprofit) {

        UtUsers users = userService.queryByID(uuserid);
        if (!StringUtils.objectIsNull(users)) {

            String ubalance = Double.parseDouble(users.getUbalance()) - uprofit + "";
            UtUsers utUsers = new UtUsers();
            utUsers.setUuserid(uuserid);
            utUsers.setUbalance(ubalance);
            //更新金额
            userService.updateByUuserid(utUsers);

            UtFinancelog utFinancelog = new UtFinancelog();
            //id
            utFinancelog.setUfinancelogid(KeyId.nextId());

            utFinancelog.setUcompid(orderItem.getUcompid());
            //订单id
            //userFinancelog.setUinfoid(utOrder.getUorderid());
            //获取第三方订单号
            //String transactionId = list.get(0).getTransactionId();
            //utFinancelog.setTransactionId(transactionId);
            //内部交易
            utFinancelog.setAccttype("2");
            if (StringUtils.StringisNotEmpty(uuserid)) {
                //id
                utFinancelog.setUuserid(uuserid);
            }
            //订单号
            utFinancelog.setUinfoid(orderItem.getUorderitemid());
            //状态
            utFinancelog.setUstatus("1");
            utFinancelog.setUtype("0");
            //类型利润提成
            utFinancelog.setUtradetype("5");
            utFinancelog.setUtrademoney(-uprofit+"");
            //是否线上支付  0线上 1 线下
            utFinancelog.setIfline("0");

            utFinancelog.setUpaymode("取消利润提成");

            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            String time = sdf.format(date);

            //流水账号
            utFinancelog.setUglidenumber(time + KeyId.nextId());
            //                        utFinancelog.setUcreatedate(System.currentTimeMillis() / 1000 + "");
            financelogMapper.saveFinaceLog(utFinancelog);
        }

    }









    public int queryFinancelogCount(UtFinancelog ufinancelog) {
        return financelogMapper.queryFinancelogCount(ufinancelog);
    }

    public List<Map<String,Object>> queryFinancelogListMap(UtFinancelog ufinancelog) {
        return financelogMapper.queryFinancelogListMap(ufinancelog);
    }

    //用户成为VIP
    @Transactional
    public void saveUserVip(List<UtUsers> usersList, List<UtFinancelog> financelogList) {
       /* for (UtUsers users : usersList) {
            int i = userService.updateByUuserid(users);
          }
*/
        CtWxpayConfig ctWxpayConfig = new CtWxpayConfig();
        ctWxpayConfig.setUcustomerid(ucustomerid);
        //0微信 1支付宝
        ctWxpayConfig.setUtypes("0");
        List<CtWxpayConfig> configs = wxpayConfigService.queryList(ctWxpayConfig);
        String appid = "";
        String appsecret = "";
        if (configs.size() > 0) {
            appid = configs.get(0).getAppid();
            appsecret = configs.get(0).getAppsecret();
        }
            UtUsers thisUser = userService.queryByID(usersList.get(0).getUuserid());
        for (int i = 0; i < usersList.size(); i++) {
            UtUsers users = usersList.get(i);
            int res = userService.updateByUuserid(users);
            if (res > 0) {

                UtThirdlogin thirdlogin = thirdloginService.selectInfoByUserid(users.getUuserid(), "0");
                if (thirdlogin != null) {
                    if (i == 0) {
                        //该用户
                        TemplateSendUtil.send_template_message(appid,appsecret,thirdlogin.getOpenid(),"MNFURs3OtrK8tpRxPIjLE0MjCJwGEp3OH3XqRp9TGSQ","恭喜成为VIP",Constant.ucustomername,"支付成为VIP",DateFormat.getDateTimeInstance(2, 2, Locale.CHINESE).format(new java.util.Date()),"");
                    }else{
                        //推荐人用户
                        TemplateSendUtil.send_template_message(appid,appsecret,thirdlogin.getOpenid(),"MNFURs3OtrK8tpRxPIjLE0MjCJwGEp3OH3XqRp9TGSQ","恭喜您推荐的用户ID:"+thisUser.getUusercode()+"名称:"+thisUser.getUname()+"成为VIP,您将获得利润提成100元",Constant.ucustomername,"支付成为VIP",DateFormat.getDateTimeInstance(2, 2, Locale.CHINESE).format(new java.util.Date()),"");
                    }
                }
            }
        }
        for (UtFinancelog utFinancelog : financelogList) {
            financelogMapper.saveFinaceLog(utFinancelog);
        }


    }












    public static double formatDouble2(double d) {
        // 旧方法，已经不再推荐使用
//        BigDecimal bg = new BigDecimal(d).setScale(2, BigDecimal.ROUND_HALF_UP);
        return StringUtils.round(d, 2);
//        // 新方法，如果不需要四舍五入，可以使用RoundingMode.DOWN
//        BigDecimal bg = new BigDecimal(d).setScale(2, RoundingMode.UP);
//
//
//        return bg.doubleValue();



    }
}
