package com.bzh.disciplineder.controller;

import com.bzh.disciplineder.model.Talk;
import com.bzh.disciplineder.service.UserService;
import com.bzh.disciplineder.webSocket.WebSocketService;
import com.bzh.disciplineder.webSocket.MessageHandlerImpl;
import lombok.extern.slf4j.Slf4j;
import org.java_websocket.WebSocket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author 段
 * @Description:
 * @time 2018/10/11 16：46
 */
@Slf4j
@RestController
@RequestMapping("message")
public class MessageController {
	//我试一试
	private final UserService userService;

	private final WebSocketService webSocketService;

	private MessageHandlerImpl MH;

	@Autowired
	public MessageController(UserService userService, WebSocketService webSocketService, MessageHandlerImpl MH) {
		this.userService = userService;
		this.webSocketService = webSocketService;
		this.MH = MH;
	}

	/**
	 * 群发消息内容
	 *
	 * @param RequestMessage
	 * @return
	 */
	@RequestMapping(value = "/sendAll", method = RequestMethod.GET)
	String sendAllMessage(@RequestParam(required = true) String RequestMessage) {
		webSocketService.sendToAll(RequestMessage);
		return "ok";
	}

	/**
	 * 指定会话ID发消息
	 *
	 * @param message 消息内容
	 * @return
	 */
	@RequestMapping(value = "/sendOne", method = RequestMethod.POST)
	String sendOneMessage(String sender, String receiver, String message) { //@RequestParam(required = true) String RequestMessage, @RequestParam(required = true) String id
		Talk talk = new Talk();
		talk.setCode("200");
		talk.setSender(sender);
		talk.setReceiver(receiver);
		talk.setMessage(message);
		talk.setTime(new Date());
		WebSocket webSocket = webSocketService.getWebSocketByUsername(receiver);
		webSocketService.sendToSingle(webSocket, talk);
		return "ok";
	}


}