package com.example.knowledge.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.knowledge.annotation.InboundRequestLog;
import com.example.knowledge.request.LoginRequest;
import com.example.knowledge.response.TokenResponse;
import com.example.knowledge.service.AuthenticateService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/authenticate")
public class AuthenticateController {
	
	private final AuthenticateService userService;
	
	@InboundRequestLog
	@PostMapping("/login")
	public ResponseEntity<TokenResponse> authorize(HttpServletRequest request, @RequestBody LoginRequest loginRequest)
			throws Exception {		
		return this.userService.authorize(request, loginRequest);
	}
	
}
