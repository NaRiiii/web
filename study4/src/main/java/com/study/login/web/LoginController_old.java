package com.study.login.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.study.common.util.CookieUtils;
import com.study.common.vo.ResultMessageVO;
import com.study.exception.BizNotFoundException;
import com.study.exception.BizPasswordNotMatchedException;
import com.study.login.service.ILoginService;
import com.study.login.service.LoginServiceImpl;
import com.study.login.vo.UserVO;
import com.study.servlet.IController;

public class LoginController_old implements IController{

	private ILoginService loginService = new LoginServiceImpl();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		// 요청 메서드가 GET : login화면, POST : loginCheck 로 실행
		if ("GET".equals(req.getMethod())) {
			return "login/login";
		}else if("POST".equals(req.getMethod())) {
			//로그인체크
			UserVO vo = new UserVO();
			vo.setUserId(req.getParameter("userId"));
			vo.setUserPass(req.getParameter("userPass"));
			
			try {
				UserVO userVO = loginService.loginCheck(vo); //vo에 아이디 비번 담고 로그인서비스로 확인 
				HttpSession session = req.getSession();
				 String remember = req.getParameter("rememberMe");
//				 if(remember != null && remember.equals("Y")){
				 if("Y".equals(remember)) {
				 	Cookie cookie = CookieUtils.createCookie("SAVE_ID", vo.getUserId(),"/",60*60*24*31);
				 	resp.addCookie(cookie);	 
				 }else{
				 	resp.addCookie(CookieUtils.createCookie("SAVE_ID", "","/",0));	 		 
				 }
				 System.out.println("세션에 정보 저장 : " + userVO);
				 session.setAttribute("USER_INFO", userVO); 
				 return "redirect:/";
			} catch (BizNotFoundException | BizPasswordNotMatchedException e) {
				e.printStackTrace();
				ResultMessageVO message = new ResultMessageVO();
				message.setResult(false)
						.setTitle("로그인실패")
						.setMessage("회원이 존재하지 않거나 비밀번호가 잘못되었습니다.");
				req.setAttribute("messageVO", message);
				return "common/message";
			}
		}else {
			//get/post 둘다 아닐때 
			throw new ServletException("지원하지 않는 메서드 요청입니다.");
		}
	}

}
