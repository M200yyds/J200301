package com.hqyj.demo.modules.test.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hqyj.demo.config.ResourceConfigBean;
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
	
	@Autowired
	private ResourceConfigBean resourceConfigBean;
	
	@RequestMapping("/download")
	@ResponseBody
	public ResponseEntity<Resource> download(@RequestParam String fileName){
		
		
		try {
			String resourcePath = resourceConfigBean.getResourcePath()+ fileName;
			Resource resource = new UrlResource(ResourceUtils.getURL(resourcePath));
			//Resource resource = new UrlResource(Paths.get("D:/VNCviewer"+fileName).toUri());
			return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE,"application/octet-stream")
								.header(HttpHeaders.CONTENT_DISPOSITION,String.format("attachment;filename=%s", fileName))
								.body(resource);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return null;
		
	}
	
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
		modelmap.addAttribute("shopLogo", "/VNCviewer/3333.png");
		modelmap.addAttribute("country", country);
		modelmap.addAttribute("cities", cities);
		modelmap.addAttribute("updateCityUri", "/api/city");
		//modelmap.addAttribute("template", "test/index");
		return "index";
	}
	
	@PostMapping(value = "/file",consumes = "multipart/form-data")
	public String uploadFile(RedirectAttributes redirectAttributes, @RequestParam MultipartFile file) {
		if(file.isEmpty()) {
			redirectAttributes.addFlashAttribute("message","please select a file");
			return "redirect:/test/index";
		}
		
		String resourcePath = resourceConfigBean.getResourcePath()+ file.getOriginalFilename();
		
		try {
			//String pathname = "D:\\VNCviewer\\"+file.getOriginalFilename();
			File destFile = new File(ResourceUtils.getURL(resourcePath).getPath());
			file.transferTo(destFile);
		}catch (IllegalStateException | IOException e) {
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("message","upload failed");
			return "redirect:/test/index";
		}
		redirectAttributes.addFlashAttribute("message","upload success");
		redirectAttributes.addFlashAttribute("resourcePath", resourcePath);
		return "redirect:/test/index";
	} 
	
	@PostMapping(value = "/files",consumes = "multipart/form-data")
	public String uploadFiles(RedirectAttributes redirectAttributes, @RequestParam MultipartFile[] files) {
		boolean isEmpyt = true;
		for (MultipartFile file : files) {
			if(file.isEmpty()) {
				continue;
			}
			try {
				//String pathname = "D:/VNCviewer"+file.getOriginalFilename();
				String pathname = "D:/VNCviewer"+File.separator+file.getOriginalFilename();
				File destFile = new File(pathname);
				file.transferTo(destFile);
				isEmpyt = false;
			}catch (IllegalStateException | IOException e) {
				e.printStackTrace();
				redirectAttributes.addFlashAttribute("message","upload failed");
				return "redirect:/test/index";
			}
			if(isEmpyt) {
				redirectAttributes.addFlashAttribute("message","please select a file");
			}else {
				redirectAttributes.addFlashAttribute("message","upload success");
			}
		}
		return "redirect:/test/index";
	}
	
}
