package com.bzh.disciplineder.config;

import com.bzh.disciplineder.webSocket.MWebSocketService;
import org.java_websocket.server.WebSocketServer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.InetSocketAddress;
import java.net.UnknownHostException;

/**
 * @author 毕泽浩
 * @Description:
 * @time 2018/9/27 14:42
 */
@Configuration
public class WebSocketConfig {

	/*@Bean
	public ServerEndpointExporter serverEndpointExporter(){
		return new ServerEndpointExporter();
	}*/

	@Bean
	public MWebSocketService mWebSocketService() throws UnknownHostException {
		return new MWebSocketService(8080);
	}

}
