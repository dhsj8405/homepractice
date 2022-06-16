//package com.douzone.jwt.Auth;
//
//import java.io.Serializable;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.function.Function;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Component;
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//
////토큰 관련 설정을 담당하는 클래스
////토큰을 발급해주고, 자격증명을 관리
//
////@Component: 개발자가 직접 컨트롤이 가능한 class들의 경우에 빈등록
////@Bean : 개발자가 컨트롤이 불가능한 외부라이브러리(ex:레디스)들을 Bean으로 등록하고싶은경우 사용
//@Component
//public class JwtTokenUtil implements Serializable {
//    private static final long serialVersionUID = -2550185165626007488L;
//    public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;
//    @Value("${jwt.secret}")
//    private String secret;
//
//    //claim : Token에 담을 정보
//    //issuer : Token 발급자
//    //subject : Toekn 제목
//    //issuedate : Token 발급 시간
//    //expiration : Token 만료 시간
//    
//    //retrieve username from jwt token
//    // jwt token으로부터 username을 획득
//    public String getUsernameFromToken(String token) {
//    	System.out.println("jwt token으로부터 username을 획득");
//        return getClaimFromToken(token, Claims::getSubject);
//    }
//
//    //retrieve expiration date from jwt token
//    // jwt token으로부터 만료일자를 알려준다.
//    public Date getExpirationDateFromToken(String token) {
//    	System.out.println("jwt token으로부터 만료일자를 알려준다.");
//        return getClaimFromToken(token, Claims::getExpiration);
//    } 
//
//    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
//        final Claims claims = getAllClaimsFromToken(token);
//        System.out.println("getClaimFromToken");
//        return claimsResolver.apply(claims);
//    }
//
//    //for retrieveing any information from token we will need the secret key
//    private Claims getAllClaimsFromToken(String token) {
//    	System.out.println("getAllClaimsFromToken");
//        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
//    }
//
//    //check if the token has expired
//    // 토큰이 만료되었는지 확인
//    private Boolean isTokenExpired(String token) {
//        final Date expiration = getExpirationDateFromToken(token);
//        System.out.println("토큰이 만료되었는지 확인");
//        return expiration.before(new Date());
//    }
//
//    //generate token for user
//    // 유저를 위한 토큰을 발급
//    public String generateToken(UserDetails userDetails) {
//        Map<String, Object> claims = new HashMap<>();
//        System.out.println("유저를 위한 토큰을 발급");
//        return doGenerateToken(claims, userDetails.getUsername());
//    }
// 
//    //while creating the token -
////1. Define  claims of the token, like Issuer, Expiration, Subject, and the ID
////2. Sign the JWT using the HS512 algorithm and secret key.
////3. According to JWS Compact Serialization(https://tools.ietf.org/html/draft-ietf-jose-json-web-signature-41#section-3.1)
////   compaction of the JWT to a URL-safe string
//    private String doGenerateToken(Map<String, Object> claims, String subject) {
//    	System.out.println("doGenerateToken");
//        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
//            //.setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
//            .setExpiration(new Date(System.currentTimeMillis() + 5 * 1000))
//            .signWith(SignatureAlgorithm.HS512, secret).compact();
//    }
//
//    //validate token
//    public Boolean validateToken(String token, UserDetails userDetails) {
//        final String username = getUsernameFromToken(token);
//        System.out.println("validate token");
//        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
//    }
//}