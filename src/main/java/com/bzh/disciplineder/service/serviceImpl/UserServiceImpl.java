package com.bzh.disciplineder.service.serviceImpl;

import com.bzh.disciplineder.mapper.UserMapper;
import com.bzh.disciplineder.model.RequestRegister;
import com.bzh.disciplineder.model.User;
import com.bzh.disciplineder.model.UserInfo;
import com.bzh.disciplineder.model.user.LoginDetail;
import com.bzh.disciplineder.model.user.TokenDetail;
import com.bzh.disciplineder.service.UserService;
import com.bzh.disciplineder.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 毕泽浩
 * @Description:
 * @time 2018/9/18 15:09
 */
@Service
public class UserServiceImpl implements UserService {

	private final UserMapper userMapper;
	private final TokenUtils tokenUtils;

	@Autowired
	public UserServiceImpl(UserMapper userMapper, TokenUtils tokenUtils) {
		this.userMapper = userMapper;
		this.tokenUtils = tokenUtils;
	}

	@Override
	public LoginDetail getLoginDetail(String username) {
		User loginDetail = userMapper.getUserByName(username);
		User user = loginDetail;
		return loginDetail;
	}

	@Override
	public String generateToken(TokenDetail tokenDetail) {
		return tokenUtils.generateToken(tokenDetail);
	}

	@Override
	public UserInfo getUserInfoById(String username) {

		return userMapper.getUserInfoById(username);
	}

	@Override
	public boolean register(RequestRegister requestRegister) {
		int v = userMapper.register(requestRegister);
		if(v > 0){
			return true;
		}
		return false;
	}
}
