package com.study.servlet.view;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RequestToViewNameTranslator extends View {
	public RequestToViewNameTranslator(StudyViewResolver viewResolver) {
		this.viewResolver = viewResolver;
	}

	public static void main(String[] args) {
		String uri = "/study31/free/freeList.wow;JSESSIONID=MILKIS1004";
		String uriVeiwName = uri;
		String ct = "/study31"; //conTextPath
		
		if(ct.length() > 0) {
			uriVeiwName = uriVeiwName.substring(ct.length() + 1);
		}
		System.out.println(uriVeiwName);
		System.out.println(uriVeiwName.indexOf("."));
		if(uriVeiwName.indexOf(".") > -1) {			
			//마지막에 있는 "/"다음의 "." 입니다 
			uriVeiwName = uriVeiwName.substring(0,uriVeiwName.indexOf("."));
		}
		System.out.println(uriVeiwName);
		if(uriVeiwName.indexOf(";") > -1) {			
			//마지막에 있는 "/"다음의 ";" 입니다 
			uriVeiwName = uriVeiwName.substring(0,uriVeiwName.indexOf(";"));
		}
		System.out.println(uriVeiwName);
		
	}	
	
	@Override
	public void render(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		// 뷰이름이 지정되지 않은 경우 현재 요청 URI 에서 뷰이름을 생성한다.
		String uri = req.getRequestURI();
		String uriViewName = uri;
		// 과제 : 아래의 조건을 만족하도록 변수 uriViewName 을 변경하시오.
		// uriViewName에서 컨텍스트 경로, 확장자, 세미콜론이 있다면 제거
		// 예 : "/study31/free/freeList.wow;JSESSIONID=MILKIS1004" -> "free/freeList"
		String ct = req.getContextPath();
		if(ct.length() > 0) {
			uriViewName = uriViewName.substring(ct.length() + 1);
		}
		if(uriViewName.indexOf(".") > -1) {			
			//마지막에 있는 "/"다음의 "." 입니다 
			uriViewName = uriViewName.substring(0,uriViewName.indexOf("."));
		}
		if(uriViewName.indexOf(";") > -1) {			
			//마지막에 있는 "/"다음의 ";" 입니다 
			uriViewName = uriViewName.substring(0,uriViewName.indexOf(";"));
		}
		
		
		String jspPath = viewResolver.getPrefix() + uriViewName + viewResolver.getSuffix();
		logger.debug("URI=" + uri + ", RequestToViewNameTranslator=" + jspPath);
		RequestDispatcher dispatcher = req.getRequestDispatcher(jspPath);
		dispatcher.forward(req, resp);
	}
}
