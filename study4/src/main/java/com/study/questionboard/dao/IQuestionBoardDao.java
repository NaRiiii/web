package com.study.questionboard.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.study.questionboard.vo.QuestionBoardSearchVO;
import com.study.questionboard.vo.QuestionBoardVO;

@Mapper
public interface IQuestionBoardDao {
	
	/**
	 * <b>목록 건수 리턴</b> 
	 * @param searchVO
	 * @return int
	 */
	
	public int getBoardCount(QuestionBoardSearchVO searchVO);
	
	/**
	 * <b>자유게시판 목록 반환</b> 
	 * @param searchVO TODO
	 * @return List &lt;QuestionBoardVO&gt;
	 */
	public List<QuestionBoardVO> getBoardList(QuestionBoardSearchVO searchVO) ;
	
	/**
	 * <b>글번호에 해당하는 게시물 반환</b>
	 * @param boNo
	 * @return QuestionBoardVO
	 */
  public QuestionBoardVO getBoard(int boNo);
  
  /**
   * <b>게시물 등록</b>
 * @param board
   * @return  영향받은 행수 
   */
  public int insertBoard(QuestionBoardVO board);
  
  /**
   * <b>해당 게시물 변경</b> 
 * @param board
   * @return  영향받은 행수 
   */
  public int updateBoard(QuestionBoardVO board);
  
  /**
   * <b>해당 게시물의 삭제여부를 "Y" 로 변경 </b> 
 * @param board
   * @return 영향받은 행수 
   */
  public int deleteBoard(QuestionBoardVO board);
  
  /**
   * <b>해당 게시물의 조회수 1 증가</b> 
 * @param boNo
   * @return 영향받은 행수
   */
  public int increaseHit(int boNo);
  
}
