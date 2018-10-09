package com.bzh.disciplineder.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author 毕泽浩
 * @Description:
 * @time 2018/10/4 9:59
 */
@EnableSwagger2 // 启动swagger注解
@Configuration
public class Swagger2Config {
	@Bean
	public Docket createRestApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(apiInfo())
				.select()
				// 指定controller存放的目录路径
				.apis(RequestHandlerSelectors.basePackage("com.bzh.disciplineder.controller"))
				.paths(PathSelectors.any())
				.build();
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("disciplineder后台接口文档")
				.description("用于app端和pc端的接口描述，参数类型等")
				.termsOfServiceUrl("http://hg22236941.51mypc.cn:49270")
				//.contact(new Contact("Mr_初晨", "https://gitee.com/beany/mySpringBoot", null)) //项目地址
				.version("1.0")
				.build();
	}
}
