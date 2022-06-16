package com.douzone.chatRedis.vo;

public class UserVo {
	private Long no;
	private String name;
	private String id;
	private String password;
	private Long chatRoomNo;
	
	
	
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public Long getChatRoomNo() {
		return chatRoomNo;
	}
	public void setChatRoomNo(Long chatRoomNo) {
		this.chatRoomNo = chatRoomNo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
