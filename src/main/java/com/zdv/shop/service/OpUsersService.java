package com.zdv.shop.service;

import com.github.pagehelper.page.PageMethod;
import com.zdv.shop.common.annotation.ServiceLog;
import com.zdv.shop.common.pojo.AjaxResult;
import com.zdv.shop.common.pojo.PageAjax;
import com.zdv.shop.common.support.DataCache;
import com.zdv.shop.common.utils.StringUtils;
import com.zdv.shop.controller.BaseController;
import com.zdv.shop.mapper.CtCompRoleMapper;
import com.zdv.shop.mapper.CtOpUserToCompMapper;
import com.zdv.shop.mapper.DtOpUserToDistributorMapper;
import com.zdv.shop.mapper.OpUsersMapper;
import com.zdv.shop.model.CtOpUserToComp;
import com.zdv.shop.model.DtOpUserToDistributor;
import com.zdv.shop.model.OpUsers;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class OpUsersService extends AbstratService<OpUsers> {

	@Autowired
	private OpUsersMapper opUsersMapper;
	@Autowired
	private CtCompRoleMapper ctCompRoleMapper;
	@Autowired
	private CtOpUserToCompMapper ctOpUserToCompMapper;
	@Autowired
	private DataCache dataCache;
	@Autowired
	private DtOpUserToDistributorMapper dtOpUserToDistributorMapper;

	@ServiceLog("添加用户")
	@Override
	public int insert(OpUsers opUsers) {
		return opUsersMapper.insert(opUsers);
	}
	
	@ServiceLog("通过手机号码查询对象")
	public OpUsers queryByMobile(String mobile) {
		return opUsersMapper.queryByUmobile(mobile);
	}
	
	@ServiceLog("通过手机号码修改密码")
	public boolean updatePasswordByMobile(OpUsers opUsers) {
		Example example = new Example(OpUsers.class);
		example.createCriteria().andEqualTo("umobile", opUsers.getUmobile());
		opUsers.setUlastmtime(BaseController.timeStamp() + "");
		return opUsersMapper.updateByExampleSelective(opUsers, example) > 0;
	}
	
	@ServiceLog("通过ID修改OpUsers")
	public boolean updateById(OpUsers opUsers) {
		return mapper.updateByPrimaryKeySelective(opUsers) > 0;
	}
	
	@ServiceLog("登录查询经销商和角色信息")
	public List<Map<String, Object>> queryDistributorRoleByOpUserId(String uopuserid) {
		return opUsersMapper.queryDistributorRoleByOpUserId(uopuserid);
	}
	/**
	 * 通过工作人员ID获得零销售与角色列表
	 * @param uopuserid
	 * @return
	 */
	@ServiceLog("通过工作人员ID获得零销售与角色列表")
	public List<Map<String, Object>> getCompanyRoleByOpUserId(String uopuserid){
		return opUsersMapper.queryCompanyRoleByOpUserId(uopuserid);
	}
	/**
	 * 查询所管理的用户
	 * @author LBY
	 * @date 2018年12月17日
	 * @param roleid
	 * @return
	 */
	@ServiceLog("根据角色Id查询所管理的用户")
	public List<Map<String, Object>> queryPossessOpUserByRoleid(Integer pageNo, Integer pageSize,
			String currentRoleid, String keywords, String roleid) {
		if (pageNo != null && pageSize != null)
			PageMethod.startPage(pageNo, pageSize);
		return opUsersMapper.queryPossessOpUserByRoleid(currentRoleid, keywords, roleid);
	}

	public List<OpUsers> queryListByDtid(String udistributorid,String uroleid) {

		return opUsersMapper.queryListByDtid(udistributorid,uroleid);
	}
	
	/**
	 * APP查询用户详情
	 * @author LBY
	 * @data 2019年1月15日
	 * @param udistributorid
	 * @param uopuserid
	 * @param uroleid
	 * @return
	 */
	public Map<String, Object> appQueryUserDetails(String udistributorid, String uopuserid, String uroleid) {
		return opUsersMapper.appQueryUserDetails(udistributorid, uopuserid, uroleid);
	}
	 //销售商下的管理员详情内容
	public Map<String, Object> appQueryCompUserDetails(String ucompid,String uopuserid, String uroleid) {
		return opUsersMapper.appQueryCompUserDetails(ucompid, uopuserid, uroleid);
	}

	@ServiceLog("绑定用户到角色")
	public AjaxResult bindUserToDTrole(String roleid, String[] ids, String udistributorid) {
		String result = null;
		try {
			for (String id : ids) {
				//传入ID清除原角色关系
				DtOpUserToDistributor opUserToDistributor = new DtOpUserToDistributor();
				opUserToDistributor.setUdistributorid(udistributorid);
				opUserToDistributor.setUopuserid(id);
				dtOpUserToDistributorMapper.delete(opUserToDistributor);
				//设置角色ID绑定关系
				opUserToDistributor.setUroleid(roleid);
				opUserToDistributor.setUstatus('0');
				opUserToDistributor.setUcreatedate(new Date().getTime()+"");
				dtOpUserToDistributorMapper.insert(opUserToDistributor);
				//清除角色ID
				opUserToDistributor.setUroleid("");
				opUserToDistributor.setUstatus(' ');
				opUserToDistributor.setUcreatedate("");
			}
		} catch (Exception e) {
			e.printStackTrace();
			result = "绑定用户时出错";
			return new AjaxResult(0,result);
		}


		return new AjaxResult(1,result);
	}

	@ServiceLog("检查登录名是否存在可用")
	public AjaxResult checkUloginnameExist(String uloginname) {

		String result = null;
		//完全匹配查询登录名
		OpUsers opUsers = opUsersMapper.queryByUsername(uloginname);
		//如果不为null
		if (!StringUtils.objectIsNull(opUsers)) {
			result = "该登录名不可用";
			return new AjaxResult(result);
		}
			return new AjaxResult(1,result);
	}
	@ServiceLog("更新用户")
	public AjaxResult updateDtUser(OpUsers opUsers, DtOpUserToDistributor opUserToDistributor) {
		String result = null;
		int code = 0;
		try {
			opUsersMapper.updateByPrimaryKey(opUsers);
			//更新中间表
			dtOpUserToDistributorMapper.updateUTD(opUserToDistributor);
			code = 1;
			result = "更新成功";
		} catch (Exception e) {
			e.printStackTrace();

		}finally {
			return new AjaxResult(code,result);
		}
	}

	@ServiceLog("查询销售商用户")
	public PageAjax<OpUsers> queryCtUserPage(PageAjax<OpUsers> page, CtOpUserToComp opUserToComp) {
		PageMethod.startPage(page.getPageNo(), page.getPageSize());
		List<OpUsers> opUsers = opUsersMapper.queryCtUserList(opUserToComp);

		return new PageAjax<OpUsers>(opUsers);
	}

	@ServiceLog("用户绑定销售商角色")
	public AjaxResult bindUserToCTrole(String roleid, String[] ids, String ucompid) {
		String result = null;
		try {
			for (String id : ids) {
				//传入ID清除原角色关系
				CtOpUserToComp opUserToComp = new CtOpUserToComp();
				opUserToComp.setUcompid(ucompid);
				opUserToComp.setUopuserid(id);
				ctOpUserToCompMapper.delete(opUserToComp);
				//设置角色ID绑定关系
				opUserToComp.setUroleid(roleid);
				opUserToComp.setUstatus('0');
				opUserToComp.setUcreatedate(new Date().getTime()+"");
				ctOpUserToCompMapper.insert(opUserToComp);
				//清除角色ID
				opUserToComp.setUroleid("");
				opUserToComp.setUstatus(' ');
				opUserToComp.setUcreatedate("");
			}
		} catch (Exception e) {
			e.printStackTrace();
			result = "绑定用户时出错";
			return new AjaxResult(0,result);
		}


		return new AjaxResult(1,result);

	}
	@ServiceLog("查询经销商的下属用户")
	public PageAjax<OpUsers> queryDtUserPage(PageAjax<OpUsers> page, DtOpUserToDistributor opUserToDistributor) {
		PageMethod.startPage(page.getPageNo(), page.getPageSize());
		List<OpUsers> opUsers = opUsersMapper.queryDtUserList(opUserToDistributor);

		return new PageAjax<OpUsers>(opUsers);
	}
	@ServiceLog("查找改代理商的根用户")
	public OpUsers findRootByUdistributorid(String udistributorid) {
		return opUsersMapper.findRootByUdistributorid(udistributorid);



	}
	@ServiceLog("查找销售商根用户")
	public OpUsers findRootByUcompid(String ucompid) {
		return opUsersMapper.findRootByUcompid(ucompid);
	}
	@ServiceLog("添加管理员用户")
	public int insertUser(OpUsers opUsers) {
		if (StringUtils.StringisNotEmpty(opUsers.getUopname())) {
			opUsers.setUopname(opUsers.getUloginname());
		}
		return opUsersMapper.insertUser(opUsers);
	}

}
