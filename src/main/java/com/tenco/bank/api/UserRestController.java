package com.tenco.bank.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tenco.bank.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api-user")
@RequiredArgsConstructor // DI
public class UserRestController {

	private final UserService userService;
	
	
	// 주소
	// http:localhost:8080/api-user/check-username?username=홍길동
	@GetMapping("/check-username")
	public boolean getMethodName(@RequestParam(name="username") String username) {
		boolean isUser =  userService.searchUsername(username) == null ? true : false;
		return isUser;
	}
	
}
