<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- 
			http://localhost:8080/sample/ex04?name=aaa&age=11&page=9
			------------------------------------------------------------------------
			SampleDTO SampleDTO(name=aaa, age=11)
			PAGE
			
			int타입으로 선언된 page는 전달되지 않는다.
	 -->
	<h2>SampleDTO   ${sampleDTO }</h2>
	<h2>PAGE   ${page }</h2>
</body>
</html>