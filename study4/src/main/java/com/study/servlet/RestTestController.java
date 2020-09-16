package com.study.servlet;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.study.free.service.FreeBoardServiceServiceImpl;
import com.study.free.service.IFreeBoardService;
import com.study.free.vo.FreeBoardVO;
//@RestController //얘는 @ResponseBody가 붙은것과 같음
@Controller
public class RestTestController {

	final Logger logger = LoggerFactory.getLogger(getClass());
	IFreeBoardService boardService = new FreeBoardServiceServiceImpl();
	
	@RequestMapping(value = "/book/{no}/{mode}")
	public String p1(@PathVariable("no") int boNo, @PathVariable("mode") String mode, ModelMap model) throws Exception {
		logger.debug("no={}, mode={}",boNo, mode);
		FreeBoardVO board = boardService.getBoard(boNo);
		model.addAttribute("board",board);
		return "test";
	}
	
	
	@RequestMapping(path = "/test.nhn", produces = "text/html; charset = utf-8" ) //produces 내가 만드는 파일에 대한 설정  //consumes 받는 파일에(들어오는 데이터) 대한 설정
	public String t1() {
		return "test 나리 나디 ";
	}
	
	@RequestMapping("/test.daum")
	public void t2() {

	}
	
	@RequestMapping("/test.kakao")
	public Map<String, Object> t3() {
		Map<String, Object> sjMap = new HashMap();
		sjMap.put("msg", "교회오빠가 좋아요 ");
		return sjMap;
	}
	
	/*
	 * @RequestMapping("/test.kbs")
	 * 
	 * @ResponseBody public String t4() { return "test"; }
	 * 
	 * @RequestMapping("/test.mbc") public @ResponseBody Map t5() { Map<String,
	 * Object> sjMap = new HashMap(); sjMap.put("msg", "교회오빠가 좋아요 "); return sjMap;
	 * }
	 */
}
