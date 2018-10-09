package com.bzh.disciplineder.model.request;

import lombok.Data;

/**
 * @author 毕泽浩
 * @Description: 添加好友
 * @time 2018/10/1 15:30
 */
@Data
public class Friend {
	private String username;
	private String friendName;
	private String remarkName;
}
