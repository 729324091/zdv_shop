package com.zdv.shop.controller;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.zdv.shop.common.exception.MalciousException;
import com.zdv.shop.common.pojo.AjaxResult;
import com.zdv.shop.common.pojo.ParamData;
import com.zdv.shop.common.utils.IPUtil;
import com.zdv.shop.common.utils.KeyId;

/**
 * 基础AppController
 * @author administrator
 */
@Controller
public class BaseAppController extends KeyId {

	/**日志*/
	public static Logger logger = LoggerFactory.getLogger(BaseAppController.class);

	/**
	 * 失败返回
	 * @param retmsg
	 * @return
	 */
	public AjaxResult returnFailed(String retmsg) {
		return new AjaxResult(retmsg);
	}

	/**
	 * 得到request对象
	 * @return
	 */
	public static HttpServletRequest getRequest() {
		return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
	}

	/** 频繁请求异常处理 **/
	@ExceptionHandler({ MalciousException.class })
	public String malExceptionHandler(MalciousException e) {
		return "common/mal_request";
	}

	/** 公共异常处理 **/
	@ExceptionHandler({ Exception.class })
	public Object exceptionHandler(Exception e, HttpServletRequest request) {
		ParamData params = new ParamData();
		logger.info("");
		StringBuilder sb = new StringBuilder(params.getString("loginIp")).append(request.getRequestURI()).append("请求发生异常:")
				.append(request.getServletPath()).append(":").append(params);
		logger.error(sb.toString(), e);
		return "common/500";
	}

	public void logBefore(String desc) {
		HttpServletRequest request = getRequest();
		logger.error("");
		StringBuilder sb = new StringBuilder(IPUtil.getIpAdd(request)).append(desc).append(":").append(request.getServletPath());
		logger.error(sb.toString());
	}
	public static String getUuid(){   
		return UUID.randomUUID().toString().trim().replaceAll("-", "");   
    } 
}
