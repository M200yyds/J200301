package com.hqyj.demo.modules.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {
	
	/**
	 * 
	 * 
	 */
	@RequestMapping("/test/desc")
	@ResponseBody
	public String testDesc() {
		return "This is test modules desc";
	}
}
