package com.zdv.shop.mapper;

import com.zdv.shop.common.dao.MyMapper;
import com.zdv.shop.model.OtConfig;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OtConfigMapper  extends MyMapper<OtConfig> {
	
	List<OtConfig> getConfigByType(@Param("utype") String type,@Param("ucustomerid")String ucustomerid);

    List<OtConfig> getConfigByType(@Param("utype")String s,@Param("ucustomerid") String ucustomerid,@Param("ucompid") String ucompid);
}
