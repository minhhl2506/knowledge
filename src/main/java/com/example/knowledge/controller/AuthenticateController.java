package com.example.knowledge.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.knowledge.annotation.InboundRequestLog;
import com.example.knowledge.jwt.JWTTokenProvider;
import com.example.knowledge.model.User;
import com.example.knowledge.repository.UserRepository;
import com.example.knowledge.request.LoginRequest;
import com.example.knowledge.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/authenticate")
public class AuthenticateController {
	
	private final UserService userService;
	
	private final UserRepository userRepository;
	
	private final JWTTokenProvider tokenProvider;
	
	@InboundRequestLog
	@PostMapping("/login")
	public String authorize(HttpServletRequest request, @RequestBody LoginRequest loginRequest)
			throws Exception {
		BCryptPasswordEncoder encode = new BCryptPasswordEncoder();
        User user1 = userRepository.findByUsername(loginRequest.getUsername());
        boolean checkPassword = encode.matches(loginRequest.getPassword(), user1.getPassword());
        if (loginRequest.getUsername().equals(user1.getUsername()) && checkPassword == true) {
            UserDetails userDetail = userService.loadUserByUsername(loginRequest.getUsername());

            return tokenProvider.generateToken(userDetail);
        } else {
            return "Tài khoản không đúng!";
        }
	}
	
}
