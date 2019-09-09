package com.zdv.shop.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.page.PageMethod;
import com.zdv.shop.common.annotation.ServiceLog;
import com.zdv.shop.common.pojo.AjaxResult;
import com.zdv.shop.common.pojo.PageAjax;
import com.zdv.shop.common.utils.AppUtil;
import com.zdv.shop.mapper.QtUserProductWinMapper;
import com.zdv.shop.model.QtCashOffset;
import com.zdv.shop.model.QtCashOrder;
import com.zdv.shop.model.QtUserProductWin;

@Service
public class QtUserProductWinService extends AbstratService<QtUserProductWin> {
	//用户中奖表
	@Autowired
	private QtUserProductWinMapper userProductWinMapper;

	@ServiceLog("用户中奖表列表")
	public PageAjax<QtUserProductWin> queryListPage(PageAjax<QtUserProductWin> page, QtUserProductWin userProductWin){
		PageMethod.startPage(page.getPageNo(), page.getPageSize());
		List<QtUserProductWin> list = userProductWinMapper.queryListAjax(userProductWin);
		return AppUtil.returnPage(list);
	}
	@ServiceLog("用户中奖表列表")
	public List<QtUserProductWin> queryList( QtUserProductWin userProductWin){
		if(userProductWin.getPageNo()==null||userProductWin.getPageNo().equals(""))
			userProductWin.setPageNo(0);
		List<QtUserProductWin> list = userProductWinMapper.queryList(userProductWin);
		return list;
	}
	@ServiceLog("未核销的销售商列表")
	public List<QtCashOrder> queryCompCashList( QtUserProductWin userProductWin){
		List<QtCashOrder> list = userProductWinMapper.queryCompCashList(userProductWin);
		return list;
	}
	@ServiceLog("经销商需要核销的")
	public List<QtCashOrder> queryDistributorCashWinList(QtUserProductWin userProductWin){
		List<QtCashOrder> list = userProductWinMapper.queryDistributorCashWinList(userProductWin);
		return list;
	}
	@ServiceLog("销售商未核销的产品 ")
	public List<QtUserProductWin> queryCompCashProductList( QtUserProductWin userProductWin){
		List<QtUserProductWin> list = userProductWinMapper.queryCompCashProductList(userProductWin);
		return list;
	}
	@ServiceLog("针对销售商核销中的产品")
	public List<QtUserProductWin> queryCompCashProductingList(QtUserProductWin userProductWin){
		List<QtUserProductWin> list = userProductWinMapper.queryCompCashProductingList(userProductWin);
		return list;
	}
	//
	@ServiceLog("用户兑奖历史记录")
	public List<QtUserProductWin> queryUserCompCashList(QtUserProductWin userProductWin){
		return userProductWinMapper.queryUserCompCashList(userProductWin);
	}
	@ServiceLog("销售商核销历史数据")
	public List<QtUserProductWin> queryCompCashhisList(QtUserProductWin userProductWin){
		List<QtUserProductWin> list = userProductWinMapper.queryCompCashhisList(userProductWin);
		return list;
	}
	@ServiceLog("经销商未核销的产品 ")
	public List<QtUserProductWin> queryDistributorCashProductList(QtCashOffset cashoffset){
		List<QtUserProductWin> list = userProductWinMapper.queryDistributorCashProductList(cashoffset);
		return list;
	}
	@ServiceLog("经销商已核销的记录")
	public List<QtCashOrder> queryDistributorCashList(QtCashOrder cashOrder){
		List<QtCashOrder> list = userProductWinMapper.queryDistributorCashList(cashOrder);
		return list;
	}
	@ServiceLog("用户中奖表")
	public QtUserProductWin selectObjById(String uproductwinid){
		return userProductWinMapper.selectObjById(uproductwinid);
	}
	@ServiceLog("添加用户中奖表")
	public AjaxResult insertsAjax(QtUserProductWin userProductWin) {
		int n = userProductWinMapper.inserts(userProductWin);
		if(n>0)
			return AppUtil.returnObj(null);
		else
			return AppUtil.returnObj("失败");
	}
	@ServiceLog("插入用户中奖表")
	public int inserts(QtUserProductWin userProductWin) {
		return userProductWinMapper.inserts(userProductWin);
	}
	@ServiceLog("修改用户中奖表")
	public AjaxResult updateByIdsAjax(QtUserProductWin userProductWin) {
		int n = userProductWinMapper.updateByIds(userProductWin);
		if(n>0)
			return AppUtil.returnObj(null);
		else
			return AppUtil.returnObj("失败");
	}

