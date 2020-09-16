package com.study.servlet;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestTransactionController {

	@Inject
	private MemberServiceTransactionImpl serviceTransactionImpl;
	
	@RequestMapping(path= "/test/{mode}", produces = "text/plain; charset=utf-8")
	@ResponseBody
	public String test(@PathVariable("mode") String md ) {
		StringBuffer sb = new StringBuffer();
		
		try {
			switch (md) {
			case "t1":  serviceTransactionImpl.t1();
			            sb.append("서비스의 T1 메서드 호출 완료 ");
			            sb.append("\n 데이터베이스를 확인해 주세요  ");
			            break;
			case "t2":  serviceTransactionImpl.t2();
						sb.append("서비스의 T2 메서드 호출 완료 ");
						sb.append("\n 데이터베이스를 확인해 주세요  ");
						break;
			case "t3":  serviceTransactionImpl.t3();
						sb.append("서비스의 T3 메서드 호출 완료 ");
						sb.append("\n 데이터베이스를 확인해 주세요  ");
						break;
			default:    sb.append("url 은 t1,t2,t3 이어야만 합니다.");
						break;
			}
		} catch (Exception e) {
			sb.append("서비스의 " + md +" 메서드 호출 완료 , 오류가 발생했습니다. ");
			sb.append("\n 데이터베이스를 확인해 주세요  ");
			e.printStackTrace();
		}
		return sb.toString();
	}
	
}
