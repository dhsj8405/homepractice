package com.douzone.jwt.controller;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.douzone.jwt.config.JwtTokenProvider;
import com.douzone.jwt.vo.UserVo;
import com.douzone.jwt.config.JwtTokenProvider;
@RestController
@RequestMapping("/login")
public class LoginController {
//
//	@GetMapping("/auth")
//	  public ResponseEntity<String> hello(){
//		return ResponseEntity.ok("hello");
//	}
	@PostMapping("/login")
	  public ResponseEntity getUserInfo(@RequestBody UserVo userVo){
//		JwtTokenProvider jwtTokenProvider = new JwtTokenProvider (); 
//		String token = jwtTokenProvider.generateToken();
//		  return ResponseEntity.ok(token);
		
		
//		UserVo a = userService.getUserInfo(userVo);
//		System.out.println(a.getName());
//	  	return token;
	  }

	@PostMapping("/auth")
	  public UserVo getUserInfo(){
		
//		UserVo a = userService.getUserInfo(userVo);
//		System.out.println(a.getName());
	  	return null;
	  }
	
}
