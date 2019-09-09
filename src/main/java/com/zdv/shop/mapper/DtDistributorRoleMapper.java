package com.zdv.shop.mapper;

import com.zdv.shop.common.dao.MyMapper;
import com.zdv.shop.model.DtDistributorRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 经销商（供应商）角色信息 mapper
 * @author LBY
 * @date: 2018年12月14日
 */
public interface DtDistributorRoleMapper extends MyMapper<DtDistributorRole> {

    DtDistributorRole queryByRolename(@Param("udistributorid")String udistributorid,@Param("urolename")String urolename);

    List<DtDistributorRole> rootRolelist(@Param("udistributorid")String udistributorid);
    List<DtDistributorRole> childRolelist(@Param("uparentid")String uparentid,@Param("udistributorid")String udistributorid);

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
    List<DtDistributorRole> queryPossessRoles(@Param("udistributorid")String udistributorid,
    		@Param("uopuserid")String uopuserid, @Param("uroleid")String uroleid);
}
