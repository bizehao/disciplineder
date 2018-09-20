package com.bzh.disciplineder.model.request;

import lombok.Data;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @version V1.0.0
 * @Description 用户登陆接口参数的实体类
 * @Author liuyuequn weanyq@gmail.com
 * @Date 2017/10/3 1:29
 */
@Data
public class RequestLoginUser {


	@NotBlank(message = "用户名不能为空")
	private String username;

	@NotBlank
	@Size(min = 6, message = "密码最少6位")
	@Size(max = 15, message = "密码最多15位")
	private String password;

	public RequestLoginUser() {
	}

	@Override
	public String toString() {
		return "RequestLoginUser{" +
				"username='" + username + '\'' +
				", password='" + password + '\'' +
				'}';
	}
}
