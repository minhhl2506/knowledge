//package com.example.knowledge.service;
//
//import java.util.Collection;
//
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import com.example.knowledge.model.User;
//
//public class UserDetail implements UserDetails {
//	
//	/**
//	 * 
//	 */
//	private static final long serialVersionUID = 3499193341360383297L;
//	
//	private User user;
//	
//	public UserDetail(User user) {
//		this.user = user;
//	}
//
//	@Override
//	public Collection<? extends GrantedAuthority> getAuthorities() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//	
//	@Override
//	public String getUsername() {
//		// TODO Auto-generated method stub
//		return this.user.getUsername();
//	}
//	
//	@Override
//	public String getPassword() {
//		// TODO Auto-generated method stub
//		return this.user.getPassword();
//	}
//	
//	@Override
//	public boolean isAccountNonExpired() {
//		// TODO Auto-generated method stub
//		return true;
//	}
//	
//	@Override
//	public boolean isAccountNonLocked() {
//		// TODO Auto-generated method stub
//		return true;
//	}
//	
//	@Override
//	public boolean isCredentialsNonExpired() {
//		// TODO Auto-generated method stub
//		return true;
//	}
//	
//	@Override
//	public boolean isEnabled() {
//		// TODO Auto-generated method stub
//		return true;
//	}
//	
//}
