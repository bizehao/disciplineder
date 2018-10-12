package com.bzh.disciplineder.webSocket;

import com.bzh.disciplineder.model.Talk;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author 毕泽浩
 * @Description:
 * @time 2018/9/27 16:42
 */
public class MWebSocketService extends WebSocketServer {

	@Autowired
	private MessageHandler messageHandler;

	private static final Map<String, WebSocket> webSocketMap = new LinkedHashMap<>();

	private static Gson gson;

	public MWebSocketService(int port) throws UnknownHostException {
		super(new InetSocketAddress(port));
	}

	public MWebSocketService(InetSocketAddress address) {
		super(address);
	}

	@Override
	public void onStart() {
		System.out.println("开始执行WebSocket");
		gson = new GsonBuilder().create();
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
		System.out.println("========================================");
		String message = String.format("(%s) <退出房间！>", address);
		System.out.println(webSocket.isClosed());
		//sendToAll(message);
		System.out.println(message);

	}

	@Override
	public void onMessage(WebSocket webSocket, String s) {
		Talk talk = gson.fromJson(s, Talk.class);
		switch (talk.getCode()) {
			case "100": //首次连接
				webSocketMap.put(talk.getSender(), webSocket);
				messageHandler.starts(talk.getSender(),this);
				break;
			case "200": //聊天
				messageHandler.start(talk,this);
				break;
            case "300":
                webSocketMap.remove(talk.getSender());
		}
	}

	private static void print(String msg) {
		System.out.println(String.format("[%d] %s", System.currentTimeMillis(), msg));
	}

	@Override
	public void onError(WebSocket webSocket, Exception e) {
		if (null != webSocket) {
			webSocket.close(0);
		}
		e.printStackTrace();
	}

	public Map<String, WebSocket> getWebSocketMap() {
		return webSocketMap;
	}

	//给所有用户发消息
	public void sendToAll(String message) {
		// 获取所有连接的客户端
		Collection<WebSocket> connections = getConnections();
		//将消息发送给每一个客户端
		for (WebSocket client : connections) {
			client.send(message);
		}
	}

	//给某个用户发消息
	public boolean sendToSingle(WebSocket webSocket,Talk message) {
        String mess = gson.toJson(message);
        if(!webSocket.isClosed()){
		 webSocket.send(mess);
      	return false;
	  }
	  webSocketMap.remove(message.getReceiver());
	  return true;
	}
}
