package com.bzh.disciplineder.controller;

import com.bzh.disciplineder.model.User;
import com.bzh.disciplineder.model.UserInfo;
import com.bzh.disciplineder.model.request.Friend;
import com.bzh.disciplineder.service.UserService;
import com.bzh.disciplineder.utils.ResultMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * @author 毕泽浩
 * @Description:
 * @time 2018/9/18 15:12
 */
@Slf4j
@RestController
@RequestMapping("user")
public class UserController {

	private final UserService userService;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	/**
	 * 获取用户信息
	 *
	 * @param username
	 * @return
	 */
	@GetMapping("/get-user-info")
	public ResultMap getUserInfo(@RequestParam("username") String username) {
		log.warn("username");
		UserInfo userInfo = userService.getUserInfoByName(username);
		return new ResultMap().success().message("").data(userInfo);
	}

	/**
	 * 添加好友
	 * @param friend
	 * @return
	 */
	@PostMapping("/addFriend")
	public ResultMap addFriend(@RequestParam("friend") Friend friend) {
		boolean sign = userService.addFriends(friend);
		if(sign){
			return new ResultMap().success().message("添加成功").data(null);
		}else {
			return new ResultMap().success().message("添加失败").data(null);
		}
	}

	/**
	 * 推荐好友
	 * @return
	 */
	@GetMapping("/addFriend")
	public ResultMap getFriends() {
		int num = userService.findAllNum();
		Random random = new Random();
		int[] ids = new int[5];
		for(int i=0; i<ids.length; i++){
			ids[i] = random.nextInt(num);
		}
		List<UserInfo> users =  userService.getUserInfoByIds(ids);
		return new ResultMap().success().message("添加成功").data(users);
	}

}
