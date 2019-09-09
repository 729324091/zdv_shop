package com.zdv.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zdv.shop.common.annotation.ServiceLog;
import com.zdv.shop.mapper.CtRoleOperationMapper;
import com.zdv.shop.model.AuthRoleOperation;
import com.zdv.shop.model.CtRoleOperation;

import java.util.List;

@Service
public class CtRoleOperationService extends AbstratService<CtRoleOperation> {
	@Autowired
	private CtRoleOperationMapper ctRoleOperationMapper;
	@ServiceLog("插入权限列表")
	public void batchInsert(List<CtRoleOperation> list){
		ctRoleOperationMapper.batchInsert(list);
	}
	@ServiceLog("删除权限列表")
	public void delRoleOpers(List<CtRoleOperation> list){
		ctRoleOperationMapper.delRoleOpers(list);
	}

	@ServiceLog("查询角色权限ID")
	public List<CtRoleOperation> queryOperations(String uroleid) {
		return ctRoleOperationMapper.queryOperations(uroleid);
	}

}
