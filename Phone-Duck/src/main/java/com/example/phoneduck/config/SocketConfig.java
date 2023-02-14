package com.example.phoneduck.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import com.example.phoneduck.ws.ChannelStateSocketHandlar;

@Configuration
@EnableWebSocket
public class SocketConfig implements WebSocketConfigurer {

    @Autowired
    private ChannelStateSocketHandlar channelStateSocketHandler;

    private final static String SOCKET_PREFIX = "/sub";

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(channelStateSocketHandler, SOCKET_PREFIX + "/channel/");
    }
}

