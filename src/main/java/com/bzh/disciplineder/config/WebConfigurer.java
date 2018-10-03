//package com.bzh.disciplineder.config;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
//
///**
// * @author 毕泽浩
// * @Description:
// * @time 2018/10/3 17:06
// */
//@Configuration
//public class WebConfigurer extends WebMvcConfigurationSupport {
//
//	@Override
//	public void addResourceHandlers(ResourceHandlerRegistry registry) {
//		registry.addResourceHandler("swagger-ui.html")
//				.addResourceLocations("classpath:/META-INF/resources/");
//		registry.addResourceHandler("/webjars/**")
//				.addResourceLocations("classpath:/META-INF/resources/webjars/");
//		registry.addResourceHandler("/favicon.ico")
//				.addResourceLocations("classpath:/META-INF/resources/favicon.ico");
//		super.addResourceHandlers(registry);
//	}
//}
