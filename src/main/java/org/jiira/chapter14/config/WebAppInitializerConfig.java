package org.jiira.chapter14.config;

import org.jiira.IWebAppInitializer;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * 使用 XML配置的时候换成IWebAppInitializer
 * 使用DispatcherServlet类配置的时候换成AbstractAnnotationConfigDispatcherServletInitializer
 * 
 * IWebAppInitializer为了方便测试的时候替换，不用来回注释太多行了
 * @author time
 *
 */
public class WebAppInitializerConfig extends IWebAppInitializer {//AbstractAnnotationConfigDispatcherServletInitializer {

	//Spring Ioc容器配置
	@Override
	protected Class<?>[] getRootConfigClasses() {
		// TODO Auto-generated method stub
		//返回Spring的Java配置文件数组
		return new Class<?>[] {};
	}

	//DispatcherServlet的URI映射关系配置
	@Override
	protected Class<?>[] getServletConfigClasses() {
		// TODO Auto-generated method stub
		//返回Spring的Java配置文件数组
		return new Class<?>[] {WebConfig.class};
	}

	//DispatcherServlet拦截器内容
	@Override
	protected String[] getServletMappings() {
		// TODO Auto-generated method stub
		return new String[] {"*.do"};
	}

}
