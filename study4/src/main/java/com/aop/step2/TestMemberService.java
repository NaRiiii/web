package com.aop.step2;

import java.util.List;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.study.member.service.IMemberService;
import com.study.member.vo.MemberSearchVO;
import com.study.member.vo.MemberVO;

public class TestMemberService {

	/**
	 * XML 기반 AOP 서비스 테스트 
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		// 스프링 설정을 읽어서 해당 빈을 받아서 실행 
		AbstractApplicationContext context 
		  = new GenericXmlApplicationContext("spring/aop_step2.xml");
		IMemberService memberService
		     = context.getBean("memberTestService", IMemberService.class);
		
		MemberVO member = memberService.getMember("b001");
		System.out.println(member.getMemName());
		
		MemberSearchVO searchVO = new MemberSearchVO();
		List<MemberVO> members = memberService.getMemberList(searchVO);
		
		int cnt = 1;
		for(MemberVO vo : members) {
			System.out.println(cnt++ + vo.getMemName() );
		}
		     
		context.close();
	}
	
}
