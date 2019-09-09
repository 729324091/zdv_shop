package com.zdv.shop.service;

import com.github.pagehelper.page.PageMethod;
import com.zdv.shop.common.annotation.ServiceLog;
import com.zdv.shop.common.pojo.AjaxResult;
import com.zdv.shop.common.pojo.PageAjax;
import com.zdv.shop.common.utils.AppUtil;
import com.zdv.shop.mapper.CtWxpayConfigMapper;
import com.zdv.shop.model.CtWxpayConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 支付配置信息 Service 层

 */
@Service
@Transactional
public class CtWxpayConfigService extends AbstratService<CtWxpayConfig> {
	@Autowired
    private CtWxpayConfigMapper mapper;

	public PageAjax<CtWxpayConfig> queryPage(PageAjax<CtWxpayConfig> page, CtWxpayConfig wxpayConfig) {

		PageMethod.startPage(page.getPageNo(), page.getPageSize());
		List<CtWxpayConfig> list = mapper.queryList(wxpayConfig);
		return AppUtil.returnPage(list);
	}
	
	
	
	@ServiceLog("通过Id查询数据")
	public CtWxpayConfig selectByIds(String uwxpayid,String ucompid,String ucustomerid){
		return mapper.selectByIds(uwxpayid,ucompid,ucustomerid);
	}

	@ServiceLog("创建")
	public AjaxResult inserts(CtWxpayConfig wxpayConfig) {
		String result = "创建成功";
		boolean a = mapper.inserts(wxpayConfig) > 0;

		if(!a){
			result = "创建失败";
		}

		return new AjaxResult(1, result);


	}


	@ServiceLog("删除")
	public AjaxResult deleteByIds(String uwxpayid) {
		String result = null;
		try {
			mapper.deleteByIds(uwxpayid);
			result = "操作成功";
		}catch (Exception e){
			e.printStackTrace();
			result = "删除出错";
			return new AjaxResult(result);
		}
		return new AjaxResult(1, result);
		//int i = mapper.delAllById(udistributorid);
	}

	/**
	 * 根据ID修改信息
	 * @return
	 */
	public AjaxResult updateById(CtWxpayConfig wxpayConfig) {
		int ret = mapper.updateByIds(wxpayConfig);
	
        String result = null;
        if(ret <= 0){
        	result = "删除失败";
        }
    	return AppUtil.returnObj(result);
	}
 
}
