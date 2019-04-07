package com.lianes.ex.board.service;

import java.util.List;

import com.lianes.ex.board.dto.Comment;
import com.lianes.ex.board.dto.CommentWithUser;
import com.lianes.ex.board.dto.Post;
import com.lianes.ex.board.dto.PostWithUser;
import com.lianes.ex.board.dto.User;

public interface BoardService {
	public static final int LIMIT = 5;
	public List<PostWithUser> getPosts(int start);
	public int getCount();
	public User getUser(int userIndex);
	public Post getPost(int postIndex);
	public int addPostCount(int postIndex);
	public int addUser(User newUser);
	public int loginUser(User user);
	public int writePost(Post post);
	public int modifyPost(Post post);
	public int deletePost(int postIndex);
	public List<CommentWithUser> getComments(int postIndex);
	public int writeComment(Comment comment);
	public int deleteComment(int commentIndex);
	public int modifyComment(Comment comment);
}
