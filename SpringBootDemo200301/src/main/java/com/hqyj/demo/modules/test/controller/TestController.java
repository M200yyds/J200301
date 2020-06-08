package com.hqyj.demo.modules.test.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hqyj.demo.modules.test.entity.City;
import com.hqyj.demo.modules.test.entity.Country;
import com.hqyj.demo.modules.test.service.CityService;
import com.hqyj.demo.modules.test.service.CountryService;
import com.hqyj.demo.modules.test.vo.ApplicationTest;

@Controller
@RequestMapping("/test")
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
	
	@Autowired
	private CityService cityService;
	@Autowired
	private CountryService countryService;
	
	private final static Logger LOGGER = LoggerFactory.getLogger(TestController.class);
	
	
	@RequestMapping("/log")
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
	
	
	
	@RequestMapping("/config")
	@ResponseBody
	public String configInfo() {
		StringBuffer sb = new StringBuffer();
		sb.append(port+"*********").append(name+"*********").append(random+"*********").append(desc+"<br>");
		sb.append(applicationtest.getName()).append(applicationtest.getDesc()).append(applicationtest.getRandom()).append("<br>");
		return sb.toString();
	}
	/**
	 * 127.0.0.1/test/desc?key=qsqsqs
	 */
	@RequestMapping("/desc")
	@ResponseBody
	public String testDesc(@RequestParam String key,HttpServletRequest req) {
		String key2 = req.getParameter("key");
		
		return "This is testdesc"+key+"=="+key2;
		
	}
	
	@RequestMapping("/index")
	public String indexPage(ModelMap modelmap) {		
		Integer countryId = 522;
		List<City> cities = cityService.getCitiesByCountryId2(countryId);
		cities = cities.stream().limit(10).collect(Collectors.toList());
		Country country = countryService.getCountryByCountryId(countryId);

		modelmap.addAttribute("thymeleafTitle", "scdscsadcsacd");
		modelmap.addAttribute("checked", true);
		modelmap.addAttribute("currentNumber", 99);
		modelmap.addAttribute("changeType", "checkbox");
		modelmap.addAttribute("baiduUrl", "/test/log");
		modelmap.addAttribute("city", cities.get(0));
		modelmap.addAttribute("shopLogo", 
				"http://cdn.duitang.com/uploads/item/201308/13/20130813115619_EJCWm.thumb.700_0.jpeg");
		modelmap.addAttribute("country", country);
		modelmap.addAttribute("cities", cities);
		modelmap.addAttribute("updateCityUri", "/api/city");
		//modelmap.addAttribute("template", "test/index");
		return "index";
	}
}
