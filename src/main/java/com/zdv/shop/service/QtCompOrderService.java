package com.zdv.shop.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.page.PageMethod;
import com.zdv.shop.common.annotation.ServiceLog;
import com.zdv.shop.common.pojo.PageAjax;
import com.zdv.shop.mapper.QtCompOrderMapper;
import com.zdv.shop.model.QtCompOrder;
import com.zdv.shop.model.QtCompOrderItem;
import com.zdv.shop.model.QtProductunit;
import com.zdv.shop.model.vo.AppCompOrderItemVo;
import com.zdv.shop.model.vo.AppCompOrderVo;
import com.zdv.shop.model.vo.AppOrderItemVo;
import com.zdv.shop.model.vo.AppOrderVo;

@Service
public class QtCompOrderService extends AbstratService<QtCompOrder> {

	@Autowired
	private QtCompOrderMapper mapper;
	
	@Autowired
	private QtProductunitService qtProductunitService;
	
	public  int insertCompOrder(QtCompOrder compOrder){
		return mapper.insertCompOrder(compOrder);
	}
	public int updateByIds(QtCompOrder compOrder){
		return mapper.updateByIds(compOrder);
	}

	@ServiceLog("店铺销售统计 ")
	public List<QtCompOrder> queryCompSalesReportList(QtCompOrder compOrder) {
		return mapper.queryCompSalesReportList(compOrder);
	}
	
	@ServiceLog("工作人员销售统计 ")
	public List<QtCompOrder> queryWorkerSalesReportList(QtCompOrder compOrder){
		return mapper.queryWorkerSalesReportList(compOrder);
	}
	//5.
	@ServiceLog("工作人员产品销售统计 ")
	public List<QtCompOrder> queryWorkerSalesProductReportList(QtCompOrder compOrder){
		return mapper.queryWorkerSalesProductReportList(compOrder);
	}
	@ServiceLog("工作人员信息 ")
	public QtCompOrder queryWorkerinfo(String udistributorid,String uopuserid,String ueflag){
		return mapper.queryWorkerinfo(udistributorid, uopuserid, ueflag);
	}
	@ServiceLog("分页查询")
	public PageAjax<QtCompOrder> queryOrderPage(PageAjax<QtCompOrder> page, QtCompOrder compOrder) {
		PageMethod.startPage(page.getPageNo(), page.getPageSize());
		List<QtCompOrder> list = mapper.queryPage(compOrder);
		return new PageAjax<QtCompOrder>(list);
	}

	public PageAjax<QtCompOrder> queryCompOrderPage(PageAjax<QtCompOrder> page, QtCompOrder compOrder,String starttime,String endtime) {
		PageMethod.startPage(page.getPageNo(), page.getPageSize());
		List<QtCompOrder> list = mapper.queryCompOrderPage(compOrder,starttime,endtime);
		return new PageAjax<QtCompOrder>(list);
	}


	public QtCompOrder selectByuorderid(String uorderid) {
		return mapper.selectByuorderid(uorderid);

	}
	// state;//0按日统计 1按月统计 2按季度统计 3 按年统计 4按周统计
	@ServiceLog("获得对应统计日期")
	public List<QtCompOrderItem> getTempDate(String begindate,String enddate,String state){
		return mapper.getTempDate(begindate, enddate, state);
	}
	//获得经销商统计信息
	/**
	 state;//0按日统计 1按月统计 2按季度统计 3 按年统计 4按周统计，
	 types;//0本经销商的单个产品销售统计，1某个店的单个产品销售统计，2业务员销售统计
	 */
	@ServiceLog("经销商统计")
	public List<QtCompOrderItem> getCompOrderReport(String begindate,String enddate,String state,String types,String uproductid,String udistributorid,String ucompid,String uopuserid){
		return mapper.getCompOrderReport(begindate, enddate, state, types, uproductid, udistributorid, ucompid, uopuserid);
	}
	/*
	 state;//0按日统计 1按月统计 2按季度统计 3 按年统计 4按周统计，
	 types;//0本销售商单个产品销售统计，1某个销售商的销售统计，2本销售商店员的销售统计
	*/
	@ServiceLog("获得销售商统计")
	public List<QtCompOrderItem> getorderReport(String begindate,String enddate,String state,String types,String uproductid,String ucompid,String uopuserid){
		return mapper.getorderReport(begindate, enddate, state, types, uproductid, ucompid, uopuserid);
	}
	@ServiceLog("APP查询订单列表")
	public List<Map<String, Object>> appQueryOrderPage(QtCompOrder compOrder) {
		if (compOrder.getPageNo() != null && compOrder.getPageSize() != null)
			PageMethod.startPage(compOrder.getPageNo(), compOrder.getPageSize(), false);
		return mapper.selectOrderList(compOrder.getUdistributorid(), compOrder.getUpeflag(), compOrder.getUeflag());
	}
	
