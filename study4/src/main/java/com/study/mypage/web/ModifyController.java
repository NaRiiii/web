package com.study.mypage.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;

import com.study.code.service.CommonCodeServiceImpl;
import com.study.code.service.ICommonCodeService;
import com.study.code.vo.CodeVO;
import com.study.common.vo.ResultMessageVO;
import com.study.exception.BizNotEffectedException;
import com.study.exception.BizNotFoundException;
import com.study.login.vo.UserVO;
import com.study.member.service.IMemberService;
import com.study.member.service.MemberServiceImpl;
import com.study.member.vo.MemberVO;
import com.study.servlet.IController;

public class ModifyController implements IController{
	private IMemberService memberService = new MemberServiceImpl();
	private ICommonCodeService codeService = new CommonCodeServiceImpl();
	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		MemberVO member = new MemberVO();
		BeanUtils.populate(member, req.getParameterMap());
		ResultMessageVO messageVO = new ResultMessageVO();

		try {
			memberService.modifyMember(member);
			messageVO.setResult(true).setTitle("회원 정보 수정 완료").setMessage("정보 수정에 성공하였습니다.")
			.setUrl("/mypage/info.wow").setUrlTitle("홈으로");
		} catch (BizNotEffectedException ex) {
			ex.printStackTrace();
			messageVO.setResult(false).setTitle("회원 정보 수정 실패").setMessage("정보 수정에 실패하였습니다.")
					.setUrl("/mypage/info.wow").setUrlTitle("목록으로");

		} catch (BizNotFoundException ex) {
			ex.printStackTrace();
			messageVO.setResult(false).setTitle("회원 정보 수정 실패").setMessage("정보 수정에 실패하였습니다.")
					.setUrl("/mypage/info.wow").setUrlTitle("목록으로");
		}
		req.setAttribute("messageVO", messageVO);
		return "common/message";
	}
	
	
}
