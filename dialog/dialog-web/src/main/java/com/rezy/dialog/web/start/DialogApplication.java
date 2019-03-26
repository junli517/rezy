package com.rezy.dialog.web.start;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

/**
 * @ClassName: DialogApplication
 * @Description: 如果需要通过打包的方式在web容器中进行部署,则需要继承SpringBootServletInitializer覆盖configure(SpringApplicationBuilder)方法
 * @Version: V1.0
 * @Author: jun.li
 * @Date: 2019年03月23
 */
@SpringBootApplication
@ComponentScan(basePackages = "com.rezy.dialog")
@MapperScan(basePackages = "com.rezy.dialog.dao")
public class DialogApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(DialogApplication.class, args);
		System.out.println("===========================dialog启动成功===========================");
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(DialogApplication.class);
	}
}
