package com.study.reply.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.study.exception.BizAccessFailException;
import com.study.exception.BizNotFoundException;
import com.study.login.vo.UserVO;
import com.study.reply.service.IReplyService;
import com.study.reply.vo.ReplySearchVO;
import com.study.reply.vo.ReplyVO;

//@Controller
@RestController
public class ReplyController {
	
	@Autowired
	private IReplyService replyService; // = new ReplyServiceImpl();

//	@ResponseBody
	@RequestMapping(value = "/reply/replyList", produces = "application/json;charset=UTF-8")
	public Map<String, Object> replyList(ReplySearchVO searchVO) throws Exception {
		List<ReplyVO> list = replyService.getReplyListByParent(searchVO);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", true);
		map.put("data", list);
		map.put("count", list.size()); 
		return map;
	}

	// @PostMapping ("/reply/replyRegist")
	@RequestMapping(value = "/reply/replyRegist", method = RequestMethod.POST)
	public Map<String, Object> replyRegist(ReplyVO reply, HttpServletRequest req, HttpSession session) throws Exception {
		reply.setReIp(req.getRemoteAddr());
		UserVO user = (UserVO) session.getAttribute("USER_INFO");
		reply.setReMemId(user.getUserId());
		replyService.registReply(reply);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", true);
		map.put("msg", "정상 등록 되었습니다.");
		return map;
		
	}

	@RequestMapping(value = "/reply/replyModify")
	public Map<String, Object> replyModify(ReplyVO reply, HttpSession session) throws Exception {
		UserVO user = (UserVO) session.getAttribute("USER_INFO");
		reply.setReMemId(user.getUserId());
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			replyService.modifyReply(reply);
			map.put("result", true);
			map.put("msg", "수정 되었습니다.");
			return map;
		} catch (BizNotFoundException e) {
			map.put("result", false);
			map.put("msg", "글이 존재하지 않습니다.");
			return map;
		} catch (BizAccessFailException e) {
			map.put("result", false);
			map.put("msg", "접근에 실패했습니다.");
			return map;
		}
	}
	
	@RequestMapping(value = "/reply/replyDelete")
	public Map<String, Object> replyDelete(ReplyVO reply, HttpSession session) throws Exception {
		UserVO user = (UserVO) session.getAttribute("USER_INFO");
		reply.setReMemId(user.getUserId());
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			replyService.removeReply(reply);
			map.put("result", true);
			map.put("msg", "삭제 되었습니다.");
			return map;
		} catch (BizNotFoundException e) {
			map.put("result", false);
			map.put("msg", "글이 존재하지 않습니다.");
			return map;
		} catch (BizAccessFailException e) {
			map.put("result", false);
			map.put("msg", "접근에 실패했습니다.");
			return map;
		}
	}

}
