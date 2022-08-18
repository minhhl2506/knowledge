package com.example.knowledge.util;

public class Validator {
	
	public static boolean isNull(Object obj) {
		if (obj == null) {
			return true;
		}

		else {
			return false;
		}
	}

	public static boolean isNull(Long number) {
		if (number == null) {
			return true;
		}

		else {
			return false;
		}
	}

	public static boolean isNull(String str) {
		if (str == null) {
			return true;
		}

		else {
			return false;
		}
	}
	
}
