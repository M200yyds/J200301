package com.hqyj.demo.modules.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hqyj.demo.modules.test.entity.Country;
import com.hqyj.demo.modules.test.service.CountryService;

@RestController
@RequestMapping("/api")
public class CountryController {
	
	@Autowired
	private CountryService countryService;
	
	@RequestMapping("/country/{countryId}")//controller层用{} dao层用#{}
	public Country getCountryByCountryId(@PathVariable Integer countryId) {
		return countryService.getCountryByCountryId(countryId);
	}
	
	@RequestMapping("/country")
	public Country getCountryByCountryName(@RequestParam String countryName) {
		return countryService.getCountryByCountryName(countryName);
	}
}
