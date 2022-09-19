package com.example.knowledge.util;

import java.net.URI;

public class ApiConstants {
 
	public interface HEADER {
		public static final String LOCALE = "locale";
	}
	
    public interface ErrorKey {
        public static final String ERROR_KEY = "errorKey";
        
        public static final String ERROR_CODE = "errorCode";

        public static final String FIELD_ERRORS = "fieldErrors";
        
        public static final String MESSAGE = "message";
        
        public static final String PARAMS = "params";

        public static final String PATH = "path";

        public static final String VIOLATIONS = "violations";
    }
	
	public interface ErrorType {
        public static final String _PROBLEM_BASE_URL = "http://www.knowledge/problem";

        public static final URI DEFAULT_TYPE = URI.create(_PROBLEM_BASE_URL + "/problem-with-message");

    }
	
}
