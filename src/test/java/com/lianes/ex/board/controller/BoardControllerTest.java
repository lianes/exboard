package com.lianes.ex.board.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.lianes.ex.board.config.ApplicationConfig;
import com.lianes.ex.board.config.DBConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={ApplicationConfig.class, DBConfig.class})
@WebAppConfiguration
public class BoardControllerTest {

	@Autowired
	WebApplicationContext context;
	
	private MockMvc mockMvc;

	@Test
	public void viewContent() throws Exception {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
		mockMvc.perform(MockMvcRequestBuilders.get("/board/content/1")); // 확인 필요
	}
}
