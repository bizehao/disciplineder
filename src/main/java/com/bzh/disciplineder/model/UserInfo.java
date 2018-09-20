package com.bzh.disciplineder.model;

import lombok.Data;

/**
 * @author 毕泽浩
 * @Description: 用户信息表
 * @time 2018/9/18 14:59
 */
@Data
public class UserInfo {
	private String name;
	private String headportrait;
	private String descript;
	private String address;
	private String motto;

	public UserInfo(String name, String headportrait, String descript, String address, String motto) {
		this.name = name;
		this.headportrait = headportrait;
		this.descript = descript;
		this.address = address;
		this.motto = motto;
	}

	@Override
	public String toString() {
		return "UserInfo{" +
				"name='" + name + '\'' +
				", headportrait='" + headportrait + '\'' +
				", descript='" + descript + '\'' +
				", address='" + address + '\'' +
				", motto='" + motto + '\'' +
				'}';
	}
}
