package com.tenco.bank.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.tenco.bank.handler.AuthIntercepter;

import lombok.RequiredArgsConstructor;

// 스프링부트 애플리케이션에 등록 - DI 생성해서 낚아 챔
// WebMvcConfigurer 구현을 하면 설정 파일로 일을 할 수 있음(약속임)

@RequiredArgsConstructor
@Configuration  // 1 개 이상의 bean을 등록할 때 설정
public class WebMvcConfig implements WebMvcConfigurer {

	@Autowired // DI(객체를 가지고 와서 씀)
	private final AuthIntercepter authIntercepter;
	
	// 생성자 => @RequiredArgsConstructor (생성자 대신 사용 가능)
//	public WebMvcConfig(AuthIntercepter authIntercepter) {
//		this.authIntercepter = authIntercepter;
//	} 
	
	// 우리가 만들어 놓은 AuthInterceptor를 등록해야 함.
	// 보고만 있다가 낚아 챔
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(authIntercepter)
		.addPathPatterns("/account/**")
		.addPathPatterns("/auth/**");
		//authIntercepter 가 (낚아채서) 위(DI)에서 동작하게끔 등록시킴
		
	}
	
	
}
