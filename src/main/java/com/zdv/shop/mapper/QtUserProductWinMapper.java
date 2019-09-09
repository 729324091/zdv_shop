package com.zdv.shop.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zdv.shop.common.dao.MyMapper;
import com.zdv.shop.model.QtCashOffset;
import com.zdv.shop.model.QtCashOrder;
import com.zdv.shop.model.QtUserProductWin;

/**
 * 用户核销表mapper
 * @author Jock
 * @date: 2018年12月18日
 */
public interface QtUserProductWinMapper extends MyMapper<QtUserProductWin> {
	List<QtUserProductWin> queryListAjax(@Param("userProductWin")QtUserProductWin userProductWin);
	List<QtUserProductWin> queryList(QtUserProductWin userProductWin);	
	//销售商需要核销的
	List<QtCashOrder> queryCompCashList(QtUserProductWin userProductWin);
	//经销商需要核销的
	List<QtCashOrder> queryDistributorCashWinList(QtUserProductWin userProductWin);
	//销售商未核销的产品 
	List<QtUserProductWin> queryCompCashProductList(QtUserProductWin userProductWin);
	//针对销售商核销中的产品
	List<QtUserProductWin> queryCompCashProductingList(QtUserProductWin userProductWin);
	//经销商未核销的产品 
	List<QtUserProductWin> queryDistributorCashProductList(QtCashOffset cashoffset);
	//用户兑奖历史记录
	List<QtUserProductWin> queryUserCompCashList(QtUserProductWin userProductWin);
	
	List<QtUserProductWin> queryCompCashhisList(QtUserProductWin userProductWin);
	//经销商已核销的记录
	List<QtCashOrder> queryDistributorCashList(QtCashOrder cashOrder);
	QtUserProductWin selectObjById(@Param("uproductwinid")String uproductwinid);
	int inserts(QtUserProductWin userProductWin);
	int updateByIds(QtUserProductWin userProductWin);
	int delObjById(@Param("uproductwinid")String uproductwinid);
	int insertcashoffset(QtCashOffset cashoffset);
	int updateByIdcashoffset(QtCashOffset cashoffset);
	int delObjBycashoffset(@Param("ucompid")String ucompid,@Param("uproductwinid")String uproductwinid,@Param("ucreatedate")String ucreatedate);
	int queryCompCashProductAllCount(QtUserProductWin userProductWin);
	int queryDistributorCashProductAllCount(QtUserProductWin userProductWin);
	//批量插入已经兑换内容
	int insertsCompProductWin(QtCashOffset cashoffset);
	//经销商批量插入
	int insertsDistributorProductWin(QtCashOffset cashoffset);
	int updateObjByuserproductwin(@Param("ucompid")String ucompid,@Param("udistributorid")String udistributorid,@Param("ueflag")String ueflag,@Param("ucreatedate")String ucreatedate);
	int updateObjBycashoffset(@Param("ucompid")String ucompid,@Param("udistributorid")String udistributorid,@Param("ueflag")String ueflag,@Param("ucreatedate")String ucreatedate);
	//确认订单时修改每个单品状态
	int updateObjBycashoffsetorderid(@Param("ucashorderid")String ucashorderid,@Param("udistributorid")String udistributorid,@Param("ueflag")String ueflag);
	//兑奖订单管理
	List<QtCashOrder>  queryCashOrderList(QtCashOrder cashOrder);
	int insertCashOrder(QtCashOrder cashOrder);
	int updateByIdCashOrder(QtCashOrder cashOrder);
	QtCashOrder selectCashOrder(@Param("ucashorderid")String ucashorderid);
}
