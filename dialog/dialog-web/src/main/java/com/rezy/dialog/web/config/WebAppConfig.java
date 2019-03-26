package com.rezy.dialog.web.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @ClassName: WebAppConfig
 * @Description: 全局拦截配置
 * @Version: V1.0
 * @Author: jun.li
 * @Date: 2019年03月19
 */
@Configuration
public class WebAppConfig implements WebMvcConfigurer {

	/**
	 * @param registry 配置静态资源放行
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {

		// 添加登录处理拦截器，拦截所有请求
		InterceptorRegistration interceptorRegistration = registry.addInterceptor(new LoginInterceptor());

		// 排除swagger配置
		interceptorRegistration.excludePathPatterns("/swagger-resources/**");
		interceptorRegistration.excludePathPatterns("/webjars/**");
		interceptorRegistration.excludePathPatterns("/swagger-ui.html/**");
		// 排除druid拦截
		interceptorRegistration.excludePathPatterns("/druid/**");

		// 配置拦截策略
		interceptorRegistration.addPathPatterns("/**");
	}

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		// 允许跨域
		registry.addMapping("/**").allowedOrigins("*").allowedMethods("POST", "GET", "PUT", "OPTIONS", "DELETE")
				.maxAge(3600).allowCredentials(true);

	}
}
