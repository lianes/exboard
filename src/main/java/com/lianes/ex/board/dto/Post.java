package com.lianes.ex.board.dto;

import java.util.Date;

public class Post {
	private int index;
	private String title;
	private String content;
	private Date createTime;
	private Date modifyTime;
	private int readCount;
	private int userIndex;
	
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
	
	@Override
	public String toString() {
		return "Post [index=" + index + ", title=" + title + ", content=" + content + ", createTime=" + createTime
				+ ", modifyTime=" + modifyTime + ", readCount=" + readCount + ", userIndex=" + userIndex + "]";
	}
}
