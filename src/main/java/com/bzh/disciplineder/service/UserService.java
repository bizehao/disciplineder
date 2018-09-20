package com.bzh.disciplineder.service;

import com.bzh.disciplineder.model.request.RequestRegister;
import com.bzh.disciplineder.model.UserInfo;
import com.bzh.disciplineder.model.user.LoginDetail;
import com.bzh.disciplineder.model.user.TokenDetail;

import java.util.Map;

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

	//完善用户信息
	boolean updateUserInfo(String username, Map<String, String> userInfo);

	//更改用户角色权利
	boolean updateUserRole(String username,int role);

	//测试事务
	int insertTest();
}
