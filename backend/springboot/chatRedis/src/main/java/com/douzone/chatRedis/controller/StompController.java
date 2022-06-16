package com.douzone.chatRedis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.douzone.chatRedis.dto.ChatMessageDto;
import com.douzone.chatRedis.service.ChatService;
import com.douzone.chatRedis.service.RedisPublisher;
import com.douzone.chatRedis.service.RedisSubscriber;

@Controller
public class StompController {

	@Autowired
	private ChatService chatService;
	
	@Autowired
	private RedisPublisher redisPublisher;
	
	@Autowired
	private RedisMessageListenerContainer redisMessageListener;

	@Autowired
	private RedisSubscriber redisSubscriber;
	
	@Autowired
	private SimpMessagingTemplate simpMessagingTemplate;
	//app or pub
	@MessageMapping("/chat/enter")
	public void enter(ChatMessageDto roomInfo) {
		System.out.println("zzzz");
//		System.out.println(roomInfo.getRoomId());
//		System.out.println(roomInfo.getRoomName());
//		System.out.println(roomInfo.getUserNo());
//		System.out.println(roomInfo.getInputMessage());
//		roomInfo.setName(roomInfo.getName() +"채팅방이 생성되었습니다.");
		System.out.println(roomInfo.getNo());
		System.out.println(roomInfo.getMessage());
		System.out.println(roomInfo.getChatRoomNo());
		roomInfo.setMessage(roomInfo.getChatRoomNo()+"번 채팅방에 입장했습니다.");
		//테스트용 가라번호삽입
//		roomInfo.setNo(1L);
//		simpMessagingTemplate.convertAndSend("/topic/chat/room/"+roomInfo.getChatRoomNo(),roomInfo);
		
		ChannelTopic topic = new ChannelTopic(Integer.toString(roomInfo.getChatRoomNo()));
	    redisMessageListener.addMessageListener(redisSubscriber,topic);
	}
	
	 @MessageMapping( "/chat/message")
	    public void message(ChatMessageDto message){

			System.out.println(message.getMessage());
			System.out.println(message.getSendUserId());
	    	
			chatService.addMessage(message);
			
			//레디스 x 기본 메시지 템플릿
//			simpMessagingTemplate.convertAndSend("/topic/chat/room/"+message.getChatRoomNo(), message);
			
			//레디스
			ChannelTopic topic = new ChannelTopic(String.valueOf(message.getChatRoomNo()));
	        redisPublisher.publish(topic,message);
	    }
	
}