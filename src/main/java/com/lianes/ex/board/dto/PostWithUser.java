package com.lianes.ex.board.dto;

import java.util.Date;

public class PostWithUser {
	// Post
	private int index;
	private String title;
	private String content;
	private Date createTime;
	private Date modifyTime;
	private int readCount;
	private int userIndex;
	// User
	private String id;
	private String name;
	private String email;
	private String password;

	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getModifyTime() {
		return modifyTime;
	}
	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}
	public int getReadCount() {
		return readCount;
	}
	public void setReadCount(int readCount) {
		this.readCount = readCount;
	}
	public int getUserIndex() {
		return userIndex;
	}
	public void setUserIndex(int userIndex) {
		this.userIndex = userIndex;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public String toString() {
		return "PostWithUser [index=" + index + ", title=" + title + ", content=" + content + ", createTime="
				+ createTime + ", modifyTime=" + modifyTime + ", readCount=" + readCount + ", userIndex=" + userIndex
				+ ", id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + "]";
	}
}
