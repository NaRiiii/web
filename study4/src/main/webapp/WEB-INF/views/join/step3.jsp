<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>    
<!DOCTYPE html>
<html lang="ko">
<head>
<%@include file="/WEB-INF/inc/header.jsp"%>
<title>회원가입 3단계</title>
</head>
<body>
<%@include file="/WEB-INF/inc/top.jsp"%>
<div class="container">

<div class="row col-md-8 col-md-offset-2">
	<div class="page-header">
		<h3>회원가입 3단계</h3>
	</div>

	<form:form modelAttribute="memberJoin" action="regist">
	<table class="table" >
		<colgroup>
			<col width="20%" />
			<col />
		</colgroup>
		
		<tr class="form-group-sm">
			<th>생일</th>
			<td>
				<form:input path="memBir" cssClass="form-control input-sm col-md-5"/>
				<form:errors path="memBir" />
			</td>
		</tr>
		<tr class="form-group-sm">
			<th>우편번호</th>
			<td>
				<form:input path="memZip" cssClass="form-control input-sm"/>
				<form:errors path="memZip" />
			</td>
		</tr>
		<tr class="form-group-sm">
			<th>주소</th>
			<td>
				<form:input path="memAdd1" cssClass="form-control input-sm"/>
				<form:errors path="memAdd1" />
				<form:input path="memAdd2" cssClass="form-control input-sm"/>
				<form:errors path="memAdd2" />
			</td>
		</tr>
		<tr  class="form-group-sm">
			<th>핸드폰</th>
			<td>
				<form:input path="memHp" cssClass="form-control"/>
				<form:errors path="memHp" />
			</td>
		</tr>	
		<tr>
			<th>직업 </th>
			<td>
				<div class="form-group-sm">
				<select name="memJob" class="form-control" >
					<option value="" >직업을 선택하세요</option>
					<c:forEach var="code" items="${jbList}">
						<c:if test="${memJob == code.commCd}">
							<option value="${code.commCd}"  selected="selected" >${code.commNm}</option>
						</c:if>
						<c:if test="${memJob ne code.commCd}">
							<option value="${code.commCd}" >${code.commNm}</option>
						</c:if>
					</c:forEach>	
				</select>
				<form:errors path="memJob" />
				</div>
			</td>
		</tr>
		<tr>
			<th>취미</th>
			<td class="form-group-sm">
				<select name="memLike" class="form-control" >
					<option value="" >취미를 선택하세요</option>
					<c:forEach var="code" items="${hbList}">
						<c:if test="${memLike == code.commCd}">
							<option value="${code.commCd}"  selected="selected" >${code.commNm}</option>
						</c:if>
						<c:if test="${memJob ne code.commCd}">
							<option value="${code.commCd}" >${code.commNm}</option>
						</c:if>
					</c:forEach>			
				</select>
				<form:errors path="memLike" />
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

</div></body>
</html>



