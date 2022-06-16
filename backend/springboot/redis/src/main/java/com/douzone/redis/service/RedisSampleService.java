package com.douzone.redis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

@Service
public class RedisSampleService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    //get
    public void getRedisStringValue(String key){
        //레디스 저장소
        ValueOperations<String, String> valueOperations = stringRedisTemplate.opsForValue();
        System.out.println("get!! redis key : "+key);
        System.out.println("get!! redis value : " + valueOperations.get(key));
    }

    //set
    public void setRedisStringValue(String key,String value){
        ValueOperations<String, String> valueOperations = stringRedisTemplate.opsForValue();
        //key-value 형태로 저장소에 저장하기
        valueOperations.set(key, value);
        System.out.println("set!! redis key : "+key);
        System.out.println("set!! redis value : " + valueOperations.get(key));
    }
}
