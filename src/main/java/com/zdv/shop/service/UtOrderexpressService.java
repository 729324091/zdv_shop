package com.zdv.shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.page.PageMethod;
import com.zdv.shop.common.annotation.ServiceLog;
import com.zdv.shop.common.pojo.AjaxResult;
import com.zdv.shop.common.pojo.PageAjax;
import com.zdv.shop.common.utils.AppUtil;
import com.zdv.shop.mapper.UtOrderexpressMapper;
import com.zdv.shop.model.UtOrderexpress;

/**
 * 支付配置信息 Service 层

 */
@Service
@Transactional
public class UtOrderexpressService extends AbstratService<UtOrderexpress> {
	@Autowired
    private UtOrderexpressMapper mapper;

	public PageAjax<UtOrderexpress> queryPage(PageAjax<UtOrderexpress> page, UtOrderexpress orderexpress) {

		PageMethod.startPage(page.getPageNo(), page.getPageSize());
		List<UtOrderexpress> list = mapper.queryList(orderexpress);
		return AppUtil.returnPage(list);
	}
	
	
	
	@ServiceLog("通过Id查询数据")
	public UtOrderexpress selectByIds(String uuserid,String uorderid){
		return mapper.selectByIds(uuserid,uorderid);
	}

 
}
