package com.zdv.shop.service;

import com.github.pagehelper.page.PageMethod;
import com.zdv.shop.common.annotation.ServiceLog;
import com.zdv.shop.common.pojo.AjaxResult;
import com.zdv.shop.common.pojo.PageAjax;
import com.zdv.shop.common.utils.AppUtil;
import com.zdv.shop.mapper.UtUsercashoutMapper;
import com.zdv.shop.model.UtUsercashout;
import com.zdv.shop.model.UtUsers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;


/**
 * 支付配置信息 Service 层

 */
@Service
@Transactional
public class UtUsercashoutService extends AbstratService<UtUsercashout> {
	@Autowired
    private UtUsercashoutMapper mapper;

	@Autowired
	private UtUserService userService;

	public PageAjax<UtUsercashout> queryPage(PageAjax<UtUsercashout> page, UtUsercashout usercashout) {

		PageMethod.startPage(page.getPageNo(), page.getPageSize());
		List<UtUsercashout> list = mapper.queryList(usercashout);
		return AppUtil.returnPage(list);
	}
	
	
	
	@ServiceLog("通过Id查询数据")
	public UtUsercashout selectByIds(String uwxpayid,String ucompid,String ucustomerid){
		return mapper.selectByIds(uwxpayid,ucompid,ucustomerid);
	}

	@ServiceLog("创建")
	public AjaxResult inserts(UtUsercashout usercashout) {
		String result = "创建成功";
		boolean a = mapper.inserts(usercashout) > 0;

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
	public AjaxResult updateById(UtUsercashout usercashout) {
		int ret = mapper.updateByIds(usercashout);
	
        String result = null;
        if(ret <= 0){
        	result = "删除失败";
        }
    	return AppUtil.returnObj(result);
	}

	public AjaxResult addUserCashout(UtUsercashout usercashout, UtUsers utUsers) {
		String result = "申请提现成功,请等待审核";
		boolean a = mapper.inserts(usercashout) > 0;
		boolean b = userService.updateByUuserid(utUsers) > 0;

		if (!a || !b) {
			result = "创建失败";
		}

		return new AjaxResult(1, result);

	}

	public List<Map<String, Object>> ListByUserid(String uuserid, Integer pageNo, Integer pageSize) {

		return mapper.ListByUserid(uuserid, pageNo, pageSize);
	}
}
