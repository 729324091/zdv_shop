package com.zdv.shop.mapper;

import com.zdv.shop.common.dao.MyMapper;
import com.zdv.shop.model.UtThirdlogin;
import org.apache.ibatis.annotations.Param;

public interface UtThirdloginMapper extends MyMapper<UtThirdlogin> {


    UtThirdlogin selectInfoByOpenId(@Param("openid") String openid,@Param("utype") String utype);

    UtThirdlogin selectInfoByUserid(@Param("uuserid") String uuserid,@Param("utype")  String utype);
}
