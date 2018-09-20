package com.bzh.disciplineder.model;

import lombok.Data;

/**
 * @author 毕泽浩
 * @Description:
 * @time 2018/9/12 16:00
 */
@Data
public class City {
	private int cityId;
	private String cityName;
	private String cityIntroduce;

	public City() {
	}

	public City(int cityId, String cityName, String cityIntroduce) {
		this.cityId = cityId;
		this.cityName = cityName;
		this.cityIntroduce = cityIntroduce;
	}

	public City(String cityName, String cityIntroduce) {
		this.cityName = cityName;
		this.cityIntroduce = cityIntroduce;
	}
}
