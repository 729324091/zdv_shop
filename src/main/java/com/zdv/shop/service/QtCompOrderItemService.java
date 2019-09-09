package com.zdv.shop.service;

import com.github.pagehelper.page.PageMethod;
import com.zdv.shop.common.pojo.PageAjax;
import com.zdv.shop.common.utils.StringUtils;
import com.zdv.shop.mapper.QtCompOrderItemMapper;
import com.zdv.shop.model.QtCompOrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 经营下单详细
 * @author LBY
 * @data: 2019年1月7日
 */
@Service
public class QtCompOrderItemService extends AbstratService<QtCompOrderItem> {

	@Autowired
	QtCompOrderItemMapper mapper;

	@Value("${publicURL}") //在配置文件中
	private String publicurl;

	public QtCompOrderItem queryOne(QtCompOrderItem record) {
		return mapper.selectOne(record);
	}
	public int insertOrderItemList(List<QtCompOrderItem> orderItemList) {
		return mapper.insertOrderItemList(orderItemList);
	}
	
	public int inserts(QtCompOrderItem orderItem) {
		return mapper.inserts(orderItem);
	}
	
	public List<QtCompOrderItem> queryOrderItemById(String udistributorid,String keywords){
		return mapper.queryOrderItemById(udistributorid, keywords);
	}
	
	public QtCompOrderItem queryItemById(String uproductid,String uunitid,String udistributorid,String ucompid) {
		return mapper.queryItemById(uproductid, uunitid, udistributorid, ucompid);
	}
	
	public int updateByIds(QtCompOrderItem orderItem) {
		return mapper.updateByIds(orderItem);
	}
	public QtCompOrderItem queryOrderItemList(String uorderitemid,String uorderid,String uproductid) {
		return mapper.queryOrderItemList(uorderitemid, uorderid, uproductid);
	}

	public PageAjax<QtCompOrderItem> queryItemPage(PageAjax<QtCompOrderItem> page, QtCompOrderItem compOrderItem) {
		PageMethod.startPage(page.getPageNo(), page.getPageSize());
		List<QtCompOrderItem> list = mapper.queryItemPage(compOrderItem);
		for (QtCompOrderItem v : list) {
			v.setUhomepic(StringUtils.doimges(publicurl,v.getUhomepic()));
		}
		return new PageAjax<QtCompOrderItem>(list);
	}
}
