<%@page import="model2.mvcboard.MVCBoardDto"%>
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
	<h2>MVC 모델2 게시판</h2>
	
	
	<%@include file="../6세션/Link.jsp" %>
	<h4>총 게시물수 : ${totalCnt }</h4>
	<!-- 검색 폼 -->
	<form method="get" name="searchForm">
    <table border="1" width="90%">
    
    <!-- 페이지 번호 -->
    <input type="hidden" name="pageNo">
    <tr>
        <td align="center">
        	<!-- 테스트  
        	searchField : [${param.searchField }]
        	비교결과 : [${param.searchField eq"content"}];
        	 -->
            <select name="searchField"> 
                <option value="title">제목</option> 
                <option value="content"
                	 ${param.searchField eq"content" ? "selected" : ""}>내용</option>
            </select>
            <input type="text" name="searchWord"  value="${param.searchWord }"/>
            <input type="submit" value="검색하기" />
        </td>
    </tr>   
    </table>
    </form>
	
	<c:set var="fileList" value="${requestScope.list }"/>
	
		<table border="1" width="90%">
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>작성자</th>
				<th>조회수</th>
				<th>작성일</th>
				<th>첨부</th>
			</tr>
			
			<c:choose>
				<c:when test="${empty list }">
					<tr>
						<td colspan="6">등록된 게시물이 없습니다</td>
					</tr>
				</c:when>
				<c:otherwise>
					<!-- 게시물 몰곡 출력 -->
					<c:forEach items="${list }" var="row" varStatus="loop">
						<tr>
							<td>${row.idx }</td>
							<td><a href="../mvcboard/view.do?idx=${row.idx }" >${row.title }</a></td>
							<td>${row.name }</td>
							<td>${row.visitcount }</td>
							<td>${row.postdate }</td>
							<td>첨부파일</td>
						</tr>
					</c:forEach>
				
				</c:otherwise>
			</c:choose>
			
			
			</table>
			
			<!-- 글쓰기 버튼 추가
					글쓰기 버튼 클릭시 페이지로 이동 -> 글쓰기 버튼 클릭하면 글등록 -->
	<table border="1" width="90%">
		<tr>
			<td align="right">
				<button onclick="location.href = '../mvcboard/write.do'">글쓰기</button>
			</td>
		</tr>
	</table>				
					
					
					
	<table border="1" width="90%">
		<tr>
			<td align="center">
				<%@include file="../9페이지/PageNavi.jsp" %>
			</td>
		</tr>
	</table>
		



</body>
</html>