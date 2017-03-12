package com.Blog.Dao;

import com.Blog.Model.User;

public interface UserDAO {
	public void saveUser (User user) ;
	public int isExist(User user);
	public User selectUserById(int id);
	public void updateUserValid(User user);
	public User selectUser(String username);
	public void updateMood(User user);
	public void updateUser(User user);
}
