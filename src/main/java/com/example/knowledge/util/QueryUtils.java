package com.example.knowledge.util;

public class QueryUtils {
	
	public static String addFullQueryParam(String param) {
		StringBuilder str = new StringBuilder();
		
		str.append("%");
		str.append(param);
		str.append("%");
		
		return str.toString();
	}
	
}
