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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/h5/cashout/")
public class UtUserCashOutController extends BaseController {

    @Autowired
    private UtThirdloginService thirdloginService;

    @Autowired
    private UtUsercashoutService usercashoutService;
    @Autowired
    private CtWxpayConfigService wxpayConfigService;
    @Autowired
    private CtProfitService profitService;
    @Autowired
    private UtBankinfoService bankinfoService;
    @Value("${ucompid}")
    private String ucompid;

    @Value("${ucustomerid}")
    private String ucustomerid;

    @Autowired
    private UtUserService userService;

    @RequestMapping("cashoutPage")
    public String cashoutPage(@SessionAttribute(Constant.SESSION_H5USER) UtUsers user,Model model) {
        CtProfit ctProfit = profitService.queryProfitByCompId(ucompid, ucustomerid);

        if (ctProfit != null) {
            model.addAttribute("uservicefee", ctProfit.getUservicefee());
        }else{
            model.addAttribute("uservicefee", 0.00);
        }
        List<UtBankinfo> bankCardList = bankinfoService.getBankCardList(user.getUuserid());
        model.addAttribute("banklist", bankCardList);


        return "templates/weui/tixian";
    }


    @RequestMapping("applyCashout")
    @ResponseBody
    public AjaxResult applyCashout(@SessionAttribute(Constant.SESSION_H5USER) UtUsers user, Model model, Double cash, String uwithdrawtype,String ubankid) {
        //获取到用户最新状态
        UtUsers users = userService.queryByID(user.getUuserid());
        Double ubalance = Double.valueOf(users.getUbalance());

        //用户余额
        BigDecimal bubalance = new BigDecimal(ubalance);
      /*  int a = bigdemical.compareTo(bigdemical2)
        a = -1,表示bigdemical小于bigdemical2；
        a = 0,表示bigdemical等于bigdemical2；
        a = 1,表示bigdemical大于bigdemical2；
       */
        //提现金额
        BigDecimal bcash = new BigDecimal(cash);
        //表示可提现的余额小于用户申请提现余额
        if (bubalance.compareTo(bcash) < 0) {
            return new AjaxResult(0, "提现金额错误,用户余额不足");
        }

        CtProfit ctProfit = profitService.queryProfitByCompId(ucompid, ucustomerid);
        Double uservicefee = ctProfit.getUservicefee();
        double fee =  cash * uservicefee;
        BigDecimal bigfee = new BigDecimal(fee);
      /*  //小于零则代表余额不足
        if (bubalance.subtract(bcash).compareTo(bigfee)<0) {
            return new AjaxResult(0, "您的提现后余额不足以支付提现手续费,手续费为" + fee + "提现后余额为" + (ubalance - cash)+"手续费为");
        }
*/
        String uuserid = users.getUuserid();


        //创建用户申请提现数据
        UtUsercashout usercashout = new UtUsercashout();
        usercashout.setUcashoutid(nextId());
        //设置商户号及经销商号
        usercashout.setUcompid(ucompid);

        usercashout.setUcustomerid(ucustomerid);

        usercashout.setUuserid(uuserid);
        usercashout.setUstatus("0");
        usercashout.setUtrademoney(cash+"");
        usercashout.setUglidenumber(getGlidenumber());
        // 服务费

        usercashout.setUfee(fee+"");
        BigDecimal newbalance = bubalance.subtract(bcash);
        UtUsers utUsers = new UtUsers();
        utUsers.setUbalance(newbalance.toString());
        utUsers.setUuserid(users.getUuserid());
        usercashout.setUwithdrawtype(uwithdrawtype);

        //申请微信提现
        if (uwithdrawtype.equalsIgnoreCase("0")) {
            UtThirdlogin thirdlogin = thirdloginService.selectInfoByUserid(uuserid, "0");
            //判断是否存在绑定
            if (StringUtils.objectIsNull(thirdlogin)) {
                return new AjaxResult(0, "用户尚未绑定微信");
            }

        } else if (uwithdrawtype.equals("1")) {
            UtBankinfo utBankinfo = bankinfoService.getBankCardByBankid(ubankid);
            if (utBankinfo != null) {
                usercashout.setUbankid(ubankid);
                usercashout.setUbankcardno(utBankinfo.getUbankCardno());
            }else{
                return new AjaxResult(0, "请检查银行卡绑定状态");
            }

        }
        AjaxResult ajaxResult = usercashoutService.addUserCashout(usercashout,utUsers);
        return ajaxResult;

//        return new AjaxResult(0, "发生未知错误");

    }

    @RequestMapping("cashoutRecordPage")
    public String cashoutRecordPage(@SessionAttribute(Constant.SESSION_H5USER) UtUsers user, Model model,Integer pageNo,Integer pageSize) {

        UtUsercashout utUsercashout = new UtUsercashout();
        utUsercashout.setUuserid(user.getUuserid());

        int total = usercashoutService.queryCount(utUsercashout);

        if (pageSize == null || pageSize < 1) {
            pageSize = 5;
        }

        if (StringUtils.objectIsNull(pageNo) || pageNo <1) {
            pageNo = 1;
        }

        int pagenum = 1;
        //如果页数刚好
        if (total % pageSize == 0) {
            pagenum = total / pageSize; //页数
        }else{
            pagenum = total / pageSize +1;
        }

        //如果传来的页数大于总页数则显示最后页
        if (pageNo > pagenum) {
            pageNo = pagenum;
        }
        int searchpageNo = 0;
        if (pageNo > 0) {

            searchpageNo = (pageNo - 1) * pageSize;
        }

        List<Map<String, Object>> maps = usercashoutService.ListByUserid(user.getUuserid(), searchpageNo, pageSize);
        for (Map<String, Object> map : maps) {
            String ustatus = (String) map.get("ustatus");
            if (ustatus.equals("0")) {
                map.put("ustatus", "申请中");
            }
            if (ustatus.equals("1")) {
                map.put("ustatus", "申请通过");
            }
            if (ustatus.equals("3")) {
                map.put("ustatus", "提现成功");
            }
            if (ustatus.equals("2")) {
                map.put("ustatus", "失败");
            }

        }

        model.addAttribute("pageNo", pageNo);
        model.addAttribute("pagenum", pagenum);


        model.addAttribute("maps", maps);


        return "templates/weui/cashout";
    }








}
