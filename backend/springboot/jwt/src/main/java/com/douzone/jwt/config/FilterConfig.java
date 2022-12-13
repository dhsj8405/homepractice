package com.douzone.jwt.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.douzone.jwt.filter.CorsFilter;

@Configuration	
public class FilterConfig {

	@Bean
	public FilterRegistrationBean<CorsFilter> corsFilter(){
		System.out.println("[FilterConfig.java] CORS 필터 등록");
		FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<>(new CorsFilter());
		bean.addUrlPatterns("/main/*");
		bean.addUrlPatterns("/login/*");		
		bean.setOrder(0); // 낮은 번호부터 실행됨.
		return bean;
	}
	  
}   

