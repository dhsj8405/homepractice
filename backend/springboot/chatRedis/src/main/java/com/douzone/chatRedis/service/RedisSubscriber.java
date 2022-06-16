package com.douzone.chatRedis.service;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import com.douzone.chatRedis.dto.ChatMessageDto;
import com.fasterxml.jackson.databind.ObjectMapper;

/*
 * 레디스 구독 서비스
 * 
 * redis에 메시지 발행이 될 때까지 대기하다가 메시지가 발행되면 해당 메시지를 읽어 처리하는 리스너
 * MessageListener를 상속받아 onMessage 메서드에서 입맛대로 작성하기
 */

@Service
public class RedisSubscriber implements MessageListener {

	
    private final ObjectMapper objectMapper;
    
    // redis에 보내는 템플릿
    private final RedisTemplate redisTemplate;
   
    // client에 보내는 템플릿
    private final SimpMessagingTemplate messagingTemplate;

	public RedisSubscriber(ObjectMapper objectMapper, RedisTemplate redisTemplate,
			SimpMessagingTemplate messagingTemplate) {
		super(); 
		this.objectMapper = objectMapper;
		this.redisTemplate = redisTemplate;
		this.messagingTemplate = messagingTemplate;
	}
    
    @Override
    public void onMessage(Message message, byte[] pattern) {
        try{
        	System.out.println("++++++++++Redis sub+++++++++++");
        	// redis에서 발행된 데이터 받아서 deserialize
            String pubMsg = (String) redisTemplate.getStringSerializer().deserialize(message.getBody());
            // chatMessageDto 객체로 매핑
            ChatMessageDto msgDto = objectMapper.readValue(pubMsg,ChatMessageDto.class);
            System.out.println(pubMsg);
            System.out.println(msgDto);
            System.out.println(msgDto.getChatRoomNo());
            
            // 웹소켓 구독자한테 메시지 보내기
            messagingTemplate.convertAndSend("/topic/chat/room/"+msgDto.getChatRoomNo(), msgDto);
            System.out.println("++++++++++++++++++++++++++++++");
            
//            switch(ChatMessageDto.getMsgType()){
//                case "chat":
//                    messagingTemplate.convertAndSend("/sub/chat/room/"+chatDTO.getRoomId(),chatDTO);
//                    break;
//                case "createRoom":
//                    messagingTemplate.convertAndSend("/sub/room/create",chatDTO);
//                    break;
//                case "messageAll":
//                    messagingTemplate.convertAndSend("/sub/chat/all",chatDTO);
//                    break;
//            }


        }catch(Exception e){
        	System.out.println(e.getMessage());
        }
    }
}