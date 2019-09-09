package com.zdv.shop.common.interceptor;

import com.zdv.shop.common.Constant;
import com.zdv.shop.model.UtUsers;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * 手机端H5页面登录拦截
 * @author LBY
 * @data 2019年2月28日 
 */
public class H5LoginInterceptor extends HandlerInterceptorAdapter {

	/**
	 * （未登录）Ajax访问拦截uri
	 * 个人中心、购物车、收货地址
	 */
	private static final String[] AJAX_INTERCEPT_URI = {"personal_center", "cart", "takeAddress","order","grade","cashout","history"};
	
	/**
	 * （未登录）页面访问拦截uri
	 * 个人中心、购物车、收货地址
	 */
	private static final String[] PAGE_INTERCEPT_URI = {"personal_center", "cart", "takeAddress","order","grade","share","wallet","userinfo","thridbind","cashout","history"};

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String requestUri = request.getRequestURI();
		if (requestUri.contains("/h5/")) {
			UtUsers user = (UtUsers) request.getSession().getAttribute(Constant.SESSION_H5USER);
			if (user == null) {
				if (request.getHeader("x-requested-with") != null
						&& request.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest")) {	// Ajax 请求访问
					if (!release(requestUri, AJAX_INTERCEPT_URI))
						return true;
						String ua = ((HttpServletRequest) request).getHeader("user-agent").toLowerCase();
						Enumeration<String> enu = request.getParameterNames();
					List<String> keylist = new ArrayList<String>();
					while(enu.hasMoreElements()){
						String paraName=(String)enu.nextElement();
						keylist.add(paraName);
						System.out.println(paraName+": "+request.getParameter(paraName));
					}
					if (keylist.size() > 0) {
						for (int i = 0; i < keylist.size(); i++) {
							if (i == 0) {
								requestUri += "?" + keylist.get(i) + "=" + request.getParameter(keylist.get(i));
							}else{
								requestUri += "&" + keylist.get(i) + "=" + request.getParameter(keylist.get(i));
							}

						}

					}
					String url = "/h5/home";
					HttpSession session = request.getSession();

					session.setAttribute("url&param", requestUri);
//
					System.out.println(url);
					response.setHeader("sessionstatus", "timeout");

//						System.out.println(ucomproductid+"*******"+uproductnum+"*****"+uobjnamevalue+"*****"+tocart);
					/*if (ua.indexOf("micromessenger") > 0) {
//						request.getRequestDispatcher(url).forward(request, response);
						response.sendRedirect(url);
					}else{
						response.sendRedirect("/h5/user/login");
					}*/
//					response.sendRedirect("/h5/user/login"); //跳转登陆

//					response.setHeader("url",requestUri);
//					response.sendRedirect("/h5/user/login");
//					response.setStatus(403);	// 服务器已经理解请求，但是拒绝执行它
				} else {	// 其他请求
					if (!release(requestUri, PAGE_INTERCEPT_URI))
						return true;
					response.sendRedirect("/h5/user/login?url="+requestUri);
					/*ServletOutputStream out=response.getOutputStream();
					OutputStreamWriter ow=new OutputStreamWriter(out,"GB2312");
					ow.write("请上传EXCEL 2003 的模板后，重试");
					ow.flush();
					ow.close();*/
				}
				return false;
			}
		}
		return true;
	}
	
	/**
	 * 是否放行
	 * @author LBY
	 * @data 2019年2月28日
	 * @param requestUri 请求的uri
	 * @param releaseUri 拦截路径
	 * @return
	 */
	private boolean release(String requestUri, String[] releaseUri) {
		if (requestUri != null && releaseUri != null && releaseUri.length > 0) {
			String[] uriGroup = requestUri.split("/");
			if (uriGroup.length > 0) {
				for (String rUri : releaseUri) {
					for (String u : uriGroup) {
						if (rUri.equals(u))
							return true;
					}
				}
			}
		}
		return false;
	}
}
