package com.douzone.jwt.config;

import java.time.Duration;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenProvider {
//		
	private int expTime = 604800000; 	
	private String secretKey = "secretKey";

	public String generateToken() {
		
		System.out.println("[JwtTokenProvider.java] JWT 토큰 생성");
	    Date now = new Date();
	    Date expDate = new Date(now.getTime() + expTime); 
	    return Jwts.builder()
	        .setHeaderParam(Header.TYPE, Header.JWT_TYPE) 			// 헤더 타입 지정 : JWT 헤더타입  
	        .setIssuer("fresh") 									// 등록된 클레임 중, 토큰 발급자 설정
	        .setIssuedAt(now) 										// 등록된 클레임 중, 발급 시간(iat) 설정 : Date 타입만 추가 가능	
	        .setExpiration(expDate) 								// 등록된 클레임 중, 만료 시간(exp) 설정 : Date 타입만 추가 가능
	        .claim("id", "아이디")									// 비공개 클레임을 설정할 수 있습니다. (키,값)
	        .claim("email", "ajufresh@gmail.com")					
	        .signWith(SignatureAlgorithm.HS256, secretKey) 		// 해싱 알고리즘, 시크릿 키를 설정
	        .compact();												// JWT 토큰 만들기
	  }
}
