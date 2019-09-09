package com.zdv.shop.service;

import com.zdv.shop.mapper.UtThirdloginMapper;
import com.zdv.shop.model.UtThirdlogin;
import com.zdv.shop.model.UtUsers;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UtThirdloginService extends AbstratService<UtThirdlogin>{
    @Autowired
    private UtThirdloginMapper thirdloginMapper;

    //type第三方分类 0为微信，1为qq,2微博
    public UtThirdlogin selectInfoByOpenId(String openid,String type) {
        return thirdloginMapper.selectInfoByOpenId(openid,type);
    }

    public UtThirdlogin selectInfoByUserid(String uuserid, String utype) {
        return thirdloginMapper.selectInfoByUserid(uuserid, utype);
    }
}
