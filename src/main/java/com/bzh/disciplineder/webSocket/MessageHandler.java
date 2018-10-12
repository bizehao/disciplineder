package com.bzh.disciplineder.webSocket;

import com.bzh.disciplineder.model.Talk;
import com.bzh.disciplineder.model.request.RequestMessage;
import com.bzh.disciplineder.service.UserService;
import org.java_websocket.WebSocket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

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
		RequestMessage RM = new RequestMessage(talk.getSender(),talk.getReceiver(),talk.getMessage(),0);
		if(webSocketService.getWebSocketMap().containsKey(talk.getReceiver())){
			WebSocket receiverWebSocket = webSocketService.getWebSocketMap().get(talk.getReceiver());
			boolean b = webSocketService.sendToSingle(receiverWebSocket, talk);
			if(!b){
				RM.setPush(1);
				userService.addMessage(RM);
				return;
			}
		}
		userService.addMessage(RM);

	}

	public void starts(String username,MWebSocketService webSocketService){
		 List<Talk> getmessage = userService.getmessage(username);
		WebSocket webSocket = webSocketService.getWebSocketMap().get(username);
		if(getmessage!=null){
		 	for(Talk ta : getmessage){
		 	if(!webSocket.isClosed()){
		 		webSocketService.sendToSingle(webSocket,ta);
		 		userService.upmespush(ta.getId());
			}
			}
		 }

	}
}
