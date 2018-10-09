package com.bzh.disciplineder.exceHandler;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 毕泽浩
 * @Description: 403处理器
 * @time 2018/9/10 15:30
 */
@Component
public class MyAccessDeniedHandler implements AccessDeniedHandler {
	@Override
	public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
		//返回json形式的错误信息
		httpServletResponse.setCharacterEncoding("UTF-8");
		httpServletResponse.setContentType("application/json");
		System.out.println("小弟弟，你没有权限访问呀！");
		httpServletResponse.getWriter().println("{\"code\":403,\"RequestMessage\":\"小弟弟，你没有权限访问呀！\",\"data\":null}");
		httpServletResponse.getWriter().flush();
	}
}
