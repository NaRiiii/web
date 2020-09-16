package com.study.servlet;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.study.member.dao.IMemberDao;

@Service
public class MemberServiceTestImpl{

	@Inject
	private IMemberDao memberDao;
	
	public void t1() {
		// b001조회 -> 이름: 선영엄마, addr2 : 관저동 헤링통 수정
		// id: a001 -> 이름: 태영♥철희 , addr2 : 용두동 수정
		// id: jiwon -> 이름: 지원엄마 등록 
		}
	

	
}
