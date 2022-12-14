package com.example.knowledge.util;

public interface SecurityConstants {
	
    public interface TokenType {
    	
    	public static final String TOKEN = "token";
    	
        public static final String REFRESH_TOKEN = "refresh-token";
        
        public static final String REFRESH_TOKEN_FAST_LOGIN = "refresh-token-fast-login";
        
        public static final String ACCESS_TOKEN = "access-token";
        
        public static final String CSRF_TOKEN = "csrf-token";
    }
    
    public interface Header {
    	public static final String BEARER = "Bearer ";
    }
    
    public interface Cache {
    	public static final String REFRESH_TOKEN = "refresh-token";
    }
	
}
