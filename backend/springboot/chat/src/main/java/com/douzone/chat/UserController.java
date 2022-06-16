package com.douzone.chat;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.douzone.chat.service.ChatService;
import com.douzone.chat.service.UserService;
import com.douzone.chat.vo.UserVo;

@RestController
public class UserController {

	@Autowired
	private UserService userService;
	
    
//    @PostMapping("/user/login")
//    public Long inviteChat(@RequestBody UserVo userVo){
//    	System.out.println(userVo.getId());
//    	userService.checkIdPwd(userVo);
//    	return null;
//    }
//    
}