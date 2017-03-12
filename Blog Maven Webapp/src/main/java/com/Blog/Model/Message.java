package com.Blog.Model;

public class Message {
	private User user;
	private User Sender;
	private String msg;
	private boolean remind;
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public User getSender() {
		return Sender;
	}
	public void setSender(User sender) {
		Sender = sender;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public boolean isRemind() {
		return remind;
	}
	public void setRemind(boolean remind) {
		this.remind = remind;
	}
}
