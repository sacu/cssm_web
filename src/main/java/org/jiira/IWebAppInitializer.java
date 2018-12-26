package org.jiira;

import javax.servlet.ServletRegistration.Dynamic;

import org.jiira.chapter14.config.WebConfig;

public class IWebAppInitializer {
	//Spring Ioc容器配置
	protected Class<?>[] getRootConfigClasses() {
		// TODO Auto-generated method stub
		//返回Spring的Java配置文件数组
		return new Class<?>[] {};
	}

	//DispatcherServlet的URI映射关系配置
	protected Class<?>[] getServletConfigClasses() {
		// TODO Auto-generated method stub
		//返回Spring的Java配置文件数组
		return new Class<?>[] {WebConfig.class};
	}

	//DispatcherServlet拦截器内容
	protected String[] getServletMappings() {
		// TODO Auto-generated method stub
		return new String[] {"*.do"};
	}
	protected void customizeRegistration(Dynamic dynamic) {
		
	}
}
