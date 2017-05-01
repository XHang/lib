package com.String.StringUitl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUitlTest {
	public static void main(String[] args) {
		String fileName=StringUitl.getFileNameByPath("D:\\xunlei\\MiniThunder\\Data\\TaskDb.dat");
		System.out.println("获取到文件名为"+fileName);
	}
}
