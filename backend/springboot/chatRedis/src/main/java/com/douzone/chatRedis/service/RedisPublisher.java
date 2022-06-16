package com.douzone.chatRedis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Service;

import com.douzone.chatRedis.dto.ChatMessageDto;


/*
 * 레디스발행서비스
 * 
 * 채팅방에 입장해서 메시지를 작성하면 해당 메시지를 Redis Topic에 발행하는 서비스
 * 이 서비스를 통해 메시지를 발행하면 대기하고 있던 redis 구독 서비스가 메시지를 처리 
 */
@Service
public class RedisPublisher {
	@Autowired
    private RedisTemplate<String,Object> redisTemplate;

    public void publish(ChannelTopic topic, ChatMessageDto message){
    	System.out.println("+++++++++++++Redis Pub++++++++++++");
    	System.out.println(topic.getTopic());
    	System.out.println(message);
        redisTemplate.convertAndSend(topic.getTopic(),message);
        System.out.println("++++++++++++++++++++++++++++++++++");
    }
}
