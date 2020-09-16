
<%@page import="com.study.code.vo.CodeVO"%>
<%@page import="com.study.code.service.ICommonCodeService"%>
<%@page import="com.study.code.service.CommonCodeServiceImpl"%>
<%@page import="com.study.member.vo.MemberVO"%>
<%@page import="com.study.member.service.MemberServiceImpl"%>
<%@page import="com.study.member.service.IMemberService"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="mytag"  tagdir="/WEB-INF/tags"%>
    <%
	request.setCharacterEncoding("utf-8");
%>
<!DOCTYPE html>
<html lang="ko">
<head>
	<%@ include file="/WEB-INF/inc/header.jsp" %>
	<title>memberList.jsp </title>
</head>
<body>
	<%@ include file="/WEB-INF/inc/top.jsp" %>
 <div class="container">	
	<h3>회원목록</h3>		

<!-- START : 검색 폼  -->
		<div class="row panel panel-default collapse" id="id_search_area">
			<div class="panel-body">
				<form name="frm_search" action="memberList.wow" method="post"
					class="form-horizontal">
					<input type="hidden" name="curPage" value="${searchVO.curPage }"> <input
						type="hidden" name="rowSizePerPage" value="${searchVO.rowSizePerPage }">
					<div class="form-group">
						<label for="id_searchType" class="col-sm-2 control-label">검색</label>
						<div class="col-sm-2">
							<select id="id_searchType" name="searchType"
								class="form-control input-sm" >
								<option value="NM" ${"NM" eq searchVO.searchType ? "selected='selected'": ""} >이름</option>
								<option value="ID" ${"ID" eq searchVO.searchType ? "selected='selected'": ""}>아이디</option>
								<option value="HP" ${"HP" eq searchVO.searchType ? "selected='selected'": ""}>전화번호</option>
								<option value="ADD" ${"ADD" eq searchVO.searchType ? "selected='selected'": ""}>주소</option>
							</select>
						</div>
						<div class="col-sm-2">
							<input type="text" name="searchWord"
								class="form-control input-sm" value="${searchVO.searchWord }" placeholder="검색어">
						</div>
						<label for="id_searchCategory"
							class="col-sm-2 col-sm-offset-2 control-label">직업</label>
						<div class="col-sm-2">
							<select id="id_searchCategory" name="searchJob"
								class="form-control input-sm">
								<option value="">-- 전체 --</option>
								<c:forEach items="${jbList}" var="code">
									<option value="${code.commCd }" ${code.commCd eq searchVO.searchJob ? "selected='selected'": ""}>${code.commNm}</option>
								</c:forEach>
							</select>
						</div>
						<label for="id_searchCategory"
							class="col-sm-8 col-sm-offset-2 control-label">취미</label>
						<div class="col-sm-2">
							<select id="id_searchCategory" name="searchLike"
								class="form-control input-sm">
								<option value="">-- 전체 --</option>
								<c:forEach items="${hbList}" var="code">
									<option value="${code.commCd }" ${code.commCd eq searchVO.searchLike ? "selected='selected'": ""}>${code.commNm}</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-2 col-sm-offset-9 text-right">
							<button type="button" id="id_btn_reset"
								class="btn btn-sm btn-default">
								<i class="fa fa-sync"></i> &nbsp;&nbsp;초기화
							</button>
						</div>
						<div class="col-sm-1 text-right">
							<button type="submit" class="btn btn-sm btn-primary ">
								<i class="fa fa-search"></i> &nbsp;&nbsp;검 색
							</button>
						</div>
					</div>
				</form>
			</div>
		</div>
		<!-- END : 검색 폼  -->
		<!-- START : 목록건수 및 새글쓰기 버튼  -->
		<div class="row" style="margin-bottom: 10px;">
			<div class="col-sm-3 form-inline">
				전체 ${searchVO.totalRowCount} 건 조회 <select id="id_rowSizePerPage"
					name="rowSizePerPage" class="form-control input-sm">
					<option value="10"${searchVO.rowSizePerPage eq 10 ? "selected='selected'" : "" }>10</option>
					<option value="20"${searchVO.rowSizePerPage eq 20 ? "selected='selected'" : "" }>20</option>
					<option value="30"${searchVO.rowSizePerPage eq 30 ? "selected='selected'" : "" }>30</option>
					<option value="50"${searchVO.rowSizePerPage eq 50 ? "selected='selected'" : "" }>50</option>
				</select>
			</div>
			<div id="btn" class="col-xs-6 col-sm-2 col-sm-offset-6 text-right" data-toggle="collapse" data-target="#id_search_area" aria-expanded="false">
				<a href="#" class="btn btn-info btn-sm">
				<i class="fas fa-chevron-down"></i>
					<span>&nbsp;검색열기</span>
				</a>
			</div>
			<div class="row">
				<a href="memberForm.wow" class="btn btn-primary btn-sm pull-right">회원등록</a>
			</div>
		</div>
		<!-- END : 목록건수 및 새글쓰기 버튼  -->
			
			
	<table class="table table-striped table-bordered">
		<caption class="hidden+
		">회원목록 조회</caption>	
		<colgroup>
			<col style="width: 15%" />
			<col />
			<col style="width: 15%" />
			<col style="width: 15%" />
			<col style="width: 15%" />
			<col style="width: 15%" />
		</colgroup>
		<thead>
			<tr>
				<th>ID</th>
				<th>회원명</th>
				<th>HP</th>
				<th>주소</th>
				<th>생일</th>
				<th>직업</th>
				<th>마일리지</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach var="mem" items="${members}"  >
			<tr>
				<td>${mem.memId }</td>
				<td><a href="memberView.wow?memId=${mem.memId }">${mem.memName }</a></td>
				<td>${mem.memHp }</td>
				<td>${mem.memAdd1 } ${mem.memAdd2 }</td>
				<td>${mem.memBir }</td>
				<td>
				<c:forEach var="code" items="${jbList }">
 				${code.commCd eq mem.memJob ? code.commNm: ""}
				</c:forEach>
				</td>

				<%-- <td>${mem.memJob}</td> --%>
				<td>${mem.memMileage }</td>
			</tr>
		</c:forEach>
		</tbody>	
	</table>
	<!-- START : 페이지네이션  -->
		<nav class="text-center">
			<mytag:paging pagingVO="${searchVO}" linkPage="freeList.wow"/>
		</nav>
		<!-- END : 페이지네이션  -->
