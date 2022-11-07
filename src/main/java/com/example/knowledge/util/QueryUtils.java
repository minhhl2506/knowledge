package com.example.knowledge.util;

public class QueryUtils {
		
	public static final String PERCENT = "%";
	
	public static String addFullQueryParam(String param) {
		
		StringBuilder str = new StringBuilder();
		
		str.append(PERCENT);
		str.append(param);
		str.append(PERCENT);
		
		return str.toString();
	}
	
}
