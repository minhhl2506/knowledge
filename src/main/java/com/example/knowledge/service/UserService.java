package com.example.knowledge.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.knowledge.request.LoginRequest;
import com.example.knowledge.response.TokenResponse;

public interface UserService {

	UserDetails loadUserByUsername(String username);

	/**
	 * @param request
	 * @param loginRequest
	 * @return
	 */
	ResponseEntity<TokenResponse> authorize(HttpServletRequest request, LoginRequest loginRequest);

}
