package com.bzh.disciplineder.webSocket;

import com.bzh.disciplineder.model.Talk;
import com.bzh.disciplineder.model.request.RequestMessage;
import com.bzh.disciplineder.service.UserService;
import org.java_websocket.WebSocket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author 毕泽浩
 * @Description: 消息处理
 * @time 2018/10/1 14:42
 */
@Component
public class MessageHandler {

	private final UserService userService;

	@Autowired
	public MessageHandler(UserService userService) {
		this.userService = userService;
	}

	public void start(Talk talk,MWebSocketService webSocketService){
		int sender = Integer.parseInt(talk.getSender());
		int receiver = Integer.parseInt(talk.getReceiver());
		String message = talk.getMessage();
		RequestMessage requestMessage = new RequestMessage(receiver,sender,message);
		userService.addMessage(requestMessage);
		if(webSocketService.getWebSocketMap().containsKey(receiver)){
			WebSocket receiverWebSocket = webSocketService.getWebSocketMap().get(receiver);
			webSocketService.sendToSingle(receiverWebSocket, message);
		}
	}
}
