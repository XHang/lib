package com.Blog.Ecxeprion;
/*
 * 用户无效访问异常
 */
@SuppressWarnings("serial")
public class UserPasswordError extends Exception{
	public UserPasswordError(String msg){
		super(msg);
	}

}
