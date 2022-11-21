package com.example.knowledge.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.knowledge.advice.BadRequestAlertException;
import com.example.knowledge.jwt.JWTTokenProvider;
import com.example.knowledge.message.MessageCode;
import com.example.knowledge.model.Role;
import com.example.knowledge.model.User;
import com.example.knowledge.repository.RoleRepository;
import com.example.knowledge.repository.UserRepository;
import com.example.knowledge.request.LoginRequest;
import com.example.knowledge.response.TokenResponse;
import com.example.knowledge.service.UserService;
import com.example.knowledge.util.Validator;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

	@Autowired
	private UserRepository userRepository;

//	private final RsaProvider rsaProvider;

	@Autowired
	private JWTTokenProvider tokenProvider;

	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private AuthenticationManagerBuilder authenticationManagerBuilder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = this.userRepository.findByUsername(username);

		if (Validator.isNull(user)) {
			throw new UsernameNotFoundException("Khong tim thay");
		} else {
			final UserDetails userDetails = this.createSpringSecurityUser(username, user);
			return userDetails;
		}
	}

	private com.example.knowledge.security.UserPrincipal createSpringSecurityUser(String username, User user) {

		List<Role> roles = this.roleRepository.findByUserId(user.getId());

		// set list of roles into user
		user.setRoles(roles);

		List<GrantedAuthority> grantedAuthorities = new ArrayList<>();

		for (Role role : roles) {
			grantedAuthorities.add(new SimpleGrantedAuthority(role.getRoleName()));
		}

		return new com.example.knowledge.security.UserPrincipal(user, grantedAuthorities);

	}

	@Override
	public ResponseEntity<TokenResponse> authorize(HttpServletRequest request, LoginRequest loginRequest) {

//		return null;
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

			UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username,
					password);

			Authentication authentication = this.authenticationManagerBuilder.getObject()
					.authenticate(authenticationToken);
		
            SecurityContextHolder.getContext().setAuthentication(authentication);

			TokenResponse tokenResponse = this.tokenProvider.createToken(username);

			return new ResponseEntity<>(tokenResponse, HttpStatus.OK);

		} catch (Exception e) {
			if (BadCredentialsException.class.isAssignableFrom(e.getClass())) {     	
            	throw new BadRequestAlertException(MessageCode.MSG1008);
            }
			
			throw e;
		}
	}

}
