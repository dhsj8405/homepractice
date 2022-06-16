package com.douzone.redis.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {
	//application.yaml 에 설정한 값을 @Value 어노테이션으로 주입
	@Value("${spring.redis.host}")
	private String host;
	
	@Value("${spring.redis.port}")
	private int port;
	
	//lettuce 라이브러리 사용
	@Bean
	public RedisConnectionFactory redisConnectionFactory() {
	    return new LettuceConnectionFactory(host, port);
	}
	//RedisTemplate 
	@Bean
    public RedisTemplate<String, Object> redisTemplate(){
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        //Redis Connection
        redisTemplate.setConnectionFactory(redisConnectionFactory());
        //Redis Key
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        //Redis value
        redisTemplate.setValueSerializer(new StringRedisSerializer());
        return redisTemplate;
    }	
	// 문자열에 특화된템플릿
	@Bean
    public StringRedisTemplate stringRedisTemplate(){
        StringRedisTemplate stringRedisTemplate = new StringRedisTemplate();
        stringRedisTemplate.setKeySerializer(new StringRedisSerializer());
        stringRedisTemplate.setValueSerializer(new StringRedisSerializer());
        stringRedisTemplate.setConnectionFactory(redisConnectionFactory());
        return stringRedisTemplate;
    }
}
