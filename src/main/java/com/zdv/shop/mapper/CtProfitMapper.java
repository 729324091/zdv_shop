package com.zdv.shop.mapper;


import com.zdv.shop.common.dao.MyMapper;
import com.zdv.shop.model.CtProfit;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CtProfitMapper extends MyMapper<CtProfit> {
	List<CtProfit> queryAll();
	CtProfit queryProfitByCompId(@Param("ucompid") String ucompid, @Param("ucustomerid") String ucustomerid);
	CtProfit queryByCtProfit(CtProfit share);
	int updateByCustomer(CtProfit share);//根据商户来更新数据
	int deleteByCustomer(CtProfit share);//根据商户来删除数据
	int inserts(CtProfit share);
}