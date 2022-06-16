package com.douzone.redis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.douzone.redis.service.RedisSampleService;

@RestController
public class MainController {
    @Autowired
    private RedisSampleService redisSampleService;

    @GetMapping("/")
    public ResponseEntity main() {
        return new ResponseEntity<>(1, HttpStatus.OK);
    }

    //get
    @PostMapping(value = "/getRedis")
    public void getRedisStringValue(String key){
        System.out.println(key);
        redisSampleService.getRedisStringValue(key);
    }

    //set
    @PostMapping(value = "/setRedis")
    public void setRedisStringValue(String key, String value){
        System.out.println(key);
        System.out.println(value);
        redisSampleService.setRedisStringValue(key, value);
    }

}