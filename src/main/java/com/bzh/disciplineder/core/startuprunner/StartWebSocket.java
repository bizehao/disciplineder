package com.bzh.disciplineder.core.startuprunner;

import com.bzh.disciplineder.webSocket.MWebSocketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author 毕泽浩
 * @Description:
 * @time 2018/9/27 16:56
 */
@Component
@Order(value = 1)
public class StartWebSocket implements ApplicationRunner {

	private final MWebSocketService socketServer;

	@Autowired
	public StartWebSocket(MWebSocketService socketServer) {
		this.socketServer = socketServer;
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		socketServer.start();
		try {
			String ip = InetAddress.getLocalHost().getHostAddress();
			int port = socketServer.getPort();
			System.out.println(String.format("服务已启动: %s:%d", ip, port));
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}

		InputStreamReader in = new InputStreamReader(System.in);
		BufferedReader reader = new BufferedReader(in);

		while (true) {
			try {
				String msg = reader.readLine();
				socketServer.sendToAll(msg);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
}
