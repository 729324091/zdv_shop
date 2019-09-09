package com.zdv.shop.mapper;

import com.zdv.shop.common.dao.MyMapper;
import com.zdv.shop.model.AuthOperation;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AuthOperationMapper extends MyMapper<AuthOperation> {

	List<AuthOperation> queryAllOpers();
	//通过opcode获得权限对象
	List<AuthOperation> queryOperation(@Param("opcode")String opcode,@Param("ophref") String ophref);

}