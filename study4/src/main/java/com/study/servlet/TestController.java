package com.study.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TestController extends HttpServlet{

	// /studt3/command.soju?c=list : 자유게시판 목록
	// /studt3/command.soju?c=view&boNo=12 : 자유게시판 상세보기
	//1. 클라이언트의 요청을 받는다.
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String cc = req.getParameter("c"); //c라는 파라미터를 받음
		String viewPage ="";
		
		switch (cc) {
		case "list": viewPage = "/free/freeList.jsp";	break;
		case "view": viewPage = "/free/freeView.jsp";	break;
		default: viewPage = "/index.jsp";	break;
		}

		
		
		//request는 정보를 저장하고 있다가 그 다음페이지, 다음페이지에서도 해당 정보를 볼 수 있게 저장하는 기능을한다.
		//response는 응답의 정보를 가지고 있는객체를 response객체라고 말함 -> 서버측에서 클라이언트측으로 데이터를 전달하기 위한 객체

		//request -> response의 단계에서 forward와 sendRedirect방식이 있다
		// 5. 뷰 페이지로 이동
		RequestDispatcher dispatcher = req.getRequestDispatcher(viewPage);
//		Forward방식에서는 A.jsp -> servlet -> B.jsp까지 파라미터정보가 넘어가지만
//		sendRedirect방식에서는 정보를 제외한 단순히 페이지호출만 한다는 차이가 있다.
//		forward만 하게되면 따로 처리를 해주지 않을 시 B다음의 단계에서는 A의 파라미터가 소실된다.
//		이떄 requestDispatcher는 정보유지를 위해 사용된다.



		dispatcher.forward(req, resp);
		
		
	}
	
}
