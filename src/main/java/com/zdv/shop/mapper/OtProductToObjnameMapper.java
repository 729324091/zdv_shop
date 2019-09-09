package com.zdv.shop.mapper;

import java.util.List;

import com.zdv.shop.common.dao.MyMapper;
import com.zdv.shop.model.OtProductToObjname;

/**
 * 产品与产品属性中间表Mapper
 * @author LBY
 * @date: 2018年12月7日
 */
public interface OtProductToObjnameMapper extends MyMapper<OtProductToObjname> {

	
	int insertBatch(List<OtProductToObjname> list);
}
