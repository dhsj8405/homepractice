package com.douzone.jwt.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {
    @GetMapping("/main")
    public String getHome(){
    	System.out.println("main");
        return "일단 cors 완성";
    }
}