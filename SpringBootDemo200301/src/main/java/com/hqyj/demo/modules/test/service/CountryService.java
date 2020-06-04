package com.hqyj.demo.modules.test.service;

import com.hqyj.demo.modules.test.entity.Country;

public interface CountryService {
	
	public Country getCountryByCountryId(Integer countryId);
	public Country getCountryByCountryName(String countryName);
}
