package com.study.code.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.study.code.vo.CodeVO;
import com.study.exception.DaoException;

public class CommonCodeDaoOracle implements ICommonCodeDao{

	@Override
	public List<CodeVO> getCodeListByParent(String parentCode) {
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer sb = new StringBuffer();
		List<CodeVO> list = new ArrayList<CodeVO>();
		
		try { //*                                                       
			sb.append("		SELECT comm_cd                              ")  ;
			sb.append("	     , comm_nm                                  ")  ;
			sb.append("	     , comm_parent                              ")  ;
			sb.append("	     , comm_ord                                 ")  ;
			sb.append("	  FROM comm_code                                ")  ;
			sb.append("	 WHERE comm_parent = ?                     ")  ;
			sb.append("	ORDER BY comm_ord asc                          ")  ;
			
			System.out.println(sb.toString().replaceAll("\\s(2,)", "")); //너무 길어서 공백이 두개인것을 지우기 정규표현식 
			
			pstmt = conn.prepareStatement(sb.toString());
			//바인드 변수 설정
			pstmt.setString(1, parentCode);
			rs = pstmt.executeQuery();
			CodeVO code= null;  
			while(rs.next()) {
				code = new CodeVO(); //여기서 new 해줌 
				code.setCommCd(rs.getString("comm_cd"));
				code.setCommNm(rs.getString("comm_nm"));
				code.setCommParent(rs.getString("comm_parent"));
				code.setCommOrd(rs.getInt("comm_ord"));
				list.add(code);
			}
			return list;
		} catch (SQLException e) {
			throw new DaoException(e.getMessage(), e);
		}finally {
			if(rs != null) try{rs.close();}catch(SQLException e){}
			if(pstmt != null) try{pstmt.close();}catch(SQLException e){}
		}
	}

}
