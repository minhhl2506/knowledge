//package com.example.knowledge.handler;
//
//import org.aopalliance.intercept.MethodInvocation;
//import org.springframework.security.access.PermissionEvaluator;
//import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
//import org.springframework.security.access.expression.method.MethodSecurityExpressionOperations;
//import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
//import org.springframework.security.core.Authentication;
//
//import com.mb.laos.security.SecurityExpressionRootImpl;
//
///**
// * 06/07/2021 - LinhLH: Create new
// *
// * @author LinhLH - ok
// */
//
//public class MethodSecurityExpressionHandlerImpl extends DefaultMethodSecurityExpressionHandler {
//	private final PermissionEvaluator permissionEvaluator;
//
//	public MethodSecurityExpressionHandlerImpl(PermissionEvaluator permissionEvaluator) {
//		this.permissionEvaluator = permissionEvaluator;
//
//		super.setPermissionEvaluator(permissionEvaluator);
//	}
//
//	@Override
//	protected MethodSecurityExpressionOperations createSecurityExpressionRoot(Authentication authentication,
//			MethodInvocation invocation) {
//		SecurityExpressionRootImpl root = new SecurityExpressionRootImpl(authentication, permissionEvaluator);
//
//		root.setTrustResolver(new AuthenticationTrustResolverImpl());
//
//		root.setRoleHierarchy(getRoleHierarchy());
//
//		return root;
//	}
//}
