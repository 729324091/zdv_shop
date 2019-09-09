package com.zdv.shop.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zdv.shop.common.dao.MyMapper;
import com.zdv.shop.model.UtExpresscomp;

public interface UtExpresscompMapper extends MyMapper<UtExpresscomp> {
	List<UtExpresscomp> queryList(UtExpresscomp message);

    UtExpresscomp selectByIds(@Param("expresscompid") String expresscompid);

   
 
}