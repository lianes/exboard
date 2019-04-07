package com.lianes.ex.board.dao;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.lianes.ex.board.config.ApplicationConfig;
import com.lianes.ex.board.config.DBConfig;
import com.lianes.ex.board.dto.CommentWithUser;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={ApplicationConfig.class, DBConfig.class})
@WebAppConfiguration
public class CommentDaoTest {
	@Autowired
	CommentDao commentDao;
	
	@Test
	public void getComments() throws Exception {
		List<CommentWithUser> testList = commentDao.getComments(10);
		
		for(CommentWithUser cwu : testList) {
			System.out.println("**** testList result **** " + cwu.toString());
		}
	}
}
