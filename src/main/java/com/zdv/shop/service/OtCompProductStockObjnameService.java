package com.zdv.shop.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zdv.shop.mapper.OtCompProductStockObjnameMapper;
import com.zdv.shop.model.OtCompProductStockObjname;

/**
 * 销售商产品库存对应产品属性中间表
 * @author LBY
 * @data 2019年2月20日 
 */
@Service
public class OtCompProductStockObjnameService extends AbstratService<OtCompProductStockObjname> {

	@Autowired
	private OtCompProductStockObjnameMapper mapper;
	
	public Map<String, ArrayList<String>> getObjnamesByucompproductid(String ucompproductid){

		List<OtCompProductStockObjname> objnames = mapper.getObjnamesByucompproductid(ucompproductid);
		List<String> uobjnameids = new ArrayList<>();
		Map<String, ArrayList<String>> objnameContain = new HashMap<>();
		for (OtCompProductStockObjname otCompProductStockObjname : objnames) {
			boolean flag = true;
			for (String string : uobjnameids) {
				if(string.equals(otCompProductStockObjname.getUobjnameid())) {
					ArrayList<String> arrayList = objnameContain.get(otCompProductStockObjname.getUobjname());
					if (!arrayList.contains(otCompProductStockObjname.getUobjvalue())) {

						arrayList.add(otCompProductStockObjname.getUobjvalue());
					}
					flag = false;
				}
			}
			//进入则说明该getUobjnameid是第一次出现,那么把他加入到容器中
			if(flag) {
				uobjnameids.add(otCompProductStockObjname.getUobjnameid());
				ArrayList<String> arrayList = new ArrayList<>();

					arrayList.add(otCompProductStockObjname.getUobjvalue());
				objnameContain.put(otCompProductStockObjname.getUobjname(), arrayList);
			}
		}
		return objnameContain;
		
	}
	
}
