<%@page import="common.JSFunction"%>
<%@page import="dao.BoardDao"%>
<%@page import="dto.Board"%>
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
	request.setCharacterEncoding("utf-8");
	String num = request.getParameter("num");
	out.print(num);
	
	
	BoardDao dao = new BoardDao();
	int res = dao.delete(num);
	
	if(res>0){
		// 성공 : 메세지 출력 상세페이지로 이동
		JSFunction.alertLocation("삭제되었습니다", "Board.jsp", out);
	}else{
		// 실패 : 메세지 출력 이전페이지로 이동
		JSFunction.alertBack("삭제중 오류가 발생하였습니다.", out);
	}
%>
</body>
</html>