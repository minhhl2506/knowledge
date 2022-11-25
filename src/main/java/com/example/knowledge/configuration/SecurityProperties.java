package com.example.knowledge.configuration;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "rsa")
public class SecurityProperties {
	private int keyLength;
	
	private String algorithm;

	private String privateKey;

	@Bean(name = "rsaProvider")
	public RsaProvider rsaProvider()
			throws NoSuchAlgorithmException, InvalidKeySpecException, IOException {
		return RsaProvider.fromPrivateKey(this.algorithm, this.privateKey);
	}
}
