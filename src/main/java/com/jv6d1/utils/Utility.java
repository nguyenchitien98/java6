package com.jv6d1.utils;

import javax.servlet.http.HttpServletRequest;

public class Utility {
	public static String getSizeUrl(HttpServletRequest request) {
		String sizeUrl = request.getRequestURI().toString();
		return sizeUrl.replace(request.getServletPath(), "http://localhost:8080");
	}
}
