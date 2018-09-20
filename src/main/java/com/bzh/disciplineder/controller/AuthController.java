package com.bzh.disciplineder.controller;

import com.bzh.disciplineder.utils.ResultMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 毕泽浩
 * @Description:
 * @time 2018/9/11 15:06
 */
@RestController
@RequestMapping("auth")
public class AuthController {

	@GetMapping("say")
	public ResultMap sayHi(){
		return new ResultMap().success().message("").data(null);
	}
}