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
	@PostMapping("/auth")
	  public ResponseEntity getUserInfo(@RequestBody UserVo userVo){
		System.out.println("@@@@@@@@@@@");
		System.out.println(userVo.getId());
		JwtTokenProvider jwtTokenProvider = new JwtTokenProvider (); 
		String token = jwtTokenProvider.generateToken();
		  return ResponseEntity.ok(token);
//		UserVo a = userService.getUserInfo(userVo);
//		System.out.println(a.getName());
//	  	return token;
	  }

//	@GetMapping("/login/auth")
//	  public UserVo getUserInfo(){
//		System.out.println("액시오스테스트");
//		
////		UserVo a = userService.getUserInfo(userVo);
////		System.out.println(a.getName());
//	  	return null;
//	  }
	
}
