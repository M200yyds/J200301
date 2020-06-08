package com.hqyj.demo.filter;

import java.io.IOException;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebFilter(filterName = "ParameterFilter",urlPatterns = "/**")
public class ParameterFilter implements Filter{
	
	private final static Logger LOGGER = LoggerFactory.getLogger(ParameterFilter.class);
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		LOGGER.debug("init ParameterFilter-----------");
	}
	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		LOGGER.debug("doFilter ParameterFilter-----------");
		HttpServletRequest hreq = (HttpServletRequest) req;
		//Map<String, String[]> maps = hreq.getParameterMap();是锁住的，无法随意修改
		
		HttpServletRequestWrapper wrapper = new HttpServletRequestWrapper(hreq) {
			@Override
			public String getParameter(String name) {
				String value = hreq.getParameter(name);
				if(StringUtils.isNotBlank(value) && value.contains("qsqsqs")) {
					return value.replace("qsqsqs", "******");
				}
				return super.getParameter(name);
			}
			
			@Override
			public String[] getParameterValues(String name) {
				String[] values = hreq.getParameterValues(name);
				for (int i = 0;i < values.length;i++) {
					String temp = values[i];
					if(StringUtils.isNotBlank(temp) && temp.contains("qsqsqs")) {
						values[i] = temp.replaceAll("qsqsqs", "******");
					}
				}
				return super.getParameterValues(name);
			}
			
		};
		
		
		chain.doFilter(wrapper, resp);
		
	}
	@Override
	public void destroy() {
		LOGGER.debug("destroy ParameterFilter-----------");
	}
}
