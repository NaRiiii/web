
<%@page import="com.study.member.vo.MemberVO"%>
<%@page import="com.study.exception.BizNotFoundException"%>

<%@page import="com.study.member.service.MemberServiceImpl"%>
<%@page import="com.study.member.service.IMemberService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<%@ include file="/WEB-INF/inc/header.jsp"%>
<title>memberEdit.jsp</title>
</head>
<body>
	<div class="container">
		<h3>회원정보 수정</h3>
		<form:form action="memberModify.wow" modelAttribute="mem">
			<form:hidden path="memId"/>
			<table class="table table-striped table-bordered">
				<tbody>
					<tr>
						<th>아이디</th>
						<!-- required="required"는 작성하지않으면 안넘어 -->
						<td id="memId">${mem.memId }</td>
					</tr>
					<tr>
						<th>비밀번호</th>
						<td>
							<form:password path="memPass" cssClass="form-control input-sm" title="알파벳과 숫자 4글자 이상 입력해주세요" pattern="\w{4,}" />
							<form:errors path="memPass" />	
						</td>
					</tr>
					<tr>
						<th>회원명</th>
						<td>
							<form:textarea path="memName" cssClass="form-control input-sm" title="한글로된 두글자 이상의 이름을 입력해주세요"  pattern="[가-힣]{2,}"/>
							<form:errors path="memName"/>
						</td>
					</tr>
					<tr>
						<th>우편번호</th>
						<td>
							<form:input path="memZip" cssClass="form-control input-sm"  />
							<form:errors path="memZip"/>
						</td>
					</tr>
					<tr>
						<th>주소</th>
						<td>
							<form:input path="memAdd1" cssClass="form-control input-sm"/>
							<form:errors path="memAdd1"/>
							<form:input path="memAdd2" cssClass="form-control input-sm"/>
							<form:errors path="memAdd2"/>
						</td>
					</tr>
					<tr>
						<th>핸드폰번호</th>
						<td>
							<form:input path="memHp" cssClass="form-control input-sm"/>
						</td>
					</tr>
					<tr>
						<th>메일</th>
						<td>
							<form:input path="memMail" cssClass="form-control input-sm"/>
							<form:errors path="memMail"/>
						</td>
					</tr>
					<tr>
						<th>생일</th>
						<td>
							<input type="date" name="memBir" value="${mem.memBir }"
							class="form-control input-sm">
						</td>
					</tr>
					<tr>
						<th>직업</th>
						<td>
							<select name="memJob" class="form-control input-sm">
								<option value="">-- 직업 선택 --</option>
								<c:forEach items="${ jbList }" var="code">
									<option value="${code.commCd }"
										${code.commCd eq mem.memJob ? "selected='selected'": "" }>${code.commNm}</option>
								</c:forEach>
							</select>
							<form:errors path="memJob"/>
						</td>
					</tr>
					<tr>
						<th>취미</th>
						<td>
							<select name="memLike" class="form-control input-sm">
								<option value="">-- 취미 선택 --</option>
								<c:forEach items="${hbList}" var="code">
									<option value=${code.commCd }
										${code.commCd eq mem.memLike ? "selected='selected'": "" }>${code.commNm}</option>
								</c:forEach>
							</select>
							<form:errors path="memLike"/>
						</td>
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
								<span class="glyphicon glyphicon-thumbs-up" aria-hidden="true">&nbsp;저장


								
							</button> <a href="memberList.wow" class="btn btn-info btn-sm"> <span
								class="glyphicon glyphicon-knight" aria-hidden="true"></span>
								&nbsp;목록
						</a> <!-- <button type="submit" class="btn btn-sm btn-danger">
									<span
							class="glyphicon glyphicon-remove" aria-hidden="true"></span>
							&nbsp;삭제
								</button> --> <a href="#" onclick="f_post()"
							class="btn btn-sm btn-danger"> <span
								class="glyphicon glyphicon-remove" aria-hidden="true"></span>
								&nbsp;삭제
						</a>
						</td>
					</tr>
				</tbody>
			</table>
		</form:form>
	</div>
	<script type="text/javascript">
		function f_post() {
			memId = document.getElementById("memId");
			memPass = document.getElementsByName("memPass");
			var f = document.forms['formmember']; //폼 name
			f.memId = memId; //POST방식으로 넘기고 싶은 값
			f.memPass = memPass;//POST방식으로 넘기고 싶은 값
			f.action = "memberDelete.wow";//이동할 페이지
			f.method = "post";//POST방식
			f.submit();
		}
	</script>

</body>
</html>