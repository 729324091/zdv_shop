package com.zdv.shop.mapper;

import com.zdv.shop.common.dao.MyMapper;
import com.zdv.shop.model.QtCompOrder;
import com.zdv.shop.model.QtCompOrderItem;
import com.zdv.shop.model.vo.AppCompOrderVo;
import com.zdv.shop.model.vo.AppOrderVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface QtCompOrderMapper extends MyMapper<QtCompOrder>{
	int insertCompOrder(QtCompOrder compOrder);
	int updateByIds(QtCompOrder compOrder);
	//3.店铺销售统计
	List<QtCompOrder> queryCompSalesReportList(QtCompOrder compOrder);
	//4.工作人员销售统计
	List<QtCompOrder> queryWorkerSalesReportList(QtCompOrder compOrder);
	//5.工作人员产品销售统计
	List<QtCompOrder> queryWorkerSalesProductReportList(QtCompOrder compOrder);
	//工作人员信息
	QtCompOrder queryWorkerinfo(@Param("udistributorid") String udistributorid,@Param("uopuserid") String uopuserid,@Param("ueflag") String ueflag);
	/**
	 * 查询订单列表
	 * 当ueflag = -1或upeflag = -1 时查询未完成订单
	 * @param compOrder
	 * @param starttime
	 * @param endttime
	 * @return
	 */
    List<QtCompOrder> queryCompOrderPage(@Param("order") QtCompOrder compOrder,@Param("starttime") String starttime,@Param("endttime") String endttime);
	List<QtCompOrder> queryPage(@Param("order") QtCompOrder compOrder);
	//获得单个订单信息
	QtCompOrder selectByuorderid(String uorderid);
	//获得对应统计日期 state;//0按日统计 1按月统计 2按季度统计 3 按年统计 4按周统计
	List<QtCompOrderItem> getTempDate(@Param("begindate") String begindate,@Param("enddate") String enddate,@Param("state") String state);
	//获得经销商统计信息
	/**
	 state;//0按日统计 1按月统计 2按季度统计 3 按年统计 4按周统计
	 types;//0本经销商的单个产品销售统计，1某个店的单个产品销售统计，2业务员销售统计
	 */
	List<QtCompOrderItem> getCompOrderReport(@Param("begindate") String begindate,@Param("enddate") String enddate,@Param("state") String state,@Param("types") String types,@Param("uproductid") String uproductid,@Param("udistributorid") String udistributorid,@Param("ucompid") String ucompid,@Param("uopuserid") String uopuserid);
	/*获得销售商统计
	 state;//0按日统计 1按月统计 2按季度统计 3 按年统计 4按周统计，
	 types;//0本销售商单个产品销售统计，1某个销售商的销售统计，2本销售商店员的销售统计
	*/
	List<QtCompOrderItem> getorderReport(@Param("begindate") String begindate,@Param("enddate") String enddate,@Param("state") String state,@Param("types") String types,@Param("uproductid") String uproductid,@Param("ucompid") String ucompid,@Param("uopuserid") String uopuserid);
	
	/**
	 * APP查询订单列表
	 * @author LBY
	 * @data 2019年1月16日
	 * @param udistributorid 经销商ID
	 * @param upeflag 发货方状态
	 * @param ueflag 进货方状态
	 * @return
	 */
	List<Map<String, Object>> selectOrderList(@Param("udistributorid")String udistributorid,
			@Param("upeflag")String upeflag, @Param("ueflag")String ueflag);
	
	/**
	 * 修改订单状态
	 * @author LBY
	 * @data 2019年1月16日
	 * @param uorderid	订单编号
	 * @param utype		类型，0发货方，1收货方
	 * @param ustatus	状态
	 * @return
	 */
	int updateStatusByOrderId(@Param("uorderid")String uorderid, @Param("utype")String utype, @Param("ustatus")String ustatus);
	
	/**
	 * 查询订单详情
	 * @author LBY
	 * @data 2019年1月17日
	 * @param uorderid
	 * @param utype
	 * @return
	 */
	AppOrderVo selectOrderItemByOrderId(@Param("uorderid")String uorderid, @Param("utype")String utype);
	
	/**
	 * APP扫码查询
	 * @author LBY
	 * @data 2019年1月23日
	 * @param udistributorid
	 * @param ucompid
	 * @param utype
	 * @return
	 */
	List<Map<String, Object>> selectOrderListAtApp(@Param("udistributorid")String udistributorid,
			@Param("ucompid")String ucompid, @Param("utype")String utype);
	//以前的 by jock
	int insertCompOrder(@Param("compOrderList")List<QtCompOrder> compOrder); 

	//List<QtCompOrder> queryPage(QtCompOrder order);
	//经销商销售统计
	List<QtCompOrder> querySalesReportList(QtCompOrder compOrder);
	//经销商销售统计总量 -->
	QtCompOrder querySalesReportCount(QtCompOrder compOrder);
	//2.商品经销商销售统计
	List<QtCompOrder> querySalesProductReportList(QtCompOrder compOrder);
	//详情
	List<QtCompOrder> querySalesProductDetailsReportList(QtCompOrder compOrder);
	
	/**
	 * 根据订单ID查询订单详情
	 * @author LBY
	 * @data 2019年1月8日
	 * @param uorderid
	 * @return
	 */
	@Deprecated
	AppCompOrderVo queryOrderDetailsByOrderID(@Param("uorderid")String uorderid);
	
	// List<Map<String, Object>> selectOrderList(@Param("udistributorid")String udistributorid,
	// @Param("upeflag")String upeflag, @Param("ueflag")String ueflag);
	
	/**
	 * 销售商查询进货订单
	 * @author LBY
	 * @data 2019年2月18日
	 * @param ucompid
	 * @param ueflag
	 * @return
	 */
	List<Map<String, Object>> selectGoodsInOrderList(@Param("ucompid")String ucompid,
			@Param("ueflag")String ueflag);
	/**
	 * 销售商进出统计 
	 */
	List<QtCompOrder> compSalesInOutReportList(QtCompOrder compOrder);
	/**
	 * 销售商进出总量统计 
	 */
	QtCompOrder compSalesInOutReportCount(QtCompOrder compOrder);
}
