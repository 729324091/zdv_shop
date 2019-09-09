package com.zdv.shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zdv.shop.common.annotation.ServiceLog;
import com.zdv.shop.mapper.AuthOperationMapper;
import com.zdv.shop.model.AuthOperation;
@Service
public class AuthOperationService extends AbstratService<AuthOperation> {
	@Autowired
	private AuthOperationMapper authOperationMapper;
	@ServiceLog("获得功能列表")
	public List<AuthOperation> queryAllOpers(){
		return authOperationMapper.queryAllOpers();
	}

}
