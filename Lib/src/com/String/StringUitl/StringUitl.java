package com.String.StringUitl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUitl {
	/**
	 * 提供一个文件名，获取这个文件的类型。
	 * 即是获取后缀名
	 * @param fileName
	 */
	public static String getFileNameSuffix(String fileName){
	    Pattern reg=Pattern.compile("\\.\\w{3,4}$");
		Matcher p=reg.matcher(fileName);
		p.find();
		return p.group();
	}
}
