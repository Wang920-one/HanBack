package com.bishe.back.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

//websocket配置类
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    //添加这个Endpoints，可以在网页通过websocket连接上服务
    //配置websocket的服务地址，并且可以指定是否使用websocket
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        //1、将ws/ep路径注册为stomp端点，用户连接后就可以进行通讯，支持socketJS
        //2、setAllowedOrigins("*")；允许跨域
        //3、withSockJS():支持socketJS访问
        registry.addEndpoint("/ws/ep").setAllowedOrigins("*").withSockJS();
    }

    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {

    }

    //配置消息代理
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        //配置代理域，可以配置多个，配置代理目的地前缀为/queue，可以在配置域上向客户端推送消息
        registry.enableSimpleBroker("/queue");
    }
}
