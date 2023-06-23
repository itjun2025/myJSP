<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>쿠키값 출력하기</title>
</head>
<body>
	<h2>쿠키값 확인하기</h2>
	<p>처음 접속시 저장된 쿠키가 없는경우 빈페이지가 출력될 수 있습니다</p>
	<%	
		Cookie[] cookies = request.getCookies(); // 모든 쿠키 가져오기
		if (cookies != null) {
	    for (Cookie cookie : cookies) {
	        String name = cookie.getName(); 	// 쿠키의 이름 가져오기
	        String value = cookie.getValue(); 	// 쿠키의 값 가져오기
	        
	        // 필요한 작업 수행
	        out.print("쿠키명 : "+ name + "<br>" +
	        "쿠키값 : "+ value + "<br><br>");
	        
	    }
	}
	%>
</body>
</html>