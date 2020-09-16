package com.study.mypage.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.study.code.service.CommonCodeServiceImpl;
import com.study.code.service.ICommonCodeService;
import com.study.common.vo.ResultMessageVO;
import com.study.exception.BizNotFoundException;
import com.study.login.vo.UserVO;
import com.study.member.service.IMemberService;
import com.study.member.service.MemberServiceImpl;
import com.study.member.vo.MemberVO;
import com.study.servlet.IController;

public class InfoController implements IController {

	private IMemberService memberService = new MemberServiceImpl();
	private ICommonCodeService codeService = new CommonCodeServiceImpl();

	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		try {
			HttpSession session = req.getSession();
			UserVO user = (UserVO) session.getAttribute("USER_INFO");
			//이러한 로그인 같은 경우에 필터 사용 
			//요기서는 로그인이 되어있다는 가정하에 코딩 -
//			if(user == null) {
//				return "redirect:/";	//로그인 하지 않았기 때문에 실행하지않고 홈 또는 로그인페이지로 보내야함 
//			}
			String memId = user.getUserId();
			MemberVO mem = memberService.getMember(memId);
			req.setAttribute("mem", mem);
			return null;
			
		} catch (BizNotFoundException ex) {
			ex.printStackTrace();
			req.setAttribute("ex", ex);
		}
		return "member/memberView";
	}

}
