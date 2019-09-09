package com.zdv.shop.controller;

import com.zdv.shop.common.Constant;
import com.zdv.shop.common.pojo.AjaxResult;
import com.zdv.shop.common.utils.BankUtil;
import com.zdv.shop.model.UtBankinfo;
import com.zdv.shop.model.UtUsers;
import com.zdv.shop.service.*;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/h5/userinfo")
public class H5UserinfoController extends BaseController {
    @Autowired
    private UtFinancelogService financelogService;
    @Autowired
    private UtUserService service;
    @Autowired
    private MessageService messageService;
    @Autowired
    private HttpSession session;
    @Autowired
    private LogService logService;
    @Autowired
    private UtBankinfoService bankService;
    @Value("${ucustomerid}")
    private String ucustomerid;

    @PostMapping("updatePwd")
    @ResponseBody
    public AjaxResult updatePwd(String upassword, String newPwd,
                                @SessionAttribute(Constant.SESSION_H5USER)UtUsers user) {
        if (!user.getUpassword().equals(upassword))
            return returnFailed("原密码有误");
        else if (user.getUpassword().equals(newPwd))
            return returnFailed("新密码不能与原密码相同");
        UtUsers arg = new UtUsers();
        arg.setUuserid(user.getUuserid());
        arg.setUpassword(newPwd);
        if (service.updateByID(arg) > 0) {
            session.invalidate();
            return returnSuccess("修改密码成功");
        }
        return returnFailed("修改密码失败");
    }

    @GetMapping("personalInfo")
    public String personalInfo(Model model, @SessionAttribute(Constant.SESSION_H5USER)UtUsers user) {
        UtUsers users = service.queryByID(user.getUuserid());
        model.addAttribute("user", users);

        return "templates/h5/personal_info";
    }
    @GetMapping("weuipersonalInfo")
    public String weuipersonalInfo(Model model, @SessionAttribute(Constant.SESSION_H5USER)UtUsers user) {
        UtUsers users = service.queryByID(user.getUuserid());
        model.addAttribute("user", users);
        return "templates/weui/personal_info";
    }

    @RequestMapping("bindUmobile")
    @ResponseBody
    public AjaxResult bindUmobile(@SessionAttribute(Constant.SESSION_H5USER) UtUsers user, String umobile, String codeno) {
        boolean b = logService.selectCodeBymobileCount(codeno, umobile) > 0;
        if (b) {
            if (StringUtils.isNotEmpty(umobile)) {

                int i = service.queryCountByUmobileOnCompany(umobile,ucustomerid);
                if (i > 0) {
                    return new AjaxResult(0, "该手机号已存在绑定的用户");
                }


                UtUsers users = new UtUsers();
                users.setUloginname(umobile);
                users.setUmobile(umobile);
                users.setUuserid(user.getUuserid());

                boolean flag = service.updateByUuserid(users) > 0;

                return  flag ? new AjaxResult(1,"绑定成功") : new AjaxResult(0,"绑定出错");

            }

        }

        return new AjaxResult(0, "验证码错误");


    }
    @RequestMapping("updateuserinfo")
    @ResponseBody
    public AjaxResult updateUserInfo(@SessionAttribute(Constant.SESSION_H5USER)UtUsers user,UtUsers newuser,String name,String pwd) {
    	String uuserid = user.getUuserid();
        UtUsers users = service.queryByID(uuserid);

        String uemail = newuser.getUemail();

        UtUsers utUsers = new UtUsers();

    	utUsers.setUuserid(uuserid);
    	utUsers.setUemail(uemail);
    	utUsers.setUlogo(newuser.getUlogo());

    	int row = service.updateByUuserid(utUsers);
    	if(row>0) {
    		return returnSuccess("修改资料成功");
    	}
    	return returnFailed("修改资料失败");
    }
    @RequestMapping("card")
    public String card(@SessionAttribute(Constant.SESSION_H5USER)UtUsers user,Model model) {
    	List<UtBankinfo> bankList = bankService.getBankCardList(user.getUuserid());
    	if(bankList!=null) {
    		//脱敏
    		for (UtBankinfo utBankInfo : bankList) {
    			String oldBankCardno = utBankInfo.getUbankCardno();
    			int strLength = oldBankCardno.length();
    			String tail = oldBankCardno.substring(strLength-4);
    			utBankInfo.setUbankCardno("**** **** **** "+tail);
			}
    	}
    	model.addAttribute("cardList", bankList);
    	return "templates/weui/card";
    }
    @RequestMapping("deleteCard")
    @ResponseBody
    public AjaxResult deleteCard(String ubankid) {
    	boolean flag = bankService.deleteCard(ubankid);
    	if(flag) {
    		return returnSuccess("删除银行卡成功");
    	}
		return returnFailed("删除银行卡失败");
    }
    @RequestMapping("addCard")
    public String addCard(@SessionAttribute(Constant.SESSION_H5USER)UtUsers user,Model model) {
    	model.addAttribute("name", user.getUname());
    	model.addAttribute("umobile", user.getUmobile());
    	return "templates/weui/add_card";
    }
    @RequestMapping("savaBankCard")
    @ResponseBody
    public AjaxResult savaBankCard(@SessionAttribute(Constant.SESSION_H5USER)UtUsers user,UtBankinfo bankInfo) {

//        String bankCardNo = BankCardNoUtil.getBankCardNo(bankInfo.getUbankCardno());
        //获取银行卡信息
        String bankCardNo = BankUtil.getNameOfBank(bankInfo.getUbankCardno());
        //银行卡错误
        if (StringUtils.isEmpty(bankCardNo)) {
            return new AjaxResult(0, "银行卡错误");
        }
        bankInfo.setUbankName(bankCardNo.split("·")[0]);
        bankInfo.setUbankId(nextId());
        bankInfo.setUuserid(user.getUuserid());
        bankInfo.setUifClose("1");

       return  bankService.saveBankinfo(bankInfo);

    }
}
