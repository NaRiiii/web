package com.di.step4;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class TestBoardService {

	public static void main(String[] args) {

//		AbstractApplicationContext context = new GenericXmlApplicationContext("spring/step2.xml");
		AbstractApplicationContext context = new AnnotationConfigApplicationContext(JavaConfigDirect.class);

		BoardService service = context.getBean("geBoardService", BoardService.class);
		System.out.print(service);
		service.getBoardList();
		context.close();

	}

}
