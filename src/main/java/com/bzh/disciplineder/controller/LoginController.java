package com.bzh.disciplineder.controller;

import com.bzh.disciplineder.model.RequestRegister;
import com.bzh.disciplineder.model.User;
import com.bzh.disciplineder.service.RedisService;
import com.bzh.disciplineder.service.UserService;
import com.bzh.disciplineder.utils.Data;
import com.bzh.disciplineder.model.RequestLoginUser;
import com.bzh.disciplineder.utils.ResultMap;
import com.bzh.disciplineder.model.user.LoginDetail;
import com.bzh.disciplineder.model.user.TokenDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

/**
 * @version V1.0.0
 * @Description 登陆接口
 * @Author liuyuequn weanyq@gmail.com
 * @Date 2017/10/3 1:30
 */
@RestController
@RequestMapping("user")
public class LoginController {

	private final UserService userService;

	private final RedisService redisService;

	@Value("${token.header}")
	private String tokenHeader;

	@Autowired
	public LoginController(UserService userService, RedisService redisService) {
		this.userService = userService;
		this.redisService = redisService;
	}

	/**
	 * 登录
	 * @param requestLoginUser
	 * @param bindingResult
	 * @return
	 */
	@RequestMapping(value = "login", method = RequestMethod.POST)
	public ResultMap login(@Valid RequestLoginUser requestLoginUser, BindingResult bindingResult) {
		// 检查有没有输入用户名密码和格式对不对
		StringBuilder message = new StringBuilder();
		if (bindingResult.hasErrors()) {
			for(FieldError fieldError : bindingResult.getFieldErrors()){
				message.append(fieldError.getDefaultMessage()).append(";");
			}
			return new ResultMap().fail("400").message(message.toString()).data(null);
		}
		LoginDetail loginDetail = userService.getLoginDetail(requestLoginUser.getUsername());
		ResultMap ifLoginFail = checkAccount(requestLoginUser, loginDetail);
		if (ifLoginFail != null) {
			return ifLoginFail;
		}
		//用户信息放入redis缓存中
		redisService.switchDB(0);
		String token = userService.generateToken((TokenDetail) loginDetail); //获取token
		User user = (User) loginDetail;
		user.setToken(token);
		redisService.set(loginDetail.getUsername(), user, 10000L);
		return new ResultMap().success().message("登录成功").data(new Data().
				addObj(tokenHeader, token).
				addObj("username", user.getUsername()));
	}

	private ResultMap checkAccount(RequestLoginUser requestLoginUser, LoginDetail loginDetail) {
		if (loginDetail == null) {
			return new ResultMap().fail("434").message("账号不存在！").data(null);
		} else {
			if (!loginDetail.enable()) {
				return new ResultMap().fail("452").message("账号在黑名单中").data(null);
			}
			if (!loginDetail.getPassword().equals(requestLoginUser.getPassword())) {
				return new ResultMap().fail("438").message("密码错误！").data(null);
			}
			//处理当前用户名是否在容器中已存在 存在: 使上一个token失效 将这个添加进去 不存在: 将这个添加进去

		}
		return null;
	}

	/**
	 * 注销
	 * @param username
	 * @return
	 */
	@GetMapping("login-out")
	public ResultMap getMessage(String username) {
		redisService.remove(username);
		return new ResultMap().success().message("注销成功").data(null);
	}

	/**
	 * 注册
	 * @param requestRegister
	 * @param bindingResult
	 * @return
	 */
	@PostMapping("register")
	public ResultMap register(@Valid RequestRegister requestRegister, BindingResult bindingResult) {
		System.out.println(requestRegister);
		StringBuilder message = new StringBuilder();
		if (bindingResult.hasErrors()) {
			for(FieldError fieldError : bindingResult.getFieldErrors()){
				message.append(fieldError.getDefaultMessage()).append(";");
			}
			return new ResultMap().fail("400").message(message.toString()).data(null);
		}
		boolean v = userService.register(requestRegister);
		if(v){
			return new ResultMap().success().message("注册成功").data(null);
		}
		return new ResultMap().fail("422").message("注册失败").data(null);
	}

}
