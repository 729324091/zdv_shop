package com.zdv.shop.mapper;

import java.util.List;

import com.zdv.shop.common.dao.MyMapper;
import com.zdv.shop.model.CtRoleOperation;

public interface CtRoleOperationMapper  extends MyMapper<CtRoleOperation> {

    void batchInsert(List<CtRoleOperation> list);

    void delRoleOpers(List<CtRoleOperation> list);

    List<CtRoleOperation> queryOperations(String uroleid);

    /*int deleteByPrimaryKey(CtRoleOperationKey key);

    int insert(CtRoleOperationKey record);

    int insertSelective(CtRoleOperationKey record);*/
}