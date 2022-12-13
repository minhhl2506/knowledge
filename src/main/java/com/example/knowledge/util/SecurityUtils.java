package com.example.knowledge.util;

import java.util.Optional;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class SecurityUtils {

	public static Optional<String> getLoggedInUser() {
		SecurityContext securityContext = SecurityContextHolder.getContext();

		return Optional.ofNullable(securityContext.getAuthentication()).map(authentication -> {
			if (authentication instanceof AnonymousAuthenticationToken) {
				return null;
			}
			if (authentication.getPrincipal() instanceof UserDetails) {
				UserDetails springSecurityUser = (UserDetails) authentication.getPrincipal();

				return springSecurityUser.getUsername();
			} else if (authentication.getPrincipal() instanceof String) {
				return (String) authentication.getPrincipal();
			}

			return null;
		});
	}

}
