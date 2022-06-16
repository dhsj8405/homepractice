package com.douzone.chatRedis.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.douzone.chatRedis.dto.ChatMessageDto;
import com.douzone.chatRedis.dto.ChatRoomDto;
import com.douzone.chatRedis.service.ChatService;
import com.douzone.chatRedis.service.UserService;
import com.douzone.chatRedis.vo.UserVo;

@RestController
public class ChatController {
	
	@Autowired
	private ChatService chatService;
	
	@Autowired
	private UserService userService;
	
    @GetMapping("/main")
    public Map<String, Object> getHome(){
    	
    	System.out.println(userService.getUser());
    	Map<String, Object> map = userService.getUser();
    	
        return map;
    }
    
    @PostMapping("/chat/invite")
    public Long inviteChat(@RequestBody List<UserVo> list){
    	
//    	System.out.println(list);
//    	for(UserVo userVo : list){
//    		System.out.println(userVo.getId());
//    	}
    	
    	Long chatRoomNo = chatService.addChatRoom(list);
    	
    	//초대하면서 바로 채팅방 켜줘야하기때문에 생성된 채팅방 번호 리턴해줌
    	return chatRoomNo;
    }
    
   // 유저아이디로 채팅방 리스트 조회
   @PostMapping("/chat/chatRoomList")
   public Map<String, Object> getChatRoomList(@RequestBody UserVo userVo){
	   System.out.println("로그인 시 방 리스트");
    	List<ChatRoomDto> chatRoomlist = chatService.getChatRoomList(userVo.getId());
    	Map<String, Object> map = new HashMap<>();
    	map.put("list", chatRoomlist);

    	return map;
    }
  // 클릭된  방번호로 채팅 내용 조회
  @GetMapping("/chat/msgList/{no}")
  public Map<String, Object> getMessageList(@PathVariable int no){
  	System.out.println("방 선택시 메시지 리스트 조회");
  	// 가라로 채팅룸번호 1번넣을것임
  	List<ChatMessageDto> list = chatService.getMessageList(no);
	Map<String, Object> map = new HashMap<>();
	map.put("list", list);

  	return map;
  }
  
    
}