package com.douzone.jwt.config;

import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.douzone.jwt.service.UserService;
import com.douzone.jwt.vo.TokenInfo;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.Builder;

@Builder
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
	//refresh Token 쓸때
    public TokenInfo generateToken(Authentication authentication) {
		// 권한 가져오기
        String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));
 
        long now = (new Date()).getTime();
        // Access Token 생성
        Date accessTokenExpiresIn = new Date(now + 86400000);
        String accessToken = Jwts.builder()
                .setSubject(authentication.getName())
                .claim("auth", authorities)
                .setExpiration(accessTokenExpiresIn)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
 
        // Refresh Token 생성
//        String refreshToken = Jwts.builder()
//                .setExpiration(new Date(now + 86400000))
//                .signWith(SignatureAlgorithm.HS256,secretKey)
//                .compact();
 
        return TokenInfo.builder()
                .grantType("Bearer")
                .accessToken(accessToken)
//                .refreshToken(refreshToken)
                .build();
	  }
    // 토큰 정보를 검증하는 메서드
    public boolean validateToken(String token) {
        try {
            Jwts
            	.parser()
            	.setSigningKey(secretKey)	//시크릿 키를 넣어주어 토큰을 해석
            	.parseClaimsJws(token);		//해석할 토큰을 문자열(String) 형태로
            return true;
        } catch (SecurityException e) {
        	System.out.println("Invalid JWT Token");
//            log.info("Invalid JWT Token", e);
        } catch(MalformedJwtException e){
        	System.out.println("Invalid JWT Token");
//        	log.info("Invalid JWT Token", e);
        } catch (ExpiredJwtException e) {
        	System.out.println("Expired JWT Token");
//            log.info("Expired JWT Token", e);
        } catch (UnsupportedJwtException e) {
        	System.out.println("Unsupported JWT Token");
//            log.info("Unsupported JWT Token", e);
        } catch (IllegalArgumentException e) {
        	System.out.println("JWT claims string is empty");
//            log.info("JWT claims string is empty.", e);
        }
        return false;
    }
    // JWT 토큰을 복호화해서 토큰에 들어있는 정보를 꺼내는 메서드
    public Authentication getAuthentication(String accessToken) {
        // 토큰 복호화
        Claims claims = parseClaims(accessToken);
 
        if (claims.get("auth") == null) {
            throw new RuntimeException("권한 정보가 없는 토큰입니다.");
        }
 
        // 클레임에서 권한 정보 가져오기
        Collection<? extends GrantedAuthority> authorities =
                Arrays.stream(claims.get("auth").toString().split(","))
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());
 
        // UserDetails 객체를 만들어서 Authentication 리턴
        UserDetails principal = new User(claims.getSubject(), "", authorities);
        return new UsernamePasswordAuthenticationToken(principal, "", authorities);
    }
    private Claims parseClaims(String accessToken) {
        try {
            return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(accessToken).getBody();
        } catch (ExpiredJwtException e) {
            return e.getClaims();
        }
    }
}
