package com.zdv.shop.controller;

import com.zdv.shop.common.Constant;
import com.zdv.shop.common.annotation.ControllerLog;
import com.zdv.shop.common.pojo.AjaxResult;
import com.zdv.shop.common.utils.KeyId;
import com.zdv.shop.common.utils.StringUtils;
import com.zdv.shop.model.*;
import com.zdv.shop.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 钱包内容
 */
@RequestMapping("/h5/wallet/")
@Controller
public class H5WalletController extends BaseController {

    @Autowired
    private UtUserService userService;

    @Autowired
    private UtOrderService orderService;
    @Autowired
    private CtCompService compService;

    @Autowired
    private UtFinancelogService financelogService;

    @Autowired
    private CtWxpayConfigService wxpayConfigService;
    @Autowired
    private UtUsercashoutService usercashoutService;

    @Autowired
    private HttpSession session;
    @Value("${ucompid}")
    private String ucompid;
    @Value("${ucustomerid}")
    private String ucustomerid;

    @ControllerLog("钱包页面")
    @RequestMapping("account_detail")
    public String account_detail(Model model,@SessionAttribute(Constant.SESSION_H5USER)UtUsers users) {
        UtUsers utUsers = userService.queryByID(users.getUuserid());
        model.addAttribute("user", utUsers);
        return "templates/h5/account_detail";
    }
    
    @ControllerLog("新钱包页面")
    @RequestMapping("weuiaccount_detail")
    public String weuiaccount_detail(Model model,@SessionAttribute(Constant.SESSION_H5USER)UtUsers users) {
        UtUsers utUsers = userService.queryByID(users.getUuserid());

        UtUsercashout utUsercashout = new UtUsercashout();
        utUsercashout.setUuserid(users.getUuserid());
        utUsercashout.setUstatus("3");
        List<UtUsercashout> list = usercashoutService.queryList(utUsercashout);
        Double totalWithdraw = 0.00;
        for (UtUsercashout usercashout : list) {

            Double utrademoney = Double.parseDouble(usercashout.getUtrademoney());

            totalWithdraw += utrademoney;
        }
        model.addAttribute("totalWithdraw", totalWithdraw);



        model.addAttribute("user", utUsers);

        UtOrder utOrder = new UtOrder();
        utOrder.setUuserid(users.getUuserid());
        utOrder.setUcustomerid(ucustomerid);

        Double consumption = orderService.queryConsumption(utOrder);


        model.addAttribute("consumption", consumption);
        return "templates/weui/myburse";
    }

    @ControllerLog("零钱转赠页面")
    @RequestMapping("zhuanzengPage")
    public String zhuanzengPage(Model model,@SessionAttribute(Constant.SESSION_H5USER)UtUsers users) {
        UtUsers utUsers = userService.queryByID(users.getUuserid());
        model.addAttribute("user", utUsers);
        return "templates/weui/zhuanzeng";
    }


