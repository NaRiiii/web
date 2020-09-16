package com.study.free.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.study.common.sql.CommonSQL;
import com.study.exception.DaoException;
import com.study.exception.DaoduplicateKeyException;
import com.study.free.vo.FreeBoardSearchVO;
import com.study.free.vo.FreeBoardVO;

public class FreeBoradDaoOracle implements IFreeBoardDao {

	@Override
	public int getBoardCount(FreeBoardSearchVO searchVO) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {                                                                                                               
			StringBuffer sb = new StringBuffer();
			sb.append( "	SELECT	 count(*)    ");
		    sb.append( "	  FROM	 free_board  ");
		    sb.append( "	  WHERE	bo_del_yn = 'N'  "); //밑에 조건에 where 또는 and 를 조건별로 집어넣어야하기 때문에 무조건 참인 1=1을 넣어 공통으로 and가 들어가도록 만든다 
		    
		    //검색처리
		    if(StringUtils.isNotBlank(searchVO.getSearchCategory())) {
		    	sb.append( "	  AND bo_category = ?  "); 
		    }
		    if(StringUtils.isNotBlank(searchVO.getSearchWord())){
		    	switch (searchVO.getSearchType()) {
				case "T":  sb.append( "	  AND bo_title like '%' ||  ?  || '%'  ");	break;
				case "W":	sb.append( "	  AND bo_writer like '%' ||  ?  || '%'  "); break;
				case "C":	sb.append( "	  AND bo_content like '%' ||  ?  || '%'  "); break;
				}
		    }
		    
			System.out.println(sb.toString().replaceAll("\\s{2,}", "")); //너무 길어서 공백이 두개인것을 지우기 정규표현식 
			pstmt = conn.prepareStatement(sb.toString());
			//바인드 변수 처리 
			int i=1;
			if(StringUtils.isNotBlank(searchVO.getSearchCategory())) {
		    	pstmt.setString(i++, searchVO.getSearchCategory() );
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
	public List<FreeBoardVO> getBoardList(FreeBoardSearchVO searchVO) {
		 
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer sb = new StringBuffer();
		List<FreeBoardVO> list = new ArrayList<FreeBoardVO>();
		
		try { //*
			sb.append(CommonSQL.PRE_PAGING_SQL);
			
			sb.append(" SELECT                                                  ");
			sb.append("    bo_no    , bo_title    , comm_nm    , bo_writer    , bo_pass      ");
			sb.append("    , bo_content    , bo_ip    , bo_hit    , to_char(bo_reg_date,'yyyy-mm-dd') as bo_reg_date    , bo_del_yn   ");
			sb.append("	FROM        free_board , comm_code                                          ");
			sb.append("	WHERE comm_cd = bo_category                                         ");
			sb.append("	AND bo_del_yn = 'N'                                        ");
			
			//검색처리
		    if(StringUtils.isNotBlank(searchVO.getSearchCategory())) {
		    	sb.append( "	  AND bo_category = ?  "); 
		    }
		    if(StringUtils.isNotBlank(searchVO.getSearchWord())){
		    	switch (searchVO.getSearchType()) {
				case "T":  sb.append( "	  AND bo_title like '%' ||  ?  || '%'  ");	break;
				case "W":	sb.append( "	  AND bo_writer like '%' ||  ?  || '%'  "); break;
				case "C":	sb.append( "	  AND bo_content like '%' ||  ?  || '%'  "); break;
				}
		    }
			sb.append("	ORDER BY bo_no desc                 ");
			
			sb.append(CommonSQL.SUF_PAGING_SQL);
			
			System.out.println(sb.toString().replaceAll("\\s{2,}", "")); //너무 길어서 공백이 두개인것을 지우기 정규표현식 
			
			pstmt = conn.prepareStatement(sb.toString());
			//bind변수
			int i =1;
			if(StringUtils.isNotBlank(searchVO.getSearchCategory())) {
		    	pstmt.setString(i++, searchVO.getSearchCategory() );
		    }
		    if(StringUtils.isNotBlank(searchVO.getSearchWord())){
		    	pstmt.setString(i++, searchVO.getSearchWord() ); 	//searchType이 설정 되어 있다는 가정하에.
		    }
			pstmt.setInt(i++, searchVO.getLastRow());
			pstmt.setInt(i++, searchVO.getFirstRow());
			pstmt.setInt(i++, searchVO.getLastRow());
			
			rs = pstmt.executeQuery();
			FreeBoardVO board = null; //요기서 new하게되면 모든 항목의 주소지가 같음 그래서 선언만 하고 
			while(rs.next()) {
				board = new FreeBoardVO(); //여기서 new 해줌 
				board.setBoNo(rs.getInt("bo_no"));
				board.setBoTitle(rs.getString("bo_title"));
				board.setBoCategoryNm(rs.getString("comm_nm"));
				board.setBoWriter(rs.getString("bo_writer"));
				board.setBoPass(rs.getString("bo_pass"));
				board.setBoContent(rs.getString("bo_content"));
				board.setBoIp(rs.getString("bo_ip"));
				board.setBoHit(rs.getInt("bo_hit"));
				board.setBoRegDate(rs.getString("bo_reg_date"));
				board.setBoDelYn(rs.getString("bo_del_yn"));
		
				list.add(board);
			}
			return list;
		} catch (SQLException e) {
			throw new DaoException(e.getMessage(), e);
		}finally {
			if(rs != null) try{rs.close();}catch(SQLException e){}
			if(pstmt != null) try{pstmt.close();}catch(SQLException e){}
		}
		
		
		
	}

	@Override
	public FreeBoardVO getBoard(int boNo) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer sb = new StringBuffer();
		List<FreeBoardVO> list = new ArrayList<FreeBoardVO>();
		
		try {                                                                                                               
			sb.append( "	SELECT		    bo_no		    , bo_title		    ,     bo_category                                 ");
		    sb.append( "	,(  select comm_nm from comm_code where bo_category = comm_cd ) AS bo_Category_Nm          ");
		    sb.append( "	,  bo_writer		    , bo_pass		    , bo_content		    , bo_ip                     ");
		    sb.append( "	,  bo_hit		    , bo_reg_date	    , bo_del_yn                 ");
		    sb.append( "	FROM		    free_board                                                                   ");
		    sb.append( "	WHERE bo_no = ?                                                                           ");
			
			
			System.out.println(sb.toString().replaceAll("\\s{2,}", "")); //너무 길어서 공백이 두개인것을 지우기 정규표현식 
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setInt(1,boNo );
			rs = pstmt.executeQuery();
			FreeBoardVO board = null; //요기서 new하게되면 모든 항목의 주소지가 같음 그래서 선언만 하고 
			while(rs.next()) {
				board =new FreeBoardVO();
				board.setBoNo(rs.getInt("bo_no"));
				board.setBoTitle(rs.getString("bo_title"));
				board.setBoCategory(rs.getString("bo_category"));
				board.setBoCategoryNm(rs.getString("bo_Category_Nm"));
				board.setBoWriter(rs.getString("bo_writer"));
				board.setBoPass(rs.getString("bo_pass"));
				board.setBoContent(rs.getString("bo_content"));
				board.setBoIp(rs.getString("bo_ip"));
				board.setBoHit(rs.getInt("bo_hit"));
				board.setBoRegDate(rs.getString("bo_reg_date"));
				board.setBoDelYn(rs.getString("bo_del_yn"));
				list.add(board);
			}
			return board;
		} catch (SQLException e) {
			throw new DaoException(e.getMessage(), e);
		}finally {
			if(rs != null) try{rs.close();}catch(SQLException e){}
			if(pstmt != null) try{pstmt.close();}catch(SQLException e){}
		}
	}

	@Override
	public int insertBoard(FreeBoardVO board) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer sb = new StringBuffer();
		
		try {                                                                                      
		sb.append(" INSERT INTO free_board (                                                          ");
		sb.append("		    bo_no    , bo_title    , bo_category    , bo_writer    , bo_pass         ");
		sb.append("		    , bo_content    , bo_ip    , bo_hit    , bo_reg_date   , bo_del_yn       ");
		sb.append("		) VALUES (                                                                   ");
		sb.append("		    SEQ_FREE_BOARD.nextval    , ?    , ?    , ?    , ?              ");
		sb.append("		    , ?    , ?    , 0    , sysdate    , 'N')                              ");
		
		System.out.println(sb.toString().replaceAll("\\s{2,}", ""));
		pstmt = conn.prepareStatement(sb.toString());
		int i = 1;
		pstmt.setString(i++, board.getBoTitle());
		pstmt.setString(i++, board.getBoCategory());
		pstmt.setString(i++, board.getBoWriter());
		pstmt.setString(i++, board.getBoPass());
		pstmt.setString(i++, board.getBoContent());
		pstmt.setString(i++, board.getBoIp());
		int cnt = pstmt.executeUpdate();
		
			return cnt;
		} catch (SQLException e) {
			if(e.getErrorCode() == 1) {	//유니크에러일때 unique
				throw new DaoduplicateKeyException("중복된 코드 발생 = [" + board.getBoNo() +"]");				
			}
			throw new DaoException(e.getMessage(),e);				
		}finally {
			if(rs != null) try{rs.close();}catch(SQLException e){}
			if(pstmt != null) try{pstmt.close();}catch(SQLException e){}
		}
	}

