package com.study.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class LoggingFilter implements Filter {

	//WAS가 JAVA8로 컴파일 되었으면 doFilter만 (Tomcat 9 이상)
	//WAS가 JAVA8 미만일 경우 init, destroy모두 구현해야 함 P.546
	
	//소요시간, uri, ip, user-agent 를 필터해야함 
	
	private final Log logger = LogFactory.getLog(getClass());
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		//전처리
		//소요시간
		long startTime = System.currentTimeMillis();	// 현재 시간 저장해둠
		
		
		chain.doFilter(request, response);
		//후처리
		String uri = ((HttpServletRequest)request).getRequestURI();
		String ip = request.getRemoteAddr();
		logger.debug("소요시간 : " +  (System.currentTimeMillis()-startTime) + ", IP : " + ip + ", 요청 uri : " + uri);
		
		
		
	}

	
	
}
