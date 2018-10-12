package com.bzh.disciplineder.model;

/**
 * @author 毕泽浩
 * @Description:
 * @time 2018/9/28 14:06
 */
public class Talk {
	private int id;//消息ID
	private String code;
	private String sender;
	private String receiver;
	private int push;//是否推送？
	private String time;
	private String message;
	public Talk() {
		setCode("200");
		setSender("我是手机001");
	}




		public String getTime() {
			return time;
		}

		public void setTime(String time) {
			this.time = time;
		}

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}

public int getId(){return id;}
public void setId(int id){this.id=id;}
	public int getPush(){
		return push;
	}

	public void setPush(int push){
		this.push=push;
	}
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}


	//设置消息体


}
