package com.zdv.shop.mapper;

import java.util.List;

import com.zdv.shop.common.dao.MyMapper;
import com.zdv.shop.model.AuthRoleOperation;

public interface AuthRoleOperationMapper extends MyMapper<AuthRoleOperation> {

	void batchInsert(List<AuthRoleOperation> list);

	void delRoleOpers(List<AuthRoleOperation> list);
}