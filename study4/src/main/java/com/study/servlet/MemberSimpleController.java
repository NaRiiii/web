package com.study.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.study.member.web.MemberDeleteController;
import com.study.member.web.MemberEditController;
import com.study.member.web.MemberFormController;
import com.study.member.web.MemberListController;
import com.study.member.web.MemberModifyController;
import com.study.member.web.MemberRegistController;
import com.study.member.web.MemberViewController;

public class MemberSimpleController extends HttpServlet{

	public static void main(String[] args) {
		
		String cp = "/study3";
		String t = "/study3/member/memberList.wow";
//		String u = t.substring(7));//문자열 자르기 
		String u = t.substring(cp.length());//우리는 contextPath를 통해 7을 구해야 함 
		System.out.println(u);
	}

	//1. 클라이언트의 요청을 받는다.
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//공통기능 처리
		req.setCharacterEncoding("utf-8");
		
		//2. 요청 분석 (uri)
		String uri = req.getRequestURI();
		uri = uri.substring(req.getContextPath().length()); //contextPath 는 /study3 , 얘의 길이만큼 짜르기  -> /free/freeList.wow
		System.out.println("uri=" + uri);
		
		String viewPage ="";
		IController controller = null;
		
		//3. 모델을 시용하여 처리
		//4. 결과를 속성에 저장
		if("/member/memberList.wow".equals(uri)) {
			controller = new MemberListController();
		}else if("/member/memberView.wow".equals(uri)) {
			controller = new MemberViewController();
		}else if("/member/memberForm.wow".equals(uri)) {
			controller = new MemberFormController();			
		}else if("/member/memberRegist.wow".equals(uri)) {
			controller = new MemberRegistController();			
		}else if("/member/memberEdit.wow".equals(uri)){
			controller = new MemberEditController();			
		}else if("/member/memberModify.wow".equals(uri)) {
			controller = new MemberModifyController();						
		}else if("/member/memberDelete.wow".equals(uri)) {
			controller = new MemberDeleteController();						
		}
		
		if(controller != null) {
			
		try {
			viewPage = controller.process(req, resp);
			System.out.println("viewPage=" + viewPage);
			
			// 5. 뷰 페이지로 이동
			RequestDispatcher dispatcher = req.getRequestDispatcher(viewPage);
			dispatcher.forward(req, resp);
		}catch (Exception e) {
			e.printStackTrace();
			//500오류 
			resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
		
		
		}else {
			//요청에 대한 컨트롤러가 존재하지 않으므로 404 던지기
//			resp.sendError(404, uri +"에 해당하는 페이지가 존재하지 않습니다.");
			resp.sendError(HttpServletResponse.SC_NOT_FOUND, uri +"에 해당하는 페이지가 존재하지 않습니다.");
			
		}
		
		
	}
	
}
