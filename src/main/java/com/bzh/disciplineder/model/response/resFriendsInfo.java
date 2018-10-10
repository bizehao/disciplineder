package com.bzh.disciplineder.model.response;

import lombok.Data;

/**
 * @author 毕泽浩
 * @Description: 好友信息
 * @time 2018/10/8 11:54
 */
@Data
public class resFriendsInfo {
	private String username;
	private String name;
	private byte[] headportrait;
	private String descript;
	private String address;
	private String motto;
	private String remarkName; //REMARK_NAME;

	public resFriendsInfo(String username, String name, byte[] headportrait, String descript, String address, String motto, String remarkName) {
		this.username = username;
		this.name = name;
		this.headportrait = headportrait;
		this.descript = descript;
		this.address = address;
		this.motto = motto;
		this.remarkName = remarkName;
	}
}
