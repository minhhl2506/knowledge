//package com.example.knowledge;
//
//import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
//import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
//import org.junit.jupiter.api.Test;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.stereotype.Component;
//import com.example.knowledge.configuration.RsaProvider;
//import lombok.Data;
//
//@Data
//@Component
//@SpringBootTest
//@ConfigurationProperties(prefix = "jasypt.encryptor")
//class KnowledgeApplicationTests {
//	
//	private String pass;
//    
//    private String algorithm;
//    
//    private String poolSize;
//    
//    private int keyObtentionIterations;
//    
//    private String providerName;
//    
//    private String saltGeneratorClassname;
//    
//    private String ivGeneratorClassname;
//    
//    private String stringOutputType;
//    
//    private final RsaProvider rsaProvider;
//	
//	@Test
//    void contextLoads() {
//    }
//
//    @Test
//    public void testEncryptionKey() throws Exception {
//        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
//        
//        SimpleStringPBEConfig config = new SimpleStringPBEConfig();
//        
//        config.setPoolSize(this.poolSize);
//        config.setPassword(this.rsaProvider.decrypt(this.pass));
//        config.setAlgorithm(this.algorithm);
//        config.setIvGeneratorClassName(this.ivGeneratorClassname);
//        config.setSaltGeneratorClassName(this.saltGeneratorClassname);
//        config.setStringOutputType(this.stringOutputType);
//        config.setKeyObtentionIterations(this.keyObtentionIterations);
//        
//        encryptor.setConfig(config);
//
//        String plaintext = "2d+GEcVXKP/CUxuYcWtqmyocWc+LmtkMqnr3aXnzpCyRN/ndpn/bHIKXCx7mngVf";
//        System.out.println("Encrypted key : " + encryptor.decrypt(plaintext));
//    }
//
//}
