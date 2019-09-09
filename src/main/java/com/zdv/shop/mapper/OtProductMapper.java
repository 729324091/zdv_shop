package com.zdv.shop.mapper;

import com.zdv.shop.common.dao.MyMapper;
import com.zdv.shop.model.OtProduct;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 产品mapper
 * @author LBY
 * @date: 2018年12月7日
 */
public interface OtProductMapper extends MyMapper<OtProduct> {

	OtProduct selOtproductById(@Param("uproductid")String uproductid,@Param("uproducttypeid")String uproducttypeid);
	
	List<OtProduct> selectList(OtProduct otProduct);

	List<Map<String,Object>> queryProductList(String uproducttypeid);
}
