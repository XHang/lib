package com.Blog.Ecxeprion;
/*
 * 用户找不到异常
 */
@SuppressWarnings("serial")
public class UserNotFound extends Exception{
	public UserNotFound(String msg){
		super(msg);
	}

}
