package com.bzh.disciplineder.controller;

import com.bzh.disciplineder.model.City;
import com.bzh.disciplineder.model.User;
import com.bzh.disciplineder.service.RedisService;
import com.bzh.disciplineder.service.serviceImpl.RedisServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 毕泽浩
 * @Description:
 * @time 2018/9/11 15:06
 */
@RestController
@RequestMapping("test")
public class TestController {

	@Autowired
	private RedisService redisService;

	@GetMapping(value = "saveCity")
	public String saveCity(int cityId,String cityName,String cityIntroduce){
		City city = new City(cityId,cityName,cityIntroduce);
		redisService.set(cityId+"",city);
		return "success";
	}



	//http://localhost:8888/getCityById?cityId=1
	@GetMapping(value = "getCityById")
	public City getCity(int cityId){
		City city = (City) redisService.get(cityId+"");
		return city;
	}

	@GetMapping(value = "set")
	public String demoTest(){
		User user = (User) redisService.get("lisi");
		System.out.println(user);
		return "success";
	}

}