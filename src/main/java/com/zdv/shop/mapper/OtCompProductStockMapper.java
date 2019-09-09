package com.zdv.shop.mapper;

import com.zdv.shop.common.dao.MyMapper;
import com.zdv.shop.model.OtCompProductStock;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 销售商产品库存与产品属性表
 * @author LBY
 * @data 2019年2月20日 
 */
public interface OtCompProductStockMapper extends MyMapper<OtCompProductStock> {

	/**
	 * 根据销售商关联产品ID查询属性库存
	 * @author LBY
	 * @data 2019年2月26日
	 * @param ucomproductid
	 * @return
	 */
	List<Map<String, Object>> selectProductStockByUcomproductid(@Param("ucomproductid")String ucomproductid);
	
	/**
	 * 根据销售商关联产品ID查询属性名list
	 * @author LBY
	 * @data 2019年2月26日
	 * @param ucomproductid
	 * @return
	 */
	List<Map<String, Object>> selectObjNameList(@Param("ucomproductid")String ucomproductid);
	
	/**
	 * 查询属性值List 
	 * @author LBY
	 * @data 2019年2月26日
	 * @param uproductstockid
	 * @param uobjnameid
	 * @return
	 */
	List<Map<String, Object>> selectObjValList(@Param("uproductstockid")String uproductstockid, @Param("uobjnameid")String uobjnameid);

	/**
	 * 属性值属性名查询库存
	 * @param uproductid
	 * @param uobjname
	 * @param uobjvalue
	 * @return
	 */
	List<Map<String, Object>> queryByObjnamevalue(@Param("ucomproductid") String uproductid,@Param("uobjname") String uobjname,@Param("uobjvalue") String uobjvalue);

	List<Map<String,Object>> queryByObjnamevalues(@Param("ucomproductid")String uproductid,@Param("uobjname") String uobjname,@Param("uobjvalue") String uobjvalue,@Param("uobjname1") String uobjname1,@Param("uobjvalue1") String uobjvalue1);
}
