package com.Blog.Ecxeprion;
/*
 * 用户无效访问异常
 */
@SuppressWarnings("serial")
public class UserInvalidAccess extends Exception{
	public UserInvalidAccess(String msg){
		super(msg);
	}

}
