package com.douzone.chatRedis.config;

import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
	//
	// Message Converter
	//
//	@Bean
//	public StringHttpMessageConverter stringHttpMessageConverter() {
//		StringHttpMessageConverter messageConverter = new StringHttpMessageConverter();
//		messageConverter.setSupportedMediaTypes(Arrays.asList(new MediaType("text", "html", Charset.forName("utf-8"))));
//		return messageConverter;
//	}
//
//	@Bean
//	public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
//		Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder().indentOutput(true)
//				.dateFormat(new SimpleDateFormat("yyyy-mm-dd"));
//
//		MappingJackson2HttpMessageConverter messageConverter = new MappingJackson2HttpMessageConverter(builder.build());
//		messageConverter
//				.setSupportedMediaTypes(Arrays.asList(new MediaType("application", "json", Charset.forName("utf-8"))));
//		return messageConverter;
//	}
//
//	@Override
//	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
//		converters.add(stringHttpMessageConverter());
//		converters.add(mappingJackson2HttpMessageConverter());
//
//	}
}
