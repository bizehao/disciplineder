package com.bzh.disciplineder.model.user;

/**
 * @author 毕泽浩
 * @Description:
 * @time 2018/9/10 14:12
 */
public interface TokenDetail {

	//TODO: 这里封装了一层，不直接使用 username 做参数的原因是可以方便未来增加其他要封装到 token 中的信息
	String getUsername();

	//获取用户id
	String getId();
}
