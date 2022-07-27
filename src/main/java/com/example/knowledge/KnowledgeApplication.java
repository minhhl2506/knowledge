package com.example.knowledge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableScheduling;
import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;

@SpringBootApplication
@EnableConfigurationProperties
@EnableScheduling
@EnableEncryptableProperties
public class KnowledgeApplication {
		
//	@Scheduled(cron = "${scheduling.cron}")
//	public void home() {
//		System.out.println("Now is " +Instant.now());
//	}

	public static void main(String[] args) {
		SpringApplication.run(KnowledgeApplication.class, args);
	}

}
