package com.lianes.ex.board.dao;

import static com.lianes.ex.board.dao.BoardDaoSqls.*;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.lianes.ex.board.dto.User;

@Repository
public class UserDao {
	private NamedParameterJdbcTemplate jdbc;
	private SimpleJdbcInsert insertAction;
	
	public UserDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
		this.insertAction = new SimpleJdbcInsert(dataSource)
				.withTableName("user")
				.usingGeneratedKeyColumns("index");
	}
	
	public User getUser(Integer userIndex) {
		return jdbc.queryForObject(GET_USER_BY_INDEX, new MapSqlParameterSource("userIndex", userIndex), BeanPropertyRowMapper.newInstance(User.class));
	}

	public int addUser(User user) {
		SqlParameterSource params = new BeanPropertySqlParameterSource(user);
		int value = insertAction.executeAndReturnKey(params).intValue();
		return value;
	}
	
	public int loginUser(User user) {
		SqlParameterSource param = new MapSqlParameterSource()
				.addValue("userId", user.getId())
				.addValue("userPassword", user.getPassword());

		try {
			return jdbc.queryForObject(LOGIN_USER, param, Integer.class);
		} catch (EmptyResultDataAccessException e) {
			return 0;
		}
	}
}
