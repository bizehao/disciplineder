package com.bzh.disciplineder.model.request;

import lombok.Data;

/**
 * @author 毕泽浩
 * @Description: 消息 pojo
 * @time 2018/10/1 15:38
 */
@Data
public class RequestMessage {
	private int userId;
	private int sender;
	private String message;

	public RequestMessage(int userId, int sender, String message) {
		this.userId = userId;
		this.sender = sender;
		this.message = message;
	}
}
