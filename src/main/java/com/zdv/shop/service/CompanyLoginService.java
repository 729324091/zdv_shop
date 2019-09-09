package com.zdv.shop.service;

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
import com.zdv.shop.mapper.CtCompRoleMapper;
import com.zdv.shop.mapper.CtOpUserToCompMapper;
import com.zdv.shop.mapper.OpUsersMapper;
import com.zdv.shop.mapper.PtOperationMapper;
import com.zdv.shop.model.CtCompRole;
import com.zdv.shop.model.CtOpUserToComp;
import com.zdv.shop.model.OpUsers;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 登录管理业务层
 * @author administrator
 */
@Service
public class CompanyLoginService extends AbstratService<OpUsers> {
	@Autowired
	private OpUsersMapper userMapper;
	@Autowired
	private CtCompRoleMapper roleMapper;
	@Autowired
	private CtOpUserToCompMapper ctOpUserToCompMapper;
	@Autowired
	private PtOperationMapper ptOperationMapper;
	@Autowired
	private DataCache dataCache;

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
			/*OpUsers oper_user = userMapper.queryByUsername(username);*/
			OpUsers oper_user = null;
			//获取所有中间表
			/*List<CtOpUserToComp> ctOpUserToCompanies1 = ctOpUserToCompMapper.opUserToCompList(new CtOpUserToComp());*/
			List<CtOpUserToComp> ctOpUserToCompanies1 = null;
			Boolean flag = false;
			for (CtOpUserToComp ctOpUserToComp : ctOpUserToCompanies1) {
				/*if(ctOpUserToComp.getUopuserid().equals(oper_user.getUopuserid())){
					flag = true;
				}*/
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
		return AppUtil.returnObj(null);
	}

	private String getSessionId(String userName, String ip) {
		String str = userName + "_" + System.currentTimeMillis() + "_" + ip;
		try {
			return MD5Util.encrypt(str);
		} catch (Exception e) {
			return "生成token错误";
		}
	}
}
