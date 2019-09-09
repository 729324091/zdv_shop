package com.zdv.shop.mapper;

import com.zdv.shop.common.dao.MyMapper;
import com.zdv.shop.model.CtWxpayConfig;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CtWxpayConfigMapper extends MyMapper<CtWxpayConfig> {
	List<CtWxpayConfig> queryList(CtWxpayConfig message);
	
    int deleteByIds(@Param("uwxpayid") String uwxpayid);

    int inserts(CtWxpayConfig record);

    CtWxpayConfig selectByIds(@Param("uwxpayid") String uwxpayid, @Param("ucompid") String ucompid, @Param("ucustomerid") String ucustomerid);

    int updateByIds(CtWxpayConfig record);
 
}