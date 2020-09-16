
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
	
<!DOCTYPE html>
<html lang="ko">
<head>
<%@include file="/WEB-INF/inc/header.jsp"%>
<title>자유게시판 - 글 수정</title>
</head>
<body>
<%@ include file="/WEB-INF/inc/top.jsp"%>
<div class="container">

  <div class="page-header">
    <h3>자유게시판 - <small>글 수정</small></h3>
  </div>
	<%-- <form action="freeModify.wow" method="post"> --%>
	<form:form action="freeModify.wow" modelAttribute="board">
	<%-- <input type="hidden" name="boNo" value="${board.boNo }"> --%>	
	<form:hidden path="boNo"/>
	<table class="table table-striped table-bordered">
		<colgroup>
			<col width="20%" />
			<col/>
		</colgroup>
		<tr>
      <th>글번호</th>
      <td>${board.boNo }</td>
    </tr>
		<tr>
			<th>제목</th>
			<td>
				<%-- <input type="text" name="boTitle" value="${board.boTitle }" class="form-control input-sm"> --%>
				<form:input path="boTitle" cssClass="form-control input-sm"/>
				<form:errors path="boTitle" />
			</td>
		</tr>
		<tr>
			<th>작성자</th>
			<td>
				<%-- <input type="text" name="boWriter" value="${board.boWriter }"  class="form-control input-sm" > --%>
				<form:input path="boWriter" cssClass="form-control input-sm"/>
				<form:errors path="boWriter" />
			</td>
		</tr>
		<tr>
			<th>비밀번호</th><!-- required="required" pattern="\w{4,}"  -->
			<td>
				<input type="password" name="boPass" value="" class="form-control input-sm" 
			           title="알파벳과 숫자로 4글자 이상 입력" >
				   <div class="text-danger">
				    <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span> 
				    글 등록시에 입력한 비밀번호를 입력하세요.
				  </div> 
				<form:errors path="boPass" />
			</td>
		</tr>
		<tr>
			<th>분류</th>
			<td>
				<%-- <select name="boCategory" class="form-control input-sm" >
					<option value="">-- 선택하세요--</option>		
					<c:forEach items="${cateList }" var="code">
						<option value="${code.commCd }" 
						${code.commCd eq board.boCategory ? "selected='selected'": '' } ">${code.commNm}</option>
					</c:forEach>
				</select> --%>
				<form:select path="boCategory" cssClass="form-control input-sm">	
					<option value="">-- 선택하세요--</option>		
					<form:options items="${cateList}" itemLabel="commNm" itemValue="commCd" />
				</form:select>
				<form:errors path="boCategory" />
			</td>
		</tr>					
		<tr>
			<th>내용</th>
			<td>
				<%-- <textarea rows="10" name="boContent" class="form-control input-sm"> ${board.boContent}</textarea> --%>
				<form:textarea path="boContent" cssClass="form-control input-sm" rows="10" />
			</td>
		</tr>
    <tr>
      <th>IP</th>
      <td>${board.boIp }</td>
    </tr>
    <tr>
      <th>조회수</th>
      <td>${board.boHit }</td>
    </tr>
    <tr>
      <th>등록일자</th>
      <td>${board.boRegDate }</td>
    </tr>   		
		<tr>
			<td colspan="2">
          <div class="pull-left">
              <a href="freeList.wow" class="btn btn-default btn-sm"> 
                <span class="glyphicon glyphicon-list" aria-hidden="true"></span>
                &nbsp;&nbsp;목록
              </a>
            </div>
            <div class="pull-right">
              <!-- 문제점 : 사용자가 입력박스에서 엔터를 치면 첫번째 submit의 formaction 방향으로 이동한다.  -->
              <button type="submit"  formaction="freeDelete.wow" class="btn btn-sm btn-danger"> 
                <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
                &nbsp;&nbsp;삭제
              </button>
              <button type="submit" class="btn btn-sm btn-primary" > 
                <span class="glyphicon glyphicon-save" aria-hidden="true"></span>
                &nbsp;&nbsp;저장
              </button>
            </div>					
			</td>
		</tr>
	</table>
	<%-- </form> --%>
	</form:form>
</div><!-- container -->
</body>
</html>