	@ServiceLog("查找总数量")
	public int compCashProductAllCount(QtUserProductWin userProductWin) {
		return userProductWinMapper.queryCompCashProductAllCount(userProductWin);
	}
	@ServiceLog("查找总数量")
	public int queryDistributorCashProductAllCount(QtUserProductWin userProductWin) {
		return userProductWinMapper.queryDistributorCashProductAllCount(userProductWin);
	}
	@ServiceLog("删除用户中奖表")
	public AjaxResult delObjByIdAjax(String uproductwinid) {
		int n = userProductWinMapper.delObjById(uproductwinid);
		if(n>0)
			return AppUtil.returnObj(null);
		else
			return AppUtil.returnObj("失败");
	}
	@ServiceLog("删除用户中奖表")
	public int delObjById(String uproductwinid) {
		return userProductWinMapper.delObjById(uproductwinid);
	}


	@ServiceLog("删除核销中间表")
	public int delObjByIdcashoffset(String ucompid,String uproductwinid,String ucreatedate){
		return userProductWinMapper.delObjBycashoffset(ucompid,uproductwinid,ucreatedate);
	}
	@ServiceLog("确认订单时修改每个单品状态")
	public int updateObjBycashoffsetorderid(String ucashorderid,String udistributorid,String ueflag){
		return userProductWinMapper.updateObjBycashoffsetorderid(ucashorderid,udistributorid,ueflag);
	}
	
	@ServiceLog("批量插入已经兑换内容")
	public int insertsCompProductWin(QtCashOffset cashoffset){
		return userProductWinMapper.insertsCompProductWin(cashoffset);
	}
	@ServiceLog("经销商批量插入")
	public int insertsDistributorProductWin(QtCashOffset cashoffset){
		return userProductWinMapper.insertsDistributorProductWin(cashoffset);
	}
	@ServiceLog("批量修改用户中奖数据")
	public int updateObjByuserproductwin(String ucompid,String udistributorid,String ueflag,String ucreatedate){
		return userProductWinMapper.updateObjByuserproductwin(ucompid,udistributorid,ueflag,ucreatedate);
	}
	@ServiceLog("批量修改经销商中奖数据")
	public int updateObjBycashoffset(String ucompid,String udistributorid,String ueflag,String ucreatedate){
		return userProductWinMapper.updateObjBycashoffset(ucompid,udistributorid,ueflag,ucreatedate);
	}
	//兑奖订单管理
	@ServiceLog("兑奖订单列表")
	public List<QtCashOrder>  queryCashOrderList(QtCashOrder cashOrder){
		List<QtCashOrder> list = userProductWinMapper.queryCashOrderList(cashOrder);
		return list;
	}
	@ServiceLog("兑奖订单下单")
	public int insertCashOrder(QtCashOrder cashOrder){
		return userProductWinMapper.insertCashOrder(cashOrder);
	}
	@ServiceLog("兑奖订单修改")
	public int updateByIdCashOrder(QtCashOrder cashOrder){
		return userProductWinMapper.updateByIdCashOrder(cashOrder);
	}
	@ServiceLog("单个兑奖订单")
	public QtCashOrder selectCashOrder(String ucashorderid) {
		return userProductWinMapper.selectCashOrder(ucashorderid);
	}
}
