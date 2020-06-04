package com.hqyj.demo.modules.test.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.hqyj.demo.modules.test.entity.City;

@Mapper
public interface CityDao {
	
	public List<City> getCitiesByCountryId(Integer countryId);
	
	
	
	 @Select("select * from m_city where country_id=#{countryId}") 
	 public List<City> getCitiesByCountryId2(Integer countryId);
	 
	 @Select("select * from m_city where city_name=#{cityName} and local_city_name=#{localCityName}") 
	 public City getCityByName(@Param("cityName")String cityName1,String localCityName);
	 
}
