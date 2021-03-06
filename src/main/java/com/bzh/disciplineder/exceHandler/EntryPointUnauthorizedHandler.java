package com.bzh.disciplineder.exceHandler;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 毕泽浩
 * @Description: 401处理器
 * @time 2018/9/10 15:29
 */
@Component
public class EntryPointUnauthorizedHandler implements AuthenticationEntryPoint {

	/**
	 * 未登录或无权限时触发的操作
	 * 返回  {"code":401,"RequestMessage":"小弟弟，你没有携带 token 或者 token 无效！","data":""}
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @param e
	 * @throws IOException
	 * @throws ServletException
	 */
	@Override
	public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
		//返回json形式的错误信息
		httpServletResponse.setCharacterEncoding("UTF-8");
		httpServletResponse.setContentType("application/json");
		System.out.println("小弟弟，你没有携带 token 或者 token 无效！");
		httpServletResponse.getWriter().println("{\"code\":401,\"RequestMessage\":\"小弟弟，你没有携带 token 或者 token 无效！\",\"data\":null}");
		httpServletResponse.getWriter().flush();
	}

}