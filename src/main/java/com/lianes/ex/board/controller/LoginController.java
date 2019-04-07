package com.lianes.ex.board.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.lianes.ex.board.dto.User;
import com.lianes.ex.board.service.BoardService;

@Controller
public class LoginController {
	@Autowired
	BoardService boardService;
	
	@GetMapping(path="/register")
	public String register() {
		return "register";
	}
	
	@PostMapping(path="/registerok")
	public String registerOk(HttpServletRequest request) {
		String id, name, email, password;
		id = request.getParameter("userId");
		name = request.getParameter("userName");
		email = request.getParameter("userEmail");
		password = request.getParameter("userPassword");

		User user = new User();
		user.setId(id);
		user.setName(name);
		user.setEmail(email);
		user.setPassword(password);
		
		boardService.addUser(user);
		
		return "redirect:login";
	}

	@GetMapping(path="/login")
	public String login() {
		return "login";
	}
	
	@PostMapping(path="/loginok")
	public String loginOk(HttpSession session, HttpServletRequest request, RedirectAttributes redirectAttr) throws Exception {
		String userId = request.getParameter("userId");
		String userPassword = request.getParameter("userPassword");
		User currentUser = new User();
		currentUser.setId(userId);
		currentUser.setPassword(userPassword);
		
		int userIndex = boardService.loginUser(currentUser);
		
		if(userIndex == 0) {
			redirectAttr.addFlashAttribute("isLoginFail", "true");
			return "redirect:login";
		}
		
		session.setAttribute("loginUserIndex", userIndex);
		session.setAttribute("loginUserId", userId);
		
		return "redirect:posts";
	}
	
	@GetMapping(path="/logout")
	public String logout(HttpSession session, HttpServletRequest request) {
		String userId = (String)session.getAttribute("loginUserId");
		
		session.removeAttribute("loginUserId");
		session.invalidate();
		
		return "redirect:posts";
	}
}
