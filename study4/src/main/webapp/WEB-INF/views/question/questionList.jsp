
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="mytag"  tagdir="/WEB-INF/tags"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<%@include file="/WEB-INF/inc/header.jsp"%>
<title>questionBoardList.jsp</title>
</head>
<body>

	<%@include file="/WEB-INF/inc/top.jsp"%>
	<div class="container">
		<div class="page-header">
			<h3>
				자유게시판 -<small>목록조회</small>
			</h3>
		</div>
		<!-- START : 검색 폼  -->
		<div class="row panel panel-default collapse" id="id_search_area">
			<div class="panel-body">
				<form name="frm_search" action="freeList.wow" method="post"
					class="form-horizontal">
					<input type="hidden" name="curPage" value="${searchVO.curPage }">
					<input type="hidden" name="rowSizePerPage"
						value="${searchVO.rowSizePerPage }">
					<div class="form-group">
						<label for="id_searchType" class="col-sm-2 control-label">검색</label>
						<div class="col-sm-2">
							<select id="id_searchType" name="searchType"
								class="form-control input-sm">
								<option value="T"
									${"T" eq searchVO.searchType ? "selected='selected'": ""}>제목</option>
								<option value="W"
									${"W" eq searchVO.searchType ? "selected='selected'": ""}>작성자</option>
								<option value="C"
									${"C" eq searchVO.searchType ? "selected='selected'": ""}>내용</option>
							</select>
						</div>
						<div class="col-sm-2">
							<input type="text" name="searchWord"
								class="form-control input-sm" value="${searchVO.searchWord }"
								placeholder="검색어">
						</div>
						<label for="id_searchCategory"
							class="col-sm-2 col-sm-offset-2 control-label">분류</label>
						<div class="col-sm-2">
							<select id="id_searchCategory" name="searchCategory"
								class="form-control input-sm">
								<option value="">-- 전체 --</option>
								<c:forEach items="${cateList}" var="code">
									<option value="${code.commCd }"
										${code.commCd eq searchVO.searchCategory ? "selected='selected'": ""}>${code.commNm}</option>
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
					<option value="10"
						${searchVO.rowSizePerPage eq 10 ? "selected='selected'" : "" }>10</option>
					<option value="20"
						${searchVO.rowSizePerPage eq 20 ? "selected='selected'" : "" }>20</option>
					<option value="30"
						${searchVO.rowSizePerPage eq 30 ? "selected='selected'" : "" }>30</option>
					<option value="50"
						${searchVO.rowSizePerPage eq 50 ? "selected='selected'" : "" }>50</option>
				</select>
			</div>
			<div id="btn" class="col-xs-6 col-sm-2 col-sm-offset-6 text-right"
				data-toggle="collapse" data-target="#id_search_area"
				aria-expanded="false">
				<a href="#" class="btn btn-info btn-sm"> <i
					class="fas fa-chevron-down"></i> <span>&nbsp;검색열기</span>
				</a>
			</div>
			<div class="col-xs-6 col-sm-1 text-right">
				<a href="freeForm.wow" class="btn btn-primary btn-sm"> <span
					class="glyphicon glyphicon-plus-sign" aria-hidden="true"></span>
					&nbsp;새글쓰기
				</a>
			</div>
		</div>
		<!-- END : 목록건수 및 새글쓰기 버튼  -->
		<table class="table table-striped table-bordered table-hover">
			<colgroup>
				<col width="10%" />
				<col width="15%" />
				<col />
				<col width="10%" />
				<col width="15%" />
				<col width="10%" />
			</colgroup>
			<thead>
				<tr>
					<th>글번호</th>
					<th>분류</th>
					<th>제목</th>
					<th>작성자</th>
					<th>등록일</th>
					<th>조회수</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="vo" items="${boards}">
					<tr class="text-center">
						<td>${vo.boNo }</td>
						<td>${vo.boCategoryNm}</td>
						<td class="text-left"><a href="freeView.wow?boNo=${vo.boNo }">${vo.boTitle}</a></td>
						<td>${vo.boWriter}</td>
						<td>${vo.boRegDate}</td>
						<td>${vo.boHit}</td>
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
	<!-- container -->
	<script type="text/javascript">
		//변수 등록
		var f = document.forms['frm_search'];

		//함수 정의

		//각 이벤트 등록 
		// 1.카테고리 선택 후 페이징 넘어갈 때 현재페이지의 curPage.value값을 같이 넘겨줌 (submit)
		$(".pagination>li>a[data-page]").on("click", function(e) {
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
		$("#id_rowSizePerPage").on("change", function(e) {
			e.preventDefault();
			f.curPage.value = 1;
			f.rowSizePerPage.value = this.value;
			f.submit();
		});

		//3.초기화 버튼 클릭
		$("#id_btn_reset").on("click", function() {
			f.curPage.value = 1;
			f.searchWord.value = "";
			f.searchType.options[0].selected = true;
			f.searchCategory.options[0].selected = true;
			f.submit();
		});

		//검색창 열기닫기 
		var check = false;
		$("#btn").on(
				"click",
				function() {
					if (!check) {
						$("#btn>a>i").removeClass('fa-chevron-down').addClass(
								'fa-chevron-up');
						$("#btn>a>span").html('&nbsp; 검색닫기');
						check = true;
					} else {
						$("#btn>a>i").removeClass('fa-chevron-up').addClass(
								'fa-chevron-down');
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




