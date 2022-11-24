package com.example.knowledge.aop;

import java.nio.charset.StandardCharsets;
import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.example.knowledge.annotation.InboundRequestLog;
import com.example.knowledge.model.InboundReqLog;
import com.example.knowledge.service.InboundReqLogService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
public class LoggingAspect {

	private final InboundReqLogService inboundReqLogService;

	private final Gson gson;

	private final ObjectMapper objectMapper;

	@Pointcut("execution(@com.example.knowledge.annotation.*RequestLog * *(..))")
	public void requestLogPointcut() {
	}

	@Around("@annotation(inboundRequestLog)")
	public Object saveInboundLog(final ProceedingJoinPoint joinPoint, InboundRequestLog inboundRequestLog)
			throws Throwable {

		log.info("save inbound req log is called!");

		InboundReqLog reqLog = new InboundReqLog();

		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();

		byte[] requestBody = StreamUtils.copyToByteArray(request.getInputStream());

		reqLog.setRequestUri(request.getRequestURI());

		reqLog.setMethod(request.getMethod());

		reqLog.setRequestTime(Instant.now());

		reqLog.setRequestData(new String(requestBody, StandardCharsets.UTF_8));

		reqLog.setServiceName(joinPoint.getSignature().getName());

		this.inboundReqLogService.save(reqLog);

		return joinPoint.proceed();
	}

	@AfterReturning(pointcut = "requestLogPointcut()", returning = "returnValue")
	public void afterRequestReturing(final JoinPoint joinPoint, Object returnValue) throws JsonProcessingException {

		InboundReqLog reqLog = this.inboundReqLogService.findNewestRecord();

		reqLog.setResponseData(this.gson.toJson(this.objectMapper.writeValueAsString(returnValue)));

		this.inboundReqLogService.save(reqLog);
	}

}