</div>
<script type="text/javascript">
	//변수 등록
	var f = document.forms['frm_search'];
	
	//함수 정의
	

	//각 이벤트 등록 
	// 1.카테고리 선택 후 페이징 넘어갈 때 현재페이지의 curPage.value값을 같이 넘겨줌 (submit)
	$(".pagination>li>a[data-page]").on("click",function(e){
		e.preventDefault(); //이벤트 전파방지 ->a의 클릭했을 때 넘어가는거 막아줌 
		//data-page에 있는 값을 f.curPage.value에 설정, 서브밋
		f.curPage.value = $(this).data('page');
		f.submit();
		//alert(this.innerHTML);
	});
	
	//2.검색버틍 클릭
	$("f").submit(function(e) {
		e.preventDefault();
		f.curPage.value = 1;
		f.submit();
	});
	
	//2.id_rowSizePerPage 변경되었을 때 
	//페이지 1, 목록수  현재 값으로 변경 후 서브밋
	$("#id_rowSizePerPage").on("change",function(e){
		e.preventDefault();
		f.curPage.value = 1;
		f.rowSizePerPage.value = this.value;
		f.submit();
	});
	
	//3.초기화 버튼 클릭
	$("#id_btn_reset").on("click",function(){
		f.curPage.value = 1;
		f.searchWord.value ="";
		f.searchType.options[0].selected = true;
		f.searchCategory.options[0].selected = true;		
		f.submit();
	});
	
	//검색창 열기닫기 
	var check = false;
	$("#btn").on("click",function(){
		if(!check){
			$("#btn>a>i").removeClass('fa-chevron-down').addClass('fa-chevron-up');
			$("#btn>a>span").html('&nbsp; 검색닫기');
			check = true;
		}else {
			$("#btn>a>i").removeClass('fa-chevron-up').addClass('fa-chevron-down');
			$("#btn>a>span").html('&nbsp; 검색열기');
			check = false;
		}
	});
	
	//부트스트랩활
	/* $('#id_search_area').on('hidden.bs.collapse', function () {
			$("#btn>a>i").removeClass('fa-chevron-up').addClass('fa-chevron-down');
			$("#btn>a>span").html('&nbsp; 검색열기');		  
		});
	$('#id_search_area').on('show.bs.collapse', function () {
			$("#btn>a>i").removeClass('fa-chevron-down').addClass('fa-chevron-up');
			$("#btn>a>span").html('&nbsp; 검색닫기');	
		}); */
</script>
</body>
</html>