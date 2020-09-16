
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<%@ include file="/WEB-INF/inc/header.jsp"%>
<title>자유게시판 - 글 보기</title>
</head>
<body>
	<%@ include file="/WEB-INF/inc/top.jsp"%>
	<div class="container">

		<div class="page-header">
			<h3>
				자유게시판 - <small>글 보기</small>
			</h3>
		</div>
		<table class="table table-striped table-bordered">
			<tbody>
				<tr>
					<th>글번호</th>
					<td>${board.boNo }</td>
				</tr>
				<tr>
					<th>글제목</th>
					<td>${board.boTitle }</td>
				</tr>
				<tr>
					<th>글분류</th>
					<td>${board.boCategoryNm }</td>
				</tr>
				<tr>
					<th>작성자명</th>
					<td>${board.boWriter }</td>
				</tr>
				<!-- 비밀번호는 보여주지 않음  -->
				<tr>
					<th>내용</th>
					<td><pre>${board.boContent}</pre></td>
				</tr>
				<tr>
					<th>등록자 IP</th>
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
					<th>삭제여부</th>
					<td>${board.boDelYn}</td>
				</tr>
				<tr>
					<td colspan="2">
						<div class="pull-left">
							<a href="freeList.wow" class="btn btn-default btn-sm"> <span
								class="glyphicon glyphicon-list" aria-hidden="true"></span>
								&nbsp;&nbsp;목록
							</a>
						</div>
						<div class="pull-right">
							<a href="freeEdit.wow?boNo=${board.boNo }"
								class="btn btn-success btn-sm"> <span
								class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
								&nbsp;&nbsp;수정
							</a>
						</div>
					</td>
				</tr>
			</tbody>
		</table>
	</div>
	<!-- container -->
	<div class="container">
		<!-- // START : 댓글 등록 영역  -->
		<div class="panel panel-default">
			<div class="panel-body form-horizontal">
				<form name="frm_reply" action="<c:url value='/reply/replyRegist' />" method="post" onclick="return false;">
					<input type="hidden" name="reParentNo" value="${board.boNo}">
					<input type="hidden" name="reCategory" value="FREE">
					<div class="form-group">
						<label class="col-sm-2  control-label">댓글</label>
						<div class="col-sm-8">
							<textarea rows="3" name="reContent" class="form-control"></textarea>
						</div>
						<div class="col-sm-2">
							<button id="btn_reply_regist" type="button" class="btn btn-sm btn-info">등록</button>
						</div>
					</div>
				</form>
			</div>
		</div>
		<!-- // END : 댓글 등록 영역  -->
		<!-- // START : 댓글 목록 영역  -->
		<div id="id_reply_list_area">
			<div class="row">
				<div class="col-sm-2 text-right">홍길동</div>
				<div class="col-sm-6">
					<pre>내용</pre>
				</div>
				<div class="col-sm-2">12/30 23:45</div>
				<div class="col-sm-2">
					<button name="btn_reply_edit" type="button" class=" btn btn-sm btn-info">수정</button>
					<button name="btn_reply_delete" type="button" class="btn btn-sm btn-danger">삭제</button>
				</div>
			</div>
			<div class="row">
				<div class="col-sm-2 text-right">밀키스</div>
				<div class="col-sm-6">
					<pre> 싸랑해요 밀키스!~~~</pre>
				</div>
				<div class="col-sm-2">11/25 12:45</div>
				<div class="col-sm-2"></div>
			</div>
		</div>

		<div class="row text-center" id="id_reply_list_more">
			<a id="btn_reply_list_more" class="btn btn-sm btn-default col-sm-10 col-sm-offset-1">
				<span class="glyphicon glyphicon-chevron-down" aria-hidden="true"></span>
				더보기
			</a>
		</div>
		<!-- // END : 댓글 목록 영역  -->
		
		<!-- START : 댓글 수정용 Modal-->
		<div class="modal fade" id="id_reply_edit_modal" role="dialog">
			<div class="modal-dialog">
				<!-- Modal content-->
				<div class="modal-content">
					<form name="frm_reply_edit" action="<c:url value='/reply/replyModify' />" method="post" onclick="return false;">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal">×</button>
							<h4 class="modal-title">댓글수정</h4>
						</div>
						<div class="modal-body">
							<input type="hidden" name="reNo" value="">
							<textarea rows="3" name="reContent" class="form-control"></textarea>
						</div>
						<div class="modal-footer">
							<button id="btn_reply_modify" type="button" class="btn btn-sm btn-info">저장</button>
							<button type="button" class="btn btn-default btn-sm" data-dismiss="modal">닫기</button>
						</div>
					</form>
				</div>
			</div>
		</div><!-- END : 댓글 수정용 Modal-->

	</div><!-- reply controller -->
	
	
	<!-- START : 댓글 처리 스크립트-->
