package com.bzh.disciplineder.model.request;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author 毕泽浩
 * @Description: 注册的实体类
 * @time 2018/9/19 14:17
 */
@Data
public class RequestRegister {

	@NotEmpty(message="用户名不能为空")
	private String username;

	@NotEmpty(message="密码不能为空")
	@Size(min = 6, max = 15, message = "密码最少6位")
	@Size(max = 15, message = "密码最多15位")
	private String password;

	@NotEmpty(message="密码不能为空")
	@Size(min = 6, max = 15, message = "密码最少6位")
	@Size(max = 15, message = "密码最多15位")
	private String againPassword;

	@Email(message="邮箱的格式不正确")
	private String email;

	//@NotEmpty(RequestMessage="签名不能为空")
	private String name;

	private String headPortrait;

	private String descript;

	private String address;

	private String motto;

	@Override
	public String toString() {
		return "RequestRegister{" +
				"username='" + username + '\'' +
				", password='" + password + '\'' +
				", email='" + email + '\'' +
				", name='" + name + '\'' +
				", headPortrait='" + headPortrait + '\'' +
				", descript='" + descript + '\'' +
				", address='" + address + '\'' +
				", motto='" + motto + '\'' +
				'}';
	}
}
