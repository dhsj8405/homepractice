package com.douzone.jwt.Auth;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

// 기본적인 웹 보안을 활성화 하겠다라는 의미
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
//	@Autowired
//	private final JwtTokenProvider jwtTokenProvider;

	    @Override 
	    protected void configure(HttpSecurity http) throws Exception {

	    	http
	    			.authorizeRequests()							// HttpServletRequest를 사용하는 요청들에 대한 접근제한을 설정하겠다
	    			.antMatchers("/login/auth").permitAll()	// url에 대한 요청은 인증없이 접근 허용 
                    .anyRequest().authenticated();					// 나머지 요청은 모두 인증받아야함
//	                .httpBasic().disable()
//	                .cors().configurationSource(corsConfigurationSource())
////	                .headers().frameOptions().disable()
//	                .and()
//	                    .csrf().disable()
//	                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//	                .and()
//	                    .authorizeRequests()
//	                        .antMatchers("/api/v1/shops/**").hasRole("OWNER")
//	                        .antMatchers(HttpMethod.PUT, "/api/v1/users/**").hasAnyRole("USER", "OWNER")
//	                        .antMatchers(HttpMethod.DELETE, "/api/v1/users/**").hasAnyRole("USER", "OWNER")
//	                        .antMatchers(HttpMethod.GET, "/api/v1/users/").hasAnyRole("USER", "OWNER")
//	                        .anyRequest().permitAll()
//	                .and()
//	                    .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider),
//	                            UsernamePasswordAuthenticationFilter.class);

//	        http
	          /*... 중략 ...*/
//	            .authorizeRequests()
//	            .requestMatchers(CorsUtils::isPreFlightRequest).permitAll() // - (1)
	            /* 중략 */
//	            .anyRequest().authenticated().and()
//	            .cors().and(); // - (2)	
	        
	    }
	    
	    // CORS 허용 적용
//	    @Bean
//	    public CorsConfigurationSource corsConfigurationSource() {
//	        CorsConfiguration configuration = new CorsConfiguration();
//	        // - (3)
//			System.out.println("[WebSecurity.java] CORS 필터 동작");
//	        configuration.addAllowedOrigin("http://localhost:3000");
////	        configuration.addAllowedMethod("*");
//	        configuration.setAllowedMethods(Arrays.asList("HEAD", "GET", "POST", "PUT", "DELETE"));
//	        configuration.addAllowedHeader("*");
//	        configuration.setAllowCredentials(true);// 클라이언트에서 쿠키 받기 위함
//	        configuration.setMaxAge(3600L); 
//	        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//	        source.registerCorsConfiguration("/**", configuration);
//	        return source;
//	    }
}
