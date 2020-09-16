package com.study.code.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.code.dao.ICommonCodeDao;
import com.study.code.vo.CodeVO;
import com.study.common.util.MybatisSqlSessionFactory;

@Service
public class CommonCodeServiceImpl implements ICommonCodeService{

	@Autowired
	private ICommonCodeDao codedao;
	
	
	@Override
	public List<CodeVO> getCodeListByParent(String parentCode) {
			List<CodeVO> list = codedao.getCodeListByParent(parentCode);
			return list;
	}

}
