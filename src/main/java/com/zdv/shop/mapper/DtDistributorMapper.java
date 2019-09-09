package com.zdv.shop.mapper;

import com.zdv.shop.common.dao.MyMapper;
import com.zdv.shop.model.DtDistributor;
import com.zdv.shop.model.DtOpUserToDistributor;
import com.zdv.shop.model.PtArea;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 经销商（供应商）Mapper
 * @author LBY
 * @date: 2018年12月17日
 */
public interface DtDistributorMapper extends MyMapper<DtDistributor>{

	/**
	 * 根据管理员ID查询所管理的经销商信息
	 * @author LBY
	 * @date 2018年12月17日
	 * @param userid
	 * @return
	 */
	List<Map<String, Object>> queryDistributorByUserid(@Param("userid")String userid,
			@Param("keywords")String keywords, @Param("areaid")String areaid);
	
	/**
	 * 查询管理员所管理的经销商区域信息
	 * @author LBY
	 * @date 2018年12月17日
	 * @param userid
	 * @return
	 */
	List<PtArea> queryPossessAreaByUserid(String userid);
	
	/**
	 * 查询指定区域内可向其下单的经销商
	 * @author LBY
	 * @data 2019年2月20日
	 * @param uproductid
	 * @param uareaid
	 * @param ustock
	 * @return 返回经销商ID
	 */
	DtDistributor selectOrdersDistributor(@Param("uproductid")String uproductid, @Param("uareaid")String uareaid,
			@Param("ustock")Integer ustock);
	
	DtDistributor selById(@Param("udistributorid")String udistributorid);
	//获得ID的父级对象
	DtDistributor selectParentDistributorById(@Param("udistributorid")String udistributorid);
	//判断一个经销商是否有子经销商 
	int getdistributorchildById(@Param("udistributorid")String udistributorid);

    int delAllById(String udistributorid);
	//查询出一个经销商下的所有经销商(只查了id)
	List<DtDistributor> queryChildrenIdList(String udistributorid);

	List<DtDistributor> queryPage(DtDistributor distributor);

	//通过用户id查询所有的关联经销商
	List<DtDistributor> queryDistributorListByUopuserid(String uopuserid);

    List<DtDistributor> querypageByUTD(DtOpUserToDistributor opUserToDistributor);

    DtDistributor selectDistributorById(String udistributorid);

    //查询出所有子数据
	List<DtDistributor> childrenList(DtDistributor distributor);

    String childrenListIdFromDistributor(String udistributorid);

    int insertDistributor(DtDistributor dtDistributor);
}
