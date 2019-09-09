package com.zdv.shop.mapper;

import com.zdv.shop.common.dao.MyMapper;
import com.zdv.shop.model.CtCompRole;
import com.zdv.shop.model.DtDistributorRole;

import java.util.List;

import org.apache.ibatis.annotations.Param;

/**
 * 销售商角色信息表 Mapper
 * @author LBY
 * @date: 2018年12月14日
 */
public interface CtCompRoleMapper extends MyMapper<CtCompRole> {

    List<CtCompRole> rootRolelist(String ucompid);
    //判断销售商下角色是否存在
    CtCompRole queryByRolename(@Param("ucompid")String ucompid,@Param("urolename")String urolename);
    /**
     * 根据销售商ID及等级查询所有的子角色
     * @author LBY
     * @data 2019年1月11日
     * @param ucompid
     * @param ulevel
     * @return
     */
    List<CtCompRole> selectChildRole(@Param("ucompid")String ucompid, @Param("ulevel")String ulevel);
    int delRoleByID(String uroleid);
    
    /**
     * 查询该用户所管辖的角色列表
     * @author LBY
     * @data 2019年1月15日
     * @param udistributorid
     * @param uopuserid
     * @param uroleid
     * @return
     */
    List<CtCompRole> queryOpuserRoles(@Param("ucompid")String ucompid,
    		@Param("uopuserid")String uopuserid, @Param("uroleid")String uroleid);
}