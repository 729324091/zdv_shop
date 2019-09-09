package com.zdv.shop.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.page.PageMethod;
import com.zdv.shop.common.pojo.PageAjax;
import com.zdv.shop.common.utils.AppUtil;
import com.zdv.shop.mapper.OtProductobjvalueMapper;
import com.zdv.shop.model.OtProductobjvalue;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

/**
 * 产品属性值 Service
 * @author LBY
 * @date: 2018年12月7日
 */
@Service
public class OtProductobjvalueService extends AbstratService<OtProductobjvalue> {

	@Autowired
	OtProductobjvalueMapper mapper;
	
	public boolean addList(List<OtProductobjvalue> objValues) {
		return mapper.insertBatch(objValues) > 0;
	}
	
	/**
	 * 根据属性名ID统计数量
	 * @author LBY
	 * @data 2019年1月22日
	 * @param uobjnameid
	 * @return
	 */
	public int countByObjnameid(String uobjnameid) {
		OtProductobjvalue arg = new OtProductobjvalue();
		arg.setUobjnameid(uobjnameid);
		return mapper.selectCount(arg);
	}
	
	/**
	 * 统计属性值数量
	 * @author LBY
	 * @data 2019年1月23日
	 * @param objVal
	 * @return
	 */
	public int countByObjVal(OtProductobjvalue objVal) {
		Example example = new Example(OtProductobjvalue.class);
		Criteria criteria = example.createCriteria().andEqualTo("uobjvalue", objVal.getUobjvalue());
		if (objVal.getUobjnameid() != null)
			criteria.andEqualTo("uobjnameid", objVal.getUobjnameid());
		if (objVal.getUobjvalueid() != null)
			criteria.andNotEqualTo("uobjvalueid", objVal.getUobjvalueid());
		return mapper.selectCountByExample(example);
	}
	
	public PageAjax<Map<String, Object>> queryObjvaluePage(PageAjax<OtProductobjvalue> page, OtProductobjvalue objValue) {
		PageMethod.startPage(page.getPageNo(), page.getPageSize());
		return AppUtil.returnPage(mapper.selectPage(objValue));
	}
}
