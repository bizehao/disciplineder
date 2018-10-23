package com.bzh.disciplineder.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author 毕泽浩
 * @Description: 好友信息
 * @time 2018/10/8 11:54
 */
@Data
public class FriendsInfo{
	private String username; //用户名
	private String name; //真实姓名
	private String headportrait;
	private String descript;
	private String address;
	private String motto;
	private String remarkName; //REMARK_NAME; 备注姓名

	public FriendsInfo(String username, String name, String headportrait, String descript, String address, String motto, String remarkName) {
		this.username = username;
		this.name = name;
		this.headportrait = headportrait;
		this.descript = descript;
		this.address = address;
		this.motto = motto;
		this.remarkName = remarkName;
	}
}
