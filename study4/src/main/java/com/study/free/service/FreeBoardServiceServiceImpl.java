package com.study.free.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.common.util.MybatisSqlSessionFactory;
import com.study.exception.BizNotEffectedException;
import com.study.exception.BizNotFoundException;
import com.study.exception.BizPasswordNotMatchedException;
import com.study.free.dao.IFreeBoardDao;
import com.study.free.vo.FreeBoardSearchVO;
import com.study.free.vo.FreeBoardVO;

@Service
public class FreeBoardServiceServiceImpl implements IFreeBoardService {

	@Autowired
	private IFreeBoardDao freeDao;

	@Override
	public List<FreeBoardVO> getBoardList(FreeBoardSearchVO searchVO) {
		// 건수를 구해서 searchVO 설정 -> searchVO.pageSetting() ->list 호
		int cnt = freeDao.getBoardCount(searchVO);
		searchVO.setTotalRowCount(cnt);
		searchVO.pageSetting();

		List<FreeBoardVO> list = freeDao.getBoardList(searchVO);
		return list;
	}

	@Override
	public FreeBoardVO getBoard(int boNo) throws BizNotFoundException {
		FreeBoardVO vo = freeDao.getBoard(boNo);
		if (vo == null) {
			throw new BizNotFoundException("[" + boNo + "] 조회 실패");
		}
		return vo;
	}

	@Override
	public void registBoard(FreeBoardVO board) {
		freeDao.insertBoard(board);
	}

	@Override
	public void modifyBoard(FreeBoardVO board)
			throws BizNotFoundException, BizPasswordNotMatchedException, BizNotEffectedException {
		FreeBoardVO vo = freeDao.getBoard(board.getBoNo());
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
	public void removeBoard(FreeBoardVO board)
			throws BizNotFoundException, BizPasswordNotMatchedException, BizNotEffectedException {
		FreeBoardVO vo = freeDao.getBoard(board.getBoNo());
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
