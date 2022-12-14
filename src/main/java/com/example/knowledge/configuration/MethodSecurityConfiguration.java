package com.example.knowledge.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;

import com.example.knowledge.security.PermissionEvaluatorImpl;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class MethodSecurityConfiguration extends GlobalMethodSecurityConfiguration {
	@Override
	protected MethodSecurityExpressionHandler createExpressionHandler() {

		DefaultMethodSecurityExpressionHandler expressionHandler = 
		          new DefaultMethodSecurityExpressionHandler();
		        
		expressionHandler.setPermissionEvaluator(new PermissionEvaluatorImpl());
		        
		return expressionHandler;

	}
}
