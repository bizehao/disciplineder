package com.bzh.disciplineder.webSocket;

import com.bzh.disciplineder.model.Talk;
import org.java_websocket.WebSocket;

import java.util.Map;

/**
 * @author 毕泽浩
 * @Description:
 * @time 2018/10/18 11:20
 */
public interface WebSocketService {

	//开始
	void start();

	//获取端口
	int getPort();

	//根据用户名获取webSocket
	WebSocket getWebSocketByUsername(String username);

	//获取用户websocket集合
	Map<String, WebSocket> getWebSocketMap();

	//点对点发送
	boolean sendToSingle(WebSocket webSocket, Talk message);

	//群发消息
	void sendToAll(String message);


}
