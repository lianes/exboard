package com.lianes.ex.board.config;

import java.sql.Connection;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={ApplicationConfig.class, DBConfig.class})
@WebAppConfiguration
public class DBConfigTest {

	@Autowired
	DataSource dataSource;
	
	@Test
	public void connectionTest() throws Exception {
		Connection conn = dataSource.getConnection();
		if(conn != null)
			System.out.println("Connection test !!!");
		conn.close();
	}
}
