package com.bzh.disciplineder.service.serviceImpl;

import com.bzh.disciplineder.mapper.UserMapper;
import com.bzh.disciplineder.model.FriendsInfo;
import com.bzh.disciplineder.model.request.Friend;
import com.bzh.disciplineder.model.request.RequestMessage;
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
import java.util.List;
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
	public UserInfo getUserInfoByName(String username) {

		return userMapper.getUserInfoByName(username);
	}

	@Override
	public List<UserInfo> getUserInfosByName(String username) {
		username = "%"+username+"%";
		return userMapper.getUserInfosByName(username);
	}

	@Override
	public List<UserInfo> getUserInfoByIds(int[] ids) {
		return userMapper.getUserInfoByIds(ids);
	}

	@Transactional
	@Override
	public boolean register(RequestRegister requestRegister) {
		int v = 0;
		try {
			v = userMapper.register(requestRegister);
		} catch (Exception e) {
			e.getMessage();
			e.printStackTrace();
		}
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
	public Integer addFriends(Friend friend) {
		String username = friend.getUsername();
		String friendName = friend.getFriendName();
		String remarkName = friend.getRemarkName();
		if (remarkName == null || remarkName.equals("")) {
			remarkName = friendName;
		}
		int userId = userMapper.getIdByUserName(username);
		int friendId = userMapper.getIdByUserName(friendName);
		int isExit = userMapper.isExitFriend(userId,friendId);
		if(isExit == 0){
			return userMapper.addFriends(userId, friendId, remarkName);
		}else {
			return 2;
		}
	}

	@Transactional
	@Override
	public boolean addMessage(RequestMessage requestMessage) {
		int v = userMapper.addMessage(requestMessage);
		return v > 0;
	}

	@Override
	public int findAllNum() {
		return userMapper.findAllNum();
	}

	@Override
	public List<FriendsInfo> selectFriendByUsername(String username) {
		return userMapper.selectFriendByUsername(username);
	}

}
