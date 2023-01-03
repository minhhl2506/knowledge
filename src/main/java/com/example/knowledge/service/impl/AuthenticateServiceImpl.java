package com.example.knowledge.service.impl;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.knowledge.advice.BadRequestAlertException;
import com.example.knowledge.advice.DecryptErrorException;
import com.example.knowledge.configuration.RsaProvider;
import com.example.knowledge.jwt.JWTTokenProvider;
import com.example.knowledge.message.MessageCode;
import com.example.knowledge.model.User;
import com.example.knowledge.repository.UserRepository;
import com.example.knowledge.request.LoginRequest;
import com.example.knowledge.response.TokenResponse;
import com.example.knowledge.service.AuthenticateService;
import com.example.knowledge.util.SecurityConstants;
import com.example.knowledge.util.Validator;

@Service
public class AuthenticateServiceImpl implements AuthenticateService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RsaProvider rsaProvider;

	@Autowired
	private JWTTokenProvider tokenProvider;
	
	@Autowired
	private AuthenticationManagerBuilder authenticationManagerBuilder;

	@Override
	public ResponseEntity<TokenResponse> authorize(HttpServletRequest request, LoginRequest loginRequest) {
		String username = loginRequest.getUsername();
		String password = loginRequest.getPassword();

		try {

			if (Validator.isNull(username.trim())) {
				throw new BadRequestAlertException(MessageCode.MSG1005);
			}

			if (Validator.isNull(password.trim())) {
				throw new BadRequestAlertException(MessageCode.MSG1006);
			}

			User user = this.userRepository.findByUsername(loginRequest.getUsername());
			
			if (Validator.isNull(user)) {
				throw new BadRequestAlertException(MessageCode.MSG1008);
			}
			
			//giai ma password
			password = this.rsaProvider.decrypt(password);

			//spring check password
			UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username,
					password);

			Authentication authentication = this.authenticationManagerBuilder.getObject()
					.authenticate(authenticationToken);
		
			//neu username/password dung thi set vao security context 
            SecurityContextHolder.getContext().setAuthentication(authentication);
            
            String accessToken = this.tokenProvider.createAccessToken(username);
            
            String refreshToken = this.tokenProvider.createRefreshToken(username);

            //generate token
			TokenResponse tokenResponse = TokenResponse.builder()
													   .type(SecurityConstants.Header.BEARER)
													   .accessToken(accessToken)
													   .refreshToken(refreshToken)
													   .accessTokenDuration(60)
													   .build();

			return new ResponseEntity<>(tokenResponse, HttpStatus.OK);

		} catch (Exception e) {
			if (BadCredentialsException.class.isAssignableFrom(e.getClass())) {     	
            	throw new BadRequestAlertException(MessageCode.MSG1008);
            }
			
			if (DecryptErrorException.class.isAssignableFrom(e.getClass())) {     	
            	throw new DecryptErrorException();
            }
			
			throw e;
		}
	}

}
