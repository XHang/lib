package com.Blog.Dao;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.Blog.Model.Category;
import com.Blog.Service.CategoryService;



public class Test_ArticleDAO {
	//测试从数据库取出的日期类型有没有转成功
	@Test
	public void Test_Article_date(){
		Pattern p=Pattern.compile("(\\.\\w{3,})$");		//匹配后缀名
		Matcher m=p.matcher("006D2KSdly1fbv5mzog15j30tz0zlqtl.jpg");
		m.find();
		String suffixName=m.group();
		System.out.println(suffixName);
		
		
	}
}
