package com.zdv.shop.mapper;

import java.util.List;
import java.util.Map;

import com.zdv.shop.common.dao.MyMapper;
import com.zdv.shop.model.OtProductobjvalue;

/**
 * 产品属性值Mapper
 * @author LBY
 * @date: 2018年12月7日
 */
public interface OtProductobjvalueMapper extends MyMapper<OtProductobjvalue> {
	
	int insertBatch(List<OtProductobjvalue> list);
	
	List<Map<String, Object>> selectPage(OtProductobjvalue objValue);
}
