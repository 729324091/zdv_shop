package com.zdv.shop.common.interceptor;

import com.zdv.shop.common.Constant;
import com.zdv.shop.common.exception.AjaxLoginException;
import com.zdv.shop.common.exception.AjaxPermissionException;
import com.zdv.shop.common.exception.LoginException;
import com.zdv.shop.common.exception.PermissionException;
import com.zdv.shop.common.pojo.Identity;
import com.zdv.shop.common.support.DataCache;
import com.zdv.shop.common.utils.CookieUtil;
import com.zdv.shop.common.utils.IPUtil;
import com.zdv.shop.model.PtOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 权限拦截器
 * @author czh
 */
public class LoginRequestInterceptor extends HandlerInterceptorAdapter {

	@Autowired
	private DataCache dataCache;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		//启动支持@Autowired注解
		WebApplicationContextUtils.getRequiredWebApplicationContext(request.getServletContext()).getAutowireCapableBeanFactory().autowireBean(this);
		//权限验证结果
		boolean isOpera = true;
		String url = request.getServletPath();
		//System.out.println("====="+url);
		if(url.contains("html5/")||url.contains("/comp")||url.contains("/exam")||url.contains("/distributor")) {//跳过权限控制
			return super.preHandle(request, response, handler);
		}
		//登录信息验证结果
		String loginResult = validateLogin(request, response);

		String requestType = request.getHeader("X-Requested-With");
		String accept = request.getHeader("Accept");
		//ajax请求
		if (requestType != null && "XMLHttpRequest".equals(requestType) && accept.contains("application/json")) {
			if(StringUtils.isNotEmpty(loginResult)){
				if (StringUtils.isNotEmpty(loginResult)) {
					throw new AjaxLoginException(401, loginResult);
				}
			}
			isOpera = validateOperation(request);
			// 校验权限 true有权限， false 没有权限
			if(!isOpera){
				throw new AjaxPermissionException(402, "您没有此操作权限");
			}
		}
		
		if (StringUtils.isNotEmpty(loginResult)) {
			throw new LoginException(401, loginResult);
		}
		isOpera = validateOperation(request);
		//控制没有权限的操作

		if(!isOpera){
			throw new PermissionException(402, "您没有此操作权限");
		}
		return super.preHandle(request, response, handler);
	}

	private String validateLogin(HttpServletRequest request, HttpServletResponse response) {
		String sessionId = CookieUtil.get(Constant.SESSION_IDENTITY_KEY, request);
		if (StringUtils.isEmpty(sessionId)) {
			return "您还没有登陆，请登陆";
		}


		return null;
	}



	private boolean validateOperation(HttpServletRequest request) {
		String sessionId = CookieUtil.get(Constant.SESSION_IDENTITY_KEY, request);
		String username = (String) dataCache.getValue(sessionId);
		Identity identity = (Identity) dataCache.getValue(username + IPUtil.getIpAdd(request));
		List<PtOperation> list = identity.getOperationList();
		boolean isOper = false;
		String url = request.getServletPath();
		String href = null;
		if(url.contains("html5/")||url.contains("/comp")||url.contains("/admin")||url.contains("/exam")||url.contains("/distributor")||url.contains("/order")) {
			return true;
		}
		//动态url过滤,如update/{id}
		String dyUrl = url.substring(url.lastIndexOf("/") + 1);
		if(StringUtils.isNumeric(dyUrl)||dyUrl.length()>30){
			url = url.substring(0, url.lastIndexOf("/"));
		}

		for (PtOperation oper : list) {
			href = oper.getUophref();
			if((StringUtils.isNumeric(dyUrl) ||dyUrl.length()>30)&& href.contains("{")){
				href = href.substring(0, href.lastIndexOf("/"));
			}

			if(url.equals(href)){
				isOper = true;
				break;
			}
		}
		return isOper;
	}



	// 校验权限 true有权限， false 没有权限
	/*private boolean validateOperation(HttpServletRequest request) {
		String sessionId = CookieUtil.get(Constant.SESSION_IDENTITY_KEY, request);
		String username = (String) dataCache.getValue(sessionId);
		Identity identity = (Identity) dataCache.getValue(username + IPUtil.getIpAdd(request));
		List<AuthOperation> list = identity.getOperationList();
		boolean isOper = false;
		String url = request.getServletPath();
		String href = null;
		if(url.contains("html5/")) {
			return true;
		}
		//动态url过滤,如update/{id}
		String dyUrl = url.substring(url.lastIndexOf("/") + 1);
		if(StringUtils.isNumeric(dyUrl)||dyUrl.length()>30){
			url = url.substring(0, url.lastIndexOf("/"));
		}

		for (AuthOperation oper : list) {
			href = oper.getOphref();
			if((StringUtils.isNumeric(dyUrl) ||dyUrl.length()>30)&& href.contains("{")){
				href = href.substring(0, href.lastIndexOf("/"));
			}	
			
			if(url.equals(href)){
				isOper = true;
				break;
			}
		}
		return isOper;
	}*/

}
