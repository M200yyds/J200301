package com.hqyj.demo.config;

import org.apache.catalina.connector.Connector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.hqyj.demo.filter.ParameterFilter;
import com.hqyj.demo.interceptor.UrlInterceptor;

@Configuration
@AutoConfigureAfter({WebMvcAutoConfiguration.class})
public class WebMVCConfig implements WebMvcConfigurer{
	
	@Value("${server.http.port}")
	private int httpPort;
	@Autowired
	private ResourceConfigBean resourceConfigBean;
	@Autowired
	private UrlInterceptor ui;
	
	@Bean
	public Connector connector() {
		Connector connector = new Connector();
		connector.setScheme("http");
		connector.setPort(httpPort);
		return connector;
	}
	@Bean
	public ServletWebServerFactory servletWebServerFactory() {
		TomcatServletWebServerFactory factory = new TomcatServletWebServerFactory();
		factory.addAdditionalTomcatConnectors(connector());
		return factory;
	}
	
	@Bean
	public FilterRegistrationBean<ParameterFilter> filter() {
		FilterRegistrationBean<ParameterFilter> register = new FilterRegistrationBean<ParameterFilter>();
		register.setFilter(new ParameterFilter());
		return register;
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(ui).addPathPatterns("/**");
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		String sysnameString = System.getProperty("os.name");
		if(sysnameString.toLowerCase().startsWith("win")) {
			registry.addResourceHandler(resourceConfigBean.getResourcePathPattern())
				.addResourceLocations(ResourceUtils.FILE_URL_PREFIX+resourceConfigBean.getLocalPathForWindow());
		}else {
			registry.addResourceHandler(resourceConfigBean.getResourcePath())
				.addResourceLocations(ResourceUtils.FILE_URL_PREFIX+resourceConfigBean.getLocalPathForLinux());
		}
	}
	
}
