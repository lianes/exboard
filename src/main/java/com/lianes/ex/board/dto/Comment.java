package com.lianes.ex.board.dto;

import java.util.Date;

public class Comment {
	private int index;
	private String content;
	private Date createTime;
	private Date modifyTime;
	private int userIndex;
	private int postIndex;
	
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

	@Override
	public String toString() {
		return "Comment [index=" + index + ", content=" + content + ", createTime=" + createTime + ", modifyTime="
				+ modifyTime + ", userIndex=" + userIndex + ", postIndex=" + postIndex + "]";
	}
}
