package com.example.demo.common.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.demo.common.interceptor.LoginCheckInterceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer{
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// TODO Auto-generated method stub
		registry.addInterceptor(new LoginCheckInterceptor())
	    .addPathPatterns("/**")
	    .excludePathPatterns("/member/joinMember","/member/loginForm","/member/login","/member/joinMemberForm","/","/common/**","/image/**","/home/**","/js/**","/css/**");
		// 우선순위는 예외처리가 높다.
	}
	
}