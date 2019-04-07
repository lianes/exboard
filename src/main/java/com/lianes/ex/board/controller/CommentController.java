package com.lianes.ex.board.controller;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.lianes.ex.board.dto.Comment;
import com.lianes.ex.board.dto.CommentWithUser;
import com.lianes.ex.board.service.BoardService;

@RestController
@RequestMapping(path="/comments")
public class CommentController {
	@Autowired
	BoardService boardService;
	
	@GetMapping("/{postIndex}")
	public Map<String, Object> comments(@PathVariable(name="postIndex") int postIndex) {
		if (postIndex == 0) {
			return Collections.emptyMap();
		}
		
		List<CommentWithUser> comments = boardService.getComments(postIndex);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("comments", comments);

		return map;
	}
	
	@PostMapping
	public Comment write(@RequestBody Comment comment, HttpServletRequest request) {
		Comment writeComment = comment;
		
		writeComment.setCreateTime(new Date());
		writeComment.setModifyTime(new Date());
		
		int commentIndex = boardService.writeComment(writeComment);
		
		return writeComment;
	}
	
	@DeleteMapping("/delete/{commentIndex}")
	public Map<String, String> deleteComment(@PathVariable(name="commentIndex")int commentIndex, HttpServletRequest request) {
		int deleteCount = boardService.deleteComment(commentIndex);
		
		return Collections.singletonMap("success", deleteCount > 0 ? "true" : "false");
	}
	
	@PutMapping()
	public Map<String, String> modify(@RequestBody Comment comment, HttpServletRequest request) {
		Comment modifyComment = comment;
		modifyComment.setModifyTime(new Date());
		
		int modifySuccessCount = boardService.modifyComment(modifyComment);
		
		return Collections.singletonMap("success", modifySuccessCount > 0 ? "true" : "false");
	}
}
