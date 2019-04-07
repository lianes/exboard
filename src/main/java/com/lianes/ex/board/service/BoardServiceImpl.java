package com.lianes.ex.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lianes.ex.board.dao.CommentDao;
import com.lianes.ex.board.dao.PostDao;
import com.lianes.ex.board.dao.UserDao;
import com.lianes.ex.board.dto.Comment;
import com.lianes.ex.board.dto.CommentWithUser;
import com.lianes.ex.board.dto.Post;
import com.lianes.ex.board.dto.PostWithUser;
import com.lianes.ex.board.dto.User;

@Service
public class BoardServiceImpl implements BoardService {
	@Autowired
	PostDao postDao;
	
	@Autowired
	UserDao userDao;
	
	@Autowired
	CommentDao commentDao;
	
	@Override
	@Transactional
	public List<PostWithUser> getPosts(int start) {
		List<PostWithUser> posts = postDao.getPosts(start, BoardService.LIMIT);
		return posts;
	}
	
	@Override
	public int getCount() {
		return postDao.getAllPostCount();
	}
	
	@Override
	public User getUser(int userIndex) {
		return userDao.getUser(userIndex);
	}
	
	@Override
	public Post getPost(int postIndex) {
		return postDao.getPost(postIndex);
	}
	
	@Override
	@Transactional
	public int addPostCount(int postIndex) {
		return postDao.addPostCount(postIndex);
	}
	
	@Override
	@Transactional
	public int addUser(User newUser) {
		return userDao.addUser(newUser);
	}
	
	@Override
	public int loginUser(User user) {
		return userDao.loginUser(user);
	}
	
	@Override
	@Transactional
	public int writePost(Post post) {
		return postDao.writePost(post);
	}
	
	@Override
	@Transactional
	public int modifyPost(Post post) {
		return postDao.modifyPost(post);
	}
	
	@Override
	@Transactional
	public int deletePost(int postIndex) {
		return postDao.deletePost(postIndex);
	}
	
	@Override
	public List<CommentWithUser> getComments(int postIndex) {
		return commentDao.getComments(postIndex);
	}
	
	@Override
	@Transactional
	public int writeComment(Comment comment) {
		return commentDao.writeComment(comment);
	}
	
	@Override
	@Transactional
	public int deleteComment(int commentIndex) {
		return commentDao.deleteComment(commentIndex);
	}
	
	@Override
	@Transactional
	public int modifyComment(Comment comment) {
		return commentDao.modifyComment(comment);
	}
}
