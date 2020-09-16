package com.study.questionboard.service;

import java.util.List;

import com.study.exception.BizNotEffectedException;
import com.study.exception.BizNotFoundException;
import com.study.exception.BizPasswordNotMatchedException;
import com.study.questionboard.vo.QuestionBoardSearchVO;
import com.study.questionboard.vo.QuestionBoardVO;

public interface IQuestionBoardService {
	
	public List<QuestionBoardVO> getBoardList(QuestionBoardSearchVO searchVO);
  public QuestionBoardVO getBoard(int boNo) throws BizNotFoundException;	
  public void registBoard(QuestionBoardVO board);	
  public void modifyBoard(QuestionBoardVO board) throws BizNotFoundException, BizPasswordNotMatchedException, BizNotEffectedException;	
  public void removeBoard(QuestionBoardVO board) throws BizNotFoundException, BizPasswordNotMatchedException, BizNotEffectedException; 	
  public void increaseHit(int boNo);   
}
