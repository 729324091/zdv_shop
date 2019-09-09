package com.zdv.shop.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zdv.shop.model.OtCompToProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.page.PageMethod;
import com.zdv.shop.common.pojo.PageAjax;
import com.zdv.shop.common.utils.KeyId;
import com.zdv.shop.mapper.OtProductMapper;
import com.zdv.shop.model.OtProduct;
import com.zdv.shop.model.OtProductToObjname;

/**
 * 产品Service
 * @author LBY
 * @date: 2018年12月7日
 */
@Service
@Transactional
public class OtProductService extends AbstratService<OtProduct> {

	@Autowired
	private OtProductMapper mapper;
	@Autowired
	private OtProductToObjnameService otProductToObjnameService;
	@Autowired
	private OtCompToProductService compToProductService;

	public boolean alter(OtProduct otProduct){
		return mapper.updateByPrimaryKey(otProduct)>0;
	}
	
	public PageAjax<OtProduct> queryPage(PageAjax<OtProduct> page, OtProduct otProduct) {
		PageMethod.startPage(page.getPageNo(), page.getPageSize());
		return new PageAjax<OtProduct>(mapper.selectList(otProduct));
	}
	
	/**
	 * 新增或修改产品信息
	 * @author LBY
	 * @data 2019年1月24日
	 * @param otProduct		产品
	 * @param uobjnameids 	产品关联属性ID
	 * @return
	 * @throws Exception
	 */
	public boolean saveProduct(OtProduct otProduct, String[] uobjnameids) throws Exception {
		int rows = 0;
		String uproductid = null;
		if (otProduct.getUproductid() == null) {	// 新增
			uproductid = KeyId.nextId();
			otProduct.setUproductid(uproductid);
			rows = mapper.insert(otProduct);
		} else {									// 修改
			uproductid = otProduct.getUproductid();
			otProductToObjnameService.delByProductid(otProduct.getUproductid());	// 删除关联
			rows = mapper.updateByPrimaryKeySelective(otProduct);
		}
		
		List<OtProductToObjname> ptoList = new ArrayList<OtProductToObjname>();
		for (String uobjnameid : uobjnameids) { 
			OtProductToObjname pto = new OtProductToObjname();
			pto.setUproductid(uproductid);
			pto.setUobjnameid(uobjnameid);
			ptoList.add(pto);
		}
		if (!otProductToObjnameService.addList(ptoList)) 
			throw new Exception("批量插入失败");
		return rows > 0;
	}


}
