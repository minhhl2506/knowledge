package com.example.knowledge.service.impl;

import org.jasypt.encryption.pbe.PBEStringEncryptor;
import org.springframework.stereotype.Service;

import com.example.knowledge.service.SystemService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SystemServiceImpl implements SystemService {
	
	private final PBEStringEncryptor pbeStringEncryptor;
	
	@Override
	public String decrypt(String data) {
		try {
			data = this.pbeStringEncryptor.decrypt(data);
			
			return data;
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}
	}
}
