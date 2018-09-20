package com.bzh.disciplineder.mapper;

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
	 * 获取用户其余资料信息
	 * @param username
	 * @return
	 */
	UserInfo getUserInfoById(@Param("username") String username);

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

	int insertTest(@Param("name") String name,@Param("age") Integer age);
}
