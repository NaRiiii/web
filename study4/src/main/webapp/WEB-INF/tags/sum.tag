<%@ tag language="java" pageEncoding="UTF-8" body-content="empty"%>
<%@ tag trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ attribute name="begin" required="true" type="java.lang.Integer" %>
<%@ attribute name="end" required="true" type="java.lang.Integer" %>
<%@ attribute name="var" required="true" rtexprvalue="false" type="java.lang.String" %>
<%@ variable alias="result" name-from-attribute="var" scope="AT_END" variable-class="java.lang.String" %>

<h3>기본 sum태그 만들기</h3>
<%
	int sum=0;
	for(int i= begin; i<= end; sum += i, i++){
	}
		out.print(sum);
%>

<hr>

<h3>나중에 사용하기위한 변수 생성</h3>
<%
	sum=0;
	for(int i= begin; i<= end; sum += i, i++){
	}
		out.print(sum);
		jspContext.setAttribute("result", sum);
%>
<%-- <c:set var="result" value="<%=sum %>"/> --%>