<script type="text/javascript">
//상단에 전역변수 - 공통함수 - 이벤트 함수 - 초기화처리
	var replyParam = {"curPage":1, "rowSizePerPage":10, "reCategory":"FREE", "reParentNo":${board.boNo} }

	// 댓글목록을 구하는 함수
	function fn_reply_list(){
		$.ajax({
			type :"POST" // 전송 방식 설정 (Defaut : GET)
			, url : '<c:url value="/reply/replyList" />'	// 요청 페이지 URL정보
			, dataType : 'json' // 서버로부터 전달받을 데이터 유형 (html, xml, json, script)
			, data : replyParam		// 서버에 전송할 파라미터 정보
			, success : function(data) {
				console.log('data',data);
				if(data.result){
				  $reply_list_area = $('#id_reply_list_area');
				  $.each(data.data, function(i, el){
					  console.log(i);
					var str = '';
					str += '<div class="row" data-re-no ="'+ el.reNo +'">';
					str += '<div class="col-sm-2 text-right">'+ el.reMemName  +'</div>';
					str += '<div class="col-sm-6"><pre>'+ el.reContent +'</pre></div>';
					str += '<div class="col-sm-2">'+ el.reRegDate +'</div>';
					str += '<div class="col-sm-2">';
					if(el.reMemId == '${sessionScope.USER_INFO.userId}'){						
						str += '<button name="btn_reply_edit" type="button" class=" btn btn-sm btn-info">수정</button>';
						str += '<button name="btn_reply_delete" type="button" class="btn btn-sm btn-danger">삭제</button>';
					}
					str += '</div>';
					str += '</div>';					  
					$reply_list_area.append(str);
				  });
				  replyParam.curPage++;
				  //더보기 버튼 
				  if(data.count < replyParam.rowSizePerPage){
					  $('#btn_reply_list_more').hide();
				  }
				}
			} // 요청에 성공한 경우 호출되는 함수 (data, status, xhr )
			, error : function(req, st, err) {
				console.log('---------------------------------');
				console.log('request',req);
				console.log('status',st);
				console.log('errors',err);
				console.log('---------------------------------');
				
				}		// 요청에 실패한 경우 호출되는 함수 (xhr, status, error)
			}); // ajax
	} // fn_reply_list
	$(document).ready(function() {
		
		// 수정버튼 클릭 
		$('#id_reply_list_area').on('click','button[name=btn_reply_edit]',function(e){
			// 모달창 띄우기, 현재 클릭한 버튼의 영역에서 reNo, reContent 를 모달창 내의 폼에 복사 
			// this : javascript 객체 -> jQuery 객체 로 만들기 : $(this)
			$btn = $(this); //현재 버튼의 객체를 변수 $btn에 담아두기
			console.log('data=' + ($btn.closest('.row')).data('reNo'));
			$div = $btn.closest('.row');
			$div.data('re-no');
			//모달창 띄우기
			$('form[name=frm_reply_edit] input[name=reNo]').val($div.data('re-no'));
			$('form[name=frm_reply_edit] textarea[name=reContent]').val($div.find('div pre').text());
			$('#id_reply_edit_modal').modal();
			
			
			
			
	}); // btn_reply_edit.click
	
	// 모달창의 (수정)저장버튼 btn_reply_modify 클릭
	$("#btn_reply_modify").click(function(e) {
		//성공하면
		//35 댓글 수정 성공
		//모달 폼에있는 textarea의 값을
		//div.class[date-re-no=35] div pre 에 교체
		//모달 폼의 reNo, reContetnt는 ''설정
		//모달창 $('#모달아이디').modal('hide')
		
		$btn = $(this); //현재 버튼의 객체를 변수 $btn에 담아두기
		$frm = $btn.closest('form[name=frm_reply_edit]');
		divNo = $frm.find('input[name=reNo]').val();
		divContent = $frm.find('textarea[name=reContent]').val();
		res = confirm("글을 수정하시겠습니까?");
		if(res){
			params = $frm.serialize();
			$.ajax({
				type :"POST" // 전송 방식 설정 (Defaut : GET)
				, url : '<c:url value="/reply/replyModify" />'	// 요청 페이지 URL정보
				, dataType : 'json' // 서버로부터 전달받을 데이터 유형 (html, xml, json, script)
				, data : params		// 서버에 전송할 파라미터 정보
				, success : function(data) {
					console.log('data',data);
					if(data.result){
						replyParam.curPage =1;
						($('#id_reply_list_area').find('div.row[data-re-no='+divNo+'] div pre')).text(divContent);
						$frm.find('input[name=reNo]').val('');
						$frm.find('textarea[name=reContent]').val('');
						$('#id_reply_edit_modal').modal('hide');
						}else{
					 alert(data.msg);
					}
				} // 요청에 성공한 경우 호출되는 함수 (data, status, xhr )
				, error : function(req, st, err) {
					if(req.status == 401){
						location.replace('<c:url value="/login/login.wow" />');
					}
					console.log('---------------------------------');
					console.log('request',req);
					console.log('status',st);
					console.log('errors',err);
					console.log('---------------------------------');
					
					}		// 요청에 실패한 경우 호출되는 함수 (xhr, status, error)
				}); // ajax
		}
		
		
		
		
	}); // btn_reply_modify.click
	
	// 삭제버튼 클릭
	$('#id_reply_list_area').on('click','button[name=btn_reply_delete]',function(e){
		$btn = $(this); //현재 버튼의 객체를 변수 $btn에 담아두기
		$div = $btn.closest('.row');
		res = confirm("글을 삭제하시겠습니까?");
		if(res){
			params = "reNo=" + $div.data('re-no');
			$.ajax({
				type :"POST" // 전송 방식 설정 (Defaut : GET)
				, url : '<c:url value="/reply/replyDelete" />'	// 요청 페이지 URL정보
				, dataType : 'json' // 서버로부터 전달받을 데이터 유형 (html, xml, json, script)
				, data : params		// 서버에 전송할 파라미터 정보
				, success : function(data) {
					console.log('data',data);
					if(data.result){
						//삭제 
						$div.remove()
					}else{
					 alert(data.msg);
					}
				} // 요청에 성공한 경우 호출되는 함수 (data, status, xhr )
				, error : function(req, st, err) {
					if(req.status == 401){
						location.replace('<c:url value="/login/login.wow" />');
					}
					console.log('---------------------------------');
					console.log('request',req);
					console.log('status',st);
					console.log('errors',err);
					console.log('---------------------------------');
					
					}		// 요청에 실패한 경우 호출되는 함수 (xhr, status, error)
				}); // ajax
		}
	}); // btn_reply_delete.click
	
	// 더보기 버튼 클릭
	$('#btn_reply_list_more').click(function(e) {
		fn_reply_list();
	}); // #btn_reply_list_more.click
	
	// 등록버튼 클릭
	$("#btn_reply_regist").click(function(e) {
		e.preventDefault();
		res = confirm("글을 등록하시겠습니까?");
		if(res){
			params = $('form[name=frm_reply]').serialize();
			$.ajax({
				type :"POST" // 전송 방식 설정 (Defaut : GET)
				, url : '<c:url value="/reply/replyRegist" />'	// 요청 페이지 URL정보
				, dataType : 'json' // 서버로부터 전달받을 데이터 유형 (html, xml, json, script)
				, data : params		// 서버에 전송할 파라미터 정보
				, success : function(data) {
					console.log('data',data);
					if(data.result){
						replyParam.curPage = 1;
					 	$('textarea[name=reContent]').val('');
					 // 현재 목록 영역지우기
					 $('#id_reply_list_area').html(''); 
					 // 목록 조회 함수 호출 
					 fn_reply_list();
					}else{
					 alert(data.msg);
					}
				} // 요청에 성공한 경우 호출되는 함수 (data, status, xhr )
				, error : function(req, st, err) {
					if(req.status == 401){
						location.replace('<c:url value="/login/login.wow" />');
					}
					console.log('---------------------------------');
					console.log('request',req);
					console.log('status',st);
					console.log('errors',err);
					console.log('---------------------------------');
					
					}		// 요청에 실패한 경우 호출되는 함수 (xhr, status, error)
				}); // ajax
		}
	}); // #btn_reply_regist.click
	
		// 초기화 함수 호출
		fn_reply_list();
	
	}); // ready
</script>
<!-- END : 댓글 처리 스크립트 -->
</body>
</html>






