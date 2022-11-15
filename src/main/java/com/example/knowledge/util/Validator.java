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
	
	public static boolean isEquals(int int1, int int2) {
		return int1 == int2;
	}
	
	public static boolean isEquals(long long1, long long2) {
		return long1 == long2;
	}
	
	public static boolean equals(Object obj1, Object obj2) {
		if ((obj1 == null) && (obj2 == null)) {
			return true;
		} else if ((obj1 == null) || (obj2 == null)) {
			return false;
		} else {
			return obj1.equals(obj2);
		}
	}

	public static boolean isNotNull(String str) {
		return !isNull(str);
	}
	
	public static boolean isNotNull(Object obj) {
		return !isNull(obj);
	}
	
	public static boolean isNotNull(Long l) {
		return !isNull(l);
	}

}
