package com.example.knowledge.util;

public class GetterUtils {

	public static String getString(String value, String defaultValue) {
		return get(value, defaultValue);
	}

	private static String get(String value, String defaultValue) {
		
		if(value != null) {
			value = value.trim();
			return value;
		}
		
		return defaultValue;
	}
	
}
