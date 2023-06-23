<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="dto.Member"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h4>일반 for문 형태의 forEach문</h4>
	<!-- 
		forEach 태그를 활용한 반복문
		begin : 시작값
		end   : 종료값 
		step  : 증가값
		var   : 변수
	 -->
	 
	
	 <c:forEach begin="2" end="20" step="2" var="i">
	 	<p>반복${i }입니다</p>
	 </c:forEach>
	 
	 <h4>varStatus 속성 살펴보기</h4>
	 <!-- 
	 	varStatus 루프의 현재 상태를 알려주는 변수 이름
	 	
	 	 current	: var에 지정한 현재 루프의 변숫값 반환
		 index		: var에 지정한 현재 루프의 인덱스값
		 count		: 실제 반복 횟수(1~마지막)
		 first		: 루프의 처음일 때 true 반환
		 last		: 루프의 마지막일 때 true 반환
	 	
	  -->
	  <table border="1">
	  <c:forEach begin="3" end="5" var="i" varStatus="loop">
	  	<tr>
	  		<td>i = ${i }</td>
	  		<td>current : ${loop.current }</td>
	  		<td>index : ${loop.index }</td>
	  		<td>count : ${loop.count }</td>
	  		<td>first : ${loop.first }</td>
	  		<td>last : ${loop.last }</td>
	  		
	  	</tr>
	  </c:forEach>
	  </table>
	  
	  <h4>1~100까지 정수중 홀수의 합</h4>
	  <c:forEach begin="1" end="100" var="num">
	  <!-- <c:if test="${num mod 2 ne 0 }"></c:if>  -->	
	  	<c:if test="${num mod 2 eq 1 }">
	  <c:set var="sum" value="${sum+num }"></c:set>
	  	</c:if>
	  </c:forEach>
	  sum: ${sum }
	  
	  <h4>향상된 for문 형태의 forEach 태그</h4>
	  <!-- 
	current :  현재 루프의 실제 요소를 반환
	index	: 현재 루프의 인덱스를 표시(0~마지막)
	count	: 일반 for문과 동일함
	first	: 일반 for문과 동일함
	last 	: 일반 for문과 동일함
	   -->
	  <%
	  	String[] rgba = {"Red", "Green","Blue","Black"};
	  %>
	  <!-- items속성에 배열을 저장하고 변수이름은 c 로 입력
	  		배열을 처음주터 끝까지 돌면서 반복문을 수행 -->
	  <c:forEach items="<%=rgba %>" var="c" varStatus="loop">
	  	<table border="1">
	  		<tr>
		  		<td><span style="color: ${c}">${c}</span></td>
		  		<td>current : ${loop.current }</td>
		  		<td>index : ${loop.index }</td>
		  		<td>count : ${loop.count }</td>
		  		<td>first : ${loop.first }</td>
		  		<td>last : ${loop.last }</td>
	  		</tr>
	  	</table>
	  </c:forEach>
	  
	  <h4>List 컬렉션 사용하기</h4>
	  <%
	  	List<Member> list = new ArrayList<Member>();
	  	list.add(new Member("id1","","name1",""));
	  	list.add(new Member("id2","","name2",""));
	  	list.add(new Member("id3","","name3",""));
	  	list.add(new Member("id4","","name4",""));
	  %>
	  <c:set var="list" value="<%=list %>"></c:set>
	  <!-- 
	  	items	: 반복을 위한 객체를 지정
	  	var 	: 변수명
	   -->
	  <c:forEach items="${list}" var="member">
	  	<li>아이디: ${member.id }, 이름 : ${member.name }</li>
	  </c:forEach>
	  
	  <h4>Map 컬렉션 사용하기</h4>
	  <%
	  	Map<String , Member> map = new HashMap<String, Member>();
	  	map.put("1st",new Member("id5","","name5",""));
	  	map.put("2st",new Member("id6","","name6",""));
	  	map.put("3rd",new Member("id7","","name7",""));
	  %>
	  <c:set var="map" value="<%=map %>"></c:set>
	  <c:forEach items="${map}" var="map">
	  	<li>
	  		key : ${map.key} <br>
	  		value : ${map.value }<br>
	  		이름 : ${map.value.name }<br>
	  		아이디 ; ${map.value.id }<br>
	  		
	  	</li>
	  </c:forEach>
	  
	  <h4>forTokens태그 사용</h4>
	  <%
	  	String rgb = "Red,Green,Blue,Black";
	  %>
	  <!-- 
	  	Token : 문법적으로 의미 있는 ㅚ소단위
	  			구분자를 기준을 문자열을 나눠 토큰의 갯수만큼 반복 합니다
	  			
	  	items	: 문자열(문자열만 사용가능)
	  	delims	: 구분자
	  	var 	: 변수명
	   -->
	   <c:forTokens items="<%=rgb %>" delims="," var="color">
	   	<span style="color:${color}">${color }</span>
	   </c:forTokens>
</body>
</html>
















