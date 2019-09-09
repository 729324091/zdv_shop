package com.zdv.shop.service;


import com.zdv.shop.mapper.CtProfitMapper;
import com.zdv.shop.model.CtProfit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CtProfitService extends AbstratService<CtProfit> {
	@Autowired
	private CtProfitMapper cProfitMapper;

	public List<CtProfit> queryAll(){
		return cProfitMapper.queryAll();
	}
	public CtProfit queryProfitByCompId(String ucompid,String ucustomerid) {
		return cProfitMapper.queryProfitByCompId(ucompid, ucustomerid);
	}
	public CtProfit queryByCtProfit(CtProfit cePro) {
		return cProfitMapper.queryByCtProfit(cePro);
	}

	public boolean updateByCustomer(CtProfit share) {
		return cProfitMapper.updateByCustomer(share)>0?true:false;
	}

	public boolean deleteByCustomer (CtProfit share) {
		return cProfitMapper.deleteByCustomer(share)>0?true:false;
	}

	public boolean inserts(CtProfit share) {
		return cProfitMapper.inserts(share)>0?true:false;
	}
}
