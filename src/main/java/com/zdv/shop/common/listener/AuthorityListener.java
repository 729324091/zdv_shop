package com.zdv.shop.common.listener;

import com.zdv.shop.service.OpOperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * 权限监听器
 * @author administrator
 */
//@WebListener(与ServletComponentScan注解一起使用;现在用另一种方式,交由WebConfig统一管理配置)
public class AuthorityListener implements ServletContextListener {
	@Autowired  
    ApplicationContext context;
	
	@Override
	public void contextInitialized(ServletContextEvent event) {
		//加载自动注入Autowired
		WebApplicationContextUtils.getRequiredWebApplicationContext(event.getServletContext()).getAutowireCapableBeanFactory().autowireBean(this);
//		OperationService operationService = context.getBean(OperationService.class);
		OpOperationService opOperationService = context.getBean(OpOperationService.class);
//		operationService.initAuthority();
		opOperationService.initAuthority();
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("销毁监听器.....");
	}
}
