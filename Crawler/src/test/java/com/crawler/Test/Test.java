package com.crawler.Test;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import com.crawler.Enum.TranslateType;
import com.crawler.Result.BaiduPic;
import com.crawler.Result.Translation;

public class Test { 
	public static void main(String[] args) throws Exception {
		BaiduPic.downloadBaiduPic("学生制服",1);
		/*Pattern reg=Pattern.compile("<(.{6,7})>|\\s");
		Matcher p=reg.matcher("clix<strong>icle</strong> customized 7inx9in");
		p.find();
		System.out.println(p.group());
		p.find();
		System.out.println(p.group());*/
		
	}
   
}
