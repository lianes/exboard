package com.lianes.ex.board.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.lianes.ex.board.dao.PostDao;
import com.lianes.ex.board.dto.Post;
import com.lianes.ex.board.dto.PostWithUser;
import com.lianes.ex.board.dto.User;
import com.lianes.ex.board.service.BoardService;

@Controller
public class BoardController {
	@Autowired
	BoardService boardService;
	
	@GetMapping(path="/posts")
	public String posts(@RequestParam(name="start", required=false, defaultValue="0") int start, HttpSession session, ModelMap model) {
		List<PostWithUser> posts = boardService.getPosts(start);
		
		int count = boardService.getCount();
		int pageCount = count / BoardService.LIMIT;
		if(count % BoardService.LIMIT > 0)
			pageCount++;
		
		List<Integer> pageStartList = new ArrayList<>();
		for(int i=0; i<pageCount; i++) {
			pageStartList.add(i * BoardService.LIMIT);
		}

		String loginUserId = null;
		if(!session.isNew())
			loginUserId = (String)session.getAttribute("loginUserId");
		
		model.addAttribute("posts", posts);
		model.addAttribute("count", count);
		model.addAttribute("pageStartList", pageStartList);
		
		return "posts";
	}
	
	@GetMapping(path="/content/{postIndex}")
	public String content(@PathVariable("postIndex") int index, HttpSession session, ModelMap model) {
		boardService.addPostCount(index);
		Post post = boardService.getPost(index);
		int userIndex = post.getUserIndex();
		User user = boardService.getUser(userIndex);
		
		session.setAttribute("postIndex", index);
		session.setAttribute("userIndex", userIndex);
		
		model.addAttribute("post", post);
		model.addAttribute("user", user);
		
		return "content";
	}
	
	@GetMapping(path="/write")
	public String write(HttpSession session) {
		String loginUserId = (String)session.getAttribute("loginUserId");
		if(loginUserId == null) {
			return "redirect:posts";
		}
		
		return "write";
	}
	
	@PostMapping(path="/writeok")
	public String writeOk(HttpSession session, HttpServletRequest request) {
		String loginUserId = (String)session.getAttribute("loginUserId");
		if(loginUserId == null) {
			return "redirect:posts";
		}

		String title = request.getParameter("title");
		String content = request.getParameter("content");
		int userIndex = (Integer)session.getAttribute("loginUserIndex");
		
		Post post = new Post();
		post.setTitle(title);
		post.setContent(content);
		post.setCreateTime(new Date());
		post.setReadCount(0);
		post.setUserIndex(userIndex);

		boardService.writePost(post);
		
		return "writeok";
	}
	
	@GetMapping(path="/modify")
	public String modify(HttpSession session, ModelMap model) {
		int index = (int)session.getAttribute("postIndex");

		String loginUserId = (String)session.getAttribute("loginUserId");
		if(loginUserId == null) {
			return "redirect:posts";
		}

		Post post = boardService.getPost(index);
		int userIndex = post.getUserIndex();
		User user = boardService.getUser(userIndex);
		
		model.addAttribute("post", post);
		model.addAttribute("user", user);
		
		return "modify";
	}
	
	@PostMapping(path="/modifyok")
	public String modifyok(HttpSession session, HttpServletRequest request) {
		String loginUserId = (String)session.getAttribute("loginUserId");
		if(loginUserId == null) {
			return "redirect:posts";
		}

		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		int loginUserIndex = (Integer)session.getAttribute("loginUserIndex");
		int postIndex = (Integer)session.getAttribute("postIndex");
		
		Post post = new Post();
		post.setIndex(postIndex);
		post.setTitle(title);
		post.setContent(content);
		post.setCreateTime(new Date());
		post.setModifyTime(new Date());
		post.setReadCount(0);
		post.setUserIndex(loginUserIndex);
		
		boardService.modifyPost(post);
		
		return "modifyok";
	}
	
	@GetMapping(path="/delete")
	public String deletePost(HttpSession session, HttpServletRequest request) {
		String loginUserId = (String)session.getAttribute("loginUserId");
		if(loginUserId == null) {
			return "redirect:posts";
		}
		
		int loginUserIndex = (Integer)session.getAttribute("loginUserIndex");
		int postIndex = (Integer)session.getAttribute("postIndex");

		Post post = boardService.getPost(postIndex);
		if (loginUserIndex != post.getUserIndex()) {
			return "redirect:posts";
		}
		
		boardService.deletePost(postIndex);
		
		return "redirect:posts";
	}
	
	@GetMapping(path="/view_comments")
	public String viewComments() {
		return "view_comments";
	}
}
