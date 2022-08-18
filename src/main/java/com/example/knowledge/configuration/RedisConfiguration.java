//package com.example.knowledge.configuration;
//
//import org.springframework.cache.annotation.EnableCaching;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.redis.connection.RedisConnectionFactory;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
//
//@Configuration
//@EnableCaching
//@EnableRedisRepositories
//public class RedisConfiguration {
//
//	@Bean
//	public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory connectionFactory) {
//		RedisTemplate<String, String> template = new RedisTemplate<>();
//		// Add some specific configuration here. Key serializers, etc.
//		return template;
//	}
//}
