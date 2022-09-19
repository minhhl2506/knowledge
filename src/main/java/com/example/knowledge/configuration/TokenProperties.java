package com.example.knowledge.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Getter;

@Getter
@ConfigurationProperties(prefix = "cache.time-to-live")
public class TokenProperties {

	private int token;
		
}
