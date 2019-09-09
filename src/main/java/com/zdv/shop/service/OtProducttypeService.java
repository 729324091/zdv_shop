package com.zdv.shop.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zdv.shop.mapper.OtProducttypeMapper;
import com.zdv.shop.model.OtProducttype;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

/**
 * 产品类型
 * @author LBY
 * @date: 2018年12月7日
 */
@Service
public class OtProducttypeService extends AbstratService<OtProducttype> {

	@Autowired
	private OtProducttypeMapper mapper;
	
	public boolean alter(OtProducttype otProducttype) {
		return mapper.updateByPrimaryKey(otProducttype) > 0;
	}
	
	public boolean add(OtProducttype otProducttype) {
		return mapper.insert(otProducttype) > 0;
	}
	
	public List<OtProducttype> productTypePage(String uparentid){
		return mapper.productTypePage(uparentid);
	}
	
	public int countByUname(OtProducttype otProducttype) {
		Example example = new Example(OtProducttype.class);
		Criteria criteria = example.createCriteria().andEqualTo("udname", otProducttype.getUname());
		if (otProducttype.getUproducttypeid() != null)
			criteria.andNotEqualTo("uproducttypeid", otProducttype.getUproducttypeid());
		return mapper.selectCountByExample(example);
	}
	
	public List<Map<String, Object>> queryProductTypeList(String ucompid) {
		return mapper.selectProductTypeList(ucompid);
	}
	
	/**
	 * h5 查询产品分类
	 * @author LBY
	 * @data 2019年3月4日
	 * @return
	 */
	public List<OtProducttype> queryProductTypeListWithH5() {
		//查询产品类型表所有父级数据
		List<OtProducttype> list = mapper.selectFirstProductTypeList();
		if (list != null && list.size() > 0) {
			for (OtProducttype otProducttype : list)
				otProducttype.setUchildList(mapper.selectChildProductTypeList(otProducttype.getUproducttypeid()));
		}
		return list;
	}
}
