package com.zdv.shop;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.support.ErrorPageFilter;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zdv.shop.common.datasource.DynamicDataSourceRegister;

/**
 * springboot启动器
 * @author administrator
 */
@Controller
// 开启缓存
@EnableCaching
@MapperScan(basePackages = "com.zdv.*.mapper")
@Import(DynamicDataSourceRegister.class)
@SpringBootApplication
public class Application extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		//setRegisterErrorPageFilter(false);
		return application.sources(Application.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@RequestMapping("/")
	String home() {
		return "redirect:/h5/weindex";
	}



	@RequestMapping("/404")
	String notFound() {
		return "pages/common/404";
	}
	
	@RequestMapping("/500")
	String error() {
		return "pages/common/500";
	}
	@Bean    
	 public ErrorPageFilter errorPageFilter() {   
	      return new ErrorPageFilter();   
	 } 
	@Bean
	public FilterRegistrationBean disableSpringBootErrorFilter(ErrorPageFilter filter) {        
	      FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();           
	      filterRegistrationBean.setFilter(filter);      
	      filterRegistrationBean.setEnabled(false);      
	      return filterRegistrationBean;  
	}
}
