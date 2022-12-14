package com.example.knowledge.service;

import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Component;

import com.example.knowledge.util.SecurityConstants;

@Component
public interface TokenService {
	
	//cache put sẽ ghi đè cache mới lên cache cũ. Bất kể cache cũ có tồn tại hay k
	@CachePut(cacheNames = SecurityConstants.Cache.REFRESH_TOKEN, key = "#username", unless = "#result == null")
    default String saveRefreshToken(String username, String token) {
        return token;
    }
}
