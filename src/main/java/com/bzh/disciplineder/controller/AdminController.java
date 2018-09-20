package com.bzh.disciplineder.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 毕泽浩
 * @Description:
 * @time 2018/9/11 15:05
 */
@RestController
@RequestMapping("admin")
public class AdminController {

	@GetMapping("say")
	public String sayHi(){
		return "你好啊 管理员";
	}
}