	@Override
	public int updateBoard(FreeBoardVO board) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer sb = new StringBuffer();
		
		try {
			sb.append(" UPDATE free_board         ");
			sb.append(" SET            ");
			sb.append("     bo_title = ?         ");
			sb.append("     ,bo_category = ?      ");
			sb.append("     ,bo_writer = ?        ");
			sb.append("     ,bo_pass = ?          ");
			sb.append("     ,bo_content = ?       ");
			sb.append("     ,bo_ip = ?            ");
			sb.append("     ,bo_hit = ?           ");
			sb.append("     ,bo_reg_date = sysdate      ");
			sb.append(" WHERE bo_no = ?           ");
			
			System.out.println(sb.toString().replaceAll("\\s{2,}", "")); //너무 길어서 공백이 두개인것을 지우기 정규표현식 
			pstmt = conn.prepareStatement(sb.toString());
			int i=1;
			pstmt.setString(i++, board.getBoTitle());
			pstmt.setString(i++, board.getBoCategory());
			pstmt.setString(i++, board.getBoWriter());
			pstmt.setString(i++, board.getBoPass());
			pstmt.setString(i++, board.getBoContent());
			pstmt.setString(i++, board.getBoIp());
			pstmt.setInt(i++, board.getBoHit());
			pstmt.setInt(i, board.getBoNo());
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
	public int deleteBoard(FreeBoardVO board) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer sb = new StringBuffer();
		
		try {
			sb.append(" UPDATE free_board ");
			sb.append(" SET bo_del_yn = 'Y' ");
			sb.append(" WHERE bo_no = ? ");
			
			System.out.println(sb.toString().replaceAll("\\s{2,}", "")); //너무 길어서 공백이 두개인것을 지우기 정규표현식 
			pstmt = conn.prepareStatement(sb.toString());
			int i=1;
			pstmt.setInt(i++, board.getBoNo());
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
	public int increaseHit(int boNo) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer sb = new StringBuffer();
		
		try {
			sb.append(" UPDATE free_board         ");
			sb.append(" SET    bo_hit = bo_hit+1        ");
			sb.append(" WHERE bo_no = ?        ");

			
			System.out.println(sb.toString().replaceAll("\\s{2,}", "")); //너무 길어서 공백이 두개인것을 지우기 정규표현식 
			pstmt = conn.prepareStatement(sb.toString());
			int i=1;
			
			pstmt.setInt(i, boNo);
			int cnt = pstmt.executeUpdate();

			return cnt;
		} catch (SQLException e) {
			throw new DaoException(e.getMessage(), e);
		}finally {
			if(rs != null) try{rs.close();}catch(SQLException e){}
			if(pstmt != null) try{pstmt.close();}catch(SQLException e){}
		}
	}

}
