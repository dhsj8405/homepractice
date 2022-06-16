package com.douzone.stomp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;


@Configuration
@EnableWebSocketMessageBroker//Stomp를 사용하기위해 선언하는 어노테이션
public class SocketConfig implements WebSocketMessageBrokerConfigurer {
	@Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // handshake가 될 endpoint 지정
		registry.addEndpoint("/stomp").withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // prefix에 따라 해당되는 메시지 핸들러로 분기
    	
    	//pub
    	// prefix가 /app일 때 SimpAnnotationMethod로 요청이 흘러감
    	// ex) 클라이언트가 메시지 보낼 때
    	// ex) stomp.send("/sub/chat/room/",function(){})
    	registry.setApplicationDestinationPrefixes("/app");

    	//sub
    	// prefix가 /topic일 때 브로커에 직접 접근 : 브로커가 직접 받아서 subscriber들을 관리
    	// ex)클라이언트가 메시지 받을 때				
    	// ex)stomp.subscribe("/sub/chat/room/",function(){})
        registry.enableSimpleBroker("/topic");
    }
}
