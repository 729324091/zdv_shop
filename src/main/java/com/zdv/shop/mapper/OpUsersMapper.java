package com.zdv.shop.mapper;

import com.zdv.shop.common.dao.MyMapper;
import com.zdv.shop.model.CtOpUserToComp;
import com.zdv.shop.model.DtOpUserToDistributor;
import com.zdv.shop.model.OpUsers;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface OpUsersMapper extends MyMapper<OpUsers> {
	
	int insertUser(OpUsers opUsers);
	
	OpUsers queryByUmobile(String umobile);
	
	/**
	 * 登录查询经销商和角色信息
	 * @author LBY
	 * @date 2018年12月14日
	 * @param uopuserid
	 * @return
	 */
	List<Map<String, Object>> queryDistributorRoleByOpUserId(String uopuserid);
	/**
	 * 通过工作人员ID获得零销售与角色列表
	 * @param uopuserid
	 * @return
	 */
	List<Map<String, Object>> queryCompanyRoleByOpUserId(String uopuserid);
	
	/**
	 * 查询所管理的用户
	 * @author LBY
	 * @date 2018年12月17日
	 * @param currentRoleid 当前经销商用户角色id
	 * @param keywords 搜索关键字
	 * @param roleid 角色ID条件
	 * @return
	 */
	List<Map<String, Object>> queryPossessOpUserByRoleid(@Param("currentRoleid")String currentRoleid,
			@Param("keywords")String keywords, @Param("roleid")String roleid);

	//查询出经销商下的所有用户
    List<OpUsers> queryListByDtid(@Param("udistributorid") String udistributorid, @Param("uroleid") String uroleid);


    OpUsers queryByUsername(String uloginname);
    
    /**
     * APP查询单个用户详情
     * @author LBY
     * @data 2019年1月15日
     * @param udistributorid
     * @param uopuserid
     * @param uroleid
     * @return
     */
    Map<String, Object> appQueryUserDetails(@Param("udistributorid")String udistributorid,
    		@Param("uopuserid")String uopuserid, @Param("uroleid")String uroleid);
    //销售商下的管理员详情内容
    Map<String, Object> appQueryCompUserDetails(@Param("ucompid")String ucompid,
    		@Param("uopuserid")String uopuserid, @Param("uroleid")String uroleid);
	/**
	 * 中间表查询销售商用户集合
	 * @param opUserToComp
	 * @return
	 */
    List<OpUsers> queryCtUserList(CtOpUserToComp opUserToComp);

    List<OpUsers> queryDtUserList(DtOpUserToDistributor opUserToDistributor);

    OpUsers findRootByUdistributorid(String udistributorid);

    int updateUsers(OpUsers users);

    //查找销售商根用户
	OpUsers findRootByUcompid(String ucompid);
}