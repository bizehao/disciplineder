package com.bzh.disciplineder.model.request;

import lombok.Data;

/**
 * @author 毕泽浩
 * @Description: 添加好友
 * @time 2018/10/1 15:30
 */
@Data
public class Friend {
	private int userId;
	private int friendId;
	private String remarkName;
}
