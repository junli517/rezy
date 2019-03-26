package com.rezy.dialog.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.yeauty.standard.ServerEndpointExporter;

/**
 * @ClassName: WebSocketConfig
 * @Description: (描述)
 * @author: Administrator
 * @date: 2019年3月19日 上午2:36:07 
 */

@Configuration
public class WebSocketConfig {
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }
}
