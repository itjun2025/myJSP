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
	// name속성의 값을 매개값을 넘겨주면 value속성의 값을 반환합니다.
	String id = request.getParameter("userid");
	String pw = request.getParameter("userpw");
	
	
	
	// 아이디가 abc, 비밀번호가 123 이면 로그인 성공
	// id != null && id.equals("abc");  = 다른 방법
	
	if("abc".equalsIgnoreCase (id) && "123".equalsIgnoreCase(pw)){
		

		out.print("로그인성공");
		response.sendRedirect("login.jsp?name="+id);
		
%>
		<% 
		} else{
			out.print("로그인 실패");
		}
		%>
</body>
</html>