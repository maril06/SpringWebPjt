<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<!-- java beans 규칙에 맞는 객체는 기본적으로 다시 화면으로 객체를 전달한다. -->
	<h2>SampleDTO  ${sampleDTO}</h2>
	
	<!-- 반면에 기본 자료형의 경우는 파라미터로 선언하더라도 기본적으로 화면까지는 전달되지 않는다. -->
	<h2>PAGE ${page }</h2>

</body>
</html>