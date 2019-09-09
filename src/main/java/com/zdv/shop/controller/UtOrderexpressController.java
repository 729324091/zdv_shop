package com.zdv.shop.controller;

import com.zdv.shop.common.Constant;
import com.zdv.shop.common.annotation.Authority;
import com.zdv.shop.common.annotation.ControllerLog;
import com.zdv.shop.common.utils.HttpRequest;
import com.zdv.shop.common.utils.MD5;
import com.zdv.shop.model.UtOrderexpress;
import com.zdv.shop.model.UtUsers;
import com.zdv.shop.service.UtOrderexpressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.HashMap;
import java.util.Map;

@RequestMapping("/h5/express")
@Controller
public class UtOrderexpressController {

    @Autowired
    private UtOrderexpressService orderexpressService;

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
                map.put("expressinfo", resp);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }else {
            map.put("expressinfo", "");

        }
        UtOrderexpress utOrderexpress = new UtOrderexpress();
        utOrderexpress.setUexpresscompname("哪都通");
        utOrderexpress.setUexpressnum("132321313");
        map.put("orderexpress", utOrderexpress);

        return "templates/weui/orderexpress";
    }



}
