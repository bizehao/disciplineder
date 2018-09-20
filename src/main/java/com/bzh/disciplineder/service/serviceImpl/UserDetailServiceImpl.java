package com.bzh.disciplineder.service.serviceImpl;

import com.bzh.disciplineder.mapper.UserMapper;
import com.bzh.disciplineder.model.User;
import com.bzh.disciplineder.service.RedisService;
import com.bzh.disciplineder.utils.SecurityModelFactory;
import com.bzh.disciplineder.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author 毕泽浩
 * @Description: 获取 userDetail
 * @time 2018/9/10 14:25
 */
@Service
public class UserDetailServiceImpl implements UserDetailsService {

	private final UserMapper userMapper;

	private final RedisService redisService;

	private final TokenUtils tokenUtils;

	@Autowired
	public UserDetailServiceImpl(UserMapper userMapper, RedisService redisService, TokenUtils tokenUtils) {
		this.userMapper = userMapper;
		this.redisService = redisService;
		this.tokenUtils = tokenUtils;
	}

	@Override
	public UserDetails loadUserByUsername(String token) throws UsernameNotFoundException {

		// 尝试拿 token 中的 username
		// 若是没有 token 或者拿 username 时出现异常，那么 username 为 null
		String username = tokenUtils.getUsernameFromToken(token);
		if (username == null) {
			return null;
		} else {
			User user = (User) redisService.get(username);
			if (user == null) {
				user = userMapper.getUserByName(username);
				if (user == null) {
					throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
				} else {
					user.setToken(token);
					redisService.set(user.getUsername(), user, 10000L);
					return SecurityModelFactory.create(user);
				}
			} else {
				return SecurityModelFactory.create(user);
			}
		}
	}
}
