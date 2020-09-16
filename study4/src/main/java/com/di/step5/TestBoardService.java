package com.di.step5;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class TestBoardService {

	public static void main(String[] args) {
				
//		AbstractApplicationContext context = new GenericXmlApplicationContext("spring/step3.xml");
		AbstractApplicationContext context = new AnnotationConfigApplicationContext(JavaConfigScan.class);
		
		BoardService service = context.getBean("boardService",BoardService.class);
		service.getBoardList();
		
		context.close();
		
	}
	 
}
