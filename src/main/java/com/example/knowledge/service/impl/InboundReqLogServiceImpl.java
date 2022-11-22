package com.example.knowledge.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.knowledge.model.InboundReqLog;
import com.example.knowledge.repository.InboundRequestLogRepository;
import com.example.knowledge.service.InboundReqLogService;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class InboundReqLogServiceImpl implements InboundReqLogService {
	private final InboundRequestLogRepository inboundReqLogRepository;

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public InboundReqLog save(InboundReqLog reqLog) {
		return inboundReqLogRepository.save(reqLog);
	}
	
	@Override
	public InboundReqLog findNewestRecord() {
		return this.inboundReqLogRepository.findTop1ByOrderByIdDesc();
	}

}