    @ControllerLog("转赠零钱")
    @RequestMapping("zhuanzeng")
    @ResponseBody
    public AjaxResult zhuanzeng(@SessionAttribute(Constant.SESSION_H5USER)UtUsers users,String uloginname,Double cash) {
        UtUsers utUsers = userService.queryByID(users.getUuserid());
        Double balance = Double.parseDouble(utUsers.getUbalance());

        if (cash == null) {
            return new AjaxResult(0, "输入金额错误");
        }

        String[] cashs = (cash + "").split(".");
        if (cashs.length > 2) {
            return new AjaxResult(0, "输入金额错误");
        } else if (cashs.length == 2) {
            if (cashs[1].length()>2) {
                return new AjaxResult(0, "输入金额错误");
            }
        }


        if (cash <= 0) {
            return new AjaxResult(0, "转赠金额不能低于0");
        }

        if (cash > balance) {
            return new AjaxResult(0,"余额不足");
        }
        UtUsers toUser =userService.selectByUloginame(uloginname);

        if (toUser != null) {
            Double tbalance = Double.parseDouble(toUser.getUbalance())+cash;
            toUser.setUbalance(tbalance + "");


            utUsers.setUbalance((balance - cash) + "");

            //当前用户日志
            UtFinancelog utFinancelog = new UtFinancelog();
            utFinancelog.setUcompid(ucompid);
            utFinancelog.setUuserid(utUsers.getUuserid());
            utFinancelog.setUinfoid(toUser.getUuserid());
            utFinancelog.setUtrademoney("-"+cash);
            utFinancelog.setUtradetype("4");
            utFinancelog.setUglidenumber(getGlidenumber());
            utFinancelog.setAccttype("2");
            utFinancelog.setUfinancelogid(nextId());
            utFinancelog.setUtype("0");
            utFinancelog.setUpaymode("转账");

            //直接成功
            utFinancelog.setUstatus("1");
//            financelogService.saveFinaceLog(utFinancelog);
            //对象用户日志
            UtFinancelog toFinancelog = new UtFinancelog();
            toFinancelog.setUcompid(ucompid);
            toFinancelog.setUuserid(toUser.getUuserid());
            toFinancelog.setUinfoid(utUsers.getUuserid());
            toFinancelog.setUtrademoney(""+cash);
            toFinancelog.setUtradetype("4");
            toFinancelog.setUglidenumber(getGlidenumber());
            toFinancelog.setUfinancelogid(nextId());
            toFinancelog.setAccttype("2");
            toFinancelog.setUtype("0");
            toFinancelog.setUpaymode("接收转账");
            //直接成功
            toFinancelog.setUstatus("1");
//            financelogService.saveFinaceLog(toFinancelog);


            return userService.zhuanzeng(utUsers, toUser,utFinancelog,toFinancelog,cash);

        }

        return new AjaxResult(0, "转赠用户不存在");

    }


