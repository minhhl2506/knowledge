package com.example.knowledge.util;

public class Validator {

	public static boolean isNull(Long number) {
		if (number == null || number.longValue() < 0) {
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

		if (str.trim().length() <= 0) {
			return true;
		}

		else {
			return false;
		}
	}

	public static boolean isNull(Object obj) {

		if (obj instanceof Long) {
			return isNull((Long) obj);
		}

		if (obj instanceof String) {
			return isNull((String) obj);
		}

		else if (obj == null) {
			return true;
		}

		else {
			return false;
		}
	}

	public static boolean isNotNull(String str) {
		return !isNull(str);
	}

}
