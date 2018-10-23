package com.bzh.disciplineder.webSocket;

import com.bzh.disciplineder.model.Talk;
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
public class MessageHandlerImpl implements MessageHandler {

	@Autowired
	private UserService userService;

	@Autowired
	private WebSocketService webSocketService;


	@Override
	public void transferPush(Talk talk) {
		if (webSocketService.getWebSocketMap().containsKey(talk.getReceiver())) {
			WebSocket receiverWebSocket = webSocketService.getWebSocketMap().get(talk.getReceiver());
			boolean sendSign = webSocketService.sendToSingle(receiverWebSocket, talk);
			if (sendSign) {
				talk.setPush(1);
			}
		}
		userService.addMessage(talk);
	}

	@Override
	public void activePush(String username) {
		List<Talk> getmessage = userService.getmessage(username);
		WebSocket webSocket = webSocketService.getWebSocketMap().get(username);
		if (getmessage != null) {
			for (Talk ta : getmessage) {
				if (webSocket.isOpen()) {
					ta.setCode("200");
					ta.setReceiver(username);
					webSocketService.sendToSingle(webSocket, ta);
					userService.upmespush(ta.getId());
				}
			}
		}
	}
}
