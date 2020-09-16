<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix ="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%
    request.setCharacterEncoding("UTF-8");
    %>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/inc/header.jsp" %>
<title>/uploadForm.jsp</title>
</head>
<body>
<%@ include file="/WEB-INF/inc/top.jsp" %>
<div class="container">

	<h4>첨부파일 테스트</h4>
<form action="<c:url value='/upload/result' />" method="post"
enctype="multipart/form-data" >
제 목 : <input type="text" name="boTitle" /> <br>
작성자 : <input type="text" name="boWriter" /> <br>
첨부파일 : <input type="file" name="boFile" /> <br>
<button type="submit">저장</button>
</form>

</div><!--container-->
</body>
</html>