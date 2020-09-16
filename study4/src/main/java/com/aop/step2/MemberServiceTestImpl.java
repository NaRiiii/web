package com.aop.step2;

import java.util.List;

import javax.inject.Inject;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.study.exception.BizDuplicateKeyException;
import com.study.exception.BizNotEffectedException;
import com.study.exception.BizNotFoundException;
import com.study.member.dao.IMemberDao;
import com.study.member.service.IMemberService;
import com.study.member.vo.MemberSearchVO;
import com.study.member.vo.MemberVO;

@Service("memberTestService")
public class MemberServiceTestImpl  implements IMemberService{

	@Inject
	private IMemberDao memberDao;
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Override
	public MemberVO getMember(String memId) throws BizNotFoundException {
		MemberVO vo = memberDao.getMember(memId);
		if (vo == null) {
			throw new BizNotFoundException("[" + memId + "] 조회 실패 ");
		}
		return vo;
	}

	@Override
	public List<MemberVO> getMemberList(MemberSearchVO searchVO) {
		int cnt = memberDao.getMemberCount(searchVO);
		searchVO.setTotalRowCount(cnt);
		searchVO.pageSetting();
		List<MemberVO> list = memberDao.getMemberList(searchVO);
		return list;
	}

	
	
	
	@Override
	public void registMember(MemberVO member) throws BizDuplicateKeyException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void modifyMember(MemberVO member) throws BizNotEffectedException, BizNotFoundException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeMember(MemberVO member) throws BizNotEffectedException, BizNotFoundException {
		// TODO Auto-generated method stub
		
	}

	
}
