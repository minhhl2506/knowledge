package com.example.knowledge.configuration;

import java.security.NoSuchAlgorithmException;

import org.jasypt.encryption.pbe.PBEStringEncryptor;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.jasypt.util.text.AES256TextEncryptor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Component
@RequiredArgsConstructor
@ConfigurationProperties(prefix = "jasypt.encryptor")
public class JasyptConfiguration {

	private String password;
    
    private String algorithm;
    
    private String poolSize;
    
    private int keyObtentionIterations;
    
    private String providerName;
    
    private String saltGeneratorClassname;
    
    private String ivGeneratorClassname;
    
    private String stringOutputType;
    
    private final RsaProvider rsaProvider;
    
    @Bean("jasyptStringEncryptor")
    public PBEStringEncryptor stringEncryptor() throws Exception {
        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
        
        SimpleStringPBEConfig config = new SimpleStringPBEConfig();
        
        config.setPoolSize(this.poolSize);
        config.setPassword(this.rsaProvider.decrypt(this.password));
        config.setAlgorithm(this.algorithm);
        config.setIvGeneratorClassName(this.ivGeneratorClassname);
        config.setSaltGeneratorClassName(this.saltGeneratorClassname);
        config.setStringOutputType(this.stringOutputType);
        config.setKeyObtentionIterations(this.keyObtentionIterations);
        
        encryptor.setConfig(config);
        
        return encryptor;
    }
  
}