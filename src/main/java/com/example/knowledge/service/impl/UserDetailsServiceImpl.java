package com.example.knowledge.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.knowledge.model.Privilege;
import com.example.knowledge.model.Role;
import com.example.knowledge.model.User;
import com.example.knowledge.repository.PrivilegeRepository;
import com.example.knowledge.repository.RoleRepository;
import com.example.knowledge.repository.UserRepository;
import com.example.knowledge.util.Validator;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private PrivilegeRepository privilegeRepository;
	
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
	
}
