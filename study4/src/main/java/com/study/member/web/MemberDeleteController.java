package com.study.member.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.study.code.service.CommonCodeServiceImpl;
import com.study.code.service.ICommonCodeService;
import com.study.common.vo.ResultMessageVO;
import com.study.exception.BizNotEffectedException;
import com.study.exception.BizNotFoundException;
import com.study.member.service.IMemberService;
import com.study.member.service.MemberServiceImpl;
import com.study.member.vo.MemberVO;
import com.study.servlet.IController;

public class MemberDeleteController implements IController {

	private IMemberService memberService = new MemberServiceImpl();
	private ICommonCodeService codeService = new CommonCodeServiceImpl();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		MemberVO member = new MemberVO();
		BeanUtils.populate(member, req.getParameterMap());
		ResultMessageVO messageVO = new ResultMessageVO();
		
		try {
			memberService.removeMember(member);
			
			messageVO.setResult(true)
			.setTitle("회원 삭제 성공")
			.setMessage("해당 회원이 삭제되었습니다.")
			.setUrl("/member/memberList.wow")
			.setUrlTitle("목록보기");
			
			
			}catch(BizNotFoundException ex) {
				ex.printStackTrace();
				messageVO.setResult(false)
				.setTitle("회원 삭제 실패")
				.setMessage("해당회원을 찾을 수 없습니다")
				.setUrl("/member/memberList.wow")
				.setUrlTitle("목록보기");
				
			}catch (BizNotEffectedException ex) {
				ex.printStackTrace();
				messageVO.setResult(false)
				.setTitle("회원 삭제 실패")
				.setMessage("정보입력이 잘못되었습니다")
				.setUrl("/member/memberList.wow")
				.setUrlTitle("목록보기");
		}
			req.setAttribute("messageVO", messageVO);
			return "common/message";
		}

}
