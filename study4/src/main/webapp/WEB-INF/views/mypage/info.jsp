
<%@page import="com.study.member.vo.MemberVO"%>
<%@page import="com.study.exception.BizNotFoundException"%>
<%@page import="com.study.member.service.MemberServiceImpl"%>
<%@page import="com.study.member.service.IMemberService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<%@ include file="/WEB-INF/inc/header.jsp"%>
<title>memberView.jsp</title>
</head>
<body>
	<%@ include file="/WEB-INF/inc/top.jsp"%>
	<div class="container">

		<h3>회원상세정보</h3>
		<!-- * -->
		<table class="table table-striped table-bordered">
			<tbody>
				<tr>
					<th>아이디</th>
					<td>${mem.memId}</td>
				</tr>
				<tr>
					<th>회원명</th>
					<td>${mem.memName}</td>
				</tr>
				<tr>
					<th>핸드번호</th>
					<td>${mem.memHp}</td>
				</tr>
				<tr>
					<th>메일</th>
					<td>${mem.memMail}</td>
				</tr>
				<tr>
					<th>생일</th>
					<td>${mem.memBir}</td>
				</tr>
				<tr>
					<th>직업</th>
					<td>${mem.memJobNm}</td>
				</tr>
				<tr>
					<th>우편번호</th>
					<td>${mem.memZip}</td>
				</tr>
				<tr>
					<th>주소</th>
					<td>${mem.memAdd1}${mem.memAdd2}</td>
				</tr>
				<tr>
					<th>취미</th>
					<td>${mem.memLikeNm}</td>
				</tr>
				<tr>
					<th>마일리지</th>
					<td>${mem.memMileage}</td>
				</tr>
				<tr>
					<th>탈퇴여부</th>
					<td>${mem.memDelete}</td>
				</tr>

				<tr>
					<td colspan="2">
					<%-- ${param.memId} , <%=rs.getString("mem_id") %> 모두 같음 <%=memId%> 자리에 올 수 있는것들 --%>
					<a href="edit.wow?memId=${mem.memId}" class="btn btn-info btn-sm"> <span
						class="glyphicon glyphicon-knight" aria-hidden="true"></span>
						&nbsp;수정
					</a>
					</td>
				</tr>

			</tbody>
		</table>
	</div>

</body>
</html>