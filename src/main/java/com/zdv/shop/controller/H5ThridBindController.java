package com.zdv.shop.controller;

import com.zdv.shop.model.UtThirdlogin;
import com.zdv.shop.service.UtThirdloginService;
import com.zdv.shop.weixinh5.config.PayConfig;
import com.zdv.shop.weixinh5.util.HttpUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.ParseException;

@Controller
@RequestMapping("/h5/thridbind/")
public class H5ThridBindController extends  BaseController {
    //    private static final Logger logger = Logger.getLogger(WXLoginController.class);
    @Autowired
    private UtThirdloginService thirdloginService;



    @RequestMapping("account_bind")
    public String account_bind() {
        return "templates/h5/account_bind";
    }


    /**
     * 公众号微信登录授权
     * @param request
     * @param response
     * @return
     * @throws ParseException
     * @author  lbh
     * @date 创建时间：2018年1月18日 下午7:33:59
     * @parameter
     */
    @RequestMapping(value = "/wxLogin", method = RequestMethod.GET)
    public String wxLogin(HttpServletRequest request,
                          HttpServletResponse response)
            throws ParseException {
        //这个url的域名必须要进行再公众号中进行注册验证，这个地址是成功后的回调地址
        String backUrl="http://d84e26b6.ngrok.io/WX_auth/wx/callBack";
        // 第一步：用户同意授权，获取code
        String url ="https://open.weixin.qq.com/connect/oauth2/authorize?appid="+PayConfig.APP_ID
                + "&redirect_uri="+URLEncoder.encode(backUrl)
                + "&response_type=code"
                + "&scope=snsapi_userinfo"
                + "&state=STATE#wechat_redirect";

//        logger.info("forward重定向地址{" + url + "}");
        //response.sendRedirect(url);
        return "redirect:"+url;//必须重定向，否则不能成功
    }
    /**
     * 公众号微信登录授权回调函数
     * @param modelMap
     * @param req
     * @param resp
     * @return
     * @throws ServletException
     * @throws IOException
     * @parameter
     */
    @RequestMapping(value = "/callBack", method = RequestMethod.GET)
    public String callBack(ModelMap modelMap, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*
         * start 获取微信用户基本信息
         */
        String code =req.getParameter("code");
        //第二步：通过code换取网页授权access_token
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid="+PayConfig.APP_ID
                + "&secret="+PayConfig.APP_SECRET
                + "&code="+code
                + "&grant_type=authorization_code";
        System.out.println("url:"+url);
        HttpServletRequest request = getRequest();

//        JSONObject jsonObject = JSONObject.parseObject(url);
    	/*
         { "access_token":"ACCESS_TOKEN",
         "expires_in":7200,
         "refresh_token":"REFRESH_TOKEN",
         "openid":"OPENID",
         "scope":"SCOPE"
        }
         */
    	/*
        String openid = jsonObject.getString("openid");
        String access_token = jsonObject.getString("access_token");
        String refresh_token = jsonObject.getString("refresh_token");*/
        String openid = HttpUtil.getParamByUrl(url, "openid");
        String appid = HttpUtil.getParamByUrl(url, "appid");
        String access_token = HttpUtil.getParamByUrl(url, "access_token");
        String refresh_token = HttpUtil.getParamByUrl(url, "refresh_token");



        //第五步验证access_token是否失效；展示都不需要
        String chickUrl="https://api.weixin.qq.com/sns/auth?access_token="+access_token+"&openid="+openid;

//        JSONObject chickuserInfo = JSONObject.parseObject(HttpUtil.getRequestWx(chickUrl));
//        JSONObject chickuserInfo = JSONObject.parseObject(chickUrl);
//        System.out.println(chickuserInfo.toString());
        if(!"0".equals(HttpUtil.getParamByUrl(chickUrl, "errcode"))){
            // 第三步：刷新access_token（如果需要）-----暂时没有使用,参考文档https://mp.weixin.qq.com/wiki，
            String refreshTokenUrl="https://api.weixin.qq.com/sns/oauth2/refresh_token?appid="+openid+"&grant_type=refresh_token&refresh_token="+refresh_token;

//            JSONObject refreshInfo = JSONObject.parseObject(HttpUtil.getRequestWx(chickUrl));
             /*
            * { "access_token":"ACCESS_TOKEN",
            "expires_in":7200,
            "refresh_token":"REFRESH_TOKEN",
            "openid":"OPENID",
            "scope":"SCOPE" }
            */
//            System.out.println(refreshInfo.toString());
//            access_token=refreshInfo.getString("access_token");
            access_token = HttpUtil.getParamByUrl(refreshTokenUrl, "access_token");
        }

        // 第四步：判断OpenID是否已经存在数据库（关注过）
        boolean exitUser = false;
        //response.sendRedirect("https://mp.weixin.qq.com/mp/profile_ext?action=home&__biz="+BIZ+"==&scene=110#wechat_redirect");
//
        String redirectUrl = "https://mp.weixin.qq.com/mp/profile_ext?action=home&amp;__biz="
                +PayConfig.BIZ
                +"&amp;chksm==&amp;scene=0#wechat_redirect";
        if (StringUtils.isNotBlank(openid)) {
            //type第三方分类 0为微信，1为qq,2微博
            UtThirdlogin thirdlogin = thirdloginService.selectInfoByOpenId(openid,"0");
//            UserToWechatModel uwm = userToWechatService.selectInfoByOpenId(openid);
            if (thirdlogin != null && StringUtils.isNotBlank(thirdlogin.getUuserid()) /*&& "1".equals(uwm.getSubscribe())*/) {
                exitUser = true;
                /*redirectUrl="https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxc8dd52122e8e6700&redirect_uri=" +
                        "https%3A%2F%2Fwechat.yimidida.com%2Fgalaxy-wechat-business%2Fwechat%2FopenidToOrder" +
                        "&response_type=code&scope=snsapi_base&state=1&connect_redirect=1#wechat_redirect";*/
                redirectUrl="https://wechat-test.yimidida.com/galaxy-wechat-business/wechat/openidToOrder?openid="+openid;
                return "redirect:https://www.baidu.com" ; //必须重定向，否则不能成功
            }


            //String gzh = request.getParameter("gzh");
            String gzh = "";//公众号名字
            String user_agent = req.getHeader("user-agent");

            redirectUrl = "http://mp.weixin.qq.com/mp/getmasssendmsg?__biz=" + PayConfig.BIZ
                    + "#wechat_webview_type=1&wechat_redirect#rd";

            if (user_agent.indexOf("MicroMessenger")>=0 &&
                    user_agent.indexOf("iPhone")>=0 ) {
                resp.sendRedirect(redirectUrl);
                return null;
            }


            //此方法已被微信封掉
         /*   if (user_agent.indexOf("MicroMessenger")>=0 &&
                    user_agent.indexOf("Android")>=0 ) {
                resp.sendRedirect("weixin://profile/"+gzh);
               return null;
            }*/

        }
        return "redirect:" + redirectUrl; //必须重定向，否则不能成功

        // 第五步：拉取用户信息(需scope为 snsapi_userinfo)
        //https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN
        //String infoUrl = "https://api.weixin.qq.com/sns/userinfo?access_token="+access_token
       /* String infoUrl = "https://api.weixin.qq.com/cgi-bin/user/info?access_token="+access_token
                + "&openid="+openid
                + "&lang=zh_CN";
        System.out.println("infoUrl:"+infoUrl);
        JSONObject userInfo = JSONObject.parseObject(HttpUtil.getRequestWx(infoUrl));*/
        /*
         {    "openid":" OPENID",
         " nickname": NICKNAME,
         "sex":"1",
         "province":"PROVINCE"
         "city":"CITY",
         "country":"COUNTRY",
         "headimgurl":    "http://wx.qlogo.cn/mmopen/g3MonUZtNHkdmzicIlibx6iaFqAc56vxLSUfpb6n5WKSYVY0ChQKkiaJSgQ1dZuTOgvLLrhJbERQQ4eMsv84eavHiaiceqxibJxCfHe/46",
         "privilege":[ "PRIVILEGE1" "PRIVILEGE2"     ],
         "unionid": "o6_bmasdasdsad6_2sgVt7hMZOPfL"
         }
         */
        /*System.out.println("JSON-----"+userInfo.toString());
        System.out.println("名字-----"+userInfo.getString("nickname"));
        System.out.println("头像-----"+userInfo.getString("headimgurl"));*/


        /*
         * end 获取微信用户基本信息
         */
        //获取到用户信息后就可以进行重定向，走自己的业务逻辑了。。。。。。
        //接来的逻辑就是你系统逻辑了，请自由发挥

    }


















  /*  public String queueInfo() throws Exception{
        HttpServletRequest request = getRequest();
        String code = request.getParameter("code");
        //通过code获取openid;
        net.sf.json.JSONObject wxUser = CoreService.getOpenid(code);
        String openid = wxUser.getString("openid");
    }*/




}












