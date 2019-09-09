package com.zdv.shop.mapper;

import com.zdv.shop.common.dao.MyMapper;
import com.zdv.shop.model.DtRoleOperation;

import java.util.List;

public interface DtRoleOperationMapper extends MyMapper<DtRoleOperation> {

    void batchInsert(List<DtRoleOperation> list);

    void delRoleOpers(List<DtRoleOperation> list);

    List<DtRoleOperation> queryOperations(String uroleid);
}
