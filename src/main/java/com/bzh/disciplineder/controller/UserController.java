package com.bzh.disciplineder.controller;

import com.bzh.disciplineder.model.UserInfo;
import com.bzh.disciplineder.service.UserService;
import com.bzh.disciplineder.utils.ResultMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 毕泽浩
 * @Description:
 * @time 2018/9/18 15:12
 */
@RestController
@RequestMapping("user")
public class UserController {

	private final UserService userService;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	/**
	 * 获取信息
	 * @param username
	 * @return
	 */
	@GetMapping("/get-user-info")
	public ResultMap getUserInfo(@RequestParam("username") String username){
		UserInfo userInfo = userService.getUserInfoById(username);
		return new ResultMap().success().message("").data(userInfo);
	}
}
