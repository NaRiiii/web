package com.di.step3;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class BoardDaoOracle implements IBoardDao {
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String username = "java";
	private String password = "oracle";
	
	//url정보를 setter를 통해 받도록 변경합ㅌ니다
	@Value("SQLD는 Oracle만 조아해 ")
	private String url; // = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
	
	
	
	@Override
	public void getBoardList() {
		System.out.printf("커넥션정보[%s]연결성공 %n", url);
		System.out.println("[오라클] 게시판 정보를 조회 했습니다.");
	}
	
	
	
	
}
