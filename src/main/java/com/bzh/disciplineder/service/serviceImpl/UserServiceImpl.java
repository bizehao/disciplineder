package com.bzh.disciplineder.service.serviceImpl;

import com.bzh.disciplineder.mapper.UserMapper;
import com.bzh.disciplineder.model.FriendsInfo;
import com.bzh.disciplineder.model.Talk;
import com.bzh.disciplineder.model.request.Friend;
import com.bzh.disciplineder.model.request.RequestRegister;
import com.bzh.disciplineder.model.User;
import com.bzh.disciplineder.model.UserInfo;
import com.bzh.disciplineder.model.user.LoginDetail;
import com.bzh.disciplineder.model.user.TokenDetail;
import com.bzh.disciplineder.service.UserService;
import com.bzh.disciplineder.utils.CommonUtils;
import com.bzh.disciplineder.utils.TokenUtils;
import com.bzh.disciplineder.webSocket.WebSocketService;
import lombok.extern.slf4j.Slf4j;
import org.java_websocket.WebSocket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * @author 毕泽浩
 * @Description:
 * @time 2018/9/18 15:09
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {


	@Autowired
	private WebSocketService webSocketService;

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
		UserInfo userInfo = userMapper.getUserInfoByName(username);
		userInfo.setHeadportrait(CommonUtils.imgtobyte(userInfo.getHeadportrait()));
		return userInfo;
	}

	@Override
	public List<UserInfo> getUserInfosByName(String username) {
		username = "%" + username + "%";
		List<UserInfo> list = userMapper.getUserInfosByName(username);
		for (UserInfo userInfo : list) {
			userInfo.setHeadportrait(CommonUtils.imgtobyte(userInfo.getHeadportrait()));
		}
		return list;
	}

	@Override
	public List<UserInfo> recommendFriends() {
		List<UserInfo> list = userMapper.recommendFriends();
		for (UserInfo userInfo : list) {
			userInfo.setHeadportrait(CommonUtils.imgtobyte(userInfo.getHeadportrait()));
		}
		return list;
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
		int isExit = userMapper.isExitFriend(userId, friendId);
		if (isExit == 0) {
			return userMapper.addFriends(userId, friendId, remarkName);
		} else {
			return 2;
		}
	}

	@Transactional
	@Override
	public boolean addMessage(Talk talk) {
		int v = userMapper.addMessage(talk);
		return v > 0;
	}

	@Override
	public int findAllNum() {
		return userMapper.findAllNum();
	}

	@Override
	public List<FriendsInfo> selectFriendByUsername(String username) {
		List<FriendsInfo> friendsInfos = userMapper.selectFriendByUsername(username);
		for (FriendsInfo inf : friendsInfos) {
			inf.setHeadportrait(CommonUtils.imgtobyte(inf.getHeadportrait()));
		}
		return friendsInfos;
	}

	@Override
	public int uploadpicture(String username, MultipartFile file) {
		File directory = new File("");//设定为当前文件夹
		String str = directory.getAbsolutePath() + "/src/main/resources/static/Headportrait/" + username + ".ico";
		int sign = userMapper.uploadpicture(username, str);
		if (sign > 1) {
			try {
				file.transferTo(new File(directory.getAbsolutePath() + "/src/main/resources/static/Headportrait/" + username + ".ico"));
			} catch (IOException e) {
				e.printStackTrace();
				sign = 0;
			}
		}
		if(sign > 1){
			WebSocket webSocket = webSocketService.getWebSocketByUsername(username);
			if(webSocket.isOpen()){
				List<FriendsInfo> friendsInfos = userMapper.selectFriendByUsername(username);
				for (FriendsInfo friendsInfo : friendsInfos){
					String friendName = friendsInfo.getUsername();
					WebSocket friendWS = webSocketService.getWebSocketByUsername(friendName);
					if(friendWS.isOpen()){
						Long talkID = UUID.randomUUID().getLeastSignificantBits();
						Talk talk = new Talk(talkID,"300",username,friendName,new Date(),CommonUtils.imgtobyte(str));
						webSocketService.sendToSingle(friendWS,talk);
					}
				}
			}
		}
		return sign;
	}

	@Override
	public String getloadPng(String username) {
		String dedao = userMapper.getloadPng(username);
		return CommonUtils.imgtobyte(dedao);
	}

	@Override
	public List<Talk> getmessage(String username) {

		return userMapper.getmessage(username);
	}

	@Override
	public int upmespush(Long id) {
		return userMapper.upmespush(id);
	}

	@Override
	public boolean deleteFriend(String username, String friendName) {
		int val = userMapper.deleteFriend(username,friendName);
		return val > 0;
	}

}
