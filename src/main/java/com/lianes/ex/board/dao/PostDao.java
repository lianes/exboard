package com.lianes.ex.board.dao;

import static com.lianes.ex.board.dao.BoardDaoSqls.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.lianes.ex.board.dto.Post;
import com.lianes.ex.board.dto.PostWithUser;
import com.lianes.ex.board.dto.User;

@Repository
public class PostDao {
	
	private NamedParameterJdbcTemplate jdbc;
	private SimpleJdbcInsert insertAction;
	private RowMapper<Post> rowMapper = BeanPropertyRowMapper.newInstance(Post.class);
	private RowMapper<PostWithUser> rowMapperForPostWithUser = BeanPropertyRowMapper.newInstance(PostWithUser.class);
	
	public PostDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
		this.insertAction = new SimpleJdbcInsert(dataSource)
				.withTableName("post")
				.usingGeneratedKeyColumns("index");
	}
	
	public List<Post> getPurePosts(Integer start, Integer limit) {
		Map<String, Integer> params = new HashMap<>();
		params.put("start", start);
		params.put("limit", limit);
		return jdbc.query(GET_PURE_POSTS, params, rowMapper);
	}

	public List<PostWithUser> getPosts(Integer start, Integer limit) {
		Map<String, Integer> params = new HashMap<String, Integer>();
		params.put("start", start);
		params.put("limit", limit);
		return jdbc.query(GET_POSTS, params, rowMapperForPostWithUser);
	}
	
	public int getAllPostCount() {
		return jdbc.queryForObject(GET_ALL_POST_COUNT, Collections.emptyMap(), Integer.class);
	}
	
	public Post getPost(Integer postIndex) {
		return jdbc.queryForObject(GET_POST_BY_INDEX, new MapSqlParameterSource("postIndex", postIndex), BeanPropertyRowMapper.newInstance(Post.class));
	}
	
	public int addPostCount(Integer postIndex) {
		return jdbc.update(ADD_POST_COUNT, new MapSqlParameterSource("postIndex", postIndex));
	}
	
	public int writePost(Post post) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("title", post.getTitle());
		params.put("content", post.getContent());
		params.put("create_time", post.getCreateTime());
		params.put("read_count", post.getReadCount());
		params.put("user_index", post.getUserIndex());
		int value = insertAction.executeAndReturnKey(params).intValue();
		return value;
	}
	
	public int modifyPost(Post post) {
		SqlParameterSource params = new MapSqlParameterSource()
				.addValue("index", post.getIndex())
				.addValue("title", post.getTitle())
				.addValue("content", post.getContent())
				.addValue("create_time", post.getCreateTime())
				.addValue("modify_time", post.getModifyTime())
				.addValue("read_count", post.getReadCount())
				.addValue("user_index", post.getUserIndex());
		int value = jdbc.update(MODIFY_POST, params);
		return value;
	}
	
	public int deletePost(int postIndex) {
		Map<String, ?> params = Collections.singletonMap("postIndex", postIndex);
		return jdbc.update(DELETE_POST, params);
	}
}
