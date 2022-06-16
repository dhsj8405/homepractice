package com.douzone.chatRedis.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.douzone.chatRedis.service.ChatService;
import com.douzone.chatRedis.service.UserService;
import com.douzone.chatRedis.vo.UserVo;

@RestController
public class UserController {

	@Autowired
	private UserService userService;
	
    
	@PostMapping("/user/userInfo")
	  public UserVo getUserInfo(@RequestBody UserVo userVo){
		System.out.println(userVo.getId());
		System.out.println("z");
//		UserVo a = userService.getUserInfo(userVo);
//		System.out.println(a.getName());
	  	return userService.getUserInfo(userVo);
	  }
	
//    @PostMapping("/user/login")
//    public Long inviteChat(@RequestBody UserVo userVo){
//    	System.out.println(userVo.getId());
//    	userService.checkIdPwd(userVo);
//    	return null;
//    }
//    
}