    @RequestMapping("walletPay")
    @ResponseBody
    public AjaxResult walletPay(String uorderid,String radetype) {

        UtOrder order = orderService.queryByID(uorderid);
        UtUsers user = (UtUsers) session.getAttribute(Constant.SESSION_H5USER);
        //如果
        if (user == null || !user.getUuserid().equals(order.getUuserid())) {
            return new AjaxResult(0, "订单不匹配");
        }
        if (!order.getUeflag().equals("00")) {
            return new AjaxResult(0, "该订单已付款");
        }

        UtUsers users = userService.queryByID(user.getUuserid());
        Double ubalance = Double.valueOf(users.getUbalance());
        Double upayprice = Double.valueOf(order.getUpayprice());

        //如果用户余额小于订单支付余额
        if (ubalance < upayprice) {
            return new AjaxResult(0, "余额不足");
        }

        ubalance -= upayprice;
        UtUsers updateUser = new UtUsers();
        updateUser.setUuserid(user.getUuserid());
        updateUser.setUbalance(ubalance+"");

        order.setUeflag("01");
        order.setUpaytype("4");
        order.setUpaystate("1");
//        orderService.updateOrder(order);

        //支付后创建财务日志
        UtFinancelog utFinancelog = new UtFinancelog();
        //店铺id
        utFinancelog.setUcompid(order.getUcompid());
        //用户id
        utFinancelog.setUuserid(order.getUuserid());
        //订单id
        utFinancelog.setUinfoid(uorderid);
        //状态   正在处理.若完成交易(用户评论之后)则状态变为1
        utFinancelog.setUstatus("0");
        //
        utFinancelog.setUtradetype(radetype);
        //支付金额
        utFinancelog.setUtrademoney("-"+order.getUpayprice());
        //流水账号
        utFinancelog.setUglidenumber(getGlidenumber());
        //是否线上支付  0线上 1 线下
        utFinancelog.setIfline("0");
        //零钱
        utFinancelog.setUpaymode("零钱");
        //第三方订单号
//        utFinancelog.setTransactionId(transaction_id);
        //时间
//        utFinancelog.setUcreatedate();

 /*       //创建企业财务日志
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
//        financelogService.saveFinaceLog(utFinancelog);
        return orderService.walletPay(order, utFinancelog, updateUser);

    }









    @ControllerLog("申请记录")
    @RequestMapping("account_log")
    public String account_log(Model model,@SessionAttribute(Constant.SESSION_H5USER)UtUsers users,UtFinancelog ufinancelog) {
        ufinancelog.setUuserid(users.getUuserid());
        ufinancelog.setPageSize(10);
        ufinancelog.setUuserid(users.getUuserid());
        ufinancelog.setUstatus("0");
        ufinancelog.setUtype("0");
        int total = financelogService.queryFinancelogCount(ufinancelog);
        Integer pageNo= 1;
        if (!StringUtils.objectIsNull(ufinancelog.getPageNo())&&ufinancelog.getPageNo().intValue()>0) {
            pageNo = ufinancelog.getPageNo();
        }
        int pagenum = 1;
        //如果页数刚好
        if (total % ufinancelog.getPageSize() == 0) {
            pagenum = total / ufinancelog.getPageSize(); //页数
        }else{
            pagenum = total / ufinancelog.getPageSize() +1;
        }
        //如果传来的页数大于总页数则显示最后页
        if (pageNo > pagenum) {
            ufinancelog.setPageNo(pagenum);
        }else{
            ufinancelog.setPageNo(pageNo);
        }

        if (ufinancelog.getPageNo() >= 1) {
            ufinancelog.setPageNo((ufinancelog.getPageNo()-1)*ufinancelog.getPageSize());
        }


        List<UtFinancelog> list = financelogService.queryFinancelogList(ufinancelog);


/*
        UtFinancelog financelog = new UtFinancelog();
        financelog.setUuserid(users.getUuserid());
        financelog.setUtype("0");

        List<UtFinancelog> list = financelogService.queryFinancelogList(financelog);*/

        for (UtFinancelog utFinancelog : list) {
            String accttype = utFinancelog.getAccttype();
            if (accttype.equals("0")) {
                utFinancelog.setAccttype("进账");
            } else if (accttype.equals("1")) {
                utFinancelog.setAccttype("出账");
            } else if (accttype.equals("2")) {
                utFinancelog.setAccttype("内部交易");
            } else if (accttype.equals("3")) {
                utFinancelog.setAccttype("分润");
            }
            String utradetype = utFinancelog.getUtradetype();
            if (utradetype.equals("0")) {
                utFinancelog.setUtradetype("充值");
            } else if (utradetype.equals("1")) {
                utFinancelog.setUtradetype("支付");
            } else if (utradetype.equals("2")) {
                utFinancelog.setUtradetype("余额扣款");
            } else if (utradetype.equals("3")) {
                utFinancelog.setUtradetype("其他");
            }
            String ustatus = utFinancelog.getUstatus();
            if (ustatus.equals("0")) {
                utFinancelog.setUstatus("正在处理");
            } else if (ustatus.equals("1")) {
                utFinancelog.setUstatus("处理成功");
            } else if (ustatus.equals("2")) {
                utFinancelog.setUstatus("处理失败");
            }



        }
        model.addAttribute("utradetype", ufinancelog.getUtradetype());
        model.addAttribute("list", list);
        model.addAttribute("pageNo", ufinancelog.getPageNo());
        model.addAttribute("pagenum", pagenum);



        return "templates/h5/account_log";
    }
    @ControllerLog("充值页面")
    @RequestMapping("account_deposit")
    public String account_deposit(Model model,@SessionAttribute(Constant.SESSION_H5USER)UtUsers users) {

        return "templates/h5/account_deposit";
    }
    @ControllerLog("提现页面")
    @RequestMapping("account_raply")
    public String account_raply(Model model,@SessionAttribute(Constant.SESSION_H5USER)UtUsers users) {

        return "templates/h5/account_raply";
    }
    @ControllerLog("付款页面")
    @RequestMapping("act_account")
    public String act_account(String act, String surplus_type, String rec_id, String payment_id, BigDecimal utrademoney, String utradetype,
                              Model model, @SessionAttribute(Constant.SESSION_H5USER)UtUsers users) {
        //微信支付
        if (payment_id.equalsIgnoreCase("1")) {
            model.addAttribute("payment_name", "微信支付");
            model.addAttribute("payment_desc", "使用微信支付");
        }

        UtFinancelog utFinancelog = new UtFinancelog();
        //设置id
        utFinancelog.setUfinancelogid(nextId());
        //设置
        utFinancelog.setUcompid(ucompid);

        utFinancelog.setUuserid(users.getUuserid());

        utFinancelog.setUinfoid(users.getUuserid());
        //设置状态,正在处理
        utFinancelog.setUstatus("0");

        utFinancelog.setUtype("0");

        utFinancelog.setUtradetype(utradetype);

        utFinancelog.setUtrademoney("-"+utrademoney.doubleValue()+"");

        //流水账号
        utFinancelog.setUglidenumber(getGlidenumber());

        utFinancelog.setIfline("0");
//        进出类型
        utFinancelog.setAccttype("0");

        financelogService.saveFinaceLog(utFinancelog);

        model.addAttribute("utrademoney", utrademoney);
        model.addAttribute("utradetype", utradetype);







        return "templates/h5/act_account";
    }

