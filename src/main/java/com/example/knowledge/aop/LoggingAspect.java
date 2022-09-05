package com.example.knowledge.aop;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.example.knowledge.annotation.InboundRequestLog;
import com.example.knowledge.model.InboundReqLog;
import com.example.knowledge.service.InboundReqLogService;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
public class LoggingAspect {
	
	private final InboundReqLogService inboundReqLogService;
	
//	private final Gson gson; 
	
	@Pointcut("execution(@com.example.knowledge.annotation.*RequestLog * *(..))")
    public void requestLogPointcut() {}
	

	@Around("@annotation(inboundRequestLog)")
	public Object saveInboundLog(final ProceedingJoinPoint joinPoint, InboundRequestLog inboundRequestLog) throws Throwable {
		
		log.info("save inbound req log is called!");
		
		ObjectMapper mapper = new ObjectMapper();
		
        InboundReqLog reqLog = new InboundReqLog();
        
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        
        reqLog.setRequestUri(request.getRequestURI());
        
        reqLog.setMethod(request.getMethod());
        
        reqLog.setRequestTime(Instant.now());
        
        reqLog.setServiceName(joinPoint.getSignature().getName());
        
//        reqLog.setRequestData(gson.fromJson(request.getReader().lines().collect(Collectors.joining()), String.class));
        
        reqLog.setResponseData(mapper.writeValueAsString(joinPoint.proceed()));
        
        this.inboundReqLogService.save(reqLog);
		
		return joinPoint.proceed();
	}
	
	

}
