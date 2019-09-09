package com.zdv.shop.controller;

import com.zdv.shop.common.Constant;
import com.zdv.shop.common.pojo.AjaxResult;
import com.zdv.shop.common.utils.MD5Util;
import com.zdv.shop.model.*;
import com.zdv.shop.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Random;

@RequestMapping("/h5/factory/")
@Controller
public class H5FactoryController extends BaseController {

    @Autowired
    private PtAreaService areaService;

    @Autowired
    private DtDistributorService distributorService;
    @Autowired
    private CtWxpayConfigService wxpayConfigService;

    @Autowired
    private UtThirdloginService thirdloginService;
    @Autowired
    private DtDistributorToCompService distributorToCompService;

    @Autowired
    private LogService logService;
    @Autowired
    private OpUsersService opUsersService;


    @Value("${ucompid}")
    private String ucompid;

    @Value("${ucustomerid}")
    private String ucustomerid;

    @RequestMapping("registPage")
    public String registPage(Model model) {
        List<PtArea> ptAreas = areaService.queryTopArea();

        model.addAttribute("topArea", ptAreas);

        return "templates/weui/factory";
    }

    @RequestMapping("queryAreaByUparentid")
    @ResponseBody
    public AjaxResult queryAreaByUparentid(String uparentid) {

        List<PtArea> list = areaService.queryAreaByUparentid(uparentid);
        return new AjaxResult(1,"成功", list);
    }


    @RequestMapping("apply")
    @ResponseBody
    public AjaxResult apply(DtDistributor dtDistributor,String[] ucertificatepath,String ucardid) {

        HttpSession session = getRequest().getSession();
        UtUsers user = (UtUsers) session.getAttribute(Constant.SESSION_H5USER);

        if (user != null) {

            String uuserid = user.getUuserid();
            //存在第三方登录记录的openid
            UtThirdlogin thirdlogin = thirdloginService.selectInfoByUserid(uuserid, "0");
            if (thirdlogin != null) {
                //获取到openid
                String openid = thirdlogin.getOpenid();



                CtWxpayConfig ctWxpayConfig = new CtWxpayConfig();
                ctWxpayConfig.setUcustomerid(ucustomerid);
                //0微信 1支付宝
                ctWxpayConfig.setUtypes("0");
                List<CtWxpayConfig> configs = wxpayConfigService.queryList(ctWxpayConfig);
                String appid = "";
                String appsecret = "";
                String mch_id = "";
                String key = "";
                if (configs.size() > 0) {
                    CtWxpayConfig config = configs.get(0);
                    //获取appid
                    appid = config.getAppid();
                    appsecret = config.getAppsecret();
                    mch_id = config.getMchid();
                    key = config.getApikey();
                }





            }
        }



        dtDistributor.setUdistributorid(nextId());
        //类型厂家
        dtDistributor.setUtypes("1");
        dtDistributor.setUcustomerid(ucustomerid);
        String path = "";
        if (ucertificatepath != null && ucertificatepath.length > 0) {


            for (int i = 0; i < ucertificatepath.length; i++) {
                if (i == 0) {
                    path = ucertificatepath[i];
                }else{
                    path += "#" + ucertificatepath[i];
                }
            }

        dtDistributor.setUcertificatepath(path);

        }else{
            return new AjaxResult(0, "请先上传相关证件照");
        }

        dtDistributor.setUlevel(logService.sp_level(0,"","","dt_distributor", ""));

        //厂家与商店的关联关系
        DtDistributorTComp dtDistributorTComp = new DtDistributorTComp();
        dtDistributorTComp.setUcompid(ucompid);
        dtDistributorTComp.setUcustomerid(ucustomerid);
        dtDistributorTComp.setUdistributorid(dtDistributor.getUdistributorid());
        //设置成审核中
        dtDistributorTComp.setUstatus('0');
        OpUsers mobile = opUsersService.queryByMobile(dtDistributor.getUtel());
        if (mobile != null) {
            return new AjaxResult("该手机号以申请成为厂商请更换");
        }

        OpUsers opUsers = new OpUsers();
        //初始用户登录名
        opUsers.setUloginname(dtDistributor.getUtel());
        opUsers.setUopuserid(nextId());
        Random random = new Random();
        int i = random.nextInt(1000000);
        //随机密码
        opUsers.setUpassword(MD5Util.encrypt(i + ""));
        opUsers.setUmobile(dtDistributor.getUtel());
        opUsers.setUcardid(ucardid);
        opUsers.setUregdate(this.timeStamp()+"");
        //有效
        opUsers.setUflag("01");
        opUsers.setUlastmtime(this.timeStamp()+"");
        opUsers.setUlastdate(this.timeStamp()+"");
        opUsers.setUlognum(0);
        opUsers.setUoptype("1");
        opUsers.setUstatus("1");


        //根角色
        DtDistributorRole distributorRole = new DtDistributorRole();
        distributorRole.setUroleid(this.nextId());
        distributorRole.setUdistributorid(dtDistributor.getUdistributorid());
        distributorRole.setUrolename("RootRole");
        distributorRole.setUlevel(logService.sp_level(0,"","","dt_distributor_role",""));
        distributorRole.setUlevelcolor("3881EC");
        //超级管理员
        distributorRole.setUtypes('0');

        return distributorToCompService.addDistributorToComp(dtDistributorTComp, dtDistributor,opUsers,distributorRole);

    }

}
