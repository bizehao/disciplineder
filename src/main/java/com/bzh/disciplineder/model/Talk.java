package com.bzh.disciplineder.model;

import lombok.Data;
import java.util.Date;

/**
 * @author 毕泽浩
 * @Description:
 * @time 2018/9/28 14:06
 */
@Data
public class Talk {
	private Long id;//消息ID
	private String code;
	private String sender;
	private String receiver;
	private int push;//是否推送？
	private Date time;
	private String message;

	public Talk() {

	}

	public Talk(Long id, String code, String sender, String receiver, Date time, String message) {
		this.id = id;
		this.code = code;
		this.sender = sender;
		this.receiver = receiver;
		this.time = time;
		this.message = message;
	}
}
