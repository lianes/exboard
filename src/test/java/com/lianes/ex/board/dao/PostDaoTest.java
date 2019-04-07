package com.lianes.ex.board.dao;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.lianes.ex.board.config.ApplicationConfig;
import com.lianes.ex.board.config.DBConfig;
import com.lianes.ex.board.dto.Post;
import com.lianes.ex.board.dto.User;

import junit.framework.Assert;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={ApplicationConfig.class, DBConfig.class})
@WebAppConfiguration
public class PostDaoTest {

	@Autowired
	PostDao postDao;
	
	@Test
	public void getPurePosts() throws Exception {
		List<Post> testList = postDao.getPurePosts(0,1);
		Assert.assertEquals(testList.size(), 1);
		
		for (Post post : testList) {
			System.out.println("Post: " + post.toString());
		}
	}
	
	@Test
	public void getPost() throws Exception {
		Post post = postDao.getPost(1);
	}
	
	@Test
	public void writePost() throws Exception {
		Post post = new Post();
		post.setTitle("제목 테스트");
		post.setContent("내용 테스트");
		post.setCreateTime(new Date());
		post.setReadCount(3);
		post.setUserIndex(1);
		
		int value = postDao.writePost(post);
	}
	
	@Test
	public void modifyPost() throws Exception {
		Post post = new Post();
		post.setTitle("제목2");
		post.setContent("내용2");
		post.setCreateTime(new Date());
		post.setModifyTime(new Date());
		post.setReadCount(0);
		post.setIndex(1);
		
		System.out.println("Post: " + post.toString());
		postDao.modifyPost(post);
	}
	
	@Test
	public void deletePost() throws Exception {
		postDao.deletePost(5);
	}
}
