package com.lianes.ex.board.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.lianes.ex.board.config.ApplicationConfig;
import com.lianes.ex.board.config.DBConfig;
import com.lianes.ex.board.dto.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={ApplicationConfig.class, DBConfig.class})
@WebAppConfiguration
public class UserDaoTest {
	@Autowired
	UserDao userDao;
	
	@Test
	public void addUser() throws Exception {
		User testUser = new User();
		testUser.setId("Park");
		testUser.setName("Tester Park");
		testUser.setEmail("testerpark@test.com");
		testUser.setPassword("1234");
		
		userDao.addUser(testUser);
	}
	
	@Test
	public void getUser() throws Exception {
		User user = userDao.getUser(1);
		System.out.println("User: " + user.toString());
	}

	@Test
	public void loginUser() throws Exception {
		User testUser = new User();
		testUser.setId("Lee");
		testUser.setPassword("1234");
		
		int result = userDao.loginUser(testUser);
		System.out.println("Login 결과: " + result);
	}
}
