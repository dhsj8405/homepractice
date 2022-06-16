package com.douzone.jwt.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
	
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
				//JWT로그인을 위해 클라이언트에서 X-AUTH-TOKEN헤더에 에 접근할 수 있도록
//				.exposedHeaders("X-AUTH-TOKEN")
				//클라이언트에서 쿠키를 받기 위해
				.allowCredentials(true)
				.allowedOrigins("http://localhost:3000");
	}
}
