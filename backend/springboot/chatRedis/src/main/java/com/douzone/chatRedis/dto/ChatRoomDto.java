package com.douzone.chatRedis.dto;

import java.util.List;

import com.douzone.chatRedis.vo.UserVo;

public class ChatRoomDto {
	private Long no;
	private String name;
	//private List<UserVo> userList;	// 채팅방 생성하면서 누구누구참여했는지 메시지용
//	private String inputMessage;	// 채팅 방 생성 후 전송할 메시지
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
		

}
