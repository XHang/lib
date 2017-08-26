package com.lib.XStreamUitl;

import org.junit.Test;

import com.test.model.UserBean;
import com.thoughtworks.xstream.XStream;

public class XStreamNullConverterTest {
	@Test
	public void testXSream(){
		UserBean user = new UserBean();
		user.setAge("22");
		user.setUsername("老王");
		//将不设置两个属性，身高，密码，查看下面的实体类转xml时空属性是不是转成了空标签
	}
}
