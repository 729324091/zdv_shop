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
import com.zdv.shop.mapper.UtExpresscompMapper;
import com.zdv.shop.model.UtExpresscomp;

/**
 * 快递公司编码信息 Service 层

 */
@Service
@Transactional
public class UtExpresscompService extends AbstratService<UtExpresscomp> {
	@Autowired
    private UtExpresscompMapper mapper;

	public PageAjax<UtExpresscomp> queryPage(PageAjax<UtExpresscomp> page, UtExpresscomp expresscomp) {

		PageMethod.startPage(page.getPageNo(), page.getPageSize());
		List<UtExpresscomp> list = mapper.queryList(expresscomp);
		return AppUtil.returnPage(list);
	}
	
	
	
	@ServiceLog("通过Id查询数据")
	public UtExpresscomp selectByIds(String expresscompid){
		return mapper.selectByIds(expresscompid);
	}

 
}
