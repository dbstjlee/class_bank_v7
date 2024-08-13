package com.tenco.bank.handler;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.tenco.bank.handler.exception.UnAuthorizedException;
import com.tenco.bank.repository.model.User;
import com.tenco.bank.utils.Define;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

// 1개의 class 단위로 등록할 때 사용
@Component // (하나의 클래스를 ) IoC 대상(싱글톤 패턴)
public class AuthIntercepter implements HandlerInterceptor {

	// preHandle 동작 흐름 (단, 스프링부트 설정 파일, 설정 클래스에 등록이 되어야 함 : 특정 URL)
	// 컨트롤러 들어 오기 전에 동작함
	// true --> 컨트롤러 안으로 들여보냄
	// false --> 컨트롤러 안 으로 못 들어감
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		HttpSession session = request.getSession();
		User principal = (User) session.getAttribute(Define.PRINCIPAL);
		if(principal == null) {
			throw new UnAuthorizedException("로그인 먼저 해주세요", HttpStatus.UNAUTHORIZED);
		}
		return true; // controller로 들여보내줌
	}
	
	
	// postHandle
	// 뷰가 렌더링 되기 바로 전에 콜백되는 메서드
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}
	
	// afterCompletion
	// 요청 처리가 완료된 후, 즉 뷰가 완전 렌더링이 된 후에 호출된다.
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		
		HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
	}
}
