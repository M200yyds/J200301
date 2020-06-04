package com.hqyj.demo.modules.test.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.hqyj.demo.modules.test.entity.City;

public interface CityService {
	public List<City> getCitiesByCountryId(Integer countryId);
	public List<City> getCitiesByCountryId2(Integer countryId);
	public City getCityByName(String cityName,String localCityName);
	
	public PageInfo<City> getCitiesByPage(Integer currentPage,Integer pageSize,Integer countryId);
}
