package com.hqyj.demo.modules.test.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.hqyj.demo.modules.common.vo.SearchVo;
import com.hqyj.demo.modules.test.entity.City;

@Mapper
public interface CityDao {
	
	public List<City> getCitiesByCountryId(Integer countryId);
	
	
	
	 @Select("select * from m_city where country_id=#{countryId}") 
	 public List<City> getCitiesByCountryId2(Integer countryId);
	 
	 @Select("select * from m_city where city_name=#{cityName} and local_city_name=#{localCityName}") 
	 public City getCityByName(@Param("cityName")String cityName1,String localCityName);
	 
	 @Select("<script>" + 
				"select * from m_city "
				+ "<where> "
				+ "<if test='keyWord != \"\" and keyWord != null'>"
				+ " and (city_name like '%${keyWord}%' or local_city_name like '%${keyWord}%') "
				+ "</if>"
				+ "</where>"
				+ "<choose>"
				+ "<when test='orderBy != \"\" and orderBy != null'>"
				+ " order by ${orderBy} ${sort}"
				+ "</when>"
				+ "<otherwise>"
				+ " order by city_id desc"
				+ "</otherwise>"
				+ "</choose>"
				+ "</script>")
	 public List<City> getCitiesBySearchVo(SearchVo searchVo);
	 
	 @Insert("insert into m_city (city_name,local_city_name,country_id,date_created) values (#{cityName},#{localCityName},#{countryId},#{dateCreated})")
	 @Options(useGeneratedKeys = true,keyColumn = "city_id",keyProperty = "cityId")//将数据库生成的cityId传给city对象
	 public void insertCity(City city);
	 
	 @Update("update m_city set local_city_name = #{localCityName} where city_id = #{cityId}")
	 public void updateCity(City city);
	 
	 @Delete("delete from m_city where city_id=#{cityId}")
	 public void deleteCity(Integer cityId);
}
