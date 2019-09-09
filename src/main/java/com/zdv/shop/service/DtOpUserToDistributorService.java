package com.zdv.shop.service;

import java.util.List;
import java.util.Map;

import com.zdv.shop.common.annotation.ServiceLog;
import com.zdv.shop.common.pojo.AjaxResult;
import com.zdv.shop.mapper.OpUsersMapper;
import com.zdv.shop.model.OpUsers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.page.PageMethod;
import com.zdv.shop.mapper.DtOpUserToDistributorMapper;
import com.zdv.shop.model.DtOpUserToDistributor;
import com.zdv.shop.model.vo.AppQueryOpuserVO;

/**
 * 管理员与经销商（供应商）中间表Service
 * @author LBY
 * @date: 2018年12月14日
 */
@Service
public class DtOpUserToDistributorService extends AbstratService<DtOpUserToDistributor> {

	@Autowired
	private DtOpUserToDistributorMapper mapper;
	@Autowired
	private OpUsersMapper usersMapper;
	
	public boolean del(DtOpUserToDistributor record) {
		return mapper.delete(record) > 0;
	}
	
	/**
	 * APP查询经销商管理人员
	 * @author LBY
	 * @data 2019年1月15日
	 * @param udistributorid
	 * @param uroleid
	 * @return
	 */
	public List<Map<String, Object>> appQueryPossessUsers(AppQueryOpuserVO argVo) {
		if (argVo.getPageNo() != null && argVo.getPageSize() != null)
			PageMethod.startPage(argVo.getPageNo(), argVo.getPageSize(), false);
		return mapper.appQueryPossessUsers(argVo);
	}

	@ServiceLog("绑定管理员")
	public AjaxResult bindUser(OpUsers users, DtOpUserToDistributor opUserToDistributor) {
		String result = null;
		try {
			OpUsers opUsers = usersMapper.queryByUsername(users.getUloginname());
			if (opUsers != null || opUsers.getUpassword().equalsIgnoreCase(users.getUpassword())) {
				opUserToDistributor.setUopuserid(opUsers.getUopuserid());
				opUserToDistributor.setUstatus('0');
				boolean b = mapper.insert(opUserToDistributor) > 0;
				if (b) {
					result = "绑定成功";
					return new AjaxResult(1, result);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new AjaxResult();

	}
	//商家销售产品中对应的经销商
	public  List<Map<String,Object>> opuserDistributorBycomproductid(String ucomproductid){
		return mapper.opuserDistributorBycomproductid(ucomproductid);
	}
	@ServiceLog("获得经销商下所有管理员ID")
	public List<Map<String, Object>> opuserDistributorBydistributorid(String udistributorid) {
		return mapper.opuserDistributorBydistributorid(udistributorid);
	}
}
