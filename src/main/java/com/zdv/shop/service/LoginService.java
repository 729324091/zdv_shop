package com.zdv.shop.service;

import com.alibaba.fastjson.JSONArray;
import com.zdv.shop.common.Constant;
import com.zdv.shop.common.annotation.ServiceLog;
import com.zdv.shop.common.pojo.AjaxResult;
import com.zdv.shop.common.pojo.Identity;
import com.zdv.shop.common.pojo.ParamData;
import com.zdv.shop.common.support.DataCache;
import com.zdv.shop.common.utils.AppUtil;
import com.zdv.shop.common.utils.CookieUtil;
import com.zdv.shop.common.utils.IPUtil;
import com.zdv.shop.common.utils.MD5Util;
import com.zdv.shop.mapper.*;
import com.zdv.shop.model.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 登录管理业务层
 * @author administrator
 */
@Service
public class LoginService extends AbstratService<OpUsers> {
	@Autowired
	private OpUsersMapper userMapper;
	@Autowired
	private AuthRoleMapper roleMapper;
	@Autowired
	private DataCache dataCache;

	@Autowired
	private DtDistributorMapper distributorMapper;
	@Autowired
	private CtCompMapper companyMapper;
	@Autowired
	private CtOpUserToCompMapper opUserToCompMapper;
	@Autowired
	private DtOpUserToDistributorMapper opUserToDistributorMapper;

	@Autowired
	private PtOperationMapper operationMapper;


	@ServiceLog("登录")
	public AjaxResult login(HttpServletRequest request, HttpServletResponse response) {
		String verifyCode = (String) request.getSession().getAttribute(Constant.VERIFY_CODE);
		String result = null;
		ParamData params = new ParamData();
		String vcode = params.getString("vcode");
		if (params.containsKey("vcode") && (StringUtils.isEmpty(verifyCode) || !verifyCode.equalsIgnoreCase(vcode))) {
			result = "验证码错误";
		}else{
			String username = params.getString("username");
			String loginIp = params.getString("loginIp");
			String key = username + loginIp + Constant.LOGIN_ERROR_TIMES;
			OpUsers opUsers = userMapper.queryByUsername(username);

			//获取用户关联的经销商集合
			List<DtDistributor> distributorList = distributorMapper.queryDistributorListByUopuserid(opUsers.getUopuserid());
			//获取用户关联的销售商集合
			List<CtComp> companyList = companyMapper.queryCompanyListByUopuserid(opUsers.getUopuserid());
			//System.out.println(opUsers.getUpassword()+"||");
			if (opUsers == null || !opUsers.getUpassword().equalsIgnoreCase((params.getString("password")).toUpperCase())) {
			
	
			}else{
				// 更新登录IP和登录时间
//				oper_user.setLoginip(loginIp);
				opUsers.setUlastdate(System.currentTimeMillis()/1000+"");
				userMapper.updateByPrimaryKeySelective(opUsers);
				
				Identity identity = new Identity();
				//AuthRole role = roleMapper.queryRoleById(oper_user.getRoleid());
				//如果只有一个经销商或销售商
				if(companyList.size()==1&& distributorList.size()==0){

					String ucompid = companyList.get(0).getUcompid();
					//查询出所有权限
					CtOpUserToComp opUserToComp = new CtOpUserToComp();
					opUserToComp.setUcompid(ucompid);
					opUserToComp.setUopuserid(opUsers.getUopuserid());
					List<PtOperation> ptOperations = operationMapper.queryByUTC(opUserToComp);
					//设置权限
					identity.setOperationList(ptOperations);
					identity.setUcompid(ucompid);
					identity.setUdistributorid(null);
				} else if (distributorList.size() == 1 && companyList.size() == 0) {
					String udistributorid = distributorList.get(0).getUdistributorid();
					//查询改用户拥有的权限
					DtOpUserToDistributor opUserToDistributor = new DtOpUserToDistributor();
					opUserToDistributor.setUdistributorid(udistributorid);
					opUserToDistributor.setUopuserid(opUsers.getUopuserid());
					List<PtOperation> ptOperations = operationMapper.queryByUTD(opUserToDistributor);
					//设置权限
					identity.setOperationList(ptOperations);
					identity.setUdistributorid(udistributorid);
					identity.setUcompid(null);
				}
				//identity.setOperationList(role.getOperations());
				identity.setLoginUser(opUsers);
				identity.setLoginIp(loginIp);
				String sessionId = getSessionId(username, identity.getLoginIp());
				identity.setSessionId(sessionId);
				dataCache.setValue(username + loginIp, identity);
				dataCache.setValue(sessionId, username);
				dataCache.remove(key);
				CookieUtil.set(Constant.SESSION_IDENTITY_KEY, sessionId, response);
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("dt", distributorList);
				map.put("ct", companyList);
				JSONArray array = new JSONArray();
				array.add(distributorList);
				array.add(companyList);
				//经销商或销售商只有一个且不为空
				if (StringUtils.isNotEmpty(identity.getUcompid())||StringUtils.isNotEmpty(identity.getUdistributorid())) {
					//如果retcode为2则直接跳转
					return new AjaxResult(2,result);
				}

				return AppUtil.returnObj(result,opUsers);
			}
		}
		return AppUtil.returnObj(result);
	}

