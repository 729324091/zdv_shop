package com.zdv.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zdv.shop.mapper.OtProductobjnameMapper;
import com.zdv.shop.model.OtProductobjname;

import tk.mybatis.mapper.entity.Example;

/**
 * 产品属性名Service
 * @author LBY
 * @date: 2018年12月7日
 */
@Service
public class OtProductobjnameService extends AbstratService<OtProductobjname> {

	@Autowired
	OtProductobjnameMapper mapper;
	
	/**
	 * 根据属性名称统计数量
	 * @author LBY
	 * @data 2019年1月23日
	 * @param uobjName
	 * @return
	 */
	public int countByObjName(OtProductobjname objName) {
		if (objName.getUobjnameid() != null) {
			Example example = new Example(OtProductobjname.class);
			example.createCriteria().andEqualTo("uobjname", objName.getUobjnameid()).andNotEqualTo("uobjnameid", objName.getUobjnameid());
			return mapper.selectCountByExample(example);
		}
		return mapper.selectCount(objName);
	}
}
