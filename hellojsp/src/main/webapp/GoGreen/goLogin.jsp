<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	String id = request.getParameter("userid");
	String pw = request.getParameter("userpw");
	
	
	
	// 아이디가 abc, 비밀번호가 123 이면 로그인 성공
	// id != null && id.equals("abc");  = 다른 방법
	
	if("abc".equalsIgnoreCase (id) && "123".equalsIgnoreCase(pw)){
		// 로그인 성공
		// 요청할 페이지 정보
		response.sendRedirect("gogreen.jsp?name=" + id);
		
	} else{
		response.sendRedirect("gogreen.jsp?loginErr=Y");
	}
%>
</body>
</html>