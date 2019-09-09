package com.zdv.shop.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zdv.shop.mapper.OtCompProductStockMapper;
import com.zdv.shop.model.OtCompProductStock;

/**
 * 销售商产品库存与产品属性表
 * @author LBY
 * @data 2019年2月20日 
 */
@Service
public class OtCompProductStockService extends AbstratService<OtCompProductStock> {

	@Autowired
	private OtCompProductStockMapper mapper;
	
	/**
	 * 根据销售商关联产品ID查询属性名list
	 * @author LBY
	 * @data 2019年2月26日
	 * @param ucomproductid
	 * @return
	 */
	public List<Map<String, Object>> queryObjNameList(String ucomproductid) {
		List<Map<String, Object>> objList = mapper.selectObjNameList(ucomproductid);
		if (objList != null) {
			for (Map<String, Object> map : objList)
				map.put("valList", mapper.selectObjValList(map.get("uproductstockid").toString(), map.get("uobjnameid").toString()));
		}
		return objList;
	}

	/**
	 * 通过商品id
	 * 属性值属性名查询库存
	 * @param uproductid
	 * @param uobjname
	 * @param uobjvalue
	 * @return
	 */
	public List<Map<String, Object>> queryByObjnamevalue(String uproductid, String uobjname, String uobjvalue) {
		return mapper.queryByObjnamevalue(uproductid, uobjname, uobjvalue);

	}

	public List<Map<String, Object>> queryByObjnamevalues(String uproductid, String uobjname, String uobjvalue, String uobjname1, String uobjvalue1) {
		return mapper.queryByObjnamevalues(uproductid, uobjname, uobjvalue,uobjname1,uobjvalue1);
	}

	public List<Map<String, Object>> queryListByUcomproductid(String ucomproductid) {
		return mapper.selectProductStockByUcomproductid(ucomproductid);
	}
}
