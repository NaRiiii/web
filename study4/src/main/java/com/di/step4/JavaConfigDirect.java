package com.di.step4;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class JavaConfigDirect {
	
	@Bean
	public BoardDaoOracle getBoardDaoOracle() {
		BoardDaoOracle oracle = new BoardDaoOracle();
		oracle.setUrl("jdbc:oracle");
		return oracle;
	}
	
	@Bean
//	@Scope(value = "prototype")
	public BoardDaoMySql getBoardDaoMySql() {
		BoardDaoMySql mySql = new BoardDaoMySql();
		mySql.setUrl("jdbc:mysql");
		return mySql;
	}
	
	@Bean(initMethod = "myInit", destroyMethod = "myDestroy")
	public BoardService geBoardService() { 
		BoardService service = new BoardService();
		service.setBoardDao(getBoardDaoMySql());
		return service;
	}

}
