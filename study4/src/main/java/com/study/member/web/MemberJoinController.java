package com.study.member.web;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.study.code.service.ICommonCodeService;
import com.study.code.vo.CodeVO;
import com.study.common.vaild.JoinStep1;
import com.study.common.vaild.JoinStep2;
import com.study.common.vaild.JoinStep3;
import com.study.common.vo.ResultMessageVO;
import com.study.exception.BizNotFoundException;
import com.study.member.service.IMemberService;
import com.study.member.vo.MemberJoinVO;
import com.study.member.vo.MemberSearchVO;
import com.study.member.vo.MemberVO;

@Controller
@SessionAttributes(names = "memberJoin" )
public class MemberJoinController {
	
	@Autowired
	private IMemberService memberService;
	@Autowired
	private ICommonCodeService codeService ;
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	// @ModelAttribute
	// 공통(반복)적으로 사용되는 모델객체를 저장할 때 사용 
	// 해당 컨트롤러의 모든 요청 전에 호출되어 저장됩니다.
	// * 만약 동일한 이름의 모델이 존재한다면 처리하지 않습니다.
	@ModelAttribute("memberJoin")
	public MemberJoinVO getInitJoinVO() {
		MemberJoinVO joinVO = new MemberJoinVO();
		return joinVO;
	}
	
	@ModelAttribute("jbList")
	public List<CodeVO> getJobCategoryList() {
		List<CodeVO> jbList = codeService.getCodeListByParent("JB00");
		return jbList;
	}
	@ModelAttribute("hbList")
	public List<CodeVO> getHobbyCategoryList() {
		List<CodeVO> hbList = codeService.getCodeListByParent("HB00");
		return hbList;
	}
	
	@GetMapping(path = {"/join/join","/join/step1"})
	public String step1(@ModelAttribute("memberJoin") MemberJoinVO JoinVO) throws Exception {
		logger.debug("step 1 = {}",JoinVO);
		return "join/step1";
	}
	
	@PostMapping(path = "/join/step2")
	public String step2(@ModelAttribute("memberJoin") @Validated(JoinStep1.class) MemberJoinVO JoinVO, BindingResult errors) throws Exception {
		logger.debug("step 2 = {}",JoinVO);
		if (errors.hasErrors()) {
			logger.info("step2 검증오류 {}", errors);
			return "join/step1";
		}
		return "join/step2";
	}
	
	@PostMapping(path = "/join/step3")
	public String step3(@ModelAttribute("memberJoin") @Validated(JoinStep2.class) MemberJoinVO JoinVO, BindingResult errors) throws Exception {
		logger.debug("step 3 = {}",JoinVO);
		if (errors.hasErrors()) {
			logger.info("step3 검증오류 {}", errors);
			return "join/step2";
		}
		try {
			memberService.getMember(JoinVO.getMemId());
			errors.rejectValue("memId","errors.required","해당 아이디는 사용중입니다");
			return "join/step2";
		}catch(BizNotFoundException e) {			
			return "join/step3";			
		}
			
		//해당아이디가 사용중이라면 errors에 
		//errors.rejectValue("memId","errors.requoed","해당 아이디는 사용중입니다")'
		//return "join/step2"
		
		
		
	}
	
	@PostMapping(path = "/join/regist")
	public String regist(@ModelAttribute("memberJoin") @Validated(JoinStep3.class) MemberJoinVO JoinVO, BindingResult errors, SessionStatus status, ModelMap model) throws Exception {
		logger.debug("regist = {}",JoinVO);
		if (errors.hasErrors()) {
			logger.info("step3 검증오류 {}", errors);
			return "join/step3";
		}		
		ResultMessageVO messageVO = new ResultMessageVO();
		memberService.registMember(JoinVO);
		
		status.setComplete(); //현재 세션에 담긴 모델정보 완료 -> 제거
		messageVO.setResult(true)
				  .setTitle("회원가입 성공")
				  .setMessage("회원가입을 축하합니다.")
		     	  .setUrl("/login/login.wow").setUrlTitle("로그인");
		model.addAttribute("resultMessage",messageVO);
		return "join/regist";
	}
	
	@PostMapping(path = "/join/cancel")
	public String cancel(SessionStatus status) throws Exception {
		status.setComplete(); //현재세션 정리후 이동 (서버 자원적인 측면)
		return "redirect:/";
	}

}