    @RequestMapping("account_list")
    public String account_list(Model model,@SessionAttribute(Constant.SESSION_H5USER)UtUsers users,UtFinancelog ufinancelog) {
        ufinancelog.setPageSize(3);
        ufinancelog.setUuserid(users.getUuserid());
        ufinancelog.setUtype("0");
        int total = financelogService.queryFinancelogCount(ufinancelog);
        Integer pageNo= 1;
        if (!StringUtils.objectIsNull(ufinancelog.getPageNo())&&ufinancelog.getPageNo().intValue()>0) {
            pageNo = ufinancelog.getPageNo();
        }
        int pagenum = 1;
        //如果页数刚好
        if (total % ufinancelog.getPageSize() == 0) {
            pagenum = total / ufinancelog.getPageSize(); //页数
        }else{
            pagenum = total / ufinancelog.getPageSize() +1;
        }
        //如果传来的页数大于总页数则显示最后页
        if (pageNo > pagenum) {
            ufinancelog.setPageNo(pagenum);
        }else{
            ufinancelog.setPageNo(pageNo);
        }

        if (ufinancelog.getPageNo() >= 1) {
            ufinancelog.setPageNo((ufinancelog.getPageNo()-1)*ufinancelog.getPageSize());
        }


        List<UtFinancelog> list = financelogService.queryFinancelogList(ufinancelog);


/*
        UtFinancelog financelog = new UtFinancelog();
        financelog.setUuserid(users.getUuserid());
        financelog.setUtype("0");

        List<UtFinancelog> list = financelogService.queryFinancelogList(financelog);*/

        for (UtFinancelog utFinancelog : list) {
            String accttype = utFinancelog.getAccttype();
            if (accttype.equals("0")) {
                utFinancelog.setAccttype("进账");
            } else if (accttype.equals("1")) {
                utFinancelog.setAccttype("出账");
            } else if (accttype.equals("2")) {
                utFinancelog.setAccttype("内部交易");
            } else if (accttype.equals("3")) {
                utFinancelog.setAccttype("分润");
            }
        }
        model.addAttribute("utradetype", ufinancelog.getUtradetype());
        model.addAttribute("list", list);
        model.addAttribute("pageNo", ufinancelog.getPageNo());
        model.addAttribute("pagenum", pagenum);
        return  "templates/h5/account_list";
    }


