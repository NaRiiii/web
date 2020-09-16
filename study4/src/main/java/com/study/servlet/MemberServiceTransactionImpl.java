package com.study.servlet;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.study.exception.BizDuplicateKeyException;
import com.study.member.dao.IMemberDao;
import com.study.member.vo.MemberVO;

@Service
public class MemberServiceTransactionImpl {

	@Inject
	private IMemberDao memberDao;
	
	
	public void t1() {
		// b001 조회 -> 이름 : 선영엄마 , addr2 : 관저동 헤링톤 수정
		// id : a001 -> 이름 : 태영♡철희 , addr2 : 용두동   수정 
		// id : jiwon -> 이름 : 지원엄마 등록
		MemberVO vo = memberDao.getMember("b001");
		vo.setMemName("선영엄마");
		vo.setMemAdd2("관저동 헤링톤");
		memberDao.updateMember(vo);
		
		vo.setMemId("a001");
		vo.setMemName("태영♡철희");
		vo.setMemAdd2("용두동");		
		memberDao.updateMember(vo);
		
		vo.setMemId("jiwon");
		vo.setMemName("지원엄마");		
		memberDao.insertMember(vo);		
	}
	
	public void t2() {
		MemberVO vo = memberDao.getMember("b001");
		vo.setMemName("선영엄마2");
		vo.setMemAdd2("관저동 헤링톤2");
		memberDao.updateMember(vo);
		
		vo.setMemId("a001");
		vo.setMemName("태영철희2");
		vo.setMemAdd2("용두동 22");		
		memberDao.updateMember(vo);
		
		// 이미 존재하는 회원이 있어서 오류가 발생  
		vo.setMemId("jiwon");
		vo.setMemName("지원엄마");		
		memberDao.insertMember(vo);		
	}
	
	public void t3() throws Exception {
		MemberVO vo = memberDao.getMember("b001");
		vo.setMemName("선영엄마3");
		vo.setMemAdd2("관저동 헤링톤3");
		memberDao.updateMember(vo);
		
		vo.setMemId("a001");
		vo.setMemName("태영철희3");
		vo.setMemAdd2("용두동 33");		
		memberDao.updateMember(vo);
		
		// 일부러 예외를 발생   
		throw new BizDuplicateKeyException();		
	}
	
	
	
}
