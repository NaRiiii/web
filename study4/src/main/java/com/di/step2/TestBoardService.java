package com.di.step2;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class TestBoardService {

	public static void main(String[] args) {

//		BoardService boardService = new BoardService();
//		BoardService boardService = BoardService.getInstance(); //싱글톤패턴
//		
//		System.out.print(boardService);

//		BoardService boardService2 = new BoardService();
//		System.out.print(boardService2);
		// 객체를 매번 만들어 사용하게되면 메모리 낭비가 일어남

//		boardService.getBoardList();

		// 스프링 설정을 읽어서 해당 빈을 받아서 실행
		
		AbstractApplicationContext context = new GenericXmlApplicationContext("spring/step2.xml");

		BoardService service = context.getBean("boardService", BoardService.class);
		System.out.print(service);
		service.getBoardList();
		
		BoardService service2 = context.getBean("boardService", BoardService.class);
		System.out.print(service2);
		service2.getBoardList();
		
		BoardService service3 = context.getBean("boardService", BoardService.class);
		System.out.print(service3);
		service3.getBoardList();

		context.close();

	}

}
