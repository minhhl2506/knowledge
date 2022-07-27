package com.example.knowledge.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import lombok.Data;

@Data
@Component
//@PropertySource(value = "classpath:application-sec.yml") 
@ConfigurationProperties(prefix = "jasypt.encryptor")
public class PropertiesConfiguration {

	private String url;

	private String pass;

	private String algorithm;

	private String poolSize;

	private int keyObtentionIterations;

	private String providerName;

	private String saltGeneratorClassname;

	private String ivGeneratorClassname;

	private String stringOutputType;

}
