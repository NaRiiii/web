package com.di.step1;

public class BoardService {

	//private IBoardDao boardDaoOracle = new BoardDaoOracle();
	private IBoardDao boardDao = new BoardDaoOracle();
	
	public void getBoardList() {
		boardDao.getBoardList();
	}
	
	
}
