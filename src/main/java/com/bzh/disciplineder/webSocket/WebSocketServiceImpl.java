package com.bzh.disciplineder.webSocket;

import com.bzh.disciplineder.model.Talk;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.util.*;

/**
 * @author 毕泽浩
 * @Description:
 * @time 2018/9/27 16:42
 */
@Component
public class WebSocketServiceImpl extends WebSocketServer implements WebSocketService {

	@Autowired
	MessageHandler messageHandler;

	private static final Map<String, WebSocket> webSocketMap = new LinkedHashMap<>();
	private static final Map<WebSocket, String> webSocketMapInverted = new LinkedHashMap<>(); //用户websocket的反向存储
	private static Gson gson;

	public WebSocketServiceImpl() throws UnknownHostException {
		super(new InetSocketAddress(8080));
	}

	public WebSocketServiceImpl(InetSocketAddress address) {
		super(address);
	}

	@Override
	public void onStart() {
		System.out.println("开始执行WebSocket");
		gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
	}

	@Override
	public void onOpen(WebSocket webSocket, ClientHandshake clientHandshake) {
		String address = webSocket.getRemoteSocketAddress().getAddress().getHostAddress();
		String message = String.format("(%s) <进入房间！>", address);
		System.out.println(message);
	}


	@Override
	public void onClose(WebSocket webSocket, int i, String s, boolean b) {
		String address = webSocket.getRemoteSocketAddress().getAddress().getHostAddress();
		String message = String.format("(%s) <退出房间！>", address);
		System.out.println(message);
		String wsKey = webSocketMapInverted.get(webSocket);
		webSocketMap.remove(wsKey);
		webSocketMapInverted.remove(webSocket);
	}

	@Override
	public void onMessage(WebSocket webSocket, String s) {
		System.out.println(s);
		Talk talk = gson.fromJson(s, Talk.class);
		switch (talk.getCode()) {
			case "100": //首次连接
				webSocketMap.put(talk.getSender(), webSocket);
				webSocketMapInverted.put(webSocket, talk.getSender());
				messageHandler.activePush(talk.getSender());
				break;
			case "200": //聊天
				messageHandler.transferPush(talk);
				break;
		}
	}

	@Override
	public void onError(WebSocket webSocket, Exception e) {
		if (null != webSocket) {
			webSocket.close(0);
		}
		e.printStackTrace();
	}

	//获取整个用户的websocket集合
	@Override
	public Map<String, WebSocket> getWebSocketMap() {
		return webSocketMap;
	}

	//根据用户名获取用户的websocket
	@Override
	public WebSocket getWebSocketByUsername(String username) {
		return webSocketMap.get(username);
	}

	//给所有用户发消息
	@Override
	public void sendToAll(String message) {
		// 获取所有连接的客户端
		Collection<WebSocket> connections = getConnections();
		//将消息发送给每一个客户端
		for (WebSocket client : connections) {
			int nameIndex = message.indexOf(":");
			String name = message.substring(0,nameIndex);
			Talk talk = new Talk();
			talk.setId(UUID.randomUUID().getLeastSignificantBits());
			talk.setCode("200");
			talk.setSender(name);
			talk.setReceiver("lisi");
			talk.setMessage(message);
			talk.setTime(new Date());
			String msg = gson.toJson(talk,Talk.class);
			client.send(msg);
		}
	}

	//给某个用户发消息
	@Override
	public boolean sendToSingle(WebSocket webSocket, Talk message) {
		String mess = gson.toJson(message);
		if (webSocket.isOpen()) {
			webSocket.send(mess);
			return true;
		} else {
			return false;
		}
	}
}
