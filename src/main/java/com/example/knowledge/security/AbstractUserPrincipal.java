package com.example.knowledge.security;

import java.io.Serializable;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

public abstract class AbstractUserPrincipal extends org.springframework.security.core.userdetails.User
		implements Serializable {

	private static final long serialVersionUID = 6960173949433045836L;

	public AbstractUserPrincipal(String username, String password, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
	}
}
