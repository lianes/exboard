package com.lianes.ex.board.dto;

import java.util.Date;

public class CommentWithUser {
	// Comment
	private int index;
	private String content;
	private Date createTime;
	private Date modifyTime;
	private int userIndex;
	private int postIndex;
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
	public int getUserIndex() {
		return userIndex;
	}
	public void setUserIndex(int userIndex) {
		this.userIndex = userIndex;
	}
	public int getPostIndex() {
		return postIndex;
	}
	public void setPostIndex(int postIndex) {
		this.postIndex = postIndex;
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
		return "CommentWithUser [index=" + index + ", content=" + content + ", createTime=" + createTime
				+ ", modifyTime=" + modifyTime + ", userIndex=" + userIndex + ", postIndex=" + postIndex + ", id=" + id
				+ ", name=" + name + ", email=" + email + ", password=" + password + "]";
	}
}
