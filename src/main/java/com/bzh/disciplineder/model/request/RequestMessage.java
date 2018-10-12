package com.bzh.disciplineder.model.request;

import lombok.Data;

/**
 * @author 毕泽浩
 * @Description: 消息 pojo
 * @time 2018/10/1 15:38
 */
@Data
public class RequestMessage {
	private String sender;//发送者ID
	private String receiver;//接收者ID
	private String message;//发送内容
	private int push;//是否推送？

	public RequestMessage(String sender, String receiver, String message,int push) {
		this.sender = sender;
		this.receiver = receiver;
		this.message = message;
		this.push=push;
	}

}
