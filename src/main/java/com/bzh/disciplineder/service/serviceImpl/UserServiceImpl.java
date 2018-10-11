package com.bzh.disciplineder.service.serviceImpl;

import com.bzh.disciplineder.mapper.UserMapper;
import com.bzh.disciplineder.model.FriendsInfo;
import com.bzh.disciplineder.model.request.Friend;
import com.bzh.disciplineder.model.request.RequestMessage;
import com.bzh.disciplineder.model.request.RequestRegister;
import com.bzh.disciplineder.model.User;
import com.bzh.disciplineder.model.UserInfo;
import com.bzh.disciplineder.model.response.resFriendsInfo;
import com.bzh.disciplineder.model.user.LoginDetail;
import com.bzh.disciplineder.model.user.TokenDetail;
import com.bzh.disciplineder.service.UserService;
import com.bzh.disciplineder.utils.CommonUtils;
import com.bzh.disciplineder.utils.TokenUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.Channel;
import java.nio.channels.FileChannel;
import java.util.*;
import java.util.zip.GZIPOutputStream;

import static com.sun.tools.javac.jvm.ByteCodes.ret;

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
		UserInfo userInfo = userMapper.getUserInfoByName(username);
		userInfo.setHeadportrait(CommonUtils.imgtobyte(userInfo.getHeadportrait()));
		return userInfo;
	}

	@Override
	public List<UserInfo> getUserInfosByName(String username) {
		username = "%" + username + "%";
		List<UserInfo> list = userMapper.getUserInfosByName(username);
		for (UserInfo userInfo : list){
			userInfo.setHeadportrait(CommonUtils.imgtobyte(userInfo.getHeadportrait()));
		}
		return list;
	}

	@Override
	public List<UserInfo> recommendFriends() {
		List<UserInfo> list = userMapper.recommendFriends();
		for (UserInfo userInfo : list){
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
		List<FriendsInfo> friendsInfos = userMapper.selectFriendByUsername(username);
		for (FriendsInfo inf : friendsInfos) {
			inf.setHeadportrait(CommonUtils.imgtobyte(inf.getHeadportrait()));
		}
		return friendsInfos;
	}

	@Override
	public int uploadpicture(String username, MultipartFile file) {
		File directory = new File("");//设定为当前文件夹
		String str = null;
		try {
			file.transferTo(new File(directory.getAbsolutePath() + "/src/main/resources/static/Headportrait/" + username + ".ico"));
			str = directory.getAbsolutePath() + "/src/main/resources/static/Headportrait/" + username + ".ico";
		} catch (IOException e) {
			e.printStackTrace();
			return 0;
		}
		return userMapper.uploadpicture(username, str);
	}

	@Override
	public String getloadPng(String username) {
		String dedao = userMapper.getloadPng(username);
		return CommonUtils.imgtobyte(dedao);
	}

}
