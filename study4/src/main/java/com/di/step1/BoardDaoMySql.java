package com.di.step1;

public class BoardDaoMySql implements IBoardDao {
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:mysql://localhost/dev";
	private String username = "java";
	private String password = "oracle";
	
	/*
	 * public void selectBoardList() { System.out.printf("커넥션정보[%s]연결성공 %n", url);
	 * System.out.println("[mySql] 게시판 정보를 조회 했습니다."); }
	 */

	
	@Override
	public void getBoardList() {
		System.out.printf("커넥션정보[%s]연결성공 %n", url);
		System.out.println("[mySql] 게시판 정보를 조회 했습니다.");
	}
	
	
	
	
}
