package com.rezy.dialog.web.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @ClassName: SwaggerConfig
 * @Description: Swagger配置
 * @Version: V1.0
 * @Author: jun.li
 * @Date: 2019年03月26
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Value("${swagger.enable:false}")
	private boolean enableSwagger;

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select()
				// 自行修改为自己的包路径
				.apis(RequestHandlerSelectors.basePackage("com.rezy.dialog.web.controller")).paths(PathSelectors.any())
				.build().enable(enableSwagger);
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("api文档").description("restful 风格接口").version("1.0").build();
	}
}
