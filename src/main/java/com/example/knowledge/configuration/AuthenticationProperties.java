package com.example.knowledge.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;

@Getter
@Configuration
public class AuthenticationProperties {
	
	@Value("${security.authentication.jwt.secret-key}")
	private String secretKey;

}
