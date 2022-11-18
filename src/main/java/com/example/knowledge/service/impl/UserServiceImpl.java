package com.example.knowledge.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.knowledge.jwt.JWTTokenProvider;
import com.example.knowledge.model.Role;
import com.example.knowledge.model.User;
import com.example.knowledge.repository.RoleRepository;
import com.example.knowledge.repository.UserRepository;
import com.example.knowledge.request.LoginRequest;
import com.example.knowledge.response.TokenResponse;
import com.example.knowledge.service.UserService;
import com.example.knowledge.util.Validator;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {

	private final UserRepository userRepository;
	
//	private final RsaProvider rsaProvider;
	
	private final JWTTokenProvider tokenProvider;
	
	private final RoleRepository roleRepository;

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
        
        System.out.println(roles);

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
