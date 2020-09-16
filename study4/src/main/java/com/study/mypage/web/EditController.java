package com.study.mypage.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.study.code.service.CommonCodeServiceImpl;
import com.study.code.service.ICommonCodeService;
import com.study.code.vo.CodeVO;
import com.study.exception.BizNotFoundException;
import com.study.login.vo.UserVO;
import com.study.member.service.IMemberService;
import com.study.member.service.MemberServiceImpl;
import com.study.member.vo.MemberVO;
import com.study.servlet.IController;

public class EditController implements IController {

	private IMemberService memberService = new MemberServiceImpl();
	private ICommonCodeService codeService = new CommonCodeServiceImpl();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		try {
			HttpSession session = req.getSession();
			UserVO user = (UserVO) session.getAttribute("USER_INFO");
			String memId = user.getUserId();
			MemberVO mem = memberService.getMember(memId);
			req.setAttribute("mem", mem);
			
		} catch (BizNotFoundException ex) {
			ex.printStackTrace();
			req.setAttribute("ex", ex);
		}
		List<CodeVO> jobList = codeService.getCodeListByParent("JB00");
		List<CodeVO> hobbyList = codeService.getCodeListByParent("HB00");
		req.setAttribute("jobList", jobList);
		req.setAttribute("hobbyList", hobbyList);
		return "mypage/edit";
	}

}
