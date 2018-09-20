package com.bzh.disciplineder.service;

import com.bzh.disciplineder.model.RequestRegister;
import com.bzh.disciplineder.model.UserInfo;
import com.bzh.disciplineder.model.user.LoginDetail;
import com.bzh.disciplineder.model.user.TokenDetail;

/**
 * @author 毕泽浩
 * @Description:
 * @time 2018/9/18 15:08
 */
public interface UserService {

	//根据用户名获取用户主要信息
	LoginDetail getLoginDetail(String username);

	//得到token
	String generateToken(TokenDetail tokenDetail);

	//获取用户其余资料
	UserInfo getUserInfoById(String username);

	//注册
	boolean register(RequestRegister requestRegister);
}
