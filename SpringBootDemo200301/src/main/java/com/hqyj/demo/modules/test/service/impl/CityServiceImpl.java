package com.hqyj.demo.modules.test.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hqyj.demo.modules.test.dao.CityDao;
import com.hqyj.demo.modules.test.entity.City;
import com.hqyj.demo.modules.test.service.CityService;

@Service
public class CityServiceImpl implements CityService{
	
	@Autowired
	private CityDao cityDao;
	
	public List<City> getCitiesByCountryId(Integer countryId) {
		return Optional.ofNullable(cityDao.getCitiesByCountryId(countryId))
						.orElse(Collections.emptyList());
	}
	
	
	public City getCityByName(String cityName, String localCityName) {
		return cityDao.getCityByName(cityName, localCityName);
	}

	@Override
	public List<City> getCitiesByCountryId2(Integer countryId) {
		return Optional.ofNullable(cityDao.getCitiesByCountryId2(countryId))
				.orElse(Collections.emptyList());
	}
	
	
	/*分页*/
	@Override
	public PageInfo<City> getCitiesByPage(Integer currentPage, Integer pageSize,Integer countryId) {
		PageHelper.startPage(currentPage, pageSize);
		return new PageInfo<City>(Optional.ofNullable(cityDao.getCitiesByCountryId2(countryId))
				.orElse(Collections.emptyList()));
	}



}
