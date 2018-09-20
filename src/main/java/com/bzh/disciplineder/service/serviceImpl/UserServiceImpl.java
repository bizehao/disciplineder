package com.bzh.disciplineder.service.serviceImpl;

import com.bzh.disciplineder.mapper.UserMapper;
import com.bzh.disciplineder.model.request.RequestRegister;
import com.bzh.disciplineder.model.User;
import com.bzh.disciplineder.model.UserInfo;
import com.bzh.disciplineder.model.user.LoginDetail;
import com.bzh.disciplineder.model.user.TokenDetail;
import com.bzh.disciplineder.service.UserService;
import com.bzh.disciplineder.utils.TokenUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 毕泽浩
 * @Description:
 * @time 2018/9/18 15:09
 */
@Slf4j
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

	@Transactional
	@Override
	public boolean register(RequestRegister requestRegister) {
		int v = userMapper.register(requestRegister);
		return v > 0;
	}

	@Transactional
	@Override
	public boolean updateUserInfo(String username, Map<String, String> userInfo) {
		int v = userMapper.updateUserInfo(username, userInfo);
		return v > 0;
	}
	@Transactional
	@Override
	public boolean updateUserRole(String username, int role) {
		int v = userMapper.updateUserRole(username, role);
		return v > 0;
	}

	@Transactional
	@Override
	public int insertTest() {
		log.warn("测试中");
		int sign = 0;
		Map<String,Integer> map = new HashMap<>();
		map.put("zhangsan",20);
		map.put("lisi",25);
		map.put("wangwu",null);
		map.put("shunliu",35);
		map.put("aqi",35);
		map.put("songba",35);
		for (Map.Entry<String,Integer> entry : map.entrySet()){
			sign+=userMapper.insertTest(entry.getKey(), entry.getValue());
		}
		return sign;
	}
}
