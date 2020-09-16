package com.study.member.web;

import java.util.List;

import javax.validation.Valid;
import javax.validation.groups.Default;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.study.code.service.ICommonCodeService;
import com.study.code.vo.CodeVO;
import com.study.common.vaild.RegistType;
import com.study.common.vo.ResultMessageVO;
import com.study.exception.BizDuplicateKeyException;
import com.study.exception.BizNotEffectedException;
import com.study.exception.BizNotFoundException;
import com.study.member.service.IMemberService;
import com.study.member.vo.MemberSearchVO;
import com.study.member.vo.MemberVO;

@Controller
public class MemberController { 
	
	@Autowired
	private IMemberService memberService; // = new MemberServiceImpl();
	@Autowired
	private ICommonCodeService codeService ; //= new CommonCodeServiceImpl();
	private final Logger logger = LoggerFactory.getLogger(getClass()); //컨트롤러에 항상 있어야함
	
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
	
	//void로 반환
	@RequestMapping("/member/memberList.wow")
	public void memberList(@ModelAttribute("searchVO") MemberSearchVO searchVO, ModelMap model) {
		logger.debug("searchVO={}",searchVO);
		List<MemberVO> members = memberService.getMemberList(searchVO);
		model.addAttribute("members", members);
	}
	
	//void로 반환
	@RequestMapping("/member/memberForm.wow")
	public void memberForm(@ModelAttribute("mem") MemberVO member) {
	}
	
	//MAV로 반환, @requestMapping POST제한
	@RequestMapping(path = "/member/memberRegist.wow", method = RequestMethod.POST)
	public ModelAndView memberRegist(@ModelAttribute("mem") @Validated({Default.class, RegistType.class} )MemberVO member, BindingResult errors) {
		logger.debug("member={}",member);
		ModelAndView mav = new ModelAndView();
		
		if(errors.hasErrors()) {
			mav.setViewName("member/memberForm");
			return mav;
		}
		
		ResultMessageVO messageVO = new ResultMessageVO();
		try {
			memberService.registMember(member);
//			return "redirect:/member/memberList.wow";
			mav.setViewName("redirect:/member/memberList.wow");
		} catch (BizDuplicateKeyException ex) {
			logger.error(ex.getMessage(),ex);
			messageVO.setResult(false)
			.setTitle("아이디 중복 오류")
			.setMessage("해당 아이디가 이미 존재합니다")
			.setUrl("/member/memberList.wow")
			.setUrlTitle("목록으로");
			mav.addObject("messageVO", messageVO);
			mav.setViewName("common/message");
		}
		return mav;
	}
	
	//String으로 반환, @requestMapping memId파라미터 존재하도록 제한
	@RequestMapping(path = "/member/memberView.wow",params="memId")
	public String memberView( String memId, ModelMap model ) {
		logger.debug("memId={}",memId);
		ResultMessageVO messageVO = new ResultMessageVO();
		try {
			MemberVO mem = memberService.getMember(memId);
			model.addAttribute("mem", mem);
		} catch (BizNotFoundException ex) {
			logger.error(ex.getMessage(),ex);
			messageVO.setResult(false)
			.setTitle("회원 보기 실패")
			.setMessage("회원을 찾을 수 없습니다.")
			.setUrl("/member/memberList.wow")
			.setUrlTitle("목록으로");
			model.addAttribute("messageVO", messageVO);
		}
		return "member/memberView";
	}
	
	//String으로 반환, @requestMapping memId파라미터 존재하도록 제한
	@RequestMapping(path = "/member/memberEdit.wow" ,params="memId")
	public String memberEdit( String memId, ModelMap model) {
		logger.debug("memId={}",memId);
		ResultMessageVO messageVO = new ResultMessageVO();
		try {
			MemberVO mem = memberService.getMember(memId);
			model.addAttribute("mem", mem);
		} catch (BizNotFoundException ex) {
			logger.error(ex.getMessage(),ex);
			messageVO.setResult(false)
			.setTitle("회원 수정 실패")
			.setMessage("회원을 찾을 수 없습니다.")
			.setUrl("/member/memberList.wow")
			.setUrlTitle("목록으로");
			model.addAttribute("messageVO", messageVO);
		}
		return "member/memberEdit";
	}
	
	//String으로 반환, @requestMapping memId파라미터 존재하도록 제한, POST제한
	@RequestMapping(path = "/member/memberModify.wow", method = RequestMethod.POST ,params = "memId")
	public String memberModify(@ModelAttribute("mem") @Valid MemberVO member, BindingResult errors, ModelMap model) {
		logger.debug("member={}",member);
		
		if(errors.hasErrors()) {
			return "member/memberEdit";
		}
		
		ResultMessageVO messageVO = new ResultMessageVO();
		try {
			memberService.modifyMember(member);
			return "redirect:/member/memberView.wow?memId=" + member.getMemId();
		} catch (BizNotEffectedException ex) {
			logger.error(ex.getMessage(),ex);
			messageVO.setResult(false).setTitle("회원 정보 수정 실패").setMessage("회원정보가 변경되지 않았습니다.")
					.setUrl("member/memberList.wow").setUrlTitle("목록으로");
		} catch (BizNotFoundException ex) {
			logger.error(ex.getMessage(),ex);
			messageVO.setResult(false).setTitle("회원 정보 수정 실패").setMessage("회원정보가 존재하지 않습니다.")
					.setUrl("member/memberList.wow").setUrlTitle("목록으로");
		}
		model.addAttribute("messageVO", messageVO);
		return "common/message";
	}
	
	//String으로 반환, @requestMapping memId, memPass파라미터 존재하도록 제한, POST제한
	@RequestMapping(path = "/member/memberDelete.wow" , method = RequestMethod.POST ,params = {"memId","memPass"})
	public String memberDelete(MemberVO member, ModelMap model) {
		logger.debug("member={}",member);
		ResultMessageVO messageVO = new ResultMessageVO();
		try {
			memberService.removeMember(member);
			messageVO.setResult(true)
			.setTitle("회원 삭제 성공")
			.setMessage("해당 회원이 삭제되었습니다.")
			.setUrl("/member/memberList.wow")
			.setUrlTitle("목록보기");
			}catch(BizNotFoundException ex) {
				logger.error(ex.getMessage(),ex);
				messageVO.setResult(false)
				.setTitle("회원 삭제 실패")
				.setMessage("해당회원을 찾을 수 없습니다")
				.setUrl("/member/memberList.wow")
				.setUrlTitle("목록보기");	
			}catch (BizNotEffectedException ex) {
				logger.error(ex.getMessage(),ex);
				messageVO.setResult(false)
				.setTitle("회원 삭제 실패")
				.setMessage("정보입력이 잘못되었습니다")
				.setUrl("/member/memberList.wow")
				.setUrlTitle("목록보기");
		}
			model.addAttribute("messageVO", messageVO);
			return "common/message";
	}
	
}
