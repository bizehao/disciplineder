package com.bzh.disciplineder.controller;

import com.bzh.disciplineder.model.request.RequestRegister;
import com.bzh.disciplineder.model.User;
import com.bzh.disciplineder.service.RedisService;
import com.bzh.disciplineder.service.UserService;
import com.bzh.disciplineder.utils.Data;
import com.bzh.disciplineder.model.request.RequestLoginUser;
import com.bzh.disciplineder.utils.ResultMap;
import com.bzh.disciplineder.model.user.LoginDetail;
import com.bzh.disciplineder.model.user.TokenDetail;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
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

@Api(tags = {"用户登录注册注销接口"}, description = "LoginController")
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
	 *
	 * @param requestLoginUser
	 * @param bindingResult
	 * @return
	 */
	@ApiOperation(value = "登录")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "username", value = "用户名", required = true, dataType = "String", paramType = "query"),
			@ApiImplicitParam(name = "password", value = "用户密码", required = true, dataType = "String", paramType = "query")
	})
	@RequestMapping(value = "login", method = RequestMethod.POST)
	public ResultMap login(@Valid RequestLoginUser requestLoginUser, BindingResult bindingResult) {
		// 检查有没有输入用户名密码和格式对不对
		StringBuilder message = new StringBuilder();
		if (bindingResult.hasErrors()) {
			for (FieldError fieldError : bindingResult.getFieldErrors()) {
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
	 *
	 * @param username
	 * @return
	 */
	@ApiOperation(value = "注销")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "username", value = "用户名", required = true, dataType = "String", paramType = "query"),
	})
	@GetMapping("login-out")
	public ResultMap getMessage(String username) {
		redisService.remove(username);
		return new ResultMap().success().message("注销成功").data(true);
	}

	/**
	 * 注册
	 *
	 * @param requestRegister
	 * @param bindingResult
	 * @return 0:注册失败，账户已存在 1:注册成功
	 */
	@ApiOperation(value = "注册")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "username", value = "用户名", required = true, dataType = "String", paramType = "query"),
			@ApiImplicitParam(name = "password", value = "用户密码", required = true, dataType = "String", paramType = "query"),
			@ApiImplicitParam(name = "againPassword", value = "再次输入密码", required = true, dataType = "String", paramType = "query"),
			@ApiImplicitParam(name = "email", value = "邮箱地址", required = true, dataType = "String", paramType = "query"),
			@ApiImplicitParam(name = "name", value = "签名", required = false, dataType = "String", paramType = "query"),
			@ApiImplicitParam(name = "headPortrait", value = "上传头像(暂未实现，先不用)", required = false, dataType = "String", paramType = "query"),
			@ApiImplicitParam(name = "descript", value = "个人描述", required = false, dataType = "String", paramType = "query"),
			@ApiImplicitParam(name = "address", value = "地址", required = false, dataType = "String", paramType = "query"),
			@ApiImplicitParam(name = "motto", value = "座右铭", required = false, dataType = "String", paramType = "query"),
	})
	@PostMapping("register")
	public ResultMap register(@Valid RequestRegister requestRegister, BindingResult bindingResult) {
		System.out.println(requestRegister);
		StringBuilder message = new StringBuilder();
		if (!requestRegister.getPassword().equals(requestRegister.getAgainPassword())) {
			return new ResultMap().fail("401").message("注册失败,两次密码不一致").data(2);
		}
		if (bindingResult.hasErrors()) {
			for (FieldError fieldError : bindingResult.getFieldErrors()) {
				message.append(fieldError.getDefaultMessage()).append(";");
			}
			return new ResultMap().fail("400").message(message.toString()).data(null);
		}
		boolean v = userService.register(requestRegister);
		if (v) {
			return new ResultMap().success().message("注册成功").data(1);
		}
		return new ResultMap().fail("422").message("注册失败").data(0);
	}

}
