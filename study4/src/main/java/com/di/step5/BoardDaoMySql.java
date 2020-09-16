package com.di.step5;

import org.springframework.stereotype.Component;

@Component
public class BoardDaoMySql implements IBoardDao {
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String username = "java";
	private String password = "oracle";


	private String url; // = "jdbc:mysql://localhost/dev";
	
	public void setUrl(String url) {
		this.url = url;
	}
	
	
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
