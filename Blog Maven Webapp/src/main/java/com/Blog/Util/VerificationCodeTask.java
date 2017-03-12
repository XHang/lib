package com.Blog.Util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.Blog.Dao.UserDAO;
import com.Blog.Model.User;

/*
 * 这个类是一个多线程类，用于校验验证码的有效性
 */
public class VerificationCodeTask  implements Runnable{
	private int id;				//保存注册时的用户id
	public VerificationCodeTask(int id){
		this.id=id;
	}
	@Override
	public void run() {
		try {
			Thread.sleep(60000);//睡大概5分钟，间途如果有用户触发验证邮箱，则线程强行结束:测试，1分钟则更改激活码
			ApplicationContext context=new ClassPathXmlApplicationContext("Spring.xml");
			UserDAO dao=context.getBean(UserDAO.class);		
			User user=dao.selectUserById(id);				//查询注册用户信息；
			if(!user.isValid()){							//如果用户仍是无效，说明邮箱没被验证，验证码要失效
				User lastinguser=new User();				//再建对象是因为要只保留两个字段去更新。
				lastinguser.setId(user.getId());
				String md5 =MD5Util.MD5(user.getUsername()+System.currentTimeMillis());
				lastinguser.setActivationCode(md5);
				dao.updateUser(lastinguser);  				//把新的验证码存回去
				
			}

		} catch (InterruptedException e) {
			System.out.println("中途退出");
		}
	}

}
