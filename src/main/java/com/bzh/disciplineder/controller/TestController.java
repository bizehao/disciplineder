package com.bzh.disciplineder.controller;

import com.bzh.disciplineder.service.RedisService;
import com.bzh.disciplineder.service.UserService;
import com.bzh.disciplineder.webSocket.MWebSocketService;
import lombok.extern.slf4j.Slf4j;
import org.java_websocket.WebSocket;
import org.java_websocket.server.WebSocketServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 毕泽浩
 * @Description:
 * @time 2018/9/11 15:06
 */
@Slf4j
@RestController
@RequestMapping("test")
public class TestController {
	//做测试，看是否成功了
	private final UserService userService;

	private final MWebSocketService mWebSocketService;

	@Autowired
	public TestController(UserService userService, MWebSocketService mWebSocketService) {
		this.userService = userService;
		this.mWebSocketService = mWebSocketService;
	}

	/**
	 * 群发消息内容
	 * @param RequestMessage
	 * @return
	 *//*
	@RequestMapping(value="/sendAll", method=RequestMethod.GET)
	String sendAllMessage(@RequestParam(required=true) String RequestMessage){
		try {
			WebSocketServer.BroadCastInfo(RequestMessage);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "ok";
	}

	*/

	/**
	 * 指定会话ID发消息
	 *
	 * @param message 消息内容
	 * @return
	 */
	@RequestMapping(value = "/sendOne", method = RequestMethod.GET)
	String sendOneMessage(String message) { //@RequestParam(required = true) String RequestMessage, @RequestParam(required = true) String id
		Map<String, WebSocket> map = mWebSocketService.getWebSocketMap();
		System.out.println(map.size());
		mWebSocketService.sendToAll(message);
		return "ok";
	}


}