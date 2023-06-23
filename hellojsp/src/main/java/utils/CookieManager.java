package utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieManager {

	public CookieManager() {
		// TODO Auto-generated constructor stub
	}
	
	// 쿠키이름, 값, 유지시간을 매개변수로 받아서
	// 쿠키를 생성후 응답 객체에 담기
	public static void makeCookie(
			HttpServletResponse response
			,String cName, String cValue, int cTime) {
		
				// 	쿠키 생성하기
				Cookie cookie = new Cookie(cName,cValue);
				
				// 경로 설정하기
				cookie.setPath("/");
				
				// 유지시간 설정(초단위로 지정)
				cookie.setMaxAge(cTime);
				
				// 응답객체에 담기
				response.addCookie(cookie);
	}
	
	// 쿠키이름을 받아서 쿠키값을 반환
	public static String readCookie(HttpServletRequest request
			, String cName) {
		String cookievalue = "";
		
		Cookie[] cookies = request.getCookies();
		if(cookies != null) {
			// 쿠키 배열을 돌면서 일치하는 이름을 찾아서 값을 반환
			for(Cookie cookie : cookies) {
				String cookieName = cookie.getName();
				if(cName.equals(cookieName)) {
					cookievalue = cookie.getValue();
					
					break;
				}
			}
		}
		
		return cookievalue;
	}
	
	// 쿠키이름을 매개변수로 받아서 쿠키 삭제
	public static void deleteCookie(HttpServletResponse response 
								, String cName) {
		makeCookie(response,cName,"",0);
	}
}



















