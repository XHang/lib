package com.test.model;
/**
 * 该类主要演示XStream的空属性转换问题
 * @author Administrator
 *
 */
public class UserBean {
	private String age;
	private String username;
	private String password;
	private String height;
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getHeight() {
		return height;
	}
	public void setHeight(String height) {
		this.height = height;
	}
}
