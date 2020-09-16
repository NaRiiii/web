package com.di.step4;

public class BoardService {

	//private IBoardDao boardDaoOracle = new BoardDaoOracle();
	//IBoardDao의 객체를 생성자/setter 통해서 받도록 변경
	private IBoardDao boardDao; // = new BoardDaoOracle();
	
	public void setBoardDao(IBoardDao boardDao) {
		this.boardDao = boardDao;
	}
	
	public void myInit() {
		//객체에서 필요한 초기화 작업
		System.out.println("초기화 작업을 하였습니다.");
	}
	
	
	public void myDestroy() {
		//객체가 소멸될때 불필요한 자원 정리 작업
		System.out.println("자원정리를 하였습니다.");
		System.out.println("객체 소멸.");
		
	}
	
	public void getBoardList() {
		System.out.println("boardDao=" + boardDao);
		boardDao.getBoardList();
	}
	
	
}
