package com.example.knowledge.service;

import com.example.knowledge.model.InboundReqLog;

public interface InboundReqLogService {
	InboundReqLog save(InboundReqLog reqLog);
	
	InboundReqLog findNewestRecord();
}
