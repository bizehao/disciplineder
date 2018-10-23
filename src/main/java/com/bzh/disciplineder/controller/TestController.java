package com.bzh.disciplineder.controller;

import com.bzh.disciplineder.service.UserService;
import com.bzh.disciplineder.webSocket.WebSocketService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author 毕泽浩
 * @Description:
 * @time 2018/9/11 15:06
 */
@Slf4j
@RestController
@RequestMapping("test")
public class TestController {
	//我试一试
	private final UserService userService;

	private final WebSocketService webSocketService;

	@Autowired
	public TestController(UserService userService, WebSocketService webSocketService) {
		this.userService = userService;
		this.webSocketService = webSocketService;
	}

	/**
	 * 群发消息内容
	 * @param RequestMessage
	 * @return
	 * */
	@RequestMapping(value="/sendAll", method=RequestMethod.GET)
	String sendAllMessage(@RequestParam(required=true) String RequestMessage){
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
		return null;
	}
	@RequestMapping(value = "/send")
	String sendOneMessage(String username){

		return null;
	}


}