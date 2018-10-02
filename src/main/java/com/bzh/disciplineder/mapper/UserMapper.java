package com.bzh.disciplineder.mapper;

import com.bzh.disciplineder.model.request.Friend;
import com.bzh.disciplineder.model.request.RequestMessage;
import com.bzh.disciplineder.model.request.RequestRegister;
import com.bzh.disciplineder.model.User;
import com.bzh.disciplineder.model.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @author 毕泽浩
 * @Description:
 * @time 2018/9/8 12:48
 */
@Mapper
@Repository
public interface UserMapper {

	/**
	 * 获取用户 登录
	 * @param username
	 * @return
	 */
	User getUserByName(@Param("username") String username);

	/**
	 * 查出所有的用户
	 * @return
	 */
	List<User> findAll();

	/**
	 * 获取所有用户的数量
	 * @return
	 */
	int findAllNum();

	/**
	 * 获取用户其余资料信息
	 * @param username
	 * @return
	 */
	UserInfo getUserInfoByName(@Param("username") String username);

	/**
	 * 获取用户其余资料信息
	 * @param ids
	 * @return
	 */
	List<UserInfo> getUserInfoByIds(@Param("ids") int[] ids);

	/**
	 * 注册
	 * @param requestRegister
	 * @return
	 */
	int register(RequestRegister requestRegister);

	/**
	 * 完善用户信息
	 * @param username
	 * @return
	 */
	int updateUserInfo(String username, Map<String, String> userInfo);

	/**
	 * 更新用户权限
	 * @param username
	 * @return
	 */
	int updateUserRole(String username,int role);

	/**
	 * 添加好友
	 * @param friend
	 * @return
	 */
	int addFriends(Friend friend);

	/**
	 * 发送消息
	 * @param requestMessage
	 * @return
	 */
	int addMessage(RequestMessage requestMessage);

}
