package com.example.knowledge.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.knowledge.request.DecryptDataRequest;
import com.example.knowledge.service.SystemService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/system")
@RequiredArgsConstructor
public class SystemController {
	private final SystemService systemService;
	
	@PostMapping("/decrypt")
    public ResponseEntity<String> decrypt(@RequestBody DecryptDataRequest request) {
        return new ResponseEntity<>(this.systemService.decrypt(request.getData()), HttpStatus.OK);
    }
}
