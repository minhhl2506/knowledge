package com.example.knowledge.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;

import com.example.knowledge.request.LoginRequest;
import com.example.knowledge.response.TokenResponse;

public interface AuthenticateService {

	/**
	 * @param request
	 * @param loginRequest
	 * @return
	 */
	ResponseEntity<TokenResponse> authorize(HttpServletRequest request, LoginRequest loginRequest);

}
