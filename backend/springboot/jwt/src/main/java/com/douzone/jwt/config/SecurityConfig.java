package com.douzone.jwt.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.douzone.jwt.filter.JwtAuthenticationFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
 
    private final JwtTokenProvider jwtTokenProvider;
 
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable()			//rest api이므로 basic auth 보안 사용x
                .cors().configurationSource(corsConfigurationSource())	// CorsConfigurationSource 를 cors 정책의 설정파일 등록
                .and()
                .csrf().disable()				//rest api이므로 csrf 보안 사용x
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)		//JWT를 사용하기 때문에 세션을 사용x
                .and()
                .authorizeRequests()
                .antMatchers("/login/login").permitAll()		//해당 API에 대해서는 모든 요청을 허가
                .antMatchers("/login/auth").hasRole("USER")		//USER 권한이 있어야 요청가능
                .anyRequest().authenticated()					//이 밖에 모든 요청에 대해서 인증 필요 설정
                .and()
                .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class);	//JWT 인증을 위하여 직접 구현한 필터를 UsernamePasswordAuthenticationFilter 전에 실행
        
        return http.build();
    }
 
    @Bean
    public PasswordEncoder passwordEncoder() {//JWT를 사용하기 위해서는 기본적으로 password encoder가 필요한데, 여기서는 Bycrypt encoder를 사용
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
    // CORS 허용 적용
    @Bean 
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        configuration.addAllowedOrigin("*");
        configuration.addAllowedHeader("*");
        configuration.addAllowedMethod("*");
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}