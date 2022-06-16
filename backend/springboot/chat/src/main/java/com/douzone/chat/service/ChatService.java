package com.douzone.chat.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.chat.dto.ChatMessageDto;
import com.douzone.chat.dto.ChatRoomDto;
import com.douzone.chat.repository.ChatRepository;
import com.douzone.chat.vo.UserVo;

@Service
public class ChatService {
	
	@Autowired
	private ChatRepository chatRepository;
	
	public Long addChatRoom(List<UserVo> list) {
		ChatRoomDto chatRoomDto = new ChatRoomDto();
		Long roomNo = chatRepository.addRoom(chatRoomDto);
		System.out.println(roomNo);
		
		// 채팅에 참가할 유저들을 돌아가면서 위에서 생성된 채팅방 번호랑 같이  join_chat_room에 넣어줌
		for(UserVo userVo: list){
			System.out.println(userVo.getNo());
			Map<String,Object> map = new HashMap<>();
			map.put("userNo", userVo.getNo());
			map.put("chatRoomNo", roomNo);
			chatRepository.addJoinChatRoom(map);
		}
		return roomNo;
	}
	// 가라이이디비번으로 룸리스트가져오기
	public void getRoonList(UserVo userVo) {
		// TODO Auto-generated method stub
		
	}
	public List<ChatMessageDto> getMessageList(int i) {
		return chatRepository.findAllMessage(i); 
	}
	public void addMessage(ChatMessageDto message) {
		chatRepository.insertMessage(message);
	}
	public List<ChatRoomDto> getChatRoomList(String id) {
		return chatRepository.findAllChatRoom(id);
		
	}

}
