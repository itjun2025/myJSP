<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="dto.Member"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
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
	//리스트 생성(object로 지정 -> 어떤 타입이든지 저장 가능)
	List<Object> aList = new ArrayList<Object>();
	aList.add("유해진");
	aList.add(new Member("test","","하니","2023-06-19"));
	
	// 페이지 영역에 리스트 객체를 저장
	pageContext.setAttribute("aList", aList);
%>

	<h2>List 컬렉션</h2>
	<ul>
		<li>0번째 요소 : ${aList[0] }</li>
		
		<!-- 영역을 지정하지 않았지만 페이지 영역에 저장된 aList의 값이 출력  -->
		<li>1번째 요소 : 
		<p>${aList[1] }</p>
		<p>id : ${aList[1].id }</p>
		<p>name :${aList[1].name }</p></li>
		
		<!-- 예외가 발생하지 않고 출력되지 않음 -->
		<li>2번째 요소 : ${aList[2] }</li>
	</ul>
	
	<h2>Map 컬렉션</h2>
	<%
		Map<String,String> map = new HashMap<String, String>();
		map.put("한글", "훈민정음");
		map.put("Eng","English");
		
		pageContext.setAttribute("map", map);
	%>
	
	<!-- map은 키값을 접근!! -->
	<ul>
		<li>한글 : ${map['한글'] }</li>
		<!-- el코드 주석처리방법 : 앞에 \를 붙힘 -->
		<li>한글 : \${map.한글 }</li>   
		<li><%=map.get("한글") %></li>
		<li>영문 : ${map.Eng }</li>
		<li><%=map.get("Eng") %></li>
	</ul>
</body>
</html>























