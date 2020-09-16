package com.study.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.map.HashedMap;

public class IpCheckFilter implements Filter {

	private Map<String, String> denyIPMap;
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		//초기화
		denyIPMap = new HashedMap();
		//거부아이피
		denyIPMap.put("192.168.20.42", "Critical");
		denyIPMap.put("192.168.20.38", "Nomal");
		denyIPMap.put("192.168.20.40", "Nomal");
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		if(denyIPMap.containsKey(request.getRemoteAddr())){
			HttpServletResponse resp = (HttpServletResponse) response;
			resp.setContentType("text/html; charset=utf-8"); 
			PrintWriter out = response.getWriter();
			out.print("<h1>접속이 거부되었습니다.</h1>"+"<h3>현재 접속하신 IP(" + request.getRemoteAddr() + ")는 거부된 아이피 입니다.</h3> <p>문의사항이 있으시면 전화 : 042-935-1406으로 연락주세요</p>");
		}else {
			chain.doFilter(request, response);
		}
		
		
	}

}
