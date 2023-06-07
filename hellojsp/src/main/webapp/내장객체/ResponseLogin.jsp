<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 처리</title>
</head>
<body>

<%
	String id = request.getParameter("user_id");
	String pw = request.getParameter("user_pwd");
	
	out.print("id : "+ id + "<br>");
	out.print("pw : "+ pw +	"<br>");
	
	// 아이디가 abc, 비밀번호가 123 이면 로그인 성공
	// id != null && id.equals("abc");  = 다른 방법
	
	if("abc".equals(id) && "123".equals(pw)){
		// 로그인 성공
		out.print("로그인 성공");
		// 요청할 페이지 정보
		response.sendRedirect("ResponseWelcome.jsp");
	} else{
		out.print("로그인 실패");
		response.sendRedirect("ResponseMain.jsp?loginErr=Y");
	}
%>
</body>
</html>