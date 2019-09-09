package com.zdv.shop.mapper;

import com.zdv.shop.common.dao.MyMapper;
import com.zdv.shop.model.QtCompOrderItem;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface QtCompOrderItemMapper extends MyMapper<QtCompOrderItem>{

	int insertOrderItemList(@Param("orderItemList")List<QtCompOrderItem> orderItemList);
	
	int inserts(QtCompOrderItem orderItem);
	
	List<QtCompOrderItem> queryOrderItemById(@Param("udistributorid")String udistributorid,@Param("keywords")String keywords);
	
	QtCompOrderItem queryItemById(@Param("uproductid")String uproductid,@Param("uunitid")String uunitid,@Param("udistributorid")String udistributorid,@Param("ucompid")String ucompid);

	/*int updateByIds(@Param("udistributorid")String udistributorid,@Param("uparentdistributorid")String uparentdistributorid);*/
	int updateByIds(QtCompOrderItem orderItem);
	QtCompOrderItem queryOrderItemList(@Param("uorderitemid")String uorderitemid,@Param("uorderid")String uorderid,@Param("uproductid")String uproductid);

	/**
	 * 页面
	 * @param compOrderItem
	 * @return
	 */
    List<QtCompOrderItem> queryItemPage(QtCompOrderItem compOrderItem);

}
