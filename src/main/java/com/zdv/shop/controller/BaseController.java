package com.zdv.shop.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.zdv.shop.common.exception.AjaxLoginException;
import com.zdv.shop.common.exception.AjaxPermissionException;
import com.zdv.shop.common.exception.LoginException;
import com.zdv.shop.common.exception.MalciousException;
import com.zdv.shop.common.exception.PermissionException;
import com.zdv.shop.common.pojo.AjaxResult;
import com.zdv.shop.common.pojo.ParamData;
import com.zdv.shop.common.utils.DateUtil;
import com.zdv.shop.common.utils.IPUtil;
import com.zdv.shop.common.utils.KeyId;

/**
 * 基础Controller
 * @author administrator
 */
@Controller
public class BaseController extends KeyId {

	/**日志*/
	public static Logger logger = LoggerFactory.getLogger(BaseController.class);

	/**
	 * 失败返回
	 * @param retmsg
	 * @return
	 */
	public AjaxResult returnFailed(int retcode, String retmsg) {
		return new AjaxResult(retcode,retmsg);
	}
	
	/**
	 * ajax 成功返回
	 * LBY
	 * 2018年12月22日
	 * @return
	 */
	public AjaxResult returnSuccess() {
		return new AjaxResult(1, "操作成功");
	}
	
	/**
	 * ajax 成功返回
	 * LBY
	 * 2018年12月22日
	 * @param msg 消息
	 * @return
	 */
	public AjaxResult returnSuccess(String msg) {
		return new AjaxResult(1, msg);
	}
	
	/**
	 * ajax 成功返回
	 * LBY
	 * 2018年12月22日
	 * @param obj 成功返回对象
	 * @return
	 */
	public AjaxResult returnSuccess(Object obj) {
		return new AjaxResult(obj);
	}
	
	/**
	 * 失败返回
	 * @param retmsg
	 * @return
	 */
	public AjaxResult returnFailed(String retmsg) {
		return new AjaxResult(0, retmsg);
	}

	/**
	 * 得到request对象
	 * @return
	 */
	public static HttpServletRequest getRequest() {
		return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
	}

	/** ajax登录异常处理 **/
	@ExceptionHandler({ AjaxLoginException.class })
	@ResponseBody
	public AjaxResult ajaxLoginExceptionHandler(AjaxLoginException e) {
		logger.error("登录请求发生异常:", e);
		return new AjaxResult(e.getKey(), e.getMessage());
	}

	/** 普通登录异常处理 **/
	@ExceptionHandler({ LoginException.class })
	public String loginExceptionHandler(LoginException e, HttpServletRequest request) {
		logger.error("登录请求发生异常:", e);
		request.setAttribute("err", e.getMessage());
		return "forward:/";
	}

	/** 普通权限异常处理 **/
	@ExceptionHandler({ PermissionException.class })
	public String permissonExceptionHandler(PermissionException e) {
		return "common/no_permisson";
	}

	/** ajax权限异常处理 **/
	@ExceptionHandler({ AjaxPermissionException.class })
	@ResponseBody
	public AjaxResult ajaxPermissionExceptionHandler(AjaxPermissionException e) {
		return new AjaxResult(e.getKey(), e.getMessage());
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
		return "pages/common/500";
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
	public static String getTime(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:dd:ss");
		String time = sdf.format(new Date());
		return time;
	};
	/** 
     * 时间戳转换成日期格式字符串 
     * @param seconds 精确到秒的字符串 
     * @param formatStr 
     * @return 
     */  
    public static String timeStamp2Date(String seconds,String format) {  
        if(seconds == null || seconds.isEmpty() || seconds.equals("null")){  
            return "";  
        }  
        if(format == null || format.isEmpty()){
            format = "yyyy-MM-dd HH:mm:ss";
        }   
        SimpleDateFormat sdf = new SimpleDateFormat(format);  
        return sdf.format(new Date(Long.valueOf(seconds)));  
    }  
    /** 
     * 日期格式字符串转换成时间戳 
     * @param date 字符串日期 
     * @param format 如：yyyy-MM-dd HH:mm:ss 
     * @return 
     */  
    public static Long date2TimeStamp(String date_str,String format){  
        try {  
        	if(format==null||format.equalsIgnoreCase(""))
        		format="yyyy-MM-dd HH:mm:ss";
            SimpleDateFormat sdf = new SimpleDateFormat(format);  
            return sdf.parse(date_str).getTime();  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return 0L;  
    }  
      
    /** 
     * 取得当前时间戳（精确到秒） 
     * @return 
     */  
    public static long timeStamp(){  
        long time = System.currentTimeMillis()/1000;
        //String t = String.valueOf(time);  
        return time;  
    }  
    /**
     * 获得订单号
     */
    public static String getOrderDateNo() {
    	return DateUtil.format(new Date(), "yyyyMMddHHmmssS")+(long)((Math.random()*9+1)*100);
    }
     /**
     * 获得流水账号
     */
    public static String getGlidenumber() {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String time = sdf.format(date);
		return time + nextId();
    }

}
