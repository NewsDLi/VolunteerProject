package com.volunteer.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieUtil {

	public static Cookie getCookie(HttpServletRequest request, String name) {
		Cookie cookies[] = request.getCookies();
		if (cookies == null || name == null || name.length() == 0) {
			return null;
		}
		for (int i = 0; i < cookies.length; i++) {
			if (name.equals(cookies[i].getName())
			/* && request.getServerName().equals(cookies[i].getDomain()) */) {
				return cookies[i];
			}
		}
		return null;
	}

	public static void deleteCookie(HttpServletRequest request, HttpServletResponse response, Cookie cookie) {
		if (cookie != null) {
			cookie.setPath(getPath(request));
			cookie.setValue("");
			cookie.setMaxAge(0);
			response.addCookie(cookie);
		}
	}

	public static void deleteCookie(HttpServletRequest request, HttpServletResponse response, String cookieName) {
		Cookie cookie = new Cookie(cookieName, "");
		deleteCookie(request, response, cookie);
	}

	public static void setCookie(HttpServletRequest request, HttpServletResponse response, String name, String value) {
		setCookie(request, response, name, value, 0x278d00);
	}

	public static void setCookie(HttpServletRequest request, HttpServletResponse response, String name, String value,
			int maxAge) {
		Cookie cookie = new Cookie(name, value == null ? "" : value);
		cookie.setMaxAge(maxAge);
		cookie.setPath(getPath(request));
		response.addCookie(cookie);
	}

	private static String getPath(HttpServletRequest request) {
		String path = request.getContextPath();
		return (path == null || path.length() == 0) ? "/" : path;
	}

	/***
	 * 设置cookie httponly和secure
	 * 
	 * @param
	 * @time: 2019年7月20日20:06:51
	 * @param name
	 * @param value
	 * @param request
	 * @param response
	 * @param interval
	 * @param httpOnly
	 * @param secure
	 */
	public static void setCookieValue(String name, String value, HttpServletRequest request,
			HttpServletResponse response, int interval, boolean httpOnly, boolean secure) {
		Cookie cookie = new Cookie(name, value);
		cookie.setValue(value);
		cookie.setPath("/");
		cookie.setMaxAge(interval);
		cookie.setSecure(secure);
		cookie.setHttpOnly(httpOnly);
		response.addCookie(cookie);
	}

}