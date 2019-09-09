package com.zdv.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zdv.shop.mapper.QtProductunitMapper;
import com.zdv.shop.model.QtProductunit;

/**
 * 产品单位Service
 * @author LBY
 * @date 2019年1月3日
 */
@Service
public class QtProductunitService extends AbstratService<QtProductunit> {

	@Autowired
	QtProductunitMapper mapper;
	
	/**
	 * 根据主键id查询第一级产品单位下的所有的子单位——慎用，调用递归
	 * @author LBY
	 * @date 2019年1月3日
	 * @param uunitid
	 * @return
	 */
	public QtProductunit queryTree(String uproductid) {
		QtProductunit firstGrade = mapper.selectFirstGrade(uproductid);
		if (firstGrade != null)
			firstGrade.setChildUnit(this.queryChild(firstGrade.getUunitid()));
		return firstGrade;
	}
	
	/**
	 * 根据父级id，递归查询所有的的子产品单位信息
	 * @author LBY
	 * @date 2019年1月3日
	 * @param upunitid 产品单位ID,作为父id查询
	 * @return
	 */
	/*public QtProductunit queryChild(String upunitid) {
		if (!StringUtils.isEmpty(upunitid)) {
			QtProductunit child = mapper.selectChild(upunitid);
			if (child != null)
				child.setChildUnit(this.queryChild(child.getUunitid()));
			return child;
		}
		return null;
	}*/
	
	/**
	 * 查询第一级单位信息
	 * @author LBY
	 * @date 2019年1月3日
	 * @param uproductid
	 * @return
	 */
	public QtProductunit queryFirstGrade(String uunitid) {
		if (uunitid != null)
			return mapper.selectFirstGrade(uunitid);
		return null;
	}
	
	/**
	 * 根据父级id查询子单位
	 * @author LBY
	 * @date 2019年1月3日
	 * @param upunitid
	 * @return
	 */
	public QtProductunit queryChild(String upunitid) {
		if (upunitid != null)
			return mapper.selectChild(upunitid);
		return null;
	}
	
	/**
	 * 获取与最小单位的兑换数
	 * @author LBY
	 * @data 2019年1月8日
	 * @param uunitid
	 * @return
	 */
	public Integer getLowestCalerNum(String uunitid) {
		QtProductunit unit = mapper.selectByPrimaryKey(uunitid);
		if (unit == null)
			return null;
		QtProductunit tempUnit = unit;
		Integer calerNum = 1;
		while(true) {
			tempUnit = mapper.selectChild(tempUnit.getUunitid());
			if (tempUnit == null)
				break;
			calerNum = calerNum.intValue() * tempUnit.getUcalernum().intValue();
		}
		return calerNum;
	}
}
