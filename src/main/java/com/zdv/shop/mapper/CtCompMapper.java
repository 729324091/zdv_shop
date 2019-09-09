package com.zdv.shop.mapper;

import java.util.List;
import java.util.Map;

import com.zdv.shop.model.CtOpUserToComp;
import org.apache.ibatis.annotations.Param;

import com.zdv.shop.common.dao.MyMapper;
import com.zdv.shop.model.CtComp;
import com.zdv.shop.model.PtArea;

/**
 * 销售商信息 Mapper 层
 * @author LBY
 * @date: 2018年12月17日
 */
public interface CtCompMapper extends MyMapper<CtComp> {

	/**
	 * 根据经销商ID查询所管理的销售商信息
	 * @author LBY
	 * @date 2018年12月17日
	 * @param distributorid 经销商ID
	 * @param keyword 搜索关键字
	 * @param areaid 区域id
	 * @return
	 */
	List<Map<String, Object>> queryCompanyByDistributorid(@Param("distributorid") String distributorid,
                                                          @Param("keywords") String keywords, @Param("areaid") String areaid);
	
	/**
	 * 查询经销商所管理的销售商区域信息
	 * @author LBY
	 * @date 2018年12月17日
	 * @param distributorid
	 * @return
	 */
	List<PtArea> queryPossessAreaByDistributorid(String distributorid);
	CtComp queryCompanyById(Long ucompid);
    List<CtComp> queryList(CtComp clCompany);

    int insertSelectives(CtComp record);

    int updateByPrimaryKeys(CtComp record);
    
	List<CtComp> queryCtComps(@Param("clcompany") CtComp clcompany);
	
	List<CtComp> ContainuNameOradrs(@Param("clcompany") CtComp clcompany);
	
	int deleteByPrimaryKeys(String ucompid);
	
	int updateCompanyByIds(CtComp company);
	//查询用户相关的所有销售商
    List<CtComp> queryCompanyListByUopuserid(String uopuserid);

    List<CtComp> querypageByUTC(CtOpUserToComp opUserToComp);

    List<CtComp> queryChildrenIdList(String ucompid);

	int delAllById(String ucompid);

	/**
	 * 获得子id字符串 例： 1222,5555,44
	 * @param ucompid
	 * @return
	 */
    String getChildFromCompany(String ucompid);

	List<CtComp> childrenList(@Param("clcompany") CtComp clcompany);

    List<CtComp> queryDistributor2CompList(@Param("clcompany") CtComp company, @Param("ids") String ids);

    CtComp selectByUcompname(String ucompname);

	List<CtComp> getComp(@Param("uUserId")String uuserid);
	//获得商户号
    Integer getSysSN(@Param("flag") Integer flag);

    int updateBalanceByUcompid(CtComp comp);
}
