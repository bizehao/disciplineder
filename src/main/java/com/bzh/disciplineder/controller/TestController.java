package com.bzh.disciplineder.controller;

import com.bzh.disciplineder.service.RedisService;
import com.bzh.disciplineder.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 毕泽浩
 * @Description:
 * @time 2018/9/11 15:06
 */
@Slf4j
@RestController
@RequestMapping("test")
public class TestController {

	private final UserService userService;

	@Autowired
	public TestController(UserService userService) {
		this.userService = userService;
	}

	@Transactional
	@GetMapping(value = "say")
	public String saveCity(){

		int value = userService.insertTest();
		return "success  "+value;
	}
}