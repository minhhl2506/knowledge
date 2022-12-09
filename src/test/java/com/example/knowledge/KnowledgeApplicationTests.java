package com.example.knowledge;

import org.junit.jupiter.api.Test;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
@SpringBootTest
@ConfigurationProperties(prefix = "jasypt.encryptor")
class KnowledgeApplicationTests {
	
	private String pass;
    
    private String algorithm;
    
    private String poolSize;
    
    private int keyObtentionIterations;
    
    private String providerName;
    
    private String saltGeneratorClassname;
    
    private String ivGeneratorClassname;
    
    private String stringOutputType;
	
	@Test
    void contextLoads() {
    }

//    @Test
//    public void testEncryptionKey() throws Exception {
//        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
//        
//        SimpleStringPBEConfig config = new SimpleStringPBEConfig();
//        
//        config.setPoolSize(this.poolSize);
//        config.setPassword("minhmomi2000");
//        config.setAlgorithm(this.algorithm);
//        config.setIvGeneratorClassName(this.ivGeneratorClassname);
//        config.setSaltGeneratorClassName(this.saltGeneratorClassname);
//        config.setStringOutputType(this.stringOutputType);
//        config.setKeyObtentionIterations(this.keyObtentionIterations);
//        
//        encryptor.setConfig(config);
//
//        String plaintext = "jdbc:mysql://localhost:3306/my_portal";
//        System.out.println("Encrypted key : " + encryptor.encrypt(plaintext));
//    }

}
