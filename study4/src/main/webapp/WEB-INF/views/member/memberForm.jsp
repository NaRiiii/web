<%@page import="com.study.code.vo.CodeVO"%>
<%@page import="java.util.List"%>
<%@page import="com.study.code.service.CommonCodeServiceImpl"%>
<%@page import="com.study.code.service.ICommonCodeService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="mytag" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<%@ include file="/WEB-INF/inc/header.jsp"%>
<title>memberForm.jsp</title>
</head>
<body>
	<div class="container">

		<h3>회원가입</h3>
		<form:form action="memberRegist.wow" modelAttribute="mem">
			<table class="table table-striped table-bordered">
				<tbody>
					<tr>
						<th>아이디</th>
						<!-- required="required"는 작성하지않으면 안넘어 -->
						<td>
							<form:input path="memId" cssClass="form-control input-sm" pattern="\w{4,12}" title="알파벳과 숫자 4글자 이상 12글자 이하로 입력해주세요" />
							<form:errors path="memId"/>
						</td>
					</tr>
					<tr>
						<th>비밀번호</th>
						<td>
							<form:password path="memPass" cssClass="form-control input-sm" pattern="[\w!-/]{4,12}" title="알파벳과 숫자, 특수문자 4글자 이상 12글자 이하로 입력해주세요" />
							<form:errors path="memPass"/>
						</td>
					</tr>
					<tr>
						<th>회원명</th>
						<td>
							<form:input path="memName" cssClass="form-control input-sm" pattern="[가-힣]{2,}" title="한글로된 두글자 이상의 이름을 입력해주세요"/>
							<form:errors path="memName"/>
						</td>
					</tr>
					<tr>
						<th>우편번호</th>
						<td>
							<form:input path="memZip" cssClass="form-control input-sm"/>
							<form:errors path="memZip"/>
						</td>
					</tr>
					<tr>
						<th>주소</th>
						<td class="form-inline">
							<form:input path="memAdd1" cssClass="form-control input-sm" />
							<form:errors path="memAdd1"/>
							<form:input path="memAdd2" cssClass="form-control input-sm" />
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
						<input type="date" name="memBir"
							class="form-control input-sm">
							</td>
					</tr>
					<tr>
						<th>직업</th>
						<td>
							 <mytag:select items="${jbList}" name="memJob" itemLabel="commNm" value="${myJob}" itemValue="commCd" class="form-control input-sm" required="required" id="id_memJob">
							 	<option value="">-- 직업 선택 --</option>
							 </mytag:select>
							<form:errors path="memJob"/>
						</td>
					</tr>
					<tr>
						<th>취미</th>
						<td>
							<select name="memLike" class="form-control input-sm" required="required">
								<option value="">-- 취미 선택 --</option>
								<c:forEach items="${hbList}" var="code">
									<option value="${code.commCd }">${code.commNm}</option>
									<!-- <option value="HB01">서예</option> -->
								</c:forEach>
							</select>
							<form:errors path="memLike"/>
						</td>
					</tr>
					<tr>
						<td colspan="2"><button type="submit" class="btn-primary">
								<span class="glyphicon glyphicon-thumbs-up" aria-hidden="true">&nbsp;회원가입
							</button> <a href="#" class="btn btn-info btn-sm"> <span
								class="glyphicon glyphicon-knight" aria-hidden="true"></span>
								&nbsp;그냥 링크
						</a></td>
					</tr>
				</tbody>
			</table>
		</form:form>
	</div>

</body>
</html>