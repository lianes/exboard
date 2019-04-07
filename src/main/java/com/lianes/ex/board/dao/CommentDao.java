package com.lianes.ex.board.dao;

import static com.lianes.ex.board.dao.BoardDaoSqls.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.lianes.ex.board.dto.Comment;
import com.lianes.ex.board.dto.CommentWithUser;
import com.lianes.ex.board.dto.Post;

@Repository
public class CommentDao {
	private NamedParameterJdbcTemplate jdbc;
	private SimpleJdbcInsert insertAction;
	private RowMapper<CommentWithUser> rowMapperForCommentWithUser
		= BeanPropertyRowMapper.newInstance(CommentWithUser.class);
	
	public CommentDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
		this.insertAction = new SimpleJdbcInsert(dataSource)
				.withTableName("comment")
				.usingGeneratedKeyColumns("index");
	}
	
	public List<CommentWithUser> getComments(int postIndex) {
		Map<String, Integer> params = new HashMap<String, Integer>();
		params.put("postIndex", postIndex);
		return jdbc.query(GET_COMMENTS, params, rowMapperForCommentWithUser);
	}
	
	public int writeComment(Comment comment) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("content", comment.getContent());
		params.put("create_time", comment.getCreateTime());
		params.put("modify_time", comment.getModifyTime());
		params.put("user_index", comment.getUserIndex());
		params.put("post_index", comment.getPostIndex());
		int commentIndex = insertAction.executeAndReturnKey(params).intValue();
		return commentIndex;
	}
	
	public int deleteComment(int commentIndex) {
		Map<String, ?> params = Collections.singletonMap("commentIndex", commentIndex);
		return jdbc.update(DELETE_COMMENT, params);
	}
	
	public int modifyComment(Comment comment) {
		SqlParameterSource params = new MapSqlParameterSource()
				.addValue("index", comment.getIndex())
				.addValue("content", comment.getContent())
				.addValue("create_time", comment.getCreateTime())
				.addValue("modify_time", comment.getModifyTime())
				.addValue("user_index", comment.getUserIndex())
				.addValue("post_index", comment.getPostIndex());
		int resultValue = jdbc.update(MODIFY_COMMENT, params);
		return resultValue;
	}
}
