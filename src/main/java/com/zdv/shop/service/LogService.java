package com.zdv.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zdv.shop.mapper.ILogMapper;
import com.zdv.shop.model.ILog;

@Service
public class LogService extends AbstratService<ILog> {
	
	@Autowired
	ILogMapper ilogMapper;
	public int selectCodeBymobileCount(String codeno,String umobile) {
		return ilogMapper.selectCodeBymobileCount(codeno, umobile);
	}
	public int sp_createRandom(String loginName){
		return ilogMapper.sp_createRandom(loginName);
	}
	public int selectBymobileCount(String umobile) {
		return ilogMapper.selectBymobileCount(umobile);
	}
	//获得层级值 in_flag INT(1)   0 没有父ID新数据编码；1有选择父ID新增数据，2修改父ID 修改数据
	public String sp_level(int flag,String fulevel,String currentlevel,String tablename,String condition) {
		return ilogMapper.sp_level(flag, fulevel, currentlevel, tablename, condition);
	}
}
