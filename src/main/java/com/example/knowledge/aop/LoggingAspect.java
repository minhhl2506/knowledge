package com.example.knowledge.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect
@Component
public class LoggingAspect {

	@Before("execution(* com.example.knowledge.service.*.*(..))")
	public void before(final JoinPoint joinPoint) {
		log.info(" before called " + joinPoint.toString());
	}

}
