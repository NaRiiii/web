package com.di.step3;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class TestBoardService {

	public static void main(String[] args) {
		
		
		//스프링 설정을 읽어서 해당 빈을 받아서 실행 
		AbstractApplicationContext context = new GenericXmlApplicationContext("spring/step3.xml");
		
//		BoardService boardService = new BoardService();
//		boardService.getBoardList();		
		BoardService service = context.getBean("boardService",BoardService.class);
		service.getBoardList();
		
		context.close();
		
	}
	 
}