	@ServiceLog("APP扫码查询")
	public List<Map<String, Object>> queryOrderListAtApp(QtCompOrder compOrder, String utype) {
		if (compOrder.getPageNo() != null && compOrder.getPageSize() != null)
			PageMethod.startPage(compOrder.getPageNo(), compOrder.getPageSize(), false);
		return mapper.selectOrderListAtApp(compOrder.getUdistributorid(), compOrder.getUcompid(), utype);
	}
	
	@ServiceLog("APP修改订单状态")
	public int updateStatusByOrderId(String uorderid, String utype, String ustatus) {
		return mapper.updateStatusByOrderId(uorderid, utype, ustatus);
	}
	
	@ServiceLog("APP查询订单详情")
	public AppOrderVo appQueryOrderItemPage(QtCompOrder compOrder, String utype) {
		if (compOrder.getPageNo() != null && compOrder.getPageSize() != null)
			PageMethod.startPage(compOrder.getPageNo(), compOrder.getPageSize(), false);
		AppOrderVo order = mapper.selectOrderItemByOrderId(compOrder.getUorderid(), utype);
		if (order != null) {
			List<AppOrderItemVo> itemList = order.getOrderItemList();
			if (itemList != null && itemList.size() > 0) {
				for (int i = 0; i < itemList.size(); i++) {
					String unitId = itemList.get(i).getUunitid();	// 单位ID
					QtProductunit child = qtProductunitService.queryChild(unitId);
					if (child != null) {
						itemList.get(i).setUchildunitname(child.getUunitname());
						itemList.get(i).setUcalerNum(child.getUcalernum());
					}
				}
			}
		}
		return order;
	}
	
	//以前的	 by jock 2019-02-13
	public boolean alter(QtCompOrder distributoOrder) {
		return mapper.updateByPrimaryKey(distributoOrder) > 0;
	}
	//经销商销售统计
	public List<QtCompOrder> querySalesReportList(QtCompOrder compOrder){
		return mapper.querySalesReportList(compOrder);
	}
	//经销商销售统计总量 -->
	public QtCompOrder querySalesReportCount(QtCompOrder compOrder){
		return mapper.querySalesReportCount(compOrder);
	}
	//2.商品经销商销售统计
	public List<QtCompOrder> querySalesProductReportList(QtCompOrder compOrder){
		return mapper.querySalesProductReportList(compOrder);
	}
	//详情
	public List<QtCompOrder> querySalesProductDetailsReportList(QtCompOrder compOrder){
		return mapper.querySalesProductDetailsReportList(compOrder);
	}
	// 查询单条记录
	public QtCompOrder queryOne(QtCompOrder record) {
		return mapper.selectOne(record);
	}
	/**
	 * 根据订单ID查询订单详情
	 * @author LBY
	 * @data 2019年1月8日
	 * @param uorderid
	 * @return
	 */
	@Deprecated
	public AppCompOrderVo queryOrderDetailsByOrderID(String uorderid, Integer pageNo, Integer pageSize) {
		if (pageNo != null && pageSize != null)
			PageMethod.startPage(pageNo, pageSize, false);
		AppCompOrderVo orderVo = mapper.queryOrderDetailsByOrderID(uorderid);
		if (orderVo != null) {
			List<AppCompOrderItemVo> itemList = orderVo.getOrderItemList();
			if (itemList != null && itemList.size() > 0) {
				for (AppCompOrderItemVo item : itemList) {
					String unitId = item.getUunitid();	// 单位ID
					QtProductunit child = qtProductunitService.queryChild(unitId);
					if (child != null) {
						item.setUchildunitname(child.getUunitname());
						item.setUcalerNum(child.getUcalernum());
						item.setUprice(String.valueOf(item.getUproductnum() * qtProductunitService.getLowestCalerNum(unitId)));
					}
				}
			}
		}
		return orderVo; 
	}
	
	/**
	 * 销售商查询进货订单列表
	 * @author LBY
	 * @data 2019年2月18日
	 * @param compOrder
	 * @return
	 */
	public List<Map<String, Object>> queryGoodsInOrderList(QtCompOrder compOrder) {
		if (compOrder.getPageNo() != null && compOrder.getPageSize() != null)
			PageMethod.startPage(compOrder.getPageNo(), compOrder.getPageSize(), false);
		return mapper.selectGoodsInOrderList(compOrder.getUcompid(), compOrder.getUeflag());
	}

	/**
	 * 销售商进出统计 
	 */
	public List<QtCompOrder> compSalesInOutReportList(QtCompOrder compOrder){
		return mapper.compSalesInOutReportList(compOrder);
	}
	/**
	 * 销售商进出总量统计 
	 */
	public QtCompOrder compSalesInOutReportCount(QtCompOrder compOrder){
		return mapper.compSalesInOutReportCount(compOrder);
	}


	public PageAjax<QtCompOrder> salesproductreportPage(PageAjax<QtCompOrder> page, QtCompOrder compOrder) {
		PageMethod.startPage(page.getPageNo(), page.getPageSize());
		List<QtCompOrder> list = mapper.querySalesProductReportList(compOrder);

		return new PageAjax<>(list);

	}

}
