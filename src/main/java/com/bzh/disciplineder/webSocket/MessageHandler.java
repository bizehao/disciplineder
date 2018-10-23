package com.bzh.disciplineder.webSocket;

import com.bzh.disciplineder.model.Talk;

/**
 * @author 毕泽浩
 * @Description:
 * @time 2018/10/18 11:59
 */
public interface MessageHandler {

	//中转推送
	void transferPush(Talk talk);

	//服务器主动推送
	void activePush(String username);
}
