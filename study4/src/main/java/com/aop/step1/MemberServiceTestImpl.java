package com.aop.step1;

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
		long startTime = System.currentTimeMillis();
		StringBuffer sb = new StringBuffer();
		sb.append("\n------------------------------------------- ");
		sb.append("\n MemberServiceTestImpl : getMember ");
		sb.append("\n args : " + ToStringBuilder.reflectionToString(memId));
		sb.append("\n------------------------------------------- ");
		logger.debug(sb.toString());
		
		MemberVO vo = memberDao.getMember(memId);
		if (vo == null) {
			sb = new StringBuffer();
			sb.append("\n MemberServiceTestImpl : getMember ");
			sb.append("\n BizNotFoundException 발생 ");
			logger.debug(sb.toString());
			throw new BizNotFoundException("[" + memId + "] 조회 실패 ");
		}
		
		sb = new StringBuffer();
		sb.append("\n MemberServiceTestImpl : getMember ");
		sb.append("\n Return " + ToStringBuilder.reflectionToString(vo));
		logger.debug(sb.toString());
		
		long duration = System.currentTimeMillis() - startTime;
		logger.info("{}.{} : 수행시간 = {}", getClass().getSimpleName(), "getMember", duration);
		return vo;
	}

	@Override
	public List<MemberVO> getMemberList(MemberSearchVO searchVO) {
		long startTime = System.currentTimeMillis();
		StringBuffer sb = new StringBuffer();
		sb.append("\n MemberServiceTestImpl : getMemberList ");
		sb.append("\n args : " + ToStringBuilder.reflectionToString(searchVO));
		logger.debug(sb.toString());
		
		int cnt = memberDao.getMemberCount(searchVO);
		searchVO.setTotalRowCount(cnt);
		searchVO.pageSetting();
		List<MemberVO> list = memberDao.getMemberList(searchVO);
		sb = new StringBuffer();
		sb.append("\n MemberServiceTestImpl : getMemberList ");
		sb.append("\n Return list.count " + list.size() );
		logger.debug(sb.toString());
		
		long duration = System.currentTimeMillis() - startTime;
		logger.info("{}.{} : 수행시간 = {}", getClass().getSimpleName(), "getMemberList", duration);
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
