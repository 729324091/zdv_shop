package com.zdv.shop.common.conf;

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import com.zdv.shop.common.filter.XssFilter;
import com.zdv.shop.common.interceptor.H5LoginInterceptor;
import com.zdv.shop.common.interceptor.LoginRequestInterceptor;
import com.zdv.shop.common.interceptor.MaliciousRequestInterceptor;
import com.zdv.shop.common.listener.AuthorityListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;

import java.util.EventListener;

/**
 * web访问配置
 * @author administrator
 */
@Configuration
@EnableWebMvc
@ComponentScan
public class WebXmlConfig extends WebMvcConfigurerAdapter {
	@Bean
	public ViewResolver viewResolver() {
		 InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		 resolver.setPrefix("/WEB-INF/");
		 resolver.setViewNames("pages/*");
		 resolver.setSuffix(".jsp");	 
		 resolver.setOrder(2);
		 return resolver;
	 }

	@Bean
	 public ITemplateResolver templateResolver() {
		SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
		templateResolver.setTemplateMode("HTML5");
		templateResolver.setPrefix("/WEB-INF/");
		templateResolver.setSuffix(".html");
		templateResolver.setCharacterEncoding("utf-8");
		templateResolver.setCacheable(false);
		return templateResolver;
	 }


	@Bean
	public SpringTemplateEngine templateEngine() {

		SpringTemplateEngine templateEngine = new SpringTemplateEngine();
		templateEngine.setTemplateResolver(templateResolver());
		return templateEngine;

	 }


	@Bean
	public ThymeleafViewResolver viewResolverThymeLeaf(){
		ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
		viewResolver.setTemplateEngine(templateEngine());
		viewResolver.setCharacterEncoding("utf-8");
		viewResolver.setViewNames(new String[]{"WEB-INF/templates/*"});
		viewResolver.setOrder(1);
		viewResolver.setCache(false);
		return viewResolver;
	}


	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
	 configurer.enable();

	 }
	
	/**
	 * 静态资源访问  就是不受限制访问
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/assets/**").addResourceLocations("/assets/")
			.addResourceLocations("/h5/").addResourceLocations("/upload/");
		//registry.addResourceHandler("/static/**").addResourceLocations("/WEB-INF/" + "/static/");
		//registry.addResourceHandler("/assetsdev/**").addResourceLocations("/assetsdev/");
		//registry.addResourceHandler("/components/**").addResourceLocations("/components/");
		//super.addResourceHandlers(registry);
	}

	/**
	 * 拦截器
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
	
		registry.addInterceptor(new LoginRequestInterceptor()).addPathPatterns("/admin/**").excludePathPatterns("/admin/login")
				.excludePathPatterns("/admin/logout");
		registry.addInterceptor(new MaliciousRequestInterceptor()).addPathPatterns("/**");
		registry.addInterceptor(new H5LoginInterceptor()).addPathPatterns("/h5/**");
		super.addInterceptors(registry);
	}

	/**
	 * 监听器
	 * @return
	 */
	@Bean
	public ServletListenerRegistrationBean<EventListener> getDemoListener() {
		ServletListenerRegistrationBean<EventListener> registrationBean = new ServletListenerRegistrationBean<EventListener>();
		registrationBean.setListener(new AuthorityListener());
		return registrationBean;
	}

	/**
	 * 过滤器
	 * @return
	 */
	@Bean
	public FilterRegistrationBean filterRegistration() {
		FilterRegistrationBean registration = new FilterRegistrationBean(new XssFilter());
		// filter只能配置"/*","/**"无法识别
		registration.addUrlPatterns("/admin/*");
		return registration;
	}
	
	@Autowired
	private Environment env;
	
	/**
	 * 访问druid监控信息servlet
	 * @return
	 */
	@Bean
	public ServletRegistrationBean druidServletRegistration() {
		ServletRegistrationBean registration = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
		// 添加初始化参数：initParams

		// 白名单：
		registration.addInitParameter("allow", env.getProperty("druid.allow"));
		// IP黑名单 (存在共同时，deny优先于allow) : 如果满足deny的话提示:Sorry, you are not
		// permitted to view this page.
		registration.addInitParameter("deny", env.getProperty("druid.deny"));
		// 登录查看信息的账号密码.
		registration.addInitParameter("loginUsername", env.getProperty("druid.loginUsername"));
		registration.addInitParameter("loginPassword", env.getProperty("druid.loginPassword"));
		// 是否能够重置数据.
		registration.addInitParameter("resetEnable", env.getProperty("druid.resetEnable"));
		return registration;
	}

	/**
	 * 过滤druid
	 * @return
	 */
	@Bean
	public FilterRegistrationBean druidStatFilter() {
		FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new WebStatFilter());

		// 添加过滤规则.
		filterRegistrationBean.addUrlPatterns("/*");

		// 添加不需要忽略的格式信息.
		filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
		return filterRegistrationBean;
	}
	


}
