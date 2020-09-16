package com.study.member.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.study.common.sql.CommonSQL;
import com.study.exception.DaoException;
import com.study.exception.DaoduplicateKeyException;
import com.study.member.vo.MemberSearchVO;
import com.study.member.vo.MemberVO; 

public class MemberDaoOracle implements IMemberDao{

	
	@Override
	public int getMemberCount(MemberSearchVO searchVO) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {                                                                                                               
			StringBuffer sb = new StringBuffer();
			sb.append( "	SELECT	 count(*)    ");
		    sb.append( "	  FROM	 member  ");
		    sb.append( "	  WHERE mem_delete = 'N'  "); //밑에 조건에 where 또는 and 를 조건별로 집어넣어야하기 때문에 무조건 참인 1=1을 넣어 공통으로 and가 들어가도록 만든다 
		    
		    //검색처리
		    if(StringUtils.isNotBlank(searchVO.getSearchJob())) {
		    	sb.append( "	  AND mem_job = ?  "); 
		    }
		    if(StringUtils.isNotBlank(searchVO.getSearchLike())) {
		    	sb.append( "	  AND mem_like = ?  "); 
		    }
		    if(StringUtils.isNotBlank(searchVO.getSearchWord())){
		    	switch (searchVO.getSearchType()) {
				case "NM":  sb.append( "	  AND mem_name like '%' ||  ?  || '%'  ");	break;
				case "ID":	sb.append( "	  AND mem_id like '%' ||  ?  || '%'  "); break;
				case "HP":	sb.append( "	  AND mem_hp like '%' ||  ?  || '%'  "); break;
				case "ADD":sb.append( "	  AND mem_add1 like '%' ||  ?  || '%'  "); break;
				}
		    }
		    
			System.out.println(sb.toString().replaceAll("\\s{2,}", "")); //너무 길어서 공백이 두개인것을 지우기 정규표현식 
			pstmt = conn.prepareStatement(sb.toString());
			//바인드 변수 처리 
			int i=1;
			if(StringUtils.isNotBlank(searchVO.getSearchJob())) {
		    	pstmt.setString(i++, searchVO.getSearchJob() );
		    }
			if(StringUtils.isNotBlank(searchVO.getSearchLike())) {
				pstmt.setString(i++, searchVO.getSearchLike() );
			}
		    if(StringUtils.isNotBlank(searchVO.getSearchWord())){
		    	pstmt.setString(i++, searchVO.getSearchWord() ); 	//searchType이 설정 되어 있다는 가정하에.
		    }
			
			
			rs = pstmt.executeQuery();
			int cnt =0;
			if(rs.next()) {
				cnt = rs.getInt(1);
			}
			return cnt;
		} catch (SQLException e) {
			throw new DaoException(e.getMessage(), e);
		}finally {
			if(rs != null) try{rs.close();}catch(SQLException e){}
			if(pstmt != null) try{pstmt.close();}catch(SQLException e){}
		}
	}
	
	@Override
	public int insertMember(MemberVO member) { //*
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer sb = new StringBuffer();
		
		try {
			sb.append("INSERT INTO member (                             ");
			sb.append("	    mem_id    , mem_pass    , mem_name        ");
			sb.append("	    , mem_bir    , mem_zip    , mem_add1      ");
			sb.append("	    , mem_add2    , mem_hp    , mem_mail      ");
			sb.append("	    , mem_job    , mem_like    , mem_mileage  ");
			sb.append("	    , mem_delete                              ");
			sb.append("	) VALUES (                                    ");
			sb.append("  ?, ?, ? ");
			sb.append(" ,?, ?, ? ");
			sb.append(" ,?, ?, ? ");
			sb.append(" ,?, ?, 0 ");
			sb.append(" , 'N')");
			
			System.out.println(sb.toString().replaceAll("\\s(2,)", "")); //너무 길어서 공백이 두개인것을 지우기 정규표현식 
			pstmt = conn.prepareStatement(sb.toString());
			int i = 1;
			pstmt.setString(i++, member.getMemId());
			pstmt.setString(i++, member.getMemPass());
			pstmt.setString(i++, member.getMemName());
			pstmt.setString(i++, member.getMemBir());
			pstmt.setString(i++, member.getMemZip());
			pstmt.setString(i++, member.getMemAdd1());
			pstmt.setString(i++, member.getMemAdd2());
			pstmt.setString(i++, member.getMemHp());
			pstmt.setString(i++, member.getMemMail());
			pstmt.setString(i++, member.getMemJob());
			pstmt.setString(i++, member.getMemLike());
			int cnt = pstmt.executeUpdate();

			return cnt;
		} catch (SQLException e) {
			if(e.getErrorCode() == 1) {	//유니크에러일때 unique
				throw new DaoduplicateKeyException("중복된 코드 발생 = [" + member.getMemId() +"]");				
			}
			throw new DaoException(e.getMessage(),e);				
		}finally {
			if(rs != null) try{rs.close();}catch(SQLException e){}
			if(pstmt != null) try{pstmt.close();}catch(SQLException e){}
		}
	}

	@Override
	public int updateMember(MemberVO member) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer sb = new StringBuffer();
		
		try {
			sb.append("UPDATE member          " );
			sb.append("   SET mem_name    = ? " );
			sb.append("     , mem_bir     = ? " );
			sb.append("     , mem_zip     = ? " );
			sb.append("     , mem_add1    = ? " );
			sb.append("     , mem_add2    = ? " );
			sb.append("     , mem_hp      = ? " );
			sb.append("     , mem_mail    = ? " );
			sb.append("     , mem_job     = ? " );
			sb.append("     , mem_like    = ? " );
			sb.append("WHERE mem_id = ?       " );
			
			System.out.println(sb.toString().replaceAll("\\s(2,)", "")); //너무 길어서 공백이 두개인것을 지우기 정규표현식 
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setString(1, member.getMemName());
			pstmt.setString(2, member.getMemBir());
			pstmt.setString(3, member.getMemZip());
			pstmt.setString(4, member.getMemAdd1());
			pstmt.setString(5, member.getMemAdd2());
			pstmt.setString(6, member.getMemHp());
			pstmt.setString(7, member.getMemMail());
			pstmt.setString(8, member.getMemJob());
			pstmt.setString(9, member.getMemLike());
			pstmt.setString(10, member.getMemId());
			int cnt = pstmt.executeUpdate();

			return cnt;
		} catch (SQLException e) {
			throw new DaoException(e.getMessage(), e);
		}finally {
			if(rs != null) try{rs.close();}catch(SQLException e){}
			if(pstmt != null) try{pstmt.close();}catch(SQLException e){}
		}
	} //updateMember

	@Override
	public int deleteMember(MemberVO member) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer sb = new StringBuffer();
		
		try {
			sb.append(" UPDATE member ");
			sb.append(" SET mem_delete = 'Y' ");
			sb.append(" WHERE mem_id = ? ");
			
			System.out.println(sb.toString().replaceAll("\\s{2,}", "")); //너무 길어서 공백이 두개인것을 지우기 정규표현식 
			pstmt = conn.prepareStatement(sb.toString());
			int i=1;
			pstmt.setString(i++, member.getMemId());
			int cnt = pstmt.executeUpdate();

			return cnt;
		} catch (SQLException e) {
			throw new DaoException(e.getMessage(), e);
		}finally {
			if(rs != null) try{rs.close();}catch(SQLException e){}
			if(pstmt != null) try{pstmt.close();}catch(SQLException e){}
		}
	}

	@Override
	public MemberVO getMember(String memId) { //*
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer sb = new StringBuffer();
		
		try {
			sb.append(" SELECT mem_id      , mem_pass    , mem_name          ");
			sb.append("	   , mem_bir     , mem_zip     , mem_add1          ");
			sb.append("      , mem_add2    , mem_hp      , mem_mail          ");
			sb.append("      , mem_job     ");
			sb.append("      , ( select comm_nm from comm_code where mem_job = comm_cd ) as mem_job_nm     ");
			sb.append("      , mem_like       ");
			sb.append("      , ( select comm_nm from comm_code where mem_like = comm_cd ) as mem_like_nm       ");
			sb.append("      , mem_delete  , mem_mileage                                  ");
			sb.append("   FROM member                                        ");
			sb.append("  WHERE mem_id = ? ");
			
			System.out.println(sb.toString().replaceAll("\\s{2,}", "")); //너무 길어서 공백이 두개인것을 지우기 정규표현식 
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setString(1, memId);
			rs = pstmt.executeQuery();
			MemberVO member = null; //요기서 new하게되면 모든 항목의 주소지가 같음 그래서 선언만 하고 
			while(rs.next()) {
				member = new MemberVO(); //여기서 new 해줌 
				member.setMemId(rs.getString("mem_id"));
				member.setMemName(rs.getString("mem_name"));
				member.setMemPass(rs.getString("mem_pass"));
				member.setMemBir(rs.getString("mem_bir"));
				member.setMemZip(rs.getString("mem_zip"));
				member.setMemAdd1(rs.getString("mem_add1"));
				member.setMemAdd2(rs.getString("mem_add2"));
				member.setMemHp(rs.getString("mem_hp"));
				member.setMemMail(rs.getString("mem_mail"));
				member.setMemJob(rs.getString("mem_job"));
				member.setMemJobNm(rs.getString("mem_job_nm"));
				member.setMemLike(rs.getString("mem_like"));
				member.setMemLikeNm(rs.getString("mem_like_nm"));
				member.setMemMileage(rs.getInt("mem_mileage"));				
				member.setMemDelete(rs.getString("mem_delete"));				
			}
			return member;
		} catch (SQLException e) {
			throw new DaoException(e.getMessage(), e);
		}finally {
			if(rs != null) try{rs.close();}catch(SQLException e){}
			if(pstmt != null) try{pstmt.close();}catch(SQLException e){}
		}
	} //getMember

	@Override
	public List<MemberVO> getMemberList(MemberSearchVO searchVO) {
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer sb = new StringBuffer();
		List<MemberVO> list = new ArrayList<MemberVO>();
		
		try { //*
			sb.append(CommonSQL.PRE_PAGING_SQL);
			
			sb.append(" SELECT mem_id      , mem_pass    , mem_name          ");
			sb.append("	   , mem_bir     , mem_zip     , mem_add1          ");
			sb.append("      , mem_add2    , mem_hp      , mem_mail          ");
			sb.append("      , mem_job     ");
			sb.append("      , b.comm_nm as mem_job_nm               ");
			sb.append("      , mem_like                              ");
			sb.append("      , c.comm_nm as mem_like_nm              ");
			sb.append("      , mem_delete    ,mem_mileage            ");
			sb.append("   FROM member, comm_code b, comm_code c       ");
			sb.append("   WHERE mem_job = b.comm_cd                   ");
			sb.append("   AND mem_job = c.comm_cd                     ");
			sb.append("   AND mem_delete = 'N'                         ");
			
			//검색처리
		    if(StringUtils.isNotBlank(searchVO.getSearchJob())) {
		    	sb.append( "	  AND mem_job = ?  "); 
		    }
		    if(StringUtils.isNotBlank(searchVO.getSearchLike())) {
		    	sb.append( "	  AND mem_like = ?  "); 
		    }
		    if(StringUtils.isNotBlank(searchVO.getSearchWord())){
		    	switch (searchVO.getSearchType()) {
				case "NM":  sb.append( "	  AND mem_name like '%' ||  ?  || '%'  ");	break;
				case "ID":	sb.append( "	  AND mem_id like '%' ||  ?  || '%'  "); break;
				case "HP":	sb.append( "	  AND mem_hp like '%' ||  ?  || '%'  "); break;
				case "ADD":	sb.append( "	  AND mem_add1 like '%' ||  ?  || '%'  "); break;
				}
		    }
		    sb.append(CommonSQL.SUF_PAGING_SQL);
			
			System.out.println(sb.toString().replaceAll("\\s{2,}", "")); //너무 길어서 공백이 두개인것을 지우기 정규표현식 
			
			pstmt = conn.prepareStatement(sb.toString());
			//bind변수
			int i =1;
			if(StringUtils.isNotBlank(searchVO.getSearchJob())) {
		    	pstmt.setString(i++, searchVO.getSearchJob() );
		    }
			if(StringUtils.isNotBlank(searchVO.getSearchLike())) {
				pstmt.setString(i++, searchVO.getSearchLike() );
			}
		    if(StringUtils.isNotBlank(searchVO.getSearchWord())){
		    	pstmt.setString(i++, searchVO.getSearchWord() ); 	//searchType이 설정 되어 있다는 가정하에.
		    }
			pstmt.setInt(i++, searchVO.getLastRow());
			pstmt.setInt(i++, searchVO.getFirstRow());
			pstmt.setInt(i++, searchVO.getLastRow());
			
			
			
			rs = pstmt.executeQuery();
			MemberVO member = null; //요기서 new하게되면 모든 항목의 주소지가 같음 그래서 선언만 하고 
			while(rs.next()) {
				member = new MemberVO(); //여기서 new 해줌 
				member.setMemId(rs.getString("mem_id"));
				member.setMemName(rs.getString("mem_name"));
				member.setMemPass(rs.getString("mem_pass"));
				member.setMemBir(rs.getString("mem_bir"));
				member.setMemZip(rs.getString("mem_zip"));
				member.setMemAdd1(rs.getString("mem_add1"));
				member.setMemAdd2(rs.getString("mem_add2"));
				member.setMemHp(rs.getString("mem_hp"));
				member.setMemJob(rs.getString("mem_job"));
				member.setMemJobNm(rs.getString("mem_job_nm")); //*
				member.setMemLike(rs.getString("mem_like"));
				member.setMemLikeNm(rs.getString("mem_like_nm")); //*
				member.setMemMileage(rs.getInt("mem_mileage"));
				list.add(member);
			}
			return list;
		} catch (SQLException e) {
			throw new DaoException(e.getMessage(), e);
		}finally {
			if(rs != null) try{rs.close();}catch(SQLException e){}
			if(pstmt != null) try{pstmt.close();}catch(SQLException e){}
		}
	} //getMemberList
}//class
