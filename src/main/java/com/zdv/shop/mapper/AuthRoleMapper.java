package com.zdv.shop.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zdv.shop.common.dao.MyMapper;
import com.zdv.shop.model.AuthRole;

public interface AuthRoleMapper extends MyMapper<AuthRole> {

	AuthRole queryRoleById(@Param("roleid")String roleid);
	
	AuthRole queryByRolename(@Param("rolename") String rolename);
	AuthRole queryByRolename1(@Param("rolename") String rolename,@Param("customerid") String customerid);
	//所有父级
	List<AuthRole> rootRolelist(@Param("customerid")String customerid);
	//所有子级
	List<AuthRole> childRolelist(@Param("roleid")String roleid,@Param("customerid")String customerid);
	
	void insertRole(AuthRole authRole);
	void insertAuth_role_operation(@Param("roleid")String roleid,@Param("ifoper")String ifoper);
}