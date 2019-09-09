package com.zdv.shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zdv.shop.mapper.CtCompRoleMapper;
import com.zdv.shop.model.CtCompRole;

/**
 * 销售商角色信息表
 * @author LBY
 * @date: 2018年12月14日
 */
@Service
public class CtCompRoleService extends AbstratService<CtCompRole> {
	
	@Autowired
	CtCompRoleMapper mapper;

	public List<CtCompRole> queryChildRole(String ucompid,String ulevel) {
		return mapper.selectChildRole(ucompid, ulevel);
	}
	public  CtCompRole queryByRolename(String ucompid,String urolename) {
		return mapper.queryByRolename(ucompid, urolename);
	}
    public int delRoleByID(String uroleid) {
    	return mapper.delRoleByID(uroleid);
    }
    
    /**
     * 查询该用户所管辖的角色列表
     * @author LBY
     * @data 2019年1月15日
     * @param udistributorid
     * @param uopuserid
     * @param uroleid
     * @return
     */
    public List<CtCompRole> opuserRoleslist(String ucompid,
    		String uopuserid,String uroleid){
    	return mapper.queryOpuserRoles(ucompid, uopuserid, uroleid);
    }
}
