package com.comento.oracleSpringBoot;

public class StaticUtil {
	/**
	 * 8이라 논널엘즈를 못 써서 따로 구현
	 */
	public static String nullElse(String v, String d) {
		return v == null ? d : v;
	}
}