	@ServiceLog("退出")
	public AjaxResult logout(HttpServletResponse response, HttpServletRequest request) {
		String sessionId = CookieUtil.get(Constant.SESSION_IDENTITY_KEY, request);

		if (StringUtils.isNotEmpty(sessionId)) {
			dataCache.remove(sessionId);
			String userName = (String) dataCache.getValue(sessionId);
			if (StringUtils.isNotEmpty(userName)) {
				dataCache.remove(userName + IPUtil.getIpAdd(request));
			}
			CookieUtil.delete(Constant.SESSION_IDENTITY_KEY, request, response);
		}
		return new AjaxResult(1,"注销成功");
	}

	private String getSessionId(String userName, String ip) {
		String str = userName + "_" + System.currentTimeMillis() + "_" + ip;
		try {
			return MD5Util.encrypt(str);
		} catch (Exception e) {
			return "生成token错误";
		}
	}

	@ServiceLog("选择销售商登陆")
	public Boolean companyLogin(HttpServletRequest request, CtOpUserToComp opUserToComp) {
		try {

			OpUsers opUsers = userMapper.selectByPrimaryKey(opUserToComp.getUopuserid());

			//获取用户名
			String username = opUsers.getUloginname();

			opUserToComp = opUserToCompMapper.selectOne(opUserToComp);

			List<PtOperation> ptOperations = operationMapper.queryByUTC(opUserToComp);
			ParamData params = new ParamData();

			String loginIp = params.getString("loginIp");


			Identity identity = (Identity) dataCache.getValue(username + IPUtil.getIpAdd(request));

			identity.setOperationList(ptOperations);
			identity.setUcompid(opUserToComp.getUcompid());
			identity.setUdistributorid(null);
			identity.setLoginIp(IPUtil.getIpAdd(request));
			identity.setLoginUser(opUsers);
			identity.setLoginIp(loginIp);
			String sessionId = identity.getSessionId();
//			String sessionId = getSessionId(username, identity.getLoginIp());
//			identity.setSessionId(sessionId);
			dataCache.setValue(username + loginIp, identity);
			dataCache.setValue(sessionId, username);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}

	@ServiceLog("选择经销商登陆")
	public Boolean distributorLogin(HttpServletRequest request, DtOpUserToDistributor opUserToDistributor) {
		try {

			OpUsers opUsers = userMapper.selectByPrimaryKey(opUserToDistributor.getUopuserid());

			//获取用户名
			String username = opUsers.getUloginname();

			opUserToDistributor = opUserToDistributorMapper.selectOne(opUserToDistributor);

			if (opUserToDistributor == null) {
				return false;
			}


			List<PtOperation> ptOperations = operationMapper.queryByUTD(opUserToDistributor);
			ParamData params = new ParamData();

			String loginIp = params.getString("loginIp");


			Identity identity = (Identity) dataCache.getValue(username + IPUtil.getIpAdd(request));

			//test
			List<PtOperation> ptOperations1 = operationMapper.queryAllOpers();


			identity.setOperationList(ptOperations1);
			identity.setUcompid(null);
			identity.setUdistributorid(opUserToDistributor.getUdistributorid());
			identity.setLoginIp(IPUtil.getIpAdd(request));
			identity.setLoginUser(opUsers);
			identity.setLoginIp(loginIp);
			String sessionId = identity.getSessionId();
			/*String sessionId = getSessionId(username, identity.getLoginIp());
			identity.setSessionId(sessionId);*/
			dataCache.setValue(username + loginIp, identity);
			dataCache.setValue(sessionId, username);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
