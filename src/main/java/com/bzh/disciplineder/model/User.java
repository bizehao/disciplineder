package com.bzh.disciplineder.model;

import com.bzh.disciplineder.model.user.LoginDetail;
import com.bzh.disciplineder.model.user.TokenDetail;
import lombok.Data;

import java.util.Date;

/**
 * @version V1.0.0
 * @Description
 * @Author liuyuequn weanyq@gmail.com
 * @Date 2017/10/3 1:45
 */
@Data
public class User implements LoginDetail, TokenDetail {

	private String id;
	private String username;
	private String password;
	private String authorities;
	private Date lastPasswordChange;
	private char enable;
	private String token;

	public User() {
	}

	public User(String id, String username, String password, String authorities, Date lastPasswordChange, char enable) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.authorities = authorities;
		this.lastPasswordChange = lastPasswordChange;
		this.enable = enable;
	}

	@Override
	public String getUsername() {
		return this.username;
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public boolean enable() {
		if (this.enable == '1') {
			return true;
		}
		return false;
	}

	@Override
	public String toString() {
		return "User{" +
				"username='" + username + '\'' +
				", password='" + password + '\'' +
				", authorities='" + authorities + '\'' +
				", lastPasswordChange=" + lastPasswordChange +
				", enable=" + enable +
				'}';
	}

}
