package com.Blog.Model;

import java.util.Date;

public class Discuss {
	private int id;
	private Discuss parent;
	private String context;
	private Date data;
	private User user;
	private Article article;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Discuss getParent() {
		return parent;
	}
	public void setParent(Discuss parent) {
		this.parent = parent;
	}
	public String getContext() {
		return context;
	}
	public void setContext(String context) {
		this.context = context;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Article getArticle() {
		return article;
	}
	public void setArticle(Article article) {
		this.article = article;
	}
	
}
