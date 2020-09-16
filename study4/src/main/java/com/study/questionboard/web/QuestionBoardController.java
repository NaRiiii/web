package com.study.questionboard.web;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.study.code.service.ICommonCodeService;
import com.study.code.vo.CodeVO;
import com.study.common.vaild.ModifyType;
import com.study.common.vaild.RegistType;
import com.study.common.vo.ResultMessageVO;
import com.study.exception.BizNotFoundException;
import com.study.exception.BizPasswordNotMatchedException;
import com.study.exception.DaoduplicateKeyException;
import com.study.questionboard.service.IQuestionBoardService;
import com.study.questionboard.vo.QuestionBoardSearchVO;
import com.study.questionboard.vo.QuestionBoardVO;

@Controller
//@RequestMapping("/question") 이렇게 하면 밑에는 이하만 작성해주면 됨 
public class QuestionBoardController {

	@Autowired // =@Inject
	IQuestionBoardService boardService; // = new QuestionBoardServiceServiceImpl();
	@Inject
	ICommonCodeService codeService; // = new CommonCodeServiceImpl();
	
	private final Logger logger = LoggerFactory.getLogger(getClass()); //컨트롤러에 항상 있어야함
	
	//@ModelAttribute 를 통해 공통으로 사용되는 모델객체( 일반적으로 공통코드 목록 )
	// 요청 메소드에 진입 전에 호출됩니다. 
	@ModelAttribute("cateList")
	public List<CodeVO> getCategoryList() {
		logger.debug("getCategoryL0ist call");
		List<CodeVO> cateList = codeService.getCodeListByParent("BC00");
		return cateList;
	}
	
//	@RequestMapping("/question/questionList.wow")
	@RequestMapping(value = {"/question/questionList.wow","/question/"})
//	@RequestMapping(value ="/question/questionList.wow")
	public String questionList(@ModelAttribute("searchVO") QuestionBoardSearchVO searchVO, ModelMap model)  throws Exception{
		//파라미터에 선언한 커맨드 객체는 자동으로 모델에 저장
		// 이름은 첫글자 소문자 클래스명
		
		
		logger.debug("searchVO={}",searchVO);
		logger.debug("questionList 메서드 call");
		// 모델 정보를 저장할 때는 request보다는 ModelMap, Model, Map , req순서로 사용하는 것이 좋음 
		List<QuestionBoardVO> boards = boardService.getBoardList(searchVO);
		model.addAttribute("boards", boards); //결과를 속성에 저장
		
		return "question/questionList";
	}
	
	@RequestMapping("/question/questionView.wow")
	public String questionView(@RequestParam(value="boNo") int boNo, ModelMap model) throws Exception {
		logger.debug("boNo={}",boNo);
		try{
			//boNo = Integer.parseInt(boNo);
			QuestionBoardVO question = boardService.getBoard(boNo);
			if(question != null) {
				boardService.increaseHit(boNo);					
			}
			model.addAttribute("board", question);
		} catch(BizNotFoundException ex){
			logger.error(ex.getMessage(),ex);
			ResultMessageVO messageVO = new ResultMessageVO();
			messageVO.setResult(false)
				      .setTitle("조회실패")
				       .setMessage("해당 글이 존재하지 않습니다.")
				       .setUrl("/question/questionList.wow")
				       .setUrlTitle("목록으로");
			model.addAttribute("messageVO", messageVO);
			return "common/message";
		}
		return "question/questionView";
		
	}
	
	@RequestMapping("/question/questionEdit.wow")
	public String questionEdit(int boNo, ModelMap model) throws Exception {
		logger.debug("boNo={}",boNo);
//		@ModelAttribute로 처리함
//		List<CodeVO> boardList = codeService.getCodeListByParent("BC00");
//		model.addAttribute("boardList", boardList);
		try{
			//int boNo = Integer.parseInt(req.getParameter("boNo"));
			QuestionBoardVO board = boardService.getBoard(boNo);
			model.addAttribute("board", board);
		}catch ( BizNotFoundException ex){
			logger.error(ex.getMessage(),ex);
			
			ResultMessageVO messageVO = new ResultMessageVO();
			messageVO.setTitle("글 수정 실패")
					  .setMessage("글 수정이 잘못되었습니다")
					  .setUrl("/question/questionList")
					  .setUrlTitle("목록으로");
			
			model.addAttribute("messageVO", messageVO);
			return "common/message";	
		}
		return "question/questionEdit";
	}
	
	@RequestMapping("/question/questionForm.wow")
	public void questionForm(@ModelAttribute("board") QuestionBoardVO board, ModelMap model) throws Exception {
//		@ModelAttribute로 처리함
//		List<CodeVO> categoryList = codeService.getCodeListByParent("BC00");
//		model.addAttribute("categoryList", categoryList);
//		return "question/questionForm";
	}
	
