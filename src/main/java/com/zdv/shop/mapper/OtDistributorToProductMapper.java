package com.zdv.shop.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.zdv.shop.common.dao.MyMapper;
import com.zdv.shop.model.OtDistributorToProduct;
import com.zdv.shop.model.OtProducttype;
import com.zdv.shop.model.vo.AppQueryStockArgVo;
import com.zdv.shop.model.vo.AppQueryStockVo;

/**
 * 经销商关联产品
 * @author LBY
 * @date: 2018年12月7日
 */
public interface OtDistributorToProductMapper extends MyMapper<OtDistributorToProduct> {

	/**
	 * APP查询库存
	 * @author LBY
	 * @date 2019年1月3日
	 * @param udistributorid 	经销商ID
	 * @param uproducttypeid 	产品类型ID
	 * @param keywords 			关键字，根据产品名称模糊查询
	 * @param stock 			库存数量，查询小于等于该指数的记录
	 * @param uqrcode 			产品二维码编号
	 * @return
	 */
	List<AppQueryStockVo> appQueryStock(AppQueryStockArgVo vo);
	
	/**
	 * APP查询库存页面中的产品类型
	 * @author LBY
	 * @date 2019年1月3日
	 * @param distributorid
	 * @return
	 */
	List<OtProducttype> appQueryProductTypeList(@Param("udistributorid")String udistributorid);
	
	/**
	 * 查询补货列表
	 * @author LBY
	 * @data: 2019年1月7日
	 * @param udistributorid
	 * @return
	 */
//	List<AppQueryStockVo> appQueryReplenishList(@Param("udistributorid")String udistributorid);

	List<OtDistributorToProduct> selectList(OtDistributorToProduct distributorToProduct);
	//获得指定地区代理某个产品的终端代理商 通过产品ID和地区 终端代理商要ulevel标识
	List<OtDistributorToProduct> queryDistributorByProductId(Map params);
}
