package com.example.knowledge.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
import com.example.knowledge.advice.DecryptErrorException;
import com.example.knowledge.configuration.RsaProvider;
import com.example.knowledge.jwt.JWTTokenProvider;
import com.example.knowledge.message.MessageCode;
import com.example.knowledge.model.Privilege;
import com.example.knowledge.model.Role;
import com.example.knowledge.model.User;
import com.example.knowledge.repository.PrivilegeRepository;
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

	@Autowired
	private RsaProvider rsaProvider;

	@Autowired
	private JWTTokenProvider tokenProvider;

	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private PrivilegeRepository privilegeRepository;
	
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
		
		List<String> roleNames = new ArrayList<>();
		
        List<Privilege> privileges = new ArrayList<>();

		// set list of roles into user
		user.setRoles(roles);
		
		roles.forEach(role -> {
			roleNames.add(role.getRoleName());
			
			List<Privilege> pves = this.privilegeRepository.findByRoleId(role.getId());
			
			role.setPrivileges(pves);
			
			privileges.addAll(pves);
		});

		List<GrantedAuthority> grantedAuthorities = privileges.stream()
				.map(privilege -> new SimpleGrantedAuthority(privilege.getPrivilegeName())).collect(Collectors.toList());
					
			roleNames.stream().forEach(roleName -> grantedAuthorities.add(new SimpleGrantedAuthority(roleName)));
			
			return new com.example.knowledge.security.UserPrincipal(user, grantedAuthorities);
	}

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

            //generate token
			TokenResponse tokenResponse = this.tokenProvider.createToken(username);

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
