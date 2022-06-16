package com.douzone.chat;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.douzone.chat.dto.ChatMessageDto;
import com.douzone.chat.dto.ChatRoomDto;
import com.douzone.chat.service.ChatService;

@Controller
public class StompController {

	@Autowired
	private ChatService chatService;
	
	
	@Autowired
	private SimpMessagingTemplate simpMessagingTemplate;

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
		simpMessagingTemplate.convertAndSend("/topic/chat/room/"+roomInfo.getChatRoomNo(),roomInfo);
	}
	
	 @MessageMapping( "/chat/message")
	    public void message(ChatMessageDto message){

			System.out.println(message.getMessage());
			System.out.println(message.getSendUserId());
	    	
			chatService.addMessage(message);
			
			//이걸로 <<<MESSAGE명령어 적힌 프레임 확인할수있음
			simpMessagingTemplate.convertAndSend("/topic/chat/room/"+message.getChatRoomNo(), message);
	    }
	
}