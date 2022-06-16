package com.douzone.chat.dto;

public class ChatMessageDto {
	private Long no;
    private String message;
    private int chatRoomNo;
    private String sendUserNo;
    private String sendUserId;
    private String sendUserName;
    private String regDate;
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getChatRoomNo() {
		return chatRoomNo;
	}
	public void setChatRoomNo(int chatRoomNo) {
		this.chatRoomNo = chatRoomNo;
	}
	public String getSendUserNo() {
		return sendUserNo;
	}
	public void setSendUserNo(String sendUserNo) {
		this.sendUserNo = sendUserNo;
	}
	public String getSendUserId() {
		return sendUserId;
	}
	public void setSendUserId(String sendUserId) {
		this.sendUserId = sendUserId;
	}
	public String getSendUserName() {
		return sendUserName;
	}
	public void setSendUserName(String sendUserName) {
		this.sendUserName = sendUserName;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	

	
    
    
}
