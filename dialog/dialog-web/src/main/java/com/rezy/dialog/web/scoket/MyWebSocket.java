package com.rezy.dialog.web.scoket;

import java.io.IOException;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.springframework.stereotype.Component;

import com.google.common.net.HttpHeaders;

/**
 * @ClassName: MyWebSocket
 * @Description: (描述)
 * @author: Administrator
 * @date: 2019年3月19日 上午2:37:43 
 */

@ServerEndpoint(value = "")
@Component
public class MyWebSocket {
    @OnOpen
    public void onOpen(Session session, HttpHeaders headers) throws IOException {
        System.out.println("new connection");
    }

    @OnClose
    public void onClose(Session session) throws IOException {
       System.out.println("one connection closed"); 
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        throwable.printStackTrace();
    }

    @javax.websocket.OnMessage
    public void OnMessage(Session session, String message) {
        System.out.println(message);
        //session.sendText("Hello Netty!");
    }
}
