package com.hqyj.demo.modules.test.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.hqyj.demo.modules.common.vo.SearchVo;
import com.hqyj.demo.modules.test.entity.City;
import com.hqyj.demo.modules.common.vo.Result;

public interface CityService {
	public List<City> getCitiesByCountryId(Integer countryId);
	public List<City> getCitiesByCountryId2(Integer countryId);
	public City getCityByName(String cityName,String localCityName);
	
	public PageInfo<City> getCitiesByPage(Integer currentPage,Integer pageSize,Integer countryId);
	//封装
	public PageInfo<City> getCitiesBySearchVo(SearchVo searchVo);
	public Result<City> insertCity(City city);
}
