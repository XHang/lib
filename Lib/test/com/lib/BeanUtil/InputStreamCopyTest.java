package com.lib.BeanUtil;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.List;

import com.lib.StreamUitl.StreamUitl;

/**
 * 测试类
 * @author Mr-hang
 *
 */
public class InputStreamCopyTest {
	public static  void inputStreamCopyTest() throws Exception {
	   InputStream in=new FileInputStream("D:\\test\\test.gif");
	   InputStream a=InputStreamCopy.getBeanCopyObject().inputStreamCopy(in);
	   InputStream b=InputStreamCopy.getBeanCopyObject().inputStreamCopy(in);
	   StreamUitl.saveFileByStream(a, "D:\\test\\1.gif");
	   StreamUitl.saveFileByStream(b, "D:\\test\\2.gif");
	   in.close();
	   
	}
	public static void main(String[] args) throws Exception {
		inputStreamCopyTest();
	}
}