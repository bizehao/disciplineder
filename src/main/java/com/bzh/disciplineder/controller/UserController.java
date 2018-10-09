package com.bzh.disciplineder.controller;

import com.bzh.disciplineder.model.FriendsInfo;
import com.bzh.disciplineder.model.User;
import com.bzh.disciplineder.model.UserInfo;
import com.bzh.disciplineder.model.request.Friend;
import com.bzh.disciplineder.service.UserService;
import com.bzh.disciplineder.utils.ResultMap;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
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
@Api(tags = {"获取用户信息添加好友接口"}, description = "UserController")
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
	@ApiOperation(value = "搜索用户信息")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "username", value = "用户名", required = true, dataType = "String", paramType = "query")
	})
	@GetMapping("/get-user-info")
	public ResultMap getUserInfo(@RequestParam("username") String username) {
		log.warn("username");
		UserInfo userInfo = userService.getUserInfoByName(username);
		return new ResultMap().success().message("").data(userInfo);
	}

	/**
	 * 获取用户信息
	 *
	 * @param username
	 * @return
	 */
	@ApiOperation(value = "搜索用户信息,模糊搜索,多个")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "username", value = "用户名", required = true, dataType = "String", paramType = "query")
	})
	@GetMapping("/get-user-infos")
	public ResultMap getUserInfos(@RequestParam("username") String username) {
		log.warn("username");
		List<UserInfo> userInfo = userService.getUserInfosByName(username);
		return new ResultMap().success().message("").data(userInfo);
	}

	/**
	 * 添加好友
	 * @param friend
	 * @return
	 */
	@ApiOperation(value = "添加好友")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "username", value = "当前用户id", required = true, dataType = "String", paramType = "query"),
			@ApiImplicitParam(name = "friendName", value = "添加的好友id", required = true, dataType = "String", paramType = "query"),
			@ApiImplicitParam(name = "remarkName", value = "好友的备注(为空的话默认为用户名)", required = true, dataType = "String", paramType = "query")
	})
	@PostMapping("/addFriend")
	public ResultMap addFriend(Friend friend) {
		int sign = userService.addFriends(friend);
		if(sign == 1){
			return new ResultMap().success().message("添加成功").data(true);
		}else if(sign == 2) {
			return new ResultMap().success().message("添加失败，该好友已添加").data(false);
		}else {
			return new ResultMap().success().message("添加失败").data(false);
		}
	}

	/**
	 * 推荐好友
	 * @return
	 */
	@ApiOperation(value = "推荐好友")
	@GetMapping("/recommendFriend")
	public ResultMap recommendFriends() {
		int num = userService.findAllNum();
		Random random = new Random();
		int[] ids = new int[5];
		for(int i=0; i<ids.length; i++){
			ids[i] = random.nextInt(num);
		}
		List<UserInfo> users =  userService.getUserInfoByIds(ids);
		return new ResultMap().success().message("添加成功").data(users);
	}

	/**
	 * 查询好友，好友列表
	 * @return
	 */
	@ApiOperation(value = "显示好友信息(所有)")
	@ApiImplicitParam(name = "username", value = "当前用户", required = true, dataType = "String", paramType = "query")
	@GetMapping("/getFriends")
	public ResultMap getFriends(@RequestParam("username") String username) {
		List<FriendsInfo> users =  userService.selectFriendByUsername(username);
		return new ResultMap().success().message("查询成功").data(users);
	}

}
