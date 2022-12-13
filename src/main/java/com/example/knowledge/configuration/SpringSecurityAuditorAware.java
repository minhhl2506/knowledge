package com.example.knowledge.configuration;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import com.example.knowledge.util.SecurityUtils;

@Component
public class SpringSecurityAuditorAware implements AuditorAware<String> {
	
	@Override
	public Optional<String> getCurrentAuditor() {
		return Optional.of(SecurityUtils.getLoggedInUser().orElse("superadmin"));
	}

}