	@RequestMapping("/question/questionDelete.wow")
	public String questionDelete(ModelMap model, QuestionBoardVO board) throws Exception {
		logger.debug("board={}",board);
		ResultMessageVO messageVO = new ResultMessageVO();
		try {
			boardService.removeBoard(board);
//			List<CodeVO> categoryList = codeService.getCodeListByParent("BC00");
			messageVO.setResult(true)
			.setTitle("글 삭제 성공")
			.setMessage("해당글이 삭제되었습니다.")
			.setUrl("/question/questionList.wow")
			.setUrlTitle("목록보기");
//			return "redirect:/question/questionList.wow";			
			}catch(BizNotFoundException ex) {
				logger.error(ex.getMessage(),ex);
				messageVO.setResult(false)
				.setTitle("글 삭제 실패")
				.setMessage("해당글을 찾을 수 없습니다")
				.setUrl("/question/questionList.wow")
				.setUrlTitle("목록보기");
				
			}catch (BizPasswordNotMatchedException ex) {
				logger.error(ex.getMessage(),ex);
				messageVO.setResult(false)
				.setTitle("글 삭제 실패")
				.setMessage("비밀번호가 잘못 입력되었습니다")
				.setUrl("/question/questionList.wow")
				.setUrlTitle("목록보기");
		}
//			messageVO.setMessage("삭제 성공").
			model.addAttribute("messageVO", messageVO);
			return "common/message";
	}
	
	
	@RequestMapping("/question/questionModify.wow")
																		//디폴트값을 쓰지않으면 group을 써놓은(boNo)만 검사함
	public String questionModify(@ModelAttribute("board") @Validated({Default.class, ModifyType.class}) QuestionBoardVO board, BindingResult errors, ModelMap model) throws Exception {
						//검증오류에서 다시 리로딩하기위해 변수이름설정해줌	                                             //에러확인할 애           애러를 담은애
		logger.debug("board={}",board);
		
		if (errors.hasErrors()) { //에러가 있다면
			//검증오류가 있으므로 입력화면으로 뷰 이동 
			return "question/questionEdit";
		}
		ResultMessageVO messageVO = new ResultMessageVO();
		try {
			boardService.modifyBoard(board);
			return "redirect:/question/questionView.wow?boNo=" + board.getBoNo();
			
			} catch (BizNotFoundException ex) {
				logger.error(ex.getMessage(),ex);
				messageVO.setResult(false)
						  .setTitle("글 수정 실패")
						  .setMessage("해당 글을 찾을 수 없습니다")
						  .setUrl("/question/questionList.wow")
						  .setUrlTitle("목록보기");
				
			} catch (BizPasswordNotMatchedException ex) {
				logger.error(ex.getMessage(),ex);
					messageVO.setResult(false)
					.setTitle("글 수정 실패")
					.setMessage("비밀번호가 잘못 입력되었습니다")
					.setUrl("question/questionList.wow")
					.setUrlTitle("목록보기");
			}
		model.addAttribute("messageVO", messageVO);
		return "common/message";
	}
	
	
	@RequestMapping(path = "/question/questionRegist.wow" , method = {RequestMethod.POST, RequestMethod.PUT})
	public ModelAndView questionRegist(@ModelAttribute("board") @Validated({Default.class, RegistType.class}) QuestionBoardVO board, BindingResult errors, HttpServletRequest req)  throws Exception{
		logger.debug("board={}",board);
		ModelAndView mav = new ModelAndView();
		ResultMessageVO messageVO = new ResultMessageVO();
		
		if (errors.hasErrors()) { //에러가 있다면
			//검증오류가 있으므로 입력화면으로 뷰 이동 
			mav.setViewName("question/questionForm");
			return mav;
		}
		
//		public ModelAndView questionRegist(QuestionBoardVO board, ModelMap model, HttpServletRequest req)  throws Exception{
		try {
			board.setBoIp(req.getRemoteAddr());
			boardService.registBoard(board);
//			return "redirect:/question/questionList.wow";
			mav.setViewName("redirect:/question/questionList.wow");
		}catch (DaoduplicateKeyException e) {
			logger.error(e.getMessage(),e);
			messageVO.setResult(false)
			.setTitle("글 등록 실패")
			.setMessage("글이 이미 존재합니다")
			.setUrl("/question/questionList.wow")
			.setUrlTitle("목록으로");
			mav.addObject("messageVO", messageVO);
			mav.setViewName("common/message");
		}
		
//		model.addAttribute("messageVO", messageVO);
		return mav;
	}
	
	
	
	
	
}
