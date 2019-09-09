package com.zdv.shop.mapper;

import com.zdv.shop.common.dao.MyMapper;
import com.zdv.shop.model.QtProductunit;

/**
 * 产品单位Mapper
 * @author LBY
 * @date 2019年1月3日
 */
public interface QtProductunitMapper extends MyMapper<QtProductunit>{

	/**
	 * 查询一级产品单位信息
	 * @author LBY
	 * @date 2019年1月3日
	 * @param uproductid
	 * @return
	 */
	QtProductunit selectFirstGrade(String uproductid);
	
	/**
	 *查询子单位信息
	 * @author LBY
	 * @date 2019年1月3日
	 * @param upunitid
	 * @return
	 */
	QtProductunit selectChild(String upunitid);
}
