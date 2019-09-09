package com.zdv.shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zdv.shop.mapper.OtProductToObjnameMapper;
import com.zdv.shop.model.OtProductToObjname;

/**
 * 产品与产品属性中间表 Service
 * @author LBY
 * @date: 2018年12月7日
 */
@Service
public class OtProductToObjnameService extends AbstratService<OtProductToObjname> {

	@Autowired
	OtProductToObjnameMapper mapper;
	
	public boolean addList(List<OtProductToObjname> list) {
		return mapper.insertBatch(list) > 0;
	}
	
	public boolean delByProductid(String uproductid) {
		OtProductToObjname dArg = new OtProductToObjname();
		dArg.setUproductid(uproductid);
		return mapper.delete(dArg) > 0;
	}
	
	public List<OtProductToObjname> queryByProductid(String uproductid) {
		OtProductToObjname record = new OtProductToObjname();
		record.setUproductid(uproductid);
		return mapper.select(record);
	}
}
