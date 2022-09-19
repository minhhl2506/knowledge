package com.example.knowledge.configuration;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.scheduling.annotation.Scheduled;

import com.example.knowledge.cache.util.CacheConstants;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Configuration
@EnableRedisRepositories
@RequiredArgsConstructor
@Slf4j
public class RedisConfiguration {

	private final CacheManager cacheManager;

	@Scheduled(cron = "${scheduling.cache.cron}")
	@CacheEvict(cacheNames = CacheConstants.Car.FIND_CAR_BY_ID, allEntries = true)
	public void refreshCache() {
		
		log.info("Clear cache function is called!");
		
        cacheManager.getCacheNames().stream()
           .forEach(cache -> cacheManager.getCache(cache).clear());

     }

}
