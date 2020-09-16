
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
<title>memberEdit.jsp</title>
</head>
<body>
	<div class="container">
		<h3>회원정보 수정</h3>
		
		
			<form action="modify.wow" method="post">
				<input name="memId" type="hidden" value="${mem.memId }">
				<table class="table table-striped table-bordered">
					<tbody>
						<tr>
							<th>아이디</th>
							<!-- required="required"는 작성하지않으면 안넘어 -->
							<td>${mem.memId }</td>
						</tr>
						<tr>
							<th>비밀번호</th>
							<td><input type="password" name="memPass"
								class="form-control input-sm" pattern="\w{4,}"
								title="알파벳과 숫자 4글자 이상 입력해주세요"></td>
						</tr>
						<tr>
							<th>회원명</th>
							<td><input type="text" name="memName"
								value="${mem.memName }" class="form-control input-sm"
								required="required" pattern="[가-힣]{2,}"
								title="한글로된 두글자 이상의 이름을 입력해주세요"></td>
						</tr>
						<tr>
							<th>우편번호</th>
							<td><input type="text" value="${mem.memZip }" name="memZip"
								class="form-control input-sm"></td>
						</tr>
						<tr>
							<th>주소</th>
							<td><input type="text" name="memAdd1"
								value="${mem.memAdd1 }" class="form-control input-sm">
							<input type="text" name="memAdd2"
								value="${mem.memAdd2 }" class="form-control input-sm"></td>
						</tr>
						<tr>
							<th>핸드폰번호</th>
							<td><input type="text" name="memHp" value="${mem.memHp }"
								class="form-control input-sm"></td>
						</tr>
						<tr>
							<th>메일</th>
							<td><input type="email" name="memMail"
								value="${mem.memMail }" class="form-control input-sm"
								required="required"></td>
						</tr>
						<tr>
							<th>생일</th>
							<td><input type="date" name="memBir" value="${mem.memBir }"
								class="form-control input-sm"></td>
						</tr>
						<tr>
							<th>직업</th>
							<td><select name="memJob" class="form-control input-sm">
									<option value="">-- 직업 선택 --</option>
									<c:forEach items="${ jobList }" var="code">
										<option value="${code.commCd }"
											${code.commCd eq mem.memJob ? "selected='selected'": "" }>${code.commNm}</option>
									</c:forEach>
							</select></td>
						</tr>
						<tr>
							<th>취미</th>
							<td><select name="memLike" class="form-control input-sm">
							<option value="">-- 취미 선택 --</option>
								<c:forEach items="${hobbyList}" var="code">
								<option value=${code.commCd } ${code.commCd eq mem.memLike ? "selected='selected'": "" } >${code.commNm}</option>
								</c:forEach>
					</select></td>
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
							
								<button type="submit" class="btn-primary">
									<span class="glyphicon glyphicon-thumbs-up" aria-hidden="true">&nbsp;수정
								</button>
								</td>
						</tr>
					</tbody>
				</table>
			</form>
	</div>

</body>
</html>