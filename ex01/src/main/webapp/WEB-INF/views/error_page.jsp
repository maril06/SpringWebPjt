<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>
<%@ page session="false" import="java.util.*" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h4><c:out value="${exception.getMessage() }"></c:out></h4>
	<ul>
		<c:forEach items="${exception.getStackTrace() }" var="stack">
			<li><c:out value="${stack }"></c:out>
		</c:forEach>
	</ul>
<!--  
 	JSTL((JavaServer Pages Standard Tag Library)
 	 : JSTL은 라이브러리이기 때문에 사용하기전에 core를 header에 추가해주어야 한다.
 	 : JSTL core의 태그들 : c:set, c:out, c:if, c:choose, c:when 등.
 	 
 	 EL(Expression Language)
 	 
 	 http://localhost:8080/sample/ex04?name=aaa&age=bbb&page=9
 -->
	
</body>
</html>