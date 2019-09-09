package com.zdv.shop.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zdv.shop.common.dao.MyMapper;
import com.zdv.shop.model.UtOrderexpress;

public interface UtOrderexpressMapper  extends MyMapper<UtOrderexpress>{
	List<UtOrderexpress> queryList(UtOrderexpress message);
    UtOrderexpress selectByIds(@Param("uuserid") String uuserid,@Param("uorderid") String uorderid);

}