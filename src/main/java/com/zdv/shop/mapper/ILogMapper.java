package com.zdv.shop.mapper;

import org.apache.ibatis.annotations.Param;

import com.zdv.shop.common.dao.MyMapper;
import com.zdv.shop.model.ILog;

public interface ILogMapper extends MyMapper<ILog> {

	int selectCodeBymobileCount(@Param("codeno") String codeno,@Param("umobile") String umobile);
	int sp_createRandom(@Param("loginName") String loginName);
	int selectBymobileCount(@Param("umobile") String umobile);
	//获得层级值 in_flag INT(1)   0 没有父ID新数据编码；1有选择父ID新增数据，2修改父ID 修改数据
	String sp_level(@Param("flag") int flag,@Param("fulevel") String fulevel,@Param("currentlevel") String currentlevel,@Param("tablename") String tablename,@Param("condition") String condition);
}