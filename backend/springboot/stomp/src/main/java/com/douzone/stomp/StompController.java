package com.douzone.stomp;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class StompController {

	@Autowired
	private SimpMessagingTemplate simpMessagingTemplate;

	@MessageMapping("/msg")
	public void showMessage(String a) {
		System.out.println(a);
		
		String msg[] = a.split(":");
		HashMap<String,Object> map = new HashMap<>();
		map.put("메시지", msg[1]);   
		simpMessagingTemplate.convertAndSend("/topic/a",map);
	}
	
}