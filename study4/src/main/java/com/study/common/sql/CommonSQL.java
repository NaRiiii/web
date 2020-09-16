package com.study.common.sql;

public class CommonSQL {
	
	public final static String PRE_PAGING_SQL = 
			" select * from ( select rownum rnum , a.* from ( ";
	
	public final static String SUF_PAGING_SQL = 
			" ) a where rownum <= ? ) b where rnum between ? and ? ";
	
	
}
