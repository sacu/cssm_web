package org.jiira.chapter14.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

//使用DispatcherServlet类配置方式，需要以下注解
//@Configuration//定义为配置类
//@ComponentScan("org.jiira.chapter14.*")//定义扫描范围
//@EnableWebMvc//启用 Spring Web MVC

public class WebConfig {
	/**
	 * 通过类配置视图解析器
	 * 也可以查看对应的xml配置
	 * @return
	 */
	@Bean(name="viewResolver")
	public ViewResolver initViewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/jsp/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}
}
