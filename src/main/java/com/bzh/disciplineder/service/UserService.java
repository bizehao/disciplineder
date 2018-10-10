package com.bzh.disciplineder.service;

import com.bzh.disciplineder.model.FriendsInfo;
import com.bzh.disciplineder.model.request.Friend;
import com.bzh.disciplineder.model.request.RequestMessage;
import com.bzh.disciplineder.model.request.RequestRegister;
import com.bzh.disciplineder.model.UserInfo;
import com.bzh.disciplineder.model.user.LoginDetail;
import com.bzh.disciplineder.model.user.TokenDetail;
import org.apache.ibatis.annotations.Param;

import javax.servlet.ServletOutputStream;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
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

	//根据指定用户名查询
	UserInfo getUserInfoByName(String username);

	//根据用户名模糊查询
	List<UserInfo> getUserInfosByName(String username);

	//随机获取用户
	List<UserInfo> getUserInfoByIds(int[] ids);

	//注册
	boolean register(RequestRegister requestRegister);

	//完善用户信息
	boolean updateUserInfo(String username, Map<String, String> userInfo);

	//更改用户角色权利
	boolean updateUserRole(String username, int role);

	//添加好友
	//0:添加失败 1:添加成功 2:该好友已存在
	Integer addFriends(Friend friend);

	//发送消息
	boolean addMessage(RequestMessage requestMessage);

	//获取所有用户数量
	int findAllNum();

	//好友列表，查询好友
	List<FriendsInfo> selectFriendByUsername(String username);

	//上传/修改头像
	int uploadpicture(String username, byte[] picture);
}
