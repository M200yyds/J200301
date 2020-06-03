package com.hqyj.demo.modules.test.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hqyj.demo.modules.test.vo.ApplicationTest;

@Controller
public class TestController {
	
	/**
	 * 读取配置信息
	 */
	@Value("${server.port}")
	private int port;
	
	@Value("${com.hqyj.name}")
	private String name;
	
	@Value("${com.hqyj.random}")
	private String random;
	
	@Value("${com.hqyj.desc}")
	private String desc;
	
	@Autowired
	private ApplicationTest applicationtest;
	
	private final static Logger LOGGER = LoggerFactory.getLogger(TestController.class);
	
	
	@RequestMapping("/test/log")
	@ResponseBody
	public String logInfo() {
		//trace<debug<info<warn<error
		LOGGER.trace("This is trace log");
		LOGGER.debug("This is debug log");
		LOGGER.info("This is info log");
		LOGGER.warn("This is warn log");
		LOGGER.error("This is error log");
		return "This is log test";
	}
	
	
	
	@RequestMapping("/test/config")
	@ResponseBody
	public String configInfo() {
		StringBuffer sb = new StringBuffer();
		sb.append(port+"*********").append(name+"*********").append(random+"*********").append(desc+"<br>");
		sb.append(applicationtest.getName()).append(applicationtest.getDesc()).append(applicationtest.getRandom()).append("<br>");
		return sb.toString();
	}
	
	
	
	/**
	 * 测试
	 * 
	 */
	@RequestMapping("/test/desc")
	@ResponseBody
	public String testDesc() {
		return "This is test modules desc";
	}
}
