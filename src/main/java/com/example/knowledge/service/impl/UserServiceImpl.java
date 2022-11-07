package com.example.knowledge.service.impl;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.knowledge.jwt.JWTTokenProvider;
import com.example.knowledge.model.User;
import com.example.knowledge.repository.UserRepository;
import com.example.knowledge.request.LoginRequest;
import com.example.knowledge.response.TokenResponse;
import com.example.knowledge.service.UserDetail;
import com.example.knowledge.service.UserService;
import com.example.knowledge.util.Validator;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {

	private final UserRepository userRepository;
	
//	private final RsaProvider rsaProvider;
	
	private final JWTTokenProvider tokenProvider;

	@Override
	public UserDetail loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = this.userRepository.findByUsername(username);

		if (Validator.isNull(user)) {
			throw new UsernameNotFoundException("Khong tim thay");
		} else {
			return new UserDetail(user);
		}
	}
	
	@Override
	public ResponseEntity<TokenResponse> authorize(HttpServletRequest request, LoginRequest loginRequest) {
		
		return null;
//		String username = loginRequest.getUsername();
//		String password = loginRequest.getPassword();
//		
//		try {
//			
//			if(Validator.isNull(username.trim())) {
//				throw new BadRequestAlertException(MessageCode.MSG1005);
//			}
//			
//			if(Validator.isNull(password.trim())) {
//				throw new BadRequestAlertException(MessageCode.MSG1006);
//			}
//			
//			User user = this.userRepository.findByUsername(loginRequest.getUsername());
//			
//			if(Validator.isNull(user)) {
//				throw new BadRequestAlertException(MessageCode.MSG1007);
//			}
//			
//			String token = this.tokenProvider.createToken(null, username);
//			
//			TokenResponse tokenResponse = TokenResponse.builder()
//													   .token(token)
//													   .type("Bearer")
//													   .build();
//			
//			return new ResponseEntity<>(tokenResponse, HttpStatus.OK);
//
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			return null;
//		}
	}

}
