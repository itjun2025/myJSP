<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import ="java.util.Arrays" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%
		// 한글깨짐 처리 => web,xml에서 설정파일에서 설정 
		// post 방식을 할경우 깨질 수도 있어서 넣어야됨 	
		request.setCharacterEncoding("UTF-8");
	
		String id = request.getParameter("id");
		String sex = request.getParameter("sex");
		
		// 체크박스는 여러개가 선택될 수 있기 때문에 배열형태로 받아서 처리해야 합니다.
		String[] favo = request.getParameterValues("favo");
		
		// 줄바꿈이 있는경우 \r\n -> <br>역할
		String intro = request.getParameter("intro");
		String favoStr = "";
		out.print(Arrays.toString(favo));
		
	%>
	<ul>
		<li>아이디 	: <%=id %></li>
		<li>성별  	: <%=sex %></li>
		<li>관심사항  : <%=Arrays.toString(favo) %></li>
		<!-- 줄바꿈 처리 -->
		<li>자기소개  : <%=intro.replace("\r\n","<br>") %></li>
	</ul>
	
</body>
</html>