    @RequestMapping("transferPage")
    public String transferPage(Model model,UtFinancelog ufinancelog) {
        UtUsers users = (UtUsers) session.getAttribute(Constant.SESSION_H5USER);
        if (users == null) {
            return "templates/weui/login";
        }
        String utradetype = ufinancelog.getUtradetype();

        model.addAttribute("utradetype", utradetype);

        if (!StringUtils.StringisNotEmpty(utradetype)) {
            utradetype = "全部";
        }
        if (utradetype.equals("充值")) {
            ufinancelog.setUtradetype("0");
        } else if (utradetype.equals("微信支付")) {
            ufinancelog.setUtradetype("1");

        }else if (utradetype.equals("余额支付")) {
            ufinancelog.setUtradetype("2");

        }else if (utradetype.equals("其他")) {
            ufinancelog.setUtradetype("3");

        }  else if (utradetype.equals("转账记录")) {
            ufinancelog.setUtradetype("4");
        }
        else if (utradetype.equals("利润提成")) {
            ufinancelog.setUtradetype("5");
        }
        else if (utradetype.equals("余额退款")) {
            ufinancelog.setUtradetype("6");
        }
        else if (utradetype.equals("微信退款")) {
            ufinancelog.setUtradetype("7");
        }
        else if (utradetype.equals("受转账记录")) {
            ufinancelog.setUtradetype("8");
        }
        else if (utradetype.equals("提现记录")) {
            ufinancelog.setUtradetype("9");
        } else {
            ufinancelog.setUtradetype("");
            model.addAttribute("utradetype", "全部");
        }

        ufinancelog.setPageSize(5);
        ufinancelog.setUuserid(users.getUuserid());
        ufinancelog.setUtype("0");
        int total = financelogService.queryFinancelogCount(ufinancelog);
        Integer pageNo= 1;
        if (!StringUtils.objectIsNull(ufinancelog.getPageNo())&&ufinancelog.getPageNo().intValue()>0) {
            pageNo = ufinancelog.getPageNo();
        }
        int pagenum = 1;
        //如果页数刚好
        if (total % ufinancelog.getPageSize() == 0) {
            pagenum = total / ufinancelog.getPageSize(); //页数
        }else{
            pagenum = total / ufinancelog.getPageSize() +1;
        }
        //如果传来的页数大于总页数则显示最后页
        if (pageNo > pagenum) {
            ufinancelog.setPageNo(pagenum);
        }else{
            ufinancelog.setPageNo(pageNo);
        }


        if (ufinancelog.getPageNo() > 0) {
            ufinancelog.setPageNo((ufinancelog.getPageNo()-1)*ufinancelog.getPageSize());
        }
        List<UtFinancelog> list = financelogService.queryFinancelogList(ufinancelog);
        List<Map<String, Object>> maps = financelogService.queryFinancelogListMap(ufinancelog);

        //如果传来的页数大于总页数则显示最后页
        if (pageNo > pagenum) {
            ufinancelog.setPageNo(pagenum);
        }else{
            ufinancelog.setPageNo(pageNo);
        }

        for (Map<String, Object> map : maps) {
            String uinfoid =  map.get("uinfoid").toString();
            String utradetype1 = (String) map.get("utradetype");
            map.put("ulogo", "");
            map.put("uusercode", "");

            if (utradetype1.equals("4")) {
                UtUsers touser = userService.queryByID(uinfoid);
                if (!StringUtils.objectIsNull(touser)) {
                    map.put("ulogo", touser.getUlogo());
                    map.put("uname", touser.getUname());
                    map.put("uusercode", touser.getUusercode());
                }

                map.put("utradetype", "转账ID:");
            } else if (utradetype1.equals("0")) {
                map.put("utradetype", "充值");
            } else if (utradetype1.equals("1")) {
                UtOrder utOrder = orderService.queryByID(uinfoid);
                if (!StringUtils.objectIsNull(utOrder)) {
                    map.put("uusercode", utOrder.getUorderno());
                }
                map.put("utradetype", "微信支付,订单号:");
            } else if (utradetype1.equals("2")) {
                UtOrder utOrder = orderService.queryByID(uinfoid);
                if (!StringUtils.objectIsNull(utOrder)) {
                    map.put("uusercode", utOrder.getUorderno());
                }

                map.put("utradetype", "余额支付,订单号:");
            }else if (utradetype1.equals("3")) {
                map.put("uusercode","");

                map.put("utradetype", "其他");

            } else if (utradetype1.equals("5")) {
                map.put("utradetype", "利润提成");
            }else if (utradetype1.equals("6")) {

                map.put("utradetype", "余额退款");
            }else if (utradetype1.equals("7")) {
                map.put("utradetype", "微信退款");

            }else if (utradetype1.equals("8")) {

            }else if (utradetype1.equals("9")) {
                map.put("utradetype", "提现至微信");

            }
        }
/*
        UtFinancelog financelog = new UtFinancelog();
        financelog.setUuserid(users.getUuserid());
        financelog.setUtype("0");

        List<UtFinancelog> list = financelogService.queryFinancelogList(financelog);*/

        for (UtFinancelog utFinancelog : list) {
            String accttype = utFinancelog.getAccttype();
            if (accttype.equals("0")) {
                utFinancelog.setAccttype("进账");
            } else if (accttype.equals("1")) {
                utFinancelog.setAccttype("出账");
            } else if (accttype.equals("2")) {
                utFinancelog.setAccttype("内部交易");
            } else if (accttype.equals("3")) {
                utFinancelog.setAccttype("分润");
            }
        }
//        model.addAttribute("utradetype", ufinancelog.getUtradetype());
        model.addAttribute("list", list);
        model.addAttribute("maps", maps);
        model.addAttribute("pageNo", ufinancelog.getPageNo());
        model.addAttribute("pagenum", pagenum);

        return "templates/weui/transfer";

    }


