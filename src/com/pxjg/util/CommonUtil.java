package com.pxjg.util;

public class CommonUtil {
	public static boolean contains(String container, String[] regex) {
		for (String str : regex) {
			if (container.contains(str)) {
				return true;
			}
		}
		return false;
	}

	public static boolean contains(String[] container, String regex) {
		for (String str : container) {
			if (str.equals(regex)) {
				return true;
			}
		}
		return false;
	}
}
