package com.hqyj.demo.modules.test.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hqyj.demo.modules.test.entity.City;
import com.hqyj.demo.modules.test.service.CityService;

@RestController
@RequestMapping("/api")
public class CityController {
	
	@Autowired
	private CityService cityService;
	
	/**
	 * 127.0.0.1/api/citys/522(输入的id指)
	 */
	@RequestMapping("/citys/{countryId}")
	public List<City> getCitiesByCountryId(@PathVariable Integer countryId){
		return cityService.getCitiesByCountryId(countryId);
	}
	
	/**
	 * 全注解
	 */
	@RequestMapping("/citys2/{countryId}")
	public List<City> getCitiesByCountryId2(@PathVariable Integer countryId){
		return cityService.getCitiesByCountryId2(countryId);
	}
	/**
	 * 127.0.0.1/api/city?cityName=shanghai & localCityName=1111
	 */
	@RequestMapping("/city")
	public City getCityByName(@RequestParam String cityName,@RequestParam(required = false) String localCityName) {
		
		return cityService.getCityByName(cityName, localCityName);
		
	}
}
