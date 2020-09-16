package com.study.member.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.study.code.service.CommonCodeServiceImpl;
import com.study.code.service.ICommonCodeService;
import com.study.exception.BizNotFoundException;
import com.study.member.service.IMemberService;
import com.study.member.service.MemberServiceImpl;
import com.study.member.vo.MemberVO;
import com.study.servlet.IController;

public class MemberViewController implements IController {

	private IMemberService memberService = new MemberServiceImpl();
	private ICommonCodeService codeService = new CommonCodeServiceImpl();

	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		try {
			String memId = req.getParameter("memId");
			MemberVO mem = memberService.getMember(memId);
			req.setAttribute("mem", mem);
		} catch (BizNotFoundException ex) {
			ex.printStackTrace();
			req.setAttribute("ex", ex);
		}
		return "member/memberView";
	}

}
