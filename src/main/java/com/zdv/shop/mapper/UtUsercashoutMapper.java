package com.zdv.shop.mapper;


import com.zdv.shop.common.dao.MyMapper;
import com.zdv.shop.model.UtUsercashout;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface UtUsercashoutMapper extends MyMapper<UtUsercashout> {
	List<UtUsercashout> queryList(UtUsercashout message);
	
    int deleteByIds(@Param("ucashoutid") String ucashoutid);

    int inserts(UtUsercashout record);

    UtUsercashout selectByIds(@Param("ucashoutid") String ucashoutid, @Param("ucompid") String ucompid, @Param("ucustomerid") String ucustomerid);

    int updateByIds(UtUsercashout record);

//    List<Map<String,Object>> ListByUserid(UtUsers users);

    List<Map<String,Object>> ListByUserid(@Param("uuserid") String uuserid,@Param("pageNo") Integer pageNo,@Param("pageSize") Integer pageSize);
}