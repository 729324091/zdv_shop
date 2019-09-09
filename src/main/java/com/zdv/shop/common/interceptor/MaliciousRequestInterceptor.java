package com.zdv.shop.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.zdv.shop.common.Constant;
import com.zdv.shop.common.exception.MalciousException;
import com.zdv.shop.common.support.DataCache;
import com.zdv.shop.common.utils.IPUtil;

/**
 * 恶意请求拦截器
 * @author administrator
 */
public class MaliciousRequestInterceptor extends HandlerInterceptorAdapter {
	@Autowired
	private DataCache dataCache;
	private static final Logger logger = LoggerFactory.getLogger(MaliciousRequestInterceptor.class);
	
	private final static boolean allRequest = false; // 拦截所有请求,否则拦截相同请求
	private final static long minRequestIntervalTime = 1000; // 允许的最小请求间隔(1秒)
	private final static int maxMaliciousTimes = 100; // 允许的最大恶意请求次数(5次)

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		//启动支持@Autowired注解
		WebApplicationContextUtils.getRequiredWebApplicationContext(request.getServletContext()).getAutowireCapableBeanFactory().autowireBean(this);
		
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "POST,GET,PUT,OPTIONS,DELETE");
		response.setHeader("Access-Control-Allow-Headers", "x-requested-with,Access-Control-Allow-Origin,EX-SysAuthToken,EX-JSESSIONID");

		//同一个IP
		String requestIp = IPUtil.getIpAdd(request);
		String url = request.getServletPath();
		

		return super.preHandle(request, response, handler);
	}
}
