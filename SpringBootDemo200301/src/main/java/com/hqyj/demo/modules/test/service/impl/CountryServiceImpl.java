package com.hqyj.demo.modules.test.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hqyj.demo.modules.test.dao.CountryDao;
import com.hqyj.demo.modules.test.entity.Country;
import com.hqyj.demo.modules.test.service.CountryService;

@Service
public class CountryServiceImpl implements CountryService {
	
	@Autowired
	private CountryDao countryDao;
	@Override
	public Country getCountryByCountryId(Integer countryId) {
		
		return countryDao.getCountryByCountryId(countryId);
	}
	@Override
	public Country getCountryByCountryName(String countryName) {
		
		return countryDao.getCountryByCountryName(countryName);
	}
	
}
