//package com.example.knowledge.service.impl;
//
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import com.example.knowledge.model.User;
//import com.example.knowledge.repository.UserRepository;
//import com.example.knowledge.service.UserDetail;
//import com.example.knowledge.service.UserService;
//import com.example.knowledge.util.Validator;
//
//import lombok.RequiredArgsConstructor;
//
//@Service
//@RequiredArgsConstructor
//public class UserServiceImpl implements UserService, UserDetailsService {
//
//	private final UserRepository userRepository;
//
//	@Override
//	public UserDetail loadUserByUsername(String username) throws UsernameNotFoundException {
//
//		User user = this.userRepository.findByUsername(username);
//
//		if (Validator.isNull(user)) {
//			throw new UsernameNotFoundException("Khong tim thay");
//		} else {
//			return new UserDetail(user);
//		}
//	}
//
//}
