package com.lianes.ex.board.dao;

public class BoardDaoSqls {
	public static final String GET_PURE_POSTS = "SELECT * FROM post ORDER BY `index` DESC limit :start, :limit";
	public static final String GET_ALL_POST_COUNT = "SELECT count(*) FROM post";
	public static final String GET_USER_BY_INDEX = "SELECT * FROM user WHERE `index` = :userIndex";
	public static final String GET_POSTS = "SELECT post.index, post.title, post.content, post.create_time, post.modify_time, post.read_count, post.user_index,user.id, user.name, user.email, user.password FROM post JOIN user ON post.user_index = user.index ORDER BY post.index DESC LIMIT :start, :limit";
	public static final String GET_POST_BY_INDEX = "SELECT post.index, post.title, post.content, post.create_time, post.modify_time, post.read_count, post.user_index FROM post WHERE `index` = :postIndex";
	public static final String ADD_POST_COUNT = "UPDATE post SET read_count = read_count + 1 WHERE `index` = :postIndex";
	public static final String ADD_MEMBER = "INSERT INTO user (id, name, email, password) VALUES (':userId',':userName',':userEmail',':userPassword')";
	public static final String LOGIN_USER = "SELECT user.index FROM user WHERE BINARY id=:userId AND password=:userPassword";
	public static final String WRITE_POST = "INSERT INTO post (title,content,create_time,read_count,user_index) VALUES (':title',':content',':create_time',':read_count',':user_index')";
	public static final String MODIFY_POST = "UPDATE post SET `title`=:title, `content`=:content, `modify_time`=:modify_time WHERE `index`=:index";
	public static final String DELETE_POST = "DELETE FROM post WHERE `index`=:postIndex";
	public static final String GET_COMMENTS = "SELECT comment.index, comment.content, comment.create_time, comment.modify_time, comment.user_index, comment.post_index, user.id, user.name, user.email, user.password FROM comment JOIN user ON comment.user_index = user.index WHERE post_index = :postIndex ORDER BY comment.index DESC";
	public static final String WRITE_COMMENT = "INSERT INTO comment (content, create_time, user_index, post_index) VALUES (':content',':createTime',':userIndex',':postIndex')";
	public static final String DELETE_COMMENT = "DELETE FROM comment WHERE `index`=:commentIndex";
	public static final String MODIFY_COMMENT = "UPDATE comment SET content=:content, modify_time=:modify_time WHERE `index`=:index";
}
