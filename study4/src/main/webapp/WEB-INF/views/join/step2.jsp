<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>    
<!DOCTYPE html>
<html lang="ko">
<head>
<%@include file="/WEB-INF/inc/header.jsp"%>
<title>회원가입 2단계</title>
</head>
<body>
<%@include file="/WEB-INF/inc/top.jsp"%>
<div class="container">

<div class="row col-md-8 col-md-offset-2">
	<div class="page-header">
		<h3>회원가입 2단계</h3>
	</div>

	<form:form modelAttribute="memberJoin" action="step3">
	<form:errors path="" />
	<table class="table" >
		<colgroup>
			<col width="20%" />
			<col />
		</colgroup>
		<tr>
			<th>ID</th>
			<td>
				<form:input path="memId" cssClass="form-control input-sm" />
				<form:errors path="memId" />
			</td>
		</tr>
		<tr>
			<th>비밀번호</th>
			<td>
				<form:password path="memPass" cssClass="form-control input-sm" />
				<form:errors path="memPass" />
			</td>
		</tr>
		<tr>
			<th>비밀번호 확인</th>
			<td>
				<form:password path="memPassConfirm" cssClass="form-control input-sm" />
			</td>
		</tr>
		<tr class="form-group-sm">
			<th>회원명</th>
			<td>
				<form:input path="memName" cssClass="form-control input-sm"/>
				<form:errors path="memName" />
			</td>
		</tr>
		<tr class="form-group-sm">
			<th>이메일</th>
			<td>
				<form:input path="memMail" cssClass="form-control input-sm"/>
				<form:errors path="memMail" />
			</td>
		</tr>		
		<tr>
			<td colspan="2">
				<div class="pull-left" >
					<a href="${pageContext.request.contextPath}/join/cancel" class="btn btn-sm btn-default" >
						<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
						&nbsp;&nbsp;취 소
					</a>
				</div>
				<div class="pull-right">
					<button type="submit" class="btn btn-sm btn-primary" >
						<span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span> 
						&nbsp;&nbsp;다 음 
					</button>
				</div>
			</td>
		</tr>	
	</table>
	</form:form>
</div>

</div> <!-- END : 메인 콘텐츠  컨테이너  -->
</body>
</html>