    @RequestMapping("rechargePage")
    public String rechargePage() {
        return "templates/weui/recharge";
    }


    @RequestMapping("recharge")
    public String recharge(String money) throws UnsupportedEncodingException {
        UtUsers sessionUser = (UtUsers) session.getAttribute(Constant.SESSION_H5USER);
        UtUsers users = userService.queryByID(sessionUser.getUuserid());

        CtComp ctComp = compService.queryByID(ucompid);
        UtOrder utOrder = new UtOrder();
        utOrder.setUorderid(nextId());
        utOrder.setUorderno(getOrderDateNo());
        utOrder.setUcompid(ucompid);
        utOrder.setUpayprice(money);
        utOrder.setUproductname("充值余额");
        utOrder.setUuserid(users.getUuserid());
        utOrder.setUtotalprice(money);
        utOrder.setUpaystate("0");
        utOrder.setUshoptype("0");
        utOrder.setUeflag("00");
        utOrder.setUcaddress(ctComp.getUaddress());
        List<UtOrder> list = new ArrayList<>();
        list.add(utOrder);
        orderService.addOrderList(list, new ArrayList<>(),new ArrayList<>());



        String state = utOrder.getUorderid() + "-0";

        HttpServletRequest request = getRequest();
        StringBuffer sb = request.getRequestURL();
        String shopurl = sb.delete(sb.length() - request.getRequestURI().length(), sb.length()).toString();

        String uri =URLEncoder.encode(shopurl+"/h5/weixin/repay", "GBK");
        CtWxpayConfig ctWxpayConfig = new CtWxpayConfig();
        ctWxpayConfig.setUcustomerid(ucustomerid);
        //0微信 1支付宝
        ctWxpayConfig.setUtypes("0");
        List<CtWxpayConfig> configs = wxpayConfigService.queryList(ctWxpayConfig);
        String appid = "";
        if (configs.size() > 0) {
            CtWxpayConfig config = configs.get(0);
            //获取appid
            appid = config.getAppid();
        }
        String codeUrl = "https://open.weixin.qq.com/connect/oauth2/authorize";
        String url = codeUrl + "?appid=" + appid + "&redirect_uri=" + uri + "&response_type=code&scope=snsapi_base&state=" + state +"#wechat_redirect";
//
        return  "redirect:"+url+"";

    }

}
