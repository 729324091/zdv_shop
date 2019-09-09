package com.zdv.shop.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zdv.shop.common.dao.MyMapper;
import com.zdv.shop.model.OtCompProductStockObjname;

/**
 * 销售商产品库存对应产品属性中间表
 * @author LBY
 * @data 2019年2月20日 
 */
public interface OtCompProductStockObjnameMapper extends MyMapper<OtCompProductStockObjname> {
	List<OtCompProductStockObjname> getObjnamesByucompproductid(@Param(value = "ucompproductid") String ucompproductid);

}
