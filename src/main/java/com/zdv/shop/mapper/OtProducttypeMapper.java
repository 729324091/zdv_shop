package com.zdv.shop.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.zdv.shop.common.dao.MyMapper;
import com.zdv.shop.model.OtProducttype;

/**
 * 产品类型Mapper
 * @author LBY
 * @date: 2018年12月7日
 */
public interface OtProducttypeMapper extends MyMapper<OtProducttype> {

	List<OtProducttype> productTypePage(@Param("uparentid")String uparentid);
	
	/**
	 * 销售商查询产品类型
	 * @author LBY
	 * @data 2019年2月19日
	 * @param ucompid
	 * @return
	 */
	List<Map<String, Object>> selectProductTypeList(@Param("ucompid")String ucompid);
	
	/**
	 * 查询一级产品类型
	 * @author LBY
	 * @data 2019年3月4日
	 * @return
	 */
	List<OtProducttype> selectFirstProductTypeList();
	
	/**
	 * 查询子级产品类型
	 * @author LBY
	 * @data 2019年3月4日
	 * @return
	 */
	List<OtProducttype> selectChildProductTypeList(@Param("uproducttypeid")String uproducttypeid);
}
