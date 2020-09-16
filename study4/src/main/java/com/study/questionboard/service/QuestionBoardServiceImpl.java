package com.study.questionboard.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.exception.BizNotEffectedException;
import com.study.exception.BizNotFoundException;
import com.study.exception.BizPasswordNotMatchedException;
import com.study.questionboard.dao.IQuestionBoardDao;
import com.study.questionboard.vo.QuestionBoardSearchVO;
import com.study.questionboard.vo.QuestionBoardVO;

@Service
public class QuestionBoardServiceImpl implements IQuestionBoardService {

	@Autowired
	private IQuestionBoardDao freeDao;

	@Override
	public List<QuestionBoardVO> getBoardList(QuestionBoardSearchVO searchVO) {
		// 건수를 구해서 searchVO 설정 -> searchVO.pageSetting() ->list 호
		int cnt = freeDao.getBoardCount(searchVO);
		searchVO.setTotalRowCount(cnt);
		searchVO.pageSetting();

		List<QuestionBoardVO> list = freeDao.getBoardList(searchVO);
		return list;
	}

	@Override
	public QuestionBoardVO getBoard(int boNo) throws BizNotFoundException {
		QuestionBoardVO vo = freeDao.getBoard(boNo);
		if (vo == null) {
			throw new BizNotFoundException("[" + boNo + "] 조회 실패");
		}
		return vo;
	}

	@Override
	public void registBoard(QuestionBoardVO board) {
		freeDao.insertBoard(board);
	}

	@Override
	public void modifyBoard(QuestionBoardVO board)
			throws BizNotFoundException, BizPasswordNotMatchedException, BizNotEffectedException {
		QuestionBoardVO vo = freeDao.getBoard(board.getBoNo());
		if (vo == null) {
			throw new BizNotFoundException("[" + board.getBoNo() + "] 조회 실패");
		}
		int cnt = freeDao.updateBoard(board);
		if (cnt < 1) {
			throw new BizNotEffectedException("[" + board.getBoNo() + "] 수정 실패");
		}
		if (!vo.getBoPass().equals(board.getBoPass())) {
			throw new BizPasswordNotMatchedException("[" + board.getBoPass() + "] 비밀번호 불일치");
		}
	}

	@Override
	public void removeBoard(QuestionBoardVO board)
			throws BizNotFoundException, BizPasswordNotMatchedException, BizNotEffectedException {
		QuestionBoardVO vo = freeDao.getBoard(board.getBoNo());
		if (vo == null) {
			throw new BizNotFoundException("[" + board.getBoNo() + "] 삭제 실패");
		}
		int cnt = freeDao.deleteBoard(board);
		if (cnt < 1) {
			throw new BizNotEffectedException("[" + board.getBoNo() + "] 삭제 실패");
		}
		if (!vo.getBoPass().equals(board.getBoPass())) {
			throw new BizPasswordNotMatchedException("[" + board.getBoPass() + "] 비밀번호 불일치");
		}
	}

	@Override
	public void increaseHit(int boNo) {
		freeDao.increaseHit(boNo);
	}
}
