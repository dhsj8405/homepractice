package com.douzone.stomp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@SpringBootConfiguration + @ComponentScan + @EnableAutoConfiguration 
public class StompApplication {

	public static void main(String[] args) {
		SpringApplication.run(StompApplication.class, args);
	}
	
}
